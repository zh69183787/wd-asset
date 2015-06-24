package com.wonders.asset.web.action;


import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.base.web.AbstractBaseAction;
import com.wonders.asset.model.*;
import com.wonders.asset.service.*;
import com.wonders.framework.util.ServiceProvider;

import net.sf.json.JsonConfig;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.io.*;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;



/**
 * Created by HH on 2014/11/17.
 */
public class LandAssetAction extends AbstractBaseAction {
    private LandAssetService landAssetService;
    private StationService stationService;
   private AssetTypeService assetTypeService;
    private OrganizationService organizationService;
    private LineService lineService;

    public LineService getLineService() {
        return lineService;
    }

    public void setLineService(LineService lineService) {
        this.lineService = lineService;
    }

    public OrganizationService getOrganizationService() {
        return organizationService;
    }

    public void setOrganizationService(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    public AssetTypeService getAssetTypeService() {
        return assetTypeService;
    }

    public void setAssetTypeService(AssetTypeService assetTypeService) {
        this.assetTypeService = assetTypeService;
    }

    public StationService getStationService() {
        return stationService;
    }

    public void setStationService(StationService stationService) {
        this.stationService = stationService;
    }
    public LandAssetService getLandAssetService() {
        return landAssetService;
    }

    public void setLandAssetService(LandAssetService landAssetService) {
        this.landAssetService = landAssetService;
    }

    /**
     * 查询方法
     */
    public void inquery() throws Exception{
        Map<String,String> filter = createFilterMap();
        int startIndex = NumberUtils.toInt(getRequestParameter("jtStartIndex"), 0);
        int pageSize = NumberUtils.toInt(getRequestParameter("jtPageSize"), 10);
        Map<String,String> sort = createSortMap();
        Pagination<LandAsset> landAssets = landAssetService.findBy(filter,sort,startIndex,pageSize);
        List<Line> lines = ServiceProvider.getService(LineService.class).findAllByPublish("1");
        int i = startIndex + 1;
        for(LandAsset landAsset : landAssets.getResult()){
        	landAsset.setNo(i);
        	for(Line line : lines){
        		if(line.getId().equals(landAsset.getLineCodeId())){
        			landAsset.setLineCodeId(line.getShortName());
        			break;
        		}
        	}
        	i++;
        }
        JsonConfig jsonConfig = basicJsonCfg.copy();
        jsonConfig.registerPropertyExclusions(LandAsset.class, new String[]{"asset"});
        renderJson(landAssets.getResult(), landAssets.getTotalCount(), jsonConfig);
    }

    /**
     * 查询详细土地资产
     */
     public String showAssetLandView() throws IOException {
         String id=request.getParameter("id");
        LandAsset landAsset= landAssetService.findByPrimaryKey(id);
         String code= landAsset.getLocationNo();
        if(code!=null){
            String lineCode=code.substring(0,2);
            String stationCode=code.substring(2);
            Line line= lineService.findByCode(lineCode);
            Station station=stationService.findByCodeAndLineId(stationCode,line.getId());
            String location=line.getName()+"   "+station.getName();
            request.setAttribute("location",location);
        }
         if(landAsset.getAssetType()!=null) {
             String assetTypeCode = landAsset.getAssetType();

             AssetType assetType = assetTypeService.findByAllCode(assetTypeCode);
             String assetTypeName = assetType == null ? assetTypeCode : assetType.getName();
             request.setAttribute("assetTypename", assetTypeName);
         }else {
             request.setAttribute("assetTypename", "");
         }
         if(landAsset.getOwnershipUnit()!=null){
             String ownershipId=landAsset.getOwnershipUnit();
             Organization organization=organizationService.findById(ownershipId);
             String organizatioName=organization == null ? "" :organization.getName();
             request.setAttribute("organizatioName",organizatioName);
         }else{
             request.setAttribute("organizatioName","");
         }

         request.setAttribute("landAsset",landAsset);

         return "success";
     }
     
     /**
      * 为空时返回空字符
      * @param obj
      * @return
      */
     String setNotNull(String obj){
      	return obj == null ? "":obj;
      }
      
     /**
      * 为空时返回0
      * @param obj
      * @return
      */
      Double setNotNull(Double obj){
       	return obj == null ? 0:obj;
       }

    //得到保留2位小数后的字符串
  	public String getFormattedMoney(String money){
  		if(money==null || "".equals(money)){
  			money = "0";
  		}
  		Double result = 0d;
  		try {
  			result = Double.valueOf(money);// /10000
  		} catch (NumberFormatException e) {
  			result = 0d;
  		}
  		DecimalFormat df = new DecimalFormat("#0.00");
  		if("0.00".equals(df.format(result))){
  			return "0";
  		}
  		return df.format(result);
  	}
      
    /**
     * 导出土地资源
     */

    public void excelLandAsset(){
        String name="土地资源台帐.xls";
        response.setContentType("application/x-download");
        response.setCharacterEncoding("utf-8");
        try {
         	response.setHeader("Content-Disposition", "attachment;filename="+new String(name.getBytes("GBK"), "ISO8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Map<String,String> filter = createFilterMap();
        Map<String,String> sort = createSortMap();
        List<LandAsset> list=landAssetService.findByWithoutCount(filter,sort);
        
        Integer rowNumber = list.size();
        String[] title=new String[]{"线路","建设类项目 ","土地坐落","项目名称","用地批准文号","土地性质","规划土地用途","建筑面积(平方米)","土地总面积(平方米)","其中地面征地（平方米）","其中待征地（平方米）","征地动迁总费用","征地动迁费用","带征地费用","使用管理人","有无闲置或出租","闲置或出租场地（平方米）","备注"};
        try {
            // 创建新的Excel 工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet();
//            sheet.setDefaultColumnWidth((short)15);
            // 创建第一行并设置标题
            HSSFRow titleRow = sheet.createRow(0);
            for(int i=0; i<title.length; i++){
                titleRow.createCell(i).setCellValue(title[i]);
            }

            // 创建单元格并为单元格赋值
            for(int i=0; i<rowNumber; i++){
                HSSFRow row = sheet.createRow(i+1);
                HSSFCell cell1=row.createCell(0);
                cell1.setCellValue(setNotNull(list.get(i).getLineCodeId()));
                HSSFCell cell2=row.createCell(1);
                cell2.setCellValue(setNotNull(list.get(i).getBuilderProject()));
                HSSFCell cell3=row.createCell(2);
                cell3.setCellValue(setNotNull(list.get(i).getLandLocation()));
                HSSFCell cell4=row.createCell(3);
                cell4.setCellValue(setNotNull(list.get(i).getProject()));
                HSSFCell cell5=row.createCell(4);
                cell5.setCellValue(setNotNull(list.get(i).getApproveNo()));
                HSSFCell cell6=row.createCell(5);
                cell6.setCellValue(setNotNull(list.get(i).getLandStatus()));
                HSSFCell cell7=row.createCell(6);
                cell7.setCellValue(setNotNull(list.get(i).getLandPlaning()));
                HSSFCell cell8=row.createCell(7);
                cell8.setCellValue(setNotNull(list.get(i).getBuildArea()));
                HSSFCell cell9=row.createCell(8);
                cell9.setCellValue(setNotNull(list.get(i).getLandTotalArea()));
                HSSFCell cell10=row.createCell(9);
                cell10.setCellValue(setNotNull(list.get(i).getLandRequisitionArea()));
                HSSFCell cell11=row.createCell(10);
                cell11.setCellValue(setNotNull(list.get(i).getInclandRequisitionArea()));
                HSSFCell cell12=row.createCell(11);
                cell12.setCellValue(setNotNull(list.get(i).getLandTotalFee()));
                HSSFCell cell13=row.createCell(12);
                if(list.get(i).getLandRequisitionTotalfee()==null){
                    cell13.setCellValue("0");
                }else{
                    cell13.setCellValue(list.get(i).getLandRequisitionTotalfee());
                }
                HSSFCell cell14=row.createCell(13);
                if(list.get(i).getInlandRequisitionTotalfee()==null){
                    cell14.setCellValue("0");
                }else {
                    cell14.setCellValue(list.get(i).getInlandRequisitionTotalfee());
                }
                HSSFCell cell15=row.createCell(14);
                cell15.setCellValue(setNotNull(list.get(i).getUseManager()));
                HSSFCell cell16=row.createCell(15);
                cell16.setCellValue(setNotNull(list.get(i).getHasOpenspace()));
                HSSFCell cell17=row.createCell(16);
                cell17.setCellValue(setNotNull(list.get(i).getOpenSpacearea()));
                HSSFCell cell18=row.createCell(17);
                cell18.setCellValue(setNotNull(list.get(i).getNote()));
            }

            OutputStream out = response.getOutputStream();
            workbook.write(out);

            out.flush();
            out.close();
//            response.sendRedirect("");
        } catch (Exception e) {
            System.out.println("Exception：" + e);
        }
    }

    public String findReport(){
        List<Map<String,String>> list=landAssetService.findReport();

        try {
            request.setAttribute("list",list);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

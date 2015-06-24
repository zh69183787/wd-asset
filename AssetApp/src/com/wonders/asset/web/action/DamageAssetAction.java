package com.wonders.asset.web.action;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import net.sf.json.JsonConfig;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.base.web.AbstractBaseAction;
import com.wonders.asset.model.DamageAsset;
import com.wonders.asset.service.DamageAssetService;

/**
 * Created by HH on 2014/11/11.
 */
public class DamageAssetAction extends AbstractBaseAction {
    private DamageAssetService damageAssetService;
	public DamageAssetService getDamageAssetService() {
        return damageAssetService;
    }

    public void setDamageAssetService(DamageAssetService damageAssetService) {
        this.damageAssetService = damageAssetService;
    }


    /**
     * 查询方法
     * @throws Exception
     */
    public void inquery() throws Exception {
        Map<String,String> filter = createFilterMap();
        int startIndex = NumberUtils.toInt(getRequestParameter("jtStartIndex"), 0);
        int pageSize = NumberUtils.toInt(getRequestParameter("jtPageSize"), 10);
        Map<String,String> sort = createSortMap();
        Pagination<DamageAsset> damageAsset =damageAssetService.findBy(filter,sort,startIndex,pageSize);
        JsonConfig jsonConfig = basicJsonCfg.copy();
        jsonConfig.registerPropertyExclusions(DamageAsset.class, new String[]{"asset"});
        renderJson(damageAsset.getResult(), damageAsset.getTotalCount(), jsonConfig);
    }
    
    /**
     * 查询台账
     * @throws Exception
     */
    public void inqueryAsset() throws Exception{
    	String damageNo = request.getParameter("damageNo");
    	Map<String,String> filter = createFilterMap();
    	filter.put("damageNo_equal", damageNo);
        int startIndex = NumberUtils.toInt(getRequestParameter("jtStartIndex"), 0);
        int pageSize = NumberUtils.toInt(getRequestParameter("jtPageSize"), 10);
        Map<String,String> sort = createSortMap();
        Pagination<DamageAsset> damageAssets =damageAssetService.findDamageAndAsset(filter, sort, startIndex, pageSize);
        int no = startIndex+1;
        for(DamageAsset damageAsset :damageAssets.getResult()){
        	damageAsset.setNo(no);
      		no++;
        }
        renderJson(damageAssets.getResult(), damageAssets.getTotalCount());
    }
    
    /**
     * 导出
     */
    public void excelDamageAsset(){
    	String name="资产报废台账.xls";
        response.setContentType("application/x-download");
        response.setCharacterEncoding("utf-8");
        try {
         	response.setHeader("Content-Disposition", "attachment;filename="+new String(name.getBytes("GBK"), "ISO8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        
    	String damageNo = request.getParameter("damageNo");
    	Map<String,String> filter = createFilterMap();
    	filter.put("damageNo_equal", damageNo);
        int startIndex = NumberUtils.toInt(getRequestParameter("jtStartIndex"), 0);
        int pageSize = NumberUtils.toInt(getRequestParameter("jtPageSize"), 10);
        Map<String,String> sort = createSortMap();
        Pagination<DamageAsset> damageAssets =damageAssetService.findDamageAndAsset(filter, sort, startIndex, pageSize);
        
        Integer rowNumber = damageAssets.getResult().size();
		String[] title = new String[]{
				"项目名称","项目编号","项目合同编码","资产编码","资产名称","系统大类(必填)","系统中类(必填)",
				"系统小类(必填)","数量(必填)","规格型号(必填)(200)","产地(50)","生产厂商(全称)(100)",
				"供应商(全称)(必填)(100)","出厂日期(yyyy/mm/dd)",
				"供应日期(yyyy/mm/dd)","安装地点(必填)(200)","权属单位(资产权属单位代码)(必填)",
				"使用单位(使用权属单位代码)(必填)","维护部门(资产维护部门代码)(必填)",
				"所属线路(线路<网络>号代码)(必填)","所属车站(所属线路车站、停车场或车辆段代码)(必填)","权属责任人",
				"使用责任人","开始使用时间(yyyy/mm/dd)(必填)","停止使用时间(yyyy/mm/dd)","批准报废时间(yyyy/mm/dd)",
				"移交时间(即项目建成移交运营单位或维保中心)(yyyy/mm/dd)(必填)","当前使用状态(使用/停用/封存/报废/待报废)(必填)",
				"资产图片名称(如有)","设计使用限()(必填)","预期使用寿命()","保修期至(yyyy/mm/dd)","大修频次(/次)(必填)",
				"出厂价","合同价","原值",//"技术、规格、操作资料及清单（有无）",
				"折旧方法","累计折旧","资产净值(元)","铭牌张贴位置(左上/左下/中/右上/右下/铭牌板)","设备清单(2000)",
				"项目(线路)竣工移交资产类型标示(初始/新增/更新)(必填)","立项或可研批复文号","资产备注",
				"资产年限","已使用年限","报废理由","类别","报废备注"
				,"单位(必填)"
				};
		try {        	
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet();

            HSSFRow titleRow = sheet.createRow(0);
            for(int i=0; i<title.length; i++){
                titleRow.createCell(i).setCellValue(title[i]);
            }
            
            for(int i=0; i<rowNumber; i++){
                HSSFRow row = sheet.createRow(i+1);
                HSSFCell cell0=row.createCell(0);
            	cell0.setCellValue(setNotNull(damageAssets.getResult().get(i).getAsset().getProject().getProjectName()));
            	HSSFCell cell1=row.createCell(1);
            	cell1.setCellValue(setNotNull(damageAssets.getResult().get(i).getAsset().getProjectNo()));
            	HSSFCell cell2=row.createCell(2);
            	cell2.setCellValue(setNotNull(damageAssets.getResult().get(i).getAsset().getProjectContractNo()));
            	HSSFCell cell3=row.createCell(3);
            	cell3.setCellValue(setNotNull(damageAssets.getResult().get(i).getAsset().getAssetCode()));
            	HSSFCell cell4=row.createCell(4);
            	cell4.setCellValue(setNotNull(damageAssets.getResult().get(i).getAsset().getName()));
            	HSSFCell cell5=row.createCell(5);
            	cell5.setCellValue(setNotNull(damageAssets.getResult().get(i).getAsset().getMainTypeCodeId()));
            	HSSFCell cell6=row.createCell(6);
            	cell6.setCellValue(setNotNull(damageAssets.getResult().get(i).getAsset().getSubTypeCodeId()));
            	HSSFCell cell7=row.createCell(7);
            	cell7.setCellValue(setNotNull(damageAssets.getResult().get(i).getAsset().getDetailTypeCodeId()));
            	HSSFCell cell8=row.createCell(8);
            	if(damageAssets.getResult().get(i).getAsset().getCount()==null)
            		cell8.setCellValue("");
            	else
            		cell8.setCellValue(setNotNull(damageAssets.getResult().get(i).getAsset().getCount()+""));
            	HSSFCell cell9=row.createCell(9);
            	cell9.setCellValue(setNotNull(damageAssets.getResult().get(i).getAsset().getType()));
            	HSSFCell cell10=row.createCell(10);
            	cell10.setCellValue(setNotNull(damageAssets.getResult().get(i).getAsset().getManufactureCountry()));
            	HSSFCell cell11=row.createCell(11);//asset.manufacturer.name 供应商
            	cell11.setCellValue(setNotNull(damageAssets.getResult().get(i).getAsset().getManufacturer().getName()));
            	HSSFCell cell12=row.createCell(12);
            	cell12.setCellValue(setNotNull(damageAssets.getResult().get(i).getAsset().getManufacturer().getId()));
            	HSSFCell cell13=row.createCell(13);
            	if(damageAssets.getResult().get(i).getAsset().getMadeDate()==null)
            		cell13.setCellValue("");
            	else
            		cell13.setCellValue(setNotNull(damageAssets.getResult().get(i).getAsset().getMadeDate()+""));
            	
            	HSSFCell cell14=row.createCell(14);
            	if(damageAssets.getResult().get(i).getAsset().getUseDate()==null)
            		cell14.setCellValue("");
            	else
            		cell14.setCellValue(setNotNull(damageAssets.getResult().get(i).getAsset().getUseDate()+""));
            	HSSFCell cell15=row.createCell(15);
            	cell15.setCellValue(setNotNull(damageAssets.getResult().get(i).getAsset().getDetailInstallSite()));
            	HSSFCell cell16=row.createCell(16);//权属单位(O)
            	cell16.setCellValue(setNotNull(damageAssets.getResult().get(i).getAssetOwner().getOwnerOrganizationCodeId()));
            	HSSFCell cell17=row.createCell(17);//使用单位
            	cell17.setCellValue(setNotNull(damageAssets.getResult().get(i).getAssetOwner().getOwnerOrganizationCodeId()));
            	HSSFCell cell18=row.createCell(18);
            	cell18.setCellValue(setNotNull(damageAssets.getResult().get(i).getAssetOwner().getDepartmentCodeId()));
            	
            	HSSFCell cell19=row.createCell(19);
            	cell19.setCellValue(setNotNull(damageAssets.getResult().get(i).getAssetOwner().getLineCodeId()));
            	HSSFCell cell20=row.createCell(20);
            	cell20.setCellValue(setNotNull(damageAssets.getResult().get(i).getAssetOwner().getStationCodeId()));
            	HSSFCell cell21=row.createCell(21);
            	cell21.setCellValue(setNotNull(damageAssets.getResult().get(i).getAsset().getOwnershipPer()));
            	HSSFCell cell22=row.createCell(22);
            	cell22.setCellValue(setNotNull(damageAssets.getResult().get(i).getAsset().getUsePer()));
            	
            	HSSFCell cell23=row.createCell(23);
            	if(damageAssets.getResult().get(i).getAssetOwner().getBeginUseDate()==null)
            		cell23.setCellValue("");
            	else
            		cell23.setCellValue(setNotNull(damageAssets.getResult().get(i).getAssetOwner().getBeginUseDate()+""));
            	HSSFCell cell24=row.createCell(24);
            	if(damageAssets.getResult().get(i).getAssetOwner().getStopUseDate()==null)
            		cell24.setCellValue("");
            	else
            		cell24.setCellValue(setNotNull(damageAssets.getResult().get(i).getAssetOwner().getStopUseDate()+""));
            	HSSFCell cell25=row.createCell(25);
            	if(damageAssets.getResult().get(i).getAsset().getApprovalScrapDate()==null)
            		cell25.setCellValue("");
            	else
            		cell25.setCellValue(setNotNull(damageAssets.getResult().get(i).getAsset().getApprovalScrapDate()+""));
            	HSSFCell cell26=row.createCell(26);
            	cell26.setCellValue(setNotNull(damageAssets.getResult().get(i).getAssetOwner().getReceiveDate()+""));
            	HSSFCell cell27=row.createCell(27);// 使用/停用/封存/报废/待报废
            	if(damageAssets.getResult().get(i).getAssetState().getState().equals("1")){
            		cell27.setCellValue("使用");
            	}else if(damageAssets.getResult().get(i).getAssetState().getState().equals("2")){
            		cell27.setCellValue("停用");
            	}else if(damageAssets.getResult().get(i).getAssetState().getState().equals("3")){
            		cell27.setCellValue("封存");
            	}else if(damageAssets.getResult().get(i).getAssetState().getState().equals("4")){
            		cell27.setCellValue("报废");
            	}else{
            		cell27.setCellValue("待报废");
            	}
            	HSSFCell cell28=row.createCell(28);
            	cell28.setCellValue(setNotNull(damageAssets.getResult().get(i).getAsset().getAssetPic()));
            	
            	HSSFCell cell29=row.createCell(29);
            	cell29.setCellValue(setNotNull(damageAssets.getResult().get(i).getAsset().getUseLife()));
            	HSSFCell cell30=row.createCell(30);
            	if(damageAssets.getResult().get(i).getAsset().getExpectancyLife()==null)
            		cell30.setCellValue("");
            	else
            		cell30.setCellValue(setNotNull(damageAssets.getResult().get(i).getAsset().getExpectancyLife()+""));
            	HSSFCell cell31=row.createCell(31);
            	if(damageAssets.getResult().get(i).getAsset().getWarrantyPeriod()==null)
            		cell31.setCellValue("");
            	else
            		cell31.setCellValue(setNotNull(damageAssets.getResult().get(i).getAsset().getWarrantyPeriod()+""));
            	HSSFCell cell32=row.createCell(32);
            	cell32.setCellValue(setNotNull(damageAssets.getResult().get(i).getAsset().getOverhaulRate()));
            	
            	
                HSSFCell cell33=row.createCell(33);
                if(damageAssets.getResult().get(i).getAsset().getFactoryPrice()==null)
                	cell33.setCellValue("");
                else
                	cell33.setCellValue(setNotNull(damageAssets.getResult().get(i).getAsset().getFactoryPrice()+""));
                HSSFCell cell34=row.createCell(34);
                if(damageAssets.getResult().get(i).getAsset().getContractPrice()==null)
                	cell34.setCellValue("");
                else
                	cell34.setCellValue(setNotNull(damageAssets.getResult().get(i).getAsset().getContractPrice()+"")); 
            	HSSFCell cell35=row.createCell(35);
            	if(damageAssets.getResult().get(i).getAsset().getOriginalValue()==null)
            		cell35.setCellValue("");
            	else
            		cell35.setCellValue(setNotNull(damageAssets.getResult().get(i).getAsset().getOriginalValue()+"")); 
            	HSSFCell cell36=row.createCell(36);
            	cell36.setCellValue(setNotNull(damageAssets.getResult().get(i).getAsset().getDepreciationMethod()));
            	//累计折旧
            	HSSFCell cell37=row.createCell(37);
            	cell37.setCellValue(setNotNull(damageAssets.getResult().get(i).getAsset().getProjectAppDocNo()));
            	
                HSSFCell cell38=row.createCell(38);
                if(damageAssets.getResult().get(i).getAssetPrice().getNetValue()==null)
                	cell38.setCellValue("");
                else
                	cell38.setCellValue(setNotNull(damageAssets.getResult().get(i).getAssetPrice().getNetValue()+""));
                HSSFCell cell39=row.createCell(39);
            	cell39.setCellValue(setNotNull(damageAssets.getResult().get(i).getAssetState().getNameplateSite())); 
            	HSSFCell cell40=row.createCell(40);
            	cell40.setCellValue(setNotNull(damageAssets.getResult().get(i).getAsset().getEquipmentList())); 
            	HSSFCell cell41=row.createCell(41);
            	cell41.setCellValue(setNotNull(damageAssets.getResult().get(i).getAsset().getCompleteTransAssetType()));
            	HSSFCell cell42=row.createCell(42);
            	cell42.setCellValue(setNotNull(damageAssets.getResult().get(i).getAsset().getProjectAppDocNo()));
            	HSSFCell cell43=row.createCell(43);
            	cell43.setCellValue(setNotNull(damageAssets.getResult().get(i).getAsset().getRemarks()));
            	//"资产年限","已使用年限","类别",
            	HSSFCell cell44=row.createCell(44);
            	cell44.setCellValue(setNotNull(damageAssets.getResult().get(i).getAsset().getUseLife()));
            	HSSFCell cell45=row.createCell(45);
            	cell45.setCellValue(setNotNull(damageAssets.getResult().get(i).getUseYear()));
            	HSSFCell cell46=row.createCell(46);
            	cell46.setCellValue(setNotNull(damageAssets.getResult().get(i).getScrapReason()));
            	HSSFCell cell47=row.createCell(47);
            	cell47.setCellValue(setNotNull(damageAssets.getResult().get(i).getDamageType()));
            	HSSFCell cell48=row.createCell(48);
            	cell48.setCellValue(setNotNull(damageAssets.getResult().get(i).getNote()));
            	
            	HSSFCell cell49=row.createCell(49);
            	cell49.setCellValue(setNotNull(damageAssets.getResult().get(i).getAsset().getUnit().getName()));
            }
            OutputStream out = response.getOutputStream();
            workbook.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            System.out.println("Exception：" + e);
        }
    }
    
    String setNotNull(String obj){
    	return obj==null ? "":obj;
    	
    }
}

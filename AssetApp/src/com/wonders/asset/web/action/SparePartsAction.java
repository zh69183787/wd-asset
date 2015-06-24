package com.wonders.asset.web.action;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JsonConfig;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.base.web.AbstractBaseAction;
import com.wonders.asset.model.Line;
import com.wonders.asset.model.SpareParts;
import com.wonders.asset.model.SpareType;
import com.wonders.asset.model.Station;
import com.wonders.asset.service.LineService;
import com.wonders.asset.service.SparePartsService;
import com.wonders.asset.service.SpareTypeService;
import com.wonders.asset.service.StationService;
import com.wonders.framework.util.ServiceProvider;

public class SparePartsAction extends AbstractBaseAction{
	private SparePartsService sparePartsService;
	private SpareTypeService spareTypeService;
	private StationService stationService;
	
	private Date startDate;
	private Date endDate;
	
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public StationService getStationService() {
		return stationService;
	}

	public void setStationService(StationService stationService) {
		this.stationService = stationService;
	}

	public SparePartsService getSparePartsService() {
		return sparePartsService;
	}

	public void setSparePartsService(SparePartsService sparePartsService) {
		this.sparePartsService = sparePartsService;
	}
	
	

	public SpareTypeService getSpareTypeService() {
		return spareTypeService;
	}

	public void setSpareTypeService(SpareTypeService spareTypeService) {
		this.spareTypeService = spareTypeService;
	}

	/**
	 * 查询备品备件
	 * @throws Exception
	 */
	public void inquery() throws Exception{
		Map<String,String> filter = createFilterMap();
        int startIndex = NumberUtils.toInt(getRequestParameter("jtStartIndex"), 0);
        int pageSize = NumberUtils.toInt(getRequestParameter("jtPageSize"), 10);
        Map<String,String> sort = createSortMap();
        List<Line> lineList = ServiceProvider.getService(LineService.class).findAllByPublish("1");
        Pagination<SpareParts> spareParts = sparePartsService.findBy(filter,sort,startIndex,pageSize);
        int i = startIndex + 1;
        for(SpareParts sparePart:spareParts.getResult()){
        	sparePart.setNo(i);
        	for(Line line:lineList){
        		if(line.getCodeId().equals(sparePart.getLocLine())){
        			sparePart.setLocLine(line.getName());
        			break;
        		}
        	}
        	i++;
        }
        JsonConfig jsonConfig = basicJsonCfg.copy();
        jsonConfig.registerPropertyExclusions(SpareParts.class, new String[]{"asset"});
        renderJson(spareParts.getResult(), spareParts.getTotalCount(), jsonConfig);
	}
	/**
	 * 备品备件详情
	 * @return
	 * @throws Exception
	 */
	public String showSparePartsView() throws Exception{
		String id=request.getParameter("id");
		SpareParts spareParts = sparePartsService.findByPrimaryKey(id);
		List<Line> lineList = ServiceProvider.getService(LineService.class).findAllByPublish("1");
		for(Line line : lineList){
			if(line.getCodeId().equals(spareParts.getLocLine())){
				spareParts.setLocLine(line.getName());
				break;
			}
		}	
//		SpareType spareType1 = ServiceProvider.getService(SpareTypeService.class).findById(spareParts.getAt1());
//		spareParts.setAt1(spareType1.getName());
//		SpareType spareType2 = ServiceProvider.getService(SpareTypeService.class).findById(spareParts.getAt2());
//		spareParts.setAt2(spareType2.getName());
//		SpareType spareType3 = ServiceProvider.getService(SpareTypeService.class).findById(spareParts.getAt3());
//		spareParts.setAt3(spareType3.getName());
		Station station = ServiceProvider.getService(StationService.class).findById(spareParts.getLocationCode());
		spareParts.setLocationCode(station.getName());		
        request.setAttribute("spareParts",spareParts);
		return "success";
	}
	
	/**
	 * 导出功能
	 */
	public void excelSpareParts(){
		String name="备品备件台账.xls";
        response.setContentType("application/x-download");
        response.setCharacterEncoding("utf-8");
        try {
         	response.setHeader("Content-Disposition", "attachment;filename="+new String(name.getBytes("GBK"), "ISO8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Map<String,String> filter = createFilterMap();
        Map<String,String> sort = createSortMap();
        List<SpareParts> list=sparePartsService.findByWithoutCount(filter,sort);
        Integer rowNumber = list.size();
//        String[] title=new String[]{"备品备件编码","备品备件名称","线路","车站","项目","移交时间"};
        String[] title = new String[]{"项目名称","项目编号","项目合同编码","备品备件编码","备品备件名称",
        "系统大类","系统中类","系统小类","单位","数量","规格型号","产地","生产厂商","供应商","出厂日期","供应日期",
        "权属单位","使用单位","维护部门","所属线路","所属车站","移交时间","设计使用年限（年）","保修期至","出厂价（元）","合同价（元）",
        "原值（决算价）（元）","技术、规格、操作资料及清单","设备清单",  
        "竣工移交资产类型标示(初始/新增/更新）","已运营项目（线路）资产大修/更新改造项目资产标示",
        "已运营项目（线路）资产大修/改造交付使用日期","已运营项目（线路）资产大修/改造决算价（元）",  
        "立项或可研批复文号","备注"};                              
        
        
        
        try {        	
            // 创建新的Excel 工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet();

            // 创建第一行并设置标题
            HSSFRow titleRow = sheet.createRow(0);
            for(int i=0; i<title.length; i++){
                titleRow.createCell(i).setCellValue(title[i]);
            }
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            
            // 创建单元格并为单元格赋值
            for(int i=0; i<rowNumber; i++){
                HSSFRow row = sheet.createRow(i+1);     
                HSSFCell cell1=row.createCell(0);
            	cell1.setCellValue(setNotNull(list.get(i).getProjectName()));        	
                HSSFCell cell2=row.createCell(1);
                cell2.setCellValue(setNotNull(list.get(i).getProjectNo()));
                
                HSSFCell cell3=row.createCell(2);
                cell3.setCellValue(setNotNull(list.get(i).getProjectContractNo()));
                
                HSSFCell cell4=row.createCell(3);               
                cell4.setCellValue(setNotNull(list.get(i).getSpareAssetNo()));	
                HSSFCell cell5=row.createCell(4);
                cell5.setCellValue(setNotNull(list.get(i).getSpareAssetDescription()));       	
                HSSFCell cell6=row.createCell(5);
                cell6.setCellValue(setNotNull(list.get(i).getAt1()));
                HSSFCell cell7=row.createCell(6);
                cell7.setCellValue(setNotNull(list.get(i).getAt2()));
                HSSFCell cell8=row.createCell(7);
                cell8.setCellValue(setNotNull(list.get(i).getAt3()));
                HSSFCell cell9=row.createCell(8);
                cell9.setCellValue(setNotNull(list.get(i).getUnitCode()));
                HSSFCell cell10=row.createCell(9);
                cell10.setCellValue(setNotNull(list.get(i).getAssetQty()+""));
                HSSFCell cell11=row.createCell(10);
                cell11.setCellValue(setNotNull(list.get(i).getFinGzxh()));
                HSSFCell cell12=row.createCell(11);
                cell12.setCellValue(setNotNull(list.get(i).getProductArea()));
                HSSFCell cell13=row.createCell(12);
                cell13.setCellValue(setNotNull(list.get(i).getManufacturer()));
                HSSFCell cell14=row.createCell(13);
                cell14.setCellValue(setNotNull(list.get(i).getSupplier()));
                HSSFCell cell15=row.createCell(14);
                cell15.setCellValue(setNotNull(list.get(i).getLeaveFactory()+""));
                HSSFCell cell16=row.createCell(15);
                cell16.setCellValue(setNotNull(list.get(i).getSupplyDate()+""));
                HSSFCell cell17=row.createCell(16);
                cell17.setCellValue(setNotNull(list.get(i).getOwnershipUnitName()));
                HSSFCell cell18=row.createCell(17);
                cell18.setCellValue(setNotNull(list.get(i).getUseUnitName()));
                HSSFCell cell19=row.createCell(18);
                cell19.setCellValue(setNotNull(list.get(i).getMaintainDepartName()));
                HSSFCell cell20=row.createCell(19);
                cell20.setCellValue(setNotNull(list.get(i).getLocLine()));
                HSSFCell cell21=row.createCell(20);
                cell21.setCellValue(setNotNull(list.get(i).getLocationCode()));
                HSSFCell cell22=row.createCell(21);
                cell22.setCellValue(setNotNull(list.get(i).getTransferDate()+""));//sdf.format
                HSSFCell cell23=row.createCell(22);
                cell23.setCellValue(setNotNull(list.get(i).getDesignUseLife()));
                HSSFCell cell24=row.createCell(23);
                cell24.setCellValue(setNotNull(list.get(i).getWarrantyPeriod())); 
                HSSFCell cell25=row.createCell(24);
                cell25.setCellValue(setNotNull(list.get(i).getFactoryPrice()+""));
                HSSFCell cell26=row.createCell(25);
                cell26.setCellValue(setNotNull(list.get(i).getContractPrice()+""));
                HSSFCell cell27=row.createCell(26);
                cell27.setCellValue(setNotNull(list.get(i).getOriginalValue()+""));
                HSSFCell cell28=row.createCell(27);
                cell28.setCellValue(setNotNull(list.get(i).getHaveList())); 
                HSSFCell cell29=row.createCell(28);
                cell29.setCellValue(setNotNull(list.get(i).getEquipList()));
                HSSFCell cell30=row.createCell(29);
                cell30.setCellValue(setNotNull(list.get(i).getCompleteTransAssetType()));
                HSSFCell cell31=row.createCell(30);
                cell31.setCellValue(setNotNull(list.get(i).getOperateProjectAsset()));
                HSSFCell cell32=row.createCell(31);
                cell32.setCellValue(setNotNull(list.get(i).getOperateProjectAssetDate()+""));
                HSSFCell cell33=row.createCell(32);
                cell33.setCellValue(setNotNull(list.get(i).getOperateProjectAssetAccount()+""));
                HSSFCell cell34=row.createCell(33);
                cell34.setCellValue(setNotNull(list.get(i).getProjectAppDocNo()));
                HSSFCell cell35=row.createCell(34);
                cell35.setCellValue(setNotNull(list.get(i).getRemarks()));
            }
            OutputStream out = response.getOutputStream();
            workbook.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            System.out.println("Exception：" + e);
        }
	}
	
	/**
	 * 判断（为空时返回空字符串）
	 * @param obj
	 * @return
	 */
	String setNotNull(String obj){
      	return obj == null ? "":obj;
    }
	/**
	 * 保留2位小数
	 * @param money
	 * @return
	 */
	public String getFormattedMoney(String money){
  		if(money==null || "".equals(money)){
  			money = "0";
  		}
  		Double result = 0d;
  		try {
  			result = Double.valueOf(money);
  		} catch (NumberFormatException e) {
  			result = 0d;
  		}
  		DecimalFormat df = new DecimalFormat("#0.00");
  		if("0.00".equals(df.format(result))){
  			return "0";
  		}
  		return df.format(result);
  	}
	
	public void findSparePartsType() throws IOException{
		SpareType spareType = new SpareType();
		spareType.setPublish("1"); 
		spareType.setParent(new SpareType());
		spareType.getParent().setId(request.getParameter("parentId"));
		List<SpareType> spareTypes = spareTypeService.find(spareType);
		JsonConfig jsonConfig = basicJsonCfg.copy();
        jsonConfig.registerPropertyExclusions(SpareType.class, new String[]{"parent","children"});
		renderJson(spareTypes,spareTypes.size(),jsonConfig);	 
	}
	
	/**
	 * 同步备品备件数据
	 */
	public void sysncSpareData(){ // ?startDate=2014-09-12&endDate=2014-10-20
		sparePartsService.syncInsertData(startDate, endDate);
		sparePartsService.syncUpdateData(startDate, endDate);
	}
}

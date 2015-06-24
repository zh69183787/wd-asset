package com.wonders.asset.web.action;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import net.sf.json.JsonConfig;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.dom4j.DocumentException;

import com.opensymphony.xwork2.Action;
import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.base.web.AbstractBaseAction;
import com.wonders.asset.model.Asset;
import com.wonders.asset.model.AssetOwner;
import com.wonders.asset.model.AssetRecord;
import com.wonders.asset.model.AssetType;
import com.wonders.asset.model.Attachment;
import com.wonders.asset.model.Contract;
import com.wonders.asset.model.Department;
import com.wonders.asset.model.Line;
import com.wonders.asset.model.Organization;
import com.wonders.asset.model.Station;
import com.wonders.asset.service.AssetPriceService;
import com.wonders.asset.service.AssetRecordService;
import com.wonders.asset.service.AssetService;
import com.wonders.asset.service.AssetTypeService;
import com.wonders.asset.service.AttachmentService;
import com.wonders.asset.service.DepartmentService;
import com.wonders.asset.service.LineService;
import com.wonders.asset.service.OrganizationService;
import com.wonders.asset.service.QuartzJobService;
import com.wonders.asset.service.StationService;
import com.wonders.framework.util.ServiceProvider;


public class AssetAction extends AbstractBaseAction{

	private AssetService assetService;
	private AssetPriceService assetPriceService;
	private AssetTypeService assetTypeService;
	private String uploadifyFileName;
	private File uploadify;
	
	private QuartzJobService quartzJobService;
	
	private AssetRecordService assetRecordService;
	
	

	public AssetRecordService getAssetRecordService() {
		return assetRecordService;
	}

	public void setAssetRecordService(AssetRecordService assetRecordService) {
		this.assetRecordService = assetRecordService;
	}

	public File getUploadify() {
		return uploadify;
	}

	public void setUploadify(File uploadify) {
		this.uploadify = uploadify;
	}

	public String getUploadifyFileName() {
		return uploadifyFileName;
	}

	public void setUploadifyFileName(String uploadifyFileName) {
		this.uploadifyFileName = uploadifyFileName;
	}

	public AssetPriceService getAssetPriceService() {
		return assetPriceService;
	}

	public void setAssetPriceService(AssetPriceService assetPriceService) {
		this.assetPriceService = assetPriceService;
	}

	public AssetService getAssetService() {
		return assetService;
	}

	public void setAssetService(AssetService assetService) {
		this.assetService = assetService;
	}

	public AssetTypeService getAssetTypeService() {
		return assetTypeService;
	}

	public void setAssetTypeService(AssetTypeService assetTypeService) {
		this.assetTypeService = assetTypeService;
	}

	public QuartzJobService getQuartzJobService() {
		return quartzJobService;
	}

	public void setQuartzJobService(QuartzJobService quartzJobService) {
		this.quartzJobService = quartzJobService;
	}

	/**
	 * 查询方法
	 * @return
	 * @throws Exception
	 */
	public void inquery() throws Exception {
		try {
			Map<String,String> filter = createFilterMap();
			
			int startIndex = NumberUtils.toInt(getRequestParameter("jtStartIndex"), 0);
			int pageSize = NumberUtils.toInt(getRequestParameter("jtPageSize"), 10);
			Map<String,String> sort = createSortMap();
			Pagination<Asset> assets;
			assets = ServiceProvider.getService(AssetService.class).findBy(filter, sort, startIndex, pageSize);
            if(assets!=null && assets.getResult()!=null && assets.getResult().size()>0){
				List<Asset> assetList = assets.getResult();
				for(int i=0;i<assetList.size();i++){
					Asset tempAsset = assetList.get(i);
					AssetOwner tempAssetOwner = tempAsset.getAssetOwnerInf();
					Line line = ServiceProvider.getService(LineService.class).findById(tempAssetOwner.getLineCodeId());
					Station station = ServiceProvider.getService(StationService.class).findById(tempAssetOwner.getStationCodeId());
					tempAssetOwner.setLine(line);
					tempAssetOwner.setStation(station);
				}
			}
			
			
			JsonConfig jsonConfig = basicJsonCfg.copy();
			jsonConfig.registerPropertyExclusions(Asset.class, new String[]{"equipments","assetRecords"});
			jsonConfig.registerPropertyExclusions(AssetType.class, new String[]{"children"});
            jsonConfig.registerPropertyExclusions(Contract.class, new String[]{"assets"});
            jsonConfig.registerPropertyExclusions(Line.class, new String[]{"stations"});
			renderJson(assets.getResult(), assets.getTotalCount(), jsonConfig);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 导入数据
	 * @throws DocumentException
	 * @throws ParseException
	 */
	public void importAssetData() throws DocumentException, ParseException{
//		ServiceProvider.getService(AssetService.class).importData();
//        ServiceProvider.getService(AssetService.class).findBeforeLastExecuteDate(null);
        ServiceProvider.getService(AssetService.class).importData();
	}
	
	/**
	 * 显示资产详细页面
	 * @return
	 * @throws DocumentException
	 */
	public String showView() throws DocumentException{
		String id = getRequestParameter("id");
		Asset asset = ServiceProvider.getService(AssetService.class).findByPrimaryKey(id);
		AssetType main = ServiceProvider.getService(AssetTypeService.class).findMainTypeByCode(asset.getMainTypeCodeId());
		AssetType sub = ServiceProvider.getService(AssetTypeService.class).findMainTypeByCode(asset.getSubTypeCodeId());
		AssetType detail = ServiceProvider.getService(AssetTypeService.class).findMainTypeByCode(asset.getDetailTypeCodeId());
		request.setAttribute("main", main);
		request.setAttribute("sub", sub);
		request.setAttribute("detail", detail);
		
		
		AssetOwner assetOwner = asset.getAssetOwnerInf();
		if(assetOwner!=null ){
			Department department = ServiceProvider.getService(DepartmentService.class).findById(assetOwner.getDepartmentCodeId());
			Organization useOrganization = ServiceProvider.getService(OrganizationService.class).findById(assetOwner.getUseOrganizationCodeId());
			Organization ownerOrganization = ServiceProvider.getService(OrganizationService.class).findById(assetOwner.getOwnerOrganizationCodeId());
			Line line = ServiceProvider.getService(LineService.class).findById(assetOwner.getLineCodeId());
			Station station = ServiceProvider.getService(StationService.class).findById(assetOwner.getStationCodeId());
			request.setAttribute("department",department);
			request.setAttribute("useOrganization",useOrganization);
			request.setAttribute("ownerOrganization",ownerOrganization);
			request.setAttribute("line",line);
			request.setAttribute("station",station);
		}
		
		
		request.setAttribute("asset", asset);
		return "success";
	}
	
	/**
	 * 显示资产履历
	 * @throws DocumentException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public void showProjectRecord() throws DocumentException, IOException{
		Map<String,String> filter = createFilterMap();
		int startIndex = NumberUtils.toInt(getRequestParameter("jtStartIndex"), 0);
		int pageSize = NumberUtils.toInt(getRequestParameter("jtPageSize"), 10);
		Map<String,String> sort = createSortMap();
		Asset asset = ServiceProvider.getService(AssetService.class).findByPrimaryKey(filter.get("asset.id_equal"));
		Pagination<AssetRecord> result = ServiceProvider.getService(AssetService.class).findAssetRecordByAssetId(filter.get("asset.id_equal") , startIndex, pageSize); 
		JsonConfig jsonConfig = basicJsonCfg.copy();
		jsonConfig.registerPropertyExclusions(AssetRecord.class, new String[]{"asset"});
		renderJson(result.getResult(), result.getTotalCount(), jsonConfig);
		
	}
	@SuppressWarnings("unchecked")
	public void queryAssetRecord()throws Exception{
		String recordNo = request.getParameter("recordNo");
		Map<String,String> filter = createFilterMap();
		filter.put("recordNo_equal", recordNo);
		int startIndex = NumberUtils.toInt(getRequestParameter("jtStartIndex"), 0);
        int pageSize = NumberUtils.toInt(getRequestParameter("jtPageSize"), 10);
        Map<String,String> sort = createSortMap();
        Pagination<AssetRecord> assetRecords = assetRecordService.findRecordAndAsset(filter, sort, startIndex, pageSize);
        int no = startIndex+1;
        for(AssetRecord assetRecord:assetRecords.getResult()){
        	assetRecord.setNo(no);
        	no++;
        }
        renderJson(assetRecords.getResult(),assetRecords.getTotalCount());
        
	}
	
	
	public void excelRecordAsset(){
		String name="资产大修更新台账.xls";
        response.setContentType("application/x-download");
        response.setCharacterEncoding("utf-8");
        try {
         	response.setHeader("Content-Disposition", "attachment;filename="+new String(name.getBytes("GBK"), "ISO8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        
    	String recordNo = request.getParameter("recordNo");
    	Map<String,String> filter = createFilterMap();
    	filter.put("recordNo_equal", recordNo);
        int startIndex = NumberUtils.toInt(getRequestParameter("jtStartIndex"), 0);
        int pageSize = NumberUtils.toInt(getRequestParameter("jtPageSize"), 10);
        Map<String,String> sort = createSortMap();
        Pagination<AssetRecord> assetRecords = assetRecordService.findRecordAndAsset(filter, sort, startIndex, pageSize);
        
        Integer rowNumber = assetRecords.getResult().size();
		String[] title = new String[]{
				"项目名称（轨道交通建设项目按名称字典；大修更新改造项目按简称）(必填)",//0
				"项目编号（轨道交通建设项目按建设工程编号<可填写工程标段号>,大修更新改造项目按集团公司项目编号规则）(必填)",//1
				"项目合同编码（轨道交通建设项目按建设工程合同编号，大修更新改造按集团公司合同编号规则）(必填)",//2
				"资产编码(必填)(20)",//3
				"资产名称(必填)(200)",//4
				"系统大类(必填)",//5
				"系统中类(必填)",//6
				"系统小类(必填)",//7
				"单位(必填)",//8
				"数量(必填)",//9
				"规格型号(必填)(200)",//10
				"产地(50)",//11
				"生产厂商(全称)(100)",//12
				"供应商(全称)(必填)(100)",//13
				"出厂日期(yyyy/mm/dd)",//14
				"供应日期(yyyy/mm/dd)",//15
				"安装地点(必填)(200)",//16
				"权属单位(资产权属单位代码)(必填)",//17
				"使用单位(使用权属单位代码)(必填)",//18
				"维护部门(资产维护部门代码)(必填)",//19
				"所属线路(线路<网络>号代码)(必填)",//20
				"所属车站(所属线路车站、停车场或车辆段代码)(必填)",//21
				"权属责任人",//22
				"使用责任人",//23
				"开始使用时间(yyyy/mm/dd)(必填)",//25
				"停止使用时间(yyyy/mm/dd)",//A25
				"批准报废时间(yyyy/mm/dd)",//26
				"移交时间(即项目建成移交运营单位或维保中心)(yyyy/mm/dd)(必填)",//27
				"当前使用状态(使用/停用/封存/报废/待报废)(必填)",//28
				"资产图片名称(如有)",//29
				"设计使用限()(必填)",//30
				"预期使用寿命()",//31
				"保修期至(yyyy/mm/dd)",//H//32
				"大修频次(/次)(必填)",//33
				"出厂价",//34
				"合同价",//35
				"原值",//36
				"技术、规格、操作资料及清单（有无）",//37
				"折旧方法",//38
				"累计折旧",//39
				"资产净值(元)",//40
				"铭牌张贴位置(左上/左下/中/右上/右下/铭牌板)",//41
				"设备清单(2000)",//Q//42
				"项目(线路)竣工移交资产类型标示(初始/新增/更新)(必填)",//43
				"已运营项目（线路）资产大修/更新改造项目资产标示(大修/改造）",//44
				"已运营项目（线路）资产大修/改造交付使用日期(yyyy/mm/dd)",//45
				"已运营项目（线路）资产大修/改造决算价（元）",//46
				"立项或可研批复文号(大修更新改造项目、报废资产项目、新购置资产项目等必填)/沪地铁（20**）**号（必填）",//47
				"备注（2000）"//48
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
                cell0.setCellValue(setNotNull(assetRecords.getResult().get(i).getAsset().getProject().getProjectName()));
                HSSFCell cell1=row.createCell(1);
            	cell1.setCellValue(setNotNull(assetRecords.getResult().get(i).getAsset().getProjectNo()));
            	HSSFCell cell2=row.createCell(2);
            	cell2.setCellValue(setNotNull(assetRecords.getResult().get(i).getAsset().getContractNo()));
            	HSSFCell cell3=row.createCell(3);
            	cell3.setCellValue(setNotNull(assetRecords.getResult().get(i).getAsset().getAssetCode()));
            	HSSFCell cell4=row.createCell(4);
            	cell4.setCellValue(setNotNull(assetRecords.getResult().get(i).getAsset().getName()));
            	HSSFCell cell5=row.createCell(5);
            	cell5.setCellValue(setNotNull(assetRecords.getResult().get(i).getAsset().getMainTypeCodeId()));
            	HSSFCell cell6=row.createCell(6);
            	cell6.setCellValue(setNotNull(assetRecords.getResult().get(i).getAsset().getSubTypeCodeId()));
            	HSSFCell cell7=row.createCell(7);
            	cell7.setCellValue(setNotNull(assetRecords.getResult().get(i).getAsset().getDetailTypeCodeId()));
            	HSSFCell cell8=row.createCell(8);
            	cell8.setCellValue(setNotNull(assetRecords.getResult().get(i).getAsset().getUnit().getName()));
            	
            	HSSFCell cell9=row.createCell(9);
            	cell9.setCellValue(setNotNull(assetRecords.getResult().get(i).getAsset().getCount()));
            	HSSFCell cell10=row.createCell(10);
            	cell10.setCellValue(setNotNull(assetRecords.getResult().get(i).getAsset().getType()));
            	HSSFCell cell11=row.createCell(11);
            	cell11.setCellValue(setNotNull(assetRecords.getResult().get(i).getAsset().getManufactureCountry()));
            	HSSFCell cell12=row.createCell(12);
            	cell12.setCellValue(setNotNull(assetRecords.getResult().get(i).getAsset().getManufacturer().getName()));
            	HSSFCell cell13=row.createCell(13);
            	cell13.setCellValue(setNotNull(assetRecords.getResult().get(i).getAsset().getSupplier().getName()));
            	HSSFCell cell14=row.createCell(14);
            	cell14.setCellValue(setNotNull(assetRecords.getResult().get(i).getAsset().getMadeDate()));
            	HSSFCell cell15=row.createCell(15);
            	cell15.setCellValue(setNotNull(assetRecords.getResult().get(i).getAsset().getUseDate()));
            	HSSFCell cell16=row.createCell(16);
            	cell16.setCellValue(setNotNull(assetRecords.getResult().get(i).getAsset().getDetailInstallSite()));
            	HSSFCell cell17=row.createCell(17);
            	cell17.setCellValue(setNotNull(assetRecords.getResult().get(i).getAsset().getAssetOwnerInf().getOwnerOrganization().getName()));
            	HSSFCell cell18=row.createCell(18);
            	cell18.setCellValue(setNotNull(assetRecords.getResult().get(i).getAsset().getAssetOwnerInf().getUseOrganization().getName()));
            	
            	HSSFCell cell19=row.createCell(19);
            	cell19.setCellValue(setNotNull(assetRecords.getResult().get(i).getAsset().getAssetOwnerInf().getDepartmentCodeId()));
            	HSSFCell cell20=row.createCell(20);
            	cell20.setCellValue(setNotNull(assetRecords.getResult().get(i).getAsset().getAssetOwnerInf().getLine().getShortName()));
            	HSSFCell cell21=row.createCell(21);
            	cell21.setCellValue(setNotNull(assetRecords.getResult().get(i).getAsset().getAssetOwnerInf().getStationCodeId()));
            	HSSFCell cell22=row.createCell(22);
            	cell22.setCellValue(setNotNull(assetRecords.getResult().get(i).getAsset().getOwnershipPer()));
            	HSSFCell cell23=row.createCell(23);
            	cell23.setCellValue(setNotNull(assetRecords.getResult().get(i).getAsset().getUsePer()));
            	HSSFCell cell24=row.createCell(24);
            	cell24.setCellValue(setNotNull(assetRecords.getResult().get(i).getAsset().getAssetOwnerInf().getBeginUseDate()));
            	HSSFCell cell25=row.createCell(25);
            	cell25.setCellValue(setNotNull(assetRecords.getResult().get(i).getAsset().getAssetOwnerInf().getStopUseDate()));
            	HSSFCell cell26=row.createCell(26);
            	cell26.setCellValue(setNotNull(assetRecords.getResult().get(i).getAsset().getApprovalScrapDate()));
            	HSSFCell cell27=row.createCell(27);
            	cell27.setCellValue(setNotNull(assetRecords.getResult().get(i).getAsset().getAssetOwnerInf().getReceiveDate()));
            	HSSFCell cell28=row.createCell(28);
            	cell28.setCellValue(setNotNull(assetRecords.getResult().get(i).getAsset().getState().getState()));
            	HSSFCell cell29=row.createCell(29);
            	cell29.setCellValue(setNotNull(assetRecords.getResult().get(i).getAsset().getAssetPic()));
            	HSSFCell cell30=row.createCell(30);
            	cell30.setCellValue(setNotNull(assetRecords.getResult().get(i).getAsset().getUseLife()));
            	HSSFCell cell31=row.createCell(31);
            	cell31.setCellValue(assetRecords.getResult().get(i).getAsset().getExpectancyLife());
            	HSSFCell cell32=row.createCell(32);
            	cell32.setCellValue(setNotNull(assetRecords.getResult().get(i).getAsset().getWarrantyPeriod()));
            	HSSFCell cell33=row.createCell(33);
            	cell33.setCellValue(setNotNull(assetRecords.getResult().get(i).getAsset().getOverhaulRate()));
            	HSSFCell cell34=row.createCell(34);
            	cell34.setCellValue(setNotNull(assetRecords.getResult().get(i).getAsset().getFactoryPrice()));
            	HSSFCell cell35=row.createCell(35);
            	cell35.setCellValue(setNotNull(assetRecords.getResult().get(i).getAsset().getContractPrice()));
            	HSSFCell cell36=row.createCell(36);
            	cell36.setCellValue(setNotNull(assetRecords.getResult().get(i).getAsset().getOriginalValue()));
            	
            	HSSFCell cell38=row.createCell(38);
            	cell38.setCellValue(setNotNull(assetRecords.getResult().get(i).getAsset().getAssetPrice().getDepreciationMethod()));
            	HSSFCell cell40=row.createCell(40);
            	cell40.setCellValue(setNotNull(assetRecords.getResult().get(i).getAsset().getAssetPrice().getNetValue()));
            	HSSFCell cell41=row.createCell(41);
            	cell41.setCellValue(setNotNull(assetRecords.getResult().get(i).getAsset().getState().getNameplateSite()));
            	HSSFCell cell42=row.createCell(42);
            	cell42.setCellValue(setNotNull(assetRecords.getResult().get(i).getAsset().getEquipmentList()));
            	
            	
            	
            	
            	
            	
            	HSSFCell cell43=row.createCell(43);
            	cell43.setCellValue(setNotNull(assetRecords.getResult().get(i).getAsset().getCompleteTransAssetType()));
            	HSSFCell cell44=row.createCell(44);
            	cell44.setCellValue(setNotNull(assetRecords.getResult().get(i).getAssetType()));
            	HSSFCell cell45=row.createCell(45);
            	cell45.setCellValue(setNotNull(assetRecords.getResult().get(i).getFinishDate()));
            	HSSFCell cell46=row.createCell(46);
            	cell46.setCellValue(setNotNull(assetRecords.getResult().get(i).getFinishPrice()));
            	HSSFCell cell47=row.createCell(47);
            	cell47.setCellValue(setNotNull(assetRecords.getResult().get(i).getProjectAppNo()));
            }
            OutputStream out = response.getOutputStream();
            workbook.write(out);
            out.flush();
            out.close();
		}catch (Exception e) {
            System.out.println("Exception：" + e);
        }
	}
	String setNotNull(Object obj){
    	return obj==null ? "":obj.toString();
    	
    }
	//得到保留6位小数后的字符串
	public String getFormattedMoney(String money){
		if(money==null || "".equals(money)){
			money = "0";
		}
		Double result = 0d;
		try {
			result = Double.valueOf(money)/10000;
		} catch (NumberFormatException e) {
			result = 0d;
		}
		DecimalFormat df = new DecimalFormat("#0.000000");
		if("0.000000".equals(df.format(result))){
			return "0";
		}
		return df.format(result);
	}
	
	/**
	 * 批量上传资产
	 * @return
	 * @throws DocumentException 
	 */
	public void assetBatchUpload() throws DocumentException{
		String saveDir = "C:/newAssetFile";
		File savePath = new File(saveDir);
		if(!savePath.exists()){
			savePath.mkdirs();
		}
		String saveFileName = saveDir+File.separator+uploadifyFileName;
		File newFile = new File(saveFileName);
		if(newFile.exists()){
			newFile.delete();
		}
		uploadify.renameTo(newFile);		//将文件移到制定位置并且改名
		
		/****************************开始解析******************************/
		ServiceProvider.getService(AssetService.class).assetBatchUpload(saveFileName);
			
	}
	
	public void transAssetObjectToAssetInfo() throws DocumentException{
		ServiceProvider.getService(AssetService.class).transAssetObjectToAssetInfo(null);
		
	}
	
	public void transAssetObjectToAssetInfoWithAssetNo() throws DocumentException{
		ServiceProvider.getService(AssetService.class).transAssetObjectToAssetInfoWithAssetNo();
	}
	
	
	
	
}

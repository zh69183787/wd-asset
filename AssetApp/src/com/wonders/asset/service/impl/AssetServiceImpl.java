package com.wonders.asset.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.wonders.api.dto.AssetInfo;
import com.wonders.asset.dao.impl.AssetDaoImpl;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.dom4j.DocumentException;

import com.wonders.asset.base.service.impl.BaseServiceImpl;
import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.dao.AssetDao;
import com.wonders.asset.dao.AssetOwnerDao;
import com.wonders.asset.dao.AssetPriceDao;
import com.wonders.asset.dao.AssetProjectRelationDao;
import com.wonders.asset.dao.AssetRecordDao;
import com.wonders.asset.dao.AssetStateDao;
import com.wonders.asset.dao.AssetTypeDao;
import com.wonders.asset.dao.ContractDao;
import com.wonders.asset.dao.DepartmentDao;
import com.wonders.asset.dao.EnterpriseDao;
import com.wonders.asset.dao.LineDao;
import com.wonders.asset.dao.OrganizationDao;
import com.wonders.asset.dao.ProjectDao;
import com.wonders.asset.dao.StationDao;
import com.wonders.asset.dao.UnitMasterDao;
import com.wonders.asset.model.Asset;
import com.wonders.asset.model.AssetOwner;
import com.wonders.asset.model.AssetPrice;
import com.wonders.asset.model.AssetProjectRelation;
import com.wonders.asset.model.AssetRecord;
import com.wonders.asset.model.AssetState;
import com.wonders.asset.model.AssetType;
import com.wonders.asset.model.Contract;
import com.wonders.asset.model.Department;
import com.wonders.asset.model.Enterprise;
import com.wonders.asset.model.Line;
import com.wonders.asset.model.Organization;
import com.wonders.asset.model.Project;
import com.wonders.asset.model.Station;
import com.wonders.asset.model.UnitMaster;
import com.wonders.asset.service.AssetService;
import com.wonders.asset.service.AssetTypeService;
import com.wonders.asset.service.ProjectService;
import com.wonders.framework.util.ServiceProvider;
import org.hibernate.SessionFactory;

public class AssetServiceImpl extends BaseServiceImpl<Asset,String> implements AssetService{

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	private AssetDao assetDao;
	private AssetTypeDao assetTypeDao;
	private UnitMasterDao unitMasterDao;
	private AssetOwnerDao assetOwnerDao;
	private OrganizationDao organizationDao; 
	private DepartmentDao departmentDao;
	private AssetPriceDao assetPriceDao;
	private AssetStateDao assetStateDao;
	private ProjectDao projectDao;
	private ContractDao contractDao;
	private EnterpriseDao enterpriseDao;
	private AssetProjectRelationDao assetProjectRelationDao;
	private LineDao lineDao;
	private StationDao stationDao;
	private AssetRecordDao assetRecordDao;
	
	
	public AssetRecordDao getAssetRecordDao() {
		return assetRecordDao;
	}

	public void setAssetRecordDao(AssetRecordDao assetRecordDao) {
		this.assetRecordDao = assetRecordDao;
	}

	public StationDao getStationDao() {
		return stationDao;
	}

	public void setStationDao(StationDao stationDao) {
		this.stationDao = stationDao;
	}

	public LineDao getLineDao() {
		return lineDao;
	}

	public void setLineDao(LineDao lineDao) {
		this.lineDao = lineDao;
	}

	public AssetProjectRelationDao getAssetProjectRelationDao() {
		return assetProjectRelationDao;
	}

	public void setAssetProjectRelationDao(
			AssetProjectRelationDao assetProjectRelationDao) {
		this.assetProjectRelationDao = assetProjectRelationDao;
	}

	public EnterpriseDao getEnterpriseDao() {
		return enterpriseDao;
	}

	public void setEnterpriseDao(EnterpriseDao enterpriseDao) {
		this.enterpriseDao = enterpriseDao;
	}

	public ContractDao getContractDao() {
		return contractDao;
	}

	public void setContractDao(ContractDao contractDao) {
		this.contractDao = contractDao;
	}

	public ProjectDao getProjectDao() {
		return projectDao;
	}

	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	public AssetStateDao getAssetStateDao() {
		return assetStateDao;
	}

	public void setAssetStateDao(AssetStateDao assetStateDao) {
		this.assetStateDao = assetStateDao;
	}

	public AssetPriceDao getAssetPriceDao() {
		return assetPriceDao;
	}

	public void setAssetPriceDao(AssetPriceDao assetPriceDao) {
		this.assetPriceDao = assetPriceDao;
	}

	public DepartmentDao getDepartmentDao() {
		return departmentDao;
	}

	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	public OrganizationDao getOrganizationDao() {
		return organizationDao;
	}

	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}

	public AssetOwnerDao getAssetOwnerDao() {
		return assetOwnerDao;
	}

	public void setAssetOwnerDao(AssetOwnerDao assetOwnerDao) {
		this.assetOwnerDao = assetOwnerDao;
	}

	public UnitMasterDao getUnitMasterDao() {
		return unitMasterDao;
	}

	public void setUnitMasterDao(UnitMasterDao unitMasterDao) {
		this.unitMasterDao = unitMasterDao;
	}

	public AssetDao getAssetDao() {
		return assetDao;
	}

	public void setAssetDao(AssetDao assetDao) {
		this.assetDao = assetDao;
		setBaseDao(assetDao);
	}

	public AssetTypeDao getAssetTypeDao() {
		return assetTypeDao;
	}

	public void setAssetTypeDao(AssetTypeDao assetTypeDao) {
		this.assetTypeDao = assetTypeDao;
	}

    public static void main(String[] args) {

        int totalRow= 62999;
        int totalPageNo = totalRow%100 == 0 ? totalRow/100 :totalRow/100 +1;
        System.out.println(NumberUtils.createBigDecimal("16.0").longValue());
    }

	@Override
	public void importData() throws ParseException {
		Date date = new Date();
        int totalRow= 69426;//347error
        int totalPageNo = totalRow%100 == 0 ? totalRow/100 :totalRow/100 +1;
        for(int x = 1;x<=totalPageNo;x++){

		List<Object[]> resultList = assetDao.findImportData(x,100);			//查询到所有的数据
		if(resultList==null || resultList.size()<1) return ;
		String errorInfo = "";
		List<Asset> saveList = new ArrayList<Asset>();
		for(int i=0,len=resultList.size(); i<len; i++){
            Object[] temp = resultList.get(i);
			
			if(temp!=null && temp.length>0){
				boolean headInfo = false;
				String assetNo = getNotEmptyValue(temp[0]);					//ok
                System.out.println(x+"：==========="+assetNo+"===============");
				String assetDescription = getNotEmptyValue(temp[1]);		//ok
				String projectName = getNotEmptyValue(temp[2]);				//
				String projectNo = getNotEmptyValue(temp[3]);				//ok，项目编号，查项目表
				String projectContractNo = getNotEmptyValue(temp[4]);		//ok，合同编号，查合同表
				String assetType1 = getNotEmptyValue(temp[5]);				
				String assetType1desc = getNotEmptyValue(temp[6]);			//中文冗余
				String assetType2 = getNotEmptyValue(temp[7]);
				String assetType2desc = getNotEmptyValue(temp[8]);			//中文冗余
				String assetType = getNotEmptyValue(temp[9]);
				String assetTypeDesc = getNotEmptyValue(temp[10]);			//中文冗余
				String unitCode = getNotEmptyValue(temp[11]);				//单位表
				String assetQty = getNotEmptyValue(temp[12]);
				String finGZXH = getNotEmptyValue(temp[13]);				//规格型号，暂时插资产型号type
				
				String produceArea= getNotEmptyValue(temp[14]);				//ok
				
				String manufacturer = getNotEmptyValue(temp[15]);			//生产厂商，暂时不填，数据给的是中文
				String supplier = getNotEmptyValue(temp[16]);				//供应厂商，暂时不填，数据该字段没有值
				
				String leaveFactory = getNotEmptyValue(temp[17]);			//ok,数据没有值，必须是yyyy-MM-dd
				String supplierDate = getNotEmptyValue(temp[18]);			//ok,数据没有值，必须是yyyy-MM-dd
				
				String installSide = getNotEmptyValue(temp[19]);			//安装地点,可能是车站
				
				String ownershipUnit = getNotEmptyValue(temp[20]);				//ok,权属单位编号
				String ownershipUnitDesc = getNotEmptyValue(temp[21]);			//中文冗余,权属单位
				String ownershipPer = getNotEmptyValue(temp[22]);				//ok,权属负责人,无数据
				String ownershipName = getNotEmptyValue(temp[23]);				//无该字段，无数据
				String useUnit = getNotEmptyValue(temp[24]);					//ok,使用单位id
				String useUnitDesc = getNotEmptyValue(temp[25]);				//中文冗余,使用单位
				String usePer = getNotEmptyValue(temp[26]);						//使用人id
				String usePerName = getNotEmptyValue(temp[27]);					//ok,使用人
				String maintainDepart = getNotEmptyValue(temp[28]);				//维护部门id
				String maintainDeptName = getNotEmptyValue(temp[29]);			//中文冗余,维护部门
				
				String locationCode = getNotEmptyValue(temp[30]);				//资产位置编码,可能是车站
				
				String locationDesc = getNotEmptyValue(temp[31]);				//中文冗余,资产位置编码
				String startUseDate = getNotEmptyValue(temp[32]);				//ok,开始使用时间
				String endUseDate = getNotEmptyValue(temp[33]);					//ok,停止使用时间
				String scrapDate = getNotEmptyValue(temp[34]);					//ok，批准报废时间
				String transferDate = getNotEmptyValue(temp[35]);				//ok，移交时间
				String useState = getNotEmptyValue(temp[36]);					//ok,使用状态
				String assetsPicture = getNotEmptyValue(temp[37]);				//无该字段,资产图片
				String designUseLife = getNotEmptyValue(temp[38]);				//ok,设计使用年限
				String expectUseLife = getNotEmptyValue(temp[39]);				//ok,预期使用寿命
				String warrantyPeriod = getNotEmptyValue(temp[40]);				//ok,保修期至
				String overhaulRate = getNotEmptyValue(temp[41]);				//无该字段,大修频次
				String factoryPrice = getNotEmptyValue(temp[42]);				//ok,出厂价
				String contractPrice = getNotEmptyValue(temp[43]);				//ok,合同价
				String originalValue = getNotEmptyValue(temp[44]);				//ok,原值
				String haveList = getNotEmptyValue(temp[45]);					//无该字段,资料及清单
				String depreciateMethod = getNotEmptyValue(temp[46]);			//ok.折旧方法,
				String accuDepreciate = getNotEmptyValue(temp[47]);				//ok,累计折旧,数据为空，需要数字类型
				String netAssetValue = getNotEmptyValue(temp[48]);				//ok,净值			
				String postPosition = getNotEmptyValue(temp[49]);				//ok，铭牌张贴位置，取值后直接插表
				String equipList = getNotEmptyValue(temp[50]);					//无该字段，设备清单,数据为空
				String completeTransAssetType = getNotEmptyValue(temp[51]);		//无该字段，竣工移交资产类型,不知道插哪张表，数据为空
				String operateProjectAsset = getNotEmptyValue(temp[52]);		//无该字段，项目资产标示,不知道插哪张表,数据为空
				String operateProjectAssetDate = getNotEmptyValue(temp[53]);	//ok
				String operateProjectAssetAccount = getNotEmptyValue(temp[54]);	//ok
				String projectAppDocNo = getNotEmptyValue(temp[55]);			//ok
				String remark = getNotEmptyValue(temp[56]);						//ok，取值后直接插表

                String lineCode = getNotEmptyValue(temp[59]);						//ok，线路编码
//                String line = getNotEmptyValue(temp[60]);						//ok，线路名称
                String stationCode = getNotEmptyValue(temp[61]);						//ok，车站编码
//                String station = getNotEmptyValue(temp[62]);						//ok，车站名称



				
				/**供应商**制造商**新增***************************************************/
				List<Enterprise> menufacturers = enterpriseDao.findByNameAndType(manufacturer.trim(), "1"); 
				Enterprise menufacturer =null;
                if(StringUtils.isNotBlank(manufacturer)){
                    if((menufacturers==null||menufacturers.size()==0)){
                        menufacturer = new Enterprise();
                        menufacturer.setName(manufacturer.trim());
                        menufacturer.setType("1");
                        menufacturer.setCreateDate(date);
                        enterpriseDao.insert(menufacturer);
                    }else{
                        menufacturer = menufacturers.get(0);
                    }
                }

				
				List<Enterprise> supplierCops = enterpriseDao.findByNameAndType(supplier.trim(), "2");
				Enterprise supplierCop = null;
                if(StringUtils.isNotBlank(supplier)){
                    if((supplierCops==null||supplierCops.size()==0)){
                        supplierCop = new Enterprise();
                        supplierCop.setName(supplier.trim());
                        supplierCop.setType("2");
                        supplierCop.setCreateDate(date);
                        enterpriseDao.insert(supplierCop);
                    }else{
                        supplierCop = supplierCops.get(0);
                    }
                }

				
				/*********************************************************/
				
				
				/**使用单位**权属单位********************************/
				Organization ownerOrganization = null;
				if(ownershipUnit!=null && !"".equals(ownershipUnit)){
					ownerOrganization = organizationDao.findByPrimaryKey(ownershipUnit);
					if(ownerOrganization==null){
						errorInfo +="资产编号:"+assetNo+";权属单位编号(OWNER_SHIP_UNIT)没有对应值;";
						headInfo = true;
					}
				}
				
				Organization useOrganization = null;
				if(useUnit!=null && !"".equals(useUnit)){
					useOrganization = organizationDao.findByPrimaryKey(useUnit);
					if(useOrganization==null){
						if(!headInfo){
							errorInfo += "资产编号:"+assetNo;
						}
						errorInfo +="使用单位编号(USE_UNIT)没有对应值;";
					}
				}

                Line line = null;
                if(lineCode!=null && !"".equals(lineCode)){
                    line = getLineDao().findByCode(lineCode);
                    if(line==null){
                        if(!headInfo){
                            errorInfo += "资产编号:"+assetNo;
                        }
                        errorInfo +="使用线路编号(LINE_CODE)没有对应值;";
                    }
                }

                Station station = null;
                if(stationCode!=null && !"".equals(stationCode)){
                    station = getStationDao().findByCodeAndLineId(stationCode,line.getId());
                    if(station==null){
                        if(!headInfo){
                            errorInfo += "资产编号:"+assetNo;
                        }
                        errorInfo +="使用单位编号(STATION_CODE)没有对应值;";
                    }
                }


				
				
				/*******************************************/
				
				/**维护部门************************/
				Department department = null;
				if(maintainDepart!=null && !"".equals(maintainDepart)){
					department = departmentDao.findByPrimaryKey(maintainDepart);
					if(department==null){
						if(!headInfo){
							errorInfo += "资产编号:"+assetNo;
						}
						errorInfo += "维护单位编号(MAINTAIN_DEPART)没有对应值;";
					}
				}
				/*********************************/
				
				
				/**资产权属信息******************************************/
				AssetOwner assetOwner = new AssetOwner();
				assetOwner.setOwner(ownershipPer);
				assetOwner.setUser(usePer);
				assetOwner.setBeginUseDate(startUseDate.equals("") ? null:sdf.parse(startUseDate));
				assetOwner.setStopUseDate(endUseDate.equals("") ? null:sdf.parse(endUseDate));
				assetOwner.setReceiveDate(transferDate.equals("") ? null:sdf.parse(transferDate));
                assetOwner.setLineCodeId(line.getId());
                assetOwner.setStationCodeId(station.getId());

				
				assetOwner.setUseOrganizationCodeId(useOrganization.getId());
				assetOwner.setOwnerOrganizationCodeId(ownerOrganization.getId());
				assetOwner.setDepartmentCodeId(department.getId());
				assetOwner.setCreateDate(date);
				assetOwnerDao.insert(assetOwner);
				/*********************************/
				
				/**资产状态信息**/
				AssetState assetState = new AssetState();
				assetState.setNameplateSite(postPosition);
				assetState.setUseState(useState);
				assetState.setCreateDate(date);
				assetStateDao.insert(assetState);
				/*********************************/

                AssetRecord assetRecord = new AssetRecord();
                assetRecord.setAssetType(completeTransAssetType);		//资产类型
                if(!operateProjectAssetDate.equals("")){
                    operateProjectAssetDate=operateProjectAssetDate.replaceAll("/","-");
                    assetRecord.setFinishDate(sdf.parse(operateProjectAssetDate));//资产交付日期
                }
                assetRecord.setProjectAppNo(projectAppDocNo);//立项或可研批复文号
                assetRecord.setMaintainCost(operateProjectAssetAccount);// 资产决算价
                assetRecord.setFinishPrice(operateProjectAssetAccount);// 资产决算价
                assetRecord.setProjectName(projectName);// 资产决算价
				
				/**资产信息***************************************/
				//基础属性字段
				Asset asset = new Asset();
                Asset t = assetDao.findByAssetCode(assetNo);
                if(t!=null){
                    asset = t;
                }
				asset.setCreateDate(date);
				asset.setAssetCode(assetNo);		
				asset.setName(assetDescription);	
				asset.setEquipmentList(equipList);


//				Project project = projectDao.findByProjectNo(projectNo);
//				if(project==null){
//					asset.setProjectNo(projectNo);
//				}else{
//					asset.setProject(project);
//				}
				Contract contract = contractDao.findByContractNo(projectContractNo);
				if(contract==null){
					asset.setContractNo(projectContractNo);
				}else{
					asset.setContract(contract);
				}
				asset.setDetailInstallSite(installSide);
				if(!scrapDate.equals("")){
					asset.setApprovalScrapDate(sdf.parse(scrapDate));
				}
				asset.setAssetPic(assetsPicture);
				asset.setOverhaulRate(overhaulRate);
				asset.setCompleteTransAssetType(completeTransAssetType);
				asset.setOperateProjectAsset(operateProjectAsset);
				if(!operateProjectAssetDate.equals("")){
                    operateProjectAssetDate=operateProjectAssetDate.replaceAll("/","-");
					asset.setOperateProjectAssetDate(sdf.parse(operateProjectAssetDate));
				}
				asset.setProjectAppDocNo(projectAppDocNo);
				
				
				AssetType mainType = assetTypeDao.findByAllCode(assetType1);
				if(mainType==null){
					if(!headInfo){
						errorInfo += "资产编号:"+assetNo;
					}
					errorInfo += "大类编号(ASSET_TYPE1)没有对应值;";
				}
				asset.setMainType(mainType);
				AssetType subType = assetTypeDao.findByAllCode(assetType2);
				if(subType==null){
					if(!headInfo){
						errorInfo += "资产编号:"+assetNo;
					}
					errorInfo += "中类编号(ASSET_TYPE2)没有对应值;";
				}
				asset.setSubType(subType);
				AssetType detailType = assetTypeDao.findByAllCode(assetType);
				if(detailType==null){
					if(!headInfo){
						errorInfo += "资产编号:"+assetNo;
					}
					errorInfo += "小类编号(ASSET_TYPE)没有对应值;";
				}
				asset.setDetailType(detailType);
				
				UnitMaster unitMaster = unitMasterDao.findByName(unitCode);
				if(unitMaster==null){
					unitMaster = new UnitMaster();
					unitMaster.setName(unitCode);
					unitMasterDao.insert(unitMaster);
				}
				asset.setUnit(unitMaster);
				if(!designUseLife.equals("") ){
					asset.setUseLife(designUseLife);
				}
				if(!expectUseLife.equals("")&&NumberUtils.isNumber(expectUseLife)){

					asset.setExpectancyLife(NumberUtils.createBigDecimal(expectUseLife).longValue());
				}
                if(StringUtils.isNotBlank(warrantyPeriod))
				asset.setWarrantyPeriod(sdf.parse(warrantyPeriod));
				if(!assetQty.equals("")&&NumberUtils.isNumber(assetQty)){
					asset.setCount(NumberUtils.createBigDecimal(assetQty).doubleValue());
				}
				
				asset.setType(finGZXH);
				asset.setManufactureCountry(produceArea);
				try {
					asset.setMadeDate(leaveFactory.equals("") ? null : sdf.parse(leaveFactory));
					asset.setUseDate(supplierDate.equals("") ? null : sdf.parse(supplierDate));
				} catch (ParseException e) {
					e.printStackTrace();
				}					//该字段没有值
				
//				if(!errorInfo.equals("")){
//					asset.setRemarks(remark+"。"+errorInfo);
//				}else{
					asset.setRemarks(remark);
//				}
                if(StringUtils.isNotBlank(projectName)){
                    Project project;
                    try {
                        project = ServiceProvider.getService(ProjectService.class).findByShortName(projectName.trim());
                        if(project!=null){
                            asset.setProjectNo(project.getProjectNo());
                            asset.setProject(project);
                        }else{
                            System.out.println("project==null,"+i+","+projectName.trim());
                        }

                    } catch (DocumentException e) {
                        e.printStackTrace();
                    }

                }

				
				
				//关联字段
				asset.setManufacturer(menufacturer);
				asset.setSupplier(supplierCop);
                if(mainType!=null)
				asset.setMainTypeCodeId(mainType.getId());
                if(subType!=null)
				asset.setSubTypeCodeId(subType.getId());
                if(detailType!=null)
				asset.setDetailTypeCodeId(detailType.getId());
				asset.setUnit(unitMaster);
				asset.setAssetOwnerInf(assetOwner);
				/*Set<AssetPrice> prices = new HashSet<AssetPrice>();
				prices.add(assetPrice);
				asset.setPrices(prices);*/		//变成一对多关系了
				asset.setState(assetState);
				/*****************************************************/
				
				saveList.add(asset);
				
				
				/**资产价值信息****************************/
				AssetPrice assetPrice = new AssetPrice();
				if(!operateProjectAssetAccount.equals("")){
					assetPrice.setFinalPrice(Double.valueOf(operateProjectAssetAccount));

				}
				if(!netAssetValue.equals("")){
					assetPrice.setNetValue(Double.valueOf(netAssetValue));

				}
				if(!accuDepreciate.equals("")){
					assetPrice.setAccumulatedDepreciation(Double.valueOf(accuDepreciate));

				}
				assetPrice.setDepreciationMethod(depreciateMethod);
				if(!originalValue.equals("")){
					assetPrice.setOriginalValue(Double.valueOf(originalValue));
                    asset.setOriginalValue(Double.valueOf(originalValue));
				}
				if(!contractPrice.equals("")){
					assetPrice.setContractPrice(Double.valueOf(contractPrice));
                    asset.setContractPrice(Double.valueOf(contractPrice));
				}
				if(!factoryPrice.equals("")){
					assetPrice.setFactoryPrice(Double.valueOf(factoryPrice));
                    asset.setFactoryPrice(Double.valueOf(factoryPrice));
				}
				assetPrice.setCreateDate(date);
				asset.setAssetPrice(assetPrice);
                asset.setVerifyState("2");
                assetRecord.setAsset(asset);
                assetRecord.setCreateDate(new Date());
                assetRecord.setUpdateDate(new Date());
                asset.setAssetRecords(new HashSet<AssetRecord>());
                asset.getAssetRecords().add(assetRecord);
//                assetRecordDao.saveOrUpdate(assetRecord);

				//assetPrice.setAsset(asset);
				//assetPriceDao.insert(assetPrice);
				/*********************************/
			}
//			break;
		}
		System.out.println("共插入数据:"+saveList.size()*x+" 条");
		assetDao.save(saveList);

        }
	}

	
	@Override
	public Pagination<AssetProjectRelation> findProjectRecord(Map<String, String> filterMap, Map<String, String> sortMap,int startIndex, int pageSize) {
		Pagination<AssetProjectRelation> results = assetProjectRelationDao.findBy(filterMap, sortMap,startIndex,pageSize);
		return results;
	}

	
	@Override
	public void assetBatchUpload(String fullFileName) {
		HSSFRow row;
		InputStream is;
		try {
			is = new FileInputStream(fullFileName);
			HSSFWorkbook  hssfWorkbook = new HSSFWorkbook(is);
			HSSFSheet sheet = hssfWorkbook.getSheetAt(0);	// 获得excel中的第一张表
			int rowsAll = sheet.getPhysicalNumberOfRows();	//总列数
			if(sheet.getPhysicalNumberOfRows()>0){			//行数大于0
				List<Asset> saveList = new ArrayList<Asset>();
				HSSFCell cell ;
				for(int i=1; i<rowsAll; i++){		//循环行,从第2行开始
//				for(int i=1; i<2; i++){	
					Asset asset = new Asset();
					AssetOwner assetOwner = new AssetOwner();
					AssetState assetState = new AssetState();
//					AssetPrice assetPrice = new AssetPrice();
					
					row = sheet.getRow(i);
					cell = row.getCell(1);			//项目名称********
					if(cell!=null){
						List<Project> list = projectDao.findByShortName(cell.toString().trim());
						if(list!=null && list.size()==1){
							asset.setProject(list.get(0));
						}
					}
					
					cell = row.getCell(2);			//项目编号********
					cell = row.getCell(3);			//项目合同编号********
					
					
					cell = row.getCell(4);			//资产编码
					if(cell!=null){
						asset.setAssetCode(cell.toString());
					}
					cell = row.getCell(5);			//资产名称
					if(cell!=null){
						asset.setName(cell.toString());
					}
					cell = row.getCell(6);			//大类
					if(cell!=null){
						List<AssetType> mainTypeList = assetTypeDao.findByNameWithPublish(cell.toString().trim());
						if(mainTypeList!=null && mainTypeList.size()==1){
							asset.setMainTypeCodeId(mainTypeList.get(0).getCodeId());
						}
					}
					
					cell = row.getCell(7);			//中类
					if(cell!=null && StringUtils.isNotEmpty(asset.getMainTypeCodeId())){
						List<AssetType> subTypeList = assetTypeDao.findByNameAndLineCodeId(cell.toString().trim(), asset.getMainTypeCodeId());
						if(subTypeList!=null && subTypeList.size()==1){
							asset.setSubTypeCodeId(subTypeList.get(0).getCodeId());
						}
					}
					
					cell = row.getCell(8);			//小类
					if(cell!=null && StringUtils.isNotEmpty(asset.getSubTypeCodeId())){
						List<AssetType> detailTypeList = assetTypeDao.findByNameAndLineCodeId(cell.toString().trim(), asset.getSubTypeCodeId());
						if(detailTypeList!=null && detailTypeList.size()==1){
							asset.setDetailTypeCodeId(detailTypeList.get(0).getCodeId());
						}
					}
					
					cell = row.getCell(9);			//数量单位
					if(cell!=null && !"".equals(cell.toString())){
						UnitMaster um = unitMasterDao.findByName(cell.toString().trim());
						if(um==null) {
							um = new UnitMaster();
							um.setName(cell.toString().trim());
							unitMasterDao.insert(um);
						}
						asset.setUnit(um);
					}
					cell = row.getCell(10);			//数量
					if(cell!=null && !"".equals(cell.toString())){
						asset.setCount(Double.valueOf(cell.toString()));
					}
					cell = row.getCell(11);			//规格型号
					if(cell!=null){
						asset.setType(cell.toString().trim());
					}
					cell = row.getCell(12);			//产地
					if(cell!=null){
						asset.setManufactureCountry(cell.toString().trim());
					}
					cell = row.getCell(13);			//生产厂商
					if(cell!=null){
						List<Enterprise> enterprises = enterpriseDao.findByNameAndType(cell.toString().trim(), "2");
						if(enterprises!=null && enterprises.size()==1){
							asset.setManufacturer(enterprises.get(0));
						}
					}
					cell = row.getCell(14);			//供应商
					if(cell!=null){
						List<Enterprise> enterprises = enterpriseDao.findByNameAndType(cell.toString().trim(), "1");
						if(enterprises!=null && enterprises.size()==1){
							asset.setSupplier(enterprises.get(0));
						}
					}
					cell = row.getCell(15);			//出厂日期
					if(cell!=null && !"".equals(cell.toString())){
						asset.setMadeDate(sdf.parse(cell.toString().trim()));
					}
					cell = row.getCell(16);			//供应日期
					if(cell!=null && !"".equals(cell.toString())){
						asset.setUseDate(sdf.parse(cell.toString().trim()));
					}
					cell = row.getCell(17);			//安装地点
					if(cell!=null){
						asset.setDetailInstallSite(cell.toString().trim());
					}
					
					
					cell = row.getCell(18);			//权属单位
					if(cell!=null){
						Organization organization = organizationDao.findByCodeIdWithPublish(cell.toString().trim());
						if(organization!=null)
							assetOwner.setOwnerOrganizationCodeId(organization.getCode());
					}
					cell = row.getCell(19);			//使用单位
					if(cell!=null){
						Organization organization = organizationDao.findByCodeIdWithPublish(cell.toString().trim());
						if(organization!=null)
							assetOwner.setUseOrganizationCodeId(organization.getCodeId());
					}
					cell = row.getCell(20);			//维护部门
					if(cell!=null){
						Department department = departmentDao.findByCodeIdWithPublish(cell.toString().trim());
						if(department!=null)
							assetOwner.setDepartmentCodeId(department.getCodeId());
					}
					cell = row.getCell(21);			//所属线路
					if(cell!=null){
						String lineId = cell.toString();
						Line line = lineDao.findByCodeIdWithPublish(lineId);
						if(line!=null)
							assetOwner.setLineCodeId(line.getCodeId());
					}
					cell = row.getCell(22);			//所属车站
					if(cell!=null && StringUtils.isNotEmpty(assetOwner.getLineCodeId())){
						Station station = stationDao.findByCodeAndLineCodeIdWithPublish(cell.toString().trim(),assetOwner.getLineCodeId());
						if(station!=null)
							assetOwner.setStationCodeId(station.getCodeId());
					}
					cell = row.getCell(23);			//权属责任人
					if(cell!=null){
						assetOwner.setOwner(cell.toString().trim());
					}
					cell = row.getCell(24);			//使用责任人
					if(cell!=null){
						assetOwner.setUser(cell.toString().trim());
					}
					cell = row.getCell(25);			//开始使用时间
					if(cell!=null && !"".equals(cell.toString())){
						assetOwner.setBeginUseDate(sdf.parse(cell.toString().trim()));
					}
					cell = row.getCell(26);			//结束使用时间
					if(cell!=null && !"".equals(cell.toString())){
						assetOwner.setStopUseDate(sdf.parse(cell.toString().trim()));
					}
					cell = row.getCell(27);			//批准报废时间
					if(cell!=null && !"".equals(cell.toString())){
						asset.setApprovalScrapDate(sdf.parse(cell.toString().trim()));
					}
					
					cell = row.getCell(28);			//移交时间
					if(cell!=null && !"".equals(cell.toString())){
						assetOwner.setReceiveDate(sdf.parse(cell.toString().trim()));
					}
					cell = row.getCell(29);			//当前使用状态
					if(cell!=null){
						assetState.setUseState(cell.toString().trim());
					}
					cell = row.getCell(30);			//资产图片名称
					if(cell!=null){
						asset.setAssetPic(cell.toString().trim());
					}
					cell = row.getCell(31);			//设计使用年限
					if(cell!=null && !"".equals(cell.toString())){
						asset.setUseLife(cell.toString().trim());
					}
					cell = row.getCell(32);			//预期使用寿命
					if(cell!=null && !"".equals(cell.toString())){
						asset.setExpectancyLife(Long.valueOf(cell.toString().trim()));
					}
					cell = row.getCell(33);			//保修期至
					if(cell!=null && !"".equals(cell.toString())){
						asset.setWarrantyPeriod(sdf.parse(cell.toString().trim()));
					}
					cell = row.getCell(34);			//大修频次
					if(cell!=null){
						asset.setOverhaulRate(cell.toString().trim());
					}
					
					cell = row.getCell(35);			//出厂价
					if(cell!=null && !"".equals(cell.toString())){
						asset.setFactoryPrice(Double.valueOf(cell.toString().trim()));
					}
					cell = row.getCell(36);			//合同价
					if(cell!=null && !"".equals(cell.toString())){
						asset.setContractPrice(Double.valueOf(cell.toString().trim()));
					}
					cell = row.getCell(37);			//原值
					if(cell!=null && !"".equals(cell.toString())){
						asset.setOriginalValue(Double.valueOf(cell.toString().trim()));
					}
					cell = row.getCell(38);			//技术、规格、操作资料及清单
					if(cell!=null){
						asset.setDataList(cell.toString().trim());
					}
					
					cell = row.getCell(39);			//折旧方法
					if(cell!=null){
						asset.setDepreciationMethod(cell.toString().trim());
					}
					
					/*cell = row.getCell(39);			//折旧方法
					if(cell!=null){
						assetPrice.setDepreciationMethod(cell.toString().trim());
					}
					cell = row.getCell(40);			//累计折旧
					if(cell!=null && !"".equals("")){
						assetPrice.setAccumulatedDepreciation(Double.valueOf(cell.toString().trim()));
					}
					cell = row.getCell(41);			//资产净值
					if(cell!=null && !"".equals(cell.toString())){
						assetPrice.setNetValue(Double.valueOf(cell.toString().trim()));
					}*/
					cell = row.getCell(42);			//铭牌张贴位置
					if(cell!=null){
						assetState.setNameplateSite(cell.toString().trim());
					}
					cell = row.getCell(43);			//设备清单
					if(cell!=null){
						asset.setEquipmentList(cell.toString().trim());
					}
					
					cell = row.getCell(44);			//项目（线路）竣工移交资产类型标示(初始/新增/更新）(必填）
					if(cell!=null){
						asset.setCompleteTransAssetType(cell.toString().trim());
					}
					cell = row.getCell(45);			//已运营项目（线路）资产大修/更新改造项目资产标示(大修/改造）
					if(cell!=null){
						asset.setOperateProjectAsset(cell.toString().trim());
					}
					cell = row.getCell(46);			//已运营项目（线路）资产大修/改造交付使用日期(yyyy/mm/dd)
					if(cell!=null && !"".equals(cell.toString())){
						asset.setOperateProjectAssetDate(sdf.parse(cell.toString().trim()));
					}
					cell = row.getCell(47);			//已运营项目（线路）资产大修/改造决算价（元）
					if(cell!=null && !"".equals(cell.toString())){
						asset.setOverhaulFinalPrice(Double.valueOf(cell.toString().trim()));
					}
					cell = row.getCell(48);			//立项或可研批复文号(大修更新改造项目、报废资产项目、新购置资产项目等必填)/沪地铁（20**）**号（必填）
					if(cell!=null){
						asset.setProjectAppDocNo(cell.toString().trim());
					}
					cell = row.getCell(49);			//备注
					if(cell!=null){
						asset.setRemarks(cell.toString().trim());
						
					}
					asset.setRemarks("test");
					/*****设置系统信息***************/
					asset.setCreateDate(new Date());
					assetOwner.setCreateDate(new Date());
					assetState.setCreateDate(new Date());
					
					assetOwnerDao.insert(assetOwner);
					assetStateDao.insert(assetState);
					
					asset.setAssetOwnerInf(assetOwner);
					asset.setState(assetState);
					
					assetDao.insert(asset);
					
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public String getNotEmptyValue(Object o){
		if(o==null) return "";
		return o.toString();
	}

	@Override
	public List<Object[]> findBeforeLastExecuteDate(Date lastExecuteDate) {
		
		return assetDao.findBeforeLastExecuteDate(lastExecuteDate);
	}

	@Override
	public void transAssetObjectToAssetInfo(Date lastExecuteDate) {	  
	    String errorInfo="";
	    boolean saveStatus = true;
	    
	    assetDao.changeSessionLanguage();
	    List<Object[]> result  = findBeforeLastExecuteDate(lastExecuteDate);
	    System.out.println("total =====" + result.size());

		if(result!=null && result.size()>0){
			int errorCount = 0;
			saveStatus = true;
			for(int i=0,length=result.size(); i<length; i++){
//			for(int i=0,length=result.size(); i<100; i++){				//测试用，先插入100个
				System.out.println("===== "+i+" of "+length);
				saveStatus = true;

				

				String currentError = "";
				Object[] target = result.get(i);
				
				Asset asset =null;
				AssetState assetState = null;
				AssetOwner assetOwner = null;
				AssetPrice assetPrice = null;
				AssetRecord assetRecord = new AssetRecord();
				if(StringUtils.isNotEmpty(ObjectUtils.toString(target[0]))){
					asset = assetDao.findByAssetCode(ObjectUtils.toString(target[0]));
				}
				if(asset==null){
					asset = new Asset();
					assetState = new AssetState();
					assetOwner = new AssetOwner();
					assetPrice = new AssetPrice();
				}else{
					assetState = asset.getState();
					assetOwner = asset.getAssetOwnerInf();
					assetPrice = asset.getAssetPrice();
				}
				
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[0]))){
					assetDao.findByAssetCode(ObjectUtils.toString(target[0]));
					asset.setAssetCode(ObjectUtils.toString(target[0]));		//资产编码
					try {
						Asset dataedAsset = assetDao.findByAssetCode(ObjectUtils.toString(target[0]));
						if(dataedAsset!=null){
							asset = dataedAsset;
							assetOwner = dataedAsset.getAssetOwnerInf();
							assetState = dataedAsset.getState();
							assetPrice = dataedAsset.getAssetPrice();
							if(assetOwner==null) assetOwner = new AssetOwner();
							if(assetState==null) assetState = new AssetState();
							if(assetPrice==null) assetPrice = new AssetPrice();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					saveStatus = false;
					errorCount++;
					currentError += "资产编码不能为空,";
					continue;
				}
				
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[1]))){
					asset.setName(ObjectUtils.toString(target[1]));				//资产名称
				}else{
					saveStatus = false;
					errorCount++;
					currentError += "资产名称不能为空,";
					
				}
				
				String projectName = ObjectUtils.toString(target[2]);			//项目名称
				if(StringUtils.isNotBlank(projectName)){
					Project project;
					try {
						project = ServiceProvider.getService(ProjectService.class).findByShortName(projectName.trim());
						if(project!=null){
							assetRecord.setProjectName(projectName);
							asset.setProjectNo(project.getProjectNo());
							asset.setProject(project);
						}else{
							System.out.println("project==null,"+i+","+projectName.trim());
						}
							
					} catch (DocumentException e) {
						e.printStackTrace();
					}
					
				}
				
				String assetCode = ObjectUtils.toString(target[3]);			//资产编码
				if(StringUtils.isNotBlank(assetCode) && assetCode.length()==6){
					String t1 = StringUtils.substring(assetCode, 0, 2);
					String t2 = StringUtils.substring(assetCode, 2, 4);
					String t3 = StringUtils.substring(assetCode, 4, 6);
					
					AssetType type1;
					try {
						type1 = ServiceProvider.getService(AssetTypeService.class).findMainTypeByCodeWithPublish(t1);
						if(type1!=null){
							asset.setMainTypeCodeId(type1.getCodeId());
							AssetType type2 = ServiceProvider.getService(AssetTypeService.class).findByParentIdAndCode(type1.getId(), t2);
							if(type2!=null){
								//asset.setSubType(type2);
								asset.setSubTypeCodeId(type2.getCodeId());
								AssetType type3 = ServiceProvider.getService(AssetTypeService.class).findByParentIdAndCode(type2.getId(), t3);
								if(type3!=null){
									//asset.setDetailType(type3);
									asset.setDetailTypeCodeId(type3.getCodeId());
								}else{
									saveStatus = false;
									errorCount++;
									currentError += "资产小类格式为空,";
								}
							}else{
								saveStatus = false;
								errorCount++;
								currentError += "资产中类格式出错,";
							}
						}else{
							saveStatus = false;
							errorCount++;
							currentError += "资产大类格式出错,";
						}
					} catch (DocumentException e) {
						e.printStackTrace();
					}
				}
				
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[4]))){
					UnitMaster um = unitMasterDao.findByName(ObjectUtils.toString(target[4]));		//单位
					if(um==null) {
						um = new UnitMaster();
						um.setName(ObjectUtils.toString(target[4]));
						unitMasterDao.insert(um);
					}
					asset.setUnit(um);
				}
				
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[5]))){
					asset.setCount(Double.valueOf(ObjectUtils.toString(target[5])));		//数量
				}
				
				asset.setType(ObjectUtils.toString(target[6]));				//规格型号
				asset.setManufactureCountry(ObjectUtils.toString(target[7]));	//产地
				
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[8]))){
					List<Enterprise> enterprises = enterpriseDao.findByNameAndType(ObjectUtils.toString(target[8]), "2");		//生产商
					if(enterprises!=null && enterprises.size()==1){
						asset.setManufacturer(enterprises.get(0));
					}
				}
				
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[9]))){
					List<Enterprise> enterprises = enterpriseDao.findByNameAndType(ObjectUtils.toString(target[9]), "1");		//供应商
					if(enterprises!=null && enterprises.size()==1){
						asset.setManufacturer(enterprises.get(0));
					}
				}
				
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[10]))){								//出厂日期
					try {
						asset.setMadeDate(sdf.parse(ObjectUtils.toString(target[10])));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[11]))){								//供应日期
					try {
						asset.setUseDate(sdf.parse(ObjectUtils.toString(target[11])));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[12]))){									//安装地点
					asset.setDetailInstallSite(ObjectUtils.toString(target[12]));
				}
				
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[13]))){									//权属单位
					Organization owner = organizationDao.findByCodeIdWithPublish(ObjectUtils.toString(target[13]));
					if(owner!=null)
						assetOwner.setOwnerOrganizationCodeId(owner.getCodeId());
				}
				
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[14]))){									//使用单位
					Organization user = organizationDao.findByCodeIdWithPublish(ObjectUtils.toString(target[14]));
					if(user!=null)
						assetOwner.setUseOrganizationCodeId(user.getCodeId());
				}
				
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[15]))){									//维护部门
					Department department = departmentDao.findByCodeIdWithPublish(ObjectUtils.toString(target[15]));
					if(department!=null)
						assetOwner.setDepartmentCodeId(department.getCodeId());
				}
				
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[16]))){									//开始使用时间
					try {
						assetOwner.setBeginUseDate(DateUtils.parseDate(ObjectUtils.toString(target[16]), new String[]{"yyyy-MM-dd"}));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[17]))){									//停止使用时间
					try {
						assetOwner.setStopUseDate(DateUtils.parseDate(ObjectUtils.toString(target[17]), new String[]{"yyyy-MM-dd"}));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
					
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[18]))){									//批准报废时间
					try {
						asset.setApprovalScrapDate(DateUtils.parseDate(ObjectUtils.toString(target[18]), new String[]{"yyyy-MM-dd"}));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[19]))){									//移交时间
					try {
						assetOwner.setReceiveDate(DateUtils.parseDate(ObjectUtils.toString(target[19]), new String[]{"yyyy-MM-dd"}));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[20]))){									//使用状态,AssetState
					//if("Use".equals(ObjectUtils.toString(target[20]))){
						assetState.setUseState(ObjectUtils.toString(target[20]));
					//}
					if("使用".equals(ObjectUtils.toString(target[20]))){
						assetState.setState("1");
					}else if("停用".equals(ObjectUtils.toString(target[20]))){
						assetState.setState("2");
					}else if("报废".equals(ObjectUtils.toString(target[20]))){
						assetState.setState("3");
					}else if("待报废".equals(ObjectUtils.toString(target[20]))){
						assetState.setState("4");
					}else if("封存".equals(ObjectUtils.toString(target[20]))){
						assetState.setState("5");
					}
				}
				
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[21]))){
					asset.setAssetPic(ObjectUtils.toString(target[21]));										//资产图片名称
				}
				
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[22]))){									//设计使用年限
					asset.setUseLife(ObjectUtils.toString(target[22]));
				}
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[23])) && NumberUtils.isNumber(ObjectUtils.toString(target[23]))){									//预期使用寿命
					asset.setExpectancyLife(Long.valueOf(ObjectUtils.toString(target[23])));
				}
				
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[24]))){									//保修期至
					try {
						asset.setWarrantyPeriod(DateUtils.parseDate(ObjectUtils.toString(target[24]), new String[]{"yyyy-MM-dd"}));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[25])) ){									//大修频次
					asset.setOverhaulRate(ObjectUtils.toString(target[25]));
				}
				
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[26])) && NumberUtils.isNumber(ObjectUtils.toString(target[26]))){									//出厂价
					asset.setFactoryPrice(Double.valueOf(ObjectUtils.toString(target[26])));
					assetPrice.setFactoryPrice(Double.valueOf(ObjectUtils.toString(target[26])));
				}
				
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[27])) && NumberUtils.isNumber(ObjectUtils.toString(target[27]))){									//合同价
					asset.setContractPrice(Double.valueOf(ObjectUtils.toString(target[27])));
					assetPrice.setContractPrice(Double.valueOf(ObjectUtils.toString(target[27])));
				}
				
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[28])) && NumberUtils.isNumber(ObjectUtils.toString(target[28]))){									//原值
					asset.setOriginalValue(Double.valueOf(ObjectUtils.toString(target[28])));
					assetPrice.setOriginalValue(Double.valueOf(ObjectUtils.toString(target[28])));
				}
				
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[29]))){										//资料清单
					asset.setDataList(ObjectUtils.toString(target[29]));
				}
				
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[30]))){										//折旧方法，AssetPrice
					//if(ObjectUtils.toString(target[30]).contains("LineMethod")){
						assetPrice.setDepreciationMethod(ObjectUtils.toString(target[30]));
					//}
				}
				
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[31])) && NumberUtils.isNumber(ObjectUtils.toString(target[31]))){				//累计折旧,AssetPrice
					assetPrice.setAccumulatedDepreciation(Double.valueOf(ObjectUtils.toString(target[31])));
				}
				
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[32])) && NumberUtils.isNumber(ObjectUtils.toString(target[32]))){				//净值
					assetPrice.setNetValue(Double.valueOf(ObjectUtils.toString(target[32])));
				}
				
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[33]))){					//铭牌张贴位置,AssetState
					//if("UpperLeft".equals(ObjectUtils.toString(target[33]))){
						assetState.setNameplateSite(ObjectUtils.toString(target[33]));
					//}
				}	
				
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[34]))){					//设备清单
					asset.setEquipmentList(ObjectUtils.toString(target[34]));
				}
				
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[35]))){					//竣工移交资产类型
					//if("Initial".equals(ObjectUtils.toString(target[35]))){
						asset.setCompleteTransAssetType(ObjectUtils.toString(target[35]));
					//}
				}
				
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[36]))){					//项目资产标示
					asset.setOperateProjectAsset(ObjectUtils.toString(target[36]));
					assetRecord.setAssetType(ObjectUtils.toString(target[36]));		//资产类型
				}
				
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[37]))){					//资产交付日期
					try {
						asset.setOperateProjectAssetDate(DateUtils.parseDate(ObjectUtils.toString(target[38]), new String[]{"yyyy-MM-dd"}));
						assetRecord.setFinishDate(asset.getOperateProjectAssetDate());
						
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[38]))){					//立项或可研批复文号		
					asset.setProjectAppDocNo(ObjectUtils.toString(target[38]));
					assetRecord.setProjectAppNo(ObjectUtils.toString(target[38]));
				}
				
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[39]))){							//备注
					asset.setRemarks(ObjectUtils.toString(target[39])+"\r\n TransAssetObject");
				}else{
					asset.setRemarks("TransAssetObject");
				}
				
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[40]))){					//创建时间
					try {
						asset.setCreateDate(DateUtils.parseDate(ObjectUtils.toString(target[40]), new String[]{"yyyy-MM-dd"}));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[41]))){					//修改时间
					try {
						asset.setUpdateDate(DateUtils.parseDate(ObjectUtils.toString(target[41]), new String[]{"yyyy-MM-dd"}));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[42]))){				//项目合同编码
					asset.setProjectContractNo(ObjectUtils.toString(target[42]));
				}

				if (StringUtils.isNotBlank(ObjectUtils.toString(target[43]))) { 			// 权属负责人
					asset.setOwnershipPer(ObjectUtils.toString(target[43]));
				}

				if (StringUtils.isNotBlank(ObjectUtils.toString(target[44]))) { 			// 使用负责人
					asset.setUsePer(ObjectUtils.toString(target[44]));
				}

				if (StringUtils.isNotBlank(ObjectUtils.toString(target[45]))) { 			// 资产位置
					asset.setLocationCode(ObjectUtils.toString(target[45]));
				}

				if (StringUtils.isNotBlank(ObjectUtils.toString(target[46])) && NumberUtils.isNumber(ObjectUtils.toString(target[46]))) { 			// 资产决算价
					asset.setOperateProjectAssetAccount(Double.valueOf(ObjectUtils.toString(target[46])));
					assetPrice.setFinalPrice(Double.valueOf(ObjectUtils.toString(target[46])));
					assetRecord.setMaintainCost(ObjectUtils.toString(target[46]));
					assetRecord.setFinishPrice(ObjectUtils.toString(target[46]));
				}
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[47]))){
					Line line = lineDao.findByCodeIdWithPublish(ObjectUtils.toString(target[47]));
					if(line!=null){
						assetOwner.setLineCodeId(line.getCodeId());
						if(StringUtils.isNotBlank(ObjectUtils.toString(target[49]))){
							Station station = stationDao.findByCodeAndLineCodeIdWithPublish(ObjectUtils.toString(target[49]),assetOwner.getLineCodeId());
							if(station!=null)
								assetOwner.setStationCodeId(station.getCodeId());
						}
					}
				}
				if(saveStatus){
					asset.setVerifyState("2");
					assetOwner.setCreateDate(asset.getCreateDate());
					assetOwner.setUpdateDate(asset.getUpdateDate());
					assetState.setCreateDate(asset.getCreateDate());
					assetState.setUpdateDate(asset.getUpdateDate());
					assetPrice.setCreateDate(asset.getCreateDate());
					assetPrice.setUpdateDate(asset.getUpdateDate());
					
					asset.setAssetOwnerInf(assetOwner);
					asset.setState(assetState);
					asset.setAssetPrice(assetPrice);
					
					
					assetOwnerDao.saveOrUpdate(assetOwner);
					assetStateDao.saveOrUpdate(assetState);
					assetPriceDao.saveOrUpdate(assetPrice);
					assetDao.saveOrUpdate(asset);
					
					assetRecord.setAsset(asset);
					assetRecordDao.saveOrUpdate(assetRecord);
				}else{
					errorInfo += "资产编号："+asset.getAssetCode()+";"+ currentError+"\r\n";
				}
			}
			if(errorCount>0){
				String dir = "c:"+File.separator+"AssetTransErrorLog"+File.separator;
				File fileDir = new File(dir);
				
				String filename = dir+File.separator+"AssetTransErrorLog"+sdf.format(new Date())+".txt";
				File errorFile = new File(filename);
				if(errorFile.getParentFile()!=null && !errorFile.getParentFile().exists()){
					errorFile.getParentFile().mkdirs();
				}
				
				
				FileOutputStream fos = null;
				try {
					fos = new FileOutputStream(errorFile);
					fos.write(errorInfo.getBytes("GBK"));
					fos.flush();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
					if(fos!=null)
						try {
							fos.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
				}
				
				
			}
			
		}
	}

    @Override
    public void transAssetObjectToAssetInfo() {
        String errorInfo="";
        boolean saveStatus = true;

//        assetDao.changeSessionLanguage();
        Date date = new Date();
        int totalRow= 69426;//347error
        int totalPageNo = totalRow%100 == 0 ? totalRow/100 :totalRow/100 +1;
        for(int x = 1;x<=totalPageNo;x++){

        List<Object[]> result = assetDao.findImportData(x,100);			//查询到所有的数据

        if(result!=null && result.size()>0) {

            int errorCount = 0;
            saveStatus = true;
            for (int i = 0, length = result.size(); i < length; i++) {
//			for(int i=0,length=result.size(); i<100; i++){				//测试用，先插入100个
                System.out.println("===== " + i + " of " + length);
                saveStatus = true;


                String currentError = "";
                Object[] target = result.get(i);

                Asset asset = null;
                AssetState assetState = null;
                AssetOwner assetOwner = null;
                AssetPrice assetPrice = null;
                AssetRecord assetRecord = new AssetRecord();
                if (StringUtils.isNotEmpty(ObjectUtils.toString(target[0]))) {
                    asset = assetDao.findByAssetCode(ObjectUtils.toString(target[0]));
                }
                if (asset == null) {
                    asset = new Asset();
                    assetState = new AssetState();
                    assetOwner = new AssetOwner();
                    assetPrice = new AssetPrice();
                } else {
                    assetState = asset.getState();
                    assetOwner = asset.getAssetOwnerInf();
                    assetPrice = asset.getAssetPrice();
                }

                if (StringUtils.isNotBlank(ObjectUtils.toString(target[0]))) {
                    assetDao.findByAssetCode(ObjectUtils.toString(target[0]));
                    asset.setAssetCode(ObjectUtils.toString(target[0]));        //资产编码
                    try {
                        Asset dataedAsset = assetDao.findByAssetCode(ObjectUtils.toString(target[0]));
                        if (dataedAsset != null) {
                            asset = dataedAsset;
                            assetOwner = dataedAsset.getAssetOwnerInf();
                            assetState = dataedAsset.getState();
                            assetPrice = dataedAsset.getAssetPrice();
                            if (assetOwner == null) assetOwner = new AssetOwner();
                            if (assetState == null) assetState = new AssetState();
                            if (assetPrice == null) assetPrice = new AssetPrice();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    saveStatus = false;
                    errorCount++;
                    currentError += "资产编码不能为空,";
                    continue;
                }

                if (StringUtils.isNotBlank(ObjectUtils.toString(target[1]))) {
                    asset.setName(ObjectUtils.toString(target[1]));                //资产名称
                } else {
                    saveStatus = false;
                    errorCount++;
                    currentError += "资产名称不能为空,";

                }

                String projectName = ObjectUtils.toString(target[2]);            //项目名称
                if (StringUtils.isNotBlank(projectName)) {
                    Project project;
                    try {
                        project = ServiceProvider.getService(ProjectService.class).findByShortName(projectName.trim());
                        if (project != null) {
                            assetRecord.setProjectName(projectName);
                            asset.setProjectNo(project.getProjectNo());
                            asset.setProject(project);
                        } else {
                            System.out.println("project==null," + i + "," + projectName.trim());
                        }

                    } catch (DocumentException e) {
                        e.printStackTrace();
                    }

                }

                String assetCode = ObjectUtils.toString(target[3]);            //资产编码
                if (StringUtils.isNotBlank(assetCode) && assetCode.length() == 6) {
                    String t1 = StringUtils.substring(assetCode, 0, 2);
                    String t2 = StringUtils.substring(assetCode, 2, 4);
                    String t3 = StringUtils.substring(assetCode, 4, 6);

                    AssetType type1;
                    try {
                        type1 = ServiceProvider.getService(AssetTypeService.class).findMainTypeByCodeWithPublish(t1);
                        if (type1 != null) {
                            asset.setMainTypeCodeId(type1.getCodeId());
                            AssetType type2 = ServiceProvider.getService(AssetTypeService.class).findByParentIdAndCode(type1.getId(), t2);
                            if (type2 != null) {
                                //asset.setSubType(type2);
                                asset.setSubTypeCodeId(type2.getCodeId());
                                AssetType type3 = ServiceProvider.getService(AssetTypeService.class).findByParentIdAndCode(type2.getId(), t3);
                                if (type3 != null) {
                                    //asset.setDetailType(type3);
                                    asset.setDetailTypeCodeId(type3.getCodeId());
                                } else {
                                    saveStatus = false;
                                    errorCount++;
                                    currentError += "资产小类格式为空,";
                                }
                            } else {
                                saveStatus = false;
                                errorCount++;
                                currentError += "资产中类格式出错,";
                            }
                        } else {
                            saveStatus = false;
                            errorCount++;
                            currentError += "资产大类格式出错,";
                        }
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    }
                }

                if (StringUtils.isNotBlank(ObjectUtils.toString(target[4]))) {
                    UnitMaster um = unitMasterDao.findByName(ObjectUtils.toString(target[4]));        //单位
                    if (um == null) {
                        um = new UnitMaster();
                        um.setName(ObjectUtils.toString(target[4]));
                        unitMasterDao.insert(um);
                    }
                    asset.setUnit(um);
                }

                if (StringUtils.isNotBlank(ObjectUtils.toString(target[5]))) {
                    asset.setCount(Double.valueOf(ObjectUtils.toString(target[5])));        //数量
                }

                asset.setType(ObjectUtils.toString(target[6]));                //规格型号
                asset.setManufactureCountry(ObjectUtils.toString(target[7]));    //产地

                if (StringUtils.isNotBlank(ObjectUtils.toString(target[8]))) {
                    List<Enterprise> enterprises = enterpriseDao.findByNameAndType(ObjectUtils.toString(target[8]), "2");        //生产商
                    if (enterprises != null && enterprises.size() == 1) {
                        asset.setManufacturer(enterprises.get(0));
                    }
                }

                if (StringUtils.isNotBlank(ObjectUtils.toString(target[9]))) {
                    List<Enterprise> enterprises = enterpriseDao.findByNameAndType(ObjectUtils.toString(target[9]), "1");        //供应商
                    if (enterprises != null && enterprises.size() == 1) {
                        asset.setManufacturer(enterprises.get(0));
                    }
                }

                if (StringUtils.isNotBlank(ObjectUtils.toString(target[10]))) {                                //出厂日期
                    try {
                        asset.setMadeDate(sdf.parse(ObjectUtils.toString(target[10])));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                if (StringUtils.isNotBlank(ObjectUtils.toString(target[11]))) {                                //供应日期
                    try {
                        asset.setUseDate(sdf.parse(ObjectUtils.toString(target[11])));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                if (StringUtils.isNotBlank(ObjectUtils.toString(target[12]))) {                                    //安装地点
                    asset.setDetailInstallSite(ObjectUtils.toString(target[12]));
                }

                if (StringUtils.isNotBlank(ObjectUtils.toString(target[13]))) {                                    //权属单位
                    Organization owner = organizationDao.findByCodeIdWithPublish(ObjectUtils.toString(target[13]));
                    if (owner != null)
                        assetOwner.setOwnerOrganizationCodeId(owner.getCodeId());
                }

                if (StringUtils.isNotBlank(ObjectUtils.toString(target[14]))) {                                    //使用单位
                    Organization user = organizationDao.findByCodeIdWithPublish(ObjectUtils.toString(target[14]));
                    if (user != null)
                        assetOwner.setUseOrganizationCodeId(user.getCodeId());
                }

                if (StringUtils.isNotBlank(ObjectUtils.toString(target[15]))) {                                    //维护部门
                    Department department = departmentDao.findByCodeIdWithPublish(ObjectUtils.toString(target[15]));
                    if (department != null)
                        assetOwner.setDepartmentCodeId(department.getCodeId());
                }

                if (StringUtils.isNotBlank(ObjectUtils.toString(target[16]))) {                                    //开始使用时间
                    try {
                        assetOwner.setBeginUseDate(DateUtils.parseDate(ObjectUtils.toString(target[16]), new String[]{"yyyy-MM-dd"}));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                if (StringUtils.isNotBlank(ObjectUtils.toString(target[17]))) {                                    //停止使用时间
                    try {
                        assetOwner.setStopUseDate(DateUtils.parseDate(ObjectUtils.toString(target[17]), new String[]{"yyyy-MM-dd"}));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                if (StringUtils.isNotBlank(ObjectUtils.toString(target[18]))) {                                    //批准报废时间
                    try {
                        asset.setApprovalScrapDate(DateUtils.parseDate(ObjectUtils.toString(target[18]), new String[]{"yyyy-MM-dd"}));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                if (StringUtils.isNotBlank(ObjectUtils.toString(target[19]))) {                                    //移交时间
                    try {
                        assetOwner.setReceiveDate(DateUtils.parseDate(ObjectUtils.toString(target[19]), new String[]{"yyyy-MM-dd"}));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                if (StringUtils.isNotBlank(ObjectUtils.toString(target[20]))) {                                    //使用状态,AssetState
                    //if("Use".equals(ObjectUtils.toString(target[20]))){
                    assetState.setUseState(ObjectUtils.toString(target[20]));
                    //}
                    if ("使用".equals(ObjectUtils.toString(target[20]))) {
                        assetState.setState("1");
                    } else if ("停用".equals(ObjectUtils.toString(target[20]))) {
                        assetState.setState("2");
                    } else if ("报废".equals(ObjectUtils.toString(target[20]))) {
                        assetState.setState("3");
                    } else if ("待报废".equals(ObjectUtils.toString(target[20]))) {
                        assetState.setState("4");
                    } else if ("封存".equals(ObjectUtils.toString(target[20]))) {
                        assetState.setState("5");
                    }
                }

                if (StringUtils.isNotBlank(ObjectUtils.toString(target[21]))) {
                    asset.setAssetPic(ObjectUtils.toString(target[21]));                                        //资产图片名称
                }

                if (StringUtils.isNotBlank(ObjectUtils.toString(target[22]))) {                                    //设计使用年限
                    asset.setUseLife(ObjectUtils.toString(target[22]));
                }
                if (StringUtils.isNotBlank(ObjectUtils.toString(target[23])) && NumberUtils.isNumber(ObjectUtils.toString(target[23]))) {                                    //预期使用寿命
                    asset.setExpectancyLife(Long.valueOf(ObjectUtils.toString(target[23])));
                }

                if (StringUtils.isNotBlank(ObjectUtils.toString(target[24]))) {                                    //保修期至
                    try {
                        asset.setWarrantyPeriod(DateUtils.parseDate(ObjectUtils.toString(target[24]), new String[]{"yyyy-MM-dd"}));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                if (StringUtils.isNotBlank(ObjectUtils.toString(target[25]))) {                                    //大修频次
                    asset.setOverhaulRate(ObjectUtils.toString(target[25]));
                }

                if (StringUtils.isNotBlank(ObjectUtils.toString(target[26])) && NumberUtils.isNumber(ObjectUtils.toString(target[26]))) {                                    //出厂价
                    asset.setFactoryPrice(Double.valueOf(ObjectUtils.toString(target[26])));
                    assetPrice.setFactoryPrice(Double.valueOf(ObjectUtils.toString(target[26])));
                }

                if (StringUtils.isNotBlank(ObjectUtils.toString(target[27])) && NumberUtils.isNumber(ObjectUtils.toString(target[27]))) {                                    //合同价
                    asset.setContractPrice(Double.valueOf(ObjectUtils.toString(target[27])));
                    assetPrice.setContractPrice(Double.valueOf(ObjectUtils.toString(target[27])));
                }

                if (StringUtils.isNotBlank(ObjectUtils.toString(target[28])) && NumberUtils.isNumber(ObjectUtils.toString(target[28]))) {                                    //原值
                    asset.setOriginalValue(Double.valueOf(ObjectUtils.toString(target[28])));
                    assetPrice.setOriginalValue(Double.valueOf(ObjectUtils.toString(target[28])));
                }

                if (StringUtils.isNotBlank(ObjectUtils.toString(target[29]))) {                                        //资料清单
                    asset.setDataList(ObjectUtils.toString(target[29]));
                }

                if (StringUtils.isNotBlank(ObjectUtils.toString(target[30]))) {                                        //折旧方法，AssetPrice
                    //if(ObjectUtils.toString(target[30]).contains("LineMethod")){
                    assetPrice.setDepreciationMethod(ObjectUtils.toString(target[30]));
                    //}
                }

                if (StringUtils.isNotBlank(ObjectUtils.toString(target[31])) && NumberUtils.isNumber(ObjectUtils.toString(target[31]))) {                //累计折旧,AssetPrice
                    assetPrice.setAccumulatedDepreciation(Double.valueOf(ObjectUtils.toString(target[31])));
                }

                if (StringUtils.isNotBlank(ObjectUtils.toString(target[32])) && NumberUtils.isNumber(ObjectUtils.toString(target[32]))) {                //净值
                    assetPrice.setNetValue(Double.valueOf(ObjectUtils.toString(target[32])));
                }

                if (StringUtils.isNotBlank(ObjectUtils.toString(target[33]))) {                    //铭牌张贴位置,AssetState
                    //if("UpperLeft".equals(ObjectUtils.toString(target[33]))){
                    assetState.setNameplateSite(ObjectUtils.toString(target[33]));
                    //}
                }

                if (StringUtils.isNotBlank(ObjectUtils.toString(target[34]))) {                    //设备清单
                    asset.setEquipmentList(ObjectUtils.toString(target[34]));
                }

                if (StringUtils.isNotBlank(ObjectUtils.toString(target[35]))) {                    //竣工移交资产类型
                    //if("Initial".equals(ObjectUtils.toString(target[35]))){
                    asset.setCompleteTransAssetType(ObjectUtils.toString(target[35]));
                    //}
                }

                if (StringUtils.isNotBlank(ObjectUtils.toString(target[36]))) {                    //项目资产标示
                    asset.setOperateProjectAsset(ObjectUtils.toString(target[36]));
                    assetRecord.setAssetType(ObjectUtils.toString(target[36]));        //资产类型
                }

                if (StringUtils.isNotBlank(ObjectUtils.toString(target[37]))) {                    //资产交付日期
                    try {
                        asset.setOperateProjectAssetDate(DateUtils.parseDate(ObjectUtils.toString(target[38]), new String[]{"yyyy-MM-dd"}));
                        assetRecord.setFinishDate(asset.getOperateProjectAssetDate());

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                if (StringUtils.isNotBlank(ObjectUtils.toString(target[38]))) {                    //立项或可研批复文号
                    asset.setProjectAppDocNo(ObjectUtils.toString(target[38]));
                    assetRecord.setProjectAppNo(ObjectUtils.toString(target[38]));
                }

                if (StringUtils.isNotBlank(ObjectUtils.toString(target[39]))) {                            //备注
                    asset.setRemarks(ObjectUtils.toString(target[39]) + "\r\n TransAssetObject");
                } else {
                    asset.setRemarks("TransAssetObject");
                }

                if (StringUtils.isNotBlank(ObjectUtils.toString(target[40]))) {                    //创建时间
                    try {
                        asset.setCreateDate(DateUtils.parseDate(ObjectUtils.toString(target[40]), new String[]{"yyyy-MM-dd"}));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                if (StringUtils.isNotBlank(ObjectUtils.toString(target[41]))) {                    //修改时间
                    try {
                        asset.setUpdateDate(DateUtils.parseDate(ObjectUtils.toString(target[41]), new String[]{"yyyy-MM-dd"}));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                if (StringUtils.isNotBlank(ObjectUtils.toString(target[42]))) {                //项目合同编码
                    asset.setProjectContractNo(ObjectUtils.toString(target[42]));
                }

                if (StringUtils.isNotBlank(ObjectUtils.toString(target[43]))) {            // 权属负责人
                    asset.setOwnershipPer(ObjectUtils.toString(target[43]));
                }

                if (StringUtils.isNotBlank(ObjectUtils.toString(target[44]))) {            // 使用负责人
                    asset.setUsePer(ObjectUtils.toString(target[44]));
                }

                if (StringUtils.isNotBlank(ObjectUtils.toString(target[45]))) {            // 资产位置
                    asset.setLocationCode(ObjectUtils.toString(target[45]));
                }

                if (StringUtils.isNotBlank(ObjectUtils.toString(target[46])) && NumberUtils.isNumber(ObjectUtils.toString(target[46]))) {            // 资产决算价
                    asset.setOperateProjectAssetAccount(Double.valueOf(ObjectUtils.toString(target[46])));
                    assetPrice.setFinalPrice(Double.valueOf(ObjectUtils.toString(target[46])));
                    assetRecord.setMaintainCost(ObjectUtils.toString(target[46]));
                    assetRecord.setFinishPrice(ObjectUtils.toString(target[46]));
                }
                if (StringUtils.isNotBlank(ObjectUtils.toString(target[47]))) {
                    Line line = lineDao.findByCodeIdWithPublish(ObjectUtils.toString(target[47]));
                    if (line != null) {
                        assetOwner.setLineCodeId(line.getCodeId());
                        if (StringUtils.isNotBlank(ObjectUtils.toString(target[49]))) {
                            Station station = stationDao.findByCodeAndLineCodeIdWithPublish(ObjectUtils.toString(target[49]), assetOwner.getLineCodeId());
                            if (station != null)
                                assetOwner.setStationCodeId(station.getCodeId());
                        }
                    }
                }
                if (saveStatus) {
                    asset.setVerifyState("2");
                    assetOwner.setCreateDate(asset.getCreateDate());
                    assetOwner.setUpdateDate(asset.getUpdateDate());
                    assetState.setCreateDate(asset.getCreateDate());
                    assetState.setUpdateDate(asset.getUpdateDate());
                    assetPrice.setCreateDate(asset.getCreateDate());
                    assetPrice.setUpdateDate(asset.getUpdateDate());

                    asset.setAssetOwnerInf(assetOwner);
                    asset.setState(assetState);
                    asset.setAssetPrice(assetPrice);


                    assetOwnerDao.saveOrUpdate(assetOwner);
                    assetStateDao.saveOrUpdate(assetState);
                    assetPriceDao.saveOrUpdate(assetPrice);
                    assetDao.saveOrUpdate(asset);

                    assetRecord.setAsset(asset);
                    assetRecordDao.saveOrUpdate(assetRecord);
                } else {
                    errorInfo += "资产编号：" + asset.getAssetCode() + ";" + currentError + "\r\n";
                }
            }
            if (errorCount > 0) {
                String dir = "c:" + File.separator + "AssetTransErrorLog" + File.separator;
                File fileDir = new File(dir);

                String filename = dir + File.separator + "AssetTransErrorLog" + sdf.format(new Date()) + ".txt";
                File errorFile = new File(filename);
                if (errorFile.getParentFile() != null && !errorFile.getParentFile().exists()) {
                    errorFile.getParentFile().mkdirs();
                }


                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(errorFile);
                    fos.write(errorInfo.getBytes("GBK"));
                    fos.flush();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fos != null)
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                }


            }
        }
        }
    }


    @Override
	public void transAssetObjectToAssetInfoWithAssetNo() {
		/* SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    Calendar calendar = Calendar.getInstance();
	    calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR)-1);
	    calendar.set(Calendar.MONTH, 10);
	    calendar.set(Calendar.DAY_OF_MONTH,28);
	    lastExecuteDate = calendar.getTime();
	    System.out.println(sdf.format(lastExecuteDate));*/
	    String errorInfo="";
	    boolean saveStatus = true;
	    
	    assetDao.changeSessionLanguage();
	    List<Object[]> result  = assetDao.findBeforeLastExecuteDateWithAssetNo();
	    System.out.println("total =====" + result.size());

		if(result!=null && result.size()>0){
			int errorCount = 0;
			saveStatus = true;
			for(int i=0,length=result.size(); i<length; i++){
				saveStatus = true;
				String currentError = "";
				Object[] target = result.get(i);
				Asset asset = new Asset();
				
				AssetState assetState = new AssetState();
				AssetOwner assetOwner = new AssetOwner();
				AssetPrice assetPrice = new AssetPrice();
				
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[0]))){
					assetDao.findByAssetCode(ObjectUtils.toString(target[0]));
					asset.setAssetCode(ObjectUtils.toString(target[0]));		//资产编码
					try {
						Asset dataedAsset = assetDao.findByAssetCode(ObjectUtils.toString(target[0]));
						if(dataedAsset!=null){
							asset = dataedAsset;
							assetOwner = dataedAsset.getAssetOwnerInf();
							assetState = dataedAsset.getState();
							assetPrice = dataedAsset.getAssetPrice();
							if(assetOwner==null) assetOwner = new AssetOwner();
							if(assetState==null) assetState = new AssetState();
							if(assetPrice==null) assetPrice = new AssetPrice();
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					saveStatus = false;
					errorCount++;
					currentError += "资产编码不能为空,";
					continue;
				}
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[1]))){
					asset.setName(ObjectUtils.toString(target[1]));				//资产名称
				}else{
					saveStatus = false;
					errorCount++;
					currentError += "资产名称不能为空,";
					
				}
				String projectNo = ObjectUtils.toString(target[2]);			//项目名称
				if(StringUtils.isNotBlank(projectNo)){
					Project project;
					try {
						project = ServiceProvider.getService(ProjectService.class).findByShortName(projectNo.trim());
						if(project!=null){
							asset.setProjectNo(project.getProjectNo());
							asset.setProject(project);
						}else{
							System.out.println("project==null,"+i+","+projectNo.trim());
						}
							
					} catch (DocumentException e) {
						e.printStackTrace();
					}
					
				}
				String assetCode = ObjectUtils.toString(target[3]);			//资产编码
				if(StringUtils.isNotBlank(assetCode) && assetCode.length()==6){
					String t1 = StringUtils.substring(assetCode, 0, 2);
					String t2 = StringUtils.substring(assetCode, 2, 4);
					String t3 = StringUtils.substring(assetCode, 4, 6);
					
					AssetType type1;
					try {
						type1 = ServiceProvider.getService(AssetTypeService.class).findMainTypeByCodeWithPublish(t1);
						if(type1!=null){
							asset.setMainTypeCodeId(type1.getCodeId());
							AssetType type2 = ServiceProvider.getService(AssetTypeService.class).findByParentIdAndCode(type1.getId(), t2);
							if(type2!=null){
								//asset.setSubType(type2);
								asset.setSubTypeCodeId(type2.getCodeId());
								AssetType type3 = ServiceProvider.getService(AssetTypeService.class).findByParentIdAndCode(type2.getId(), t3);
								if(type3!=null){
									//asset.setDetailType(type3);
									asset.setDetailTypeCodeId(type3.getCodeId());
								}else{
									saveStatus = false;
									errorCount++;
									currentError += "资产小类格式为空,";
								}
							}else{
								saveStatus = false;
								errorCount++;
								currentError += "资产中类格式出错,";
							}
						}else{
							saveStatus = false;
							errorCount++;
							currentError += "资产大类格式出错,";
						}
					} catch (DocumentException e) {
						e.printStackTrace();
					}
				}
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[4]))){
					UnitMaster um = unitMasterDao.findByName(ObjectUtils.toString(target[4]));		//单位
					if(um==null) {
						um = new UnitMaster();
						um.setName(ObjectUtils.toString(target[4]));
						unitMasterDao.insert(um);
					}
					asset.setUnit(um);
				}
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[5]))){
					asset.setCount(Double.valueOf(ObjectUtils.toString(target[5])));		//数量
				}
				asset.setType(ObjectUtils.toString(target[6]));				//规格型号
				asset.setManufactureCountry(ObjectUtils.toString(target[7]));	//产地				
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[8]))){
					List<Enterprise> enterprises = enterpriseDao.findByNameAndType(ObjectUtils.toString(target[8]), "2");		//生产商
					if(enterprises!=null && enterprises.size()==1){
						asset.setManufacturer(enterprises.get(0));
					}
				}
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[9]))){
					List<Enterprise> enterprises = enterpriseDao.findByNameAndType(ObjectUtils.toString(target[9]), "1");		//供应商
					if(enterprises!=null && enterprises.size()==1){
						asset.setManufacturer(enterprises.get(0));
					}
				}
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[10]))){								//出厂日期
					try {
						asset.setMadeDate(sdf.parse(ObjectUtils.toString(target[10])));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[11]))){								//供应日期
					try {
						asset.setUseDate(sdf.parse(ObjectUtils.toString(target[11])));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[12]))){									//安装地点
					asset.setDetailInstallSite(ObjectUtils.toString(target[12]));
				}
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[13]))){									//权属单位
					Organization owner = organizationDao.findByCodeIdWithPublish(ObjectUtils.toString(target[13]));
					if(owner!=null)
						assetOwner.setOwnerOrganizationCodeId(owner.getCodeId());
				}
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[14]))){									//使用单位
					Organization user = organizationDao.findByCodeIdWithPublish(ObjectUtils.toString(target[14]));
					if(user!=null)
						assetOwner.setUseOrganizationCodeId(user.getCodeId());
				}
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[15]))){									//维护部门
					Department department = departmentDao.findByCodeIdWithPublish(ObjectUtils.toString(target[15]));
					if(department!=null)
						assetOwner.setDepartmentCodeId(department.getCodeId());
				}
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[16]))){									//开始使用时间
					try {
						assetOwner.setBeginUseDate(DateUtils.parseDate(ObjectUtils.toString(target[16]), new String[]{"yyyy-MM-dd"}));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[17]))){									//停止使用时间
					try {
						assetOwner.setStopUseDate(DateUtils.parseDate(ObjectUtils.toString(target[17]), new String[]{"yyyy-MM-dd"}));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[18]))){									//批准报废时间
					try {
						asset.setApprovalScrapDate(DateUtils.parseDate(ObjectUtils.toString(target[18]), new String[]{"yyyy-MM-dd"}));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[19]))){									//移交时间
					try {
						assetOwner.setReceiveDate(DateUtils.parseDate(ObjectUtils.toString(target[19]), new String[]{"yyyy-MM-dd"}));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[20]))){									//使用状态,AssetState
					//if("Use".equals(ObjectUtils.toString(target[20]))){
						assetState.setUseState(ObjectUtils.toString(target[20]));
					//}
					if("使用".equals(ObjectUtils.toString(target[20]))){
						assetState.setState("1");
					}else if("停用".equals(ObjectUtils.toString(target[20]))){
						assetState.setState("2");
					}else if("报废".equals(ObjectUtils.toString(target[20]))){
						assetState.setState("3");
					}else if("待报废".equals(ObjectUtils.toString(target[20]))){
						assetState.setState("4");
					}else if("封存".equals(ObjectUtils.toString(target[20]))){
						assetState.setState("5");
					}
				}
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[21]))){
					asset.setAssetPic(ObjectUtils.toString(target[21]));										//资产图片名称
				}
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[22]))){									//设计使用年限
					asset.setUseLife(ObjectUtils.toString(target[22]));
				}
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[23])) && NumberUtils.isNumber(ObjectUtils.toString(target[23]))){									//预期使用寿命
					asset.setExpectancyLife(Long.valueOf(ObjectUtils.toString(target[23])));
				}
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[24]))){									//保修期至
					try {
						asset.setWarrantyPeriod(DateUtils.parseDate(ObjectUtils.toString(target[24]), new String[]{"yyyy-MM-dd"}));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[25])) ){									//大修频次
					asset.setOverhaulRate(ObjectUtils.toString(target[25]));
				}
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[26])) && NumberUtils.isNumber(ObjectUtils.toString(target[26]))){									//出厂价
					asset.setFactoryPrice(Double.valueOf(ObjectUtils.toString(target[26])));
					assetPrice.setFactoryPrice(Double.valueOf(ObjectUtils.toString(target[26])));
				}
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[27])) && NumberUtils.isNumber(ObjectUtils.toString(target[27]))){									//合同价
					asset.setContractPrice(Double.valueOf(ObjectUtils.toString(target[27])));
					assetPrice.setContractPrice(Double.valueOf(ObjectUtils.toString(target[27])));
				}
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[28])) && NumberUtils.isNumber(ObjectUtils.toString(target[28]))){									//原值
					asset.setOriginalValue(Double.valueOf(ObjectUtils.toString(target[28])));
					assetPrice.setOriginalValue(Double.valueOf(ObjectUtils.toString(target[28])));
				}
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[29]))){										//资料清单
					asset.setDataList(ObjectUtils.toString(target[29]));
				}
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[30]))){										//折旧方法，AssetPrice
					//if(ObjectUtils.toString(target[30]).contains("LineMethod")){
						assetPrice.setDepreciationMethod(ObjectUtils.toString(target[30]));
					//}
				}
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[31])) && NumberUtils.isNumber(ObjectUtils.toString(target[31]))){				//累计折旧,AssetPrice
					assetPrice.setAccumulatedDepreciation(Double.valueOf(ObjectUtils.toString(target[31])));
				}
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[32])) && NumberUtils.isNumber(ObjectUtils.toString(target[32]))){				//净值
					assetPrice.setNetValue(Double.valueOf(ObjectUtils.toString(target[32])));
				}
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[33]))){					//铭牌张贴位置,AssetState
					//if("UpperLeft".equals(ObjectUtils.toString(target[33]))){
						assetState.setNameplateSite(ObjectUtils.toString(target[33]));
					//}
				}	
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[34]))){					//设备清单
					asset.setEquipmentList(ObjectUtils.toString(target[34]));
				}
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[35]))){					//竣工移交资产类型
					//if("Initial".equals(ObjectUtils.toString(target[35]))){
						asset.setCompleteTransAssetType(ObjectUtils.toString(target[35]));
					//}
				}
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[36]))){					//项目资产标示
					asset.setOperateProjectAsset(ObjectUtils.toString(target[36]));
				}
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[37]))){					//资产交付日期
					try {
						asset.setOperateProjectAssetDate(DateUtils.parseDate(ObjectUtils.toString(target[38]), new String[]{"yyyy-MM-dd"}));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[38]))){					//立项或可研批复文号		
					asset.setProjectAppDocNo(ObjectUtils.toString(target[38]));
				}
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[39]))){							//备注
					asset.setRemarks(ObjectUtils.toString(target[39])+"\r\n TransAssetObject");
				}else{
					asset.setRemarks("TransAssetObject");
				}
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[40]))){					//创建时间
					try {
						asset.setCreateDate(DateUtils.parseDate(ObjectUtils.toString(target[40]), new String[]{"yyyy-MM-dd"}));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[41]))){					//修改时间
					try {
						asset.setUpdateDate(DateUtils.parseDate(ObjectUtils.toString(target[41]), new String[]{"yyyy-MM-dd"}));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[42]))){				//项目合同编码
					asset.setProjectContractNo(ObjectUtils.toString(target[42]));
				}
				if (StringUtils.isNotBlank(ObjectUtils.toString(target[43]))) { 			// 权属负责人
					asset.setOwnershipPer(ObjectUtils.toString(target[43]));
				}
				if (StringUtils.isNotBlank(ObjectUtils.toString(target[44]))) { 			// 使用负责人
					asset.setUsePer(ObjectUtils.toString(target[44]));
				}
				if (StringUtils.isNotBlank(ObjectUtils.toString(target[45]))) { 			// 资产位置
					asset.setLocationCode(ObjectUtils.toString(target[45]));
				}
				if (StringUtils.isNotBlank(ObjectUtils.toString(target[46])) && NumberUtils.isNumber(ObjectUtils.toString(target[46]))) { 			// 资产决算价
					asset.setOperateProjectAssetAccount(Double.valueOf(ObjectUtils.toString(target[44])));
					assetPrice.setFinalPrice(Double.valueOf(ObjectUtils.toString(target[44])));
				}
				if(StringUtils.isNotBlank(ObjectUtils.toString(target[47]))){
					Line line = lineDao.findByCodeIdWithPublish(ObjectUtils.toString(target[47]));
					if(line!=null){
						assetOwner.setLineCodeId(line.getCodeId());
						if(StringUtils.isNotBlank(ObjectUtils.toString(target[49]))){
							Station station = stationDao.findByCodeAndLineCodeIdWithPublish(ObjectUtils.toString(target[49]),assetOwner.getLineCodeId());
							if(station!=null)
								assetOwner.setStationCodeId(station.getCodeId());
						}
					}
				}
				if(saveStatus){
					asset.setVerifyState("2");
					assetOwner.setCreateDate(asset.getCreateDate());
					assetOwner.setUpdateDate(asset.getUpdateDate());
					assetState.setCreateDate(asset.getCreateDate());
					assetState.setUpdateDate(asset.getUpdateDate());
					assetPrice.setCreateDate(asset.getCreateDate());
					assetPrice.setUpdateDate(asset.getUpdateDate());
					
					asset.setAssetOwnerInf(assetOwner);
					asset.setState(assetState);
					asset.setAssetPrice(assetPrice);
					
					assetOwnerDao.saveOrUpdate(assetOwner);
					assetStateDao.insert(assetState);
					assetPriceDao.insert(assetPrice);
					
					assetDao.saveOrUpdate(asset);
				}else{
					errorInfo += "资产编号："+asset.getAssetCode()+";"+ currentError+"\r\n";
				}
			}
			if(errorCount>0){
				String dir = "c:"+File.separator+"AssetTransErrorLog"+File.separator;
				File fileDir = new File(dir);
				
				String filename = dir+File.separator+"AssetTransErrorLog"+sdf.format(new Date())+".txt";
				File errorFile = new File(filename);
				if(errorFile.getParentFile()!=null && !errorFile.getParentFile().exists()){
					errorFile.getParentFile().mkdirs();
				}
				
				
				FileOutputStream fos = null;
				try {
					fos = new FileOutputStream(errorFile);
					fos.write(errorInfo.getBytes("GBK"));
					fos.flush();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
					if(fos!=null)
						try {
							fos.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
				}
				
				
			}
			
		}
	}

    @Override
    public Pagination findAssetInfo(AssetInfo assetInfo, int pageNo, int pageSize){
        System.out.println("=================service===================");
              return assetDao.findSomeAssetInfo(assetInfo, pageNo, pageSize);
    }
    
    @Override
	public AssetRecord findAssetRecordById(String id) {
		return assetRecordDao.findAssetRecordById(id);
	}

	@Override
	public void saveOrUpdate(AssetRecord assetRecord) {
		assetRecordDao.saveOrUpdate(assetRecord);
	}

	@Override
	public Pagination findAssetRecordByProjectAppNo(String projectAppNo,
			Integer pageSize, Integer currentPageNo) {
		return assetRecordDao.findAssetRecordByProjectAppNo(projectAppNo, pageSize, currentPageNo);
	}

	@Override
	public Pagination<AssetRecord> findAssetRecordByAssetId(String assetId, int startIndex, int pageSize) {
		Map<String,String> filter = new HashMap<String, String>();
		filter.put("asset.id_equal", assetId);
		return assetRecordDao.findBy(filter, null, startIndex, pageSize);
	}
}

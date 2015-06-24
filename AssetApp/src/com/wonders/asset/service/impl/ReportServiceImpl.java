package com.wonders.asset.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

import org.apache.ibatis.session.SqlSession;

import com.wonders.asset.dao.AssetDao;
import com.wonders.asset.dao.AssetTypeDao;
import com.wonders.asset.dao.DamageAssetDao;
import com.wonders.asset.dao.DwAssetEntityStatDao;
import com.wonders.asset.dao.DwAssetImportantRatioDao;
import com.wonders.asset.dao.DwAssetLineValueDao;
import com.wonders.asset.dao.DwAssetOwnerOrganizationDao;
import com.wonders.asset.dao.DwAssetProjectLineValueDao;
import com.wonders.asset.dao.DwAssetStateLineDao;
import com.wonders.asset.dao.DwAssetTypeStateDao;
import com.wonders.asset.dao.DwAssetUseOrganizationDao;
import com.wonders.asset.dao.DwCheckAssetAccuracyYearDao;
import com.wonders.asset.dao.DwHomePageStatDao;
import com.wonders.asset.dao.DwImportantAssetRankDao;
import com.wonders.asset.dao.DwMaterialsConsumeDao;
import com.wonders.asset.dao.DwOverhaulBudgetYearDao;
import com.wonders.asset.dao.DwOverhaulLineDao;
import com.wonders.asset.dao.DwOverhaulMajorTypeDao;
import com.wonders.asset.dao.DwOverhaulProjectStatDao;
import com.wonders.asset.dao.DwProjectCompanyPriceDao;
import com.wonders.asset.dao.DwProjectPriceByYearDao;
import com.wonders.asset.dao.DwScrapAssetUseLifeDao;
import com.wonders.asset.dao.DwScrapStateAssetValueDao;
import com.wonders.asset.dao.DwStopStateAssetValueDao;
import com.wonders.asset.dao.ProjectDao;
import com.wonders.asset.model.Asset;
import com.wonders.asset.model.bo.CountPrice;
import com.wonders.asset.model.bo.MoneySource;
import com.wonders.asset.model.dw.DwAssetEntityStat;
import com.wonders.asset.model.dw.DwAssetImportantRatio;
import com.wonders.asset.model.dw.DwAssetLineValue;
import com.wonders.asset.model.dw.DwAssetOwnerOrganizationUnit;
import com.wonders.asset.model.dw.DwAssetProjectLineValue;
import com.wonders.asset.model.dw.DwAssetStateLine;
import com.wonders.asset.model.dw.DwAssetTypeState;
import com.wonders.asset.model.dw.DwAssetUseOrganizationUnit;
import com.wonders.asset.model.dw.DwCheckAssetAccuracyYear;
import com.wonders.asset.model.dw.DwHomePageStat;
import com.wonders.asset.model.dw.DwImportantAssetLine;
import com.wonders.asset.model.dw.DwImportantAssetRank;
import com.wonders.asset.model.dw.DwMaterialsConsume;
import com.wonders.asset.model.dw.DwOverhaulBudgetYear;
import com.wonders.asset.model.dw.DwOverhaulLine;
import com.wonders.asset.model.dw.DwOverhaulMajorType;
import com.wonders.asset.model.dw.DwOverhaulProjectStat;
import com.wonders.asset.model.dw.DwProjectCompanyPrice;
import com.wonders.asset.model.dw.DwProjectPriceByYear;
import com.wonders.asset.model.dw.DwScrapAssetUseLife;
import com.wonders.asset.model.dw.DwScrapStateAssetValue;
import com.wonders.asset.model.dw.DwStopStateAssetValue;
import com.wonders.asset.service.ReportService;
import com.wonders.framework.util.MyBatisUtils;

public class ReportServiceImpl implements ReportService {

	private AssetTypeDao assetTypeDao;
	private AssetDao assetDao;
	private ProjectDao projectDao;
	
	private DwAssetEntityStatDao dwAssetEntityStatDao;
	private DwAssetLineValueDao dwAssetLineValueDao;
	private DwAssetUseOrganizationDao dwAssetUseOrganizationDao;
	private DwAssetOwnerOrganizationDao dwAssetOwnerOrganizationDao;
	private DwAssetProjectLineValueDao dwAssetProjectLineValueDao;
	private DwHomePageStatDao dwHomePageStatDao;
	private DwProjectCompanyPriceDao dwProjectCompanyPriceDao;
	private DwProjectPriceByYearDao dwProjectPriceByYearDao;
	private DwAssetStateLineDao dwAssetStateLineDao;
	private DwStopStateAssetValueDao dwStopStateAssetValueDao;
	private DwScrapStateAssetValueDao dwScrapStateAssetValueDao;
	private DwOverhaulProjectStatDao dwOverhaulProjectStatDao;
	private DwOverhaulBudgetYearDao dwOverhaulBudgetYearDao;
	private DwScrapAssetUseLifeDao dwScrapAssetUseLifeDao;
	private DwCheckAssetAccuracyYearDao dwCheckAssetAccuracyYearDao;
	private DwMaterialsConsumeDao dwMaterialsConsumeDao;
	private DwOverhaulMajorTypeDao dwOverhaulMajorTypeDao;
	private DwImportantAssetRankDao dwImportantAssetRankDao;
	private DwAssetTypeStateDao dwAssetTypeStateDao;
	private DwAssetImportantRatioDao dwAssetImportantRatioDao;
	private DwOverhaulLineDao dwOverhaulLineDao;
	
	public ProjectDao getProjectDao() {
		return projectDao;
	}

	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	public DwAssetImportantRatioDao getDwAssetImportantRatioDao() {
		return dwAssetImportantRatioDao;
	}

	public void setDwAssetImportantRatioDao(
			DwAssetImportantRatioDao dwAssetImportantRatioDao) {
		this.dwAssetImportantRatioDao = dwAssetImportantRatioDao;
	}

	public DwAssetTypeStateDao getDwAssetTypeStateDao() {
		return dwAssetTypeStateDao;
	}

	public void setDwAssetTypeStateDao(DwAssetTypeStateDao dwAssetTypeStateDao) {
		this.dwAssetTypeStateDao = dwAssetTypeStateDao;
	}

	public DwImportantAssetRankDao getDwImportantAssetRankDao() {
		return dwImportantAssetRankDao;
	}

	public void setDwImportantAssetRankDao(
			DwImportantAssetRankDao dwImportantAssetRankDao) {
		this.dwImportantAssetRankDao = dwImportantAssetRankDao;
	}

	public DwMaterialsConsumeDao getDwMaterialsConsumeDao() {
		return dwMaterialsConsumeDao;
	}

	public void setDwMaterialsConsumeDao(DwMaterialsConsumeDao dwMaterialsConsumeDao) {
		this.dwMaterialsConsumeDao = dwMaterialsConsumeDao;
	}
	
	public DwScrapAssetUseLifeDao getDwScrapAssetUseLifeDao() {
		return dwScrapAssetUseLifeDao;
	}

	public void setDwScrapAssetUseLifeDao(
			DwScrapAssetUseLifeDao dwScrapAssetUseLifeDao) {
		this.dwScrapAssetUseLifeDao = dwScrapAssetUseLifeDao;
	}
	
	public DwCheckAssetAccuracyYearDao getDwCheckAssetAccuracyYearDao() {
		return dwCheckAssetAccuracyYearDao;
	}

	public void setDwCheckAssetAccuracyYearDao(
			DwCheckAssetAccuracyYearDao dwCheckAssetAccuracyYearDao) {
		this.dwCheckAssetAccuracyYearDao = dwCheckAssetAccuracyYearDao;
	}

	public DwOverhaulBudgetYearDao getDwOverhaulBudgetYearDao() {
		return dwOverhaulBudgetYearDao;
	}

	public void setDwOverhaulBudgetYearDao(
			DwOverhaulBudgetYearDao dwOverhaulBudgetYearDao) {
		this.dwOverhaulBudgetYearDao = dwOverhaulBudgetYearDao;
	}

	public DwOverhaulMajorTypeDao getDwOverhaulMajorTypeDao() {
		return dwOverhaulMajorTypeDao;
	}

	public void setDwOverhaulMajorTypeDao(
			DwOverhaulMajorTypeDao dwOverhaulMajorTypeDao) {
		this.dwOverhaulMajorTypeDao = dwOverhaulMajorTypeDao;
	}

	public DwOverhaulProjectStatDao getDwOverhaulProjectStatDao() {
		return dwOverhaulProjectStatDao;
	}

	public void setDwOverhaulProjectStatDao(
			DwOverhaulProjectStatDao dwOverhaulProjectStatDao) {
		this.dwOverhaulProjectStatDao = dwOverhaulProjectStatDao;
	}

	public DwScrapStateAssetValueDao getDwScrapStateAssetValueDao() {
		return dwScrapStateAssetValueDao;
	}

	public void setDwScrapStateAssetValueDao(
			DwScrapStateAssetValueDao dwScrapStateAssetValueDao) {
		this.dwScrapStateAssetValueDao = dwScrapStateAssetValueDao;
	}

	public DwStopStateAssetValueDao getDwStopStateAssetValueDao() {
		return dwStopStateAssetValueDao;
	}

	public void setDwStopStateAssetValueDao(
			DwStopStateAssetValueDao dwStopStateAssetValueDao) {
		this.dwStopStateAssetValueDao = dwStopStateAssetValueDao;
	}

	public DwAssetStateLineDao getDwAssetStateLineDao() {
		return dwAssetStateLineDao;
	}

	public void setDwAssetStateLineDao(DwAssetStateLineDao dwAssetStateLineDao) {
		this.dwAssetStateLineDao = dwAssetStateLineDao;
	}

	public DwProjectPriceByYearDao getDwProjectPriceByYearDao() {
		return dwProjectPriceByYearDao;
	}

	public void setDwProjectPriceByYearDao(
			DwProjectPriceByYearDao dwProjectPriceByYearDao) {
		this.dwProjectPriceByYearDao = dwProjectPriceByYearDao;
	}

	public DwProjectCompanyPriceDao getDwProjectCompanyPriceDao() {
		return dwProjectCompanyPriceDao;
	}

	public void setDwProjectCompanyPriceDao(
			DwProjectCompanyPriceDao dwProjectCompanyPriceDao) {
		this.dwProjectCompanyPriceDao = dwProjectCompanyPriceDao;
	}

	public DwHomePageStatDao getDwHomePageStatDao() {
		return dwHomePageStatDao;
	}

	public void setDwHomePageStatDao(DwHomePageStatDao dwHomePageStatDao) {
		this.dwHomePageStatDao = dwHomePageStatDao;
	}

	public DwAssetOwnerOrganizationDao getDwAssetOwnerOrganizationDao() {
		return dwAssetOwnerOrganizationDao;
	}

	public void setDwAssetOwnerOrganizationDao(
			DwAssetOwnerOrganizationDao dwAssetOwnerOrganizationDao) {
		this.dwAssetOwnerOrganizationDao = dwAssetOwnerOrganizationDao;
	}

	public DwAssetProjectLineValueDao getDwAssetProjectLineValueDao() {
		return dwAssetProjectLineValueDao;
	}

	public void setDwAssetProjectLineValueDao(
			DwAssetProjectLineValueDao dwAssetProjectLineValueDao) {
		this.dwAssetProjectLineValueDao = dwAssetProjectLineValueDao;
	}

	public DwAssetUseOrganizationDao getDwAssetUseOrganizationDao() {
		return dwAssetUseOrganizationDao;
	}

	public void setDwAssetUseOrganizationDao(
			DwAssetUseOrganizationDao dwAssetUseOrganizationDao) {
		this.dwAssetUseOrganizationDao = dwAssetUseOrganizationDao;
	}

	public DwAssetLineValueDao getDwAssetLineValueDao() {
		return dwAssetLineValueDao;
	}

	public void setDwAssetLineValueDao(DwAssetLineValueDao dwAssetLineValueDao) {
		this.dwAssetLineValueDao = dwAssetLineValueDao;
	}

	public DwAssetEntityStatDao getDwAssetEntityStatDao() {
		return dwAssetEntityStatDao;
	}

	public void setDwAssetEntityStatDao(DwAssetEntityStatDao dwAssetEntityStatDao) {
		this.dwAssetEntityStatDao = dwAssetEntityStatDao;
	}

	public AssetTypeDao getAssetTypeDao() {
		return assetTypeDao;
	}

	public void setAssetTypeDao(AssetTypeDao assetTypeDao) {
		this.assetTypeDao = assetTypeDao;
	}

	public AssetDao getAssetDao() {
		return assetDao;
	}

	public void setAssetDao(AssetDao assetDao) {
		this.assetDao = assetDao;
	}

	public DwOverhaulLineDao getDwOverhaulLineDao() {
		return dwOverhaulLineDao;
	}

	public void setDwOverhaulLineDao(DwOverhaulLineDao dwOverhaulLineDao) {
		this.dwOverhaulLineDao = dwOverhaulLineDao;
	}

	@Override
	public void calculateDwAssetEntityStat() {
		SqlSession session = MyBatisUtils.getSqlSession();
		List<DwAssetEntityStat> resultList = new ArrayList<DwAssetEntityStat>();
		try {
			resultList = session
					.selectList("report.calculateDwAssetEntityStat");
		} finally {
			session.close();
		}
		for (DwAssetEntityStat dwAssetEntityStat : resultList) {
			dwAssetEntityStatDao.insert(dwAssetEntityStat);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void calculateDwAssetLineValue() {
		SqlSession session = MyBatisUtils.getSqlSession();
		List<DwAssetLineValue> resultList = new ArrayList<DwAssetLineValue>();
		try {
			resultList = session
					.selectList("report.calculateDwAssetLineValue");
		} finally {
			session.close();
		}
		for(DwAssetLineValue dwAssetLineValue : resultList){
			dwAssetLineValueDao.insert(dwAssetLineValue);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void calculateDwAssetProjectLineValue() {
		SqlSession session = MyBatisUtils.getSqlSession();
		List<DwAssetProjectLineValue> resultList = new ArrayList<DwAssetProjectLineValue>();
		try {
			resultList = session
					.selectList("report.calculateDwAssetProjectLineValue");
		} finally {
			session.close();
		}
		for(DwAssetProjectLineValue dwAssetProjectLineValue : resultList){
			dwAssetProjectLineValueDao.insert(dwAssetProjectLineValue);
		}
	}
	
	

	@SuppressWarnings("unchecked")
	@Override
	public void calculateDwAssetUseOrganizationValue() {
		SqlSession session = MyBatisUtils.getSqlSession();
		List<DwAssetUseOrganizationUnit> resultList = new ArrayList<DwAssetUseOrganizationUnit>();
		try {
			resultList = session
					.selectList("report.calculateDwAssetUseOrganizationUnit");
		} finally {
			session.close();
		}
		for(DwAssetUseOrganizationUnit dwAssetUseOrganizationUnit : resultList){
			dwAssetUseOrganizationDao.insert(dwAssetUseOrganizationUnit);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void calculateDwAssetOwnerOrganizationValue() {
		SqlSession session = MyBatisUtils.getSqlSession();
		List<DwAssetOwnerOrganizationUnit> resultList = new ArrayList<DwAssetOwnerOrganizationUnit>();
		try {
			resultList = session
					.selectList("report.calculateDwAssetOwnerOrganizationUnit");
		} finally {
			session.close();
		}
		for(DwAssetOwnerOrganizationUnit dwAssetOwnerOrganizationUnit : resultList){
			dwAssetOwnerOrganizationDao.insert(dwAssetOwnerOrganizationUnit);
		}
	}
	
	@Override
	public void calculateDwHomePageStat() {
		SqlSession session = MyBatisUtils.getSqlSession();
		DwHomePageStat dwHomePageStat = new DwHomePageStat();
		try {
			CountPrice project = session.selectOne("report.calculateProjectCountAndPrice");
			String assetProjectCount = session.selectOne("report.calculateAssetProjectCount");
			CountPrice asset = session.selectOne("report.calculateAssetCountAndPrice");
			String accessAssetProjectCount = session.selectOne("report.calculateAccessAssetProjectCount");
			CountPrice accessAsset = session.selectOne("report.calculateAccessAssetCountAndPrice");

			dwHomePageStat.setProjectCount(project.getCount());
			dwHomePageStat.setProjectPrice(project.getPrice());
			dwHomePageStat.setAssetProjectCount(assetProjectCount);
			dwHomePageStat.setAssetCount(asset.getCount());
			dwHomePageStat.setAssetContractPrice(asset.getPrice());
			dwHomePageStat.setAccessAssetProjectCount(accessAssetProjectCount);
			dwHomePageStat.setAccessAssetCount(accessAsset.getCount());
			dwHomePageStat.setAccessAssetPrice(accessAsset.getPrice());
			dwHomePageStat.setCreateDate(new Date());
		} finally {
			session.close();
		}
		dwHomePageStatDao.insert(dwHomePageStat);
	}
	
	@Override
	public void calculateDwOverhaulProjectStat() {
		SqlSession session = MyBatisUtils.getSqlSession();
		DwOverhaulProjectStat overhaulProjectStat = new DwOverhaulProjectStat();
		try {
			Map<String, Object> fliter = new HashMap<String, Object>();
			fliter.put("thisYear", "Y");
			CountPrice renovateProject = session.selectOne("report.calculateRenovateProjectCountAndPrice");
			CountPrice overhaultProject = session.selectOne("report.calculateOverhaultProjectCountAndPrice");
			CountPrice renovateProjectThisYear = session.selectOne("report.calculateRenovateProjectCountAndPrice", fliter);
			CountPrice overhaultProjectThisYear = session.selectOne("report.calculateOverhaultProjectCountAndPrice", fliter);
			CountPrice newAsset = session.selectOne("report.calculateNewAssetCountAndPrice");
			CountPrice newAssetThisYear = session.selectOne("report.calculateNewAssetCountAndPrice", fliter);
			CountPrice scrapAsset = session.selectOne("report.calculateAssetCountAndPriceByState", "3");
			CountPrice useAsset = session.selectOne("report.calculateAssetCountAndPriceByState", "1");
			CountPrice stopAsset = session.selectOne("report.calculateAssetCountAndPriceByState", "2");
			overhaulProjectStat.setRenovateCount(renovateProject.getCount());
			overhaulProjectStat.setRenovatePrice(renovateProject.getPrice());
			overhaulProjectStat.setRenovateCountThisYear(renovateProjectThisYear.getCount());
			overhaulProjectStat.setRenovatePriceThisYear(renovateProjectThisYear.getPrice());
			overhaulProjectStat.setOverhaultCount(overhaultProject.getCount());
			overhaulProjectStat.setOverhaultPrice(overhaultProject.getPrice());
			overhaulProjectStat.setOverhaultCountThisYear(overhaultProjectThisYear.getCount());
			overhaulProjectStat.setOverhaultPriceThisYear(overhaultProjectThisYear.getPrice());
			overhaulProjectStat.setNewAssetCount(newAsset.getCount());
			overhaulProjectStat.setNewAssetPrice(newAsset.getPrice());
			overhaulProjectStat.setNewAssetCountThisYear(newAssetThisYear.getCount());
			overhaulProjectStat.setNewAssetPriceThisYear(newAssetThisYear.getPrice());
			
			overhaulProjectStat.setScrapAssetCount(scrapAsset.getCount());
			overhaulProjectStat.setScrapAssetValue(scrapAsset.getPrice());
			overhaulProjectStat.setUseAssetCount(useAsset.getCount());
			overhaulProjectStat.setUseAssetValue(useAsset.getPrice());
			overhaulProjectStat.setStopAssetCount(stopAsset.getCount());
			overhaulProjectStat.setStopAssetValue(stopAsset.getPrice());
			//todo
			overhaulProjectStat.setRentAssetCount("");
			overhaulProjectStat.setRentAssetValue("");
			overhaulProjectStat.setUnusedAssetCount("");
			overhaulProjectStat.setUnusedAssetValue("");
			overhaulProjectStat.setAllotAssetCount("");
			overhaulProjectStat.setAllotAssetValue("");
			overhaulProjectStat.setCheckedAssetCount("");	
			overhaulProjectStat.setCheckedResultCount("");	
			overhaulProjectStat.setCreateDate(new Date());
		} finally {
			session.close();
		}
		dwOverhaulProjectStatDao.insert(overhaulProjectStat);
	}

	
	@Override
	public void calculateDwOverhaulMajorType() {
		SqlSession session = MyBatisUtils.getSqlSession();
		List<DwOverhaulMajorType> resultList = new ArrayList<DwOverhaulMajorType>();
		Calendar cal = Calendar.getInstance();
		String year = String.valueOf(cal.get(Calendar.YEAR));
		try {
			resultList = session.selectList("report.calculateDwOverhaulMajorType", year);
		} finally {
			session.close();
		}
		for (DwOverhaulMajorType dwOverhaulMajorType : resultList) {
			dwOverhaulMajorTypeDao.insert(dwOverhaulMajorType);
		}
		/*for (DwOverhaulMajorType thisYear : thisYearList) {
			String typeId = thisYear.getTypeId();
			Double lastUpdatePrice = lastYearMap.get(typeId).getUpdatePrice();
			Double lastOverhaulPrice = lastYearMap.get(typeId).getOverhaulPrice();
			if(lastUpdatePrice != 0){
				Double updateRate = thisYear.getUpdatePrice()/lastUpdatePrice*100-100;
				BigDecimal updateRateBd = new BigDecimal(updateRate); 
				updateRateBd = updateRateBd.setScale(2, BigDecimal.ROUND_HALF_UP);
				thisYear.setUpdateIncreaseRate(updateRateBd.toString() + "%");
			}else {
				thisYear.setUpdateIncreaseRate("-");
			}if(lastOverhaulPrice != 0){
				Double overhaulRate = thisYear.getOverhaulPrice()/lastOverhaulPrice*100-100;
				BigDecimal overhaulRateBd = new BigDecimal(overhaulRate); 
				overhaulRateBd = overhaulRateBd.setScale(2, BigDecimal.ROUND_HALF_UP);
				thisYear.setOverhaulIncreaseRate(overhaulRateBd.toString() + "%");
			}else{
				thisYear.setOverhaulIncreaseRate("-");
			}
		    dwOverhaulMajorTypeDao.insert(thisYear);
		}*/
		
	}

	@Override
	public void calculateDwProjectCompanyPrice() {
		SqlSession session = MyBatisUtils.getSqlSession();
		List<DwProjectCompanyPrice> resultList = new ArrayList<DwProjectCompanyPrice>();
		try {
			resultList = session
					.selectList("report.calculateDwProjectCompanyPrice");
		} finally {
			session.close();
		}
		for (DwProjectCompanyPrice dwProjectCompanyPrice : resultList) {
			dwProjectCompanyPriceDao.insert(dwProjectCompanyPrice);
		}
	}
	
	
	@Override
	public void calculateDwProjectPriceByYear() {
		SqlSession session = MyBatisUtils.getSqlSession();
		List<DwProjectPriceByYear> resultList = new ArrayList<DwProjectPriceByYear>();
		try {
			resultList = session
					.selectList("report.calculateDwProjectPriceByYear");
		} finally {
			session.close();
		}
		for(DwProjectPriceByYear temp : resultList){
			dwProjectPriceByYearDao.insert(temp);
		}
	}
	
	@Override
	public void calculateDwAssetStateLine() {
		SqlSession session = MyBatisUtils.getSqlSession();
		List<DwAssetStateLine> resultList = new ArrayList<DwAssetStateLine>();
		try {
			resultList = session
					.selectList("report.calculateDwAssetStateLine");
		} finally {
			session.close();
		}
		for(DwAssetStateLine temp : resultList){
			dwAssetStateLineDao.insert(temp);
		}
	}
	
	@Override
	public void calculateDwStopStateAssetValue() {
		SqlSession session = MyBatisUtils.getSqlSession();
		List<DwStopStateAssetValue> resultList = new ArrayList<DwStopStateAssetValue>();
		try {
			resultList = session
					.selectList("report.calculateDwStopStateAssetValue");
		} finally {
			session.close();
		}
		for(DwStopStateAssetValue temp : resultList){
			dwStopStateAssetValueDao.insert(temp);
		}
	}

	@Override
	public void calculateDwScrapStateAssetValue() {
		SqlSession session = MyBatisUtils.getSqlSession();
		List<DwScrapStateAssetValue> resultList = new ArrayList<DwScrapStateAssetValue>();
		try {
			resultList = session
					.selectList("report.calculateDwScrapStateAssetValue");
		} finally {
			session.close();
		}
		for(DwScrapStateAssetValue temp : resultList){
			dwScrapStateAssetValueDao.insert(temp);
		}
		
	}

	@Override
	public void calculateDwMaterialsConsume() {
		SqlSession session = MyBatisUtils.getSqlSession();
		List<DwMaterialsConsume> resultList = new ArrayList<DwMaterialsConsume>();
		try {
			resultList = session
					.selectList("report.calculateDwMaterialsConsume");
		} finally {
			session.close();
		}
		for (DwMaterialsConsume dwMaterialsConsume : resultList) {
			dwMaterialsConsumeDao.insert(dwMaterialsConsume);
		}
	}
	
	@Override
	public void calculateDwOverhaulBudgetYear() {
		SqlSession session = MyBatisUtils.getSqlSession();
		List<DwOverhaulBudgetYear> resultList = new ArrayList<DwOverhaulBudgetYear>();
		try {
			resultList = session
					.selectList("report.calculateDwOverhaulBudgetYear");
		} finally {
			session.close();
		}
		for (DwOverhaulBudgetYear dwOverhaulBudgetYear : resultList) {
			dwOverhaulBudgetYearDao.insert(dwOverhaulBudgetYear);
		}
	}

	@Override
	public void calculateDwCheckAssetAccuracyYear() {
		SqlSession session = MyBatisUtils.getSqlSession();
		List<DwCheckAssetAccuracyYear> resultList = new ArrayList<DwCheckAssetAccuracyYear>();
		try {
			resultList = session
					.selectList("report.");	//todo
		} finally {
			session.close();
		}
		for (DwCheckAssetAccuracyYear dwCheckAssetAccuracyYear : resultList) {
			dwCheckAssetAccuracyYearDao.insert(dwCheckAssetAccuracyYear);
		}
	}
	
	@Override
	public void calculateDwImportantAssetRank() {
		SqlSession session = MyBatisUtils.getSqlSession();
		List<DwImportantAssetRank> resultList = new ArrayList<DwImportantAssetRank>();
		try {
			resultList = session
					.selectList("report.calculateDwImportantAssetRank");
		} finally {
			session.close();
		}
		for (DwImportantAssetRank dwImportantAssetRank : resultList) {
			dwImportantAssetRankDao.insert(dwImportantAssetRank);
		}
	}
	
	@Override
	public void calculateDwAssetImportantRatio() {
		SqlSession session = MyBatisUtils.getSqlSession();
		List<DwAssetImportantRatio> resultList = new ArrayList<DwAssetImportantRatio>();
		try {
			resultList = session
					.selectList("report.calculateDwAssetImportantRatio");
		} finally {
			session.close();
		}
		for (DwAssetImportantRatio dwAssetImportantRatio : resultList) {
			dwAssetImportantRatioDao.insert(dwAssetImportantRatio);
		}
	}

	@Override
	public void calculateDwAssetTypeState() {
		SqlSession session = MyBatisUtils.getSqlSession();
		List<DwAssetTypeState> resultList = new ArrayList<DwAssetTypeState>();
		Map<String, Object> filter = new HashMap<String, Object>();
		try {
			filter.put("type", "assetType");
			List<DwAssetTypeState> assetTypeResult = session
					.selectList("report.calculateDwAssetTypeState", filter);
			resultList.addAll(assetTypeResult);
			filter.put("type", "org");
			List<DwAssetTypeState> orgResult = session
					.selectList("report.calculateDwAssetTypeState", filter);
			resultList.addAll(orgResult);
			filter.put("type", "line");
			List<DwAssetTypeState> lineResult = session
					.selectList("report.calculateDwAssetTypeState", filter);
			resultList.addAll(lineResult);
		} finally {
			session.close();
		}
		Date createDate = new Date();
		for (DwAssetTypeState dwAssetTypeState : resultList) {
			dwAssetTypeState.setCreateDate(createDate);
			dwAssetTypeStateDao.insert(dwAssetTypeState);
		}
	}
	
	@Override
	public void calculateDwOverhaulLine() {
		SqlSession session = MyBatisUtils.getSqlSession();
		List<DwOverhaulLine> resultList = new ArrayList<DwOverhaulLine>();
		List<String> moneySourceRenovate = new ArrayList<String>();
		List<String> moneySourceRenovateVehicle = new ArrayList<String>();
		List<String> moneySourceRenovatePower = new ArrayList<String>();
		List<String> moneySourceRenovateSignal = new ArrayList<String>();
		List<String> moneySourceRenovateWork = new ArrayList<String>();
		List<String> moneySourceRenovateLogistics = new ArrayList<String>();
		List<String> moneySourceRenovateStationEle = new ArrayList<String>();
		List<String> moneySourceRenovateOperation1 = new ArrayList<String>();
		List<String> moneySourceRenovateOperation2 = new ArrayList<String>();
		List<String> moneySourceRenovateOperation3 = new ArrayList<String>();
		List<String> moneySourceRenovateOperation4 = new ArrayList<String>();
		List<String> moneySourceRenovateTransportManager = new ArrayList<String>();
		
		List<String> moneySourceOverhaul = new ArrayList<String>();
		List<String> moneySourceOverhaulVehicle = new ArrayList<String>();
		List<String> moneySourceOverhaulPower = new ArrayList<String>();
		List<String> moneySourceOverhaulSignal = new ArrayList<String>();
		List<String> moneySourceOverhaulWork = new ArrayList<String>();
		List<String> moneySourceOverhaulLogistics = new ArrayList<String>();
		List<String> moneySourceOverhaulStationEle = new ArrayList<String>();
		List<String> moneySourceOverhaulOperation1 = new ArrayList<String>();
		List<String> moneySourceOverhaulOperation2 = new ArrayList<String>();
		List<String> moneySourceOverhaulOperation3 = new ArrayList<String>();
		List<String> moneySourceOverhaulOperation4 = new ArrayList<String>();
		List<String> moneySourceOverhaulTransportManager = new ArrayList<String>();
		try {
			Calendar cal = Calendar.getInstance();
			String year = String.valueOf(cal.get(Calendar.YEAR));
			resultList = session.selectList("report.calculateDwOverhaulLine", year);
			Map<String, String> filter = new HashMap<String, String>();
			filter.put("year", year);
			filter.put("projectType", "2");
			/*			
			moneySourceRenovate = session.selectList("report.findMoneySource", filter);//更新
			filter.put("professionalType", "1");
			moneySourceRenovateVehicle = session.selectList("report.findMoneySource", filter);
			filter.put("professionalType", "2");
			moneySourceRenovatePower = session.selectList("report.findMoneySource", filter);
			filter.put("professionalType", "3");
			moneySourceRenovateSignal = session.selectList("report.findMoneySource", filter);
			filter.put("professionalType", "4");
			moneySourceRenovateWork = session.selectList("report.findMoneySource", filter);
			filter.put("professionalType", "5");
			moneySourceRenovateLogistics = session.selectList("report.findMoneySource", filter);
			filter.put("professionalType", "6");
			moneySourceRenovateStationEle = session.selectList("report.findMoneySource", filter);
			filter.put("professionalType", null);
			filter.put("orgCode", "55");
			moneySourceRenovateOperation1 = session.selectList("report.findMoneySource", filter);
			filter.put("orgCode", "56");
			moneySourceRenovateOperation2 = session.selectList("report.findMoneySource", filter);
			filter.put("orgCode", "57");
			moneySourceRenovateOperation3 = session.selectList("report.findMoneySource", filter);
			filter.put("orgCode", "58");
			moneySourceRenovateOperation4 = session.selectList("report.findMoneySource", filter);
			filter.put("orgCode", "54");
			moneySourceRenovateTransportManager = session.selectList("report.findMoneySource", filter);
			*/			
			/*******/
			/*			
			filter.clear();
			filter.put("projectType", "3");
			moneySourceOverhaul = session.selectList("report.findMoneySource", filter);//大修
			filter.put("professionalType", "1");
			moneySourceOverhaulVehicle = session.selectList("report.findMoneySource", filter);
			filter.put("professionalType", "2");
			moneySourceOverhaulPower = session.selectList("report.findMoneySource", filter);
			filter.put("professionalType", "3");
			moneySourceOverhaulSignal = session.selectList("report.findMoneySource", filter);
			filter.put("professionalType", "4");
			moneySourceOverhaulWork = session.selectList("report.findMoneySource", filter);
			filter.put("professionalType", "5");
			moneySourceOverhaulLogistics = session.selectList("report.findMoneySource", filter);
			filter.put("professionalType", "6");
//			moneySourceOverhaulStationEle = session.selectList("report.findMoneySource", filter);
			
			filter.put("professionalType", null);
			filter.put("orgCode", "55");
			moneySourceOverhaulOperation1 = session.selectList("report.findMoneySource", filter);
			filter.put("orgCode", "56");
			moneySourceOverhaulOperation2 = session.selectList("report.findMoneySource", filter);
			filter.put("orgCode", "57");
			moneySourceOverhaulOperation3 = session.selectList("report.findMoneySource", filter);
			filter.put("orgCode", "58");
			moneySourceOverhaulOperation4 = session.selectList("report.findMoneySource", filter);
			filter.put("orgCode", "54");
			moneySourceOverhaulTransportManager = session.selectList("report.findMoneySource", filter);
			*/
		} finally {
			session.close();
		}
		Map<String, Double> renovateMap = parseMoneySource(moneySourceRenovate);
		Map<String, Double> renovateVehicleMap = parseMoneySource(moneySourceRenovateVehicle);
		Map<String, Double> renovatePowerMap = parseMoneySource(moneySourceRenovatePower);
		Map<String, Double> renovateSignalMap = parseMoneySource(moneySourceRenovateSignal);
		Map<String, Double> renovateWorkMap = parseMoneySource(moneySourceRenovateWork);
		Map<String, Double> renovateLogisticsMap = parseMoneySource(moneySourceRenovateLogistics);
		Map<String, Double> renovateStationEleMap = parseMoneySource(moneySourceRenovateStationEle);
		Map<String, Double> renovateOperation1Map = parseMoneySource(moneySourceRenovateOperation1);
		Map<String, Double> renovateOperation2Map = parseMoneySource(moneySourceRenovateOperation2);
		Map<String, Double> renovateOperation3Map = parseMoneySource(moneySourceRenovateOperation3);
		Map<String, Double> renovateOperation4Map = parseMoneySource(moneySourceRenovateOperation4);
		Map<String, Double> renovateTransportManagerMap = parseMoneySource(moneySourceRenovateTransportManager);
		
		Map<String, Double> overhaulMap = parseMoneySource(moneySourceOverhaul);
		Map<String, Double> overhaulVehicleMap = parseMoneySource(moneySourceOverhaulVehicle);
		Map<String, Double> overhaulPowerMap = parseMoneySource(moneySourceOverhaulPower);
		Map<String, Double> overhaulSignalMap = parseMoneySource(moneySourceOverhaulSignal);
		Map<String, Double> overhaulWorkMap = parseMoneySource(moneySourceOverhaulWork);
		Map<String, Double> overhaulLogisticsMap = parseMoneySource(moneySourceOverhaulLogistics);
		Map<String, Double> overhaulStationEleMap = parseMoneySource(moneySourceOverhaulStationEle);
		Map<String, Double> overhaulOperation1Map = parseMoneySource(moneySourceOverhaulOperation1);
		Map<String, Double> overhaulOperation2Map = parseMoneySource(moneySourceOverhaulOperation2);
		Map<String, Double> overhaulOperation3Map = parseMoneySource(moneySourceOverhaulOperation3);
		Map<String, Double> overhaulOperation4Map = parseMoneySource(moneySourceOverhaulOperation4);
		Map<String, Double> overhaulTransportManagerMap = parseMoneySource(moneySourceOverhaulTransportManager);
		
		for (DwOverhaulLine dwOverhaulLine : resultList) {
			String lineName = dwOverhaulLine.getName();
			if(renovateMap.get(lineName) != null){
				dwOverhaulLine.setRenovatePrice(renovateMap.get(lineName));
			}
			if(renovateVehicleMap.get(lineName) != null){
				dwOverhaulLine.setRenovateVehicle(renovateVehicleMap.get(lineName));
			}
			if(renovatePowerMap.get(lineName) != null){
				dwOverhaulLine.setRenovatePower(renovatePowerMap.get(lineName));
			}
			if(renovateSignalMap.get(lineName) != null){
				dwOverhaulLine.setRenovateSignal(renovateSignalMap.get(lineName));
			}
			if(renovateWorkMap.get(lineName) != null){
				dwOverhaulLine.setRenovateWork(renovateWorkMap.get(lineName));
			}
			if(renovateLogisticsMap.get(lineName) != null){
				dwOverhaulLine.setRenovateLogistics(renovateLogisticsMap.get(lineName));
			}
			if(renovateStationEleMap.get(lineName) != null){
				dwOverhaulLine.setRenovateStationEle(renovateStationEleMap.get(lineName));
			}
			if(renovateOperation1Map.get(lineName) != null){
				dwOverhaulLine.setRenovateOperation1(renovateOperation1Map.get(lineName));
			}
			if(renovateOperation2Map.get(lineName) != null){
				dwOverhaulLine.setRenovateOperation2(renovateOperation2Map.get(lineName));
			}
			if(renovateOperation3Map.get(lineName) != null){
				dwOverhaulLine.setRenovateOperation3(renovateOperation3Map.get(lineName));
			}
			if(renovateOperation4Map.get(lineName) != null){
				dwOverhaulLine.setRenovateOperation4(renovateOperation4Map.get(lineName));
			}
			if(renovateTransportManagerMap.get(lineName) != null){
				dwOverhaulLine.setRenovateTransportManager(renovateTransportManagerMap.get(lineName));
			}
			
			if(overhaulMap.get(dwOverhaulLine.getName()) != null){
				dwOverhaulLine.setOverhaulPrice(overhaulMap.get(lineName));
			}
			if(overhaulVehicleMap.get(lineName) != null){
				dwOverhaulLine.setOverhaulVehicle(overhaulVehicleMap.get(lineName));
			}
			if(overhaulPowerMap.get(lineName) != null){
				dwOverhaulLine.setOverhaulPower(overhaulPowerMap.get(lineName));
			}
			if(overhaulSignalMap.get(lineName) != null){
				dwOverhaulLine.setOverhaulSignal(overhaulSignalMap.get(lineName));
			}
			if(overhaulWorkMap.get(lineName) != null){
				dwOverhaulLine.setOverhaulWork(overhaulWorkMap.get(lineName));
			}
			if(overhaulLogisticsMap.get(lineName) != null){
				dwOverhaulLine.setOverhaulLogistics(overhaulLogisticsMap.get(lineName));
			}
			if(overhaulStationEleMap.get(lineName) != null){
				dwOverhaulLine.setOverhaulStationEle(overhaulStationEleMap.get(lineName));
			}
			if(overhaulOperation1Map.get(lineName) != null){
				dwOverhaulLine.setOverhaulOperation1(overhaulOperation1Map.get(lineName));
			}
			if(overhaulOperation2Map.get(lineName) != null){
				dwOverhaulLine.setOverhaulOperation2(overhaulOperation2Map.get(lineName));
			}
			if(overhaulOperation3Map.get(lineName) != null){
				dwOverhaulLine.setOverhaulOperation3(overhaulOperation3Map.get(lineName));
			}
			if(overhaulOperation4Map.get(lineName) != null){
				dwOverhaulLine.setOverhaulOperation4(overhaulOperation4Map.get(lineName));
			}
			if(overhaulTransportManagerMap.get(lineName) != null){
				dwOverhaulLine.setOverhaulTransportManager(overhaulTransportManagerMap.get(lineName));
			}
			dwOverhaulLineDao.insert(dwOverhaulLine);
		}

	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Double> parseMoneySource(List<String> moneySources){
		Map<String, Double> map = new HashMap<String, Double>();
		for (String str : moneySources) {
			JSONObject jsonObj  = JSONObject.fromObject(str);
			JSONArray jsonArray = jsonObj.getJSONArray("moneySource");
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setArrayMode( JsonConfig.MODE_LIST );
			jsonConfig.setRootClass(MoneySource.class );
			List<MoneySource> stats = (List<MoneySource>) JSONSerializer.toJava( jsonArray, jsonConfig );
			for(MoneySource ms : stats){
				String lineName = ms.getLineName();
				if(lineName != null && !lineName.isEmpty()){
					if(map.get(lineName) == null){
						map.put(lineName, ms.getMoney()*10000);
					}else {
						map.put(lineName, map.get(lineName)+ ms.getMoney()*10000);
					}
				}
			}
		}
		return map;
	}
	
	@Override
	public List<DwAssetEntityStat> findReportStat() {
		return dwAssetEntityStatDao.findAssetStatByTree();
	}
	
	

	@Override
	public List<DwAssetEntityStat> findReportStatByYear(String start,String end) {
		return dwAssetEntityStatDao.findAssetStatByTreeAndYear(start,end);
	}

	@Override
	public List<DwAssetEntityStat> findReportMainType() {

		return dwAssetEntityStatDao.findAssetStatMainType();
	}

	
	
	@Override
	public List<DwAssetEntityStat> findReportMainTypeByDate(String start,String end) {
		return dwAssetEntityStatDao.findReportMainTypeByDate(start,end);
	}

	@Override
	public List<DwAssetLineValue> findAssetLine() {

		return dwAssetLineValueDao.findAssetLine();
	}

	@Override
	public List<DwAssetLineValue> findAssetLineByDate(String start, String end) {
		return dwAssetLineValueDao.findAssetLineByDate(start,end);
	}

	@Override
	public List<DwAssetUseOrganizationUnit> findAssetUseOrganization() {
	
		return dwAssetUseOrganizationDao.findAssetUseOrganization();
	}

	
	
	@Override
	public List<DwAssetUseOrganizationUnit> findAssetUseOrganizationByDate(
			String start, String end) {
		return dwAssetUseOrganizationDao.findAssetUseOrganizationByDate(start,end);
	}

	@Override
	public List<DwAssetOwnerOrganizationUnit> findAssetOwnerOrganization() {
	
		return dwAssetOwnerOrganizationDao.findAssetOwnerOrganization();
	}
	
	

	@Override
	public List<DwAssetOwnerOrganizationUnit> findAssetOwnerOrganizationByDate(
			String start, String end) {
		return dwAssetOwnerOrganizationDao.findAssetOwnerOrganizationByDate(start,end);
	}

	@Override
	public List<DwAssetProjectLineValue> findAssetProjectLineValue() {
		
		return dwAssetProjectLineValueDao.findAssetProjectLineValue();
	}

	
	
	@Override
	public List<DwAssetProjectLineValue> findAssetProjectLineValueByDate(
			String start, String end) {
		return dwAssetProjectLineValueDao.findAssetProjectLineValueByDate(start,end);
	}

	@Override
	public List<Object[]> findLineAndCountProject() {

		return dwAssetProjectLineValueDao.findLineAndCountProject();
	}

	@Override
	public List<DwAssetLineValue> findDwAssetLineValue() {
		
		return dwAssetLineValueDao.findAssetLine();
	}
	
	@Override
	public List<DwAssetUseOrganizationUnit> findUseOrgUnit() {
		
		return dwAssetUseOrganizationDao.findAssetUseOrganization();
	}

	@Override
	public List<DwAssetEntityStat> findEntityStatForChart() {
		return dwAssetEntityStatDao.findAssetStatMainTypeForCHart();
	}

	@Override
	public DwHomePageStat findLastestDwHomePageStat() {
		
		return dwHomePageStatDao.findLastestDwHomePageStat();
	}
	
	@Override
	public List<DwProjectCompanyPrice> findDwProjectCompanyPrice() {
		return dwProjectCompanyPriceDao.findAllByOrder();
	}
	
	@Override
	public List<DwProjectPriceByYear> findDwProjectPriceByYear() {
		return dwProjectPriceByYearDao.findAllByOrder();
	}

	@Override
	public List<DwAssetStateLine> findDwAssetStateLine() {
		
		return dwAssetStateLineDao.findAllByOrder();
	}
	
	@Override
	public List<DwStopStateAssetValue> findDwStopStateAssetValue() {
		
		return dwStopStateAssetValueDao.findDwStopStateAssetValue();
	}

	@Override
	public List<DwScrapStateAssetValue> findDwScrapStateAssetValue() {
		
		return dwScrapStateAssetValueDao.findDwScrapStateAssetValue();
	}

	@Override
	public DwOverhaulProjectStat findDwOverhaulProjectStat() {
		
		return dwOverhaulProjectStatDao.findDwOverhaulProjectStat();
	}

	@Override
	public List<DwOverhaulBudgetYear> findDwOverhaulBudgetYear() {
		
		return dwOverhaulBudgetYearDao.findDwOverhaulBudgetYear();
	}

	@Override
	public DwScrapAssetUseLife findDwScrapAssetUseLifeBySubTypeId(String year,String subTypeName) {
		
		return dwScrapAssetUseLifeDao.findDwScrapAssetUseLife(year, subTypeName);
	}

	@Override
	public List<DwScrapAssetUseLife> findAllDwScrapUseLifeSubTypeName(
			String year) {
		
		return dwScrapAssetUseLifeDao.findAllDwScrapUseLifeSubTypeName(year);
	}

	@Override
	public void calculateDwScrapAssetUseLife() {
		SqlSession session = MyBatisUtils.getSqlSession();
		DwScrapAssetUseLife dwScrapAssetUseLife1 = new DwScrapAssetUseLife();
		DwScrapAssetUseLife dwScrapAssetUseLife2 = new DwScrapAssetUseLife();
		try {
			Date createDate = new Date();
			dwScrapAssetUseLife1 = session.selectOne("report.calculateDwScrapAssetUseLife", "647");//自动扶梯
			dwScrapAssetUseLife1.setCreateDate(createDate);
			dwScrapAssetUseLife2 = session.selectOne("report.calculateDwScrapAssetUseLife", "648");//直升电梯
			dwScrapAssetUseLife2.setCreateDate(createDate);
		} finally {
			session.close();
		}
		dwScrapAssetUseLifeDao.insert(dwScrapAssetUseLife1);
		dwScrapAssetUseLifeDao.insert(dwScrapAssetUseLife2);
	}

	@Override
	public List<DwCheckAssetAccuracyYear> findDwCheckAssetAccuracyYear() {
		return dwCheckAssetAccuracyYearDao.findDwCheckAssetAccuracyYear();
	}

	@Override
	public List<DwMaterialsConsume> findDwMaterialsConsume(String year) {
		return dwMaterialsConsumeDao.findDwMaterialsConsume(year);
	}

	
	@Override
	public List<DwOverhaulMajorType> findDwOverhaulMajorType(String year) {
		return dwOverhaulMajorTypeDao.findDwOverhaulMajorType(year);
	}

	@Override
	public List<DwImportantAssetLine> findDwImportantAssetLine(String typeId) {
		SqlSession session = MyBatisUtils.getSqlSession();
		List<DwImportantAssetLine> resultList = new ArrayList<DwImportantAssetLine>();
		Map<String, String> filterMap = new HashMap<String, String>();
		String[] strArray = typeId.split("#");
		if(strArray[1].length() == 2){
			filterMap.put("mainTypeId", strArray[0]);
		}else if(strArray[1].length() == 4){
			filterMap.put("subTypeId", strArray[0]);
		}
		try {
			resultList = session
					.selectList("report.calculateDwImportantAssetLine", filterMap);
		} finally {
			session.close();
		}
		return resultList;
	}

	@Override
	public List<String> findImportantAssetRankType() {
		List<Object> list = dwImportantAssetRankDao.findImportantAssetRankType();
		List<String> result = null;
		if(list!=null && list.size()>0){
			result = new ArrayList<String>();
			for(int i=0,len=list.size(); i<len; i++){
				result.add(list.get(i).toString());
			}
		}
		return result;
	}

	@Override
	public List<DwImportantAssetRank> findImportantAssetRankByType(String type) {
		
		return dwImportantAssetRankDao.findImportantAssetRankByType(type);
	}


	@Override
	public List<DwAssetTypeState> queryDwAssetTypeState() {
		DwAssetTypeState dwAssetTypeState = dwAssetTypeStateDao.findLastest();
		List<DwAssetTypeState> result = null;
		if(dwAssetTypeState!=null && dwAssetTypeState.getCreateDate()!=null){
			result = dwAssetTypeStateDao.findByCreateDate(dwAssetTypeState.getCreateDate());
		}
		return result;
	}

	@Override
	public List<DwAssetTypeState> queryDwAssetTypeState(String type) {
		List<DwAssetTypeState>  result = dwAssetTypeStateDao.findByType(type);
		return result;
	}

	@Override
	public List<DwAssetImportantRatio> findDwAssetImportantRation() {
		return dwAssetImportantRatioDao.findLastestDwImportantRation();
	}

	@Override
	public List<DwOverhaulLine> findDwOverhaulLine() {
		return dwOverhaulLineDao.findLastestDwOverhaulLine();
	}

	
	
	@Override
	public List<DwOverhaulLine> findDwOverhaulLineByYear(String year) {
		return dwOverhaulLineDao.findDwOverhaulLineByYear(year);
	}

	@Override
	public List<String> findDwOverhaulLineYear() {
		
		return dwOverhaulLineDao.findDwOverhaulLineYear();
	}

	@Override
	public List<DwOverhaulMajorType> findDwOverhaulMajorType() {
		
		return dwOverhaulMajorTypeDao.findLastestDwOverhaulMajorType();
	}

	@Override
	public List<String> findAllYearOfDwAssetEntityStat() {
		
		return dwAssetEntityStatDao.findAllYearOfDwAssetEntityStat();
	}

	@Override
	public List<String> findAllYearOfDwAssetLineValue() {
		
		return dwAssetLineValueDao.findAllYearOfDwAssetLineValue();
	}

	@Override
	public List<String> findAllYearOfDwAssetUseOrganizationUnit() {
		return dwAssetUseOrganizationDao.findAllYearOfDwAssetUseOrganizationUnit();
	}

	@Override
	public List<String> findAllYearOfDwAssetOwnerOrganizationUnit() {
		return dwAssetOwnerOrganizationDao.findAllYearOfDwAssetOwnerOrganizationUnit();
	}

	@Override
	public List<String> findAllYearOfDwAssetProjectLineValue() {
		return dwAssetProjectLineValueDao.findAllYearOfDwAssetProjectLineValue();
	}

    @Override
    public List<DwAssetProjectLineValue> findAssetTransferValueByDate(String start, String end, boolean hasCount) {
        return dwAssetProjectLineValueDao.findAssetProjectLineValueByDate(start,end,hasCount);
    }
    
    /**
     * 资产移交
     */
    @Override
    public List<Object[]> findAssetTransferValue(String start, String end, boolean hasCount) {
    	return assetDao.findAssetTransfer(start, end, hasCount);
    }
    @Override
    public List<Object[]> getAssetTransferValue(String start, String end, boolean hasCount) {
    	return assetDao.getAssetTransfer(start, end, hasCount);
    }

    @Override
    public Map getAssetTransferKpi(String start, String end) {
        return dwAssetProjectLineValueDao.count(start,end);
    }

    @Override
    public Map getAssetUpdateData(String start, String end) {
        List<Map> assetTypeMap = dwAssetProjectLineValueDao.countByAssetType(start, end);
        if(assetTypeMap.size()>5)
            assetTypeMap = assetTypeMap.subList(0,5);
        List<Map> lineMap = dwAssetProjectLineValueDao.countByLine(start, end);
        List<Map> departmentMap = dwAssetProjectLineValueDao.countByDepartment(start, end);
        List<DwOverhaulBudgetYear> budgetYearChart = findDwOverhaulBudgetYear();
        Map result = new HashMap();
        result.put("assetTypeChart",assetTypeMap);
        result.put("lineChart",lineMap);
        result.put("departmentChart",departmentMap);
        result.put("budgetYearChart",budgetYearChart);
        return result;
    }

	@Override
	public Map getAssetUpdateKpi(String year) {
		Map map = new HashMap();
		double allInvest = projectDao.countAllInvest();
		double yearInvest = projectDao.countInvestByYear(year);
		int allAsset = assetDao.getAllAssetCount();
		int updateAsset = assetDao.getAssetCountByType("更新");
		int newAsset = assetDao.getAssetCountByType("新增");
		int overhaulAsset = updateAsset + newAsset;
		String pInvestChange = Math.round(100*(allInvest-yearInvest)/allInvest)+"%";
		String pNewAsset = Math.round(100*Double.valueOf(newAsset)/allAsset)+"%";
		String pUpdateAsset = Math.round(100*Double.valueOf(updateAsset)/allAsset)+"%";
		String pOverhaulAsset = Math.round(100*Double.valueOf(overhaulAsset)/allAsset)+"%";
		
		map.put("allInvest", allInvest);
		map.put("yearInvest", yearInvest);
		map.put("pInvestChange", pInvestChange);
		map.put("pNewAsset", pNewAsset);
		map.put("pUpdateAsset", pUpdateAsset);
		map.put("pOverhaulAsset", pOverhaulAsset);
		return map;
	}
	
	
	public int calByFlag(int flag,int inAdvance,int normal,int late,int add){
		int tmp=0;
		switch(flag){
		case -1:
		{
			tmp=inAdvance;
			break;
			}
		case 0:
		{
			tmp = normal;
			break;
			}
		case 1:
		{
			tmp = late;
			break;
			}
		default:{
			//错误数据的处理
		}
		}
		return tmp+add;
	}
	/** 
	 * 重点专项-大修资产更新
	 * @see com.wonders.asset.service.ReportService#getAssetUpdateKpi(java.lang.String, java.lang.String)
	 */
	@Override
	public Map getAssetUpdateKpi(String year, String detailTypeCodeId) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		double allInvest = projectDao.countAllInvest();
		double yearInvest = projectDao.countInvestByYear(year);
		double lastYearInvest = projectDao.countInvestByYear(String.valueOf(Integer.parseInt(year)-1));
		int allAsset = assetDao.getAllAssetCount();
		int updateAsset = assetDao.getAssetCountByType("更新");
		int newAsset = assetDao.getAssetCountByType("新增");
		//大修资产占比
	
		int overhaulAsset = assetDao.getOverhaulAsset(detailTypeCodeId);
		//先得到数据，再处理得到结果
//		List<Object[]> dataList = assetDao.getOverhaulAssetData();
		
//		dataList.size();
		int normal=0; //正常大修数
		int inAdvance=0;//提前大修数
		int late=0;//延迟大修数
		
//		for (Object[] each:dataList){
//			
//			switch( Integer.parseInt(each[0].toString()) ){
//			case 0:{//0 == isNull	
//					switch(Integer.parseInt(each[1].toString()))//dateDif
//					{
//					case 0: // 排名后有一条记录的
//					{
//						if (each[7].toString().equals("初始")){
//							switch(Integer.parseInt(each[3].toString())){
//							case -1:
//							{
//								inAdvance += Integer.parseInt(each[9].toString());
//								break;
//								}
//							case 0:
//							{
//								normal += Integer.parseInt(each[9].toString());
//								break;
//								}
//							case 1:
//							{
//								late += Integer.parseInt(each[9].toString());
//								break;
//								}
//							default:{
//								//错误数据的处理
//							}
//							}
//						}else if(each[7].toString().equals("新增或更新")){
//									normal+=Integer.parseInt(each[6].toString());
//								}else{
//									//查出数据有问题
//								}
//						break;
//					}//case 0: // 排名后有一条记录的
//					case 1: //排名后有两条以上记录的
//					{
//						switch(Integer.parseInt(each[4].toString())){
//						case -1:
//						{
//							inAdvance += Integer.parseInt(each[5].toString());
//							break;
//							}
//						case 0:
//						{
//							normal += Integer.parseInt(each[5].toString());
//							break;
//							}
//						case 1:
//						{
//							late += Integer.parseInt(each[5].toString());
//							break;
//							}
//						default:{
//							//错误数据的处理
//						}
//						}
//						break;
//					}//case 1: //排名后有两条以上记录的
//						default: break;
//					}//end of 
//				break;
//			}//0 == isNull
//			case 1://1 == isNull
//			{
//				
//					switch(Integer.parseInt(each[3].toString())){
//					case -1:
//					{
//						inAdvance += Integer.parseInt(each[2].toString());
//						break;
//						}
//					case 0:
//					{
//						normal += Integer.parseInt(each[2].toString());
//						break;
//						}
//					case 1:
//					{
//						late += Integer.parseInt(each[2].toString());
//						break;
//						}
//					default:{
//						//错误数据的处理
//					}
//					}
//				break;
//			}//1 == isNull
//			
//			default: 
//			{
//				//错误数据处理
//				break;
//			}
//			}// end of switch( Integer.parseInt(each[0].toString()) )
//			
//		}//end of for
		String pInvestChange = String.format("%.2f",100*(yearInvest-lastYearInvest)/lastYearInvest)+"%";
		String pNewAsset = String.format("%.2f",100*Double.valueOf(newAsset)/allAsset)+"%";
		String pUpdateAsset = String.format("%.2f",100*Double.valueOf(updateAsset)/allAsset)+"%";
		String pOverhaulAsset = String.format("%.2f",100*Double.valueOf(overhaulAsset)/allAsset)+"%";
		String pInAdvance = String.format("%.2f",100*Double.valueOf(inAdvance)/allAsset)+"%";
		String pNormal = String.format("%.2f",100*Double.valueOf(normal)/allAsset)+"%";
		String pLate = String.format("%.2f",100*Double.valueOf(late)/allAsset)+"%";
		map.put("allInvest", allInvest);
		map.put("yearInvest", yearInvest);
		map.put("pInvestChange", pInvestChange);
		map.put("pNewAsset", pNewAsset);
		map.put("pUpdateAsset", pUpdateAsset);
		//大修资产占比
		map.put("pOverhaulAsset", pOverhaulAsset);
		map.put("pInAdvance", pInAdvance);//提前
		map.put("pNormal", pNormal);//正常
		map.put("pLate", pLate);//延迟
		return map;
	}

	@Override
	public Map getAssetDamageKpi() {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		Integer iAllAsset = assetDao.getAllAssetCount(); //所有资产数
		List<Object[]> dataList = assetDao.getAssetDamage(); //计算提前 正常 超期 kpi所需数据
		
		Integer damage = assetDao.getDamageAssetCount();
		Integer outOfService = assetDao.getOutOfServiceAssetCount();
		int dInAdvance = 0;
		int dNormal=0;
		int dLate=0;

		for (Object[] each:dataList){
			/*-- flag1 = 1 flag = -1 提前 flag = 0 正常 flag = 1 超期
	-- flag1= 0 超期
	-- flag1 = -1 */
			switch( Integer.parseInt(each[0].toString()) ){
			case 1:{
				switch(Integer.parseInt(each[2].toString())){
				case -1:{
					dInAdvance += Integer.parseInt(each[3].toString());
					break;}
				case 0:{
					dNormal += Integer.parseInt(each[3].toString());
					break;}
				case 1:{
					dLate += Integer.parseInt(each[3].toString());
					break;}
				default:{};
				}
				break;
				}
			case 0:{
				dLate += Integer.parseInt(each[3].toString());
				break;
				}
			case -1:{}
			default:{}
			}//end of 
		}
		
		Double allAsset = iAllAsset.doubleValue();
		String kDamage = String.format("%.2f", 100*(damage)/allAsset)+"";
		String kOutOfService = String.format("%.2f",100*(outOfService)/allAsset)+"";
		String kDInAdvance =String.format("%.2f",100*(dInAdvance)/allAsset)+"";
		String kDNormal=String.format("%.2f",100*(dNormal)/allAsset)+"";
		String kDLate=String.format("%.2f",100*(dLate)/allAsset)+"";
		map.put("kDamage", kDamage);
		map.put("kOutOfService", kOutOfService);
		map.put("kDInAdvance", kDInAdvance);
		map.put("kDNormal", kDNormal);
		map.put("kDLate", kDLate);
		//大修资产占比

		return map;
	}

	@Override
	public Map getAssetDamageChart() {
		// TODO Auto-generated method stub

        List<Map> assetDamageChangeChart = assetDao.assetDamageChangeChart();

        List<Map> assetDamageProfessionChart = assetDao.assetDamageProfessionChart();
        List<Map> assetDamageDepartmentChart = assetDao.assetDamageDepartmentChart();
        List<Map> assetDamageLineChart = assetDao.assetDamageLineChart();
        Map result = new HashMap();
        result.put("assetDamageChangeChart",assetDamageChangeChart);
        result.put("assetDamageProfessionConChart",assetDamageProfessionChart);
        result.put("assetDamageDepartmentChart",assetDamageDepartmentChart);
        result.put("assetDamageLineChart",assetDamageLineChart);
        return result;
	}
}

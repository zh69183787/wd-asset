package com.wonders.asset.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;

import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.dao.StopAssetDao;
import com.wonders.asset.model.Asset;
import com.wonders.asset.model.AssetOwner;
import com.wonders.asset.model.AssetPrice;
import com.wonders.asset.model.AssetState;
import com.wonders.asset.model.Enterprise;
import com.wonders.asset.model.Project;
import com.wonders.asset.model.StopAsset;
import com.wonders.asset.model.UnitMaster;

/**
 * Created by HH on 2014/11/12.
 */
public class StopAssetDaoImpl extends BaseDaoImpl<StopAsset,String> implements StopAssetDao{
	private SessionFactory sessionFactory2;
	
	
    public SessionFactory getSessionFactory2() {
		return sessionFactory2;
	}

	public void setSessionFactory2(SessionFactory sessionFactory2) {
		this.sessionFactory2 = sessionFactory2;
	}

	public StopAssetDaoImpl() {
        super(StopAsset.class);
    }

    protected String getTableName(boolean isTem){
        return !isTem ? "T_STOP_ASSET D" : "T_STOP_ASSET_TMP T";
    }

    protected String getPk(){
        return "STOP_ASSET_ID";
    }

    protected StringBuffer getFields(){

        StringBuffer fields = new StringBuffer("ASSET_NO,CREATOR,ORGAN,STOP_REASON,STOP_DATE,START_REASON,START_DATE,NOTE,STATE,ID,LOAN_NO");   
        return  fields;
    }

    protected String getTmpPk(){
        return "ASSET_NO";
    }

	@Override
	public Pagination<StopAsset> findStopAndAsset(
			Map<String, String> filterMap, Map<String, String> sortMap,
			int startIndex, int pageSize) {
		String name = filterMap.get("asset.name_like");
		String assetCode = filterMap.get("asset.assetCode_like");
		String lineCodeId = filterMap.get("lineCodeId_equal");
		String station = filterMap.get("station_equal");
		String mainType = filterMap.get("mainType_equal");
		String subType = filterMap.get("subType_equal");
		String detailType = filterMap.get("detailType_equal");
		String projectNo = filterMap.get("projectNo_like");
		String useOrganization = filterMap.get("useOrganization_equal");
		String ownerOrganization = filterMap.get("ownerOrganization_equal");
		String assetDepartment = filterMap.get("assetDepartment_equal");
		String stopDateStart = filterMap.get("stopDateStart_equal");
		String stopDateEnd = filterMap.get("stopDateEnd_equal");
		
		String sql="SELECT L.SHORT_NAME,T.PROJECT_NO,T.ASSET_CODE,T.NAME,S.ORGAN,S.STOP_DATE," +
					"S.STOP_REASON,S.START_DATE,S.START_REASON,S.NOTE,T.ID," +
					"P.NET_VALUE,E.NAMEPLATE_SITE,T.EQUIPMENT_LIST,T.COMPLETE_TRANS_ASSET_TYPE,T.PROJECT_APP_DOC_NO,T.REMARKS" +
					",R.project_name,t.project_contract_no," +
					"(select name from t_asset_type where id = t.main_type_code_id)," +
					"(select name from t_asset_type where id = t.sub_type_code_id)," +
					"(select name from t_asset_type where id = t.detail_type_code_id)," +
					"(select name from t_organization where id = a.owner_org_code_id),t.count,t.type,t.manufacture_country," +
					"(SELECT NAME FROM T_ENTERPRISE WHERE T.MANUFACTURER_ID = ID AND TYPE = '2')," +
					"(SELECT NAME FROM T_ENTERPRISE WHERE T.MANUFACTURER_ID = ID AND TYPE = '1')," +
					"t.made_date,t.use_date,t.detail_install_site,a.owner_,a.user_," +
					"(select name from t_department where a.department_code_id = id)," +
					"(select name from t_station where id = a.station_code_id),t.ownership_per,t.use_per,a.GEGIN_USE_DATE," +
					"a.stop_use_date,t.approval_scrap_date,a.receive_date,e.state st,t.asset_pic,t.use_life," +
					"t.expectancy_life,t.warranty_period,t.overhaul_rate," +
					"T.FACTORY_PRICE,T.CONTRACT_PRICE,T.ORIGINAL_VALUE,T.DEPRECIATION_METHOD,P.ACCUMULATED_DEPRECIATION " +
					",U.NAME UNITNAME" +
					" FROM T_LINE L,T_STOP_ASSET S,T_ASSET T,T_ASSET_OWNER A,T_ASSET_PRICE P,T_ASSET_STATE E,T_PROJECT R,T_UNIT_MASTER U" +
				    " WHERE A.LINE_CODE_ID = L.CODE_ID AND T.ASSET_CODE = S.ASSET_NO AND a.id=t.asset_owner_info_id AND P.ASSET_ID = T.ID AND E.ASSET_ID = T.ID AND R.id = t.project_id AND U.ID = T.UNIT_ID" ;
		if(StringUtils.isNotBlank(station)){
			sql += " and A.STATION_CODE_ID = '"+station+"'";
		}
		if(StringUtils.isNotBlank(mainType)){
			sql += " and t.main_type_code_id = '"+mainType+"'";
		}
		if(StringUtils.isNotBlank(subType)){
			sql += " and t.sub_type_code_id = '"+subType+"'";
		}
		if(StringUtils.isNotBlank(detailType)){
			sql += " and t.detail_type_code_id = '"+detailType+"'";
		}
		if(StringUtils.isNotBlank(assetCode)){
			sql += " and t.asset_Code like '%"+assetCode+"%'";
		}
		if(StringUtils.isNotBlank(name)){
			sql += " and t.name like'%"+name+"%'";
		}
		if(StringUtils.isNotBlank(lineCodeId)){
			sql += " and a.line_code_id='"+lineCodeId+"'";
		}
		if(StringUtils.isNotBlank(projectNo)){
			sql += " and t.project_no like '%"+projectNo+"%'";
		}
		if(StringUtils.isNotBlank(useOrganization)){
			sql += " and A.USER_ORG_CODE_ID='"+useOrganization+"'";
		}
		if(StringUtils.isNotBlank(ownerOrganization)){
			sql += " and A.OWNER_ORG_CODE_ID='"+ownerOrganization+"'";
		}
		if(StringUtils.isNotBlank(assetDepartment)){
			sql += " and A.DEPARTMENT_CODE_ID='"+assetDepartment+"'";
		}
		if(StringUtils.isNotBlank(stopDateStart)){
			sql += " and s.stop_date>=to_date('"+stopDateStart+"','yyyy-mm-dd')";
		}
		if(StringUtils.isNotBlank(stopDateEnd)){
			sql += " and s.stop_date<=to_date('"+stopDateEnd+"','yyyy-mm-dd')";
		}
		
		SQLQuery query = sessionFactory2.getCurrentSession().createSQLQuery(sql);
		long totalCount = query.list().size();
		
		int pageNo = startIndex / pageSize + 1;
		int totalPages = (totalCount > 0) ? (int) Math.ceil((double) totalCount
				/ pageSize) : 1;

		if (startIndex < 0)
			startIndex = 0;
		if (startIndex > totalCount)
			startIndex = (totalPages - 1) * pageSize;
	    List<Object[]> list = sessionFactory2.getCurrentSession().createSQLQuery(sql).setFirstResult(startIndex).setMaxResults(pageSize).list();
		List<StopAsset> result = new ArrayList<StopAsset>();

		for(Object[] object : list){
 			StopAsset stopAsset = new StopAsset();
 			
 			AssetOwner assetOwner = new AssetOwner();
 			assetOwner.setLineCodeId((String)object[0]);
 			assetOwner.setOwnerOrganizationCodeId((String)object[22]);
 			assetOwner.setOwner((String)object[31]);
 			assetOwner.setUser((String)object[32]);
 			assetOwner.setDepartmentCodeId((String)object[33]);
 			assetOwner.setStationCodeId((String)object[34]);
 			assetOwner.setBeginUseDate((Date)object[37]);
 			assetOwner.setStopUseDate((Date)object[38]);
 			assetOwner.setReceiveDate((Date)object[40]);
 			stopAsset.setAssetOwner(assetOwner);
 			
 			Asset asset = new Asset();
 			asset.setProjectNo((String)object[1]);
 			asset.setAssetCode((String)object[2]);
 			asset.setName((String)object[3]);
 			asset.setId((String)object[10]);
 			asset.setEquipmentList((String)object[13]);
 			asset.setCompleteTransAssetType((String)object[14]);
 			asset.setProjectAppDocNo((String)object[15]);
 			asset.setRemarks((String)object[16]);
 				Project project = new Project();
	 			project.setProjectName((String)object[17]);
	 			asset.setProject(project);
	 		asset.setProjectContractNo((String)object[18]);
	 		asset.setMainTypeCodeId((String)object[19]);
	 		asset.setSubTypeCodeId((String)object[20]);
	 		asset.setDetailTypeCodeId((String)object[21]);
	 		if(object[23]!=null)
	 			asset.setCount(((BigDecimal)object[23]).doubleValue());
	 		asset.setType((String)object[24]);
	 		asset.setManufactureCountry((String)object[25]);
		 		Enterprise manufacturer = new Enterprise();
		 		manufacturer.setName((String)object[26]);//生产厂商
		 		manufacturer.setId((String)object[27]);//供应商
		 		asset.setManufacturer(manufacturer);
		 		
		 	asset.setMadeDate((Date)object[28]);
		 	asset.setUseDate((Date)object[29]);
		 	asset.setDetailInstallSite((String)object[30]);
		 	asset.setOwnershipPer((String)object[35]);
		 	asset.setUsePer((String)object[36]);
		 	asset.setApprovalScrapDate((Date)object[39]);
		 	asset.setAssetPic((String)object[42]);
		 	asset.setUseLife((String)object[43]);
		 	if(object[44]!=null)
		 		asset.setExpectancyLife(((BigDecimal)object[44]).longValueExact());
		 	asset.setWarrantyPeriod((Date)object[45]);
		 	asset.setOverhaulRate((String)object[46]);
		 	
		 	if(object[47]!=null)
 				asset.setFactoryPrice(((BigDecimal)object[47]).doubleValue());
 			if(object[48]!=null)
 				asset.setContractPrice(((BigDecimal)object[48]).doubleValue());
 			if(object[49]!=null)
 				asset.setOriginalValue(((BigDecimal)object[49]).doubleValue());
 			asset.setDepreciationMethod((String)object[50]);
 			
 			UnitMaster unit = new UnitMaster();
 			if((String)object[52]!=null)
 				unit.setName((String)object[52]);
 			asset.setUnit(unit);
 			
 			stopAsset.setAsset(asset);
 			
 			stopAsset.setOrgan((String)object[4]);
 			stopAsset.setStopDate((Date)object[5]);
 			stopAsset.setStopReason((String)object[6]);
 			stopAsset.setStartDate((Date)object[7]);
 			stopAsset.setStartReason((String)object[8]);
 			stopAsset.setNote((String)object[9]);
 			
 			AssetPrice assetPrice = new AssetPrice();
 			assetPrice.setNetValue((Double)object[11]);
 			if(object[51]!=null)
 				assetPrice.setAccumulatedDepreciation(((BigDecimal)object[51]).doubleValue());
 			stopAsset.setAssetPrice(assetPrice);
 			
 			AssetState assetState = new AssetState();
 			assetState.setNameplateSite((String)object[12]);
 			assetState.setState((String)object[41]);
 			stopAsset.setAssetState(assetState);
 			
 			result.add(stopAsset);
 		}
	    
	    return new Pagination<StopAsset>(pageNo, totalPages, totalCount, result);
	}
}

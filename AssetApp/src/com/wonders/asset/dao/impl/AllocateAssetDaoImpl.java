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
import com.wonders.asset.dao.AllocateAssetDao;
import com.wonders.asset.model.AllocateAsset;
import com.wonders.asset.model.Asset;
import com.wonders.asset.model.AssetOwner;
import com.wonders.asset.model.AssetPrice;
import com.wonders.asset.model.AssetState;
import com.wonders.asset.model.Enterprise;
import com.wonders.asset.model.Project;
import com.wonders.asset.model.UnitMaster;

/**
 * Created by HH on 2014/11/9.
 */
public class AllocateAssetDaoImpl extends BaseDaoImpl<AllocateAsset,String> implements AllocateAssetDao {
	private SessionFactory sessionFactory2;

    public SessionFactory getSessionFactory2() {
		return sessionFactory2;
	}

	public void setSessionFactory2(SessionFactory sessionFactory2) {
		this.sessionFactory2 = sessionFactory2;
	}

	public AllocateAssetDaoImpl() {
        super(AllocateAsset.class);
    }

    protected String getTableName(boolean isTem){
        return !isTem ? "T_ALLOCATE_ASSET D" : "T_ALLOCATE_ASSET_TMP T";
    }

    protected String getPk(){
        return "ALLOCATE_ASSET_ID";
    }

    protected StringBuffer getFields(){

        StringBuffer fields = new StringBuffer("ID,ASSET_NO,OUT_ORG,ORIGINAL_LOCAL_NO,ORIGINAL_USER,IN_ORG,NEW_LOACL_NO,NEW_USER,ALLOT_DATE,ALLOT_REASON,NOTE,LOAN_NO");
        return  fields;
    }

	@Override
	public Pagination<AllocateAsset> findAllocateAndAsset(
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
		
		String outOrg = filterMap.get("outOrg_equal");
		String inOrg = filterMap.get("inOrg_equal");
		String allotDateStart = filterMap.get("allotDateStart_equal");
		String allotDateEnd = filterMap.get("allotDateEnd_equal");
		
		String sql="SELECT L.SHORT_NAME,T.PROJECT_NO,T.ASSET_CODE,T.NAME,S.out_Org,S.in_Org,S.allot_Reason,S.allot_Date," +
					"S.note,T.ID,P.NET_VALUE,E.NAMEPLATE_SITE,T.EQUIPMENT_LIST,T.COMPLETE_TRANS_ASSET_TYPE,T.PROJECT_APP_DOC_NO,T.REMARKS" +
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
					"T.FACTORY_PRICE,T.CONTRACT_PRICE,T.ORIGINAL_VALUE,T.DEPRECIATION_METHOD,P.ACCUMULATED_DEPRECIATION" +
					",U.NAME unitName" +
					" FROM T_LINE L,T_ALLOCATE_ASSET S,T_ASSET T,T_ASSET_OWNER A,T_ASSET_PRICE P,T_ASSET_STATE E,T_PROJECT R,T_UNIT_MASTER U" +
					" WHERE A.LINE_CODE_ID = L.CODE_ID AND T.ASSET_CODE = S.ASSET_NO AND a.id=t.asset_owner_info_id AND P.ASSET_ID = T.ID AND E.ASSET_ID = T.ID AND R.id = t.project_id AND T.UNIT_ID = U.ID" ;
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
		if(StringUtils.isNotBlank(outOrg)){
			sql += " and A.DEPARTMENT_CODE_ID='"+outOrg+"'";
		}
		if(StringUtils.isNotBlank(inOrg)){
			sql += " and A.USER_ORG_CODE_ID='"+inOrg+"'";
		}
		if(StringUtils.isNotBlank(allotDateStart)){
			sql += " and s.allot_date>=to_date('"+allotDateStart+"','yyyy-mm-dd')";
		}
		if(StringUtils.isNotBlank(allotDateEnd)){
			sql += " and s.allot_date<=to_date('"+allotDateEnd+"','yyyy-mm-dd')";
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
		List<AllocateAsset> result = new ArrayList<AllocateAsset>();
		
		for(Object[] object : list){
			AllocateAsset allocateAsset = new AllocateAsset();
			
			AssetOwner assetOwner = new AssetOwner();
 			assetOwner.setLineCodeId((String)object[0]);
 			assetOwner.setOwnerOrganizationCodeId((String)object[21]);
 			assetOwner.setOwner((String)object[30]);
 			assetOwner.setUser((String)object[31]);
 			assetOwner.setDepartmentCodeId((String)object[32]);
 			assetOwner.setStationCodeId((String)object[33]);
 			assetOwner.setBeginUseDate((Date)object[36]);
 			assetOwner.setStopUseDate((Date)object[37]);
 			assetOwner.setReceiveDate((Date)object[39]);
 			allocateAsset.setAssetOwner(assetOwner);
 			
 			Asset asset = new Asset();
 			asset.setProjectNo((String)object[1]);
 			asset.setAssetCode((String)object[2]);
 			asset.setName((String)object[3]);
 			asset.setId((String)object[9]);
 			asset.setEquipmentList((String)object[12]);
 			asset.setCompleteTransAssetType((String)object[13]);
 			asset.setProjectAppDocNo((String)object[14]);
 			asset.setRemarks((String)object[15]);
	 			Project project = new Project();
	 			project.setProjectName((String)object[16]);
	 			asset.setProject(project);
	 		asset.setProjectContractNo((String)object[17]);
	 		asset.setMainTypeCodeId((String)object[18]);
	 		asset.setSubTypeCodeId((String)object[19]);
	 		asset.setDetailTypeCodeId((String)object[20]);
	 		if(object[22]!=null)
	 			asset.setCount(((BigDecimal)object[22]).doubleValue());
	 		asset.setType((String)object[23]);
	 		asset.setManufactureCountry((String)object[24]);
		 		Enterprise manufacturer = new Enterprise();
		 		manufacturer.setName((String)object[25]);//生产厂商
		 		manufacturer.setId((String)object[26]);//供应商
		 		asset.setManufacturer(manufacturer);
		 		
		 	asset.setMadeDate((Date)object[27]);
		 	asset.setUseDate((Date)object[28]);
		 	asset.setDetailInstallSite((String)object[29]);
		 	asset.setOwnershipPer((String)object[34]);
		 	asset.setUsePer((String)object[35]);
		 	asset.setApprovalScrapDate((Date)object[38]);
		 	asset.setAssetPic((String)object[41]);
		 	asset.setUseLife((String)object[42]);
		 	if(object[43]!=null)
		 		asset.setExpectancyLife(((BigDecimal)object[43]).longValueExact());
		 	asset.setWarrantyPeriod((Date)object[44]);
		 	asset.setOverhaulRate((String)object[45]);
		 	
		 	if(object[46]!=null)
 				asset.setFactoryPrice(((BigDecimal)object[46]).doubleValue());
 			if(object[47]!=null)
 				asset.setContractPrice(((BigDecimal)object[47]).doubleValue());
 			if(object[48]!=null)
 				asset.setOriginalValue(((BigDecimal)object[48]).doubleValue());
 			asset.setDepreciationMethod((String)object[49]);
 			
 			UnitMaster unit = new UnitMaster();
 			if((String)object[51]!=null)
 				unit.setName((String)object[51]);
 			asset.setUnit(unit);
 			
 			allocateAsset.setAsset(asset);
 			
 			allocateAsset.setOutOrg((String)object[4]);
 			allocateAsset.setInOrg((String)object[5]);
 			allocateAsset.setAllotReason((String)object[6]);
 			allocateAsset.setAllotDate((Date)object[7]);
 			allocateAsset.setNote((String)object[8]);
 		
 			
 			AssetPrice assetPrice = new AssetPrice();
 			assetPrice.setNetValue((Double)object[10]);
 			if(object[50]!=null)
 				assetPrice.setAccumulatedDepreciation(((BigDecimal)object[50]).doubleValue());
 			allocateAsset.setAssetPrice(assetPrice);
 			
 			AssetState assetState = new AssetState();
 			assetState.setNameplateSite((String)object[11]);
 			assetState.setState((String)object[40]);
 			allocateAsset.setAssetState(assetState);
 			
 			result.add(allocateAsset);
 		}
	    
	    return new Pagination<AllocateAsset>(pageNo, totalPages, totalCount, result);
	}

}

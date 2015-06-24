package com.wonders.asset.dao.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;

import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.dao.BorrowAssetDao;
import com.wonders.asset.model.Asset;
import com.wonders.asset.model.AssetOwner;
import com.wonders.asset.model.AssetPrice;
import com.wonders.asset.model.AssetState;
import com.wonders.asset.model.BorrowAsset;
import com.wonders.asset.model.Enterprise;
import com.wonders.asset.model.Project;
import com.wonders.asset.model.UnitMaster;

public class BorrowAssetDaoImpl extends BaseDaoImpl<BorrowAsset, String> implements BorrowAssetDao{
	private SessionFactory sessionFactory2;
	public BorrowAssetDaoImpl() {
		super(BorrowAsset.class);
	}
	
	protected String getTableName(boolean isTem){
        return !isTem ? "T_BORROW_ASSET D" : "T_BORROW_ASSET_TMP T";
    }

    protected String getPk(){
        return "BORROW_ASSET_ID";
    }

    
    public SessionFactory getSessionFactory2() {
		return sessionFactory2;
	}

	public void setSessionFactory2(SessionFactory sessionFactory2) {
		this.sessionFactory2 = sessionFactory2;
	}

	protected StringBuffer getFields(){

        StringBuffer fields = new StringBuffer("ASSET_NO,LOAN_ORG,LOAN_DATE,");
        fields.append("LOAN_REASON,BORR_ORG,BORROWER,RETURN_BACK,RETURN_PERSON,RETURN_DATE,NOTE,STATE,ID,BUSINESS_TYPE,LOAN_NO");   
        return  fields;
    }

    public static void main(String[] args) {
//        DamageAssetDaoImpl impl = new DamageAssetDaoImpl();
//        System.out.println((Annotation)impl.getPersistentClass().getAnnotations()[0]);
    }

	@Override
	public Pagination<BorrowAsset> findBorrowAndAsset(Map<String, String> filterMap,
			Map<String, String> sortMap, int startIndex, int pageSize) {
		String name = filterMap.get("asset.name_like");
		String assetCode = filterMap.get("asset.assetCode_like");
		String lineCodeId = filterMap.get("lineCodeId_equal");
		String station = filterMap.get("station_equal");
		String mainType = filterMap.get("mainType_equal");
		String subType = filterMap.get("subType_equal");
		String detailType = filterMap.get("detailType_equal");
		String projectNo = filterMap.get("projectNo_like");
		String businessType = filterMap.get("businessType_equal");
		String loanOrg = filterMap.get("loanOrg_equal");
		String borrOrg = filterMap.get("borrOrg_equal");
		String loanDateStart = filterMap.get("loanDateStart_equal");
		String loanDateEnd = filterMap.get("loanDateEnd_equal");
/*		String sql = "select b.business_type,L.SHORT_NAME,t.project_no,t.asset_code," +
					 "t.name,b.loan_org,b.borr_org,b.loan_date,b.state,b.return_date,t.id, " +
					 "P.NET_VALUE,E.NAMEPLATE_SITE,T.EQUIPMENT_LIST,T.COMPLETE_TRANS_ASSET_TYPE,T.PROJECT_APP_DOC_NO,T.REMARKS " +
					 ",(select name from t_asset_type where id = t.main_type_code_id)"+
					 "FROM T_LINE L,t_borrow_asset B,T_ASSET T,T_ASSET_OWNER A,T_ASSET_PRICE P,T_ASSET_STATE E " +
					 "WHERE A.LINE_CODE_ID = L.CODE_ID AND T.ASSET_CODE = B.ASSET_NO AND a.id=t.asset_owner_info_id AND P.ASSET_ID = T.ID AND E.ASSET_ID = T.ID ";
*/
		String sql = "select b.business_type,L.SHORT_NAME,t.project_no,t.asset_code,t.name,b.loan_org,b.borr_org," +
				"b.loan_date,b.state,b.return_date,t.id,P.NET_VALUE,E.NAMEPLATE_SITE,T.EQUIPMENT_LIST," +
				"T.COMPLETE_TRANS_ASSET_TYPE,T.PROJECT_APP_DOC_NO,T.REMARKS" +
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
				" FROM T_LINE L,t_borrow_asset B,T_ASSET T,T_ASSET_OWNER A,T_ASSET_PRICE P,T_ASSET_STATE E,T_PROJECT R,T_UNIT_MASTER U" +
				" WHERE A.LINE_CODE_ID = L.CODE_ID AND T.ASSET_CODE = B.ASSET_NO AND a.id = t.asset_owner_info_id" +
				" AND P.ASSET_ID = T.ID AND E.ASSET_ID = T.ID AND R.id = t.project_id AND U.ID = T.UNIT_ID";
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
		if(StringUtils.isNotBlank(businessType)){
			sql += " and b.business_type='"+businessType+"'";
		}
		if(StringUtils.isNotBlank(loanOrg)){
			sql += " and A.DEPARTMENT_CODE_ID='"+loanOrg+"'";
		}
		if(StringUtils.isNotBlank(borrOrg)){
			sql += " and A.USER_ORG_CODE_ID='"+borrOrg+"'";
		}
		if(StringUtils.isNotBlank(loanDateStart)){
			sql += " and b.loan_date>=to_date('"+loanDateStart+"','yyyy-mm-dd')";
		}
		if(StringUtils.isNotBlank(loanDateEnd)){
			sql += " and b.loan_date<=to_date('"+loanDateEnd+"','yyyy-mm-dd')";
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
		List<BorrowAsset> result = new ArrayList<BorrowAsset>();
		for(Object[] object : list){
 			BorrowAsset borrowAsset = new BorrowAsset();
 			borrowAsset.setBusinessType((String)object[0]);
 
 			AssetOwner assetOwner = new AssetOwner();
 			assetOwner.setLineCodeId((String)object[1]);
 			assetOwner.setOwnerOrganizationCodeId((String)object[22]);
 			assetOwner.setOwner((String)object[31]);
 			assetOwner.setUser((String)object[32]);
 			assetOwner.setDepartmentCodeId((String)object[33]);
 			assetOwner.setStationCodeId((String)object[34]);
 			assetOwner.setBeginUseDate((Date)object[37]);
 			assetOwner.setStopUseDate((Date)object[38]);
 			assetOwner.setReceiveDate((Date)object[40]);
 			borrowAsset.setAssetOwner(assetOwner);
 			
 			
 			
 			Asset asset = new Asset();
 			asset.setProjectNo((String)object[2]);
 			asset.setAssetCode((String)object[3]);
 			asset.setName((String)object[4]);
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
 			
 			borrowAsset.setAsset(asset);
 			
 			borrowAsset.setLoanOrg((String)object[5]);
 			borrowAsset.setBorrOrg((String)object[6]);
 		
 			borrowAsset.setLoanDate((Date)object[7]);
 			borrowAsset.setState((String)object[8]);
 			borrowAsset.setReturnDate((Date)object[9]);
 			
 			AssetPrice assetPrice = new AssetPrice();
 			if(object[11]!=null)
 				assetPrice.setNetValue(((BigDecimal)object[11]).doubleValue());
 			if(object[51]!=null)
 				assetPrice.setAccumulatedDepreciation(((BigDecimal)object[51]).doubleValue());
 			borrowAsset.setAssetPrice(assetPrice);
 			
 			AssetState assetState = new AssetState();
 			assetState.setNameplateSite((String)object[12]);
 			assetState.setState((String)object[41]);
 			borrowAsset.setAssetState(assetState);
 			
 			result.add(borrowAsset);
 		}
	    
	    return new Pagination<BorrowAsset>(pageNo, totalPages, totalCount, result);
	}


}

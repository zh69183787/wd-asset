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
import com.wonders.asset.dao.DamageAssetDao;
import com.wonders.asset.model.Asset;
import com.wonders.asset.model.AssetOwner;
import com.wonders.asset.model.AssetPrice;
import com.wonders.asset.model.AssetState;
import com.wonders.asset.model.DamageAsset;
import com.wonders.asset.model.Enterprise;
import com.wonders.asset.model.Project;
import com.wonders.asset.model.UnitMaster;

/**
 * Created by Administrator on 2014/11/6.
 */
public class DamageAssetDaoImpl extends BaseDaoImpl<DamageAsset, String> implements DamageAssetDao {
	private SessionFactory sessionFactory2;

    public SessionFactory getSessionFactory2() {
		return sessionFactory2;
	}

	public void setSessionFactory2(SessionFactory sessionFactory2) {
		this.sessionFactory2 = sessionFactory2;
	}

	public DamageAssetDaoImpl() {
        super(DamageAsset.class);
    }

    protected String getTableName(boolean isTem){
        return !isTem ? "T_DAMAGE_ASSET D" : "T_DAMAGE_ASSET_TMP T";
    }

    protected String getPk(){
        return "DAMAGE_ASSET_ID";
    }

    protected String getTmpPk(){  
        return "ASSET_NO";
    }

    protected StringBuffer getFields(){

        StringBuffer fields = new StringBuffer("ASSET_NO,");//DAMAGE_VALUE,RESIDUAL_VALUE,
        fields.append("DEPRECIATIONED,USER_YEAR,REQU_DATE,SCRAP_DATE,SCRAP_REASON,SUB_ASSET,APPLY_ORG,LOAN_NO,");
        fields.append("APPLY_DO_PERSON,APPLY_DO_DATE,APPLY_OPINION,REPLY_OPINION,REPLY_DATE,NOTE,ID,ACCUMULATED_DEPRECIATION,NET_VALUE");
        return  fields;
    }

	@Override
	public Pagination<DamageAsset> findDamageAndAsset(
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
		/**
		 * 报废类别(已报废)
		 * 0 正常报废
		 * 1 提前报废
		 * 2 延时报废
		 */
		
		/**
		 * 报废类别(未报废)
		 * 0 计划今年报废
		 * 1 超期服役
		 */
		String damageType = filterMap.get("damageType_equal");
		String damageType1 = filterMap.get("damageTypes_equal");
		
		String approvalScrapDateStart = filterMap.get("approvalScrapDateStart_equal");
		String approvalScrapDateEnd = filterMap.get("approvalScrapDateEnd_equal");
		
		String damageNo = filterMap.get("damageNo_equal");
		String sql = "";
		
		if("1".equals(damageNo)){
			//已报废
			sql +="SELECT L.SHORT_NAME,T.PROJECT_NO,T.ASSET_CODE,T.NAME," +
					"T.USE_LIFE,ceil(months_between(trunc(T.APPROVAL_SCRAP_DATE,'MONTH'),TRUNC(A.GEGIN_USE_DATE,'MONTH'))/12),S.USE_STATE," +
					"D.SCRAP_REASON," +
					"(CASE" +
					" WHEN not regexp_like(T.USE_LIFE, \'^(\\+)?[[:digit:]]+$\') THEN '使用年限不为数字'" +
					" WHEN T.APPROVAL_SCRAP_DATE IS NULL THEN '计划使用年限为空'" +
					" WHEN trunc(A.GEGIN_USE_DATE + T.USE_LIFE*365,'yy') = trunc(T.APPROVAL_SCRAP_DATE,'YY') THEN '正常报废'" +
					" WHEN trunc(A.GEGIN_USE_DATE + T.USE_LIFE*365,'yy') < trunc(T.APPROVAL_SCRAP_DATE,'YY') THEN '延时报废'" +
					" WHEN trunc(A.GEGIN_USE_DATE + T.USE_LIFE*365,'yy') > trunc(T.APPROVAL_SCRAP_DATE,'YY') THEN '提前报废'" +
//					" WHEN to_char(A.GEGIN_USE_DATE +  T.USE_LIFE*365,'yyyy') = TO_CHAR(T.APPROVAL_SCRAP_DATE,'YYYY') THEN '正常报废'" +
//					" WHEN to_char(A.GEGIN_USE_DATE +  T.USE_LIFE*365,'yyyy') < TO_CHAR(T.APPROVAL_SCRAP_DATE,'YYYY') THEN '延时报废'" +
//					" WHEN to_char(A.GEGIN_USE_DATE +  T.USE_LIFE*365,'yyyy') > TO_CHAR(T.APPROVAL_SCRAP_DATE,'YYYY') THEN '提前报废'" +					
					" ELSE '异常数据' END) 类别,D.NOTE," +
					"P.NET_VALUE,S.NAMEPLATE_SITE,T.EQUIPMENT_LIST," +
					"T.COMPLETE_TRANS_ASSET_TYPE,T.PROJECT_APP_DOC_NO,T.REMARKS," +
					"T.FACTORY_PRICE," +
					"T.CONTRACT_PRICE,T.ORIGINAL_VALUE,T.DEPRECIATION_METHOD," +
					"P.ACCUMULATED_DEPRECIATION," +
					"R.project_name,t.project_contract_no," +
					"(select name from t_asset_type where id = t.main_type_code_id)," +
					"(select name from t_asset_type where id = t.sub_type_code_id)," +
					"(select name from t_asset_type where id = t.detail_type_code_id)," +
					"(select name from t_organization where id = a.owner_org_code_id)," +
					"t.count,t.type,t.manufacture_country," +
					"(SELECT NAME FROM T_ENTERPRISE WHERE T.MANUFACTURER_ID = ID  AND TYPE = '2')," +
					"(SELECT NAME FROM T_ENTERPRISE WHERE T.MANUFACTURER_ID = ID AND TYPE = '1')," +
					"t.made_date,t.use_date,t.detail_install_site,a.owner_,a.user_," +
					"(select name from t_department where a.department_code_id = id)," +
					"(select name from t_station where id = a.station_code_id)," +
					"t.ownership_per,t.use_per,a.GEGIN_USE_DATE,a.stop_use_date,T.APPROVAL_SCRAP_DATE," +
					"a.receive_date,S.state st,t.asset_pic," +
					"t.expectancy_life,t.warranty_period,t.overhaul_rate," +
					"t.id,U.NAME UNITNAME" +
					" FROM T_LINE L,T_ASSET T,T_DAMAGE_ASSET D,T_ASSET_OWNER A,T_ASSET_STATE S," +
					"T_PROJECT R,T_ASSET_PRICE P,T_UNIT_MASTER U" +
					" WHERE T.ASSET_CODE = D.ASSET_NO" +
					" AND A.LINE_CODE_ID = L.CODE_ID" +
					" AND T.ASSET_OWNER_INFO_ID = A.ID" +
					" AND T.STATE_ID = S.ID" +
					" AND S.STATE = '3'" +
					" AND R.ID = T.PROJECT_ID" +
					" AND P.ASSET_ID = T.ID" +
					" AND R.ID = T.PROJECT_ID" +
					" AND T.UNIT_ID = U.ID";
			if(StringUtils.isNotBlank(approvalScrapDateStart)){
				sql += " and T.APPROVAL_SCRAP_DATE>=to_date('"+approvalScrapDateStart+"','yyyy-mm-dd')";
			}
			if(StringUtils.isNotBlank(approvalScrapDateEnd)){
				sql += " and T.APPROVAL_SCRAP_DATE<=to_date('"+approvalScrapDateEnd+"','yyyy-mm-dd')";
			}
			if(StringUtils.isNotBlank(damageType)){
				if(damageType.equals("0")){
					sql += " and trunc(A.GEGIN_USE_DATE +  T.USE_LIFE*365,'yy') = trunc(T.APPROVAL_SCRAP_DATE,'yy')";
				}else if(damageType.equals("1")){
					sql += " and trunc(A.GEGIN_USE_DATE +  T.USE_LIFE*365,'yy') > trunc(T.APPROVAL_SCRAP_DATE,'yy')";
				}else{
					sql += " and trunc(A.GEGIN_USE_DATE +  T.USE_LIFE*365,'yy') < trunc(T.APPROVAL_SCRAP_DATE,'yy')";
				}
			}
		}else if("2".equals(damageNo)){
			//未报废
			sql +="SELECT L.SHORT_NAME,T.PROJECT_NO,T.ASSET_CODE,T.NAME," +
					"T.USE_LIFE,ceil(months_between(trunc(SYSDATE,'MONTH'),TRUNC(A.GEGIN_USE_DATE,'MONTH'))/12),S.USE_STATE," +
					"D.SCRAP_REASON," +
					"(CASE" +
					" WHEN not regexp_like(T.USE_LIFE, \'^(\\+)?[[:digit:]]+$\') THEN '使用年限不为数字'" +
					" WHEN trunc(A.GEGIN_USE_DATE + T.USE_LIFE*365,'yy') = trunc(SYSDATE,'yy') THEN '计划今年报废'" +
					" WHEN trunc(A.GEGIN_USE_DATE + T.USE_LIFE*365,'yy') < trunc(SYSDATE,'yy') THEN '超期服役'" +
					" ELSE '正常使用' END) 类别,"+
					"D.NOTE," +
					"P.NET_VALUE,S.NAMEPLATE_SITE,T.EQUIPMENT_LIST," +
					"T.COMPLETE_TRANS_ASSET_TYPE,T.PROJECT_APP_DOC_NO,T.REMARKS," +
					"T.FACTORY_PRICE," +
					"T.CONTRACT_PRICE,T.ORIGINAL_VALUE,T.DEPRECIATION_METHOD," +
					"P.ACCUMULATED_DEPRECIATION," +
					"R.project_name,t.project_contract_no," +
					"(select name from t_asset_type where id = t.main_type_code_id)," +
					"(select name from t_asset_type where id = t.sub_type_code_id)," +
					"(select name from t_asset_type where id = t.detail_type_code_id)," +
					"(select name from t_organization where id = a.owner_org_code_id)," +
					"t.count,t.type,t.manufacture_country," +
					"(SELECT NAME FROM T_ENTERPRISE WHERE T.MANUFACTURER_ID = ID  AND TYPE = '2')," +
					"(SELECT NAME FROM T_ENTERPRISE WHERE T.MANUFACTURER_ID = ID AND TYPE = '1')," +
					"t.made_date,t.use_date,t.detail_install_site,a.owner_,a.user_," +
					"(select name from t_department where a.department_code_id = id)," +
					"(select name from t_station where id = a.station_code_id)," +
					"t.ownership_per,t.use_per,a.GEGIN_USE_DATE,a.stop_use_date,T.APPROVAL_SCRAP_DATE," +
					"a.receive_date,S.state st,t.asset_pic," +
					"t.expectancy_life,t.warranty_period,t.overhaul_rate," +
					"t.id,U.NAME UNITNAME" +
					" FROM T_LINE L,T_ASSET T,T_DAMAGE_ASSET D,T_ASSET_OWNER A,T_ASSET_STATE S," +
					"T_PROJECT R,T_ASSET_PRICE P,T_UNIT_MASTER U" +
					" WHERE T.ASSET_CODE = D.ASSET_NO" +
					" AND A.LINE_CODE_ID = L.CODE_ID" +
					" AND T.ASSET_OWNER_INFO_ID = A.ID" +
					" AND T.STATE_ID = S.ID" +
					" AND S.STATE <> '3'" +
					" AND R.ID = T.PROJECT_ID" +
					" AND P.ASSET_ID = T.ID" +
					" AND T.UNIT_ID = U.ID";
			if(StringUtils.isNotBlank(damageType1)){
				if(damageType1.equals("0")){
					sql += " and trunc(A.GEGIN_USE_DATE + T.USE_LIFE*365,'yy') = trunc(SYSDATE,'yy')";
				}else{
					sql += " and trunc(A.GEGIN_USE_DATE + T.USE_LIFE*365,'yy') < trunc(SYSDATE,'yy')";
				}
			}
		}
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
			sql += " and R.project_name like '%"+projectNo+"%'";
		}
		if(StringUtils.isNotBlank(useOrganization)){
			sql += " and A.USER_ORG_CODE_ID='"+useOrganization+"'";
		}
		if(StringUtils.isNotBlank(ownerOrganization)){
			sql += " and A.OWNER_ORG_CODE_ID='"+ownerOrganization+"'";
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
		List<DamageAsset> result = new ArrayList<DamageAsset>();
		for(Object[] object:list){
			//自身有十个
			DamageAsset damageAsset = new DamageAsset();
			
			AssetOwner assetOwner = new AssetOwner();
 			assetOwner.setLineCodeId((String)object[0]);
 			assetOwner.setOwnerOrganizationCodeId((String)object[26]);
 			assetOwner.setOwner((String)object[35]);
 			assetOwner.setUser((String)object[36]);
 			assetOwner.setDepartmentCodeId((String)object[37]);
 			assetOwner.setStationCodeId((String)object[38]);
 			assetOwner.setBeginUseDate((Date)object[41]);
 			assetOwner.setStopUseDate((Date)object[42]);
 			assetOwner.setReceiveDate((Date)object[44]);
 			damageAsset.setAssetOwner(assetOwner);
 			
 			Asset asset = new Asset();
 			asset.setProjectNo((String)object[1]);
 			asset.setAssetCode((String)object[2]);
 			asset.setName((String)object[3]);
 			asset.setUseLife((String)object[4]);
// 			asset.setId((String)object[8]);
 			asset.setEquipmentList((String)object[12]);
 			asset.setCompleteTransAssetType((String)object[13]);
 			asset.setProjectAppDocNo((String)object[14]);
 			asset.setRemarks((String)object[15]);
 			if(object[15]!=null)
 				asset.setFactoryPrice(((BigDecimal)object[16]).doubleValue());
 			if(object[16]!=null)
 				asset.setContractPrice(((BigDecimal)object[17]).doubleValue());
 			if(object[17]!=null)
 				asset.setOriginalValue(((BigDecimal)object[18]).doubleValue());
 			asset.setDepreciationMethod((String)object[19]);
 			
	 			Project project = new Project();
	 			project.setProjectName((String)object[21]);
	 			asset.setProject(project);
	 		asset.setProjectContractNo((String)object[22]);
	 		asset.setMainTypeCodeId((String)object[23]);
	 		asset.setSubTypeCodeId((String)object[24]);
	 		asset.setDetailTypeCodeId((String)object[25]);
	 		if(object[27]!=null)
	 			asset.setCount(((BigDecimal)object[27]).doubleValue());
	 		asset.setType((String)object[28]);
	 		asset.setManufactureCountry((String)object[29]);
		 		Enterprise manufacturer = new Enterprise();
		 		manufacturer.setName((String)object[30]);//生产厂商
		 		manufacturer.setId((String)object[31]);//供应商
		 		asset.setManufacturer(manufacturer);
		 		
		 	asset.setMadeDate((Date)object[32]);
		 	asset.setUseDate((Date)object[33]);
		 	asset.setDetailInstallSite((String)object[34]);
		 	asset.setOwnershipPer((String)object[39]);
		 	asset.setUsePer((String)object[40]);
		 	asset.setApprovalScrapDate((Date)object[43]);
		 	asset.setAssetPic((String)object[46]);
		 	if(object[47]!=null)
		 		asset.setExpectancyLife(((BigDecimal)object[47]).longValueExact());
		 	asset.setWarrantyPeriod((Date)object[48]);
		 	asset.setOverhaulRate((String)object[49]);
		 	asset.setId((String)object[50]);
		 	
		 	UnitMaster unit = new UnitMaster();
 			if((String)object[51]!=null)
 				unit.setName((String)object[51]);
 			asset.setUnit(unit);
 			
 			damageAsset.setAsset(asset);
 		
 			//已使用年限object[5]
 			//类别object[8]
 			if(object[5]!=null)
 				damageAsset.setUseYear(((BigDecimal)object[5]).toString());
 			damageAsset.setDamageType((String)object[8]);
 			damageAsset.setScrapReason((String)object[7]);
 			damageAsset.setNote((String)object[9]);
 			
 			AssetPrice assetPrice = new AssetPrice();
 			if(object[10]!=null)
 				assetPrice.setNetValue(((BigDecimal)object[10]).doubleValue());
 			if(object[20]!=null)
 				assetPrice.setAccumulatedDepreciation(((BigDecimal)object[20]).doubleValue());
 			damageAsset.setAssetPrice(assetPrice);
 			
 			AssetState assetState = new AssetState();
 			//资产状态
 			assetState.setUseState((String)object[6]);
 			assetState.setNameplateSite((String)object[11]);
 			assetState.setState((String)object[45]);
 			damageAsset.setAssetState(assetState);
 			
 			result.add(damageAsset);
		}
		return new Pagination<DamageAsset>(pageNo, totalPages, totalCount, result);
	}   

}

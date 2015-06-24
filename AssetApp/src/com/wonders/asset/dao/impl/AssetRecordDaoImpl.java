package com.wonders.asset.dao.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;

import com.wonders.api.dto.AssetRecordDto;
import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.dao.AssetRecordDao;
import com.wonders.asset.model.Asset;
import com.wonders.asset.model.AssetOwner;
import com.wonders.asset.model.AssetPrice;
import com.wonders.asset.model.AssetRecord;
import com.wonders.asset.model.AssetState;
import com.wonders.asset.model.AssetType;
import com.wonders.asset.model.Enterprise;
import com.wonders.asset.model.Line;
import com.wonders.asset.model.Organization;
import com.wonders.asset.model.Project;
import com.wonders.asset.model.UnitMaster;

public class AssetRecordDaoImpl extends BaseDaoImpl<AssetRecord, String> implements AssetRecordDao {

	private SessionFactory sessionFactory2;
	
	public SessionFactory getSessionFactory2() {
		return sessionFactory2;
	}

	public void setSessionFactory2(SessionFactory sessionFactory2) {
		this.sessionFactory2 = sessionFactory2;
	}
	
	public AssetRecordDaoImpl() {
		super(AssetRecord.class);
	}

	@Override
	public void saveOrUpdate(AssetRecord assetRecord) {
		getHibernateTemplate().saveOrUpdate(assetRecord);
	}

	@Override
	public AssetRecord findAssetRecordById(String id) {
//		return (AssetRecord) getHibernateTemplate().get(AssetRecord.class, id);
		String hql = "from AssetRecord t where t.id = ?";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, id);
		return (AssetRecord) query.list().get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pagination findAssetRecordByProjectAppNo(
			String projectAppNo, Integer pageSize, Integer currentPageNo) {
		if(pageSize==null || pageSize==0){
			pageSize = 10;
		}
		if(currentPageNo==null || currentPageNo==0){
			currentPageNo = 1;
		}
		String condition="";
        String sql="select a.asset_code,a.name,a.operate_project_asset,a.operate_project_asset_date,a.original_value,p.net_value,"
        	+ "a.use_life,r.maintain_cost,r.asset_type,r.id,a.COMPLETE_TRANS_ASSET_TYPE,a.ASSET_CODE_TYPE "
        	+ "from t_asset_record r left outer join t_asset a on a.id=r.asset_id left outer join t_asset_price p on a.id=p.asset_id "
        	+ "where 1=1 ";
                
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(projectAppNo)) {
            condition = " and r.project_app_no like :projectAppNo";
            map.put("projectAppNo","%"+projectAppNo+"%");
        }
        sql+=condition;

        List<Object[]> result =  getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).setProperties(map).setFirstResult((currentPageNo-1)*pageSize).setMaxResults(currentPageNo*pageSize).list();
        Integer totalCount =((BigDecimal) getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery("select count(1) from ("+sql+")").setProperties(map).uniqueResult()).intValue();

        int totalPages = (totalCount.intValue() > 0) ? (int) Math.ceil((double) totalCount.intValue()  / pageSize) : 1;
        List<AssetRecordDto>  list=new ArrayList<AssetRecordDto>();
        for(Object[] o:result){
            AssetRecordDto dto = new AssetRecordDto();
            dto.setAssetNo((String)o[0]);
            dto.setAssetName((String)o[1]);
            dto.setOperateProjectAsset((String)o[2]);
            dto.setOperateProjectAssetDate((Timestamp)o[3]);
            dto.setOriginalValue((BigDecimal)o[4]);
            dto.setNetValue((BigDecimal)o[5]);
            dto.setUseLife((String)o[6]);
            dto.setMaintainCost((String)o[7]);
            dto.setAssetType((String)o[8]);
            dto.setId((String)o[9]);
            dto.setCompleteTransAssetType((String)o[10]);
            if(o[11]!=null)
            	dto.setAssetCodeType(((Character)o[11]).toString());
            list.add(dto);
        }

		Pagination pagination = new Pagination(currentPageNo, totalPages,
				totalCount.intValue(), list);
			return pagination;
	    }

		protected String getTableName(boolean isTem) {
			return !isTem ? "T_ASSET_RECORD D" : "T_ASSET_RECORD_TMP T";
		}

		protected String getPk() {
			return "ID";
		}

		protected String getTmpPk() {
			return "ASSET_NO";
		}

		protected StringBuffer getFields() {
			StringBuffer fields = new StringBuffer("ID,PROJECT_NAME,MAINTAIN_CONTENT,MAINTAIN_COST,");  
			fields.append("ASSET_TYPE,PROJECT_APP_NO,FINISH_DATE,FINISH_PRICE,ASSET_NO");     
			return fields;
		}
		
		public void updateAssetCodeType(String assetCodeType,List<String> assetNoList){
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE T_ASSET D SET ASSET_CODE_TYPE = :assetCodeType WHERE D.ID IN :assetNoList AND (D.ASSET_CODE_TYPE <>:assetCodeType OR D.ASSET_CODE_TYPE IS NULL)");
			System.out.println(sql.toString());
			getSession().createSQLQuery(sql.toString()).setParameter("assetCodeType",assetCodeType).setParameterList("assetNoList",assetNoList).executeUpdate();
		}
		
		public void batchUpdate() {
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE T_ASSET_RECORD D");
			sql.append("   SET (ID,PROJECT_NAME,MAINTAIN_CONTENT,MAINTAIN_COST,ASSET_TYPE,PROJECT_APP_NO,FINISH_DATE,FINISH_PRICE,ASSET_ID,ASSET_RECORD_ID) = ");
			sql.append("       (SELECT SYS_GUID(),PROJECT_NAME,MAINTAIN_CONTENT,MAINTAIN_COST,ASSET_TYPE,PROJECT_APP_NO,FINISH_DATE,FINISH_PRICE,(select distinct ID from t_asset where asset_code = ASSET_NO),ID");
			sql.append("         FROM (SELECT *");
			sql.append("                 FROM (SELECT T.*,");
			sql.append("                             ROW_NUMBER() OVER(PARTITION BY ID ORDER BY UPLOAD_DATE DESC) AS NUM_");
			sql.append("                          FROM T_ASSET_RECORD_TMP T) T");
			sql.append("                 WHERE NUM_ = 1) T");
			sql.append("         WHERE T.ID = D.ASSET_RECORD_ID)");
			sql.append(" 	 WHERE EXISTS (SELECT 1 FROM T_ASSET_RECORD_TMP T WHERE T.ID = D.ASSET_RECORD_ID)");
			System.out.println(sql.toString());
			getSession().createSQLQuery(sql.toString()).executeUpdate();  
		}

		public void batchInsert() {
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO t_asset_record D");
			sql.append(" ( ");
			sql.append(" ID,PROJECT_NAME,MAINTAIN_CONTENT,MAINTAIN_COST,ASSET_TYPE,PROJECT_APP_NO,FINISH_DATE,FINISH_PRICE,ASSET_ID,ASSET_RECORD_ID)");
			sql.append("  SELECT SYS_GUID(),PROJECT_NAME,MAINTAIN_CONTENT,MAINTAIN_COST,ASSET_TYPE,PROJECT_APP_NO,FINISH_DATE,FINISH_PRICE,(select distinct ASSET_ID from t_asset where asset_no = ASSET_NO),ID");
			sql.append(" FROM (SELECT *");
			sql.append(" FROM (SELECT T.*,");
			sql.append("ROW_NUMBER() OVER(PARTITION BY ID ORDER BY UPLOAD_DATE DESC) AS NUM_");  
			sql.append(" FROM t_asset_record_TMP T) T");
			sql.append(" WHERE NUM_ = 1");
			sql.append(" AND NOT EXISTS");
			sql.append("(SELECT 1 FROM t_asset_record D WHERE D.ASSET_RECORD_ID = T.ID))");  

			System.out.println(sql.toString());
			getSession().createSQLQuery(sql.toString()).executeUpdate();
			
//			
//			StringBuffer sql = new StringBuffer();
//			sql.append("insert into t_asset_record t(ID,PROJECT_NAME,MAINTAIN_CONTENT,MAINTAIN_COST,ASSET_TYPE,PROJECT_APP_NO,FINISH_DATE,FINISH_PRICE,ASSET_ID)");
//			sql.append("select SYS_GUID(),PROJECT_NAME,MAINTAIN_CONTENT,MAINTAIN_COST,ASSET_TYPE,PROJECT_APP_NO,FINISH_DATE,FINISH_PRICE,(select ASSET_ID from t_asset_record where asset_no = 'ASSET_NO')");
//			sql.append("from t_asset_record_tmp");
//
//			System.out.println(sql.toString());
//			getSession().createSQLQuery(sql.toString()).executeUpdate();
		}
		/*
		 * 大修更新改造DAO
		 * (non-Javadoc)
		 * @see com.wonders.asset.dao.AssetRecordDao#findRecordAndAsset(java.util.Map, java.util.Map, int, int)
		 */
		public Pagination<AssetRecord> findRecordAndAsset(Map<String, String> filterMap,
				Map<String, String> sortMap, int startIndex, int pageSize){
			
			String assetCode = filterMap.get("assetCode_like"); //资产编码 模糊
			String name = filterMap.get("name_like");//资产名称 模糊
			String lineCodeId = filterMap.get("lineCodeId_equal");
			String station = filterMap.get("station_equal");
			String mainType = filterMap.get("mainType_equal");
			String subType = filterMap.get("subType_equal");
			String detailType = filterMap.get("detailType_equal");
			String projectNo = filterMap.get("projectNo_like");//初始项目  模糊
			String completeTransAssetType = filterMap.get("completeTransAssetType_equal");//竣工移交标识
			String departmentId = filterMap.get("departmentId_equal");
			String useOrganization = filterMap.get("useOrganization_equal");
			String ownerOrganization = filterMap.get("ownerOrganization_equal");
			String projectName = filterMap.get("projectName_like");
			String finishDateStart = filterMap.get("finishDateStart_equal");
			String finishDateEnd = filterMap.get("finishDateEnd_equal");
			
			String recordNo = filterMap.get("recordNo_equal");
			
			
			String sql = "";
			if("1".equals(recordNo)){
				sql += " select l.short_name,r.project_name pname,t.asset_code,t.name ,d.project_name,"+
					" d.asset_type,d.project_app_no,d.finish_date,d.finish_price,t.id," +
					" t.contract_id ," +
					" (select name from t_asset_type where id = t.main_type_code_id), " +
					" (select name from t_asset_type where id = t.sub_type_code_id), " +
					" (select name from t_asset_type where id = t.detail_type_code_id), " +
					" t.count," +
					" t.type," +
					" t.manufacture_country," +
					" (SELECT NAME FROM T_ENTERPRISE WHERE T.MANUFACTURER_ID = ID ) MANUFACTURER," +//2生成商
					" (SELECT NAME FROM T_ENTERPRISE WHERE t.supplier_id = ID ) sup," +//1供应商
					" t.made_date,t.use_date,t.detail_install_site," +
					" (select name from T_ORGANIZATION where id = a.owner_org_code_id)," +
					
					" (select name from T_ORGANIZATION where id =  a.user_org_code_id)," +
					
					" (select name from t_department where a.department_code_id = id)," +
					" (select name from t_station where id = a.station_code_id)," +
					" t.ownership_per,t.use_per,a.GEGIN_USE_DATE,a.stop_use_date,T.APPROVAL_SCRAP_DATE," +
					" a.receive_date,S.state st,t.asset_pic," +
					" t.USE_LIFE,t.expectancy_life,t.warranty_period,t.overhaul_rate," +
					" t.FACTORY_PRICE,t.CONTRACT_PRICE,t.ORIGINAL_VALUE," +
					" p.DEPRECIATION_METHOD,p.NET_VALUE" +
					",s.NAMEPLATE_SITE," +
					" t.EQUIPMENT_LIST,t.complete_trans_asset_type," +
					" (SELECT NAME FROM T_UNIT_MASTER WHERE T.UNIT_ID = ID )" +
					
					" from t_asset t,t_asset_owner a,t_line l,t_project r,t_asset_record d, " +
					" T_ASSET_STATE  S," +
					" T_ASSET_PRICE  P"+
					" where t.id=d.asset_id "+
					" AND T.STATE_ID = S.ID"+
					" and t.asset_owner_info_id=a.id "+
					" and a.line_code_id=l.code_id "+
					" and r.id=t.project_id " +
					" AND P.ASSET_ID = T.ID";
				if(StringUtils.isNotBlank(assetCode)){
					sql += " and t.asset_Code like '%"+assetCode+"%'";
				}
				if(StringUtils.isNotBlank(name)){
					sql += " and t.name like'%"+name+"%'";
				}
				if(StringUtils.isNotBlank(lineCodeId)){
					sql += " and a.line_code_id='"+lineCodeId+"'";
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
				if(StringUtils.isNotBlank(projectNo)){
					sql += " and r.project_name like'%"+projectNo+"%'";
				}
				if(StringUtils.isNotBlank(completeTransAssetType)){
					sql += " and t.complete_trans_asset_type = '"+completeTransAssetType+"'";
				}
				if(StringUtils.isNotBlank(departmentId)){
					sql += " and a.department_code_id = '"+departmentId+"'";
				}
				if(StringUtils.isNotBlank(useOrganization)){
					sql += " and a.user_org_code_id = '"+useOrganization+"'";
				}
				if(StringUtils.isNotBlank(ownerOrganization)){
					sql += " and a.owner_org_code_id = '"+ownerOrganization+"'";
				}
				if(StringUtils.isNotBlank(projectName)){
					sql += " and d.project_name like'%"+projectName+"%'";
				}
				if(StringUtils.isNotBlank(finishDateStart)){
					sql += " and d.finish_date >= to_date('"+finishDateStart+"','yyyy-mm-dd')";
				}
				if(StringUtils.isNotBlank(finishDateEnd)){
					sql += " and d.finish_date <= to_date('"+finishDateEnd+"','yyyy-mm-dd')";   
				}
				
			}
			SimpleDateFormat formate =new SimpleDateFormat("yyyy-MM-dd");
//			Date date = new Date();
//			String fdate = formate.format(date);
			if("2".equals(recordNo)){
				sql +="select "+
						   " l.short_name, "+
					       " r.project_name, "+
					       " t.asset_code, "+
					       " t.name ," +
					       " t.id," +
					       " t.contract_id," +
					       " (select name from t_asset_type where id = t.main_type_code_id)," +
					       " (select name from t_asset_type where id = t.sub_type_code_id)," +
						   " (select name from t_asset_type where id = t.detail_type_code_id)," +
						   " t.count," +
						   " t.type," +
						   " t.manufacture_country," +
						   " (SELECT NAME FROM T_ENTERPRISE WHERE T.MANUFACTURER_ID = ID ) MANUFACTURER," +
						   " (SELECT NAME FROM T_ENTERPRISE WHERE t.supplier_id = ID ) sup," +
						   " t.made_date,t.use_date,t.detail_install_site," +
						   " (select name from T_ORGANIZATION where id = a.owner_org_code_id)," +
							
					" (select name from T_ORGANIZATION where id =  a.user_org_code_id)," +
						   
						   " (select name from t_department where a.department_code_id = id)," +
						   " (select name from t_station where id = a.station_code_id)," +
						   " t.ownership_per,t.use_per,a.GEGIN_USE_DATE,a.stop_use_date,T.APPROVAL_SCRAP_DATE," +
						   " a.receive_date,S.state st,t.asset_pic," +
						   " t.USE_LIFE,t.expectancy_life,t.warranty_period,t.overhaul_rate," +
						   " t.FACTORY_PRICE,t.CONTRACT_PRICE,t.ORIGINAL_VALUE," +
						   " p.DEPRECIATION_METHOD ,p.NET_VALUE" +
						   ",s.NAMEPLATE_SITE," +
						   " t.EQUIPMENT_LIST,t.complete_trans_asset_type," +
						   " y.overhaul_frequency_num," +
						   " (SELECT NAME FROM T_UNIT_MASTER WHERE T.UNIT_ID = ID )" +
//						   
					       " from t_asset t, "+
					       " t_asset_owner a, "+
					       " t_line l, "+
					       " t_project r, "+
					       " t_asset_type y ," +
					       " T_ASSET_STATE  S," +
					       " T_ASSET_PRICE  P"+
					       " where t.asset_owner_info_id = a.id "+
					       " and a.line_code_id = l.code_id " +
					       " AND T.STATE_ID = S.ID"+
					       " and r.id = t.project_id "+
					       " and (case "+
					       " when t.detail_type_code_id is not null then "+
					       " t.detail_type_code_id "+
					       " when t.sub_type_code_id is not null then "+
					       " t.sub_type_code_id "+
					       " else "+
					       " t.main_type_code_id "+
					       " end) = y.id " +
					       " AND P.ASSET_ID = T.ID"+
					       " and to_char(sysdate,'yyyy') = to_char(a.gegin_use_date+nvl(y.overhaul_frequency_num*365,0),'yyyy')";
						
				if(StringUtils.isNotBlank(assetCode)){
					sql += " and t.asset_Code like '%"+assetCode+"%'";
				}
				if(StringUtils.isNotBlank(name)){
					sql += " and t.name like'%"+name+"%'";
				}
				if(StringUtils.isNotBlank(lineCodeId)){
					sql += " and a.line_code_id='"+lineCodeId+"'";
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
				if(StringUtils.isNotBlank(projectNo)){
					sql += " and r.project_name like'%"+projectNo+"%'";
				}
				if(StringUtils.isNotBlank(completeTransAssetType)){
					sql += " and t.complete_trans_asset_type = '"+completeTransAssetType+"'";
				}
				if(StringUtils.isNotBlank(departmentId)){
					sql += " and a.department_code_id = '"+departmentId+"'";
				}
				if(StringUtils.isNotBlank(useOrganization)){
					sql += " and a.user_org_code_id = '"+useOrganization+"'";
				}
				if(StringUtils.isNotBlank(ownerOrganization)){
					sql += " and a.owner_org_code_id = '"+ownerOrganization+"'";
				}
				
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
			List<AssetRecord> result = new ArrayList<AssetRecord>();
			for(Object[] object:list){
				Asset asset = new Asset();
				AssetRecord assetRecord = new AssetRecord();
//				Set<AssetRecord> assetRecords = new HashSet<AssetRecord>();
				Project project = new Project();
				Line line = new Line();
				AssetOwner assetOwnerInf = new AssetOwner();
				Enterprise manufacturer = new Enterprise();
				Enterprise supplier = new Enterprise();
				AssetState state = new AssetState();
				AssetPrice assetPrice = new AssetPrice();
				AssetType assetType = new AssetType();     
				
				UnitMaster unit  = new UnitMaster();
				Organization owner =  new Organization();
				Organization user =  new Organization();
				if("1".equals(recordNo)){
					line.setShortName((String)object[0]);
					
					project.setProjectName((String)object[1]);
					
					asset.setAssetCode((String)object[2]);
					asset.setName((String)object[3]);
					assetRecord.setProjectName((String)object[4]);
					assetRecord.setAssetType((String)object[5]);
					assetRecord.setProjectAppNo((String)object[6]);
					assetRecord.setFinishDate((Date)object[7]);
					assetRecord.setFinishPrice((String)object[8]);
					asset.setId((String)object[9]);
					asset.setContractNo((String)object[10]);
					asset.setMainTypeCodeId((String)object[11]);
					asset.setSubTypeCodeId((String)object[12]);
					asset.setDetailTypeCodeId((String)object[13]);
					asset.setCount(((BigDecimal)object[14]).doubleValue());
					asset.setType((String)object[15]);
					asset.setManufactureCountry((String)object[16]);
					manufacturer.setName((String)object[17]);
					supplier.setName((String)object[18]);
					if(object[19]==null){
						asset.setMadeDate((Date)object[19]);
					}
					if(object[20]!=null){
						asset.setUseDate((Date)object[20]);
					}
					asset.setDetailInstallSite((String)object[21]);
					owner.setName((String)object[22]);
					user.setName((String)object[23]);
//					assetOwnerInf.setOwner((String)object[22]);
//					assetOwnerInf.setUser((String)object[23]);
					assetOwnerInf.setDepartmentCodeId((String)object[24]);
					assetOwnerInf.setStationCodeId((String)object[25]);
					asset.setOwnershipPer((String)object[26]);
					asset.setUsePer((String)object[27]);
					assetOwnerInf.setBeginUseDate((Date)object[28]);
					assetOwnerInf.setStopUseDate((Date)object[29]);
					asset.setApprovalScrapDate((Date)object[30]);
					assetOwnerInf.setReceiveDate((Date)object[31]);
					if("1".equals((String)object[32])){
						state.setState("使用");
					}
					if("2".equals((String)object[32])){
						state.setState("停用");
					}
					if("3".equals((String)object[32])){
						state.setState("报废");
					}
					if("4".equals((String)object[32])){
						state.setState("待报废");
					}
					if("5".equals((String)object[32])){
						state.setState("封存");
					}
					
//					state.setState((String)object[32]);
					asset.setAssetPic((String)object[33]);
					asset.setUseLife((String)object[34]);
					if(object[35]!=null)
				 		asset.setExpectancyLife(((BigDecimal)object[35]).longValueExact());
				 	
					asset.setWarrantyPeriod((Date)object[36]);
					asset.setOverhaulRate((String)object[37]);
					if(object[38]!=null)
		 				asset.setFactoryPrice(((BigDecimal)object[38]).doubleValue());
					if(object[39]!=null)
		 				asset.setContractPrice(((BigDecimal)object[39]).doubleValue());
					if(object[40]!=null)
		 				asset.setOriginalValue(((BigDecimal)object[40]).doubleValue());
					assetPrice.setDepreciationMethod((String)object[41]);
					if(object[42]!=null)
						assetPrice.setNetValue(((BigDecimal)object[42]).doubleValue());
					state.setNameplateSite((String)object[43]);
					asset.setEquipmentList((String)object[44]);
					asset.setCompleteTransAssetType((String)object[45]);
					unit.setName((String)object[46]);
					
					
				}
				if("2".equals(recordNo)){
					line.setShortName((String)object[0]);
					project.setProjectName((String)object[1]);
					asset.setAssetCode((String)object[2]);
					asset.setName((String)object[3]);
					asset.setId((String)object[4]);
					asset.setContractNo((String)object[5]);
					asset.setMainTypeCodeId((String)object[6]);
					asset.setSubTypeCodeId((String)object[7]);
					asset.setDetailTypeCodeId((String)object[8]);
					asset.setCount(((BigDecimal)object[9]).doubleValue());
					asset.setType((String)object[10]);
					asset.setManufactureCountry((String)object[11]);
					manufacturer.setName((String)object[12]);
					supplier.setName((String)object[13]);
					if(object[14]!=null){
						asset.setMadeDate((Date)object[14]);
					}
					if(object[15]!=null){
						asset.setUseDate((Date)object[15]);
					}
					
					asset.setDetailInstallSite((String)object[16]);
					owner.setName((String)object[17]);
					user.setName((String)object[18]);
//					assetOwnerInf.setOwner((String)object[17]);
//					assetOwnerInf.setUser((String)object[18]);
					assetOwnerInf.setDepartmentCodeId((String)object[19]);
					assetOwnerInf.setStationCodeId((String)object[20]);
					asset.setOwnershipPer((String)object[21]);
					asset.setUsePer((String)object[22]);
					assetOwnerInf.setBeginUseDate((Date)object[23]);
					assetOwnerInf.setStopUseDate((Date)object[24]);
					asset.setApprovalScrapDate((Date)object[25]);
					assetOwnerInf.setReceiveDate((Date)object[26]);
					if("1".equals((String)object[27])){
						state.setState("使用");
					}
					if("2".equals((String)object[27])){
						state.setState("停用");
					}
					if("3".equals((String)object[27])){
						state.setState("报废");
					}
					if("4".equals((String)object[27])){
						state.setState("待报废");
					}
					if("5".equals((String)object[27])){
						state.setState("封存");
					}
//					state.setState((String)object[27]);
					asset.setAssetPic((String)object[28]);
					asset.setUseLife((String)object[29]);
					if(object[30]!=null)
				 		asset.setExpectancyLife(((BigDecimal)object[30]).longValueExact());
				 	
					asset.setWarrantyPeriod((Date)object[31]);
					asset.setOverhaulRate((String)object[32]);
					if(object[33]!=null)
		 				asset.setFactoryPrice(((BigDecimal)object[33]).doubleValue());
					if(object[34]!=null)
		 				asset.setContractPrice(((BigDecimal)object[34]).doubleValue());
					if(object[35]!=null)
		 				asset.setOriginalValue(((BigDecimal)object[35]).doubleValue());
					assetPrice.setDepreciationMethod((String)object[36]);
					if(object[37]!=null)
						assetPrice.setNetValue(((BigDecimal)object[37]).doubleValue());
					state.setNameplateSite((String)object[38]);
					asset.setEquipmentList((String)object[39]);
					asset.setCompleteTransAssetType((String)object[40]);
//					assetOwnerInf.setBeginUseDate((Date)object[41]);//开始使用时间
					assetType.setOverhaulFrequencyNum(((BigDecimal)object[41]).longValueExact());//大修频次
					unit.setName((String)object[42]);
				}
				assetOwnerInf.setOwnerOrganization(owner);
				assetOwnerInf.setUseOrganization(user);
				asset.setUnit(unit);
				asset.setDetailType(assetType);
				asset.setAssetPrice(assetPrice);
				assetOwnerInf.setLine(line);
				asset.setAssetOwnerInf(assetOwnerInf);
				asset.setProject(project);
				asset.setSupplier(supplier);
				asset.setManufacturer(manufacturer);
				asset.setState(state);
				assetRecord.setAsset(asset);
				
				result.add(assetRecord);
			}
			
			return new Pagination<AssetRecord>(pageNo, totalPages, totalCount, result);
		}
}

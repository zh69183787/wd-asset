package com.wonders.asset.dao.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

import com.wonders.api.dto.AssetInfo;
import com.wonders.asset.base.util.Pagination;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.dao.AssetDao;
import com.wonders.asset.model.Asset;
import com.wonders.framework.core.dto.PagedQueryObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;


public class AssetDaoImpl extends BaseDaoImpl<Asset, String> implements AssetDao {

	private SessionFactory sessionFactory2;

	public SessionFactory getSessionFactory2() {
		return sessionFactory2;
	}

	public void setSessionFactory2(SessionFactory sessionFactory2) {
		this.sessionFactory2 = sessionFactory2;
	}

	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	public AssetDaoImpl() {
		super(Asset.class);
		
	}

	@Override
	public List<Asset> findByPagedQueryObject(PagedQueryObject pagedQueryObject) {
		
		return super.findByPagedQueryObject(pagedQueryObject);
	}

	@Override
	public List<Object[]> findImportData(int pageNo,int pageSize) {
		String sql = "select * from ASSET_OBJECT_temp2";
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createSQLQuery(sql).setFirstResult((pageNo-1)*pageSize).setMaxResults(pageSize);
		List<Object[]> resultList = query.list(); 
		session.close();
		return resultList;
	}

	@Override
	public void saveOrUpdate(Asset asset) {
		getHibernateTemplate().saveOrUpdate(asset);
	}

	@Override
	public void saveAll(List<Asset> assets) {
        for (int i = 0; i < assets.size(); i++) {
            getSession().save(assets.get(i));

            if (i % 100 == 0) // 以每50个数据作为一个处理单元，也就是我上面说的“一定的量”，这个量是要酌情考虑的

            {
                getSession().flush();
                getSession().clear();
            }
        }
	}

//    public void synData(Date lastExecuteDate){
////        changeSessionLanguage();
//        String sql = "select ASSET_NO                   资产编码," +
//                "       ASSET_DESCRIPTION              资产名称," +
//                "       PROJECT_NAME                   项目名称," +
//                "       PROJECT_NO                     项目编号," +
//                "       PROJECT_CONTRACT_NO            项目合同编码," +
//                "substr(ASSET_TYPE,1,2)         大类编码," +
//                "       IFSAPP.ASSET_TYPE_API.Get_Description(substr(ASSET_TYPE,1,2)) 大类名称," +
//                "substr(ASSET_TYPE,1,4)         中类编码," +
//                "       IFSAPP.ASSET_TYPE_API.Get_Description(substr(ASSET_TYPE,1,4)) 中类名称," +
//                "       ASSET_TYPE                     资产类型," +
//                "       IFSAPP.ASSET_TYPE_API.Get_Description(ASSET_TYPE) 类型名称," +
//                "       UNIT_CODE                      单位," +
//                "       ASSET_QTY                      数量," +
//                "       FIN_GZXH                       规格型号," +
//                "       PRODUCE_AREA                   产地," +
//                "       MANUFACTURER                   生产厂商," +
//                "       SUPPLIER                       供应商," +
//                "       LEAVE_FACTORY                  出厂日期," +
//                "       SUPPLY_DATE                    供应日期," +
//                "       INSTALL_SIDE                   安装地点," +
//                "       OWNERSHIP_UNIT                 权属单位编号," +
//                "       IFSAPP.ASSET_DEPT_API.Get_Description(OWNERSHIP_UNIT) 权属单位名称," +
//                "       OWNERSHIP_PER                  权属负责人," +
//                "       IFSAPP.PERSON_INFO_API.Get_Name(OWNERSHIP_PER) 权属负责人名称," +
//                "       USE_UNIT                       使用单位编号," +
//                "       IFSAPP.USING_DEPT_API.Get_Dept_Name(USE_UNIT) 使用单位名称," +
//                "       USE_PER                        使用负责人," +
//                "       IFSAPP.PERSON_INFO_API.Get_Name(USE_PER) 使用负责人名称," +
//                "       MAINTAIN_DEPART                维护部门编号," +
//                "       IFSAPP.MAINTAIN_DEPT_API.Get_Dept_Name(MAINTAIN_DEPART) 维护部门名称," +
//                "       LOCATION_CODE                  资产位置编码," +
//                "       IFSAPP.ASSET_LOCATION_API.Get_Description(LOCATION_CODE) 资产位置," +
//                "       START_USE_DATE                 开始使用时间," +
//                "       END_USE_DATE                   停止使用时间," +
//                "       SCRAP_DATE                     批准报废时间," +
//                "       TRANSFER_DATE                  移交时间," +
//                "       USE_STATE                      当前使用状态," +
//                "       ASSETS_PICTURE                 资产图片名称," +
//                "       DESIGN_USE_LIFE                设计使用年限," +
//                "       EXPECT_USE_LIFE                预期使用寿命," +
//                "       WARRANTY_PERIOD                保修期至," +
//                "       OVERHAUL_RATE                  大修频次," +
//                "       FACTORY_PRICE                  出厂价," +
//                "       CONTRACT_PRICE                 合同价," +
//                "       ORIGINAL_VALUE                 原值," +
//                "decode(HAVE_LIST,'TRUE','有','无') 资料及清单," +
//                "       DEPRECIATE_METHOD              折旧方法," +
//                "       ACCU_DEPRECIATE                累计折旧," +
//                "       NET_ASSET_VALUE                资产净值," +
//                "       POST_POSITION                  铭牌张贴位置," +
//                "       EQUIP_LIST                     设备清单," +
//                "       COMPLETE_TRANS_ASSET_TYPE      竣工移交资产类型," +
//                "       OPERATE_PROJECT_ASSET          项目资产标示," +
//                "       OPERATE_PROJECT_ASSET_DATE     资产交付日期," +
//                "       OPERATE_PROJECT_ASSET_ACCOUNT  资产决算价," +
//                "       PROJECT_APP_DOC_NO             立项或可研批复文号," +
//                "       REMARK                         备注," +
//                "CREATE_TIME                    创建时间," +
//                "       MODIFY_TIME                    修改时间," +
//                "       substr(location_code,1,2)      线路编码," +
//                "       IFSAPP.ASSET_LOCATION_API.Get_Description(substr(location_code,1,2)) 线路名称," +
//                "       substr(location_code,3,2)      车站编码," +
//                "       IFSAPP.ASSET_LOCATION_API.Get_Description(location_code) 车站名称" +
//                "from ASSET_OBJECT";
////                +"where manage_type_db = 'DEPOSIT' ";
//        Query query = getSessionFactory().getCurrentSession().createSQLQuery(sql);
//        if(lastExecuteDate!=null){
//            sql +=" and MODIFY_TIME<=? and MODIFY_TIME>=?";
//        }
//        if(lastExecuteDate!=null){
//            query.setDate(0, lastExecuteDate).setDate(1, DateUtils.addDays(lastExecuteDate, -3));
//        }
//        List<Object[]> list = query.list();
//        for (Object[] objects : list) {
//
//        }
//        getSessionFactory().getCurrentSession().createSQLQuery(sql).executeUpdate();
//    }

	@Override
	public List<Object[]> findBeforeLastExecuteDate(Date lastExecuteDate) {
		String sql = "select ASSET_NO ,"
			+ "ASSET_DESCRIPTION ,"
			//+ "PROJECT_NO ,"
			+ "PROJECT_NAME ,"
			+ "ASSET_TYPE ,"
			+ "UNIT_CODE ,"
			+ "ASSET_QTY ,"
			+ "FIN_GZXH ,"
			+ "PRODUCE_AREA ,"
			+ "MANUFACTURER ,"
			+ "SUPPLIER ,"
			+ "LEAVE_FACTORY ,"
			+ "SUPPLY_DATE ,"
			+ "INSTALL_SIDE ,"
			+ "OWNERSHIP_UNIT ,"
			+ "USE_UNIT ,"
			+ "MAINTAIN_DEPART ,"			
			+ "START_USE_DATE ," + "END_USE_DATE ,"
			+ "SCRAP_DATE ," + "TRANSFER_DATE ,"
			+ "USE_STATE ," + "ASSETS_PICTURE ,"
			+ "DESIGN_USE_LIFE ," + "EXPECT_USE_LIFE ,"
			+ "WARRANTY_PERIOD ," + "OVERHAUL_RATE ,"
			+ "FACTORY_PRICE ," + "CONTRACT_PRICE ,"
			+ "ORIGINAL_VALUE ,"
			+ "decode(HAVE_LIST,'TRUE','有','无') ,"
			+ "DEPRECIATE_METHOD ," + "ACCU_DEPRECIATE ,"
			+ "NET_ASSET_VALUE ," + "POST_POSITION ,"			
			+ "EQUIP_LIST ,"
			+ "COMPLETE_TRANS_ASSET_TYPE ,"
			+ "OPERATE_PROJECT_ASSET ,"
			+ "OPERATE_PROJECT_ASSET_DATE ,"
			+ "PROJECT_APP_DOC_NO ," + "REMARK ,"		
			+ "CREATE_TIME ," + "MODIFY_TIME ,"
			+ "PROJECT_CONTRACT_NO,OWNERSHIP_PER,USE_PER,LOCATION_CODE,OPERATE_PROJECT_ASSET_ACCOUNT, "
			+ "substr(location_code,1,2),"      //"线路编码
		    + "IFSAPP.ASSET_LOCATION_API.Get_Description(substr(location_code,1,2))," 	//"线路名称"
		    + "substr(location_code,3,2) "      //"车站编码"
		    //+ "IFSAPP.ASSET_LOCATION_API.Get_Description(location_code) "// 		"车站名称"
			+ " from Ifsapp.Asset_Object where manage_type_db = 'DEPOSIT' ";
//        			+ " from Asset_Object where manage_type_db = 'DEPOSIT' ";
		if(lastExecuteDate!=null){
			sql +=" and MODIFY_TIME<=? and MODIFY_TIME>=?";
		}
		sql += " order by modify_time";
		/*ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/applicationContext-dataAccess-hibernate-annotation.xml");
		SessionFactory sessionFactory2 = (SessionFactory) ctx.getBean("sessionFactory2");*/
		Query query = sessionFactory2.getCurrentSession().createSQLQuery(sql);
		if(lastExecuteDate!=null){
			query.setDate(0, lastExecuteDate).setDate(1, DateUtils.addDays(lastExecuteDate, -3));
		}
		return query.list();
	}

	@Override
	public Asset findByAssetCode(String assetCode) {
		String hql="from Asset t where t.assetCode=?";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, assetCode);
		List<Asset> list = query.list();
		if(list!=null && list.size()==1) return list.get(0);
		return null;
	}

	@Override
	public void changeSessionLanguage() {
		String sql = "{Call IFSAPP.FND_SESSION_API.Set_Property('LANGUAGE','zh')}";
		Query query = sessionFactory2.getCurrentSession().createSQLQuery(sql);
		query.executeUpdate();
	}

	
	
	@Override
	public List<Object[]> findBeforeLastExecuteDateWithAssetNo() {
		String sql = "select ASSET_NO ,"
			+ "ASSET_DESCRIPTION ,"
			//+ "PROJECT_NO ,"
			+ "PROJECT_NAME ,"
			+ "ASSET_TYPE ,"
			+ "UNIT_CODE ,"
			+ "ASSET_QTY ,"
			+ "FIN_GZXH ,"
			+ "PRODUCE_AREA ,"
			+ "MANUFACTURER ,"
			+ "SUPPLIER ,"
			+ "LEAVE_FACTORY ,"
			+ "SUPPLY_DATE ,"
			+ "INSTALL_SIDE ,"
			+ "OWNERSHIP_UNIT ,"
			+ "USE_UNIT ,"
			+ "MAINTAIN_DEPART ,"			
			+ "START_USE_DATE ," + "END_USE_DATE ,"
			+ "SCRAP_DATE ," + "TRANSFER_DATE ,"
			+ "USE_STATE ," + "ASSETS_PICTURE ,"
			+ "DESIGN_USE_LIFE ," + "EXPECT_USE_LIFE ,"
			+ "WARRANTY_PERIOD ," + "OVERHAUL_RATE ,"
			+ "FACTORY_PRICE ," + "CONTRACT_PRICE ,"
			+ "ORIGINAL_VALUE ,"
			+ "decode(HAVE_LIST,'TRUE','有','无') ,"
			+ "DEPRECIATE_METHOD ," + "ACCU_DEPRECIATE ,"
			+ "NET_ASSET_VALUE ," + "POST_POSITION ,"			
			+ "EQUIP_LIST ,"
			+ "COMPLETE_TRANS_ASSET_TYPE ,"
			+ "OPERATE_PROJECT_ASSET ,"
			+ "OPERATE_PROJECT_ASSET_DATE ,"
			+ "PROJECT_APP_DOC_NO ," + "REMARK ,"		
			+ "CREATE_TIME ," + "MODIFY_TIME ,"
			+ "PROJECT_CONTRACT_NO,OWNERSHIP_PER,USE_PER,LOCATION_CODE,OPERATE_PROJECT_ASSET_ACCOUNT, "
			+ "substr(location_code,1,2),"      //"线路编码
		    + "IFSAPP.ASSET_LOCATION_API.Get_Description(substr(location_code,1,2))," 	//"线路名称"
		    + "substr(location_code,3,2) "      //"车站编码"
		    //+ "IFSAPP.ASSET_LOCATION_API.Get_Description(location_code) "// 		"车站名称"
			+ " from Ifsapp.Asset_Object where manage_type_db = 'DEPOSIT' and ASSET_NO in('04521060499040106157','04521060499040106158','04521060499040106159','04521060499040106160','04521060499040106161','04521060499040106162')";
		
		sql += " order by modify_time";
		/*ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/applicationContext-dataAccess-hibernate-annotation.xml");
		SessionFactory sessionFactory2 = (SessionFactory) ctx.getBean("sessionFactory2");*/
		Query query = sessionFactory2.getCurrentSession().createSQLQuery(sql);
		return query.list();
	}


    /**
     *查询资产信息的方法
     *
     * @param assetInfo
     * @param pageNo
     * @param pageSize
     * @return Pagination
     */
    @Override
    public Pagination findSomeAssetInfo(AssetInfo assetInfo, int pageNo, int pageSize) {

        String url = "/";
        try {
            Resource resource = new ClassPathResource("/config.properties");
            Properties props = PropertiesLoaderUtils.loadProperties(resource);
            url = props.getProperty("apiUrl");
        } catch (IOException e) {
            e.printStackTrace();
        }

        String sql="SELECT A.ID," +
                "       A.ASSET_CODE," +
                "       A.NAME," +
                "       A.USE_LIFE," +
                "       A.OVERHAUL_RATE," +
                "       A.MAIN_TYPE_CODE_ID," +
                "       A.ORIGINAL_VALUE," +
                "       (SELECT NAME FROM T_ASSET_TYPE T WHERE T.ID = A.MAIN_TYPE_CODE_ID) MAIN_TYPE_NAME," +
                "       A.SUB_TYPE_CODE_ID," +
                "       (SELECT NAME FROM T_ASSET_TYPE T WHERE T.ID = A.SUB_TYPE_CODE_ID) SUB_TYPE_NAME," +
                "       A.DETAIL_TYPE_CODE_ID," +
                "       (SELECT NAME FROM T_ASSET_TYPE T WHERE T.ID = A.DETAIL_TYPE_CODE_ID) DETAIL_TYPE_NAME,'" +url+
                "/AssetWeb/asset/showView.action?id=' || A.ID URL," +
                "       O.GEGIN_USE_DATE BEGIN_USE_DATE," +
                "       O.LINE_CODE_ID," +
                "       (SELECT NAME FROM T_LINE T WHERE T.ID = O.LINE_CODE_ID) LINE_NAME," +
                "       O.STATION_CODE_ID," +
                "       (SELECT NAME" +
                "          FROM T_STATION T" +
                "         WHERE T.ID = O.STATION_CODE_ID" +
                "           AND T.LINE_ID = O.LINE_CODE_ID) STATION_NAME," +
                "       R.LAST_MAINTAIN_DATE," +
                "       R.MAINTAIN_NUM" +
                "  FROM T_ASSET A," +
                "       T_ASSET_OWNER O," +
                "       (SELECT MAX(R.UPDATE_DATE) LAST_MAINTAIN_DATE," +
                "               COUNT(R.ID) MAINTAIN_NUM," +
                "               R.ASSET_ID" +
                "          FROM T_ASSET_RECORD R" +
                "         GROUP BY R.ASSET_ID) R" +
                " WHERE O.ID(+) = A.ASSET_OWNER_INFO_ID" +
                "   AND A.ID = R.ASSET_ID(+) ";

        HashMap map = new HashMap();
        System.out.println("==============================dao1================================="+assetInfo);
        if (StringUtils.isNotBlank(assetInfo.getAssetNo())) {
            sql+=" AND A.ASSET_CODE =:assetCode ";
            map.put("assetCode",assetInfo.getAssetNo());
        }
        if (StringUtils.isNotBlank(assetInfo.getAssetName())) {
            sql+=" AND A.NAME like :name ";
            map.put("name","%"+assetInfo.getAssetName()+"%");
        }
        if (StringUtils.isNotBlank(assetInfo.getMainTypeCodeId())) {
            sql+=" AND A.MAIN_TYPE_CODE_ID =:MainTypeCodeId ";
            map.put("MainTypeCodeId",assetInfo.getMainTypeCodeId());
        }
        if (StringUtils.isNotBlank(assetInfo.getSubTypeCodeId())) {
            sql+=" AND A.SUB_TYPE_CODE_ID =:SubTypeCodeId ";
            map.put("SubTypeCodeId",assetInfo.getSubTypeCodeId());
        }
        if (StringUtils.isNotBlank(assetInfo.getDetailTypeCodeId())) {
            sql+=" AND A.DETAIL_TYPE_CODE_ID =:DetailTypeCodeId ";
            map.put("DetailTypeCodeId",assetInfo.getDetailTypeCodeId());
        }
        if (StringUtils.isNotBlank(assetInfo.getLineCodeId())) {
            sql+=" AND O.LINE_CODE_ID =:LineCodeId ";
            map.put("LineCodeId",assetInfo.getLineCodeId());
        }
        if (StringUtils.isNotBlank(assetInfo.getStationCodeId())) {
            sql+=" AND O.STATION_CODE_ID =:StationCodeId ";
            map.put("StationCodeId",assetInfo.getStationCodeId());
        }
        if (assetInfo.getOriginalValue()!=null) {
            sql+=" AND A.ORIGINAL_VALUE =:OriginalValue ";
            map.put("OriginalValue",assetInfo.getOriginalValue());
        }

        if (StringUtils.isNotBlank(assetInfo.getDepartmentId())) {
            sql+=" AND O.DEPARTMENT_CODE_ID =:DepartmentCodeId ";
            map.put("DepartmentCodeId",assetInfo.getDepartmentId());
        }
        if (StringUtils.isNotBlank(assetInfo.getOwnerOrganizationId())) {
            sql+=" AND A.OWNER_ORG_CODE_ID =:OwnerCodeId ";
            map.put("OwnerCodeId",assetInfo.getOwnerOrganizationId());
        }
        if (StringUtils.isNotBlank(assetInfo.getUseOrganizationId())) {
            sql+=" AND A.USER_ORG_CODE_ID =:UseOrgCodeId ";
            map.put("UseOrgCodeId",assetInfo.getUseOrganizationId());
        }
        System.out.println("==============================dao=================================");
        List<Object[]> result =  getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).setProperties(map).setFirstResult((pageNo-1)*pageSize).setMaxResults(pageSize).list();
        BigDecimal totalCount = (BigDecimal)getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery("SELECT COUNT(1) FROM ("+sql+")").setProperties(map).uniqueResult();
        List<AssetInfo>  list=new ArrayList<AssetInfo>();

        for(Object[] o:result){
            AssetInfo assetinfo=new AssetInfo();
            assetinfo.setId((String) o[0]);
            assetinfo.setAssetNo((String) o[1]);
            assetinfo.setAssetName((String) o[2]);
            assetinfo.setUserLife((String) o[3]);
            assetinfo.setOverhaulRate((String) o[4]);
            assetinfo.setMainTypeCodeId((String) o[5]);
            if(o[6]!=null)
            assetinfo.setOriginalValue(((BigDecimal)o[6]).doubleValue());
            assetinfo.setMainTypeName((String)o[7]);
            assetinfo.setSubTypeCodeId((String)o[8]);
            assetinfo.setSubTypeName((String)o[9]);
            assetinfo.setDetailTypeCodeId((String)o[10]);
            assetinfo.setDetailTypeName((String)o[11]);
            assetinfo.setUrl((String)o[12]);
            assetinfo.setBeginUseDate((Date)o[13]);
            assetinfo.setLineCodeId((String)o[14]);
            assetinfo.setLineName((String)o[15]);
            assetinfo.setStationCodeId((String)o[16]);
            assetinfo.setStationName((String)o[17]);
            assetinfo.setLastMaintainDate((Date)o[18]);
            if(o[19]!=null)
            assetinfo.setMaintainNum(((BigDecimal)o[19]).intValue());
            list.add(assetinfo);
        }

        int totalPages = (totalCount.intValue() > 0) ? (int) Math.ceil((double) totalCount.intValue()  / pageSize) : 1;


        Pagination pagination=new Pagination(pageNo,totalPages,totalCount.intValue(),list);
        return pagination;
//        return null;
    }

	@Override
	public Integer getAllAssetCount() {
		String sql = "select count(1) from T_ASSET";
		BigDecimal res = (BigDecimal) getSession().createSQLQuery(sql).list().get(0);
		if(res == null){
			return new Integer(0);
		}
		return res.intValue();
	}

	@Override
	public Integer getAssetCountByType(String type) {
		String sql = "select count(1) from T_ASSET where complete_trans_asset_type = '"+type+"'";
		Query query = getSession().createSQLQuery(sql);
		BigDecimal res = (BigDecimal) getSession().createSQLQuery(sql).list().get(0);
		if(res == null){
			return new Integer(0);
		}
		return res.intValue();
	}

    /**
     * 根据资产小类名 对应的资产小类id计算大修数（用于计算大修比）
     * @param detailTypeName
     * @return
     */
	@Override
	public int getOverhaulAsset(String detailTypeCodeId) {
		// TODO Auto-generated method stub
		String sql = " select sum(t.count) "+
				" from T_ASSET t "+
				" where t.detail_type_code_id = "+detailTypeCodeId +" and (t.operate_project_asset = '大修' "+
				" or t.operate_project_asset = '改造') ";
		//select sum(t.count) from T_ASSET t  where t.detail_type_code_id = (select at.id from T_ASSET_TYPE at where at.name = '空调箱')  and (t.operate_project_asset = '大修' or t.operate_project_asset = '改造')
		BigDecimal res = (BigDecimal) getSession().createSQLQuery(sql).list().get(0);
		if(res == null){
			return new Integer(0);
		}
		return res.intValue();
		
	}

	@Override
	public List<Object[]> getOverhaulAssetData() {
		// TODO Auto-generated method stub
		/**/
		/*合并写sql*/
		/*
		-- 先group  ar.asset_id
		select isNull, dateDif,count(beginDif),beginDif,
		       (case when yearDif>0 then 1 when yearDif < 0 then -1 else 0 end ) yearDif,
		       count( yearDif),
		       count(asset_type),
		       (case asset_type when '初始' then '初始' else '新增或更新' end ) asset_type,
		       (case  when finishDif=0 then 0 when finishDif>0 then 1 else -1 end ) finishDif,
		       count(finishDif  )
		        from(
		  select (case when asset_id is  null then 1 when asset_id is not null then  0 else -1 end )isNull,count(a_id),asset_type,ovelhaul_frequency,gegin_use_date, a_id,MAX(finish_date),MIN(finish_date),
		  (case (MAX(finish_date)-MIN(finish_date)) when 0 then 0 else 1 end )dateDif,
		   (to_char(Max(finish_date),'yyyy')-to_char(Min(finish_date),'yyyy')-ovelhaul_frequency) yearDif,
		    ( case when (to_char(Max(sysdate),'yyyy')-to_char(gegin_use_date,'yyyy')-ovelhaul_frequency) = 0 then 0 when (to_char(Max(sysdate),'yyyy')-to_char(gegin_use_date,'yyyy')-ovelhaul_frequency) > 0 then 1 when (to_char(Max(sysdate),'yyyy')-to_char(gegin_use_date,'yyyy')-ovelhaul_frequency)< 0 then -1 else -404 end  )beginDif,
		    (to_char(Max(finish_date),'yyyy')-to_char(gegin_use_date,'yyyy')-ovelhaul_frequency) finishDif
		    from (select t.overhaul_frequency ovelhaul_frequency,
		                 ar.asset_type asset_type,
		                 ar.finish_date,
		                 ao.gegin_use_date gegin_use_date,
		                 a.complete_trans_asset_type complete_trans_asset_type,
		                 dense_rank() over(partition by ar.asset_id order by ar.finish_date desc) daterank,
		                 a.id a_id,
		                 ar.id ar_id,
		                 ar.asset_id
		            from (T_ASSET a left join T_ASSET_RECORD ar on a.id = ar.asset_id left join
		          T_ASSET_OWNER ao on a.asset_owner_info_id = ao.id left join
		          T_ASSET_TYPE t on a.detail_type_code_id = t.id)
		            )
		   where daterank <= 2  
		  group by (case when asset_id is  null then 1 when asset_id is not null then  0 else -1 end ),
		   a_id ,asset_type,ovelhaul_frequency,gegin_use_date
		             )
		            
		             group by isNull,beginDif,dateDif,
		             (case when yearDif>0 then 1 when yearDif < 0 then -1 else 0 end ),
		             (case asset_type when '初始' then '初始' else '新增或更新' end ),
		             (case  when finishDif=0 then 0 when finishDif>0 then 1 else -1 end )
		             order by isNull



		------------------------------------------------------------------------------------------------
		--排名后有两条记录的，比较的是两条记录finish_date之差
		--排名后有一条记录的，移交类型 为“初始” 开始使用时间 + 频次 ？ 当前时间
		                            --   “新增或更新” 默认为正常
		--已取出最大日期和最小日期
		--最大日期，最小日期相减 (dateDif)
		                     --0 : 排名后有一条记录的
		                                              --asset_type
		                                                          --“初始”   
		                                                                --开始使用日期 + 频次 - 当前日期(finishDif) ？ 0
		                                                                -- 当前日期/还是竣工日期？？ -开始使用日期 - 频次？ 0
		                                                                              --finishDif = 0正常（标识0） finishDif > 0 延时（标志1） finishDif <0 提前（标志-1） 
		                                                           --“新增或更新” 默认为正常
		                     --不为0 : 排名后有两条记录的
		                                              --最大日期年份-最小日期年份-大修频次 (yearDif)？ 0  
		                                                                              --yearDif = 0正常（标识0） yearDif > 0 延时（标志1） yearDif <0 提前（标志-1） 
		                                              
		-- group dateDif  yearDif select count(yearDif)
		-- group dateDif  asset_type finishDif  select count(asset_type),count(finishDif)
		--用case when 当 dateDif 为0 then 0 ；当dateDif 不为 0 then 1 ；
		select dateDif,
		       (case when yearDif>0 then 1 when yearDif < 0 then -1 else 0 end ) yearDif,
		       count( yearDif),
		       count(asset_type),
		       (case asset_type when '初始' then '初始' else '新增或更新' end ) asset_type,
		       (case  when finishDif=0 then 0 when finishDif>0 then 1 else -1 end ) finishDif,
		       count(finishDif  )
		        from(
		  select (case when asset_id is  null then 1 when asset_id is not null then  0 else -1 end )isNull,count(a_id),asset_type,ovelhaul_frequency,gegin_use_date, a_id,MAX(finish_date),MIN(finish_date),
		  (case (MAX(finish_date)-MIN(finish_date)) when 0 then 0 else 1 end )dateDif,
		   (to_char(Max(finish_date),'yyyy')-to_char(Min(finish_date),'yyyy')-ovelhaul_frequency) yearDif
		    ,(to_char(Max(finish_date),'yyyy')-to_char(gegin_use_date,'yyyy')-ovelhaul_frequency) finishDif
		    from (select t.overhaul_frequency ovelhaul_frequency,
		                 ar.asset_type asset_type,
		                 ar.finish_date,
		                 ao.gegin_use_date gegin_use_date,
		                 a.complete_trans_asset_type complete_trans_asset_type,
		                 dense_rank() over(partition by ar.asset_id order by ar.finish_date desc) daterank,
		                 a.id a_id,
		                 ar.id ar_id,
		                 ar.asset_id
		            from (T_ASSET a left join T_ASSET_RECORD ar on a.id = ar.asset_id left join
		          T_ASSET_OWNER ao on a.asset_owner_info_id = ao.id left join
		          T_ASSET_TYPE t on a.detail_type_code_id = t.id)
		            )
		   where daterank <= 2  
		  group by (case when asset_id is  null then 1 when asset_id is not null then  0 else -1 end ),
		   a_id ,asset_type,ovelhaul_frequency,gegin_use_date
		             )
		             where isNull = 0
		             group by dateDif,
		             (case when yearDif>0 then 1 when yearDif < 0 then -1 else 0 end ),
		             (case asset_type when '初始' then '初始' else '新增或更新' end ),
		             (case  when finishDif=0 then 0 when finishDif>0 then 1 else -1 end )
		             
		             
		-- T_ASSET 表里有 但是T_ASSET_RECORD 表没有的记录
		-- 当前时间 - 开始使用日期 - 大修频次 （beginDif）? 0
		-- beginDif > 0 (延时) beginDif  = 0 (正常) beginDif < 0 (提前)

		-- 涉及表 T_ASSET a、T_ASSET_RECORD ar、T_ASSET_OWNER ao、T_ASSET_TYPE t
		-- 主要字段 ao.gegin_use_date at.ovelhaul_frequency

		-- 连接之后ar.id 为空时，按ar.asset_id分 ar.finish_date排序 不会合并
		-- 按 beginDif ? 0 group 并 count
		select count(beginDif),beginDif
		from
		 ( select (case when asset_id is  null then 1 when asset_id is not null then  0 else -1 end )isNull, count(a_id) c_a_id,asset_type,ovelhaul_frequency,gegin_use_date, a_id,MAX(finish_date),MIN(finish_date),
		  (case (MAX(finish_date)-MIN(finish_date)) when 0 then 0 else 1 end )dateDif,
		   (to_char(Max(finish_date),'yyyy')-to_char(Min(finish_date),'yyyy')-ovelhaul_frequency) yearDif,
		    
		    ( case when (to_char(Max(sysdate),'yyyy')-to_char(gegin_use_date,'yyyy')-ovelhaul_frequency) = 0 then 0 when (to_char(Max(sysdate),'yyyy')-to_char(gegin_use_date,'yyyy')-ovelhaul_frequency) > 0 then 1 when (to_char(Max(sysdate),'yyyy')-to_char(gegin_use_date,'yyyy')-ovelhaul_frequency)< 0 then -1 else -404 end  )beginDif
		    from (select t.overhaul_frequency ovelhaul_frequency,
		                 ar.asset_type asset_type,
		                 ar.finish_date,
		                 ao.gegin_use_date gegin_use_date,
		                 a.complete_trans_asset_type complete_trans_asset_type,
		                 dense_rank() over(partition by ar.asset_id order by ar.finish_date desc) daterank,
		                 a.id a_id,
		                 ar.id ar_id,
		                 ar.asset_id
		            from (T_ASSET a left join T_ASSET_RECORD ar on a.id = ar.asset_id left join
		          T_ASSET_OWNER ao on a.asset_owner_info_id = ao.id left join
		          T_ASSET_TYPE t on a.detail_type_code_id = t.id)
		           )
		   where daterank <= 2  
		  group by (case when asset_id is  null then 1 when asset_id is not null then  0 else -1 end ),
		  a_id ,asset_type,ovelhaul_frequency,gegin_use_date  
		  )  
		  where isNull = 1
		  group by beginDif
		  
		  
		  
		  -------------------------
*/
		
		
		String sql = 		"select isNull, dateDif,count(beginDif),beginDif, "+
			      " (case when yearDif>0 then 1 when yearDif < 0 then -1 else 0 end ) yearDif, "+
			      "  count( yearDif), "+
			      "  count(asset_type), "+
			      "  (case asset_type when '初始' then '初始' else '新增或更新' end ) asset_type, "+
			      "  (case  when finishDif=0 then 0 when finishDif>0 then 1 else -1 end ) finishDif, "+
			      "  count(finishDif  ) "+
			      "    from( "+
			      "  select (case when asset_id is  null then 1 when asset_id is not null then  0 else -1 end )isNull,count(a_id),asset_type,ovelhaul_frequency,gegin_use_date, a_id,MAX(finish_date),MIN(finish_date), "+
			      "  (case (MAX(finish_date)-MIN(finish_date)) when 0 then 0 else 1 end )dateDif, "+
			      " (to_char(Max(finish_date),'yyyy')-to_char(Min(finish_date),'yyyy')-ovelhaul_frequency) yearDif, "+
			      " ( case when (to_char(Max(sysdate),'yyyy')-to_char(gegin_use_date,'yyyy')-ovelhaul_frequency) = 0 then 0 when (to_char(Max(sysdate),'yyyy')-to_char(gegin_use_date,'yyyy')-ovelhaul_frequency) > 0 then 1 when (to_char(Max(sysdate),'yyyy')-to_char(gegin_use_date,'yyyy')-ovelhaul_frequency)< 0 then -1 else -404 end  )beginDif, "+
			      " (to_char(Max(finish_date),'yyyy')-to_char(gegin_use_date,'yyyy')-ovelhaul_frequency) finishDif "+
			      " from (select t.overhaul_frequency ovelhaul_frequency, "+
			      "            ar.asset_type asset_type, "+
			      "            ar.finish_date, "+
			      "            ao.gegin_use_date gegin_use_date, "+
			      "            a.complete_trans_asset_type complete_trans_asset_type, "+
			      "            dense_rank() over(partition by ar.asset_id order by ar.finish_date desc) daterank, "+
			      "            a.id a_id, "+
			      "            ar.id ar_id, "+
			      "            ar.asset_id "+
			      "       from (T_ASSET a left join T_ASSET_RECORD ar on a.id = ar.asset_id left join "+
			      "     T_ASSET_OWNER ao on a.asset_owner_info_id = ao.id left join "+
			      "     T_ASSET_TYPE t on a.detail_type_code_id = t.id) "+
			      "       ) "+
			      "  where daterank <= 2   "+
			      "  group by (case when asset_id is  null then 1 when asset_id is not null then  0 else -1 end ), "+
			      "  a_id ,asset_type,ovelhaul_frequency,gegin_use_date "+
			      "        ) "+
			            
				  "        group by isNull,beginDif,dateDif, "+
				  "       (case when yearDif>0 then 1 when yearDif < 0 then -1 else 0 end ), "+
			             "       (case asset_type when '初始' then '初始' else '新增或更新' end ), "+
			             "       (case  when finishDif=0 then 0 when finishDif>0 then 1 else -1 end ) "+
			             "       order by isNull ";
			/*ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/applicationContext-dataAccess-hibernate-annotation.xml");
			SessionFactory sessionFactory2 = (SessionFactory) ctx.getBean("sessionFactory2");*/
			Query query = sessionFactory2.getCurrentSession().createSQLQuery(sql);
			return query.list();
	}

	@Override
	public List<Object[]> getAssetDamage() {
		// TODO Auto-generated method stub
		/*
		 * -- flag1 = 1 flag = -1 提前 flag = 0 正常 flag = 1 超期
-- flag1= 0 超期
-- flag1 = -1 

select count(flag1), count(flag)
  from (select (case
                 when s.use_state = '报废' then
                  1
                 when s.use_state != '报废' and
                      (to_char(sysdate, 'yyyy') -
                      to_char(o.gegin_use_date, 'yyyy') - a.expectancy_life) > 0 then
                  0
                 else
                  -1
               end) flag1,
               (case
                 when (to_char(da.scrap_date, 'yyyy') -
                      to_char(o.gegin_use_date, 'yyyy') - a.expectancy_life) < 0 then
                  -1
                 when (to_char(da.scrap_date, 'yyyy') -
                      to_char(o.gegin_use_date, 'yyyy') - a.expectancy_life) = 0 then
                  0
                 when (to_char(da.scrap_date, 'yyyy') -
                      to_char(o.gegin_use_date, 'yyyy') - a.expectancy_life) > 0 then
                  1
                 else
                  -404
               end) flag
          from T_ASSET_OWNER  o,
               T_ASSET        a,
               T_DAMAGE_ASSET da,
               T_ASSET_STATE  s
         where a.ASSET_OWNER_INFO_ID(+) = o.id
           and a.state_id = s.id
           and a.asset_code = da.asset_no
         group by (case
                    when s.use_state = '报废' then
                     1
                    when s.use_state != '报废' and
                         (to_char(sysdate, 'yyyy') -
                         to_char(o.gegin_use_date, 'yyyy') -
                         a.expectancy_life) > 0 then
                     0
                    else
                     -1
                  end),
                  (case
                    when (to_char(da.scrap_date, 'yyyy') -
                         to_char(o.gegin_use_date, 'yyyy') -
                         a.expectancy_life) < 0 then
                     -1
                    when (to_char(da.scrap_date, 'yyyy') -
                         to_char(o.gegin_use_date, 'yyyy') -
                         a.expectancy_life) = 0 then
                     0
                    when (to_char(da.scrap_date, 'yyyy') -
                         to_char(o.gegin_use_date, 'yyyy') -
                         a.expectancy_life) > 0 then
                     1
                    else
                     -404
                  end))
                  */
		String sql = "select flag1,count(flag1),flag, count(flag) "+
				" from (select (case "+
						"  when s.use_state = '报废' then "+
                		 "   1 "+
                  "   when s.use_state != '报废' and "+
                		 "      (to_char(sysdate, 'yyyy') - "+
                    		  "       to_char(o.gegin_use_date, 'yyyy') - a.expectancy_life) > 0 then "+
                      "     0 "+
                  "     else "+
                	 "     -1 "+
                  "  end) flag1, "+
               "   (case "+
            		   "    when (to_char(da.scrap_date, 'yyyy') - "+
                		 "      to_char(o.gegin_use_date, 'yyyy') - a.expectancy_life) < 0 then "+
                      "   -1 "+
                  "   when (to_char(da.scrap_date, 'yyyy') - "+
                		 "      to_char(o.gegin_use_date, 'yyyy') - a.expectancy_life) = 0 then "+
                    		  "    0 "+
                  "   when (to_char(da.scrap_date, 'yyyy') - "+
                		 "    to_char(o.gegin_use_date, 'yyyy') - a.expectancy_life) > 0 then "+
                      "    1 "+
                  "     else "+
                	 "      -404 "+
                  "     end) flag "+
               "   from T_ASSET_OWNER  o, "+
          "   T_ASSET        a, "+
               "   T_DAMAGE_ASSET da, "+
               "  T_ASSET_STATE  s "+
               " where a.ASSET_OWNER_INFO_ID(+) = o.id "+
        		 "  and a.state_id = s.id "+
        		   "   and a.asset_code = da.asset_no) "+
        		   " group by flag1,flag ";
		Query query =  getSession().createSQLQuery(sql);
		return query.list();
	}

	@Override
	public Integer getDamageAssetCount() {
		// TODO Auto-generated method stub
		String sql = "               select count(a.id) "+
               " from t_asset a, t_asset_owner o,T_ASSET_STATE s "+
               "   where a.ASSET_OWNER_INFO_ID(+) = o.id  "+
               " and a.state_id = s.id "+
               " and s.use_state = '报废'";
		Query query = getSession().createSQLQuery(sql);
		BigDecimal res = (BigDecimal) getSession().createSQLQuery(sql).list().get(0);
		if(res == null){
			return new Integer(0);
		}
		return res.intValue();
	}

	@Override
	public Integer getOutOfServiceAssetCount() {
		// TODO Auto-generated method stub
		String sql =          "      select count(a.id) "+
				"  from t_asset a, t_asset_owner o,T_ASSET_STATE s "+
				"   where a.ASSET_OWNER_INFO_ID(+) = o.id  "+
				" and a.state_id = s.id  "+
				" and s.use_state != '使用' "+
				"  and s.use_state != '报废' "; 
		Query query = getSession().createSQLQuery(sql);
		BigDecimal res = (BigDecimal) getSession().createSQLQuery(sql).list().get(0);
		if(res == null){
			return new Integer(0);
		}
		return res.intValue();
	}

	@Override
	public List<Map> assetDamageChangeChart() {
		// TODO Auto-generated method stub
		/*报废资产年变化情况
-- select a.original_value 报废资产价值（纵坐标）, da.scrap_date 年度（横坐标）
--  from T_ASSET a, T_DAMAGE_ASSET da(主表)
-- a.asset_code = da.asset_no
 
  select  to_char(da.scrap_date,'yyyy') year, sum(a.original_value) original_value
  from  T_DAMAGE_ASSET da, T_ASSET a 
 where a.asset_code = da.asset_no
 group by to_char(da.scrap_date,'yyyy')
 order by to_char(da.scrap_date,'yyyy')
 
  	var result = [{"year":"2008","price":200},{"year":"2009", "price":300},
		{"year":"2010", "price":335},{"year":"2012", "price":400},{"year":"2014", "price":600}];
		*/
		
        String sql = "  select  to_char(a.approval_scrap_date,'yyyy') year, sum(a.original_value) original_value " +
                "      from   T_ASSET a  " +
                "        where a.approval_scrap_date is not null" +
                "             group by to_char(a.approval_scrap_date,'yyyy') " +
                "             order by to_char(a.approval_scrap_date,'yyyy')";

        Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
        SQLQuery query = session.createSQLQuery(sql);
        List<Object[]> list = query.list();
        List<Map> result = new ArrayList<Map>();
        if(list!=null){
            for (Object[] objects : list) {
                HashMap map = new HashMap();
                map.put("year", objects[0]);
 
                map.put("price", (getFormattedMoney((BigDecimal) objects[1]).doubleValue()));
                result.add(map);
            }
        }
        return result;
	}




	               



	 
	@Override
	public List<Map> assetDamageProfessionChart() {
		// TODO Auto-generated method stub
		/*	/*报废资产专业分布
	--  select  t.name(专业)?, a.original_value(价值) 
	--  from T_ASSET a, T_ASSET_STATE s, T_ASSET_TYPE t
	--  a.state_id = s.use_state = '报废' a.main_type_code_id = t.?
	  
	    select a.main_type_code_id,
	     ( select t.name from T_ASSET_TYPE t where t.id = a.main_type_code_id ) profession, nvl(sum(a.original_value), 0) original_value  
	  from T_ASSET a,T_ASSET_OWNER o, T_ASSET_STATE s
	              where a.ASSET_OWNER_INFO_ID(+) = o.id 
	               and a.state_id = s.id 
	               and s.use_state = '报废'
	               group by a.main_type_code_id
	               order by a.main_type_code_id*/
        String sql = " 	    select a.main_type_code_id, "+
	     " ( select t.name from T_ASSET_TYPE t where t.id = a.main_type_code_id ) profession, nvl(sum(a.original_value), 0) original_value  "+ 
	     " from T_ASSET a,T_ASSET_OWNER o, T_ASSET_STATE s "+
	     "      where a.ASSET_OWNER_INFO_ID(+) = o.id "+ 
	     "         and a.state_id = s.id  "+
	     "         and s.use_state = '报废' "+
	     "         group by a.main_type_code_id "+
	     "         order by a.main_type_code_id ";

        Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
        SQLQuery query = session.createSQLQuery(sql);
        List<Object[]> list = query.list();
        List<Map> result = new ArrayList<Map>();
        if(list!=null){
            for (Object[] objects : list) {
                HashMap map = new HashMap();
                map.put("name", objects[1]);
 
                map.put("value", (getFormattedMoney((BigDecimal) objects[2])).doubleValue());
                result.add(map);
            }
        }
        return result;
	}

	@Override
	public List<Map> assetDamageDepartmentChart() {
		// TODO Auto-generated method stub
/*	/*报废资产公司分布

	--  select d.name(公司), a.original_value(价值)
	--  from T_ASSET a, T_ASSET_STATE s,T_ASSET_OWNER ao,T_DEPARTMENT d
	--  a.state_id = s.use_state = '报废' a.asset_code = ao.id  ao.department_code_id = d.id
	  
	  select (select t.name from T_DEPARTMENT t where id = o.department_code_id) department_name,
	         nvl(sum(a.original_value), 0) original_value,
	         o.department_code_id

	    from T_ASSET a, T_ASSET_STATE s, T_ASSET_OWNER o
	   where a.ASSET_OWNER_INFO_ID(+) = o.id
	     and a.state_id = s.id
	     and s.use_state = '报废'

	   group by o.department_code_id
	   order by o.department_code_id*/
        String sql = "  	  select (select t.short_name from T_DEPARTMENT t where id = o.department_code_id) department_name, "+
        		"  nvl(sum(a.original_value), 0) original_value, "+
        		" o.department_code_id "+

		"  from T_ASSET a, T_ASSET_STATE s, T_ASSET_OWNER o "+
		"  where a.ASSET_OWNER_INFO_ID(+) = o.id "+
		"     and a.state_id = s.id "+
		"     and s.use_state = '报废' "+

	  " group by o.department_code_id "+
	  " order by o.department_code_id ";

        Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
        SQLQuery query = session.createSQLQuery(sql);
        List<Object[]> list = query.list();
        List<Map> result = new ArrayList<Map>();
        if(list!=null){
            for (Object[] objects : list) {
                HashMap map = new HashMap();
                map.put("name", objects[0]);
 
                map.put("value", (getFormattedMoney((BigDecimal) objects[1])).doubleValue());
                result.add(map);
            }
        }
        return result;
	}

	@Override
	public List<Map> assetDamageLineChart() {
		// TODO Auto-generated method stub
		/*	  /*报废资产线路分布
	--  select (线路)T_ASSET_OWNER ao ao.line_code_id, , (价值)
	--  from  T_ASSET a, T_ASSET_STATE s,
	--  a.state_id = s.use_state = '报废' 
	  
	       select o.line_code_id,
	              (select name from t_line t where id = o.line_code_id) line_name,
	              nvl(sum(a.original_value), 0) original_value
	         from t_asset a, t_asset_owner o, T_ASSET_STATE s
	        where a.ASSET_OWNER_INFO_ID(+) = o.id
	          and a.state_id = s.id          
	          and s.use_state = '报废'
	        group by o.line_code_id
	        order by o.line_code_id*/
        String sql = "  	       select o.line_code_id, "+
        		" (select short_name from t_line t where id = o.line_code_id) line_name, "+
        		"   nvl(sum(a.original_value), 0) original_value "+
        		"  from t_asset a, t_asset_owner o, T_ASSET_STATE s "+
        		"  where a.ASSET_OWNER_INFO_ID(+) = o.id "+
        		"   and a.state_id = s.id  "+         
        		"   and s.use_state = '报废' "+
        		" group by o.line_code_id "+
        		"  order by o.line_code_id ";

        Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
        SQLQuery query = session.createSQLQuery(sql);
        List<Object[]> list = query.list();
        List<Map> result = new ArrayList<Map>();
        if(list!=null){
            for (Object[] objects : list) {
                HashMap map = new HashMap();
                map.put("name", objects[1]);
 
                map.put("value", (getFormattedMoney((BigDecimal) objects[2])).doubleValue());  
                result.add(map);
            }
        }
        return result;
	}

	@Override
	public List<Object[]> findAssetTransfer(String start, String end,
			boolean hasCount) {
			String sql = "select p.count plan," +
                    "       sum(t.count) Actual," +
                    "       short_name," +
                    "       sum(t.count) / p.count * 100 zone" +
                    "  from DW_ASSET_PROJECT_LINE_VALUE t, t_project_transfer p" +
                    " where t.project_id = p.project_id" +
                    " and create_date=("+
                    " 		select max(t1.create_date)"+
                    " 		from DW_ASSET_PROJECT_LINE_VALUE t1"+
                    "   where t1.create_date >=" +
                    "       to_date('"+start+"', 'yyyy-mm-dd hh24:mi:ss')" +
                    "   and t1.create_date <=" +
                    "       to_date('"+end+"', 'yyyy-mm-dd hh24:mi:ss')" +
                    " )group by (short_name, p.count, line_id)" +
                    " order by to_number(line_id) asc";
			
			Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
			if(query.list()!=null && query.list().size()>0){
				return query.list();
			}
			return null;
	}

	@Override
	public List<Object[]> getAssetTransfer(String start, String end,
			boolean hasCount) {
		String sql = "select short_name,ORIGINAL_VALUE+Spare_Parts,(final_price+invest_added-reserve_estimation-tax),(ORIGINAL_VALUE+Spare_Parts)/(final_price+invest_added-reserve_estimation-tax)*100 from DW_ASSET_PROJECT_LINE_VALUE where create_Date=(select max(create_Date) from DW_ASSET_PROJECT_LINE_VALUE where  create_date >= to_date('"+start+"', 'yyyy-mm-dd hh24:mi:ss') and   create_date <=  to_date('"+end+"', 'yyyy-mm-dd hh24:mi:ss')) and (final_price+invest_added-reserve_estimation-tax) > 0  order by to_number(line_Id) ASC" ;
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		if(query.list()!=null && query.list().size()>0){  
			List<Object[]> result = query.list();
			for(Object[] obj : result){
				obj[1] = getFormattedMoney(((BigDecimal)obj[1]));
				obj[2] = getFormattedMoney(((BigDecimal)obj[2]));
			}
			return result;
		}
		return null;
	}

	//得到保留6位小数后的字符串
    public BigDecimal getFormattedMoney(BigDecimal money) {
        if (money == null || "".equals(money)) {
            money = BigDecimal.ZERO;
        }
        Double result = 0d;
        try {
            result = money.doubleValue()/10000;
        } catch (NumberFormatException e) {
            result = 0d;
        }
        
        DecimalFormat df = new DecimalFormat("#0.000000");
        if ("0.000000".equals(df.format(result))) {
            return BigDecimal.ZERO;
        }
        return BigDecimal.valueOf(result).setScale(6, BigDecimal.ROUND_HALF_UP);
    }
}

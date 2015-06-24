package com.wonders.asset.dao.impl;

import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.dao.LandAssetDao;
import com.wonders.asset.model.LandAsset;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

public class LandAssetDaoImpl extends BaseDaoImpl<LandAsset, String> implements LandAssetDao{

	public LandAssetDaoImpl() {
		super(LandAsset.class);
	}

	protected String getTableName(boolean isTem){
        return !isTem ? "T_LANDASSET D" : "T_LANDASSET_TMP T";
    }

    protected String getPk(){
        return "T_LANDASSET_ID";
    }

    protected String getTmpPk(){
        return "ASSET_NO";
    }

    protected StringBuffer getFields(){

        StringBuffer fields = new StringBuffer("CONTRACT_NO,CONTRACT_NAME,CARD_ID,ASSET_NO,OWNERSHIP_UNIT,ASSET_VALUE,");
        fields.append("PROJECT,LOCATION_NO,ASSET_TYPE,ASSET_NAME,AREA,WARRANTS_NO,BOOK_VALUE,LAND_USECERTIFICATE,");
        fields.append("OWN_CERTIFICATE,NOTE,KEEP_NAME,KEEP_DATE,REKEEP_NAME,REKEEP_DATE,USE_NAME,USE_DATE,");
        fields.append("SCRAP_NAME,SCRAP_DATE,IMPORT_TIME,IMPORT_PERSON,TRANS_DEP,TAKE_OVER_DEP,CLASSIFICATION_NAME,");
        fields.append("UNIT_CODE,LAND_STATUS,LAND_LOCATION,BUILDER_PROJECT,LAND_DH,STATE,APPROVE_NO,APPROVE_NO_OR_DECIDE,");
        fields.append("LAND_PLANING,BUILD_AREA,LAND_TOTAL_AREA,LAND_REQUISITION_AREA,INCLAND_REQUISITION_AREA,LAND_TOTAL_FEE,");
        fields.append("LAND_REQUISITION_TOTALFEE,INLAND_REQUISITION_TOTALFEE,HAS_OPENSPACE,OPEN_SPACEAREA,ID,USE_MANAGER,LINE_CODE_ID");
        return  fields;
    }

    public static void main(String[] args) {
//        DamageAssetDaoImpl impl = new DamageAssetDaoImpl();
//        System.out.println((Annotation)impl.getPersistentClass().getAnnotations()[0]);
    }

    @Override
    public List<Map<String, String>> findReports() {
        String sql="select * from (select L.SHORT_NAME,builder_project,to_char(sum(BUILD_AREA)),to_char(sum(LAND_TOTAL_AREA)),to_char(sum(LAND_REQUISITION_AREA)), "+
        			"to_char(sum(underground_requisition_area)),to_char(sum(INCLAND_REQUISITION_AREA)),to_char(sum(LAND_TOTAL_FEE)),to_char(sum(LAND_REQUISITION_TOTALFEE)), "+
        			"to_char(sum(INLAND_REQUISITION_TOTALFEE)),LINE_CODE_ID "+
        			"from t_landasset ,T_LINE L where builder_project is not null AND L.CODE_ID = LINE_CODE_ID "+ 
        			"group by builder_project,L.SHORT_NAME,LINE_CODE_ID "+ 
        			"union "+ 
        			"select '总计','',to_char(sum(sum(BUILD_AREA))),to_char(sum(sum(LAND_TOTAL_AREA))),to_char(sum(sum(LAND_REQUISITION_AREA))), "+
        			"to_char(sum(sum(underground_requisition_area))),to_char(sum(sum(INCLAND_REQUISITION_AREA))),to_char(sum(sum(LAND_TOTAL_FEE))),to_char(sum(sum(LAND_REQUISITION_TOTALFEE))),	"+
        			"to_char(sum(sum(INLAND_REQUISITION_TOTALFEE))),'' "+
        			"from t_landasset where builder_project is not null "+
        			"group by builder_project ) order by LINE_CODE_ID";

        List<Object[]> result= getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).list();
        List<Map<String,String>> list=new ArrayList<Map<String, String>>();
        for(Object[] o:result){
           Map<String,String> map=new HashMap<String, String>();
           map.put("line", (String)o[0]);
           map.put("builderProject",(String)o[1]);
             if(o[2]!=null){
                 map.put("totalBuildArea", (String)o[2]);
//                 map.put("totalBuildArea",((Long)o[1]).floatValue()+"");
             }else{
                 map.put("totalBuildArea","");
             }
            if(o[3]!=null){
                map.put("totalLandArea", (String)o[3]);

//                map.put("totalLandArea",((Long)o[2]).floatValue()+"");
            }else{
                map.put("totalLandArea","");
            }
            if(o[4]!=null){
            	//其中征地面积
                map.put("totalLandRequisitionArea", (String)o[4]);
//                map.put("totalLandRequisitionArea",((Long)o[3]).floatValue()+"");
            }else{
                map.put("totalLandRequisitionArea","");
            }
            if(o[5]!=null){
            	//地下征地
            	map.put("undergroundRequisitionArea", (String)o[5]);
            	
//                map.put("undergroundRequisitionArea",((BigDecimal)o[8]).floatValue()+"");
            }else{
            	map.put("undergroundRequisitionArea","");
            }
            if(o[6]!=null){
            	//其中带征地
                map.put("totalInclandRequisitionArea", (String)o[6]);
//                map.put("totalInclandRequisitionArea",((Long)o[4]).floatValue()+"");
            }else{
                map.put("totalInclandRequisitionArea","");
            }
            if(o[7]!=null){
            	map.put("totalLandTotalFee", (String)o[7]);
            }else{
            	map.put("totalLandTotalFee", "");
            }
            if(o[8]!=null){
            	//征地总费用
                map.put("totalLandRequisitionTotalfee", (String)o[8]);

//                map.put("totalLandRequisitionTotalfee",((BigDecimal)o[6]).floatValue()+"");
            }else{
                map.put("totalLandRequisitionTotalfee","");
            }
            if(o[9]!=null){
            	//带征地费用
                map.put("totalInlandRequisitionTotalfee", (String)o[9]);

//                map.put("totalInlandRequisitionTotalfee",((BigDecimal)o[7]).floatValue()+"");
            }else{
                map.put("totalInlandRequisitionTotalfee","");
            }
            
            list.add(map);
        }
        return list;
    }

	@Override
	public List<Object[]> getBuildAreaByLine() {
		// TODO Auto-generated method stub
		String sql = " select t.builder_project as builderProject, sum(t.land_total_area) as landTotalArea, sum(t.asset_value) as lSumAsset from T_LANDASSET t group by t.builder_project order by  translate(t.builder_project,'一二三四五六七八九十号','abcdefghija') "; 
		
		Query query = getSession().createSQLQuery(sql.toString());

		return query.list();
	}

//	@Override
//	public Pagination<LandAsset> findBy(Map<String, String> filterMap,
//			Map<String, String> sortMap, int startIndex, int pageSize) {
//		if(filterMap == null){
//			filterMap = new HashMap<String, String>();
//		}
//		String hql = "from LandAsset t ";
//		String countSql = "select count(*) from LandAsset t ";
//		String filterPart = "";
//		int filterCounter = 0;
//		if (!filterMap.isEmpty()) {
//			filterPart += " where ";
//			for (Iterator<String> i = filterMap.keySet().iterator(); i.hasNext();) {
//				if (filterCounter > 0 ) {
//					filterPart += " and ";
//				}
//				String paramName = i.next();
//				if(paramName.equals("project") && filterMap.get("project")!=null && !"".equals(filterMap.get("projecet"))) {
//					filterPart += "t." + paramName + " like '%" + filterMap.get(paramName)+"%'";
//				}else if(paramName.equals("builderProject") && filterMap.get("builderProject")!=null && !"".equals(filterMap.get("builderProject"))){
//					filterPart += "t.builderProject='" + filterMap.get(paramName)+"'";
//				}else if(paramName.equals("landStatus") && filterMap.get("landStatus")!=null && !"".equals(filterMap.get("landStatus"))){
//					filterPart += "t.landStatus='" + filterMap.get(paramName)+"'";
//				}else if(paramName.equals("landPlaning") && filterMap.get("landPlaning")!=null && !"".equals(filterMap.get("landPlaning"))){
//					filterPart += "t.landPlaning like '%" + filterMap.get(paramName)+"%'";
//				}else if(paramName.equals("landTotalAreaStart") && filterMap.get("landTotalAreaStart")!=null && !"".equals(filterMap.get("landTotalAreaStart"))){
//					filterPart += "to_number(t.landTotalArea) >= " + filterMap.get(paramName);
//				}else if(paramName.equals("landTotalAreaEnd") && filterMap.get("landTotalAreaEnd")!=null && !"".equals(filterMap.get("landTotalAreaEnd"))){
//					filterPart += "to_number(t.landTotalArea) <= " + filterMap.get(paramName);
//				}else if(paramName.equals("publish") && filterMap.get("publish")!=null && !"".equals(filterMap.get("publish"))){
//					filterPart += "t.publish='" + filterMap.get(paramName)+"'";
//				}
//				filterCounter++;
//			}
//		}
//		
//		Long totalCount = (Long) getHibernateTemplate().getSessionFactory().getCurrentSession()
//									.createQuery(countSql+filterPart).uniqueResult();
//		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql+filterPart);
//		int pageNo = startIndex / pageSize + 1;
//		int totalPages = (totalCount > 0) ? (int) Math.ceil((double) totalCount
//				/ pageSize) : 1;
//
//		if (startIndex < 0)
//			startIndex = 0;
//		if (startIndex > totalCount)
//			startIndex = (totalPages - 1) * pageSize;
//
//		if (startIndex >= 0) {
//			query.setFirstResult(startIndex);
//		}
//		if (pageSize > 0) {
//			query.setMaxResults(pageSize);
//		}
//		List<LandAsset> result = query.list();
//		
//		return new Pagination<LandAsset>(pageNo, totalPages, totalCount, result);
//	}
	
	
}

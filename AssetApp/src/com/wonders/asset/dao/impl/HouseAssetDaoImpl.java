package com.wonders.asset.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.dao.HouseAssetDao;
import com.wonders.asset.model.HouseAsset;

public class HouseAssetDaoImpl extends BaseDaoImpl<HouseAsset, String> implements HouseAssetDao {

    @SuppressWarnings("unchecked")
    @Override
    public Pagination<HouseAsset> findHouseAssetAreaInfo(Map<String, String> houseAssetFilter,
                                                         Map<String, String> areaInfoFilter, Map<String, String> sortMap, int pageNo, int pageSize) {

        Criteria criteria = getSession().createCriteria(HouseAsset.class);
        if (houseAssetFilter != null && !houseAssetFilter.isEmpty()) {
            if (StringUtils.isNotBlank(houseAssetFilter.get("assetName"))) {
                criteria.add(Restrictions.like("assetName", houseAssetFilter.get("assetName"), MatchMode.ANYWHERE));
            }
            if (StringUtils.isNotBlank(houseAssetFilter.get("bigLine"))) {
                criteria.add(Restrictions.eq("line", houseAssetFilter.get("bigLine")));
            }
            if (StringUtils.isNotBlank(houseAssetFilter.get("line"))) {
                criteria.add(Restrictions.eq("builderProject", houseAssetFilter.get("line")));
            }
            if (StringUtils.isNotBlank(houseAssetFilter.get("station"))) {
                criteria.add(Restrictions.eq("station", houseAssetFilter.get("station")));
            }
            if (StringUtils.isNotBlank(houseAssetFilter.get("houseType"))) {
                criteria.add(Restrictions.eq("houseType", houseAssetFilter.get("houseType")));
            }
            if (StringUtils.isNotBlank(houseAssetFilter.get("buildAreaStart"))) {
                Double start = Double.parseDouble(houseAssetFilter.get("buildAreaStart"));
                criteria.add(Restrictions.ge("buildArea", start));
            }
            if (StringUtils.isNotBlank(houseAssetFilter.get("buildAreaEnd"))) {
                Double end = Double.parseDouble(houseAssetFilter.get("buildAreaEnd"));
                criteria.add(Restrictions.le("buildArea", end));
            }
        }
        if (areaInfoFilter != null && !areaInfoFilter.isEmpty()) {
            criteria.createAlias("areaInfos", "areaInfos");
            if (StringUtils.isNotBlank(areaInfoFilter.get("useType"))) {
                criteria.add(Restrictions.like("areaInfos.useType", areaInfoFilter.get("useType"), MatchMode.ANYWHERE));
            }
            if (StringUtils.isNotBlank(areaInfoFilter.get("takeOverDep"))) {
                criteria.add(Restrictions.like("areaInfos.takeOverDep", areaInfoFilter.get("takeOverDep"), MatchMode.ANYWHERE));
            }
        }

//		criteria.setProjection(null);
        long totalCount = (Integer) criteria.list().size();
        int startIndex = (pageNo - 1) * pageSize;
        if (startIndex >= 0) {
            criteria.setFirstResult(startIndex);
        }
        if (pageSize > 0) {
            criteria.setMaxResults(pageSize);
        }

        List<HouseAsset> result = criteria.list();
        int totalPages = (totalCount > 0) ? (int) Math.ceil((double) totalCount
                / pageSize) : 1;

        if (startIndex < 0)
            startIndex = 0;
        if (startIndex > totalCount)
            startIndex = (totalPages - 1) * pageSize;


        return new Pagination<HouseAsset>(pageNo, totalPages, totalCount, result);

    }


    public HouseAssetDaoImpl() {
        super(HouseAsset.class);
    }

    protected String getTableName(boolean isTem) {
        return !isTem ? "T_HOUSE_ASSET D" : "T_HOUSE_ASSET_TMP T";
    }

    protected String getPk() {
        return "HOUSE_ASSET_ID";
    }

    protected String getTmpPk() {
        return "ASSET_NO";
    }

    protected StringBuffer getFields() {

        StringBuffer fields = new StringBuffer("CONTRACT_NO,CARD_ID,PROJECT,ASSET_NO,ASSET_NAME,ASSET_TYPE,LOCATION_NO,");
        fields.append("UNIT,QUANTITY,COMPLETION_DATE,ASSET_LIFE_YEAR,CERTIFICATE_NO,PROJECT_VALUE,ANCILLARY_FACILITIES,");
        fields.append("CONTRACT_UNIT,DESIGN_UNIT,CONSTRUCTION_UNIT,SUPERVISING_COMPANY,OWNERSHIP_UNIT,WARRANTS_NO,ASSET_VALUE,");
        fields.append("TRANSFER_TIME,TRANS_DEP,TRANS_DEPLEADER,TAKEOVER_LEADER,IMPORT_TIME,IMPORT_PERSON,NOTE,KEEP_NAME,");
        fields.append("KEEP_DATE,REKEEP_NAME,REKEEP_DATE,USE_NAME,USE_DATE,SCRAP_NAME,SCRAP_DATE,CLASSIFICATION_NAME,STATE,PROPERTY_RIGHT_NO,");
        fields.append("LINE,BUILDER_PROJECT,STATION,BUILD_AREA,GROUND_FLOOR,UNDERGROUND_FLOOR,COMPLETED_LICENSE,PLAN_LICENSE,ID,HOUSE_TYPE");
        return fields;
    }

    public static void main(String[] args) {
//        DamageAssetDaoImpl impl = new DamageAssetDaoImpl();
//        System.out.println((Annotation)impl.getPersistentClass().getAnnotations()[0]);
    }


    @Override
    public List<Object[]> getLineBuildAreaReport() {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from (select (select short_name");
        sql.append("		from T_LINE l");
        sql.append("		where l.id=h.line),sum(build_area),h.line,0 order_ ");
        sql.append("from T_HOUSE_ASSET h,T_AREA_INFO t ");
        sql.append("where line is not null and h.house_asset_id = t.house_asset_id and t.use_type='商业经营' ");
        sql.append("group by line ");
        sql.append("union ");
        sql.append("select '合计',sum(build_area),'',1 order_ ");
        sql.append("from T_HOUSE_ASSET h,T_AREA_INFO t ");
        sql.append("where h.house_asset_id = t.house_asset_id and t.use_type='商业经营' ");
        sql.append("order by order_ ) order by line");
        Query query = getSession().createSQLQuery(sql.toString());
        return query.list();
    }


    @Override
    public List<Object[]> getLineStationAreaReport() {
        StringBuilder sql = new StringBuilder();
        sql.append("select line,station,area,builder_project,use_type,take_over_dep,really_area from (select (select l.name ");
        sql.append("from T_LINE l ");
        sql.append("where h.line=l.id) line,h.station station, sum(build_area) area,h.builder_project,T.USE_TYPE,T.TAKE_OVER_DEP,SUM(T.REALLY_AREA) REALLY_AREA,0 no,h.line lineid ");
        sql.append("from T_HOUSE_ASSET h, T_AREA_INFO T ");
        sql.append("where line is not null AND T.HOUSE_ASSET_ID = H.HOUSE_ASSET_ID ");        //去空
        sql.append("group by h.line,h.station,h.builder_project,T.USE_TYPE,T.TAKE_OVER_DEP ");
        sql.append("union ");
        sql.append("select '合计','',sum(build_area) area,'','','',sum(T.REALLY_AREA),1 no,'' ");
        sql.append("from T_HOUSE_ASSET h, T_AREA_INFO T ");
        sql.append("where line is not null AND T.HOUSE_ASSET_ID = H.HOUSE_ASSET_ID ");
        sql.append(") order by lineid ");
        Query query = getSession().createSQLQuery(sql.toString());
        return query.list();
    }

    @Override
    public List<Object[]> getUseTypeReport() {
        StringBuilder sql = new StringBuilder();
        sql.append("select take_over_dep,use_type,builder_project,area from (select a.take_over_dep, a.use_type, h.builder_project, sum(h.build_area) area ,0 no ");
        sql.append("from T_AREA_INFO a left outer join T_HOUSE_ASSET h ");
        sql.append("on  a.house_asset_id=h.house_asset_id ");
        sql.append("where a.take_over_dep is not null ");        //去空
        sql.append("group by take_over_dep, use_type, h.builder_project ");
        sql.append("union ");
        sql.append("select '合计',' ',builder_project,sum(build_area),1 no from t_area_info a ");
        sql.append("left outer join T_HOUSE_ASSET h ").
                append("on a.house_asset_id = h.house_asset_id ").
                append("where a.take_over_dep is not null ").
                append("group by  h.builder_project ").
                append(") order by no,take_over_dep desc ");


        Query query = getSession().createSQLQuery(sql.toString());
        return query.list();
    }

    @Override
    public List<Object[]> getUseTypeReportByBigLine() {
        StringBuilder sql = new StringBuilder();
        sql.append("select take_over_dep,use_type,short_name,area from (select a.take_over_dep, a.use_type, ( ");
        sql.append("select short_name ");
        sql.append("from t_line l ");
        sql.append("where l.id=h.line ");
        sql.append(") short_name,sum(h.build_area) area,0 no  ");
        sql.append("from T_AREA_INFO a left outer join T_HOUSE_ASSET h ");
        sql.append("on  a.house_asset_id=h.house_asset_id  ");
        sql.append("where a.take_over_dep is not null ");
        sql.append("group by take_over_dep, use_type, h.line  ");
        sql.append("union ");
        sql.append("select '合计',' ',l.short_name,sum(h.build_area),1 no  ");
        sql.append("from T_HOUSE_ASSET h,T_AREA_INFO a,t_line l  ");
        sql.append("where a.house_asset_id(+)=h.house_asset_id and h.line=l.id(+) and a.take_over_dep is not null  ");
        sql.append("group by l.short_name)  order by no,take_over_dep desc  ");
        Query query = getSession().createSQLQuery(sql.toString());
        return query.list();
    }

    @Override
    public Integer getUseTypeTakeOverDepRows() {
        String sql = "select count(distinct take_over_dep)*count(distinct use_type) from t_area_info";
        String count = getSession().createSQLQuery(sql).list().get(0).toString();
        return Integer.parseInt(count);
    }


    @Override
    public List<Object[]> getBuildAreaByLine() {
        // TODO Auto-generated method stub
        String sql = " select l.short_name as line, a.area as build_area, hSumAsset from (select t.line, sum(t.build_area) as area,sum(t.asset_value) as hSumAsset  from T_HOUSE_ASSET t group by t.line) a, t_line l where a.line = l.code order by a.line asc ";

        Query query = getSession().createSQLQuery(sql.toString());

        return query.list();


    }


}

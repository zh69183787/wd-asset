package com.wonders.asset.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.dao.DwAssetProjectLineValueDao;
import com.wonders.asset.model.dw.DwAssetProjectLineValue;

@SuppressWarnings("unchecked")
public class DwAssetProjectLineValueDaoImpl extends BaseDaoImpl implements
		DwAssetProjectLineValueDao {

	public DwAssetProjectLineValueDaoImpl() {
		super(DwAssetProjectLineValue.class);
	}

	@Override
	public List<DwAssetProjectLineValue> findAssetProjectLineValue() {
		String hql = "from DwAssetProjectLineValue t where t.createDate=(select max(t2.createDate) from DwAssetProjectLineValue t2) order by to_number(t.lineId) ASC";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public List<Object[]> findLineAndCountProject() {
		String sql = "select l.id,l.short_name,b.countnum from t_line l," +
				"(select d.line_id lid ,count(*) countnum from dw_asset_project_line_value d group by d.line_id) b where l.id = b.lid";
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createSQLQuery(sql);
		return query.list();
	}

	@Override
	public List<DwAssetProjectLineValue> findAssetProjectLineValueByDate(
			String start, String end) {
		String hql = "from DwAssetProjectLineValue t " +
				" where t.createDate=(" +
				" select max(t2.createDate) from DwAssetProjectLineValue t2 where t2.createDate>= to_date('"+start+"','yyyy-mm-dd hh24:mi:ss') and t2.createDate<=to_date('"+end+"','yyyy-mm-dd hh24:mi:ss')) " +
				" order by to_number(t.lineId) ASC";
		return getHibernateTemplate().find(hql);
	}


    @Override
    public List<DwAssetProjectLineValue> findAssetProjectLineValueByDate(String start, String end,boolean hasCount) {
        String hql = "from DwAssetProjectLineValue t " +
		        " where t.createDate=(" +
				" select max(t2.createDate) from DwAssetProjectLineValue t2 where t2.createDate>= to_date('"+start+"','yyyy-mm-dd hh24:mi:ss') and t2.createDate<=to_date('"+end+"','yyyy-mm-dd hh24:mi:ss')) " ;
        if(hasCount)
            hql += "and t.count > 0";

        hql+=" order by to_number(t.lineId) ASC";
        return getHibernateTemplate().find(hql);
    }

    @Override
    public Map count(String start, String end) {
        Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
        BigDecimal total = (BigDecimal)session.createSQLQuery("SELECT COUNT(1) FROM T_PROJECT_TRANSFER WHERE YEAR = "+end.split("-")[0]).uniqueResult();
        String sql ="select sum(count) totalAssetNum," +
                "       sum(decode(nvl(original_value, 0), 0, 0, 1)) originalValueNum," +
                "       sum(original_value) totalOriginalValue," +
                "       sum(decode(original_value - final_price - nvl(invest_added, 0) +" +
                "                  nvl(tax, 0) + nvl(reserve_estimation, 0)," +
                "                  0," +
                "                  1," +
                "                  0)) ||'/'|| "+total.intValue()+" transfer" +
                "  from Dw_Asset_Project_Line_Value t" +
                " where t.create_Date >=" +
                "       to_date('"+start+"', 'yyyy-mm-dd hh24:mi:ss')" +
                "   and t.create_Date <=" +
                "       to_date('"+end+"', 'yyyy-mm-dd hh24:mi:ss')";

        SQLQuery query = session.createSQLQuery(sql);
        Object[] objects = (Object[])query.uniqueResult();
        if(objects!=null){
            HashMap result = new HashMap();
            result.put("totalAssetNum",objects[0]);
            result.put("originalValueNum",objects[1]);
            result.put("totalOriginalValue",objects[2]);
            result.put("transfer",objects[3]);
            result.put("total",total.intValue());
            return result;
        }
        return null;
    }

    @Override
    public List<Map> countByDepartment(String start, String end) {
       String sql = "select department_code_id,(select short_name from t_department t where code = department_code_id)," +
               "nvl(sum(a.original_value),0) original_value from t_asset a,t_asset_owner o where a.ASSET_OWNER_INFO_ID(+) = o.id " +
               "   and a.complete_trans_asset_type in ('新增', '更新')" +
               "  and a.create_date >= to_date('"+start+"', 'yyyy-mm-dd hh24:mi:ss') and " +
               "  a.create_date <=  to_date('"+end+"', 'yyyy-mm-dd hh24:mi:ss') " +
               "group by o.department_code_id";
        Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
        SQLQuery query = session.createSQLQuery(sql);
        List<Object[]> list = query.list();
        List<Map> result = new ArrayList<Map>();
        if(list!=null){
            for (Object[] objects : list) {
                HashMap map = new HashMap();
                map.put("id", objects[0]);
                map.put("name", objects[1]);
                map.put("originalValue", ((BigDecimal) objects[2]).doubleValue());
                result.add(map);
            }
        }
        return result;
    }

    @Override
    public List<Map> countByLine(String start, String end) {
        String sql = "select o.line_code_id," +
                "       (select short_name from t_line t where id = o.line_code_id) line_name," +
                "       nvl(sum(a.original_value),0) original_value" +
                "  from t_asset a, t_asset_owner o" +
                " where a.ASSET_OWNER_INFO_ID(+) = o.id" +
                "   and a.complete_trans_asset_type in ('新增', '更新')" +
                "  and a.create_date >= to_date('"+start+"', 'yyyy-mm-dd hh24:mi:ss') and " +
                "a.create_date <=  to_date('"+end+"', 'yyyy-mm-dd hh24:mi:ss') group by o.line_code_id";

        Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
        SQLQuery query = session.createSQLQuery(sql);
        List<Object[]> list = query.list();
        List<Map> result = new ArrayList<Map>();
        if(list!=null){
            for (Object[] objects : list) {
                HashMap map = new HashMap();
                map.put("id", objects[0]);
                map.put("name", objects[1]);
                map.put("originalValue", ((BigDecimal) objects[2]).doubleValue());
                result.add(map);
            }
        }
        return result;
    }

    @Override
    public List<Map> countByAssetType(String start, String end) {
     String sql="select a.main_type_code_id," +
             "       (select name from t_asset_type t where code_id = main_type_code_id) main_type_name," +
             "       nvl(sum(a.original_value),0) original_value,rank () OVER(order by sum(a.original_value) desc)" +
             "  from t_asset a" +
             " where a.main_type_code_id > 0" +
             " and a.complete_trans_asset_type in ('新增', '更新')" +
             "  and a.create_date >= to_date('"+start+"', 'yyyy-mm-dd hh24:mi:ss') and " +
             "  a.create_date <=  to_date('"+end+"', 'yyyy-mm-dd hh24:mi:ss') " +
             " group by a.main_type_code_id";
        Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
        SQLQuery query = session.createSQLQuery(sql);
        List<Object[]> list = query.list();
        List<Map> result = new ArrayList<Map>();
        if(list!=null){
            for (Object[] objects : list) {
                HashMap map = new HashMap();
                map.put("id",objects[0]);
                map.put("name",objects[1]);
                map.put("originalValue",((BigDecimal)objects[2]).doubleValue());
                map.put("rank",((BigDecimal)objects[3]).intValue());
                result.add(map);
            }
        }
        return result;
    }

    @Override
    public float getDwAssetProjectLineValueRate() {

        String sql="select sum(original_value )/sum(final_price+invest_added-reserve_estimation-tax) from dw_asset_project_line_value where  count>0";

        SQLQuery query= getSession().createSQLQuery(sql);
        Object obj =query.uniqueResult();
        if(obj != null){

            float firt=Float.valueOf( obj.toString())*100;

            return firt;
        }else
            return 0;
    }


    @Override
	public List<String> findAllYearOfDwAssetProjectLineValue() {
		String sql="select to_char(t.create_date,'yyyy') year from DW_ASSET_PROJECT_LINE_VALUE t group by to_char(t.create_date,'yyyy') order by to_char(t.create_date,'yyyy') desc";
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);
		List<String> list = query.list();
		return list;
	}


}

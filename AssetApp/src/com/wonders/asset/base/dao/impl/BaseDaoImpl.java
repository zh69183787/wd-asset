package com.wonders.asset.base.dao.impl;

import static org.apache.commons.lang.StringUtils.countMatches;
import static org.apache.commons.lang.StringUtils.split;
import static org.apache.commons.lang.StringUtils.substringAfter;
import static org.apache.commons.lang.StringUtils.substringBefore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.base.util.Pagination;
import com.wonders.framework.core.dao.hibernate.GenericDaoImpl;


@SuppressWarnings("unchecked")
public class BaseDaoImpl<T, PK extends Serializable> extends
	GenericDaoImpl<T, PK> implements BaseDao<T, PK> {

	public BaseDaoImpl(Class<T> persistentClass) {
		super(persistentClass);
	}

	@Override
	public Pagination<T> findBy(Map<String, String> filterMap,
			Map<String, String> sortMap, int startIndex, int pageSize) {
		Criteria criteria = createCriteria(filterMap, null);

		long totalCount = (Integer) criteria.setProjection(Projections.rowCount()).uniqueResult();

		int pageNo = startIndex / pageSize + 1;
		int totalPages = (totalCount > 0) ? (int) Math.ceil((double) totalCount
				/ pageSize) : 1;

		if (startIndex < 0)
			startIndex = 0;
		if (startIndex > totalCount)
			startIndex = (totalPages - 1) * pageSize;

		criteria = createCriteria(filterMap, sortMap);
		if (startIndex >= 0) {
			criteria.setFirstResult(startIndex);
		}
		if (pageSize > 0) {
			criteria.setMaxResults(pageSize);
		}
		List<T> result = criteria.list();
		
		return new Pagination<T>(pageNo, totalPages, totalCount, result);
	}

	@Override
	public Pagination<T> findBy(Map<String, String> filterMap,
			Map<String, String> sortMap) {
		return findBy(filterMap, sortMap, -1, -1);
	}

	@Override
	public List<T> findByWithoutCount(Map<String, String> filterMap,
			Map<String, String> sortMap, int start, int limit) {
		Criteria criteria = createCriteria(filterMap, sortMap);
		if (start >= 0) {
			criteria.setFirstResult(start);
		}
		if (limit > 0) {
			criteria.setMaxResults(limit);
		}
		return criteria.list();
	}

	@Override
	public List<T> findByWithoutCount(Map<String, String> filterMap,
			Map<String, String> sortMap) {
		return findByWithoutCount(filterMap, sortMap, -1, -1);
	}
	
	public Criteria createCriteria(Map<String, String> filterMap,
			Map<String, String> sortMap) {

		Map<String, Criteria> subCriteriaMap = new HashMap<String, Criteria>();
		List<String> associationAliases = new ArrayList<String>();
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		if (filterMap != null && !filterMap.isEmpty()) {
			for (Map.Entry<String, String> entry : filterMap.entrySet()) {
				String[] splits = split(entry.getKey(), '_');
				String property = splits[0], op = splits[1], convertType = "";
				if (splits.length > 2)
					convertType = splits[2];
				try {
					String value = entry.getValue();
					int n = countMatches(property, ".");
					if (n == 0) {
						criteria.add(createCriterion(property, op, convertType,
								value));
					} else if (n == 1) {
						String name = substringBefore(property, ".");
						Criteria secCriteria = subCriteriaMap.get(name);
						if (secCriteria == null) {
							secCriteria = criteria.createCriteria(name);
							subCriteriaMap.put(name, secCriteria);
						}
						secCriteria.add(createCriterion(
								substringAfter(property, "."), op, convertType,
								value));
					} else if (n == 2) {
						String names[] = split(property, ".");
						String name1 = names[0], name2 = names[1], name3 = names[2];
						Criteria secCriteria = subCriteriaMap.get(name1);
						if (secCriteria == null) {
							secCriteria = criteria.createCriteria(name1);
							subCriteriaMap.put(name1, secCriteria);
						}
						if (!associationAliases.contains(name2)) {
							secCriteria.createAlias(name2, name2);
							associationAliases.add(name2);
						}
						secCriteria.add(createCriterion(name2 + '.' + name3,
								op, convertType, value));
					}
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}

		if (sortMap != null && !sortMap.isEmpty()) {
			addOrder(sortMap, criteria, subCriteriaMap);
		}

		return criteria;
	}

	public Criterion createCriterion(String property, String op, String type,
			String value) throws Exception {

		if (StringUtils.equals(op, "like")) {
			return Restrictions.like(property, value, MatchMode.ANYWHERE);
		} else if (StringUtils.equals(op, "llike")) {
			return Restrictions.like(property, value, MatchMode.START);
		} else if (StringUtils.equals(op, "in")) {
			return Restrictions.in(property, split(value, ","));
		} else if (StringUtils.equals(op, "notIn")) {
			return Restrictions.not(Restrictions
					.in(property, split(value, ",")));
		} else if (StringUtils.equals(op, "isNull")
				|| StringUtils.equals(op, "isNotNull")) {
			return (Criterion) MethodUtils.invokeMethod(
					Restrictions.class, op, property);
		} else if(StringUtils.equals(op, "equal")){
			return Restrictions.eq(property, value);
		} else if(StringUtils.equals(op, "before")){
			return Restrictions.le(property, Double.parseDouble(value));
		}else if(StringUtils.equals(op, "after")){
			return Restrictions.ge(property, Double.parseDouble(value));
		}else {
			return (Criterion) MethodUtils.invokeMethod(
					Restrictions.class, op,
					new Object[] { property, convert(value, type) });
		}
	}

	public void addOrder(Map<String, String> sortMap, Criteria c,
			Map<String, Criteria> secCriteriaMap) {

		for (Entry<String, String> entry : sortMap.entrySet()) {
			Criteria criteria = c;
			String sort = entry.getKey(), dir = entry.getValue();
			if (StringUtils.contains(sort, '.') && secCriteriaMap != null) {
				String name = substringBefore(sort, ".");
				criteria = secCriteriaMap.get(name);
				sort = substringAfter(sort, ".");
				if (criteria == null) {
					criteria = c.createCriteria(name, Criteria.LEFT_JOIN); //different
					secCriteriaMap.put(name, criteria);
				}
			}
			if (StringUtils.equalsIgnoreCase(dir, "asc")) {
				criteria.addOrder(Order.asc(sort));
			} else if (StringUtils.equalsIgnoreCase(dir, "desc")) {
				criteria.addOrder(Order.desc(sort));
			}
		}
	}

	public Object convert(String value, String convertType) throws Exception {
		if ("".equals(convertType) || "str".equals(convertType)) {
			return value;
		} else if ("date".equals(convertType)) {
			return DateUtils.parseDate(value, new String[] { "yyyy-MM-dd" });
		} else if ("time".equals(convertType)) {
			return DateUtils.parseDate(value,
					new String[] { "yyyy-MM-dd HH:mm:ss" });
		} else if ("boolean".equals(convertType)) {
			return Boolean.valueOf(value);
		} else if ("int".equals(convertType)) {
			return Integer.valueOf(value);
		} else if ("long".equals(convertType)) {
			return Long.valueOf(value);
		} else if ("float".equals(convertType)) {
			return Float.valueOf(value);
		} else if ("double".equals(convertType)) {
			return Double.valueOf(value);
		} else if ("short".equals(convertType)) {
			return Short.valueOf(value);
		}
		return value;
	}

	public void save(List entities) {
        for (int i = 0; i < entities.size(); i++) {
            getSession().save(entities.get(i));

            if (i % 50 == 0) // 以每50个数据作为一个处理单元，也就是我上面说的“一定的量”，这个量是要酌情考虑的

            {
                getSession().flush();
                getSession().clear();
            }
        }
        getSession().flush();
        getSession().clear();
    }

    public void batchUpdate() {
        StringBuffer sql = new StringBuffer();
        sql.append("UPDATE ").append(getTableName(false));
        sql.append("  SET (").append(getFields()).append(") = ");
        sql.append("  (SELECT ");
        sql.append(getFields());
        sql.append("  FROM (SELECT * FROM (SELECT T.*,ROW_NUMBER() OVER(PARTITION BY ").append(getTmpPk()).append(" ORDER BY UPLOAD_DATE DESC) AS NUM_ FROM ")
                .append(getTableName(true)).append(" ) T");
        sql.append("  WHERE NUM_ =1) T WHERE T.").append(getTmpPk()).append(" = D.").append(getTmpPk()).append(")");
        sql.append("  WHERE EXISTS (SELECT 1 FROM ").append(getTableName(true)).append(" WHERE T.").append(getTmpPk()).append(" = D.").append(getTmpPk()).append(")");
        System.out.println(sql.toString());
        getSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public void batchInsert() {
        StringBuffer sql = new StringBuffer();
        sql.append("INSERT INTO ").append(getTableName(false));
        sql.append("  (").append(getPk()).append(",").append(getFields()).append(")");
        sql.append("  SELECT ");
        sql.append("  SYS_GUID(),").append(getFields());
        sql.append("  FROM (SELECT * FROM (SELECT T.*,ROW_NUMBER() OVER(PARTITION BY ").append(getTmpPk())
                .append(" ORDER BY UPLOAD_DATE DESC) AS NUM_ FROM ").append(getTableName(true)).append(") T");
        sql.append("  WHERE NUM_=1 AND NOT EXISTS (SELECT 1 FROM ").append(getTableName(false)).append(" WHERE D.").append(getTmpPk()).append("=T.").append(getTmpPk()).append("))");

        System.out.println(sql.toString());
        getSession().createSQLQuery(sql.toString()).executeUpdate();
    }


    public void clear() {
        getSession().createSQLQuery("DELETE FROM "+getTableName(true)).executeUpdate();
    }

    protected String getTmpPk(){
        return "ID";
    }

    protected String getPk(){
        return "";
    }

    protected String getTableName(boolean isTmp){
        return "";
    }


    protected StringBuffer getFields(){
        return new StringBuffer();
    }
}

package com.wonders.framework.core.dao.hibernate;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wonders.framework.core.dao.GenericDao;
import com.wonders.framework.core.dto.PagedQueryObject;
import com.wonders.framework.core.dto.QueryObject;
import com.wonders.framework.core.dto.QueryObject.Criteria;
import com.wonders.framework.core.dto.QueryObject.CriteriaParameter;
import com.wonders.framework.core.dto.QueryObject.OrderBy;

/**
 * This class serves as the Base class for all other DAOs - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 * 
 * <p>
 * To register this class in your Spring context file, use the following XML.
 * 
 * <pre>
 *      &lt;bean id=&quot;fooDao&quot; class=&quot;com.huateng.framework.dao.hibernate.GenericDaoHibernate&quot;&gt;
 *          &lt;constructor-arg value=&quot;com.huateng.framework.model.Foo&quot;/&gt;
 *          &lt;property name=&quot;sessionFactory&quot; ref=&quot;sessionFactory&quot;/&gt;
 *      &lt;/bean&gt;
 * </pre>
 * 
 * @author cheney.yuan
 * @param <T>
 *            a type variable
 * @param <PK>
 *            the primary key for that type
 */
public abstract class GenericDaoImpl<T, PK extends Serializable> extends
		HibernateDaoSupport implements GenericDao<T, PK> {
	/**
	 * Log variable for all child classes. Uses LogFactory.getLog(getClass())
	 * from Commons Logging
	 */
	protected final Log log = LogFactory.getLog(getClass());

	/**
	 * 日志句柄
	 */
	private Logger logger = Logger.getLogger(this.getClass().getName());

	/**
	 * 持久化类
	 */
	private Class<T> persistentClass;

	/**
	 * Constructor that takes in a class to see which type of entity to persist
	 * 
	 * @param persistentClass
	 *            the class type you'd like to persist
	 */
	public GenericDaoImpl(final Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}
	
	public Class<T> getPersistentClass(){
		return this.persistentClass;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return super.getHibernateTemplate().loadAll(this.persistentClass);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public T findByPrimaryKey(PK id) {
		T entity = (T) super.getHibernateTemplate().get(this.persistentClass,
				id);

		if (entity == null) {
			log.warn("Uh oh, '" + this.persistentClass + "' object with id '"
					+ id + "' not found...");
			throw new ObjectRetrievalFailureException(this.persistentClass, id);
		}

		return entity;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public boolean exists(PK id) {
		T entity = (T) super.getHibernateTemplate().get(this.persistentClass,
				id);
		return entity != null;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public PK insert(T object) {
		return (PK) super.getHibernateTemplate().save(object);
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(T object) {
		super.getHibernateTemplate().update(object);
	}

	/**
	 * {@inheritDoc}
	 */
	public void remove(PK id) {
		super.getHibernateTemplate().delete(this.findByPrimaryKey(id));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.j2ee.jpa.dao.GenericDao#count(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public long getResultCount(QueryObject queryObject) {
		String hql = "select count(*) from " + this.persistentClass.getName()
				+ " obj";
		QueryObjectResult result = getQueryObjectResult(hql, queryObject, false);
		final String ql = result.getReturnQL();
		final Map<String, Object> paramMap = result.getParameterMap();

		List countList = getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(ql);
						for (Iterator<String> ite = paramMap.keySet()
								.iterator(); ite.hasNext();) {
							String key = ite.next();
							Object param = paramMap.get(key);
							query.setParameter(key, param);
						}
						return query.list();
					}
				});
		return Long.parseLong(countList.get(0).toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.j2ee.jpa.dao.GenericDao#findByParametersQuery(java.util.Map,
	 * java.lang.String, boolean, int, int)
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByPagedQueryObject(
			final PagedQueryObject pagedQueryObject) {

		String hql = "select obj from " + this.persistentClass.getName()
				+ " obj";
		QueryObjectResult result = getQueryObjectResult(hql, pagedQueryObject,
				false);
		final String ql = result.getReturnQL();
		final Map<String, Object> paramMap = result.getParameterMap();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(ql);
				for (Iterator<String> ite = paramMap.keySet().iterator(); ite
						.hasNext();) {
					String key = ite.next();
					Object param = paramMap.get(key);
					query.setParameter(key, param);
				}
				query.setFirstResult(
						(pagedQueryObject.getPageNo() - 1)
								* pagedQueryObject.getPageSize())
						.setMaxResults(pagedQueryObject.getPageSize());
				return query.list();
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.j2ee.jpa.dao.GenericDao#findByParametersQuery(java.util.Map,
	 * java.lang.String, boolean)
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByQueryObject(QueryObject queryObject) {
		String hql = "select obj from " + this.persistentClass.getName()
		+ " obj";
		QueryObjectResult result = getQueryObjectResult(hql, queryObject, true);
		final String ql = result.getReturnQL();
		final Map<String, Object> paramMap = result.getParameterMap();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(ql);
				for (Iterator<String> ite = paramMap.keySet().iterator(); ite
						.hasNext();) {
					String key = ite.next();
					Object param = paramMap.get(key);
					query.setParameter(key, param);
				}
				return query.list();
			}
		});

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.j2ee.jpa.dao.GenericDao#deleteByQueryObject(com.huateng.framework
	 * .core.dto.QueryObject)
	 */
	public void deleteByQueryObject(QueryObject queryObject) {
		String hql = "delete from " + this.persistentClass.getName() + " obj";
		QueryObjectResult result = getQueryObjectResult(hql, queryObject, false);
		final String ql = result.getReturnQL();
		final Map<String, Object> paramMap = result.getParameterMap();

//		Query query = super.getSession().createQuery(ql);
//		for (Iterator<String> ite = paramMap.keySet().iterator(); ite.hasNext();) {
//			String key = ite.next();
//			Object param = paramMap.get(key);
//			query.setParameter(key, param);
//		}
//		query.executeUpdate();
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(ql);
				for (Iterator<String> ite = paramMap.keySet().iterator(); ite
						.hasNext();) {
					String key = ite.next();
					Object param = paramMap.get(key);
					query.setParameter(key, param);
				}
				return query.executeUpdate();
			}
		});
	}

	/**
	 * 封装QueryObject处理的结果
	 * 
	 * @author cheney
	 * 
	 */
	@SuppressWarnings("unchecked")
	protected static class QueryObjectResult {
		/**
		 * 返回的QL
		 */
		protected String returnQL = "";

		/**
		 * 参数集合
		 */
		protected Map<String, Object> parameterMap = new HashMap();

		/**
		 * 得到返回的QL
		 * 
		 * @return
		 */
		public String getReturnQL() {
			return returnQL;
		}

		/**
		 * 设置返回的QL
		 * 
		 * @param returnQL
		 */
		public void setReturnQL(String returnQL) {
			this.returnQL = returnQL;
		}

		/**
		 * 得到参数集合
		 * 
		 * @return
		 */
		public Map<String, Object> getParameterMap() {
			return parameterMap;
		}

		/**
		 * 设置参数集合
		 * 
		 * @param parameterMap
		 */
		public void setParameterMap(Map<String, Object> parameterMap) {
			this.parameterMap = parameterMap;
		}

	}

	/**
	 * 处理queryObject
	 * 
	 * @param hql
	 * @param queryObject
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected QueryObjectResult getQueryObjectResult(String ql,
			QueryObject queryObject, boolean includeOrderByClause) {
		int index = 0;
		Map<String, Object> paramMap = new HashMap();
		QueryObjectResult result = new QueryObjectResult();
		if (queryObject != null) {
			List<Criteria> orCriteriaList = queryObject.getOredCriteriaList();
			List<String> whereClauseList = new ArrayList();
			for (Criteria cri : orCriteriaList) {
				int innerIndex = 0;
				StringBuffer innerHQL = new StringBuffer();
				// 无参数的查询条件
				List<CriteriaParameter> withoutValueList = cri
						.getCriteriaWithoutValue();
				for (CriteriaParameter param : withoutValueList) {
					if (innerIndex > 0) {
						innerHQL.append(" and ");
					}
					innerHQL.append(param.getPropertity() + " "
							+ param.getOperator());
					innerIndex++;
				}
				// 单值参数的查询条件
				List<CriteriaParameter> singleValueList = cri
						.getCriteriaWithSingleValue();
				for (CriteriaParameter param : singleValueList) {
					if (innerIndex > 0) {
						innerHQL.append(" and ");
					}
					if(param.getOperator().trim().equals("like") || param.getOperator().trim().equals("not like")){
//						innerHQL.append(param.getPropertity() + " " + param.getOperator()+ " :param_" + index + " escape '\\'");
						innerHQL.append(param.getPropertity() + " " + param.getOperator()+":param_" + index );
						String parameterValue = param.getValues().toString();
						if(parameterValue.startsWith("%") && parameterValue.endsWith("%")){
							parameterValue = parameterValue.substring(1, parameterValue.length() - 1);												
						}
						parameterValue = escapeSQLLike(parameterValue, '\\');		
						parameterValue = "%" + parameterValue + "%";
						paramMap.put("param_" + index, parameterValue);
					}
					else {
						innerHQL.append(param.getPropertity() + " " + param.getOperator()+ " :param_" + index );
						paramMap.put("param_" + index, param.getValues());
					}
//					innerHQL.append(param.getPropertity() + " "
//							+ param.getOperator() + " :param_" + index);
//					paramMap.put("param_" + index, param.getValues());
					innerIndex++;
					index++;
				}
				// 多值参数的查询条件
				List<CriteriaParameter> listValueList = cri
						.getCriteriaWithListValue();
				for (CriteriaParameter param : listValueList) {
					if (innerIndex > 0) {
						innerHQL.append(" and ");
					}
					List<Object> valueList = (List<Object>) param.getValues();
					String inClause = "(";
					for (int i = 0; i < valueList.size(); i++) {
						if (i > 0) {
							inClause += ",";
						}
						inClause += valueList.get(i);
					}
					inClause += ")";
					innerHQL.append(param.getPropertity() + " "
							+ param.getOperator() + inClause);
					innerIndex++;
				}
				// betweenAnd的查询条件
				List<CriteriaParameter> betweenAndValueList = cri
						.getCriteriaWithBetweenValue();
				for (CriteriaParameter param : betweenAndValueList) {
					if (innerIndex > 0) {
						innerHQL.append(" and ");
					}
					List<Object> valueList = (List<Object>) param.getValues();
					innerHQL.append(param.getPropertity() + " "
							+ param.getOperator() + " " + ":param_" + index
							+ " and " + ":param_" + (index + 1));
//					paramMap.put("param_" + (index+1), valueList.get(0));
//					paramMap.put("param_" + (index + 2), valueList.get(1));
					paramMap.put("param_" + index, valueList.get(0));
					paramMap.put("param_" + (index + 1), valueList.get(1));
					innerIndex++;
					index += 2;
				}

				// 加入到子句中
				if (innerHQL.toString().trim().length() > 0) {
					whereClauseList.add(innerHQL.toString().trim());
				}
			}
			String wholeWhereClause = "";
			int whereClauseIndex = 0;
			for (String whereClause : whereClauseList) {
				if (whereClauseIndex > 0) {
					wholeWhereClause += " or ";
				}
				if (whereClauseList.size() > 0) {
					wholeWhereClause += "(" + whereClause + " )";
				}
				whereClauseIndex++;
			}
			if (wholeWhereClause.trim().length() > 0) {
				wholeWhereClause = " where " + wholeWhereClause;
			}
			ql += wholeWhereClause;

			if (includeOrderByClause) {
				List<OrderBy> orderByList = queryObject.getOderByList();
				int innerIndex = 0;
				StringBuffer innerHQL = new StringBuffer();
				for (OrderBy orderBy : orderByList) {
					if (innerIndex > 0) {
						innerHQL.append(" , ");
					}
					innerHQL.append(orderBy.getPropertity() + " "
							+ orderBy.getOrderByType());
					innerIndex++;
				}
				String orderByClause = innerHQL.toString().trim();
				if (orderByClause.length() > 0) {
					ql += (" order by " + orderByClause);
				}

			}
		}

		result.setReturnQL(ql);
		result.setParameterMap(paramMap);
		return result;
	}

	public static String escapeSQLLike(String likeStr, char escapeChar) {
        String str = StringUtils.replace(likeStr, "_", escapeChar + "_");
        str = StringUtils.replace(str, "%", escapeChar + "%");
        str = StringUtils.replace(str, "?", "_");
        str = StringUtils.replace(str, "*", "%");
        return str;
    }
	
	public List findByNativeSql(String strSql,Map params) throws Exception
	{
		/*Query query = createSQLQuery(strSql);
		Iterator it = params.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String)it.next();
			query.setParameter(key, params.get(key));
		}
//		return query.list();
*/		
		final String  ql = strSql;
		final Map paramsMap = params;
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createSQLQuery(ql);
				for (Iterator<String> ite = paramsMap.keySet().iterator(); ite.hasNext();) {
					String key = ite.next();
					Object param = paramsMap.get(key);
					query.setParameter(key, param);
				}
				return query.list();
			}
		});
	}
}

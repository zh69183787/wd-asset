package com.wonders.framework.core.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wonders.framework.core.dao.GenericDao;
import com.wonders.framework.core.dto.PagedQueryObject;
import com.wonders.framework.core.dto.PagedResultObject;
import com.wonders.framework.core.dto.QueryObject;
import com.wonders.framework.core.dto.ResultObject;
import com.wonders.framework.core.service.GenericService;


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
public class GenericServiceImpl<T, PK extends Serializable> implements
		GenericService<T, PK> {
	/**
	 * Log variable for all child classes. Uses LogFactory.getLog(getClass())
	 * from Commons Logging
	 */
	protected final Log log = LogFactory.getLog(getClass());

	/**
	 * GenericDao instance, set by constructor of this class
	 */
	protected GenericDao<T, PK> genericDao;	


	/**
	 * set genericDao
	 * 
	 * @param genericDao
	 */
	public void setGenericDao(GenericDao<T, PK> genericDao) {
		this.genericDao = genericDao;
	}

	/**
	 * {@inheritDoc}
	 */	
	public List<T> findAll() {
		return genericDao.findAll();
	}

	/**
	 * {@inheritDoc}
	 */	
	public T findByPrimaryKey(PK id) {
		return genericDao.findByPrimaryKey(id);
	}

	/**
	 * {@inheritDoc}
	 */	
	public boolean exists(PK id) {
		return genericDao.exists(id);
	}

	/**
	 * {@inheritDoc}
	 */	
	public PK insert(T object) {
		return (PK) genericDao.insert(object);
	}

	/**
	 * {@inheritDoc}
	 */	
	public void update(T object) {
		genericDao.update(object);
	}

	/**
	 * {@inheritDoc}
	 */
	public void remove(PK id) {
		genericDao.remove(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.j2ee.jpa.dao.GenericDao#findByParametersQuery(java.util.Map,
	 *      java.lang.String, boolean, int, int)
	 */
	@SuppressWarnings("unchecked")
	public PagedResultObject<T> findByPagedQueryObject(
			PagedQueryObject pagedQueryObject) {
		PagedResultObject obj = new PagedResultObject();
		long count = genericDao.getResultCount(pagedQueryObject);
		List<T> resultList = genericDao
				.findByPagedQueryObject(pagedQueryObject);
		obj.setCount(count);
		obj.setObjectList(resultList);
		return obj;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.j2ee.jpa.dao.GenericDao#findByParametersQuery(java.util.Map,
	 *      java.lang.String, boolean)
	 */
	public ResultObject<T> findByQueryObject(QueryObject queryObject) {
		ResultObject<T> obj = new ResultObject<T>();
		List<T> resultList = genericDao.findByQueryObject(queryObject);
		obj.setObjectList(resultList);
		return obj;
	}
	
	public List findByNativeSql(String strSql,Map params) throws Exception 
	{
		return genericDao.findByNativeSql(strSql, params);
	}
	
}
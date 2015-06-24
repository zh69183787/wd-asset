package com.wonders.asset.base.service.impl;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.base.service.BaseService;
import com.wonders.asset.base.util.Pagination;

public class BaseServiceImpl<T, PK extends Serializable> implements BaseService<T, PK> {
	/**
	 * Log variable for all child classes. Uses LogFactory.getLog(getClass())
	 * from Commons Logging
	 */
	protected final Log log = LogFactory.getLog(getClass());

	/**
	 * BaseDao instance, set by constructor of this class
	 */
	protected BaseDao<T, PK> baseDao;	


	/**
	 * set baseDao
	 * 
	 * @param baseDao
	 */
	public void setBaseDao(BaseDao<T, PK> baseDao) {
		this.baseDao = baseDao;
	}

	/**
	 * {@inheritDoc}
	 */	
	public List<T> findAll() {
		return baseDao.findAll();
	}

	/**
	 * {@inheritDoc}
	 */	
	public T findByPrimaryKey(PK id) {
		return baseDao.findByPrimaryKey(id);
	}

	/**
	 * {@inheritDoc}
	 */	
	public boolean exists(PK id) {
		return baseDao.exists(id);
	}

	/**
	 * {@inheritDoc}
	 */	
	public PK insert(T object) {
		return (PK) baseDao.insert(object);
	}

	/**
	 * {@inheritDoc}
	 */	
	public void update(T object) {
		baseDao.update(object);
	}

	/**
	 * {@inheritDoc}
	 */
	public void remove(PK id) {
		baseDao.remove(id);
	}

	@Override
	public Pagination<T> findBy(Map<String, String> filterMap,
			Map<String, String> sortMap) {
		return findBy(filterMap, sortMap, -1, -1);
	}
	
	@Override
	public Pagination<T> findBy(Map<String, String> filterMap,
			Map<String, String> sortMap, int startIndex, int pageSize) {
		return baseDao.findBy(filterMap, sortMap, startIndex, pageSize);
	}

	@Override
	public List<T> findByWithoutCount(Map<String, String> filterMap,
			Map<String, String> sortMap) {
		return findByWithoutCount(filterMap, sortMap, -1, -1);
	}
	
	@Override
	public List<T> findByWithoutCount(Map<String, String> filterMap,
			Map<String, String> sortMap, int startIndex, int pageSize) {
		return baseDao.findByWithoutCount(filterMap, sortMap, startIndex, pageSize);
	}
}
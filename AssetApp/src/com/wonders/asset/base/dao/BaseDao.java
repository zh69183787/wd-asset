package com.wonders.asset.base.dao;


import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wonders.asset.base.util.Pagination;
import com.wonders.framework.core.dao.GenericDao;

/**
 * 公用Dao接口
 * @author Kai Yao
 */

public interface BaseDao<T, PK extends Serializable> extends GenericDao<T, PK> {
	
	public Pagination<T> findBy(Map<String, String> filterMap,
			Map<String, String> sortMap);
	
	public Pagination<T> findBy(Map<String, String> filterMap,
			Map<String, String> sortMap, int startIndex, int pageSize);
	
	public List<T> findByWithoutCount(Map<String, String> filterMap,
			Map<String, String> sortMap);
	
	public List<T> findByWithoutCount(Map<String, String> filterMap,
			Map<String, String> sortMap, int start, int limit);
	
	public void save(List entities) ;



}
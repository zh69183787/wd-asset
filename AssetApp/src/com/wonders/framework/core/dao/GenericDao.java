package com.wonders.framework.core.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.wonders.framework.core.dto.PagedQueryObject;
import com.wonders.framework.core.dto.QueryObject;

/**
 * Generic DAO (Data Access Object) with common methods to CRUD POJOs.
 * 
 * <p>
 * Extend this interface if you want typesafe (no casting necessary) DAO's for
 * your domain objects.
 * 
 * @author cheney.yuan
 * @param <T>
 *            a type variable
 * @param <PK>
 *            the primary key for that type
 */
public interface GenericDao<T, PK extends Serializable> {

	/**
	 * Generic method used to get all objects of a particular type. This is the
	 * same as lookup up all rows in a table.
	 * 
	 * @return List of populated objects
	 */
	public List<T> findAll();

	/**
	 * Generic method to get an object based on class and identifier. An
	 * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
	 * found.
	 * 
	 * @param id
	 *            the identifier (primary key) of the object to get
	 * @return a populated object
	 * @see org.springframework.orm.ObjectRetrievalFailureException
	 */
	public T findByPrimaryKey(PK id);

	/**
	 * Checks for existence of an object of type T using the id arg.
	 * 
	 * @param id
	 *            the id of the entity
	 * @return - true if it exists, false if it doesn't
	 */
	public boolean exists(PK id);

	/**
	 * Generic method to insert an object - handles both update and insert.
	 * 
	 * @param object
	 *            the object to save
	 * @return the persisted object
	 */
	public PK insert(T object);

	/**
	 * Generic method to insert an object - handles both update and insert.
	 * 
	 * @param object
	 *            the object to save
	 * @return the persisted object
	 */
	public void update(T object);

	/**
	 * Generic method to delete an object based on class and id
	 * 
	 * @param id
	 *            the identifier (primary key) of the object to remove
	 */
	public void remove(PK id);

	
	
	/**
	 * Find a list of records by giving parameters
	 * 
	 * @param queryName
	 *            query name of the named query
	 * @param queryParams
	 *            a map of the query names and the values
	 * @return a list of the records found
	 */
	public void deleteByQueryObject(QueryObject queryObject);

	/**
	 * Find a list of records by giving parameters
	 * 
	 * @param queryName
	 *            query name of the named query
	 * @param queryParams
	 *            a map of the query names and the values
	 * @return a list of the records found
	 */
	public List<T> findByQueryObject(QueryObject queryObject);

	/**
	 * find a list of records by giving parameters and page information
	 * 
	 * @param queryName
	 *            query name of the named query
	 * @param queryParams
	 *            a map of the query names and the values
	 * @return a list of the records found
	 */
	public List<T> findByPagedQueryObject(PagedQueryObject pagedQueryObject);

	/**
	 * get the count of records by giving queryObject
	 * 
	 * @param queryObject
	 * @return
	 */
	public long getResultCount(QueryObject queryObject);
	
	/**
	 * 使用原生sql查询
	 * @param strSql 
	 * @param params
	 * @return java.util.List
	 * @throws Exception
	 */
	public List findByNativeSql(String strSql,Map params) throws Exception ;

}
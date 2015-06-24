package com.wonders.asset.base.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.wonders.asset.base.util.Pagination;

public interface BaseService<T, PK extends Serializable> {
	
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

	public Pagination<T> findBy(Map<String, String> filterMap,
			Map<String, String> sortMap);

	public Pagination<T> findBy(Map<String, String> filterMap,
			Map<String, String> sortMap, int startIndex, int pageSize);

	public List<T> findByWithoutCount(Map<String, String> filterMap,
			Map<String, String> sortMap);

	public List<T> findByWithoutCount(Map<String, String> filterMap,
			Map<String, String> sortMap, int startIndex, int pageSize);
}

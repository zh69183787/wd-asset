package com.wonders.framework.core.dto;



/**
 * 
 * 封装查询类
 * 
 * @author cheney
 *
 */
@SuppressWarnings("serial")
public class UpdateByQueryObject<T> extends QueryObject{
	
	/**
	 * 对象
	 */
	protected T record;

	/**
	 * 得到对象
	 * @return
	 */
	public T getRecord() {
		return record;
	}

	/**
	 * 设置对象
	 * @param record
	 */
	public void setRecord(T record) {
		this.record = record;
	}	
	
}

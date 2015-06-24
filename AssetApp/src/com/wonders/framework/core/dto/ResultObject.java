package com.wonders.framework.core.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 封装查询的结果信息
 * 
 * @author cheney
 * 
 */
@SuppressWarnings("serial")
public class ResultObject<T> implements Serializable {

	/**
	 * 结果结合
	 */
	private List<T> objList = null;

	/**
	 * 得到结果列表
	 * 
	 * @return
	 */
	public List<T> getObjectList() {
		return objList;
	}

	/**
	 * 设置结果列表
	 * 
	 * @param list
	 */
	public void setObjectList(List<T> list) {
		this.objList = list;
	}

}

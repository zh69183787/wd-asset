package com.wonders.framework.core.dto;

import java.io.Serializable;

/**
 * 
 * 封装查询结果类
 * 
 * @author cheney
 * 
 */
@SuppressWarnings("serial")
public class PagedResultObject<T> extends ResultObject<T> implements
		Serializable {

	/**
	 * 总数
	 */
	private long count = 0;

	/**
	 * 得到总数
	 * 
	 * @return
	 */
	public long getCount() {
		return count;
	}

	/**
	 * 设置总数
	 * 
	 * @param count
	 */
	public void setCount(long count) {
		this.count = count;
	}

}

package com.wonders.framework.core.dto;

import java.io.Serializable;

/**
 * 
 * 封装查询条件类
 * 
 * @author cheney
 * 
 */
@SuppressWarnings("serial")
public class PagedQueryObject extends QueryObject implements Serializable{

	/**
	 * 当前第几页
	 */
	protected int pageNo;

	/**
	 * 每页个数
	 */
	protected int pageSize;
	

	/**
	 * 得到页数
	 * 
	 * @return
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * 设置页数
	 * 
	 * @param pageNo
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * 得到每页的大小
	 * 
	 * @return
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置每页的大小
	 * 
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 得到起始位置
	 * 
	 * @return
	 */
	public int getBeginPos() {
		return (pageNo - 1) * pageSize;
	}

	/**
	 * 得到结束位置
	 * 
	 * @return
	 */
	public int getEndPos() {
		return getBeginPos() + pageSize;
	}
	
}

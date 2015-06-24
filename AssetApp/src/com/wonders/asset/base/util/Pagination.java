package com.wonders.asset.base.util;

import java.util.Collections;
import java.util.List;

public class Pagination<T> {
	
	//-- 分页参数 --//
	private int pageNo;
	private int totalPages;
	private long totalCount;
    private int pageSize;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    //-- 返回结果 --//
	private List<T> result = Collections.emptyList();
	
	//-- 构造函数 --//
	public Pagination() {
		
	}
	
	public Pagination(List<T> result, int totalCount) {
		this.result = result;
		this.totalCount = totalCount;
	}

	public Pagination(int pageNo, int totalPages, long totalCount, List<T> result) {
		this.pageNo = pageNo;
		this.totalPages = totalPages;
		this.totalCount = totalCount;
		this.result = result;
	}
	
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}
	
}

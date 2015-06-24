package com.wonders.jeecms.model;

import java.util.List;

public class PageResultSet<T> {

	private List<T> list;  //当前页的数据信息
	private PageInfo pageInfo; //当前页的信息
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}
	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
}


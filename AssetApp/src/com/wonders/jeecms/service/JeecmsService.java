package com.wonders.jeecms.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wonders.jeecms.model.InfoChannelVo;
import com.wonders.jeecms.model.InfoDetailVo;
import com.wonders.jeecms.model.PageResultSet;


public interface JeecmsService {
	
	public PageResultSet<InfoDetailVo> queryByPage(HttpServletRequest servletRequest,HttpServletResponse servletResponse,String channelId,String searchWord,int pageSize, int page,String latestDays);
	
	public List<InfoChannelVo> findNewsByType(HttpServletRequest servletRequest,HttpServletResponse servletResponse,String parentId,String latestDays);

	public List<Object> findWsLeaders();
}

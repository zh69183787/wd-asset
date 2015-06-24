package com.wonders.jeecms.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.dom4j.DocumentException;

import com.opensymphony.xwork2.ActionSupport;
import com.wonders.framework.util.ServiceProvider;
import com.wonders.jeecms.ShowJeecmsInfo;
import com.wonders.jeecms.model.InfoChannelVo;
import com.wonders.jeecms.model.InfoDetailVo;
import com.wonders.jeecms.model.InfoSearchVo;
import com.wonders.jeecms.model.PageInfo;
import com.wonders.jeecms.model.PageResultSet;
import com.wonders.jeecms.service.JeecmsService;
import com.wonders.jeecms.utils.StringUtil;

public class JeecmsInfoAction extends ActionSupport {

	private JeecmsService jeecmsService;
	public JeecmsService getJeecmsService() {
		return jeecmsService;
	}

	public void setJeecmsService(JeecmsService jeecmsService) {
		this.jeecmsService = jeecmsService;
	}

	private InfoSearchVo infoSearchVo = new InfoSearchVo();
	private PageResultSet<InfoDetailVo> pageResultSet= new PageResultSet<InfoDetailVo>();

	public InfoSearchVo getInfoSearchVo() {
		return infoSearchVo;
	}

	public void setInfoSearchVo(InfoSearchVo infoSearchVo) {
		this.infoSearchVo = infoSearchVo;
	}

	public PageResultSet<InfoDetailVo> getPageResultSet() {
		return pageResultSet;
	}

	public void setPageResultSet(PageResultSet<InfoDetailVo> pageResultSet) {
		this.pageResultSet = pageResultSet;
	}

	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	public String getJeecmsInfo() throws IOException {
		String method = getRequest().getParameter("method");
		String channelId = getRequest().getParameter("channelId");
		String hasTitleImg = getRequest().getParameter("hasTitleImg");
		String typeId = getRequest().getParameter("typeId");
		String rownum = getRequest().getParameter("rownum");

		JSONObject jsonParameter = new JSONObject();
		jsonParameter.accumulate("channelId", channelId);
		jsonParameter.accumulate("hasTitleImg", hasTitleImg);
		jsonParameter.accumulate("typeId", typeId);
		jsonParameter.accumulate("rownum", rownum);

		ShowJeecmsInfo dwgk = new ShowJeecmsInfo(getRequest(), getResponse());
		JSONArray jsonResultList = dwgk.getInfoList(method, jsonParameter
				.toString());
		String jsonString = jsonResultList.toString();
		if (jsonResultList != null && jsonResultList.size() > 0) {
			// createJSonData(jsonResultList.toString());
			write(jsonString);
			return null;
		}
		return null;
	}

	public void write(String jsonString) throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(jsonString);
		out.flush();
		out.close();
	}

	public String findStfbNewsByPage() throws DocumentException {
		
		String channelId = getRequest().getParameter("channelId");
		String latestDays = getRequest().getParameter("latestDays");
		String page = getRequest().getParameter("page");
		
		String searchWord = getRequest().getParameter("searchWord");
		
		infoSearchVo = new InfoSearchVo();
		
		getRequest().setAttribute("channelId", channelId);
		getRequest().setAttribute("latestDays", latestDays);
		
		if(page!=null && !"".equals(page)){
			infoSearchVo.setPage(Integer.valueOf(page));
		}
		
		if(channelId!=null && !"".equals(channelId)){
			infoSearchVo.setChannelId(channelId);
		}
		if(searchWord!=null && !"".equals(searchWord)){
			infoSearchVo.setSearchWord(searchWord);
		}
		
		this.pageResultSet = ServiceProvider.getService(JeecmsService.class).queryByPage(getRequest(),getResponse(), channelId, infoSearchVo.getSearchWord(),infoSearchVo.getPageSize(), infoSearchVo.getPage(), latestDays);
		
		getRequest().setAttribute("infoSearchVo", infoSearchVo);
		getRequest().setAttribute("pageResultSet", pageResultSet);
		
		System.out.println("findStfbNewsByPage end.........");
		return "success";
	}

	public String findByPage() {
		
		String parentId = getRequest().getParameter("parentId");
		String latestDays = getRequest().getParameter("latestDays");
		
		this.getRequest().setAttribute("latestDays", latestDays);
		List<InfoChannelVo> channelList = jeecmsService.findNewsByType(getRequest(), getResponse(), parentId, latestDays); // 得到子栏目的list
		getRequest().setAttribute("channelList", channelList);
		getRequest().setAttribute("parentId", parentId);

		String channelId = getRequest().getParameter("channelId");

		// 查询数据

		/*
		 * if(sj_id!=null && !"".equals(sj_id.trim())){
		 * infoSearchVo.setSj_id(sj_id); }
		 */

		if (channelId != null && !"".equals(channelId.trim())) {
			infoSearchVo.setChannelId(channelId);
		} else {
			if (channelList != null && channelList.size() > 0) {
				infoSearchVo.setChannelId(channelList.get(0).getChannelId()
						+ "");
			}
		}
		getRequest().setAttribute("channelId", infoSearchVo.getChannelId());

		this.pageResultSet = jeecmsService.queryByPage(getRequest(),
				getResponse(), infoSearchVo.getChannelId(), infoSearchVo
						.getSearchWord(), infoSearchVo.getPageSize(),
				infoSearchVo.getPage(), latestDays);

		return "success";
	}

	public String findDateByPage() {
		String channelId = getRequest().getParameter("channelId");
		String latestDays = getRequest().getParameter("latestDays");
		this.pageResultSet = jeecmsService.queryByPage(getRequest(),
				getResponse(), channelId, infoSearchVo.getSearchWord(),
				infoSearchVo.getPageSize(), infoSearchVo.getPage(), latestDays);
		//String jsonData = VOUtils.getJsonData(pageResultSet);
		//createJSonData(jsonData);
		return "";
	}

	public String findIfWsLeader() {
		String loginName = (String) this.getRequest().getSession()
				.getAttribute("loginName");
		List<Object> list = jeecmsService.findWsLeaders();
		boolean flag = false;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).equals(loginName)) {
					flag = true;
					break;
				}
			}
		}
		//this.createJSonData("{\"ifLeader\":" + flag + "}");
		return "";
	}
	

	public String findNewsByPage() throws DocumentException{
		//pageResultSet = new PageResultSet<InfoDetailVo>();
		
		String channelId = getRequest().getParameter("channelId");
		String latestDays = getRequest().getParameter("latestDays");
		String page = getRequest().getParameter("page");
		
		String searchWord = getRequest().getParameter("searchWord");
		
		infoSearchVo = new InfoSearchVo();
		
		getRequest().setAttribute("channelId", channelId);
		getRequest().setAttribute("latestDays", latestDays);
		
		if(page!=null && !"".equals(page)){
			infoSearchVo.setPage(Integer.valueOf(page));
		}
		
		if(channelId!=null && !"".equals(channelId)){
			infoSearchVo.setChannelId(channelId);
		}
		if(searchWord!=null && !"".equals(searchWord)){
			infoSearchVo.setSearchWord(searchWord);
		}
		
		
		
		
		//this.pageResultSet = ServiceProvider.getService(JeecmsService.class).queryByPage(getRequest(),getResponse(), channelId, infoSearchVo.getSearchWord(),infoSearchVo.getPageSize(), infoSearchVo.getPage(), latestDays);

		ShowJeecmsInfo dwgk=new ShowJeecmsInfo(getRequest(),getResponse());

		String sJsonParams="";
		sJsonParams="{'channelId':'"+channelId+"'";
		if(searchWord!=null&&!searchWord.equals("")){
			sJsonParams += ",'title':'"+searchWord+"'";
		}
		if(latestDays!=null&&!latestDays.equals("")){
			sJsonParams += ",'latestDays':'"+latestDays+"'";
		}
		sJsonParams += "}";
		JSONArray jsonResultList = dwgk.getInfoList("JEECMS.SEARCH_CONTENT_NUM",sJsonParams);
		
		JSONObject obj=null; 
		if(jsonResultList.size()>0){obj=jsonResultList.getJSONObject(0);}
		int totalRow=obj.getInt("contentNum");
		
		PageInfo pageinfo = new PageInfo(totalRow, infoSearchVo.getPageSize(), infoSearchVo.getPage());	

		//获取该页的记录
		sJsonParams="{'channelId':'"+channelId+"','page':"+infoSearchVo.getPage()+",'rownum':"+infoSearchVo.getPageSize();
		if(searchWord!=null&&!searchWord.equals("")){
			sJsonParams += ",'title':'"+searchWord+"'";
		}
		if(latestDays!=null&&!latestDays.equals("")){
			sJsonParams += ",'latestDays':'"+latestDays+"'";
		}
		sJsonParams += "}";
		
		 jsonResultList = dwgk.getInfoList("JEECMS.SEARCH_CONTENT_LIST",sJsonParams);
		 if(null!=jsonResultList){
			 List<InfoDetailVo>list=new ArrayList<InfoDetailVo>();
				for(int i=0;i<jsonResultList.size();i++){
					obj=jsonResultList.getJSONObject(i);
					InfoDetailVo info=new InfoDetailVo();
					String description=obj.getString("description");
					String txt=obj.getString("txt");
					if(description=="null"){
						if(txt=="null"){
							description=StringUtil.MySubstring(obj.getString("title"), 150);
						}else{
							description=StringUtil.splitAndFilterString(txt, 150);
						}					
					}else{
						description=StringUtil.MySubstring(description, 150);
					}
					
					info.setChannelName(obj.getString("channelName"));
					info.setContentId(obj.getString("contentId"));
					info.setShortTitle(obj.getString("shortTitle"));
					if(StringUtils.isNotBlank(searchWord)){
						info.setTitle(obj.getString("title").replace(searchWord, "<font color='red'>"+searchWord+"</font>"));
						info.setDescription(description.replace(searchWord, "<font color='red'>"+searchWord+"</font>"));
					}else{
						info.setTitle(obj.getString("title"));
						info.setDescription(description);
					}
					info.setReleaseDate(obj.getString("releaseDate"));
			
					list.add(info);
				}
				
				pageResultSet.setList(list);
				pageResultSet.setPageInfo(pageinfo);

		 }
		 

		getRequest().setAttribute("infoSearchVo", infoSearchVo);
		getRequest().setAttribute("pageResultSet", pageResultSet);
		
		return "success";
	}
	
	
	
}

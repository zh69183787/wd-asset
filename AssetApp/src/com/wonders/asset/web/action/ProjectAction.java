package com.wonders.asset.web.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import net.sf.json.JsonConfig;

import org.apache.commons.lang.math.NumberUtils;
import org.dom4j.DocumentException;

import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.base.web.AbstractBaseAction;
import com.wonders.asset.model.Project;
import com.wonders.asset.service.ProjectService;
import com.wonders.framework.util.ServiceProvider;

public class ProjectAction extends AbstractBaseAction{
	private ProjectService projectService;

	public ProjectService getProjectService() {
		return projectService;
	}

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	/**
	 * 根据线路查询
	 * @throws DocumentException
	 * @throws IOException
	 */
	public void getProjectByLine() throws DocumentException, IOException{
		String codeId = getRequestParameter("codeId");
		List<Project> projects = ServiceProvider.getService(ProjectService.class).findByLineCodeId(codeId);
		JsonConfig jsonConfig = basicJsonCfg.copy();
		jsonConfig.registerPropertyExclusion(Project.class,"assets");
		renderJson(projects,jsonConfig);
	}
	
	/**
	 * 根据资产id查询
	 * @throws DocumentException 
	 * @throws IOException 
	 */
	public void inquery() throws DocumentException, IOException{
		try {
			Map<String,String> filter = createFilterMap();
			int startIndex = NumberUtils.toInt(getRequestParameter("jtStartIndex"), 0);
			int pageSize = NumberUtils.toInt(getRequestParameter("jtPageSize"), 10);
			Map<String,String> sort = createSortMap();
			Pagination<Project> projects;
			projects = ServiceProvider.getService(ProjectService.class).findBy(filter, sort, startIndex, pageSize);
			JsonConfig jsonConfig = basicJsonCfg.copy();
			renderJson(projects.getResult(), projects.getTotalCount(), jsonConfig);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 小线
	 * @throws Exception
	 */
	public void findShortName() throws Exception {
		List<Map<String,String>> smallLines 
			= ServiceProvider.getService(ProjectService.class).findShortNameAndLineCode();
		request.setAttribute("smallLines", smallLines);
		renderJson(smallLines);
	}

	
}

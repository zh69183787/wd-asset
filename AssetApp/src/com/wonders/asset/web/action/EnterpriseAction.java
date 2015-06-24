package com.wonders.asset.web.action;

import java.util.Map;

import net.sf.json.JsonConfig;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;

import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.base.web.AbstractBaseAction;
import com.wonders.asset.model.Enterprise;
import com.wonders.asset.service.EnterpriseService;
import com.wonders.framework.util.ServiceProvider;

/**
 * 厂商信息action类
 * @author Kai Yao
 * @date 2013-11-4
 */
public class EnterpriseAction extends AbstractBaseAction{

	/**
	 * 日志对象
	 */
	public static final Logger logger = Logger.getLogger("EnterpriseAction");
	
	private EnterpriseService enterpriseService;
	
	public EnterpriseService getEnterpriseService() {
		return enterpriseService;
	}

	public void setEnterpriseService(EnterpriseService enterpriseService) {
		this.enterpriseService = enterpriseService;
	}
	
	/**
	 * 查询方法
	 * @return
	 * @throws Exception
	 */
	public void inquery() throws Exception {
		Map<String,String> filter = createFilterMap();
		
		int startIndex = NumberUtils.toInt(getRequestParameter("jtStartIndex"), 0);
		int pageSize = NumberUtils.toInt(getRequestParameter("jtPageSize"), 10);
		Map<String,String> sort = createSortMap();
		Pagination<Enterprise> enterprises = ServiceProvider.getService(EnterpriseService.class).findBy(filter, sort, startIndex, pageSize);
		
		JsonConfig jsonConfig = basicJsonCfg.copy();
		renderJson(enterprises.getResult(), enterprises.getTotalCount(), jsonConfig);
	}
}

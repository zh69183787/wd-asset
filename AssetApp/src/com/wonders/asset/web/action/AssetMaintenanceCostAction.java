package com.wonders.asset.web.action;

import java.util.Map;

import net.sf.json.JsonConfig;

import org.apache.commons.lang.math.NumberUtils;

import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.base.web.AbstractBaseAction;
import com.wonders.asset.model.AssetMaintenanceCost;
import com.wonders.asset.service.AssetMaintenanceCostService;
import com.wonders.framework.util.ServiceProvider;

public class AssetMaintenanceCostAction extends AbstractBaseAction{

	private AssetMaintenanceCostService assetMaintenanceCostService;

	public AssetMaintenanceCostService getAssetMaintenanceCostService() {
		return assetMaintenanceCostService;
	}

	public void setAssetMaintenanceCostService(
			AssetMaintenanceCostService assetMaintenanceCostService) {
		this.assetMaintenanceCostService = assetMaintenanceCostService;
	}

	/**
	 * 查询方法
	 */
	public void inquery() throws Exception {
		Map<String,String> filter = createFilterMap();
		
		int startIndex = NumberUtils.toInt(getRequestParameter("jtStartIndex"), 0);
		int pageSize = NumberUtils.toInt(getRequestParameter("jtPageSize"), 10);
		Map<String,String> sort = createSortMap();
		Pagination<AssetMaintenanceCost> assetMaintenanceCost = ServiceProvider.getService(AssetMaintenanceCostService.class).findBy(filter, sort, startIndex, pageSize);
		
		JsonConfig jsonConfig = basicJsonCfg.copy();
		jsonConfig.registerPropertyExclusions(AssetMaintenanceCost.class, new String[]{"asset"});
		renderJson(assetMaintenanceCost.getResult(), assetMaintenanceCost.getTotalCount(), jsonConfig);
	}
	
}

package com.wonders.asset.web.action;

import java.util.Map;

import net.sf.json.JsonConfig;

import org.apache.commons.lang.math.NumberUtils;

import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.base.web.AbstractBaseAction;
import com.wonders.asset.model.AssetOverhaul;
import com.wonders.asset.service.AssetOverhaulService;
import com.wonders.framework.util.ServiceProvider;

public class AssetOverhaulAction extends AbstractBaseAction{

	private AssetOverhaulService assetOverhaulService;

	public AssetOverhaulService getAssetOverhaulService() {
		return assetOverhaulService;
	}
	public void setAssetOverhaulService(AssetOverhaulService assetOverhaulService) {
		this.assetOverhaulService = assetOverhaulService;
	}

	/**
	 * 查询方法
	 */
	public void inquery() throws Exception {
		Map<String,String> filter = createFilterMap();
		
		int startIndex = NumberUtils.toInt(getRequestParameter("jtStartIndex"), 0);
		int pageSize = NumberUtils.toInt(getRequestParameter("jtPageSize"), 10);
		Map<String,String> sort = createSortMap();
		Pagination<AssetOverhaul> assetOverhaul = ServiceProvider.getService(AssetOverhaulService.class).findBy(filter, sort, startIndex, pageSize);
		
		JsonConfig jsonConfig = basicJsonCfg.copy();
		jsonConfig.registerPropertyExclusions(AssetOverhaul.class, new String[]{"asset"});
		renderJson(assetOverhaul.getResult(), assetOverhaul.getTotalCount(), jsonConfig);
	}
}

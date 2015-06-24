package com.wonders.asset.web.action;

import java.util.Map;

import net.sf.json.JsonConfig;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;

import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.base.web.AbstractBaseAction;
import com.wonders.asset.model.AssetPrice;
import com.wonders.asset.model.Equipment;
import com.wonders.asset.service.AssetPriceService;
import com.wonders.framework.util.ServiceProvider;

public class AssetPriceAction extends AbstractBaseAction{

	/**
	 * 日志对象
	 */
	public static final Logger logger = Logger.getLogger("AssetPriceAction");
	
	private AssetPriceService assetPriceService;
	
	
	public AssetPriceService getAssetPriceService() {
		return assetPriceService;
	}

	public void setAssetPriceService(AssetPriceService assetPriceService) {
		this.assetPriceService = assetPriceService;
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
		Pagination<AssetPrice> assetPrices = ServiceProvider.getService(AssetPriceService.class).findBy(filter, sort, startIndex, pageSize);
		
		
		JsonConfig jsonConfig = basicJsonCfg.copy();
		jsonConfig.registerPropertyExclusions(Equipment.class, new String[]{"asset"});
		renderJson(assetPrices.getResult(), assetPrices.getTotalCount(), jsonConfig);
	}
}

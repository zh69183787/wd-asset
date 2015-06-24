package com.wonders.asset.web.action;

import java.util.Map;

import net.sf.json.JsonConfig;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;

import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.base.web.AbstractBaseAction;
import com.wonders.asset.model.Equipment;
import com.wonders.asset.service.EquipmentService;
import com.wonders.framework.util.ServiceProvider;

public class EquipmentAction extends AbstractBaseAction{

	/**
	 * 日志对象
	 */
	public static final Logger logger = Logger.getLogger("EquipmentAction");
	
	private EquipmentService equipmentService;
	
	
	public EquipmentService getEquipmentService() {
		return equipmentService;
	}

	public void setEquipmentService(EquipmentService equipmentService) {
		this.equipmentService = equipmentService;
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
		Pagination<Equipment> equipments = ServiceProvider.getService(EquipmentService.class).findBy(filter, sort, startIndex, pageSize);
		
		JsonConfig jsonConfig = basicJsonCfg.copy();
		jsonConfig.registerPropertyExclusions(Equipment.class, new String[]{"asset"});
		renderJson(equipments.getResult(), equipments.getTotalCount(), jsonConfig);
	}
}

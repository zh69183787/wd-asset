package com.wonders.asset.service.impl;

import com.wonders.asset.base.service.impl.BaseServiceImpl;
import com.wonders.asset.dao.EquipmentDao;
import com.wonders.asset.model.Equipment;
import com.wonders.asset.service.EquipmentService;

public class EquipmentServiceImpl extends BaseServiceImpl<Equipment,String> implements EquipmentService {

	private EquipmentDao equipmentDao;
	
	public EquipmentDao getEquipmentDao() {
		return equipmentDao;
	}

	public void setEquipmentDao(EquipmentDao equipmentDao) {
		this.equipmentDao = equipmentDao;
		setBaseDao(equipmentDao);
	}

	
}
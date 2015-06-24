package com.wonders.asset.dao.impl;

import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.dao.EquipmentDao;
import com.wonders.asset.model.Equipment;

/**
 * EquipmentDao的Hibernate实现
 * 
 */
public class EquipmentDaoImpl extends BaseDaoImpl<Equipment, String>
		implements EquipmentDao {

	public EquipmentDaoImpl() {
		super(Equipment.class);
	}

	
	
	

}
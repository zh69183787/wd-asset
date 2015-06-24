package com.wonders.asset.dao.impl;

import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.dao.TaskInventoryDao;
import com.wonders.asset.model.TaskInventory;

/**
 * TaskInventoryDao的Hibernate实现
 * 
 */
public class TaskInventoryDaoImpl extends BaseDaoImpl<TaskInventory, String>
		implements TaskInventoryDao {

	public TaskInventoryDaoImpl() {
		super(TaskInventory.class);
	}

}
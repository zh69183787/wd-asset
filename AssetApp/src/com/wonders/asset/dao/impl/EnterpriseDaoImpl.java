package com.wonders.asset.dao.impl;

import java.util.List;

import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.dao.EnterpriseDao;
import com.wonders.asset.model.Enterprise;

/**
 * EnterpriseDao的Hibernate实现
 * 
 * @author Kai Yao
 * 
 */
public class EnterpriseDaoImpl extends BaseDaoImpl<Enterprise, String>
		implements EnterpriseDao {

	public EnterpriseDaoImpl() {
		super(Enterprise.class);
	}

	@Override
	public List<Enterprise> findByNameAndType(String name, String type) {
		String hql="from Enterprise t where t.name='"+name+"' and t.type='"+type+"'";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public List<Enterprise> findByNameAndTypeWithPublish(String name,
			String type) {
		String hql="from Enterprise t where t.name='"+name+"' and t.type='"+type+"' and t.publish='1'";
		return getHibernateTemplate().find(hql);
	}
	
	

}
package com.wonders.asset.dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.dao.SpareTypeDao;
import com.wonders.asset.model.SpareType;

public class SpareTypeDaoImpl extends BaseDaoImpl<SpareType, String> implements SpareTypeDao{
	private org.hibernate.SessionFactory sessionFactory2;
	public SessionFactory getSessionFactory2() {
        return sessionFactory2;
    }

    public void setSessionFactory2(SessionFactory sessionFactory2) {
        this.sessionFactory2 = sessionFactory2;
    }

    public SpareTypeDaoImpl() {
		super(SpareType.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public SpareType findRootByVersion(String version) {
		String hql="from SpareType t where t.version='"+version+"' and t.parent.id is null";
		List<SpareType> list = getHibernateTemplate().find(hql);
		if(list!=null && list.size()==1) return list.get(0);
		return null;
	}

	/**
	 * 查询所有版本
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> findAllVersion() {
		String sql = "select t.version from T_SPARE_TYPE t group by t.version order by to_number(t.version) DESC";
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);
		query.addScalar("version", Hibernate.STRING);
		return query.list();
	}

	@Override
	public void sparePublish(String version) {
		String hql="update SpareType t set t.publish='0' where t.publish='1'";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.executeUpdate();
		
		String hql2 = "update SpareType t set t.publish='1'where t.version=?";
		Query query2 = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql2);
		query2.setString(0, version);
		query2.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SpareType> findByParentId(String parentId, String version) {
		String hql = "from SpareType t where t.parent.id='" + parentId
				+ "' and t.id!='0' and t.version='" + version
				+ "' order by to_number(t.code) ASC";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public void deleteSpareTypeById(String id) {
		getHibernateTemplate().delete(getHibernateTemplate().get(SpareType.class, id));
	}

	@Override
	public String findMaxCodeIdByVerison(String version) {
		String sql="select max(to_number(t.code_Id)) codeId from T_SPARE_TYPE t where t.version=?";
		SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		query.setString(0, version);
		query.addScalar("codeId", Hibernate.STRING);
		String result = (String) query.uniqueResult();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SpareType> findByParentIdAndCodeAndVersion(String id,
			String parentId, String code, String version) {
		String hql="from SpareType t where t.parent.id=? and t.code=? and t.version=?";
		if(id!=null && !"".equals(id)){
			hql += " and t.id!=?";
		}
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, parentId).setString(1, code).setString(2, version);
		if(id!=null && !"".equals(id)){
			query.setString(3, id);
		}
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public SpareType findById(String id) {
		String hql = "from SpareType t where t.id='" + id + "'";
		List<SpareType> list = getHibernateTemplate().find(hql);
		return (list == null || list.size() <= 0) ? null : list.get(0);
	}

	@Override
	public void save(SpareType spareType) {
		getHibernateTemplate().save(spareType);
	}

	@Override
	public void updateChildrenAllCode(String parentId, String parentAllCode) {
		try {
			String sql="update T_SPARE_TYPE t set t.all_Code=(to_char('"+parentAllCode+"') || to_char(t.code)) where t.parent_id=?";
			SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
			query.setString(0, parentId);
			query.executeUpdate();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SpareType> findSpareTypeByVersion(String version) {
		String hql = "from SpareType t where t.version ='" + version + "'";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public String findLastestVersion() {
		String sql = "select max(to_number(t.version)) maxVersion from T_SPARE_TYPE t ";
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);
		query.addScalar("maxVersion", Hibernate.STRING);
		String result = (String) query.uniqueResult();
		return result;
	}

	@Override
	public void saveAll(List<SpareType> list) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			for (int i = 0; i < list.size(); i++) {
				session.save(list.get(i));
			}
		
	}
	
	/**
	 * 查询备品备件分类（大类）
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SpareType> find(SpareType example) {	
//		Criteria c = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(SpareType.class);	
//		c.add(Example.create(example).enableLike(MatchMode.ANYWHERE).excludeNone().excludeZeroes()).addOrder(Order.asc("code"));
//		c.add(Restrictions.eq("parent.id",example.getParent().getId()));
		String hql="from SpareType t where t.parent.id = :parentId and t.publish = :publish order by to_number(t.code) ";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setString("publish","1").setString("parentId", example.getParent().getId());
		return query.list();  
	}

}

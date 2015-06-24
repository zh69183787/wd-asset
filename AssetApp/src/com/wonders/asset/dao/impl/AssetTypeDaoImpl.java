package com.wonders.asset.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.wonders.asset.base.util.Pagination;
import org.apache.commons.lang.StringUtils;
import org.hibernate.*;

import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.dao.AssetTypeDao;
import com.wonders.asset.model.AssetType;

public class AssetTypeDaoImpl extends BaseDaoImpl<AssetType, String> implements
		AssetTypeDao {
    private org.hibernate.SessionFactory sessionFactory2;

    public SessionFactory getSessionFactory2() {
        return sessionFactory2;
    }

    public void setSessionFactory2(SessionFactory sessionFactory2) {
        this.sessionFactory2 = sessionFactory2;
    }

    public AssetTypeDaoImpl() {
		super(AssetType.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AssetType> findByParentId(String parentId, String version) {
		String hql = "from AssetType t where t.parent.id='" + parentId
				+ "' and t.id!='0' and t.version='" + version
				+ "' order by to_number(t.code) ASC";
		return getHibernateTemplate().find(hql);
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<AssetType> findByParentIdWithPublish(String parentId) {
		String sql ="";
		if(StringUtils.isNotEmpty(parentId)){
			sql = "select * from t_Asset_Type t where t.parent_id=? and t.publish='1' order by to_number(t.code) ASC";
			SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
			query.setString(0, parentId);
			query.addEntity(AssetType.class);
			return query.list();
		}else{
			sql = "select * from t_Asset_Type t where t.parent_id = " +
					" (select t2.id from t_Asset_Type t2 where t2.parent_id is null and t2.publish='1') " +
					" order by to_number(t.code) ASC";
			SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
			query.addEntity(AssetType.class);
			return query.list();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AssetType> findByNameAndParentId(String name, String parentId) {
		String hql = "from AssetType t where t.name='" + name
				+ "' and t.parent.id='" + parentId
				+ "' order by to_number(t.code) ASC";
		return getHibernateTemplate().find(hql);
	}

	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AssetType> findByNameAndLineCodeId(String name,String lineCodeId) {
		String hql = "from AssetType t where t.name='"+name+"' and t.parent.codeId='"+lineCodeId+"' and t.parent.publish='1'" 
		+ " order by to_number(t.code) ASC";
		return getHibernateTemplate().find(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AssetType> findByNameWithPublish(String name) {
		String hql="from AssetType t where t.publish='1' and t.name=?";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, name);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findByVersion(String version) {
		/*
		 * String hql = "from AssetType t where t.version='" + version +
		 * "' connect by prior t.id=t.parentId start with t.parentId='0'";
		 * return getHibernateTemplate().find(hql);
		 */
		String sql = "select t.id,t.parent_id,t.code,t.name,t.all_code "
				+ " from T_ASSET_TYPE t where t.version = '"+version+"' "
				+ " connect by prior t.id = t.parent_id start with t.parent_id = " 
				+ "(select t2.id from T_ASSET_TYPE t2 where t2.version='"+version+"' and t2.parent_id is null ) "
				+ " ORDER SIBLINGS BY to_number(t.all_code) ASC";
		Session session = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);
		query.addScalar("id", Hibernate.STRING).addScalar("parent_id",
				Hibernate.STRING).addScalar("code", Hibernate.STRING)
				.addScalar("name", Hibernate.STRING).addScalar("all_code",
						Hibernate.STRING);
		List<Object[]> list = query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public AssetType findById(String id) {
		String hql = "from AssetType t where t.id='" + id + "'";
		List<AssetType> list = getHibernateTemplate().find(hql);
		return (list == null || list.size() <= 0) ? null : list.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AssetType> findByVersionAndParentId(String version,
			String parentId) {
		String hql = "from AssetType t where t.version='" + version
				+ "' and t.parentId='" + parentId + "'";
		return getHibernateTemplate().find(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object[] findByRoot(String version) {
		String sql = "select t.id,t.parent_id,t.code,t.name,t.all_code"
				+ " from T_ASSET_TYPE t where t.version = '" + version
				+ "' and t.parent_id is null";

		Session session = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);
		query.addScalar("id", Hibernate.STRING).addScalar("parent_id",
				Hibernate.STRING).addScalar("code", Hibernate.STRING)
				.addScalar("name", Hibernate.STRING).addScalar("all_code",
						Hibernate.STRING);
		List<Object[]> list = query.list();

		return list == null ? null : list.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AssetType> findAssetTypeByVersion(String version) {
		String hql = "from AssetType t where t.version ='" + version + "'";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public void saveAll(List<AssetType> list) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		try {
			Transaction tx = session.beginTransaction();
			for (int i = 0; i < list.size(); i++) {
				session.save(list.get(i));
			}
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findFirstLevel(String version) {
		String sql = "select t.id from T_ASSET_TYPE t where t.version='"
				+ version + "' and t.parent_id is null";
		Session session = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);
		query.addScalar("id", Hibernate.STRING);
		return query.list();
	}

	@Override
	public String findLastestVersion() {
		String sql = "select max(to_number(t.version)) maxVersion from T_ASSET_TYPE t ";
		Session session = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);
		query.addScalar("maxVersion", Hibernate.STRING);
		String result = (String) query.uniqueResult();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findAllVersion() {
		String sql = "select t.version from T_ASSET_TYPE t group by t.version order by to_number(t.version) DESC";
		Session session = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);
		query.addScalar("version", Hibernate.STRING);
		return query.list();
	}

	@Override
	public void save(AssetType assetType) {
		getHibernateTemplate().save(assetType);
	}

	@Override
	public void deleteAssetTypeById(String id) {
		getHibernateTemplate().delete(getHibernateTemplate().get(AssetType.class, id));
	}

	@Override
	public void getAllAssetTypeByVersion(String version) {
		String hql ="from AssetType t where t.";
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public AssetType findRootByVersion(String version) {
		String hql="from AssetType t where t.version='"+version+"' and t.parent.id is null";
		List<AssetType> list = getHibernateTemplate().find(hql);
		if(list!=null && list.size()==1) return list.get(0);
		return null;
	}

	@Override
	public void publish(String version) {
		String hql="update AssetType t set t.publish='0' where t.publish='1'";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.executeUpdate();
		
		String hql2 = "update AssetType t set t.publish='1'where t.version=?";
		Query query2 = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql2);
		query2.setString(0, version);
		query2.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AssetType> findByParentIdAndCodeAndVersion(String id,String parentId,
			String code, String version) {
		String hql="from AssetType t where t.parent.id=? and t.code=? and t.version=?";
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

	@Override
	public void updateChildrenAllCode(String parentId, String parentAllCode) {
		try {
			String sql="update T_ASSET_TYPE t set t.all_Code=(to_char("+parentAllCode+") || to_char(t.code)) where t.parent_id=?";
			SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
			query.setString(0, parentId);
			query.executeUpdate();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public int findCountOfCodeId(String codeId) {
		String sql="select count(*) count from T_ASSET_TYPE t where t.code_id=?";
		SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		query.setString(0, codeId);
		query.addScalar("count",Hibernate.INTEGER);
		Integer result = (Integer) query.uniqueResult(); 
		return result; 
	}

	@Override
	public String findMaxCodeIdByVerison(String version) {
		String sql="select max(to_number(t.code_Id)) codeId from T_ASSET_TYPE t where t.version=?";
		SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		query.setString(0, version);
		query.addScalar("codeId", Hibernate.STRING);
		String result = (String) query.uniqueResult();
		return result;
	}

	@Override
	public AssetType findMainTypeByCodeWithPublish(String code) {
		 String hql="from AssetType t where t.code=? and t.allCode=? and t.publish='1'";
		 Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		 query.setString(0, code).setString(1, code);
		 List<AssetType> list = query.list();
		 if(list!=null && list.size()==1) return list.get(0);
		 return null;
	}

	@Override
	public AssetType findByParentIdAndCode(String parentId, String code) {
		String hql="from AssetType t where t.parent.id=? and t.code=? and t.publish='1'";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, parentId).setString(1, code);
		List<AssetType> list = query.list();
		if(list!=null && list.size()==1) return list.get(0);
		return null;
	}


    public AssetType findByAllCode(String allcode) {
        String hql="from AssetType t where  t.allCode=? and t.publish='1'";
        Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
        query.setString(0, allcode);
        List<AssetType> list = query.list();
        if(list!=null && list.size()==1) return list.get(0);
        return null;
    }


}

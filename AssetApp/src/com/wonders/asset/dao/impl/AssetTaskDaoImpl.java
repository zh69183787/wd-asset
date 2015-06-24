package com.wonders.asset.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.dao.AssetTaskDao;
import com.wonders.asset.model.AssetTask;
import com.wonders.asset.model.Attach;

public class AssetTaskDaoImpl extends BaseDaoImpl<AssetTask, String> implements AssetTaskDao {	
	
	public AssetTaskDaoImpl() {
		super(AssetTask.class);
	}

	@Override
	public int findSumOfTaskCheckByTaskId(String taskId) {
		String sql="select distinct t.asset_Info_Id from t_Asset_Task_Check t where t.task_Id=? and t.removed='0'";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setString(0, taskId);
		return (Integer) query.uniqueResult();
	}

	@Override
	public void saveAttach(Attach attach) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(attach);
		tx.commit();
		session.close();
	}

	@Override
	public List<Attach> findAttachByGroupId(String groupId) {
		String hql="from Attach t where t.groupid = ?";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, groupId);
		return query.list();
	}

	@Override
	public Attach findAttachById(Long id) {
		return (Attach) getHibernateTemplate().get(Attach.class, id);
	}

	@Override
	public void deleteAttachById(Long id) {
		String hql="delete Attach t where t.id=?";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setLong(0, id);
		query.executeUpdate();
	}

    @Override
    public float getAssetCompleteRate() {
        String sql="select sum(to_number(completerate))/count(1) from t_asset_task";
        
        //String sql = "select sum(completerate)/count(*) from t_asset_task";
        SQLQuery query= getSession().createSQLQuery(sql);
        Object obj  = query.uniqueResult();
        if(obj != null) {
            float totalRate = Float.valueOf(obj.toString())*100;
            return totalRate;
        }else
            return 0;
    }

    protected String getTmpPk(){
        return "EAMTASKID";
    }

    protected String getTableName(boolean isTem){
        return !isTem ? "T_ASSET_TASK D" : "T_ASSET_TASK_TMP T";
    }

    protected String getPk(){
        return "ID";
    }

    protected StringBuffer getFields(){

        StringBuffer fields = new StringBuffer("EAMTASKID,TASKNAME,CHECKPERSONLIST,");
        fields.append("TASKUSER,STARTTIME,ENDTIME,TASKMEMO_FILTER,REALITYTIME,TASKSTATUS,COMPLETERATE,");
        fields.append("ERRORNUM,OPERATE_DATE");
        return  fields;
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findCheckInfoAndAssetByTaskId(String id,int startIndex,int pageSize) {
		StringBuilder sql = new StringBuilder();
		sql.append("select a.asset_code, l.name lineName, a.name assetName, s.name stationName, o.gegin_use_date, ");
		sql.append("		c.check_info, c.check_date, c.check_person ");
		sql.append("from t_asset_owner o inner join t_line l on o.line_code_id=l.id ");
		sql.append("	inner join t_station s on o.station_code_id=s.id ");
		sql.append("	inner join t_asset a on a.asset_owner_info_id=o.id "); 
		sql.append("	inner join t_asset_checkinfo c on c.asset_no=a.asset_code ");
		sql.append("	inner join t_asset_task t on t.id=c.asset_task_id ");
		sql.append("where t.id = ?");
		Query query = getSession().createSQLQuery(sql.toString()).setFirstResult(startIndex).setMaxResults(pageSize);
		query.setString(0, id);
		return query.list();
	}

	@Override
	public List<Object[]> getAssetTaskByYear() {
		// TODO Auto-generated method stub
		/*
		select id, taskname,  completerate, sum(count) as sCount, errornum 
  from (select t.id, 
               t.taskname, 
               t.starttime, 
               t.completerate, 
               a.count, 
               t.errornum 
          from ( (select * from t_asset_task t where  t.removed = 0) t left join (select * from t_asset_checkinfo c ) c on 
                t.id = c.asset_task_id) 
          left join t_asset a 
            on c.asset_no = a.asset_code 
         where    substr(t.starttime,0,4) = TO_CHAR(SYSDATE,'YYYY')  )  --可以是参数也可以不是sysdate
 group by id, taskname,  completerate, errornum 
 order by taskname
		  */
		StringBuilder sql = new StringBuilder();
		sql.append(" select id, taskname, completerate*100, count(1) as sCount, errornum, Round((count(1)-errornum)/count(1)*100,2)");
		sql.append("   from (select t.id,  ");
		sql.append("                t.taskname,");
		sql.append("                t.starttime,");
		sql.append("                t.completerate, ");
		sql.append("                t.errornum  ");
		sql.append("          from ( (select * from t_asset_task t where  t.removed = 0) t left join (select * from t_asset_checkinfo c ) c on ");
		sql.append("                 t.id = c.asset_task_id) ");
		sql.append("           left join t_asset a ");
		sql.append("             on c.asset_no = a.asset_code  ");
		sql.append("          where substr(t.starttime,0,4) = TO_CHAR(SYSDATE,'YYYY') ) ");
		sql.append("  group by id, taskname,  completerate, errornum  ");
		sql.append("  order by taskname ");
        System.out.println(sql.toString());
        Query query = getSession().createSQLQuery(sql.toString());
	
		return query.list();
//		return null;
	}

    public static void main(String[] args) {
     AssetTaskDaoImpl dao = new AssetTaskDaoImpl();
        dao.getAssetTaskByYear();
    }
}

package com.wonders.asset.dao.impl;

import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.dao.WorkPlanDao;
import com.wonders.asset.model.WorkPlan;

import java.util.List;

/**
 * Created by HH on 2014/11/19.
 */
public class WorkPlanDaoImpl extends BaseDaoImpl<WorkPlan,String> implements WorkPlanDao {
    public WorkPlanDaoImpl() {
        super(WorkPlan.class);
    }

    @Override
    public List<WorkPlan> findWorkPlanByPublishAndDeptId(String deptId) {
        String hql="from WorkPlan w where w.publish='1' and w.deptId="+deptId;
        return getHibernateTemplate().find(hql);
    }
}

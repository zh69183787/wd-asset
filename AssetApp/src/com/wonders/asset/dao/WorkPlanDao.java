package com.wonders.asset.dao;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.model.WorkPlan;

import java.util.List;

/**
 * Created by HH on 2014/11/19.
 */
public interface WorkPlanDao extends BaseDao<WorkPlan,String> {
    public List<WorkPlan> findWorkPlanByPublishAndDeptId(String deptId);
}

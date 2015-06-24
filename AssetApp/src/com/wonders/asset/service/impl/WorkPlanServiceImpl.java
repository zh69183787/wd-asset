package com.wonders.asset.service.impl;

import com.wonders.asset.base.service.impl.BaseServiceImpl;

import com.wonders.asset.dao.WorkPlanDao;
import com.wonders.asset.model.WorkPlan;
import com.wonders.asset.service.WorkPlanService;


import java.util.List;

/**
 * Created by HH on 2014/11/19.
 */
public class WorkPlanServiceImpl extends BaseServiceImpl<WorkPlan,String> implements WorkPlanService {
    private WorkPlanDao workPlanDao;

    public WorkPlanDao getWorkPlanDao() {
        return workPlanDao;
    }

    public void setWorkPlanDao(WorkPlanDao workPlanDao) {
        this.workPlanDao = workPlanDao;
    }

    @Override
    public List<WorkPlan> findByPublishAndDeptId(String deptId) {
        return workPlanDao.findWorkPlanByPublishAndDeptId(deptId);
    }
}

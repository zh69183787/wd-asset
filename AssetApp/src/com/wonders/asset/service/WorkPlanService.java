package com.wonders.asset.service;

import com.wonders.asset.base.service.BaseService;
import com.wonders.asset.model.WorkPlan;

import java.util.List;

/**
 * Created by HH on 2014/11/19.
 */
public interface WorkPlanService extends BaseService<WorkPlan,String> {
    /**
     * 查询工作计划信息z
     */
    public List<WorkPlan> findByPublishAndDeptId(String deptId);
}

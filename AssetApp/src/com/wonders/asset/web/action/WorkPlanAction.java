package com.wonders.asset.web.action;

import com.wonders.asset.base.web.AbstractBaseAction;
import com.wonders.asset.model.WorkPlan;
import com.wonders.asset.service.WorkPlanService;

import java.io.IOException;
import java.util.List;

/**
 * Created by HH on 2014/11/19.
 */
public class WorkPlanAction extends AbstractBaseAction {
    private WorkPlanService workPlanService;

    public WorkPlanService getWorkPlanService() {
        return workPlanService;
    }

    public void setWorkPlanService(WorkPlanService workPlanService) {
        this.workPlanService = workPlanService;
    }

    /**
     * 查询工作计划
     */
    public String inquery() throws IOException {
        String deptId=request.getParameter("deptId");
        List<WorkPlan> workPlans=workPlanService.findByPublishAndDeptId(deptId);
       request.setAttribute("workPlans",workPlans);
        return "success";
    }

}

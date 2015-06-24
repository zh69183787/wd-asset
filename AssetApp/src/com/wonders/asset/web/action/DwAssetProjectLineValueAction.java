package com.wonders.asset.web.action;

import com.wonders.asset.base.web.AbstractBaseAction;
import com.wonders.asset.dao.DwAssetProjectLineValueDao;

import java.io.IOException;

/**
 * Created by HH on 2014/11/20.
 */
public class DwAssetProjectLineValueAction extends AbstractBaseAction {
    private DwAssetProjectLineValueDao projectLineValueDao;

    public DwAssetProjectLineValueDao getProjectLineValueDao() {
        return projectLineValueDao;
    }

    public void setProjectLineValueDao(DwAssetProjectLineValueDao projectLineValueDao) {
        this.projectLineValueDao = projectLineValueDao;
    }

    /**
     *  查询价值任务进展
     */
    public void getDwAssetProjectLineValue() throws IOException {
        float dwAssetProjectLineValueRate=projectLineValueDao.getDwAssetProjectLineValueRate();
        String da=dwAssetProjectLineValueRate+"";
        renderJson(da);
    }
}

package com.wonders.asset.service.impl;

import com.wonders.asset.base.service.impl.BaseServiceImpl;
import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.dao.UnitMasterDao;
import com.wonders.asset.model.UnitMaster;
import com.wonders.asset.service.UnitMasterService;

import java.util.List;

/**
 * Created by HH on 2014/11/4.
 */
public class UnitMaserServiceImpl extends BaseServiceImpl<UnitMaster,String> implements UnitMasterService {
    private UnitMasterDao unitMasterDao;

    public UnitMasterDao getUnitMasterDao() {
        return unitMasterDao;
    }

    public void setUnitMasterDao(UnitMasterDao unitMasterDao) {
        this.unitMasterDao = unitMasterDao;
    }

    @Override
    public List<UnitMaster> findAllUnitMaster() {
        return unitMasterDao.findAllTheUnitMaster();
    }
}

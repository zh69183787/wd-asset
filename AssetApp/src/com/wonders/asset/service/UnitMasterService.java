package com.wonders.asset.service;

import com.wonders.asset.base.service.BaseService;
import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.model.UnitMaster;

import java.util.List;

/**
 * Created by HH on 2014/11/4.
 */
public interface UnitMasterService extends BaseService<UnitMaster,String> {
    public List<UnitMaster> findAllUnitMaster();
}

package com.wonders.asset.dao;

import java.util.List;
import java.util.Map;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.model.HouseAsset;

public interface HouseAssetDao extends BaseDao<HouseAsset, String>{

	void clear();
    void batchUpdate();
    void batchInsert();
    
    public Pagination<HouseAsset> findHouseAssetAreaInfo(Map<String, String> houseAssetFilter, 
    		Map<String,String> areaInfoFilter, Map<String, String> sortMap, int pageNo, int pageSize);
    
    public List<Object[]> getLineBuildAreaReport();
    
    public List<Object[]> getLineStationAreaReport();
    
    public List<Object[]> getUseTypeReport();
    
    public List<Object[]> getUseTypeReportByBigLine();
    
    /**
     * 生成使用单位时需要返回报表行数
     * 使用单位和用途类型乘积所得
     * @return
     */
    Integer getUseTypeTakeOverDepRows();
	List<Object[]> getBuildAreaByLine();
}

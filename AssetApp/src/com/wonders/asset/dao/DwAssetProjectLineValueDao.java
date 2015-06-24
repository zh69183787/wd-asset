package com.wonders.asset.dao;

import java.util.List;
import java.util.Map;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.model.dw.DwAssetProjectLineValue;

/**
 * 项目线路价值
 * @author ycl
 *
 */
@SuppressWarnings("unchecked")
public interface DwAssetProjectLineValueDao extends BaseDao{

	
	public List<DwAssetProjectLineValue> findAssetProjectLineValue() ;

	
	/**
	 * 查询线路项目表
	 */
	public List<Object[]> findLineAndCountProject();
	
	/**
	 * 显示项目线路资产统计
	 */
	public List<DwAssetProjectLineValue> findAssetProjectLineValueByDate(String start,String end);
	
	public List<String> findAllYearOfDwAssetProjectLineValue();

    public List<DwAssetProjectLineValue> findAssetProjectLineValueByDate(String start, String end,boolean hasCount);

    public Map count(String start,String end);


    /**
     * 查询价值任务进展
     */
    public float getDwAssetProjectLineValueRate();

    /**
     * 按公司统计大修更新项目
     * @param start
     * @param end
     * @return
     */
    public List<Map> countByDepartment(String start,String end);


    public List<Map> countByLine(String start,String end);

    public List<Map> countByAssetType(String start,String end);
}

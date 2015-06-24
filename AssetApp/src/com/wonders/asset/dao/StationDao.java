package com.wonders.asset.dao;

import java.util.List;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.model.Line;
import com.wonders.asset.model.Station;

/**
 * 车站dao接口
 * @author ycl 2013-11-25
 *
 */
public interface StationDao extends BaseDao<Station, String>{

	
	/**
	 * 查询
	 * @param code 
	 * @return
	 */
	public Station findByCodeAndLineCodeIdWithPublish(String code,String lineCodeId);
	
	/**
	 * 根据code查询
	 * @param code
	 * @return
	 */
	public Station findByCodeAndLineId(String code,String lineId);
	
	/**
	 * 查询
	 * @param lineId 线路id
	 * @return	车站
	 */
	public List<Station> findByLineId(String lineId);
	
	/**
	 * 查询
	 * @param id 主键
	 * @param name 名称 
	 * @param shortName 简称
	 * @param code 代码
	 * @return 线路
	 */
	public List<Line> findByCondition(String id,String name,String code,String version,String lineId);
	
	/**
	 * 查询
	 * @param id 主键
	 * @return 车站
	 */
	public Station findById(String id);
	
	/**
	 * 保存
	 * @param station 车站
	 */
	public void saveStation(Station station);

	/**
	 * 删除
	 * @param id 主键
	 */
	public void deleteById(String id);
	
	/**
	 * 批量保存
	 * @param list 车站
	 */
	public void saveAll(List<Station> list);
	
	/**
	 * 发布
	 * @param version 版本
	 */
	public void publish(String version);
	
	/**
	 * 查询
	 * @param id
	 * @param version
	 * @param codeId
	 * @return
	 */
	public List<Station> findByVersionAndCodeId(String id,String version,String codeId);
	
	public String findMaxCodeId(String version);


}

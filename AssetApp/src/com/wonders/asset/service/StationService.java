package com.wonders.asset.service;

import java.util.List;

import com.wonders.asset.base.service.BaseService;
import com.wonders.asset.model.Station;

public interface StationService extends BaseService<Station, String>{

	/**
	 * 查询
	 * @param lineId 线路id
	 * @return 车站
	 */
	public List<Station> findByLineId(String lineId);
	
	/**
	 * 是否唯一
	 * @param id 主键
	 * @param name 名称
	 * @param shortName 简称
	 * @param code	代码
	 * @return true,false
	 */
	public boolean isUniquene(String id,String name,String code,String version,String lineId);
	
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
	 * 检查是否唯一codeId
	 * @param id
	 * @param version
	 * @param codeId
	 * @return
	 */
	public boolean isUniqueneByVersionAndCodeId(String id,String version,String codeId);
	
	/**
	 * 查询当前版本下的下一个codeId
	 * @param version
	 * @return
	 */
	public String findNextCodeId(String version);


    public Station findByCodeAndLineId(String stationCode,String lineId);
    
}

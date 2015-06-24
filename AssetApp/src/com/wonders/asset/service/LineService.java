package com.wonders.asset.service;

import java.util.List;
import java.util.Set;

import com.wonders.asset.base.service.BaseService;
import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.model.Line;
import com.wonders.asset.model.Station;

public interface LineService extends BaseService<Line, String>{


    public Pagination findAllLine(int page,int pageSize);

	/**
	 * 查询
	 */
	public List<Line> findAll();
	
	/**
	 * 根据发布查询
	 * @param publish
	 * @return
	 */
	public List<Line> findAllByPublish(String publish);
	
	/**
	 * 查询新路
	 * @return 符合前台控件要求的json数据格式
	 */
	public String findLineForTree();
	
	/**
	 * 查询所有版本号
	 * @return
	 */
	public List<String> showVersion();
	
	/**
	 * 排序
	 * @param stations 车站
	 * @return 车站
	 */
	public List<Station> sortStations(Set<Station> stations);
	
	/**
	 * 保存线路
	 * @param line 线路
	 */
	public void saveLine(Line line);
	
	/**
	 * 查询
	 * @param id 主键
	 * @return 线路
	 */
	public Line findById(String id);
	
	/**
	 * 是否唯一
	 * @param id 主键
	 * @param name 名称
	 * @param shortName 简称
	 * @param code	代码
	 * @return true,false
	 */
	public boolean isUniquene(String id,String name,String code,String version);
	
	/**
	 * 删除
	 * @param id 主键
	 */
	public void deleteLine(String id);
	
	/**
	 * 查询
	 * @param version 版本
	 * @return 线路
	 */
	public List<Line> findByVersion(String version);
	
	/**
	 * 发布
	 * @param version 版本
	 */
	public void publish(String version);
	/**
	 * 查询最新的版本
	 * @return
	 */
	public int findLasetVersion();
	
	/**
	 * 查询当前版本下codeId是否有重复
	 * @param id
	 * @param version
	 * @param codeId
	 * @return true:结果有值，false:无值
	 */
	public boolean isUnqueneByVersionAndCodeId(String id,String version,String codeId);
	
	/**
	 * 查询当前版本下的下一个codeId
	 * @param version
	 * @return
	 */
	public String findNextCodeId(String version);

    public Line findByCode(String lineCode );
}

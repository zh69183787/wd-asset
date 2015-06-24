package com.wonders.asset.dao;

import java.util.List;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.model.Line;

/**
 * 线路dao接口
 * @author ycl 2013-11-25
 *
 */
public interface LineDao extends BaseDao<Line, String>{
    public Pagination findAllTheLine(int page,int pageSize);

	/**
	 * 根据code查询
	 * @param code
	 * @return
	 */
	public Line findByCode(String code);
	
	
	/**
	 * 根据发布查询
	 * @param publish
	 * @return
	 */
	public List<Line> findAllByPublish(String publish);
	
	public List<Line> findAllByOrder();
	
	/**
	 * 查询所有版本号
	 * @return 版本号
	 */
	public List<String> findAllVersion();
	
	/**
	 * 保存
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
	 * 查询
	 * @param id 主键
	 * @param name 名称 
	 * @param shortName 简称
	 * @param code 代码
	 * @return 线路
	 */
	public List<Line> findByCondition(String id,String name,String code,String version);
	
	/**
	 * 删除
	 * @param id 主键
	 */
	public void deleteById(String id);
	
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
	 * 根据codeId查询发布中的数据
	 * @param codeId
	 * @return
	 */
	public Line findByCodeIdWithPublish(String codeId);
	
	/**
	 * 根据code查询发布中的数据
	 * @param code
	 * @return
	 */
	public Line findByCodeWithPublish(String code);
	
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
	 * @return
	 */
	public List<Line> findByVersionAndCodeId(String id,String version,String codeId);
	
	public String findMaxCodeId(String version);
}

package com.wonders.asset.dao;

import java.util.List;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.model.dw.DwOverhaulLine;

public interface DwOverhaulLineDao extends BaseDao<DwOverhaulLine, String>{

	/**
	 * 大修更新改造按线路分布情况
	 * @return
	 */
	public List<DwOverhaulLine> findDwOverhaulLine(String year);
	
	/**
	 * 查询最新年份的大修更新改造线路
	 * @return
	 */
	public List<DwOverhaulLine> findLastestDwOverhaulLine();
	
	/**
	 * 查询最新年份的大修更新改造线路
	 * @return
	 */
	public List<DwOverhaulLine> findDwOverhaulLineByYear(String year);
	
	/**
	 * 查询所有年份
	 * @return
	 */
	public List<String> findDwOverhaulLineYear();
}

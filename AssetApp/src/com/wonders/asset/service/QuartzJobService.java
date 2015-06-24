package com.wonders.asset.service;

import org.dom4j.DocumentException;

/**
 * 定时任务Service类
 * @author Kai Yao
 * @date 2013-11-4
 */
public interface QuartzJobService {

    /**
     * 从数据中心导入数据
     */
    public void importData() throws Exception;


    /**
     * 同步更新备品备件数据
     * @throws Exception
     */
    public void sysncSpareData() throws Exception;
	
	/**
	 * 静态首页统计-资产概况
	 */
	public void calculateDwHomePageStat();
	
	/**
	 * 权属单位资产价值分布
	 */
	public void calculateDwProjectCompanyPrice();
	
	/**
	 * 线路资产价值分布
	 */
	public void calculateDwAssetLineValue();
	
	/**
	 * 使用单位资产价值分布
	 */
	public void calculateDwAssetUseOrganizationValue();
	
	/**
	 * 资产形成年份分析
	 */
	public void calculateDwProjectPriceByYear();
	
	/**
	 * 重要资产专业分布
	 */
	public void calculateDwImportantAssetRank();
	
	/**
	 * 动态首页统计
	 */
	public void calculateDwOverhaulProjectStat();
	
	/**
	 * 大修更新改造项目投资年变化
	 */
	public void calculateDwOverhaulBudgetYear();
	
	/**
	 * 计算资产重要指标情况
	 */
	public void calculateDwAssetImportantRatio();
	
	/**
	 * 报废资产实际寿命统计
	 */
	public void calculateDwScrapAssetUseLife();
	
	/**
	 * 资产分类统计
	 */
	public void calculateDwAssetTypeState();
	
	/**
	 * 大修更新改造按线路分布情况
	 */
	public void calculateDwOverhaulLine();
	
	/**
	 * 大修更新改造专业分布执行情况趋势
	 */
	public void calculateDwOverhaulMajorType();
	
	/**
	 * 资产实物汇总统计
	 */
	public void calculateDwAssetEntityStat();
		
	/**
	 * 权属单位资产价值分布
	 */
	public void calculateDwAssetOwnerOrganizationValue();
	
	/**
	 * 建设项目价值统计
	 */
	public void calculateDwAssetProjectLineValue();
	
	/**
	 * 将AssetObject中的数据存储到本系统中
	 * @throws DocumentException 
	 */
	public void transAssetObjectToAssetInfo() throws DocumentException ;
	
	/**
	 * 同步处理工作（合同和项目等）
	 */
	public void syncHandle();
	
}

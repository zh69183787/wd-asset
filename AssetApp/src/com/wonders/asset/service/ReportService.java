package com.wonders.asset.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wonders.asset.model.dw.DwAssetEntityStat;
import com.wonders.asset.model.dw.DwAssetImportantRatio;
import com.wonders.asset.model.dw.DwAssetLineValue;
import com.wonders.asset.model.dw.DwAssetOwnerOrganizationUnit;
import com.wonders.asset.model.dw.DwAssetProjectLineValue;
import com.wonders.asset.model.dw.DwAssetStateLine;
import com.wonders.asset.model.dw.DwAssetTypeState;
import com.wonders.asset.model.dw.DwAssetUseOrganizationUnit;
import com.wonders.asset.model.dw.DwCheckAssetAccuracyYear;
import com.wonders.asset.model.dw.DwHomePageStat;
import com.wonders.asset.model.dw.DwImportantAssetLine;
import com.wonders.asset.model.dw.DwImportantAssetRank;
import com.wonders.asset.model.dw.DwMaterialsConsume;
import com.wonders.asset.model.dw.DwOverhaulBudgetYear;
import com.wonders.asset.model.dw.DwOverhaulLine;
import com.wonders.asset.model.dw.DwOverhaulMajorType;
import com.wonders.asset.model.dw.DwOverhaulProjectStat;
import com.wonders.asset.model.dw.DwProjectCompanyPrice;
import com.wonders.asset.model.dw.DwProjectPriceByYear;
import com.wonders.asset.model.dw.DwScrapAssetUseLife;
import com.wonders.asset.model.dw.DwScrapStateAssetValue;
import com.wonders.asset.model.dw.DwStopStateAssetValue;

/**
 * 报表服务类
 * @author Kai Yao
 * @date 2013-11-11
 */
public interface ReportService {

	/*************计算报表************************/
	/**
	 * 计算大中小类报表
	 */
	public void calculateDwAssetEntityStat();
	
	/**
	 * 计算线路价值表
	 */
	public void calculateDwAssetLineValue();
		
	/**
	 * 计算项目线路价值表
	 */
	public void calculateDwAssetProjectLineValue();
	
	/**
	 * 计算使用单位资产价值表
	 */
	public void calculateDwAssetUseOrganizationValue();
	
	/**
	 * 计算权属单位价值信息
	 */
	public void calculateDwAssetOwnerOrganizationValue();
	
	/**
	 * 计算资产使用情况
	 */
	public void calculateDwHomePageStat();
	
	/**
	 * 计算项目公司资产价值统计
	 */
	public void calculateDwProjectCompanyPrice();
	
	/**
	 * 计算资产形成年份分析
	 */
	public void calculateDwProjectPriceByYear(); 
	
	/**
	 * 计算重要资产专业分布 
	 */
	public void calculateDwImportantAssetRank();
	
	/**
	 * 计算线路资产状态分布
	 */
	public void calculateDwAssetStateLine();
	
	/**
	 * 计算资产重要指标情况
	 */
	public void calculateDwAssetImportantRatio();
	
	/**
	 * 计算停用资产分类统计(按价值)
	 */
	public void calculateDwStopStateAssetValue();
	
	/**
	 * 计算报废资产分类统计(按价值)
	 */
	public void calculateDwScrapStateAssetValue();
	
	/**
	 * 计算大修更新改造项目总体情况
	 */
	public void calculateDwOverhaulProjectStat();
	
	/**
	 * 计算大修更新改造专业分布及变化
	 */
	public void calculateDwOverhaulMajorType();
	
	/**
	 * 计算大修/更新改造项目投资年变化情况
	 */
	public void calculateDwOverhaulBudgetYear();
	
	/**
	 * 计算报废资产实际寿命统计
	 */
	public void calculateDwScrapAssetUseLife();
	
	/**
	 * 计算实物资产盘点准确率统计（按年）
	 */
	public void calculateDwCheckAssetAccuracyYear();
	
	/**
	 * 计算物资消耗及人工情况
	 */
	public void calculateDwMaterialsConsume();
	
	/**
	 * 资产分类统计
	 */
	public void calculateDwAssetTypeState();
	
	/**
	 * 计算大修更新改造按线路分布 
	 */
	public void calculateDwOverhaulLine();
	
	/**********************************************/
	/**
	 * 查询大中小类报表数据
	 */
	public List<DwAssetEntityStat> findReportStat();
	
	public List<DwAssetEntityStat> findReportStatByYear(String start,String end);
	
	public List<String> findAllYearOfDwAssetEntityStat();
	

	/**
	 * 查询大类原值数据
	 * @return
	 */
	public List<DwAssetEntityStat> findReportMainType();
	
	/**
	 * 查询大类原值数据
	 * @return
	 */
	public List<DwAssetEntityStat> findReportMainTypeByDate(String start,String end);
	
	
	

	/**
	 * 查询线路资产统计表
	 * @return
	 */
	public List<DwAssetLineValue> findAssetLine();
	
	
	public List<String> findAllYearOfDwAssetLineValue();
	
	/**
	 * 查询线路资产统计表
	 * @return
	 */
	public List<DwAssetLineValue> findAssetLineByDate(String start,String end);

	
	/**
	 * 显示使用单位-资产价值统计
	 * @return
	 */
	public List<DwAssetUseOrganizationUnit> findAssetUseOrganization();
	
	/**
	 * 显示使用单位-资产价值统计
	 * @return
	 */
	public List<DwAssetUseOrganizationUnit> findAssetUseOrganizationByDate(String start,String end);
	
	/**
	 * 显示权属单位-资产价值统计
	 * @return
	 */
	public List<DwAssetOwnerOrganizationUnit> findAssetOwnerOrganization();
	
	/**
	 * 显示权属单位-资产价值统计
	 * @return
	 */
	public List<DwAssetOwnerOrganizationUnit> findAssetOwnerOrganizationByDate(String start,String end);
	
	
	/**
	 * 显示项目线路资产统计
	 */
	public List<DwAssetProjectLineValue> findAssetProjectLineValue();
	
	/**
	 * 显示项目线路资产统计
	 */
	public List<DwAssetProjectLineValue> findAssetProjectLineValueByDate(String start,String end);
	
	/**
	 * 查询线路项目表
	 */
	public List<Object[]> findLineAndCountProject();
	
	/**
	 * 查询线路资产价值表
	 */
	public List<DwAssetLineValue> findDwAssetLineValue();
	
	/**
	 * 查询使用单位
	 * @return
	 */
	public List<DwAssetUseOrganizationUnit> findUseOrgUnit();
	
	/**
	 * 查询资产实物
	 * @return
	 */
	public List<DwAssetEntityStat> findEntityStatForChart();
	
	/**
	 * 查询最新的资产首页显示数据
	 * @return
	 */
	public DwHomePageStat findLastestDwHomePageStat();
	
	/**
	 * 查询项目公司资产价值
	 * @return
	 */
	public List<DwProjectCompanyPrice> findDwProjectCompanyPrice();
	
	/**
	 * 查询资产形成年份分析
	 */
	public List<DwProjectPriceByYear> findDwProjectPriceByYear();
	
	/**
	 * 查询重要资产专业分布 
	 */
	public List<DwImportantAssetLine> findDwImportantAssetLine(String typeId);
	
	/**
	 * 查询线路资产状态分布
	 */
	public List<DwAssetStateLine> findDwAssetStateLine();
	
	/**
	 * 查询停用资产
	 */
	public List<DwStopStateAssetValue> findDwStopStateAssetValue();
	
	/**
	 * 查询报废资产
	 */
	public List<DwScrapStateAssetValue> findDwScrapStateAssetValue();
	
	/**
	 * 查询大修更新改造项目总体情况
	 */
	public DwOverhaulProjectStat findDwOverhaulProjectStat();
	
	/**
	 * 大修更新改造项目投资年变化
	 * @return
	 */
	public List<DwOverhaulBudgetYear> findDwOverhaulBudgetYear();
	
	/**
	 * 查询报废资产实际寿命统计
	 * @param subTypeName
	 */
	public DwScrapAssetUseLife findDwScrapAssetUseLifeBySubTypeId(String year,String subTypeName);
	
	/**
	 * 查询报废资产实际寿命统计的所有中类
	 * @param year
	 * @return
	 */
	public List<DwScrapAssetUseLife> findAllDwScrapUseLifeSubTypeName(String year);
	
	/**
	 * 查询实物资产盘点准确率统计
	 * @return
	 */
	public List<DwCheckAssetAccuracyYear> findDwCheckAssetAccuracyYear();
	
	/**
	 * 查询物资消耗及人工情况
	 */
	public List<DwMaterialsConsume> findDwMaterialsConsume(String year);
	
	/**
	 * 显示大修更新改造情况
	 */
	public List<DwOverhaulMajorType> findDwOverhaulMajorType(String year);
	
	
	/**
	 * 查询重要资产排名类型
	 */
	public List<String> findImportantAssetRankType();
	
	/**
	 * 根据类型查询重要资产排名
	 * @param type
	 * @return
	 */
	public List<DwImportantAssetRank> findImportantAssetRankByType(String type);
	
	/**
	 * 按专业分类查询
	 */
	public List<DwAssetTypeState> queryDwAssetTypeState();
	
	/**
	 * 按专业分类查询
	 */
	public List<DwAssetTypeState> queryDwAssetTypeState(String type);
	
	/**
	 * 查询重要资产分布
	 * @return
	 */
	public List<DwAssetImportantRatio> findDwAssetImportantRation();
	
	/**
	 * 查询更新改造线路
	 * @return
	 */
	public List<DwOverhaulLine> findDwOverhaulLine();
	
	/**
	 * 查询更新改造线路
	 * @return
	 */
	public List<DwOverhaulLine> findDwOverhaulLineByYear(String year);
	
	/**
	 * 查询大修更新所有的年份
	 * @return
	 */
	public List<String> findDwOverhaulLineYear();
	
	/**
	 * 查询大修更新改造专业分布执行情况
	 * @return
	 */
	public List<DwOverhaulMajorType> findDwOverhaulMajorType();
	
	
	public List<String> findAllYearOfDwAssetUseOrganizationUnit();
	
	public List<String> findAllYearOfDwAssetOwnerOrganizationUnit();
	
	public List<String> findAllYearOfDwAssetProjectLineValue();

    /**
     * 统计有资产的建设类项目
     * @param start
     * @param end
     * @param hasCount
     * @return
     */
    public List<DwAssetProjectLineValue> findAssetTransferValueByDate(String start, String end, boolean hasCount);
    public List<Object[]> findAssetTransferValue(String start, String end, boolean hasCount);
    public List<Object[]> getAssetTransferValue(String start, String end, boolean hasCount);
    

    public Map getAssetTransferKpi(String start, String end);

    public Map getAssetUpdateData(String start, String end);
    
    public Map getAssetUpdateKpi(String year);

	public Map getAssetUpdateKpi(String substring, String detailTypeCodeId);

	public Map getAssetDamageKpi();

	public Map getAssetDamageChart();
}

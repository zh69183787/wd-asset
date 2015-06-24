package com.wonders.asset.web.report;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.dom4j.DocumentException;

import com.wonders.asset.base.web.AbstractBaseAction;
import com.wonders.asset.model.bo.DwOverhaulLineBo;
import com.wonders.asset.model.bo.DwOverhaulMajorTypeBo;
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
import com.wonders.asset.service.AssetTaskService;
import com.wonders.asset.service.HouseAssetService;
import com.wonders.asset.service.LandAssetService;
import com.wonders.asset.service.ReportService;
import com.wonders.framework.util.ServiceProvider;

/**
 * 资产报表统计
 *
 * @author ycl
 */
public class ReportAction extends AbstractBaseAction {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private ReportService reportService;
    private Map kpi;
    private List<DwAssetProjectLineValue> values;
    private DwHomePageStat homePageStat;
    private List<Map> transfer;
    private LandAssetService landAssetService;
    private String detailTypeCodeId;
    
    private List<Map> land;//土地资源
    private List<Map> house;//房屋资源

    private List<Map> assetTask; //资产盘点年度完成情况
    
    public LandAssetService getLandAssetService() {
        return landAssetService;
    }

    public void setLandAssetService(LandAssetService landAssetService) {
        this.landAssetService = landAssetService;
    }

    public ReportService getReportService() {
        return reportService;
    }

    public void setReportService(ReportService reportService) {
        this.reportService = reportService;
    }

    public static void main(String[] args) {
        System.out.println(Calendar.getInstance().get(Calendar.YEAR)+"");
    }

    private String[] getDate() {
        String year = getRequestParameter("year");
        if (StringUtils.isEmpty(year)) {
            year = Calendar.getInstance().get(Calendar.YEAR) + "";
        }

        request.setAttribute("year", year);
        Calendar yearFirst = Calendar.getInstance();
        Calendar yearEnd = Calendar.getInstance();
        yearFirst.set(Calendar.YEAR, Integer.valueOf(year));
        yearFirst.set(Calendar.MONTH, yearFirst.getMinimum(Calendar.MONTH));
        yearFirst.set(Calendar.DATE, yearFirst.getMinimum(Calendar.DATE));
        yearFirst.set(Calendar.HOUR_OF_DAY, yearFirst.getMinimum(Calendar.HOUR_OF_DAY));
        yearFirst.set(Calendar.MINUTE, yearFirst.getMinimum(Calendar.MINUTE));
        yearFirst.set(Calendar.SECOND, yearFirst.getMinimum(Calendar.SECOND));

        yearEnd.set(Calendar.YEAR, Integer.valueOf(year));
        yearEnd.set(Calendar.MONTH, yearEnd.getMaximum(Calendar.MONTH));
        yearEnd.set(Calendar.DATE, yearEnd.getMaximum(Calendar.DATE));
        yearEnd.set(Calendar.HOUR_OF_DAY, yearEnd.getMaximum(Calendar.HOUR_OF_DAY));
        yearEnd.set(Calendar.MINUTE, yearEnd.getMaximum(Calendar.MINUTE));
        yearEnd.set(Calendar.SECOND, yearEnd.getMaximum(Calendar.SECOND));

        Date start = yearFirst.getTime();
        Date end = yearEnd.getTime();

        return new String[]{sdf.format(start), sdf.format(end)};
    }

    
    /**
     * 更新资产
     * @throws Exception
     */
    public void showAssetUpdateChartAndKpi() throws Exception {
        String[] dates = getDate();
        Map charts = ServiceProvider.getService(ReportService.class).getAssetUpdateData(dates[0], dates[1]);
        kpi = ServiceProvider.getService(ReportService.class).getAssetUpdateKpi(dates[1].substring(0, 4),detailTypeCodeId);
        HashMap map = new HashMap();
        map.put("charts", charts);
        map.put("kpi", kpi);
        renderJson(map);
    }

 
    /**
     * 资产报废
     * @throws Exception
     */
    public void showAssetDamageChartAndKpi() throws Exception{
    	//Map charts = ServiceProvider.getService(ReportService.class).getAssetDamageChart();
    	//ServiceProvider.getService(ReportService.class).getAssetDamageKpi();
       Map map = new HashedMap();
        Map charts = ServiceProvider.getService(ReportService.class).getAssetDamageChart();
        kpi = ServiceProvider.getService(ReportService.class).getAssetDamageKpi();
        map.put("charts", charts);
        map.put("kpi", kpi);
        renderJson(map);
    	


    }
    
    /**
     * 资产移交
     * @throws Exception
     */
    public void showAssetTransferChartAndKpi() throws Exception {

    	String[] dates = getDate();
        values = ServiceProvider.getService(ReportService.class).findAssetTransferValueByDate(dates[0], dates[1], true);
        List<Object[]> lists = ServiceProvider.getService(ReportService.class).findAssetTransferValue(dates[0], dates[1], true);
        List<Object[]> alists = ServiceProvider.getService(ReportService.class).getAssetTransferValue(dates[0], dates[1], true);
       
        homePageStat = ServiceProvider.getService(ReportService.class).findLastestDwHomePageStat();
//        transfer = new ArrayList<Map>();
        int completed = 0;
//        for (DwAssetProjectLineValue dwAssetProjectLineValue : values) {
//            Map result = new HashMap();
//            Double value = dwAssetProjectLineValue.getFinalPrice() + dwAssetProjectLineValue.getInvestAdded() - dwAssetProjectLineValue.getReserveEstimation() - dwAssetProjectLineValue.getTax();
//            Double percent = (dwAssetProjectLineValue.getOriginalValue()+dwAssetProjectLineValue.getSpareParts()) / value * 100;
//            result.put("percent", percent);
            
//            if(!percent.isInfinite() && percent>=100)
//                completed++;
//            result.put("shortName", dwAssetProjectLineValue.getShortName());
//            transfer.add(result);
//        }
        for(Object[] obj : alists){
        	if(obj!=null){
        		Double tmp = ((BigDecimal) obj[3]).doubleValue();
        		if(tmp >= 100){
        			completed ++;
        		}
        	}
        }
        kpi = ServiceProvider.getService(ReportService.class).getAssetTransferKpi(dates[0], dates[1]);
        HashMap map = new HashMap();
       
        List<Map> list = new ArrayList<Map>();
        
        for(Object[] object : lists){
        	HashMap map1 = new HashMap();
        	map1.put("plan", (BigDecimal) object[0]);
        	map1.put("actual", (BigDecimal) object[1]);
        	map1.put("shortName", (String) object[2]);
        	map1.put("zone", ((BigDecimal) object[3]).doubleValue());
        	
        	list.add(map1);  
        }
        List<Map> alist = new ArrayList<Map>();
        for(Object[] object : alists){
        	HashMap map2 = new HashMap();
        	map2.put("shortName", (String) object[0]);
        	map2.put("actual", (BigDecimal) object[1]);
        	map2.put("plan", (BigDecimal) object[2]);
        	map2.put("percent", ((BigDecimal) object[3]).doubleValue());
        	
        	alist.add(map2);  
        }
        
//        map.put("values", values);
//        map.put("transfer", "");
        map.put("lists", list);
        map.put("alists",alist);
        map.put("kpi",kpi);
        kpi.put("assetCount",homePageStat.getAssetCount());
        kpi.put("accessAssetPrice",homePageStat.getAccessAssetPrice());
        kpi.put("accessAssetProjectCount",homePageStat.getAccessAssetProjectCount());
        kpi.put("transfer",completed+"/"+kpi.get("total"));   
    
        renderJson(map);
    }

    
    /**
     * 为空或为""时返回 "0"
     * @param obj
     * @return
     */
    public String nullOrnullString (Object obj){

    	return ( (null == obj)  ||  "".equals(obj)  ) ? "0" : obj.toString();
    }
    /**
     * 土地/房屋资源
     * @throws Exception
     */
    public void showAssetLandHouseChartAndKpi() throws Exception {
    	
    	 List<Object[]> hResults = ServiceProvider.getService(HouseAssetService.class).getBuildAreaByLine();
    	 List<Object[]> lResults = ServiceProvider.getService(LandAssetService.class).getBuildAreaByLine();
    	 HashMap map = new HashMap();
    	 house = new ArrayList<Map>();
    	 land = new ArrayList<Map>();
    	 BigDecimal lSumArea = new BigDecimal(0); //土地资产总面积
    	 BigDecimal lSumAsset = new BigDecimal(0);//土地资产价值
    	 BigDecimal hSumArea = new BigDecimal(0); //房屋资产总面积
    	 BigDecimal hSumAsset = new BigDecimal(0); //房屋资产价值
    	 for (Object[] each : hResults) {

    		 Map result = new HashMap();
    		 
    			result.put( "line",each[0]);
    			result.put("area", each[1]);
    			
    			BigDecimal areaTmp = BigDecimal.valueOf(Double.parseDouble(nullOrnullString(each[1])));
    			BigDecimal assetTmp = BigDecimal.valueOf(Double.parseDouble(nullOrnullString(each[2])));
    			
    		 hSumArea = hSumArea.add(areaTmp);
    		 hSumAsset = hSumAsset.add(assetTmp);

    		 house.add(result);
    		 
    	 }
    	 
    	 for (Object[] each : lResults) {

    		 Map result = new HashMap();
    		 
    			result.put( "line",each[0]);
    			result.put("area", each[1]);
    			
    			BigDecimal areaTmp = BigDecimal.valueOf(Double.parseDouble(nullOrnullString(each[1])));
    			BigDecimal assetTmp = BigDecimal.valueOf(Double.parseDouble(nullOrnullString(each[2])));
    		 
    		 land.add(result);
    		 lSumArea = lSumArea.add(areaTmp);
    		 lSumAsset = lSumAsset.add(assetTmp);

    	 }
    	 kpi.put("lSumArea", String.format("%.2f",lSumArea.doubleValue()));
    	 kpi.put("lSumAsset", String.format("%.2f",lSumAsset.doubleValue()));
    	 kpi.put("hSumArea", String.format("%.2f",hSumArea.doubleValue()));
    	 kpi.put("hSumAsset", String.format("%.2f",hSumAsset.doubleValue()));
    	 map.put("house", house);
    	 map.put("land", land);
    	 map.put("kpi",kpi);
    	renderJson(map);
    	//System.out.println("showAssetLandHouseChartAndKpi is called");
    }
    
   /**
    * 资产盘点
    * @throws Exception
    */
    public void showAssetTaskChartAndKpi() throws Exception {
    	/*
             该任务正确盘点资产项数=该任务盘点资产项数-该任务盘点错误编码项目数
	该任务盘点准确率=该任务正确盘点资产项数/该任务盘点资产项数
	盘点准确率=所有任务准确率相加/盘点任务个数
	计划完成率=盘点任务完成率相加/盘点任务个数
	前提条件：盘点任务为的任务开始时间为当年
    	 */
    	
    	List<Object[]> result = ServiceProvider.getService(AssetTaskService.class).getAssetTaskByYear();
   	 	float accuracyRate = 0.0f; //盘点准确率
   	 	float planCompleteRate = 0.0f; //计划完成率
   	 	HashMap map = new HashMap();
   	 	//添加图表数据
   	 	//计算盘点准确率、 计划完成率
   	 	assetTask = new ArrayList<Map>();
   	 	
    	for (Object[] each : result) {

		 Map tmp = new HashMap();
		 
		 tmp.put( "task",each[1]);
		 tmp.put("percent", Float.parseFloat(nullOrnullString(each[2])));
			
		 if ( 0 != Integer.parseInt(nullOrnullString(each[3])) ){
			 accuracyRate += (Integer.parseInt(nullOrnullString(each[3])) - Integer.parseInt(nullOrnullString(each[4])) )/Double.parseDouble(nullOrnullString(each[3]));

		 }
		 planCompleteRate += Float.parseFloat(nullOrnullString(each[2]));

		 assetTask.add(tmp);
		 
	 }
    	accuracyRate /= result.size();
    	planCompleteRate /= result.size();
    	kpi.put("accuracyRate", String.format("%.2f", accuracyRate*100));
    	kpi.put("planCompleteRate", String.format("%.2f", planCompleteRate));
   	 map.put("result", assetTask);
   	 map.put("kpi",kpi);
   	renderJson(map);
    }
    
    /**
     * 计算
     *
     * @throws Exception
     */
    public void calculateDwAssetEntityStat() throws Exception {
//		ServiceProvider.getService(ReportService.class).calculateDwAssetEntityStat();
//		ServiceProvider.getService(ReportService.class).calculateDwAssetLineValue();
//		ServiceProvider.getService(ReportService.class).calculateDwAssetProjectLineValue(); //计算项目线路价值表
//		ServiceProvider.getService(ReportService.class).calculateDwAssetUseOrganizationValue();
//		ServiceProvider.getService(ReportService.class).calculateDwAssetOwnerOrganizationValue();
//		ServiceProvider.getService(ReportService.class).calculateDwHomePageStat(); //静态首页统计
//		ServiceProvider.getService(ReportService.class).calculateDwProjectCompanyPrice();
//		ServiceProvider.getService(ReportService.class).calculateDwProjectPriceByYear();
//		ServiceProvider.getService(ReportService.class).calculateDwImportantAssetRank();
//		
//		ServiceProvider.getService(ReportService.class).calculateDwOverhaulProjectStat(); //动态首页统计
//		ServiceProvider.getService(ReportService.class).calculateDwStopStateAssetValue();
//		ServiceProvider.getService(ReportService.class).calculateDwScrapStateAssetValue();
        ServiceProvider.getService(ReportService.class).calculateDwOverhaulMajorType();
//		ServiceProvider.getService(ReportService.class).calculateDwOverhaulBudgetYear();
//		ServiceProvider.getService(ReportService.class).calculateDwScrapAssetUseLife();
//		ServiceProvider.getService(ReportService.class).calculateDwCheckAssetAccuracyYear(); //todo
//		ServiceProvider.getService(ReportService.class).calculateDwAssetTypeState();
//		ServiceProvider.getService(ReportService.class).calculateDwAssetImportantRatio(); //计算资产重要指标情况
//		ServiceProvider.getService(ReportService.class).calculateDwOverhaulLine();

//		ServiceProvider.getService(ReportService.class).calculateDwAssetStateLine();//已删除	
//		ServiceProvider.getService(ReportService.class).calculateDwMaterialsConsume(); //已删除
    }

    /**
     * 显示资产实物大中小类
     *
     * @throws DocumentException
     * @throws Exception
     */
    public String showReportStat() throws DocumentException {
        String year = getRequestParameter("year");
        request.setAttribute("year", year);
        if (StringUtils.isEmpty(year)) {
            year = Calendar.getInstance().get(Calendar.YEAR) + "";
        }
        Calendar yearFirst = Calendar.getInstance();
        Calendar yearEnd = Calendar.getInstance();
        yearFirst.set(Calendar.YEAR, Integer.valueOf(year));
        yearFirst.set(Calendar.MONTH, yearFirst.getMinimum(Calendar.MONTH));
        yearFirst.set(Calendar.DATE, yearFirst.getMinimum(Calendar.DATE));
        yearFirst.set(Calendar.HOUR, yearFirst.getMinimum(Calendar.HOUR));
        yearFirst.set(Calendar.MINUTE, yearFirst.getMinimum(Calendar.MINUTE));
        yearFirst.set(Calendar.SECOND, yearFirst.getMinimum(Calendar.SECOND));

        yearEnd.set(Calendar.YEAR, Integer.valueOf(year));
        yearEnd.set(Calendar.MONTH, yearEnd.getMaximum(Calendar.MONTH));
        yearEnd.set(Calendar.DATE, yearEnd.getMaximum(Calendar.DATE));
        yearEnd.set(Calendar.HOUR, yearEnd.getMaximum(Calendar.HOUR));
        yearEnd.set(Calendar.MINUTE, yearEnd.getMaximum(Calendar.MINUTE));
        yearEnd.set(Calendar.SECOND, yearEnd.getMaximum(Calendar.SECOND));

        Date start = yearFirst.getTime();
        Date end = yearEnd.getTime();

        List<DwAssetEntityStat> list = ServiceProvider.getService(ReportService.class).findReportStatByYear(sdf.format(start), sdf.format(end));
        request.setAttribute("list", list);

        List<String> yearList = ServiceProvider.getService(ReportService.class).findAllYearOfDwAssetEntityStat();
        request.setAttribute("yearList", yearList);
        return "success";
    }

    /**
     * 显示大类统计表
     *
     * @throws DocumentException
     */
    public String showReportMain() throws DocumentException {
        String year = getRequestParameter("year");
        request.setAttribute("year", year);
        if (StringUtils.isEmpty(year)) {
            year = Calendar.getInstance().get(Calendar.YEAR) + "";
        }
        Calendar yearFirst = Calendar.getInstance();
        Calendar yearEnd = Calendar.getInstance();
        yearFirst.set(Calendar.YEAR, Integer.valueOf(year));
        yearFirst.set(Calendar.MONTH, yearFirst.getMinimum(Calendar.MONTH));
        yearFirst.set(Calendar.DATE, yearFirst.getMinimum(Calendar.DATE));
        yearFirst.set(Calendar.HOUR, yearFirst.getMinimum(Calendar.HOUR));
        yearFirst.set(Calendar.MINUTE, yearFirst.getMinimum(Calendar.MINUTE));
        yearFirst.set(Calendar.SECOND, yearFirst.getMinimum(Calendar.SECOND));

        yearEnd.set(Calendar.YEAR, Integer.valueOf(year));
        yearEnd.set(Calendar.MONTH, yearEnd.getMaximum(Calendar.MONTH));
        yearEnd.set(Calendar.DATE, yearEnd.getMaximum(Calendar.DATE));
        yearEnd.set(Calendar.HOUR, yearEnd.getMaximum(Calendar.HOUR));
        yearEnd.set(Calendar.MINUTE, yearEnd.getMaximum(Calendar.MINUTE));
        yearEnd.set(Calendar.SECOND, yearEnd.getMaximum(Calendar.SECOND));

        Date start = yearFirst.getTime();
        Date end = yearEnd.getTime();

        List<DwAssetEntityStat> list = ServiceProvider.getService(ReportService.class).findReportMainTypeByDate(sdf.format(start), sdf.format(end));
        request.setAttribute("list", list);

        List<String> yearList = ServiceProvider.getService(ReportService.class).findAllYearOfDwAssetEntityStat();
        request.setAttribute("yearList", yearList);
        return "success";
    }

    /**
     * 显示土地资产
     */

    public String  showLandAsset(){
        return "success";
    }
    /**
     * 显示线路-资产价值统计
     *
     * @throws DocumentException
     */
    public String showLineAssetValue() throws DocumentException {
        String year = getRequestParameter("year");
        request.setAttribute("year", year);
        if (StringUtils.isEmpty(year)) {
            year = Calendar.getInstance().get(Calendar.YEAR) + "";
        }
        Calendar yearFirst = Calendar.getInstance();
        Calendar yearEnd = Calendar.getInstance();
        yearFirst.set(Calendar.YEAR, Integer.valueOf(year));
        yearFirst.set(Calendar.MONTH, yearFirst.getMinimum(Calendar.MONTH));
        yearFirst.set(Calendar.DATE, yearFirst.getMinimum(Calendar.DATE));
        yearFirst.set(Calendar.HOUR, yearFirst.getMinimum(Calendar.HOUR));
        yearFirst.set(Calendar.MINUTE, yearFirst.getMinimum(Calendar.MINUTE));
        yearFirst.set(Calendar.SECOND, yearFirst.getMinimum(Calendar.SECOND));

        yearEnd.set(Calendar.YEAR, Integer.valueOf(year));
        yearEnd.set(Calendar.MONTH, yearEnd.getMaximum(Calendar.MONTH));
        yearEnd.set(Calendar.DATE, yearEnd.getMaximum(Calendar.DATE));
        yearEnd.set(Calendar.HOUR, yearEnd.getMaximum(Calendar.HOUR));
        yearEnd.set(Calendar.MINUTE, yearEnd.getMaximum(Calendar.MINUTE));
        yearEnd.set(Calendar.SECOND, yearEnd.getMaximum(Calendar.SECOND));

        Date start = yearFirst.getTime();
        Date end = yearEnd.getTime();

        List<DwAssetLineValue> list = ServiceProvider.getService(ReportService.class).findAssetLine();
        request.setAttribute("list", list);

        List<String> yearList = ServiceProvider.getService(ReportService.class).findAllYearOfDwAssetLineValue();
        request.setAttribute("yearList", yearList);
        return "success";
    }

    /**
     * 显示使用单位-资产价值统计表
     *
     * @throws DocumentException
     * @throws DocumentException
     */
    public String showUseOrganization() throws DocumentException {
        String year = getRequestParameter("year");
        request.setAttribute("year", year);
        if (StringUtils.isEmpty(year)) {
            year = Calendar.getInstance().get(Calendar.YEAR) + "";
        }
        Calendar yearFirst = Calendar.getInstance();
        Calendar yearEnd = Calendar.getInstance();
        yearFirst.set(Calendar.YEAR, Integer.valueOf(year));
        yearFirst.set(Calendar.MONTH, yearFirst.getMinimum(Calendar.MONTH));
        yearFirst.set(Calendar.DATE, yearFirst.getMinimum(Calendar.DATE));
        yearFirst.set(Calendar.HOUR, yearFirst.getMinimum(Calendar.HOUR));
        yearFirst.set(Calendar.MINUTE, yearFirst.getMinimum(Calendar.MINUTE));
        yearFirst.set(Calendar.SECOND, yearFirst.getMinimum(Calendar.SECOND));

        yearEnd.set(Calendar.YEAR, Integer.valueOf(year));
        yearEnd.set(Calendar.MONTH, yearEnd.getMaximum(Calendar.MONTH));
        yearEnd.set(Calendar.DATE, yearEnd.getMaximum(Calendar.DATE));
        yearEnd.set(Calendar.HOUR, yearEnd.getMaximum(Calendar.HOUR));
        yearEnd.set(Calendar.MINUTE, yearEnd.getMaximum(Calendar.MINUTE));
        yearEnd.set(Calendar.SECOND, yearEnd.getMaximum(Calendar.SECOND));

        Date start = yearFirst.getTime();
        Date end = yearEnd.getTime();

        List<DwAssetUseOrganizationUnit> list = ServiceProvider.getService(ReportService.class).findAssetUseOrganizationByDate(sdf.format(start), sdf.format(end));
        request.setAttribute("list", list);

        List<String> yearList = ServiceProvider.getService(ReportService.class).findAllYearOfDwAssetUseOrganizationUnit();
        request.setAttribute("yearList", yearList);
        return "success";
    }

    /**
     * 显示权属单位-资产价值统计表
     *
     * @throws DocumentException
     * @throws DocumentException
     */
    public String showOwnerOrganization() throws DocumentException {
        String year = getRequestParameter("year");
        request.setAttribute("year", year);
        if (StringUtils.isEmpty(year)) {
            year = Calendar.getInstance().get(Calendar.YEAR) + "";
        }
        Calendar yearFirst = Calendar.getInstance();
        Calendar yearEnd = Calendar.getInstance();
        yearFirst.set(Calendar.YEAR, Integer.valueOf(year));
        yearFirst.set(Calendar.MONTH, yearFirst.getMinimum(Calendar.MONTH));
        yearFirst.set(Calendar.DATE, yearFirst.getMinimum(Calendar.DATE));
        yearFirst.set(Calendar.HOUR, yearFirst.getMinimum(Calendar.HOUR));
        yearFirst.set(Calendar.MINUTE, yearFirst.getMinimum(Calendar.MINUTE));
        yearFirst.set(Calendar.SECOND, yearFirst.getMinimum(Calendar.SECOND));

        yearEnd.set(Calendar.YEAR, Integer.valueOf(year));
        yearEnd.set(Calendar.MONTH, yearEnd.getMaximum(Calendar.MONTH));
        yearEnd.set(Calendar.DATE, yearEnd.getMaximum(Calendar.DATE));
        yearEnd.set(Calendar.HOUR, yearEnd.getMaximum(Calendar.HOUR));
        yearEnd.set(Calendar.MINUTE, yearEnd.getMaximum(Calendar.MINUTE));
        yearEnd.set(Calendar.SECOND, yearEnd.getMaximum(Calendar.SECOND));

        Date start = yearFirst.getTime();
        Date end = yearEnd.getTime();

        List<DwAssetOwnerOrganizationUnit> list = ServiceProvider.getService(ReportService.class).findAssetOwnerOrganizationByDate(sdf.format(start), sdf.format(end));
        request.setAttribute("list", list);

        List<String> yearList = ServiceProvider.getService(ReportService.class).findAllYearOfDwAssetOwnerOrganizationUnit();
        request.setAttribute("yearList", yearList);
        return "success";
    }

    /**
     * 显示项目线路资产价值统计表
     *
     * @throws DocumentException
     */
    public String showProjectLineValue() throws DocumentException {
        String year = getRequestParameter("year");
        request.setAttribute("year", year);
        if (StringUtils.isEmpty(year)) {
            year = Calendar.getInstance().get(Calendar.YEAR) + "";
        }
        Calendar yearFirst = Calendar.getInstance();
        Calendar yearEnd = Calendar.getInstance();
        yearFirst.set(Calendar.YEAR, Integer.valueOf(year));
        yearFirst.set(Calendar.MONTH, yearFirst.getMinimum(Calendar.MONTH));
        yearFirst.set(Calendar.DATE, yearFirst.getMinimum(Calendar.DATE));
        yearFirst.set(Calendar.HOUR, yearFirst.getMinimum(Calendar.HOUR));
        yearFirst.set(Calendar.MINUTE, yearFirst.getMinimum(Calendar.MINUTE));
        yearFirst.set(Calendar.SECOND, yearFirst.getMinimum(Calendar.SECOND));

        yearEnd.set(Calendar.YEAR, Integer.valueOf(year));
        yearEnd.set(Calendar.MONTH, yearEnd.getMaximum(Calendar.MONTH));
        yearEnd.set(Calendar.DATE, yearEnd.getMaximum(Calendar.DATE));
        yearEnd.set(Calendar.HOUR, yearEnd.getMaximum(Calendar.HOUR));
        yearEnd.set(Calendar.MINUTE, yearEnd.getMaximum(Calendar.MINUTE));
        yearEnd.set(Calendar.SECOND, yearEnd.getMaximum(Calendar.SECOND));

        Date start = yearFirst.getTime();
        Date end = yearEnd.getTime();

        List<DwAssetProjectLineValue> list = ServiceProvider.getService(ReportService.class).findAssetProjectLineValueByDate(sdf.format(start), sdf.format(end));
        request.setAttribute("list", list);

        List<String> yearList = ServiceProvider.getService(ReportService.class).findAllYearOfDwAssetProjectLineValue();
        request.setAttribute("yearList", yearList);
        return "success";
    }


    /**
     * 查询到线路项目表
     *
     * @throws DocumentException
     * @throws IOException
     */
    public void queryProjectLine() throws DocumentException, IOException {
        List<Object[]> list = ServiceProvider.getService(ReportService.class).findLineAndCountProject();
        renderJson(list);
    }

    /**
     * 查询线路资产表
     *
     * @throws DocumentException
     * @throws IOException
     */
    public void queryAssetLineValue() throws DocumentException, IOException {
        List<DwAssetLineValue> list = ServiceProvider.getService(ReportService.class).findDwAssetLineValue();
        renderJson(list);
    }


    /**
     * 查询使用单位
     *
     * @throws IOException
     * @throws DocumentException
     */
    public void queryUseOrgUnit() throws IOException, DocumentException {
        List<DwAssetUseOrganizationUnit> list = ServiceProvider.getService(ReportService.class).findUseOrgUnit();
        renderJson(list);
    }

    /**
     * 查询重要资产专业分布
     *
     * @throws IOException
     * @throws DocumentException
     */
    public void queryImportantAssetRankType() throws IOException, DocumentException {
        System.out.println("hello");
        List<String> result = ServiceProvider.getService(ReportService.class).findImportantAssetRankType();
        renderJson(result);
    }

    /**
     * 查询重要资产排名类型
     *
     * @throws DocumentException
     * @throws IOException
     */
    public void queryImportantAssetRank() throws DocumentException, IOException {
        String type = getRequestParameter("type");
        List<DwImportantAssetRank> list = ServiceProvider.getService(ReportService.class).findImportantAssetRankByType(type);
        renderJson(list);
    }

    /**
     * 查询资产大类
     *
     * @throws DocumentException
     * @throws IOException
     */
    public void queryEntityStat() throws DocumentException, IOException {
        List<DwAssetEntityStat> list = ServiceProvider.getService(ReportService.class).findEntityStatForChart();
        renderJson(list);
    }

    /**
     * 查询首页显示数据
     *
     * @throws DocumentException
     * @throws IOException
     */
    public void queryDwHomePageStat() throws DocumentException, IOException {
        DwHomePageStat homePageStat = ServiceProvider.getService(ReportService.class).findLastestDwHomePageStat();
        renderJson(homePageStat);
    }


    /**
     * 显示项目公司资产价值分布
     *
     * @throws DocumentException
     * @throws IOException
     */
    public void queryProjectCompanyAssetValue() throws DocumentException, IOException {
        List<DwProjectCompanyPrice> list = ServiceProvider.getService(ReportService.class).findDwProjectCompanyPrice();
        renderJson(list);
    }

    /**
     * 显示资产形成年份分析
     *
     * @throws DocumentException
     * @throws IOException
     */
    public void queryAssetFormYear() throws DocumentException, IOException {
        List<DwProjectPriceByYear> list = ServiceProvider.getService(ReportService.class).findDwProjectPriceByYear();
        renderJson(list);
    }

    /**
     * 显示线路资产状态分布
     *
     * @throws DocumentException
     * @throws IOException
     */
    public void queryAssetStateLine() throws DocumentException, IOException {
        List<DwAssetStateLine> list = ServiceProvider.getService(ReportService.class).findDwAssetStateLine();
        renderJson(list);
    }

    /**
     * 显示专业分类统计
     *
     * @throws DocumentException
     * @throws IOException
     */
    public void queryDwAssetTypeState() throws DocumentException, IOException {
        String type = getRequestParameter("type");
        //List<DwAssetTypeState> list = ServiceProvider.getService(ReportService.class).queryDwAssetTypeState();
        List<DwAssetTypeState> list = ServiceProvider.getService(ReportService.class).queryDwAssetTypeState(type);
        renderJson(list);
    }

    /**
     * 资产动态首页-停用资产分类统计
     *
     * @throws DocumentException
     * @throws IOException
     */
    public void queryStopStateAssetValue() throws DocumentException, IOException {
        List<DwStopStateAssetValue> list = ServiceProvider.getService(ReportService.class).findDwStopStateAssetValue();
        renderJson(list);
    }

    /**
     * 资产动态首页-北非资产分类统计
     *
     * @throws DocumentException
     * @throws IOException
     */
    public void queryScrapStateAssetValue() throws DocumentException, IOException {
        List<DwScrapStateAssetValue> list = ServiceProvider.getService(ReportService.class).findDwScrapStateAssetValue();
        renderJson(list);
    }

    /**
     * 资产动态首页-查询大修更新改造项目总体情况
     *
     * @throws DocumentException
     * @throws IOException
     */
    public void queryDwOverhaulProjectStat() throws DocumentException, IOException {
        DwOverhaulProjectStat stat = ServiceProvider.getService(ReportService.class).findDwOverhaulProjectStat();
        renderJson(stat);
    }

    /**
     * 资产动态首页- 大修更新改造项目投资年变化
     *
     * @throws DocumentException
     * @throws IOException
     */
    public void queryDwOverHaulhaulBudgetYear() throws DocumentException, IOException {
        List<DwOverhaulBudgetYear> list = ServiceProvider.getService(ReportService.class).findDwOverhaulBudgetYear();
        renderJson(list);
    }

    /**
     * 显示报废资产实际寿命统计所有中类
     *
     * @throws DocumentException
     * @throws IOException
     */
    public void queryDwScrapAssetUseLifeSubType() throws DocumentException, IOException {
        String year = getRequestParameter("year");
        if (year == null || "".equals(year)) {
            year = Calendar.getInstance().get(Calendar.YEAR) + "";
        }
        List<DwScrapAssetUseLife> list = ServiceProvider.getService(ReportService.class).findAllDwScrapUseLifeSubTypeName(year);
        renderJson(list);
    }

    /**
     * 显示报废资产实际寿命统计
     *
     * @throws DocumentException
     * @throws IOException
     */
    public void queryDwScrapAssetUseLife() throws DocumentException, IOException {
        String year = getRequestParameter("year");
        String subTypeId = getRequestParameter("subTypeId");
        if (year == null || "".equals(year)) {
            year = Calendar.getInstance().get(Calendar.YEAR) + "";
        }
        DwScrapAssetUseLife list = ServiceProvider.getService(ReportService.class).findDwScrapAssetUseLifeBySubTypeId(year, subTypeId);
        renderJson(list);
    }

    /**
     * 实物资产盘点准确率统计
     *
     * @param
     * @return
     * @throws DocumentException
     * @throws IOException
     */
    public void queryDwCheckAssetAccuracyYear() throws DocumentException, IOException {
        List<DwCheckAssetAccuracyYear> list = ServiceProvider.getService(ReportService.class).findDwCheckAssetAccuracyYear();
        renderJson(list);
    }

    /**
     * 物资消耗及人工情况
     *
     * @throws DocumentException
     * @throws IOException
     */
    public void queryDwMaterialsConsume() throws DocumentException, IOException {
        String year = getRequestParameter("year");
        if (year == null || "".equals(year)) {
            year = Calendar.getInstance().get(Calendar.YEAR) + "";
        }
        List<DwMaterialsConsume> list = ServiceProvider.getService(ReportService.class).findDwMaterialsConsume(year);
        renderJson(list);
    }

    /**
     * 查询线路资产状态分布
     *
     * @throws IOException
     * @throws DocumentException
     */
    public void queryDwImportantAssetLine() throws IOException, DocumentException {
        String typeId = getRequestParameter("typeId");
        List<DwImportantAssetLine> list = ServiceProvider.getService(ReportService.class).findDwImportantAssetLine(typeId);
        renderJson(list);
    }

    /**
     * 显示大修更新改造情况
     *
     * @throws DocumentException
     * @throws IOException
     */
    public void queryDwOverhaulMajorType() throws DocumentException, IOException {
        List<DwOverhaulMajorType> list = ServiceProvider.getService(ReportService.class).findDwOverhaulMajorType();
        List<DwOverhaulMajorTypeBo> boList = null;
        if (list != null && list.size() > 0) {
            boList = new ArrayList<DwOverhaulMajorTypeBo>();
            for (int i = 0, len = list.size(); i < list.size(); i++) {
                boList.add(new DwOverhaulMajorTypeBo(list.get(i)));
            }
        }
        renderJson(boList);
    }

    /**
     * 显示线路更新改造
     *
     * @throws DocumentException
     * @throws IOException
     */
    public void queryDwOverhaulLine() throws DocumentException, IOException {
        List<DwOverhaulLine> list = ServiceProvider.getService(ReportService.class).findDwOverhaulLine();
        renderJson(list);
    }


    public void queryAssetImportantRatio() throws DocumentException, IOException {
        List<DwAssetImportantRatio> list = ServiceProvider.getService(ReportService.class).findDwAssetImportantRation();
        renderJson(list);
    }

    /**
     * 显示大修更新报表
     *大修/更新项目预算统计表
     * @return
     * @throws DocumentException
     */
    public String showDwOverhaulLine() throws DocumentException {
        String year = getRequestParameter("year");
        request.setAttribute("year", year);
        String showType = getRequestParameter("showType");
        if (StringUtils.isEmpty(showType)) {
            showType = "1";
        }
        List<String> yearList = ServiceProvider.getService(ReportService.class).findDwOverhaulLineYear();
        request.setAttribute("yearList", yearList);
        if (StringUtils.isEmpty(year)) {
            if (yearList != null && yearList.size() > 0) {
                year = yearList.get(0);
            }
        }

        request.setAttribute("showType", showType);
        List<DwOverhaulLine> list = ServiceProvider.getService(ReportService.class).findDwOverhaulLineByYear(year);
        List<DwOverhaulLineBo> boList = null;
        if (list != null && list.size() > 0) {
            boList = new ArrayList<DwOverhaulLineBo>();
            for (int i = 0, len = list.size(); i < len; i++) {
                boList.add(new DwOverhaulLineBo(list.get(i)));
            }
        }
        request.setAttribute("list", boList);
        return "success";
    }

    /**
     * 显示快速插入
     *
     * @return
     */
    public String showQuickInsert() {
        return "success";
    }

    public String quickInsert() throws DocumentException, IOException {
        String type = getRequestParameter("type");
        int iType = -1;
        if (StringUtils.isNotEmpty(type)) {
            iType = Integer.valueOf(type);
        }

        switch (iType) {
            case 1:
                ServiceProvider.getService(ReportService.class).calculateDwHomePageStat();   //静态首页统计-资产概况
                break;
            case 2:
                ServiceProvider.getService(ReportService.class).calculateDwProjectCompanyPrice();        //权属单位资产价值分布
                break;
            case 3:
                ServiceProvider.getService(ReportService.class).calculateDwAssetLineValue();        //线路资产价值分布
                break;
            case 4:
                ServiceProvider.getService(ReportService.class).calculateDwAssetUseOrganizationValue();        //使用单位资产价值分布
                break;
            case 5:
                ServiceProvider.getService(ReportService.class).calculateDwProjectPriceByYear();        //资产形成年份分析
                break;
            case 6:

                break;
            case 7:
                ServiceProvider.getService(ReportService.class).calculateDwImportantAssetRank();        //重要资产专业分布
                break;
            case 11:
                ServiceProvider.getService(ReportService.class).calculateDwOverhaulProjectStat();        //动态首页统计
                break;
            case 12:
                ServiceProvider.getService(ReportService.class).calculateDwOverhaulBudgetYear();        //大修更新改造项目投资年变化
                break;
            case 13:
                ServiceProvider.getService(ReportService.class).calculateDwAssetImportantRatio();        //计算资产重要指标情况
                break;
            case 14:
                ServiceProvider.getService(ReportService.class).calculateDwScrapAssetUseLife();            //报废资产实际寿命统计
                break;
            case 15:
                ServiceProvider.getService(ReportService.class).calculateDwAssetTypeState();            //资产分类统计
                break;
            case 16:
                ServiceProvider.getService(ReportService.class).calculateDwOverhaulLine();                //大修更新改造按线路分布情况
                break;
            case 17:
                ServiceProvider.getService(ReportService.class).calculateDwOverhaulMajorType();            //大修更新改造专业分布执行情况趋势
                break;
            case 21:
                ServiceProvider.getService(ReportService.class).calculateDwAssetEntityStat();        //资产实物汇总统计
                break;
            case 23:
                ServiceProvider.getService(ReportService.class).calculateDwAssetLineValue();        //线路资产价值分布
                break;
            case 24:
                ServiceProvider.getService(ReportService.class).calculateDwAssetUseOrganizationValue();        //使用单位资产价值分布
                break;
            case 25:
                ServiceProvider.getService(ReportService.class).calculateDwAssetOwnerOrganizationValue();        //权属单位资产价值分布
                break;
            case 26:
                ServiceProvider.getService(ReportService.class).calculateDwAssetProjectLineValue();    //建设项目价值统计
                break;
            case 27:
                ServiceProvider.getService(ReportService.class).calculateDwOverhaulLine();                //大修更新项目预算
                break;
            default:
                break;
        }
        render("text/html", "数据生成成功！");
        return null;
    }


    //得到保留6位小数后的字符串
    public String getFormattedMoney(String money) {
        if (money == null || "".equals(money)) {
            money = "0";
        }
        Double result = 0d;
        try {
            result = Double.valueOf(money)/10000;
        } catch (NumberFormatException e) {
            result = 0d;
        }
        DecimalFormat df = new DecimalFormat("#0.000000");
        if ("0.000000".equals(df.format(result))) {
            return "0";
        }
        return df.format(result);
    }

    public Map getKpi() {
        return kpi;
    }

    public void setKpi(Map kpi) {
        this.kpi = kpi;
    }

    public List<DwAssetProjectLineValue> getValues() {
        return values;
    }

    public void setValues(List<DwAssetProjectLineValue> values) {
        this.values = values;
    }

    public List<Map> getTransfer() {
        return transfer;
    }

    public void setTransfer(List<Map> transfer) {
        this.transfer = transfer;
    }

    /**
     * 导出土地报表
     */
    public void excelLandAsset(){
        String name="土地报表.xls";
        response.setContentType("application/x-download");
        response.setCharacterEncoding("utf-8");
        try {
            response.setHeader("Content-Disposition", "attachment;filename="+new String(name.getBytes("utf-8"), "ISO8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Map<String,String> filter = createFilterMap();
        Map<String,String> sort = createSortMap();
        List<Map<String,String>> list=landAssetService.findReport();
        Integer rowNumber = list.size();
        String[] title=new String[]{"序号","线路","建筑类项目名称","建筑占地面积（平方米）","土地总面积（平方米）","其中地面征地（平方米）","其中地下征地或征用道路（平方米）","其中带征地（平方米）","征地动迁总费用（元）","征地动迁费用（元）","带征地费用（元）"};
        try {
            // 创建新的Excel 工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet();
            sheet.setDefaultColumnWidth((short)15);
            // 创建第一行并设置标题
            HSSFRow titleRow = sheet.createRow(0);
            for(int i=0; i<title.length; i++){
                titleRow.createCell(i).setCellValue(title[i]);
            }
            // 创建单元格并为单元格赋值
            for(int i=0; i<rowNumber; i++){
                HSSFRow row = sheet.createRow(i+1);
                HSSFCell cell0=row.createCell(0);
                cell0.setCellValue(i+1);
                HSSFCell cell1=row.createCell(1);
                cell1.setCellValue(list.get(i).get("line"));
                HSSFCell cell2=row.createCell(2);
                cell2.setCellValue(list.get(i).get("builderProject"));
                HSSFCell cell3=row.createCell(3);
                cell3.setCellValue(list.get(i).get("totalBuildArea"));
                HSSFCell cell4=row.createCell(4);
                cell4.setCellValue(list.get(i).get("totalLandArea"));
                HSSFCell cell5=row.createCell(5);
                cell5.setCellValue(list.get(i).get("totalLandRequisitionArea"));
                HSSFCell cell6=row.createCell(6);
                cell6.setCellValue(list.get(i).get("undergroundRequisitionArea"));
                HSSFCell cell7=row.createCell(7);
                cell7.setCellValue(list.get(i).get("totalInclandRequisitionArea"));
                HSSFCell cell8=row.createCell(8);
                cell8.setCellValue(list.get(i).get("totalLandTotalFee"));
                HSSFCell cell9=row.createCell(9);
                cell9.setCellValue(list.get(i).get("totalLandRequisitionTotalfee"));
                HSSFCell cell10=row.createCell(10);
                cell10.setCellValue(list.get(i).get("totalInlandRequisitionTotalfee"));
            }

            OutputStream out = response.getOutputStream();
            workbook.write(out);

            out.flush();
            out.close();
            response.sendRedirect("");
        } catch (Exception e) {
            System.out.println("Exception：" + e);
        }
    }

	public String getDetailTypeCodeId() {
		return detailTypeCodeId;
	}

	public void setDetailTypeCodeId(String detailTypeCodeId) {
		this.detailTypeCodeId = detailTypeCodeId;
	}

	public List<Map> getHouse() {
		return house;
	}

	public void setHouse(List<Map> house) {
		this.house = house;
	}




}

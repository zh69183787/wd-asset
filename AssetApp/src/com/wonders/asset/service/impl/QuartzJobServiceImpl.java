package com.wonders.asset.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;
import org.dom4j.DocumentException;

import com.wonders.asset.service.AllocateAssetService;
import com.wonders.asset.service.AssetRecordService;
import com.wonders.asset.service.AssetService;
import com.wonders.asset.service.AssetTaskService;
import com.wonders.asset.service.BorrowAssetService;
import com.wonders.asset.service.DamageAssetService;
import com.wonders.asset.service.DisableAssetService;
import com.wonders.asset.service.HouseAssetService;
import com.wonders.asset.service.LandAssetService;
import com.wonders.asset.service.ProjectService;
import com.wonders.asset.service.QuartzJobService;
import com.wonders.asset.service.ReportService;
import com.wonders.asset.service.SparePartsService;
import com.wonders.asset.service.StopAssetService;
import com.wonders.framework.util.ServiceProvider;

public class QuartzJobServiceImpl implements QuartzJobService {

    private Logger logger = Logger.getLogger(QuartzJobServiceImpl.class);

    @Override
    public void importData() throws Exception{
        System.out.println("importData");
        ServiceProvider.getService(DisableAssetService.class).importData();
        ServiceProvider.getService(AssetRecordService.class).importData();
        ServiceProvider.getService(BorrowAssetService.class).importData();
        ServiceProvider.getService(HouseAssetService.class).importData();
        ServiceProvider.getService(LandAssetService.class).importData();
        ServiceProvider.getService(StopAssetService.class).importData();
        ServiceProvider.getService(AllocateAssetService.class).importData();
        ServiceProvider.getService(DamageAssetService.class).importData();
        ServiceProvider.getService(AssetTaskService.class).importData();
    }
    
    @Override
	public void sysncSpareData() throws DocumentException {
        System.out.println("sysncSpareData");
        GregorianCalendar begin = new GregorianCalendar();
        begin.add(Calendar.DATE, -1);
        begin.set(Calendar.HOUR_OF_DAY,0);
        begin.set(Calendar.MINUTE,0);
        begin.set(Calendar.SECOND,0);
        GregorianCalendar end = new GregorianCalendar();
        end.add(Calendar.DATE, -1);
        end.set(Calendar.HOUR_OF_DAY,23);
        end.set(Calendar.MINUTE,59);
        end.set(Calendar.SECOND,59);
       
        ServiceProvider.getService(SparePartsService.class).syncInsertData(begin.getTime(),end.getTime());
        ServiceProvider.getService(SparePartsService.class).syncUpdateData(begin.getTime(),end.getTime());
		
	}

    @Override
	public void calculateDwHomePageStat() {
		try {
            System.out.println("calculateDwHomePageStat");
            ServiceProvider.getService(ReportService.class).calculateDwHomePageStat();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void calculateDwProjectCompanyPrice() {
		try {
            System.out.println("calculateDwProjectCompanyPrice");
			ServiceProvider.getService(ReportService.class).calculateDwProjectCompanyPrice();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void calculateDwAssetLineValue() {
		try {
            System.out.println("calculateDwAssetLineValue");
			ServiceProvider.getService(ReportService.class).calculateDwAssetLineValue();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void calculateDwAssetUseOrganizationValue() {
		try {
            System.out.println("calculateDwAssetUseOrganizationValue");
			ServiceProvider.getService(ReportService.class).calculateDwAssetUseOrganizationValue();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void calculateDwProjectPriceByYear() {
		try {
            System.out.println("calculateDwProjectPriceByYear");
			ServiceProvider.getService(ReportService.class).calculateDwProjectPriceByYear();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void calculateDwImportantAssetRank() {
		try {
            System.out.println("calculateDwImportantAssetRank");
			ServiceProvider.getService(ReportService.class).calculateDwImportantAssetRank();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void calculateDwOverhaulProjectStat() {
		try {
            System.out.println("calculateDwOverhaulProjectStat");
			ServiceProvider.getService(ReportService.class).calculateDwOverhaulProjectStat();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void calculateDwOverhaulBudgetYear() {
		try {
            System.out.println("calculateDwOverhaulBudgetYear");
			ServiceProvider.getService(ReportService.class).calculateDwOverhaulBudgetYear();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void calculateDwAssetImportantRatio() {
		try {
//            ApplicationContext ac = new ClassPathXmlApplicationContext("spring/schedule/applicationContext-quartz.xml");
//            System.out.println(ac.getBean("startQuertz"));
//            logger.info("======================ImportantRatio=======start=========================="+this);
//            logger.info(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
//            logger.info(this);
			ServiceProvider.getService(ReportService.class).calculateDwAssetImportantRatio();
//            logger.info("======================ImportantRatio========end========================="+this);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void calculateDwScrapAssetUseLife() {
		try {
            System.out.println("calculateDwScrapAssetUseLife");
			ServiceProvider.getService(ReportService.class).calculateDwScrapAssetUseLife();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void calculateDwAssetTypeState() {
		try {
            System.out.println("calculateDwAssetTypeState");
			ServiceProvider.getService(ReportService.class).calculateDwAssetTypeState();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void calculateDwOverhaulLine() {
		try {
            System.out.println("calculateDwOverhaulLine");
			ServiceProvider.getService(ReportService.class).calculateDwOverhaulLine();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void calculateDwOverhaulMajorType() {
		try {
            System.out.println("calculateDwOverhaulMajorType");
			ServiceProvider.getService(ReportService.class).calculateDwOverhaulMajorType();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void calculateDwAssetEntityStat() {
		try {
            System.out.println("calculateDwAssetEntityStat");
			ServiceProvider.getService(ReportService.class).calculateDwAssetEntityStat();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void calculateDwAssetOwnerOrganizationValue() {
		try {
            System.out.println("calculateDwAssetOwnerOrganizationValue");
			ServiceProvider.getService(ReportService.class).calculateDwAssetOwnerOrganizationValue();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void calculateDwAssetProjectLineValue() {
		try {
            System.out.println("calculateDwAssetProjectLineValue");
			ServiceProvider.getService(ReportService.class).calculateDwAssetProjectLineValue();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void transAssetObjectToAssetInfo() throws DocumentException {
        System.out.println("transAssetObjectToAssetInfo");
		ServiceProvider.getService(AssetService.class).transAssetObjectToAssetInfo(new Date());
	}

	@Override
	public void syncHandle() {
		try {
			ServiceProvider.getService(ProjectService.class).syncProjectAndContract();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}


}

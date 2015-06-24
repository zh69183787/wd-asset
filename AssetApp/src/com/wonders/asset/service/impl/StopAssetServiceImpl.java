package com.wonders.asset.service.impl;

import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.dom4j.DocumentException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wonders.asset.base.service.impl.BaseServiceImpl;
import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.dao.StopAssetDao;
import com.wonders.asset.model.StopAsset;
import com.wonders.asset.service.AttachmentService;
import com.wonders.asset.service.DataStoreService;
import com.wonders.asset.service.LandAssetService;
import com.wonders.asset.service.StopAssetService;
import com.wonders.webservice.dto.AttachmentDto;
import com.wonders.webservice.dto.BorrowAssetDto;
import com.wonders.webservice.dto.Response;
import com.wonders.webservice.dto.StopAssetDto;

/**
 * Created by HH on 2014/11/12.
 */
public class StopAssetServiceImpl extends BaseServiceImpl<StopAsset,String>  implements StopAssetService {
    private DataStoreService dataStoreService;

    private StopAssetDao stopAssetDao;
    private AttachmentService attachmentService;
    
    public AttachmentService getAttachmentService() {
		return attachmentService;
	}

	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}
	
	/**
     * 读取数据并执行保存，更新，查找工作
     * @throws JAXBException
     * @throws DocumentException
     */
    @Override
    public void importData() throws JAXBException, DocumentException {
        List<Response> responseList = dataStoreService.getDataFromStore(StopAssetDto.class);
		for (Response response : responseList) {
			stopAssetDao.save(response.getList());
            stopAssetDao.batchInsert();
            stopAssetDao.batchUpdate();
            stopAssetDao.clear();

			for (Object obj : response.getList()) {
				StopAssetDto stopAsset = (StopAssetDto) obj;
				List<AttachmentDto> attachmentList = stopAsset.getAttachmentList();
				attachmentService.importData(attachmentList,stopAsset.getUploadDate(), "4",stopAsset.getLoanNo());
			}
			dataStoreService.confirmData(StopAssetDto.class,response.getId());
		}
    }

    public StopAssetDao getStopAssetDao() {
        return stopAssetDao;
    }

    public void setStopAssetDao(StopAssetDao stopAssetDao) {
        this.stopAssetDao = stopAssetDao;
        setBaseDao(stopAssetDao);
    }

    public DataStoreService getDataStoreService() {
        return dataStoreService;
    }

    public void setDataStoreService(DataStoreService dataStoreService) {
        this.dataStoreService = dataStoreService;
    }


    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/schedule/applicationContext-quartz.xml");
        System.out.println(ac.getBean("startQuertz"));

    }

	@Override
	public Pagination<StopAsset> findStopAndAsset(
			Map<String, String> filterMap, Map<String, String> sortMap,
			int startIndex, int pageSize) {
		return stopAssetDao.findStopAndAsset(filterMap, sortMap, startIndex, pageSize);
	}
}

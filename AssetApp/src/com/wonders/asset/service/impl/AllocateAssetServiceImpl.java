package com.wonders.asset.service.impl;

import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.dom4j.DocumentException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wonders.asset.base.service.impl.BaseServiceImpl;
import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.dao.AllocateAssetDao;
import com.wonders.asset.model.AllocateAsset;
import com.wonders.asset.service.AllocateAssetService;
import com.wonders.asset.service.AttachmentService;
import com.wonders.asset.service.DataStoreService;
import com.wonders.webservice.dto.AllocateAssetDto;
import com.wonders.webservice.dto.AttachmentDto;
import com.wonders.webservice.dto.Response;

/**
 * Created by HH on 2014/11/9.
 */
public class AllocateAssetServiceImpl extends BaseServiceImpl<AllocateAsset,String> implements AllocateAssetService {

    private AllocateAssetDao allocateAssetDao;
    private AttachmentService attachmentService;

    public AttachmentService getAttachmentService() {
		return attachmentService;
	}

	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}

	public AllocateAssetDao getAllocateAssetDao() {
        return allocateAssetDao;
    }

    public void setAllocateAssetDao(AllocateAssetDao allocateAssetDao) {
        this.allocateAssetDao = allocateAssetDao;
        setBaseDao(allocateAssetDao);
    }

    private DataStoreService dataStoreService;

    public DataStoreService getDataStoreService() {
        return dataStoreService;
    }

    public void setDataStoreService(DataStoreService dataStoreService) {
        this.dataStoreService = dataStoreService;
    }

    /**
     * 从XML中读取数据，并放入到T_ALLOCATE_ASSET中
     * @throws JAXBException
     * @throws DocumentException
     */
    @Override
    public void importData() throws JAXBException, DocumentException {
        List<Response> responseList =  dataStoreService.getDataFromStore(AllocateAssetDto.class);
        for (Response response : responseList) {
        	allocateAssetDao.save(response.getList());
            
        	allocateAssetDao.batchInsert();
        	allocateAssetDao.batchUpdate();
        	allocateAssetDao.clear();     

            for (Object obj : response.getList()) {
            	AllocateAssetDto allocateAsset = (AllocateAssetDto)obj;
            	List<AttachmentDto> attachmentList = allocateAsset.getAttachmentList();
            	attachmentService.importData(attachmentList,allocateAsset.getUploadDate(),"2",allocateAsset.getLoanNo());
            	
            }   
           dataStoreService.confirmData(AllocateAssetDto.class,response.getId());
        }  

    }



    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        AllocateAssetService service = (AllocateAssetService)ac.getBean("allocateAssetService");
        try {
            service.importData();
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

	@Override
	public Pagination<AllocateAsset> findAllocateAndAsset(
			Map<String, String> filterMap, Map<String, String> sortMap,
			int startIndex, int pageSize) {
		return allocateAssetDao.findAllocateAndAsset(filterMap,sortMap,startIndex,pageSize);
	}

}

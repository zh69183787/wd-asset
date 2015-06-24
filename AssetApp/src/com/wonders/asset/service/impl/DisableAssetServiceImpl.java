package com.wonders.asset.service.impl;

import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.dom4j.DocumentException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wonders.asset.base.service.impl.BaseServiceImpl;
import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.dao.DisableAssetDao;
import com.wonders.asset.model.DisableAsset;
import com.wonders.asset.service.AttachmentService;
import com.wonders.asset.service.DataStoreService;
import com.wonders.asset.service.DisableAssetService;
import com.wonders.asset.service.LandAssetService;
import com.wonders.webservice.dto.AttachmentDto;
import com.wonders.webservice.dto.DisableAssetDto;
import com.wonders.webservice.dto.Response;
import com.wonders.webservice.dto.StopAssetDto;

public class DisableAssetServiceImpl extends BaseServiceImpl<DisableAsset,String>  implements DisableAssetService {
    private DataStoreService dataStoreService;


    private DisableAssetDao disableAssetDao;
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
        List<Response> responseList = dataStoreService.getDataFromStore(DisableAssetDto.class);
		for (Response response : responseList) {   
			disableAssetDao.save(response.getList());
			disableAssetDao.batchInsert();
			disableAssetDao.batchUpdate();
			disableAssetDao.clear();

			for (Object obj : response.getList()) {
				DisableAssetDto disableAsset = (DisableAssetDto) obj;
				List<AttachmentDto> attachmentList = disableAsset.getAttachmentList();
				attachmentService.importData(attachmentList,disableAsset.getUploadDate(),"5",disableAsset.getLoanNo());
			}
			dataStoreService.confirmData(DisableAssetDto.class,response.getId());  
		}
    }


    


	public DisableAssetDao getDisableAssetDao() {
		return disableAssetDao;
	}


	public void setDisableAssetDao(DisableAssetDao disableAssetDao) {
		this.disableAssetDao = disableAssetDao;
		setBaseDao(disableAssetDao);
	}


	public DataStoreService getDataStoreService() {
        return dataStoreService;
    }

    public void setDataStoreService(DataStoreService dataStoreService) {
        this.dataStoreService = dataStoreService;
    }


    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        LandAssetService service = (LandAssetService)ac.getBean("landAssetService");
        try {
            service.importData();
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }


	@Override
	public Pagination<DisableAsset> findDisableAndAsset(
			Map<String, String> filterMap, Map<String, String> sortMap,
			int startIndex, int pageSize) {
		return disableAssetDao.findDisableAndAsset(filterMap,sortMap,startIndex,pageSize);
	}
}

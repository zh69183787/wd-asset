package com.wonders.asset.service.impl;

import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.dom4j.DocumentException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wonders.asset.base.service.impl.BaseServiceImpl;
import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.dao.BorrowAssetDao;
import com.wonders.asset.model.BorrowAsset;
import com.wonders.asset.service.AttachmentService;
import com.wonders.asset.service.BorrowAssetService;
import com.wonders.asset.service.DataStoreService;
import com.wonders.webservice.dto.AttachmentDto;
import com.wonders.webservice.dto.BorrowAssetDto;
import com.wonders.webservice.dto.DamageAssetDto;
import com.wonders.webservice.dto.Response;

public class BorrowAssetServiceImpl extends BaseServiceImpl<BorrowAsset, String> implements BorrowAssetService{

	private DataStoreService dataStoreService;
	private BorrowAssetDao borrowAssetDao;
	private AttachmentService attachmentService;
	
	@Override
	public void importData() throws JAXBException, DocumentException {
		List<Response> responseList = dataStoreService.getDataFromStore(BorrowAssetDto.class);
		for (Response response : responseList) {
			borrowAssetDao.save(response.getList());
			borrowAssetDao.batchInsert();
			borrowAssetDao.batchUpdate();  
			borrowAssetDao.clear();

			for (Object obj : response.getList()) {
				BorrowAssetDto borrowAsset = (BorrowAssetDto) obj;
				List<AttachmentDto> attachmentList = borrowAsset.getAttachmentList();
				attachmentService.importData(attachmentList,borrowAsset.getUploadDate(), "3",borrowAsset.getLoanNo());
			}
			dataStoreService.confirmData(BorrowAssetDto.class,response.getId());
		}
		
	}
	
	public AttachmentService getAttachmentService() {
		return attachmentService;
	}

	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}

	public DataStoreService getDataStoreService() {
		return dataStoreService;
	}

	public void setDataStoreService(DataStoreService dataStoreService) {
		this.dataStoreService = dataStoreService;
	}

	public BorrowAssetDao getBorrowAssetDao() {
		return borrowAssetDao;
	}

	public void setBorrowAssetDao(BorrowAssetDao borrowAssetDao) {
		this.borrowAssetDao = borrowAssetDao;
		setBaseDao(borrowAssetDao);
	}
	
	
	public static void main(String[] args) throws Exception{
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		BorrowAssetService service = (BorrowAssetService) ac.getBean("borrowAssetService");
        service.importData();
        System.out.println("operation over..");
       
	}

	@Override
	public Pagination<BorrowAsset> findBorrowAndAsset(Map<String, String> filterMap,
			Map<String, String> sortMap, int startIndex, int pageSize) {
		return borrowAssetDao.findBorrowAndAsset(filterMap, sortMap, startIndex, pageSize);
	}

}

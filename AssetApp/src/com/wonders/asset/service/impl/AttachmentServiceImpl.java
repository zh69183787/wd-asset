package com.wonders.asset.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.dom4j.DocumentException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wonders.asset.base.service.impl.BaseServiceImpl;
import com.wonders.asset.dao.AttachmentDao;
import com.wonders.asset.model.Attachment;
import com.wonders.asset.service.AllocateAssetService;
import com.wonders.asset.service.AttachmentService;
import com.wonders.webservice.dto.AttachmentDto;

public class AttachmentServiceImpl extends BaseServiceImpl<Attachment, String>
		implements AttachmentService {

	private AttachmentDao attachmentDao;

	public AttachmentDao getAttachmentDao() {
		return attachmentDao;
	}

	public void setAttachmentDao(AttachmentDao attachmentDao) {
		this.attachmentDao = attachmentDao;
		setBaseDao(attachmentDao);
	}

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"spring/applicationContext.xml");
		AllocateAssetService service = (AllocateAssetService) ac
				.getBean("allocateAssetService");
		try {
			service.importData();
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void importData(List<AttachmentDto> attachmentList, Date uploadDate,String type,String id) throws JAXBException, DocumentException {
		if(attachmentList == null){
			return ;
		}
		for (AttachmentDto attachmentDto : attachmentList) {
			attachmentDto.setUploadDate(uploadDate);
			attachmentDto.setObjectId(id);
			attachmentDto.setType(type);
		}
		attachmentDao.save(attachmentList);
		attachmentDao.batchInsert();
		attachmentDao.batchUpdate();
		attachmentDao.clear();
//		attachmentDao.updateObjectId(id,type);
		
	}

	@Override
	public List<Attachment> getAttachments(String objectId, String type) {
		return attachmentDao.findByAttachmentId(objectId,type);
	}

}

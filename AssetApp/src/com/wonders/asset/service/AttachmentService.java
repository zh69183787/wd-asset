package com.wonders.asset.service;

import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.dom4j.DocumentException;

import com.wonders.asset.base.service.BaseService;
import com.wonders.asset.model.Attachment;
import com.wonders.webservice.dto.AttachmentDto;

public interface AttachmentService extends BaseService<Attachment,String> {
    void importData(List<AttachmentDto> attachmentList,Date uploadDate,String type,String id) throws JAXBException, DocumentException;
    List<Attachment> getAttachments(String objectId,String type);
}

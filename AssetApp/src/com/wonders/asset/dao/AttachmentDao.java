package com.wonders.asset.dao;

import java.util.List;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.model.Attachment;

public interface AttachmentDao  extends BaseDao<Attachment, String>{

	void clear();  
    void batchUpdate();
    void batchInsert();
    void updateObjectId(String objectId,String type) ;
    List<Attachment> findByAttachmentId(String objectId,String type);
}

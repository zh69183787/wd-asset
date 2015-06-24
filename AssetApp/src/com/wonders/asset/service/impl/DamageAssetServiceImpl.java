package com.wonders.asset.service.impl;

import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.dom4j.DocumentException;

import com.wonders.asset.base.service.impl.BaseServiceImpl;
import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.dao.AttachmentDao;
import com.wonders.asset.dao.DamageAssetDao;
import com.wonders.asset.model.DamageAsset;
import com.wonders.asset.service.AttachmentService;
import com.wonders.asset.service.DamageAssetService;
import com.wonders.asset.service.DataStoreService;
import com.wonders.webservice.dto.AttachmentDto;
import com.wonders.webservice.dto.DamageAssetDto;
import com.wonders.webservice.dto.Response;

/**
 * Created by Administrator on 2014/11/5.
 */
public class DamageAssetServiceImpl  extends BaseServiceImpl<DamageAsset,String> implements DamageAssetService {

    private DataStoreService dataStoreService;
    private AttachmentService attachmentService;

    public AttachmentService getAttachmentService() {
		return attachmentService;
	}

	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}

	private DamageAssetDao damageAssetDao;

    @Override
	public void importData() throws JAXBException, DocumentException {
		List<Response> responseList = dataStoreService
				.getDataFromStore(DamageAssetDto.class);
		for (Response response : responseList) {
			damageAssetDao.save(response.getList());

			damageAssetDao.batchInsert();
			damageAssetDao.batchUpdate();
			damageAssetDao.clear();

			for (Object obj : response.getList()) {
				DamageAssetDto damageAsset = (DamageAssetDto) obj;
				List<AttachmentDto> attachmentList = damageAsset
						.getAttachmentList();
				attachmentService.importData(attachmentList,
						damageAsset.getUploadDate(), "1",damageAsset.getId());
			}
			dataStoreService.confirmData(DamageAssetDto.class,response.getId());
		}
	}

    public DataStoreService getDataStoreService() {
        return dataStoreService;
    }

    public void setDataStoreService(DataStoreService dataStoreService) {
        this.dataStoreService = dataStoreService;
    }

    public DamageAssetDao getDamageAssetDao() {
        return damageAssetDao;
    }

    public void setDamageAssetDao(DamageAssetDao damageAssetDao) {
        this.damageAssetDao = damageAssetDao;
        setBaseDao(damageAssetDao);
    }

	@Override
	public Pagination<DamageAsset> findDamageAndAsset(
			Map<String, String> filterMap, Map<String, String> sortMap,
			int startIndex, int pageSize) {
		return damageAssetDao.findDamageAndAsset(filterMap, sortMap, startIndex, pageSize);
	}
    
         
}

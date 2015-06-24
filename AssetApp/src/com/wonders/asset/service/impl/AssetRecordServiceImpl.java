package com.wonders.asset.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;
import org.dom4j.DocumentException;
import com.wonders.webservice.dto.AssetRecordDto;
import com.wonders.asset.base.service.impl.BaseServiceImpl;
import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.dao.AssetRecordDao;
import com.wonders.asset.model.AssetRecord;
import com.wonders.asset.service.AssetRecordService;
import com.wonders.asset.service.DataStoreService;
import com.wonders.webservice.dto.Response;

public class AssetRecordServiceImpl extends BaseServiceImpl<AssetRecord,String> implements AssetRecordService {

    private DataStoreService dataStoreService;
	private AssetRecordDao assetRecordDao;
	

    @Override
	public void importData() throws JAXBException, DocumentException {
		List<Response> responseList = dataStoreService.getDataFromStore(AssetRecordDto.class);
        List<String> assetNoList = new ArrayList<String>();
		for (Response response : responseList) {
            List assetRecordList = response.getList();
            for(int i=0;i<assetRecordList.size();i++){
                AssetRecordDto assetRecordDto = (AssetRecordDto)assetRecordList.get(i);
                assetNoList.add(assetRecordDto.getAssetNo());
            }

            assetRecordDao.save(response.getList());
            assetRecordDao.batchInsert();
            assetRecordDao.batchUpdate();
            assetRecordDao.clear();
			dataStoreService.confirmData(AssetRecordDto.class, response.getId());
		}


        assetRecordDao.updateAssetCodeType("2",assetNoList);//跟新為老編碼
	}
    
    public Pagination<AssetRecord> findRecordAndAsset(Map<String, String> filterMap,
			Map<String, String> sortMap, int startIndex, int pageSize){
    	return assetRecordDao.findRecordAndAsset(filterMap, sortMap, startIndex, pageSize);
    }

    public DataStoreService getDataStoreService() {  
        return dataStoreService;
    }

    public void setDataStoreService(DataStoreService dataStoreService) {
        this.dataStoreService = dataStoreService;
    }

    public AssetRecordDao getAssetRecordDao() {
        return assetRecordDao;
    }

    public void setAssetRecordDao(AssetRecordDao assetRecordDao) {
        this.assetRecordDao = assetRecordDao;
    }
}


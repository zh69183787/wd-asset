package com.wonders.asset.service.impl;

import java.io.File;
import java.util.List;

import com.wonders.asset.base.service.impl.BaseServiceImpl;
import com.wonders.asset.dao.AssetCheckinfoDao;
import com.wonders.asset.dao.AssetTaskDao;
import com.wonders.asset.model.Asset;
import com.wonders.asset.model.AssetCheckinfo;
import com.wonders.asset.model.AssetTask;
import com.wonders.asset.model.Attach;
import com.wonders.asset.service.AssetTaskService;
import com.wonders.asset.service.DamageAssetService;
import com.wonders.asset.service.DataStoreService;
import com.wonders.webservice.dto.AssetCheckinfoDto;
import com.wonders.webservice.dto.AssetTaskDto;
import com.wonders.webservice.dto.Response;
import org.dom4j.DocumentException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.xml.bind.JAXBException;


public class AssetTaskServiceImpl extends BaseServiceImpl<AssetTask,String> implements AssetTaskService {
	private AssetTaskDao assetTaskDao;
    private AssetCheckinfoDao assetCheckinfoDao;

    private DataStoreService dataStoreService;

	public AssetTaskDao getAssetTaskDao() {
		return assetTaskDao;
	}

	public void setAssetTaskDao(AssetTaskDao assetTaskDao) {
		this.assetTaskDao = assetTaskDao;
		setBaseDao(assetTaskDao);
	}

	@Override
	public int findSumOfTaskCheckByTaskId(String taskId) {
		
		return this.assetTaskDao.findSumOfTaskCheckByTaskId(taskId);
	}

	@Override
	public AssetTask findById(String id) {
		return assetTaskDao.findByPrimaryKey(id);
	}

	@Override
	public void saveAttach(Attach attach) {
		assetTaskDao.saveAttach(attach);
	}

	@Override
	public List<Attach> findAttachByGroupId(String groupId) {
		return assetTaskDao.findAttachByGroupId(groupId);
	}

	@Override
	public Attach findAttachById(Long id) {
		return assetTaskDao.findAttachById(id);
	}

	@Override
	public void deleteAttachById(Long id) {
		Attach attach = assetTaskDao.findAttachById(id);
		File file = new File(attach.getSavefilename());
		file.delete();
		assetTaskDao.deleteAttachById(id);
	}


    @Override
    public void importData() throws JAXBException, DocumentException {
        List<Response> responseList =  dataStoreService.getDataFromStore(AssetTaskDto.class);
        for (Response response : responseList) {
            for (Object obj : response.getList()) {
                AssetTaskDto assetTask = (AssetTaskDto)obj;
                List<AssetCheckinfoDto> assetCheckinfoList = assetTask.getAssetCheckinfoList();
                for (AssetCheckinfoDto assetCheckinfoDto : assetCheckinfoList) {
                    assetCheckinfoDto.setId(assetTask.getId());
                    assetCheckinfoDto.setUploadDate(assetTask.getUploadDate());
                }
                assetCheckinfoDao.save(assetCheckinfoList);
            }
            assetTaskDao.save(response.getList());
            assetTaskDao.batchInsert();
            assetTaskDao.batchUpdate();

            assetCheckinfoDao.batchInsert();
            assetCheckinfoDao.batchUpdate();

            assetTaskDao.clear();
            assetCheckinfoDao.clear();

            dataStoreService.confirmData(AssetTaskDto.class,response.getId());
        }


    }

    @Override
    public List<Object[]> findCheckInfoAndAssetByTaskId(String id,int startIndex,int pageSize) {
    	return assetTaskDao.findCheckInfoAndAssetByTaskId(id, startIndex, pageSize);
    }
    
    /**
     * 查询盘点任务完成率
     * @return
     */
    @Override
    public float getAssetTaskCompleteRate() {
        return assetTaskDao.getAssetCompleteRate();
    }

    public DataStoreService getDataStoreService() {
        return dataStoreService;
    }

    public void setDataStoreService(DataStoreService dataStoreService) {
        this.dataStoreService = dataStoreService;
    }

    public AssetCheckinfoDao getAssetCheckinfoDao() {
        return assetCheckinfoDao;
    }

    public void setAssetCheckinfoDao(AssetCheckinfoDao assetCheckinfoDao) {
        this.assetCheckinfoDao = assetCheckinfoDao;
    }

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        AssetTaskService service = (AssetTaskService)ac.getBean("assetTaskService");
//        DamageAssetService service =  (DamageAssetService)ac.getBean("damageAssetService");
        try {
            service.importData();
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

	@Override
	public List<Object[]> getAssetTaskByYear() {
		// TODO Auto-generated method stub
		return assetTaskDao.getAssetTaskByYear();
	}
}

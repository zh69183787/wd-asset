package com.wonders.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JsonConfig;

import org.apache.commons.lang.math.NumberUtils;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.base.web.AbstractBaseAction;
import com.wonders.asset.model.AssetRecord;
import com.wonders.asset.model.BorrowAsset;
import com.wonders.asset.model.StopAsset;
import com.wonders.asset.service.AssetRecordService;
import com.wonders.asset.service.AssetService;

public class AssetRecordAction extends AbstractBaseAction implements ModelDriven<AssetRecord> {

    private AssetRecord assetRecord = new AssetRecord();
    private AssetService assetService;
    private Integer pageSize;
    private Integer currentPageNo;
    private String callback;
    private AssetRecordService assetRecordService;

    public String upateAssetRecord() throws IOException {
        AssetRecord record = assetService.findAssetRecordById(assetRecord.getId());
        record.setMaintainContent(assetRecord.getMaintainContent());
        Map map = new HashMap();
        try {
            assetService.saveOrUpdate(record);
            map.put("errorCode", "1000");
            map.put("errorMsg", "更新成功");
        } catch (Exception e) {
            map.put("errorCode", "1001");
            map.put("errorMsg", "更新失败");
        }
        renderJsonp(map,callback);

        return Action.NONE;
    }

    public String assetRecords() throws IOException {
        Map map = new HashMap();
        Pagination p = assetService.findAssetRecordByProjectAppNo(assetRecord.getProjectAppNo(), getPageSize(), getCurrentPageNo());
        Map page = new HashMap();
        page.put("pageSize", getPageSize());
        page.put("currentPageNo", getCurrentPageNo());
        page.put("totalSize", p.getTotalCount());
        page.put("totalPageCount", p.getTotalPages());
        map.put("assetRecords", p.getResult());
        map.put("page", page);
        renderJsonp(map,callback);
        return Action.NONE;
    }

    @Override
    public AssetRecord getModel() {
        return assetRecord;
    }

    public AssetRecord getAssetRecord() {
        return assetRecord;
    }

    public void setAssetRecord(AssetRecord assetRecord) {
        this.assetRecord = assetRecord;
    }


    public AssetService getAssetService() {
        return assetService;
    }

    public void setAssetService(AssetService assetService) {
        this.assetService = assetService;
    }

    public Integer getPageSize() {
        return pageSize == null ? 10 : pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPageNo() {
        return currentPageNo == null ? 1 : currentPageNo;
    }

    public void setCurrentPageNo(Integer currentPageNo) {
        this.currentPageNo = currentPageNo;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }
    
    public String inquery() throws Exception{
		Map<String,String> filter = createFilterMap();
		int startIndex = NumberUtils.toInt(getRequestParameter("jtStartIndex"), 0);
		int pageSize = NumberUtils.toInt(getRequestParameter("jtPageSize"), 10);
		Map<String,String> sort = createSortMap();
		Pagination<AssetRecord> assetRecords = assetRecordService.findBy(filter,sort, startIndex, pageSize);
		JsonConfig jsonConfig = basicJsonCfg.copy();
		String[] excusions = new String[]{"id"};
		jsonConfig.registerPropertyExclusions(AssetRecord.class, excusions);
		renderJson(assetRecords.getResult(), assetRecords.getTotalCount(), jsonConfig);
		return Action.NONE;
	
	}
}

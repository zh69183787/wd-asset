package com.wonders.api;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.wonders.api.dto.AssetInfo;
import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.base.web.AbstractBaseAction;
import com.wonders.asset.model.*;
import com.wonders.asset.service.*;
import com.wonders.framework.util.ServiceProvider;
import org.apache.commons.lang.StringUtils;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HH on 2014/11/3.
 */
public class AssetInfoAction extends AbstractBaseAction implements ModelDriven<AssetInfo> {


    private AssetInfo assetInfo = new AssetInfo();
    private int currentPageNo;
    private int pageSize;
    private String type;
    private String parentId;
    private AssetService assetService;
    private String callback;


    /**
     * 对外接口
     * 根据页面传入的参数，查询出资产信息，并封装成json并返回给页面
     * 根据参数
     * 实体：assetInfo、页面数：currentPageNo、页面大小：pageSize
     * 将查询的结果封装成如下的json格式，返回给页面：
     * json格式：
     * {
     * "assetInfos" : {
     * "assetNo" : "01011010077210101001",
     * "assetName" : "小型机",
     * "beginUseDate" : "1999-02-12",
     * "useLife" : "5",
     * "mainTypeName" : "5",
     * "subTypeName" : "5",
     * "detailTypeName" : "5",
     * "lineName" : "5",
     * "stationName" : "5",
     * "overhaulRate" : "1/5",
     * "maintainNum" : 5,
     * "lastMaintainDate" : "2011-10-1 12:00:00:00",
     * "url":""
     * "url":"http://"
     * },
     * "page":{
     * "pageSize":10,
     * "currentPageNo":1，
     * "totalSize":100，
     * "totalPageCount":1，
     * <p/>
     * }
     * }
     *
     * @throws IOException
     */
    public void assetInfos() {

        try {
//            if("2".equals(assetInfo.getUrl()))
//                assetInfo.setUrl("http://10.1.48.16:7001");
//            else
//                assetInfo.setUrl("http://10.1.48.20");
            Pagination pagination = assetService.findAssetInfo(assetInfo, getCurrentPageNo(), getPageSize());
            Map<String, Object> map = new HashMap<String, Object>();
            Map<String, Object> pageMap = new HashMap<String, Object>();
            pageMap.put("pageSize", pagination.getPageSize());
            pageMap.put("currentPageNo", pagination.getPageNo());
            pageMap.put("totalSize", pagination.getTotalCount());
            pageMap.put("totalPageCount", pagination.getTotalPages());
            pageMap.put("pageSize", pagination.getPageSize());
            map.put("assetInfos", pagination.getResult());
            map.put("page", pageMap);
            renderJsonp(map,callback);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 对外接口
     * 根据父节点Id和类型，查询出线路id和线路名，并封装成json并返回给页面
     * 根据
     * 类型：type、父节点id：parentId
     * 查询出结果，并封装为如下json格式，并返回给页面：
     * json格式：
     * {
     * "dicts" : {
     * "id" : "01",
     * "name" : "轨道交通一号线"
     * }，
     * {
     * "id" : "01",
     * "name" : "轨道交通一号线"
     * }
     * }
     *
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public String dicts() throws IOException, DocumentException {

        List jsonList = new ArrayList();
        Map<String, Object> result = new HashMap<String, Object>();
        if ("1".equals(type)) {
            List<Line> lines = ServiceProvider.getService(LineService.class).findAllByPublish("1");
            for (Line line : lines) {
                Map<String, Object> dictMap = new HashMap<String, Object>();
                dictMap.put("id", line.getId());
                dictMap.put("name", line.getName());
                jsonList.add(dictMap);
            }
        } else if ("2".equals(type)) {
            List<AssetType> assetTypes = ServiceProvider.getService(AssetTypeService.class).findByParentIdWithPublish(parentId);
            for (AssetType assetType : assetTypes) {
                Map<String, Object> dictMap = new HashMap<String, Object>();
                dictMap.put("id", assetType.getId());
                dictMap.put("name", assetType.getName());
                jsonList.add(dictMap);
            }
        } else if ("3".equals(type)) {
            List<Department> departments = ServiceProvider.getService(DepartmentService.class).findByPublish();
            for (Department department : departments) {
                Map<String, Object> dictMap = new HashMap<String, Object>();
                dictMap.put("id", department.getId());
                dictMap.put("name", department.getName());
                jsonList.add(dictMap);
            }
        } else if ("5".equals(type) || "4".equals(type)) {
            List<Organization> organizations = ServiceProvider.getService(OrganizationService.class).findByPublish();
            for (Organization organization : organizations) {
                Map<String, Object> dictMap = new HashMap<String, Object>();
                dictMap.put("id", organization.getId());
                dictMap.put("name", organization.getName());
                jsonList.add(dictMap);
            }
        } else if ("6".equals(type)) {
            String[] assetsStatuses = new String[]{"使用", "停用", "报废", "待报废", "待报废", "封存"};
            for (int i = 0; i < assetsStatuses.length; i++) {
                Map<String, Object> dictMap = new HashMap<String, Object>();
                dictMap.put("id", i + 1);
                dictMap.put("name", assetsStatuses[i]);
                jsonList.add(dictMap);
            }
        } else if ("7".equals(type)) {
            List<Station> stations = ServiceProvider.getService(StationService.class).findByLineId(parentId);
            for (Station station : stations) {
                Map<String, Object> dictMap = new HashMap<String, Object>();
                dictMap.put("id", station.getId());
                dictMap.put("name", station.getName());
                jsonList.add(dictMap);
            }
        } else if (StringUtils.isBlank(type)) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("errorCode", 1001);
            map.put("errorMsg", "类型不能为空");
            renderJsonp(map, callback);
            return Action.NONE;
        } else {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("errorCode", 1002);
            map.put("errorMsg", "无效的参数");
            renderJsonp(map,callback);
            return Action.NONE;
        }
        result.put("dicts", jsonList);

        renderJsonp(result, callback);
        return Action.NONE;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public AssetInfo getAssetInfo() {
        return assetInfo;
    }

    public void setAssetInfo(AssetInfo assetInfo) {
        this.assetInfo = assetInfo;
    }

    public int getCurrentPageNo() {
        return currentPageNo == 0 ? currentPageNo = 1 : currentPageNo;
    }

    public void setCurrentPageNo(int currentPageNo) {
        this.currentPageNo = currentPageNo;
    }

    public int getPageSize() {
        return pageSize == 0 ? pageSize = 10 : pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public AssetInfo getModel() {
        return assetInfo;
    }

    public AssetService getAssetService() {
        return assetService;
    }

    public void setAssetService(AssetService assetService) {
        this.assetService = assetService;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }
}

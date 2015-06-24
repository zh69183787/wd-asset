package com.wonders.api.dto;

import java.util.Date;
import java.util.List;

/**
 * Created by HH on 2014/11/3.
 */
public class AssetInfo {
    private String id;
    private String assetNo;
    private String assetName;
    private Date beginUseDate;
    private String overhaulRate;
    private Integer maintainNum;
    private Date lastMaintainDate;
    private String mainTypeCodeId;
    private String subTypeCodeId;
    private String detailTypeCodeId;
    private String mainTypeName;
    private String lineName;
    private String stationName;
    private String userLife;
    private Double originalValue;
    private String url;
    private String lineCodeId;
    private String stationCodeId;
    private String useOrganizationId;
    private String ownerOrganizationId;
    private String departmentId;

    public String getUseOrganizationId() {
        return useOrganizationId;
    }

    public void setUseOrganizationId(String useOrganizationId) {
        this.useOrganizationId = useOrganizationId;
    }

    public String getOwnerOrganizationId() {
        return ownerOrganizationId;
    }

    public void setOwnerOrganizationId(String ownerOrganizationId) {
        this.ownerOrganizationId = ownerOrganizationId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMainTypeCodeId() {
        return mainTypeCodeId;
    }

    public void setMainTypeCodeId(String mainTypeCodeId) {
        this.mainTypeCodeId = mainTypeCodeId;
    }

    public String getSubTypeCodeId() {
        return subTypeCodeId;
    }

    public void setSubTypeCodeId(String subTypeCodeId) {
        this.subTypeCodeId = subTypeCodeId;
    }

    public String getDetailTypeCodeId() {
        return detailTypeCodeId;
    }

    public void setDetailTypeCodeId(String detailTypeCodeId) {
        this.detailTypeCodeId = detailTypeCodeId;
    }

    public Double getOriginalValue() {
        return originalValue;
    }

    public void setOriginalValue(Double originalValue) {
        this.originalValue = originalValue;
    }

    public String getUserLife() {
        return userLife;
    }

    public void setUserLife(String userLife) {
        this.userLife = userLife;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getLineCodeId() {
        return lineCodeId;
    }

    public void setLineCodeId(String lineCodeId) {
        this.lineCodeId = lineCodeId;
    }

    public String getStationCodeId() {
        return stationCodeId;
    }

    public void setStationCodeId(String stationCodeId) {
        this.stationCodeId = stationCodeId;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    @Override
    public String toString() {
        return "AssetInfo{" +
                "assetNo='" + assetNo + '\'' +
                ", assetName='" + assetName + '\'' +
                ", beginUseDate=" + beginUseDate +
                ", overhaulRate='" + overhaulRate + '\'' +
                ", maintainNum=" + maintainNum +
                ", lastMaintainDate=" + lastMaintainDate +
                ", mainTypeName='" + mainTypeName + '\'' +
                ", subTypeName='" + subTypeName + '\'' +
                ", detailTypeName='" + detailTypeName + '\'' +
                '}';
    }

    public void setMainTypeName(String mainTypeName) {
        this.mainTypeName = mainTypeName;
    }

    public void setSubTypeName(String subTypeName) {
        this.subTypeName = subTypeName;
    }

    public void setDetailTypeName(String detailTypeName) {
        this.detailTypeName = detailTypeName;
    }

    public String getMainTypeName() {

        return mainTypeName;
    }

    public String getSubTypeName() {
        return subTypeName;
    }

    public String getDetailTypeName() {
        return detailTypeName;
    }

    private String subTypeName;
    private String detailTypeName;

    public String getAssetName() {
        return assetName;
    }

    public Date getLastMaintainDate() {
        return lastMaintainDate;
    }

    public Integer getMaintainNum() {
        return maintainNum;
    }

    public String getOverhaulRate() {
        return overhaulRate;
    }

    public Date getBeginUseDate() {
        return beginUseDate;
    }

    public String getAssetNo() {
        return assetNo;
    }

    public void setAssetNo(String assetNo) {
        this.assetNo = assetNo;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public void setBeginUseDate(Date beginUseDate) {
        this.beginUseDate = beginUseDate;
    }

    public void setOverhaulRate(String overhaulRate) {
        this.overhaulRate = overhaulRate;
    }

    public void setMaintainNum(Integer maintainNum) {
        this.maintainNum = maintainNum;
    }

    public void setLastMaintainDate(Date lastMaintainDate) {
        this.lastMaintainDate = lastMaintainDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}

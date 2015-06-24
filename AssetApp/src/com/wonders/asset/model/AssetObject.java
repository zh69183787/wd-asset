package com.wonders.asset.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2015/1/6.
 */
@Entity
@Table(name = "ASSET_OBJECT")
public class AssetObject {
    private String assetNo;//资产编码
    private String assetDescription;//资产名称
    private String projectName;//项目名称
    private String projectNo;//项目编号
    private String projectContractNo;//项目合同编码
    private String mainType;//大类编码
    private String mainTypeName;//大类名称
    private String subType;//中类编码
    private String subTypeName;//中类名称
    private String detailType;//资产类型
    private String detailTypeName;//类型名称
    private String unitCode;//单位
    private String assetQty;//数量
    private String finGzxh;//规格型号
    private String productArea;//产地
    private String manufacturer;//生产厂商
    private String supplier;//供应商
    private String leaveFactory;//出厂日期
    private String supplyDate;//供应日期
    private String installSide;//安装地点
    private String ownershipUnit;//权属单位编号
    private String ownershipUnitName;//权属单位名称




    @Column(name = "资产编码")
    public String getAssetNo() {
        return assetNo;
    }

    public void setAssetNo(String assetNo) {
        this.assetNo = assetNo;
    }

    @Column(name = "资产名称")
    public String getAssetDescription() {
        return assetDescription;
    }

    public void setAssetDescription(String assetDescription) {
        this.assetDescription = assetDescription;
    }

    @Column(name = "项目名称")
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Column(name = "项目编号")
    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }
//
//    public String getProjectContractNo() {
//        return projectContractNo;
//    }
//
//    public void setProjectContractNo(String projectContractNo) {
//        this.projectContractNo = projectContractNo;
//    }
//
//    public String getMainType() {
//        return mainType;
//    }
//
//    public void setMainType(String mainType) {
//        this.mainType = mainType;
//    }
//
//    public String getMainTypeName() {
//        return mainTypeName;
//    }
//
//    public void setMainTypeName(String mainTypeName) {
//        this.mainTypeName = mainTypeName;
//    }
//
//    public String getSubType() {
//        return subType;
//    }
//
//    public void setSubType(String subType) {
//        this.subType = subType;
//    }
//
//    public String getSubTypeName() {
//        return subTypeName;
//    }
//
//    public void setSubTypeName(String subTypeName) {
//        this.subTypeName = subTypeName;
//    }
//
//    public String getDetailType() {
//        return detailType;
//    }
//
//    public void setDetailType(String detailType) {
//        this.detailType = detailType;
//    }
//
//    public String getDetailTypeName() {
//        return detailTypeName;
//    }
//
//    public void setDetailTypeName(String detailTypeName) {
//        this.detailTypeName = detailTypeName;
//    }
//
//    public String getUnitCode() {
//        return unitCode;
//    }
//
//    public void setUnitCode(String unitCode) {
//        this.unitCode = unitCode;
//    }
//
//    public String getAssetQty() {
//        return assetQty;
//    }
//
//    public void setAssetQty(String assetQty) {
//        this.assetQty = assetQty;
//    }
//
//    public String getFinGzxh() {
//        return finGzxh;
//    }
//
//    public void setFinGzxh(String finGzxh) {
//        this.finGzxh = finGzxh;
//    }
//
//    public String getProductArea() {
//        return productArea;
//    }
//
//    public void setProductArea(String productArea) {
//        this.productArea = productArea;
//    }
//
//    public String getManufacturer() {
//        return manufacturer;
//    }
//
//    public void setManufacturer(String manufacturer) {
//        this.manufacturer = manufacturer;
//    }
//
//    public String getSupplier() {
//        return supplier;
//    }
//
//    public void setSupplier(String supplier) {
//        this.supplier = supplier;
//    }
//
//    public String getLeaveFactory() {
//        return leaveFactory;
//    }
//
//    public void setLeaveFactory(String leaveFactory) {
//        this.leaveFactory = leaveFactory;
//    }
//
//    public String getSupplyDate() {
//        return supplyDate;
//    }
//
//    public void setSupplyDate(String supplyDate) {
//        this.supplyDate = supplyDate;
//    }
//
//    public String getInstallSide() {
//        return installSide;
//    }
//
//    public void setInstallSide(String installSide) {
//        this.installSide = installSide;
//    }
//
//    public String getOwnershipUnit() {
//        return ownershipUnit;
//    }
//
//    public void setOwnershipUnit(String ownershipUnit) {
//        this.ownershipUnit = ownershipUnit;
//    }
//
//    public String getOwnershipUnitName() {
//        return ownershipUnitName;
//    }
//
//    public void setOwnershipUnitName(String ownershipUnitName) {
//        this.ownershipUnitName = ownershipUnitName;
//    }


//            "       OWNERSHIP_PER                  权属负责人," +
//            "       IFSAPP.PERSON_INFO_API.Get_Name(OWNERSHIP_PER) 权属负责人名称," +
//            "       USE_UNIT                       使用单位编号," +
//            "       IFSAPP.USING_DEPT_API.Get_Dept_Name(USE_UNIT) 使用单位名称," +
//            "       USE_PER                        使用负责人," +
//            "       IFSAPP.PERSON_INFO_API.Get_Name(USE_PER) 使用负责人名称," +
//            "       MAINTAIN_DEPART                维护部门编号," +
//            "       IFSAPP.MAINTAIN_DEPT_API.Get_Dept_Name(MAINTAIN_DEPART) 维护部门名称," +
//            "       LOCATION_CODE                  资产位置编码," +
//            "       IFSAPP.ASSET_LOCATION_API.Get_Description(LOCATION_CODE) 资产位置," +
//            "       START_USE_DATE                 开始使用时间," +
//            "       END_USE_DATE                   停止使用时间," +
//            "       SCRAP_DATE                     批准报废时间," +
//            "       TRANSFER_DATE                  移交时间," +
//            "       USE_STATE                      当前使用状态," +
//            "       ASSETS_PICTURE                 资产图片名称," +
//            "       DESIGN_USE_LIFE                设计使用年限," +
//            "       EXPECT_USE_LIFE                预期使用寿命," +
//            "       WARRANTY_PERIOD                保修期至," +
//            "       OVERHAUL_RATE                  大修频次," +
//            "       FACTORY_PRICE                  出厂价," +
//            "       CONTRACT_PRICE                 合同价," +
//            "       ORIGINAL_VALUE                 原值," +
//            "decode(HAVE_LIST,'TRUE','有','无') 资料及清单," +
//            "       DEPRECIATE_METHOD              折旧方法," +
//            "       ACCU_DEPRECIATE                累计折旧," +
//            "       NET_ASSET_VALUE                资产净值," +
//            "       POST_POSITION                  铭牌张贴位置," +
//            "       EQUIP_LIST                     设备清单," +
//            "       COMPLETE_TRANS_ASSET_TYPE      竣工移交资产类型," +
//            "       OPERATE_PROJECT_ASSET          项目资产标示," +
//            "       OPERATE_PROJECT_ASSET_DATE     资产交付日期," +
//            "       OPERATE_PROJECT_ASSET_ACCOUNT  资产决算价," +
//            "       PROJECT_APP_DOC_NO             立项或可研批复文号," +
//            "       REMARK                         备注," +
//            "CREATE_TIME                    创建时间," +
//            "       MODIFY_TIME                    修改时间," +
//            "       substr(location_code,1,2)      线路编码," +
//            "       IFSAPP.ASSET_LOCATION_API.Get_Description(substr(location_code,1,2)) 线路名称," +
//            "       substr(location_code,3,2)      车站编码," +
//            "       IFSAPP.ASSET_LOCATION_API.Get_Description(location_code) 车站名称" +
}

package com.firebaselibrary.bean.search.dispatch;

import com.firebaselibrary.bean.search.PicPath;

import java.util.List;

/**
 * 应急物资
 */
public class GoodsListDispatchBean extends BaseDispatchBean {
    private String address;//地址
    private double latitude;//纬度
    private String usableNum;//可用数量
    private String telephone;//联系电话
    private String goodsSpec;//物资规格
    private String orgId;//所属部门
    private String orgName;//所属部门名称
    private String principal;//联系人
    private String unit;//单位
    private String goodsSourceCode;//物资来源
    private String goodsSourceName;//物资来源名称
    private String areaId;//行政区划
    private String areaName;//行政区划名称
    private String storeDept;//存储单位
    private String goodsStatus;//物资状态
    private String goodsStatusName;//物资状态名称
    private String resTypeCode;//物资类型编码
    private String resTypeCodeName;//物资类型编码名称
    private String createTime;//创建时间
    private String storeDeptId;//存储单位id
    private String storeNum;//存储数量
    private String goodsName;//物资名称
    private double longitude;//经度
    private String goodsDesc;//备注说明
    private String status;//状态
    private String goodsSubjection;//物资归属
    private String goodsSubjectionName;//物资归属名称
    private String picPath;//附件图片
    private List<PicPath> picPaths;//图片附件列表

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getUsableNum() {
        return usableNum;
    }

    public void setUsableNum(String usableNum) {
        this.usableNum = usableNum;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getGoodsSpec() {
        return goodsSpec;
    }

    public void setGoodsSpec(String goodsSpec) {
        this.goodsSpec = goodsSpec;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getGoodsSourceCode() {
        return goodsSourceCode;
    }

    public void setGoodsSourceCode(String goodsSourceCode) {
        this.goodsSourceCode = goodsSourceCode;
    }

    public String getGoodsSourceName() {
        return goodsSourceName;
    }

    public void setGoodsSourceName(String goodsSourceName) {
        this.goodsSourceName = goodsSourceName;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getStoreDept() {
        return storeDept;
    }

    public void setStoreDept(String storeDept) {
        this.storeDept = storeDept;
    }

    public String getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(String goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public String getGoodsStatusName() {
        return goodsStatusName;
    }

    public void setGoodsStatusName(String goodsStatusName) {
        this.goodsStatusName = goodsStatusName;
    }

    public String getResTypeCode() {
        return resTypeCode;
    }

    public void setResTypeCode(String resTypeCode) {
        this.resTypeCode = resTypeCode;
    }

    public String getResTypeCodeName() {
        return resTypeCodeName;
    }

    public void setResTypeCodeName(String resTypeCodeName) {
        this.resTypeCodeName = resTypeCodeName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getStoreDeptId() {
        return storeDeptId;
    }

    public void setStoreDeptId(String storeDeptId) {
        this.storeDeptId = storeDeptId;
    }

    public String getStoreNum() {
        return storeNum;
    }

    public void setStoreNum(String storeNum) {
        this.storeNum = storeNum;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGoodsSubjection() {
        return goodsSubjection;
    }

    public void setGoodsSubjection(String goodsSubjection) {
        this.goodsSubjection = goodsSubjection;
    }

    public String getGoodsSubjectionName() {
        return goodsSubjectionName;
    }

    public void setGoodsSubjectionName(String goodsSubjectionName) {
        this.goodsSubjectionName = goodsSubjectionName;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public List<PicPath> getPicPaths() {
        return picPaths;
    }

    public void setPicPaths(List<PicPath> picPaths) {
        this.picPaths = picPaths;
    }
}

package com.firebaselibrary.bean.search;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 救援队
 */
public class TeamListBean {
    private String teamName;//队伍名称
    private String equipmentDesc;//装备描述
    private String address;//地址
    private String recommandUnit;//推荐单位
    private String comments;//备注
    private String leader;//主管单位领导
    private String leaderTel;//主管单位领导电话
    private String headerTel;//救援队长电话
    private double latitude;//纬度
    private String teamCode;//队伍类型
    private String teamCodeName;//队伍类型名称
    private String updater;//更新人
    private String updateTime;//更新时间
    private String dutyStatus;//出勤状态
    private String dutyStatusName;//出勤状态名称
    private String expertise;//救援领域
    private String orgId;//归属部门
    private String orgName;//归属部门名称
    private String picPath;//附件图片
    private List<PicPath> picPaths;//附件图片列表
    private String areaId;//行政区划
    private String areaName;//行政区划名称
    private String dutyTel;//值班电话
    private String dutyFax;//值班传真
    private String creator;//创建人
    private String createTime;//创建时间
    private String resTypeCode;//资源类型编码
    private String header;//救援队长
    private String id;
    private String teamNum;//队伍人数
    private double longitude;//经度
    private String status;//状态
    private String dispatchStatusName;//派遣状态名称

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getEquipmentDesc() {
        return equipmentDesc;
    }

    public void setEquipmentDesc(String equipmentDesc) {
        this.equipmentDesc = equipmentDesc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRecommandUnit() {
        return recommandUnit;
    }

    public void setRecommandUnit(String recommandUnit) {
        this.recommandUnit = recommandUnit;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getLeaderTel() {
        return leaderTel;
    }

    public void setLeaderTel(String leaderTel) {
        this.leaderTel = leaderTel;
    }

    public String getHeaderTel() {
        return headerTel;
    }

    public void setHeaderTel(String headerTel) {
        this.headerTel = headerTel;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode;
    }

    public String getTeamCodeName() {
        return teamCodeName;
    }

    public void setTeamCodeName(String teamCodeName) {
        this.teamCodeName = teamCodeName;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getDutyStatus() {
        return dutyStatus;
    }

    public void setDutyStatus(String dutyStatus) {
        this.dutyStatus = dutyStatus;
    }

    public String getDutyStatusName() {
        return dutyStatusName;
    }

    public void setDutyStatusName(String dutyStatusName) {
        this.dutyStatusName = dutyStatusName;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
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

    public String getDutyTel() {
        return dutyTel;
    }

    public void setDutyTel(String dutyTel) {
        this.dutyTel = dutyTel;
    }

    public String getDutyFax() {
        return dutyFax;
    }

    public void setDutyFax(String dutyFax) {
        this.dutyFax = dutyFax;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getResTypeCode() {
        return resTypeCode;
    }

    public void setResTypeCode(String resTypeCode) {
        this.resTypeCode = resTypeCode;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeamNum() {
        return teamNum;
    }

    public void setTeamNum(String teamNum) {
        this.teamNum = teamNum;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDispatchStatusName() {
        return dispatchStatusName;
    }

    public void setDispatchStatusName(String dispatchStatusName) {
        this.dispatchStatusName = dispatchStatusName;
    }
}

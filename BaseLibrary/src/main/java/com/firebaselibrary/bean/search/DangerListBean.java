package com.firebaselibrary.bean.search;

import java.util.List;

import lombok.Data;

/**
 * 危险源
 */
@Data
public class DangerListBean {
    private String address;//地址
    private String buildTime;//承建时间
    private String influenceRange;//影响范围
    private String dangerStatus;//危险源状态
    private String dangerStatusName;//危险源状态名称
    private String stateDesc;//状态描述
    private String dangerLevel;//危险源等级
    private String dangerLevelName;//危险源等级名称
    private double latitude;//纬度
    private String dangerName;//危险源名称
    private String telephone;//联系电话
    private String updateTime;//更新时间
    private String orgId;//管理部门
    private String orgName;//管理部门名称
    private String disaster;//受灾形式
    private String principal;//联系人
    private String dangerCode;//危险源类型
    private String dangerCodeName;//危险源类型名称
    private String areaId;//行政区划
    private String areaName;//行政区划名称
    private String createTime;//创建时间
    private String id;
    private String department;//所属部门
    private String fax;//传真
    private double longitude;//经度
    private String status;//状态
    private String picPath;//图片
    private List<PicPath> picPaths;//图片列表
}

package com.firebaselibrary.bean.search;

import lombok.Data;

/**
 * 重点防护目标
 */
@Data
public class AimListBean {
    private String aimLevel;//防护等级
    private String aimLevelName;//防护等级中文
    private String address;//地址
    private String buildTime;//承建时间
    private String stateDesc;//状态描述
    private double latitude;//纬度
    private String telephone;//联系电话
    private String updateTime;//更新时间
    private String orgId;//所属单位
    private String orgName;//所属单位名称
    private String disaster;//受灾形式
    private String principal;//联系人
    private String areaId;//行政区划
    private String areaName;//行政区划名称
    private String createTime;//创建时间
    private String id;
    private String aimCode;//目标类型
    private String aimCodeName;//目标类型中文
    private String fax;//邮箱
    private String aimName;//目标名称
    private double longitude;//经度
    private String status;//状态
}

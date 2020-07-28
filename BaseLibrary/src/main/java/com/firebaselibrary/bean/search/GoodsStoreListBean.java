package com.firebaselibrary.bean.search;

import java.util.List;

import lombok.Data;

/**
 * 物资仓库
 */
@Data
public class GoodsStoreListBean {
    private String id;//id
    private String storeName;//仓库名称
    private String storeCode;//仓库类型
    private String storeCodeName;//仓库类型中文
    private double area;//面积（m2）
    private double storage;//库容（m3）
    private String orgId;//所属部门id
    private String orgName;//所属单位名称
    private String areaId;//行政区划id
    private String areaName;//行政区划名称
    private String areaCode;//行政区划编码
    private String linkMan;//联系人
    private String linkPhone;//联系人电话
    private String address;//地址
    private double longitude;//
    private double latitude;//
    private String status;//状态
    private String createTime;//创建时间
    private String updateTime;//更新时间
    private String linkTel;//联系人固话
    private String charge;//负责人
    private String chargeTel;//负责人固话
    private String chargePhone;//负责人电话
    private String dutyTel;//值班电话
    private String fax;//传真
    private String picPath;//附件随机值
    private List<PicPath> picPaths;//图片附件列表
}

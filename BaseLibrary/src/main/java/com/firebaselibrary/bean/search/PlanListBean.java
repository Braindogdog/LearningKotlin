package com.firebaselibrary.bean.search;

import java.util.List;

import lombok.Data;

@Data
public class PlanListBean {
    //预案id
    private String digitalId;
    private String orgName;
    private String preplanType;
    private String typeName;
    private String commanderLink;
    private String workDuty;
    private String orgId;
    private String typeCode;
    private String commander;
    private String preplanName;
    private String schemeId;
    private String schemeName;
    private String versionNo;
    private List<DePutyBean> deputyList;
}

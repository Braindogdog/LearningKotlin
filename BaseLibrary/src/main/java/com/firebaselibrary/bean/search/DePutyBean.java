package com.firebaselibrary.bean.search;

import java.util.List;

import lombok.Data;

@Data
public class DePutyBean {
    private String deputyId;
    private String digitalId;
    private String workDuty;
    private List<DeputyCommander> deputyCommander;
}

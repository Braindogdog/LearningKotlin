package com.firebaselibrary.bean.search;

import java.util.List;

import lombok.Data;

@Data
public class PlanResource {
    private List<TransportBean> transportList;
    private List<ShelterBean> shelterList;
    private List<GoodsBean> goodsList;
    private List<TeamBean> teamList;
    private List<ExpertBean> expertList;
}

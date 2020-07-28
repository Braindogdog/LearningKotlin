package com.firebaselibrary.bean.search.query;

import lombok.Data;

@Data
public class PlanQuery {
    private String name;
    private String type;
    private String eventType;
    private int page;
    private int pageSize;
}

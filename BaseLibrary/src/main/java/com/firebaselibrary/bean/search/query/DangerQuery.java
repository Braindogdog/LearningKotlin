package com.firebaselibrary.bean.search.query;

import lombok.Data;

/**
 * 危险源搜索条件
 */
@Data
public class DangerQuery {
    private String dangerName;
    private String dangerLevel;
    private String dangerCode;
    private String areaCode;
    private String areaId;
    private String id;
    private int page;
    private int pageSize;
}

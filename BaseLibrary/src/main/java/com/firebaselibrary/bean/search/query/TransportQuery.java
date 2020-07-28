package com.firebaselibrary.bean.search.query;

import lombok.Data;

/**
 * 运输资源管理查询条件
 */
@Data
public class TransportQuery {
    private String transportName;
    private String transportCode;
    private String areaCode;
    private String id;
    private int page;
    private int pageSize;
}

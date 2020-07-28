package com.firebaselibrary.bean.search.query;

import lombok.Data;

/**
 * 专家搜索条件
 */
@Data
public class ExpertQuery {
    private String expertName;
    private String expertCode;
    private String areaCode;
    private String id;
    private int page;
    private int pageSize;
}

package com.firebaselibrary.bean.search.query;

import lombok.Data;

/**
 * 重点防护目标搜索条件
 */
@Data
public class ShelterQuery {
    private String shelterName;
    private String shelterCode;
    private String areaId;
    private String areaCode;
    private String id;
    private int page;
    private int pageSize;
}

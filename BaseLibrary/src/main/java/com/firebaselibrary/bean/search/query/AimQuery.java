package com.firebaselibrary.bean.search.query;

import lombok.Data;

/**
 * 重点防护目标搜索条件
 */
@Data
public class AimQuery {
    private String aimName;
    private String aimLevel;
    private String aimCode;
    private String areaCode;
    private String id;
    private int page;
    private int pageSize;
}

package com.firebaselibrary.bean.search.query;

import lombok.Data;

/**
 * 物资企业搜索条件
 */
@Data
public class CompanyQuery {
    private String companyName;
    private String companyCode;
    private String areaCode;
    private String id;
    private int page;
    private int pageSize;
}

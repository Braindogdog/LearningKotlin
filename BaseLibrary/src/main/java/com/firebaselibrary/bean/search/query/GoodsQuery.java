package com.firebaselibrary.bean.search.query;

import lombok.Data;

/**
 * 物资企业搜索条件
 */
@Data
public class GoodsQuery {
    private String goodsName;
    private String resTypeCode;
    private String storeDeptId;
    private String id;
    private String areaCode;
    private int page;
    private int pageSize;
}

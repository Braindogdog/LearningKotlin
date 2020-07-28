package com.firebaselibrary.bean.search.query;

import lombok.Data;

/**
 * 物资仓库搜索条件
 */
@Data
public class GoodsStoreQuery {
    private String storeCode;
    private String storeName;
    private String areaId;
    private String areaCode;
    private String id;
    private int page;
    private int pageSize;
}

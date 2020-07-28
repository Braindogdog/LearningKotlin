package com.firebaselibrary.bean.search.query;

import lombok.Data;

/**
 * 医疗卫生资源查询条件
 */
@Data
public class MedicalQuery {
    private String medicalName;
    private String medicalCode;
    private String areaCode;
    private String id;
    private int page;
    private int pageSize;
}

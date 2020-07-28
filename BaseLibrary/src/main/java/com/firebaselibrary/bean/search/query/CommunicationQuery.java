package com.firebaselibrary.bean.search.query;

import lombok.Data;

/**
 * 通信保障查询
 */
@Data
public class CommunicationQuery {
    private String communicationName;
    private String areaCode;
    private String communicationCode;
    private String id;
    private int page;
    private int pageSize;
}

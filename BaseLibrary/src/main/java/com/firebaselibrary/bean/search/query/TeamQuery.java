package com.firebaselibrary.bean.search.query;

import lombok.Data;

/**
 * 救援队伍搜索条件
 */
@Data
public class TeamQuery {
    private String teamName;
    private String teamCode;
    private String areaCode;
    private String areaId;
    private String id;
    private int page;
    private int pageSize;
}

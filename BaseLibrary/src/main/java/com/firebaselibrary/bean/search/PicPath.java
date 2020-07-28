package com.firebaselibrary.bean.search;

import lombok.Data;

@Data
public class PicPath {
    private String id;
    private String path;
    private String newFileName;
    private String originalFileName;
    private String fkId;
    private String status;
    private String picFkId;
}

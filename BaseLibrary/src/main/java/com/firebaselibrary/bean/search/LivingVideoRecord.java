package com.firebaselibrary.bean.search;

/**
 * 直播录像
 *
 * @author zhangmh
 * @date 2020/5/28 17:56
 */
public class LivingVideoRecord extends LivingVideo{

    private String id;
    /**
     * 实际地址
     */
    private String path;

    private String url;

    private String recordName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRecordName() {
        return recordName;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

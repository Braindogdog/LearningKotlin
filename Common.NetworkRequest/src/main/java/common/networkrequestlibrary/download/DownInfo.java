package common.networkrequestlibrary.download;

import common.networkrequestlibrary.util.RetrofitHttpService;

/**
 * Created by chasen on 2018/3/14.
 */

public class DownInfo {
    /*存储位置*/
    private String savePath;
    /*文件总长度*/
    private long countLength;
    /*下载长度*/
    private long readLength;
    /*url*/
    private String url;

    public DownInfo() {
    }

    public DownInfo(String savePath, String url) {
        this.savePath = savePath;
        this.url = url;
    }

    public DownInfo(String savePath) {
        this.savePath = savePath;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public long getCountLength() {
        return countLength;
    }

    public void setCountLength(long countLength) {
        this.countLength = countLength;
    }

    public long getReadLength() {
        return readLength;
    }

    public void setReadLength(long readLength) {
        this.readLength = readLength;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
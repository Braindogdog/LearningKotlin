package com.firebaselibrary.bean;

/**
 * Created by chasen on 2018/4/3.
 * 首页菜单item数据bean
 */

public class HomePageMenuItemBean {
    private String title;
    private int imageResourceId;
    private int typeId;

    public HomePageMenuItemBean() {
    }

    public HomePageMenuItemBean(String title, int imageResourceId, int typeId) {

        this.title = title;
        this.imageResourceId = imageResourceId;
        this.typeId = typeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
}

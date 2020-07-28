package com.firebaselibrary.bean.search.dispatch;

import java.io.Serializable;

/**
 * 调派基础实体
 * Created by zhangmh on 2020/4/26.
 */
public class BaseDispatchBean implements Serializable {

    /**
     * 资源id
     */
    private String id;

    /**
     * 是否派遣
     */
    private boolean dispatched;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isDispatched() {
        return dispatched;
    }

    public void setDispatched(boolean dispatched) {
        this.dispatched = dispatched;
    }
}

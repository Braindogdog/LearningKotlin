package com.firebaselibrary.bean;

import java.io.Serializable;

/**
 * 字典表
 *
 * @author zhangmh
 * @date 2019/11/9 11:04
 */

public class Dictionary implements Serializable {


    private String id;
    /**
     * 编码
     */
    private String code;
    /**
     * 描述
     */
    private String name;
    /**
     * 上级
     */
    private String parent;
    /**
     * 类型
     */
    private String scope;
    /**
     * 排序，正序
     */
    private Integer sequence;
    /**
     * 废弃
     */
    private Boolean disused;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Boolean getDisused() {
        return disused;
    }

    public void setDisused(Boolean disused) {
        this.disused = disused;
    }





}

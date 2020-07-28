package com.firebaselibrary.bean;

import org.creation.common.models.BaseQuery;

/**
 * 字典查询
 *
 * @author zhangmh
 * @date 2019/11/11 10:58
 */

public class DictionaryQuery extends BaseQuery {

    /**
     * 代码
     */
    private String code;
    /**
     * 描述
     */
    private String des;
    /**
     * 类型
     */
    private String scope;
    /**
     * 父类
     */
    private String parentId;

    /**
     * 父类
     */
    private ParentSearchEnum parentSearchEnum;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public ParentSearchEnum getParentSearchEnum() {
        return parentSearchEnum;
    }

    public void setParentSearchEnum(ParentSearchEnum parentSearchEnum) {
        this.parentSearchEnum = parentSearchEnum;
    }





}

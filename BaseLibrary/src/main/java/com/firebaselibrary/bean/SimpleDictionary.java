package com.firebaselibrary.bean;

import java.io.Serializable;

/**
 * 字典表
 *
 * @author zhangmh
 * @date 2019/11/11 10:39
 */
public class SimpleDictionary implements Serializable {

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

    public SimpleDictionary() {
    }

    public SimpleDictionary(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * G 编码
     */
    public String getCode() {
        return this.code;
    }

    /**
     *  S 编码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * G 描述
     */
    public String getName() {
        return this.name;
    }

    /**
     *  S 描述
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * G 上级
     */
    public String getParent() {
        return this.parent;
    }

    /**
     *  S 上级
     */
    public void setParent(String parent) {
        this.parent = parent;
    }
}

package com.firebaselibrary.bean;

/**
 * 父id 在sql中如何处理
 *
 * @author zhangmh
 * @date 2019/8/4 15:17
 */
public enum ParentSearchEnum {
    /**
     * 相等（默认）
     */
    EQUALS,
    /**
     * 忽略
     */
    IGNORE,

    /**
     * 开始以
     */
    STARTWITH;


}

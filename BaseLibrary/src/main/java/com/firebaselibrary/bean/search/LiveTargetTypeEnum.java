package com.firebaselibrary.bean.search;

import common.baselibrary.baseutil.StringUtils;

/**
 * 直播
 *
 * @author zhangmh
 * @date 2020/5/29 22:07
 */
public enum LiveTargetTypeEnum {

    /**
     * 事件
     */
    EVENT,

    /**
     * 任务-通知
     */
    NOTIFICATION;


    public static LiveTargetTypeEnum getByName(String name){
        if(StringUtils.isBlank(name)){
            return null;
        }
        return LiveTargetTypeEnum.valueOf(name);
    }

}

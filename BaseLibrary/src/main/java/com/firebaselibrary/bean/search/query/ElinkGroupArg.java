package com.firebaselibrary.bean.search.query;

import org.creation.common.models.BaseApiQuery;

/**
 * 建组api
 *
 * @author zhangmh
 * @date 2020/5/28 13:20
 */
public class ElinkGroupArg extends BaseApiQuery {

    /**
     * 群组名称
     */
    private String groupName;
    /**
     * 群组公告
     */
    private String groupNotice;
    /**
     * 目标id
     * 值为事件id或者任务id
     */
    private String targetId;
    /**
     * 目标类型
     * 值为事件或者任务
     */
    private ElinkTargetTypeEnum type;
    /**
     * 登录人账号
     */
    private String account;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupNotice() {
        return groupNotice;
    }

    public void setGroupNotice(String groupNotice) {
        this.groupNotice = groupNotice;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public ElinkTargetTypeEnum getType() {
        return type;
    }

    public void setType(ElinkTargetTypeEnum type) {
        this.type = type;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}

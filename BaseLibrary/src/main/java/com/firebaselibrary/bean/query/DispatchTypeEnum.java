package com.firebaselibrary.bean.query;

/**
 * @author Zyq
 * @date 2020/3/6
 */
public enum DispatchTypeEnum {

    /**
     * 车辆
     */
    car("car"),
    /**
     * 物资企业
     */
    company("company"),
    /**
     * 通信保障
     */
    communication("communication"),
    /**
     * 运输资源
     */
    transport("transport"),
    /**
     * 医疗卫生
     */
    medical("medical"),
    /**
     * 专家
     */
    expert("expert"),
    /**
     * 队伍
     */
    team("team"),
    /**
     * 物资
     */
    goods("goods"),
    /**
     * 避难场所
     */
    shelter("shelter")
    ;

    private String value;

    private DispatchTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

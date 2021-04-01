package com.learn.util;

/**
 * Description: .
 *
 * @author : ys.
 * @date :
 */
public enum KieSessionType {
    OTHER(0, "other"),
    FIRST(1, "firstSession"),
    SECOND(2, "secondSession");

    private Integer code;

    private String desc;

    KieSessionType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer code() {
        return this.code;
    }

    public String desc() {
        return this.desc;
    }

    public static KieSessionType getVaule(String type) {
        for (KieSessionType value : KieSessionType.values()) {
            if (value.desc.equals(type)) {
                return value;
            }
        }
        return KieSessionType.OTHER;
    }
}

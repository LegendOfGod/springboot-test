package com.example.springboot.redislock;

/**
 * @author lqb
 * @date 2021/12/23 17:32
 */
public enum BusinessLockTypeEnum {
    /**
     * 业务1
     */
    ONE("business1", "test1"),
    /**
     * 业务2
     */
    TWO("business2", "test2"),
    ;

    private final String code;
    private final String desc;

    BusinessLockTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public String getUniqueKey(String key) {
        return code + ":" + key;
    }
}

package com.example.springboot.redislock;

import lombok.Data;

/**
 * @author lqb
 * @date 2021/12/23 17:39
 */
@Data
public class RedisLockDefinitionHolder {

    /**
     * getUniqueKey 加锁redis的key
     */
    private String businessKey;

    /**
     * 加锁时间秒
     */
    private Long lockTime;

    /**
     * 上次更新时间ms
     */
    private Long lastModifyTime;


    /**
     * 总共尝试次数
     */
    private int totalTryCount;

    /**
     * 当前尝试次数
     */
    private int currentTryCount;

    /**
     * 当前线程
     */
    private Thread thread;

    /**
     * 更新时间周期
     */
    private Long modifyPeriod;

    public RedisLockDefinitionHolder(String businessKey, Long lockTime, Long lastModifyTime, int totalTryCount, Thread thread) {
        this.businessKey = businessKey;
        this.lockTime = lockTime;
        this.lastModifyTime = lastModifyTime;
        this.totalTryCount = totalTryCount;
        this.thread = thread;
        this.modifyPeriod = lockTime*1000/3;
    }
}


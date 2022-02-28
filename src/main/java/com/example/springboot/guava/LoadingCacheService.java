package com.example.springboot.guava;

/**
 * @author lqb
 * @date 2022/2/28 14:37
 */
public interface LoadingCacheService {
    /**
     * 获取令牌
     * @return true or false
     */
    public boolean tryAcquire();
}

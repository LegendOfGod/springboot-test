package com.example.springboot.guava;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.stereotype.Service;

/**
 * @author lqb
 * @date 2022/2/28 14:38
 */
@Service
public class LoadingCacheServiceImpl implements LoadingCacheService{

    private static final RateLimiter RATE_LIMITER = RateLimiter.create(5);

    @Override
    public boolean tryAcquire() {
        return RATE_LIMITER.tryAcquire();
    }
}

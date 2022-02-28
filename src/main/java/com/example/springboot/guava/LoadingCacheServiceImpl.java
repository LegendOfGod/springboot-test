package com.example.springboot.guava;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.stereotype.Service;

import java.util.concurrent.Semaphore;

/**
 * @author lqb
 * @date 2022/2/28 14:38
 */
@Service
public class LoadingCacheServiceImpl implements LoadingCacheService{

    private static final RateLimiter RATE_LIMITER = RateLimiter.create(5);
    private static final Semaphore semaphore = new Semaphore(5,true);

    @Override
    public boolean tryAcquire() {
        return semaphore.tryAcquire();
    }

    @Override
    public void releaseAcquire() {
        semaphore.release();
    }
}

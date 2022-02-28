package com.example.springboot.guava;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author lqb
 * @date 2022/2/28 14:49
 */
@RestController
public class LoadingCacheController {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(1);

    @Resource
    private LoadingCacheService loadingCacheService;

    @GetMapping("/acquire")
    public String acquire() throws InterruptedException {
        System.out.println("等待时间"+ RATE_LIMITER.acquire()+"秒");
        System.out.println("当前时间"+sdf.format(new Date()));
        return "success";
    }
}

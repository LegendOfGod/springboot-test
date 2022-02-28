package com.example.springboot.guava;

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

    @Resource
    private LoadingCacheService loadingCacheService;

    @GetMapping("/acquire")
    public String acquire() throws InterruptedException {
        System.out.println("请求时间：" + sdf.format(new Date()));
        if (loadingCacheService.tryAcquire()){
            TimeUnit.SECONDS.sleep(1);
            return "success";
        }else {
            int a =  1/0;
            return "error";
        }
    }
}

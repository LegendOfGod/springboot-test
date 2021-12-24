package com.example.springboot.test;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author lqb
 * @date 2021/12/24 10:33
 */
public class threadPoolTest {

    public static void main(String[] args) {
        ScheduledExecutorService scheduler = new ScheduledThreadPoolExecutor(1,new ThreadFactoryBuilder().setNameFormat("延时").build());
        //scheduler.scheduleAtFixedRate(new MyThread("频率任务1"),0,5, TimeUnit.SECONDS);
        scheduler.scheduleWithFixedDelay(new MyThread("延迟任务2"),0,5,TimeUnit.SECONDS);
    }
}

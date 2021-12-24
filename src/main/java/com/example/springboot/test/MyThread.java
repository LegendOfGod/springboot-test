package com.example.springboot.test;

/**
 * @author lqb
 * @date 2021/12/24 10:55
 */
public class MyThread implements Runnable {

    private final String name;

    public MyThread(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + "执行任务：" + name + " 开始run " + System.currentTimeMillis());
            Thread.sleep(4000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "执行任务：" + name + " 结束run " + System.currentTimeMillis());
        }
    }
}

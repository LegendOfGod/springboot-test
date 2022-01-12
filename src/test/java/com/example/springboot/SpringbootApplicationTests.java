package com.example.springboot;

import com.example.springboot.async.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.Future;

@SpringBootTest
class SpringbootApplicationTests {

    @Autowired
    private Task task;

    @Test
    void contextLoads() throws Exception {
        Future<String> future1 = task.doTaskOne();
        Future<String> future2 = task.doTaskTwo();
        Future<String> future3 = task.doTaskThree();
        System.out.println(future1.get());
        System.out.println(future2.get());
        System.out.println(future3.get());
    }

}

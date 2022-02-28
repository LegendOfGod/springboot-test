package com.example.springboot;

import com.example.springboot.async.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Future;

@SpringBootTest
@EnableScheduling
class SpringbootApplicationTests {

    @Autowired
    private Task task;

    @Autowired
    private HttpSession httpSession;

    @Test
    void contextLoads() throws Exception {
        Future<String> future1 = task.doTaskOne();
        Future<String> future2 = task.doTaskTwo();
        Future<String> future3 = task.doTaskThree();
        System.out.println(future1.get());
        System.out.println(future2.get());
        System.out.println(future3.get());
    }

    @Test
    void testFileOperation() throws Exception {
        System.out.println(httpSession.getServletContext().getContextPath());
        httpSession.getServletContext().getRealPath("");
        System.out.println(System.getProperty("user.dir"));
        System.out.println(System.getProperty("user.home"));
        FileOutputStream fileOutputStream = new FileOutputStream("a.txt");
        fileOutputStream.write("a".getBytes(StandardCharsets.UTF_8));
        fileOutputStream.close();
    }



}

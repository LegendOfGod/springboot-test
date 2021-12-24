package com.example.springboot.anno;

import java.lang.annotation.*;

/**
 * @author lqb
 * @date 2021/12/20 17:11
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SwitchSource {
    String DEFAULT_DATASOURCE = "hisDataSource";
    String value() default DEFAULT_DATASOURCE;
}

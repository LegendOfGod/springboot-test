package com.example.springboot.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.Assert;

/**
 * @author lqb
 * @date 2021/12/20 14:50
 */
public class WindowsCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        Environment environment = context.getEnvironment();
        String osName = environment.getProperty("os.name");
        Assert.notNull(osName,"osName属性为空");
        if (osName.contains("Windows")){
            return true;
        }
        return false;
    }
}

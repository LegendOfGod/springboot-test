package com.example.springboot.config;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lqb
 * @date 2021/12/21 10:31
 */
@Target({ElementType.FIELD,ElementType.ANNOTATION_TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@NotNull(message = "不能为空")
@Constraint(validatedBy = EnumValuesConstraintValidator.class)
public @interface EnumValues {
    /**
     * 提示消息
     */
    String message() default "传入的值不在范围内";

    /**
     * 分组
     * @return
     */
    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    /**
     * 可以传入的值
     * @return
     */
    int[] values() default { };
}

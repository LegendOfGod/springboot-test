package com.example.springboot.config;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author lqb
 * @date 2021/12/21 10:33
 */
public class EnumValuesConstraintValidator implements ConstraintValidator<EnumValues, Integer> {

    private final Set<Integer> sets = new HashSet<>();

    @Override
    public void initialize(EnumValues constraintAnnotation) {
        sets.addAll(Arrays.stream(constraintAnnotation.values()).boxed().collect(Collectors.toList()));
    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        return sets.contains(integer);
    }
}

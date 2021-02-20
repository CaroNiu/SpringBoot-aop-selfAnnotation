package com.springboot.configstudy.annotation.logRecord;

import java.lang.annotation.*;

/**
 * 自定义注解
 * @author Nuri
 * @CreateTime 2021/2/20
 * @Describe
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogTrack {
    String value() default "logTrack";
}

package com.springboot.configstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class ConfigstudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigstudyApplication.class, args);
    }

}

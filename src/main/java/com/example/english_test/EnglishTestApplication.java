package com.example.english_test;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan("com.example.english_test.mapper")
@EnableAspectJAutoProxy(exposeProxy = true)
public class EnglishTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnglishTestApplication.class, args);
    }

}

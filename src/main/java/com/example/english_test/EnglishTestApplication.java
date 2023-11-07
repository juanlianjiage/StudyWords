package com.example.english_test;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.english_test.mapper")
public class EnglishTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnglishTestApplication.class, args);
    }

}

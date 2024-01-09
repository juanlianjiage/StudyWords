package com.example.english_test;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.english_test.dto.Result;
import com.example.english_test.dto.UserDTO;
import com.example.english_test.entity.Cet4Word;
import com.example.english_test.entity.Word;
import com.example.english_test.service.ICet4WordService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;


import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class EnglishTestApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedisTemplate<String,Cet4Word> redisTemplate1;
    @Autowired
    private ICet4WordService iCet4WordService;
    @Test
    void addtoredis() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        LambdaQueryWrapper<Cet4Word> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.last("limit 500");
        List<Cet4Word> list = iCet4WordService.list(queryWrapper);




        RedisOperations<String, Cet4Word> operations = redisTemplate1.opsForList().getOperations();

        for (int i = 0; i < list.size(); i++) {
            //String s = mapper.writeValueAsString(list.get(1));
            operations.opsForList().rightPush("Cet4:WordList",list.get(i));
        }

//        List<Cet4Word> cet4WordList = operations.opsForList().range("Cet4:WordList", 20, 39);
//        System.out.println(cet4WordList);

    }

}

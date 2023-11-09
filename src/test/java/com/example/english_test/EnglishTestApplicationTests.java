package com.example.english_test;

import com.example.english_test.dto.Result;
import com.example.english_test.dto.UserDTO;
import com.example.english_test.entity.Word;
import com.example.english_test.service.IWordService;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class EnglishTestApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource
    private IWordService iWordService;
    @Test
    void test1()
    {
        Result words_list = iWordService.get_Words_List();
        System.out.println(words_list);
    }
    @Test
    void time()
    {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.of(2023, 11, 10, 13, 02, 38);
        Duration between = Duration.between(end, now);

        System.out.println(between.toMinutes());
        System.out.println((between.toMinutes())/60.0);
        UserDTO userDTO = new UserDTO();
    }
}

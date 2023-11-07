package com.example.english_test;

import com.example.english_test.dto.Result;
import com.example.english_test.entity.Word;
import com.example.english_test.service.IWordService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
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
}

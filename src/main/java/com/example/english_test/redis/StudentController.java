package com.example.english_test.redis;

import com.example.english_test.dto.Result;
import com.example.english_test.dto.UserDTO;
import com.example.english_test.entity.Cet4Word;
import com.example.english_test.entity.WordsSelect;
import com.example.english_test.utils.USerHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import com.example.english_test.service.serviceimpl.Cet4WordServiceImpl;
@RestController
@Slf4j
@RequestMapping("/redis/student/")
public class StudentController {
    @Autowired
    private RedisTemplate<String, Cet4Word> redisTemplate;

    /*通过redis获取学习的单词
    * */
    @RequestMapping("/cet4Study")
    public Result getCet4Word(){
        UserDTO user = USerHolder.getUser();
        int studyed = user.getStudyed();
        int count = user.getCount();
        RedisOperations<String, Cet4Word> operations = redisTemplate.opsForList().getOperations();

        List<Cet4Word> cet4WordList = operations.opsForList().range("Cet4:WordList", Long.valueOf(studyed), studyed+count-1);
        ArrayList<WordsSelect> wordsSelects = Cet4WordServiceImpl.getWordsSelects(cet4WordList);
        return Result.ok(wordsSelects);

    }
}

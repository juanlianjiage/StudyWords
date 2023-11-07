package com.example.english_test.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.english_test.dto.Result;
import com.example.english_test.entity.TestWords;
import com.example.english_test.entity.Word;
import com.example.english_test.service.IWordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//TODO 管理员或者老师生成试卷，后续添加过滤器，学生不可访问

@Slf4j
@RestController
@RequestMapping("/manager")
public class ManagerVocabularyController {
    @Resource
    private IWordService iWordService;

    //TODO 管理员页面初始化，显示一些单词

    /*访问该页面，首先随机展示一些单词供选择，随机展示200个
    * */
    @PostMapping("/words_list")
    public Result showList()
    {
        /*生成去重列表
        * */
        int listSize=200;
        Random random = new Random();
        ArrayList<Integer> list = new ArrayList<>();
        int i=0;
        while(i<listSize) {
            int id = random.nextInt(12308);
            if(!list.contains(id))
            {
                list.add(id);
                i++;
            }
        }
        List<Word> words = iWordService.listByIds(list);
        ArrayList<TestWords> wordsSelects = new ArrayList<>();
        for (int j = 0; j < words.size(); j++) {
            TestWords testWords = new TestWords();
            Word word = words.get(j);
            testWords.setWordSpell(word.getWordSpell());
            testWords.setWordMeaning(word.getWordMeaning());
            testWords.setWordLevel(word.getWordLevel());
            wordsSelects.add(testWords);
        }
        return Result.ok(wordsSelects);
    }
    //TODO 管理员查询单词，获取输入的单词，进行查询，进行模糊匹配，返回前端
    @PostMapping
    public Result selectWord(@RequestParam String wordSpell)
    {
        LambdaQueryWrapper<Word> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.likeRight(Word::getWordSpell,wordSpell);

        List<Word> list = iWordService.list(queryWrapper);
        log.info(list.toString());
        return Result.ok(list);
    }


}

package com.example.english_test.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.english_test.dto.Result;
import com.example.english_test.dto.UnkonwWord;
import com.example.english_test.entity.UnknowWord;
import com.example.english_test.mapper.UnknowWordMapper;
import com.example.english_test.service.IUnknowWordService;
import com.example.english_test.utils.USerHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class UnknowWordServiceImpl extends ServiceImpl<UnknowWordMapper,UnknowWord> implements IUnknowWordService{

    //TODO 用户添加生词本，点击一次添加，进行一次 添加
    @Override
    @Transactional
    public Result saveUnkonwWords(UnkonwWord unknows) {
        //用户添加生词到生词本
            //获取用户id
            String studentId = USerHolder.getUser().getStudentId();
            Integer wordId = unknows.getWordId();
            UnknowWord unknowWord = new UnknowWord();
            unknowWord.setWordId(wordId);
            unknowWord.setStudentId(studentId);
            unknowWord.setAddTime(LocalDateTime.now());
            unknowWord.setBrowseTimes(0);
            //TODO 查询用户有没有添加过该单词
        LambdaQueryWrapper<UnknowWord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UnknowWord::getStudentId,studentId).eq(UnknowWord::getWordId,wordId);
        UnknowWord one = getOne(queryWrapper);
        if (one==null)
        {
            save(unknowWord);
            return Result.ok("添加成功！");
        }
        else {
            return Result.ok("已在生词本中！");
        }

    }



}

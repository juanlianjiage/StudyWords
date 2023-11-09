package com.example.english_test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.english_test.dto.Result;
import com.example.english_test.dto.UnkonwWord;
import com.example.english_test.entity.UnknowWord;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IUnknowWordService extends IService<UnknowWord> {
    Result saveUnkonwWords(UnkonwWord unknow);



}

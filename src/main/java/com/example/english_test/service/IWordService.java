package com.example.english_test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.english_test.dto.Result;
import com.example.english_test.entity.Word;

import java.util.List;

public interface IWordService extends IService<Word> {
    public Result get_Words_List();

}

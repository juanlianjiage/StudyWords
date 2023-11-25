package com.example.english_test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.english_test.dto.Result;
import com.example.english_test.dto.UserDTO;
import com.example.english_test.entity.Cet4Word;

public interface ICet4WordService extends IService<Cet4Word> {

    Result get_CET4Words_List();
}

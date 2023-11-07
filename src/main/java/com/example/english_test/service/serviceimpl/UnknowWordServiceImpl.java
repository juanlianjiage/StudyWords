package com.example.english_test.service.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.english_test.entity.UnknowWord;
import com.example.english_test.mapper.UnknowWordMapper;
import com.example.english_test.service.IUnknowWordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UnknowWordServiceImpl extends ServiceImpl<UnknowWordMapper,UnknowWord> implements IUnknowWordService{
}

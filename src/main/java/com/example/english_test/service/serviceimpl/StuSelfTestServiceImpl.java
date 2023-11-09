package com.example.english_test.service.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.english_test.entity.StuSelfTest;
import com.example.english_test.mapper.StuSelfTestMapper;
import com.example.english_test.service.IStuSelfTestService;
import org.springframework.stereotype.Service;

@Service
public class StuSelfTestServiceImpl extends ServiceImpl<StuSelfTestMapper, StuSelfTest> implements IStuSelfTestService{
}

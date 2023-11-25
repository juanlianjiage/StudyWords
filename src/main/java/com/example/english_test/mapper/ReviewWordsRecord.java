package com.example.english_test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.english_test.entity.StudentReview;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewWordsRecord extends BaseMapper<StudentReview> {
}

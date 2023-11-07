package com.example.english_test.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.english_test.entity.Word;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.jmx.export.annotation.ManagedAttribute;

import java.util.List;

@Mapper
public interface WordMapper extends BaseMapper<Word> {

}

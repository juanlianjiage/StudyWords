package com.example.english_test.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.english_test.dto.stuUnkownWord;
import com.example.english_test.entity.Word;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.jmx.export.annotation.ManagedAttribute;

import java.util.List;

@Mapper
public interface WordMapper extends BaseMapper<Word> {

    //学生生词表和单词表进行连接查询查询学生的生词
    @Select("select a.* from words a,(select word_id from student_words b where b.student_id=#{studentId}) c where a.word_id=c.word_id")
    List<stuUnkownWord> getAllstuUnkownWord(@Param("studentId") String studentId);
}

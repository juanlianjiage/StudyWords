package com.example.english_test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.english_test.entity.Student;
import com.example.english_test.entity.Word;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {
    @Update("update student set studyed=#{studyed} where student_id=#{studentId}")
    void updateStuStudyed(@Param("studyed") int studyed,@Param("studentId") String studentId);

}

package com.example.english_test.service.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.english_test.entity.Student;
import com.example.english_test.mapper.StudentMapper;
import com.example.english_test.service.IStudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {
}

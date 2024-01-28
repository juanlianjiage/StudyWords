package com.example.english_test.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("student")
public class Student {
    /*学生学号，主键
    * */
    @TableId(value = "student_id",type = IdType.AUTO)
    private String studentId;

    /*学生姓名
    * */
    private String name;
    private String password;
    private String level;
    private int studyed;
    private int count;
}

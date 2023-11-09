package com.example.english_test.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

//学生自测表
@Data
@TableName("self_test")
public class StuSelfTest {

    //id，主键，UUID生成id
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    //学生id，外键
    private String studentId;

    //单词等级
    private String level;

    //自测时间
    private LocalDateTime  time;

    //成绩
    private float score;

    //词汇量
    private Integer vocabulary;

    //备用字段
    @TableField(exist = false)
    private Integer data1;
    @TableField(exist = false)
    private Integer data2;
}

package com.example.english_test.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("student_review")
public class StudentReview {
    @TableId(value = "id",type = IdType.AUTO)
    private int id;
    private String studentId;
    private LocalDateTime addTime;
    private String level;
    private int beginId;
    private int endId;
    private int day;
}

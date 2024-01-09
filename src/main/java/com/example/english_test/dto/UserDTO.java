package com.example.english_test.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@TableName("student_visit")
public class UserDTO {
    private String studentId;
    private LocalDateTime loginTime;
    private String onlineTime;
    private LocalDateTime latestLoginTime;
    private int review;
    private String level;
    private int studyed;
}

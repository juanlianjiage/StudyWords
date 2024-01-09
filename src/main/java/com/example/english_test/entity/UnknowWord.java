package com.example.english_test.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/*学生生词表实体类
 * */
@Data
@TableName("student_words")
@Component
public class UnknowWord {
    /*单词Id
     * */

    private Integer wordId;

    /*学生id
     * */
    private String studentId;

    /*添加时间
     * */
    private LocalDateTime addTime;

    /*数字等级
     * */
    private Integer familiarity;

    /*浏览次数
     * */
    private Integer browseTimes;
}


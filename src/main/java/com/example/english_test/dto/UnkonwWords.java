package com.example.english_test.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("student_words")
public class UnkonwWords {
    /*单词Id
    * */
    private Integer WordId;

    /*学生Id
    * */
    private String studentId;
    /*
    * */
}

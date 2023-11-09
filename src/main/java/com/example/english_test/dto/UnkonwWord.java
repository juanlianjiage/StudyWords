package com.example.english_test.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("student_words")
public class UnkonwWord {
    /*单词Id
    * */
    private Integer wordId;

    /*学生Id
    * */
}

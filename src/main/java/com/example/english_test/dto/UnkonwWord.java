package com.example.english_test.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@TableName("student_words")
@Component
public class UnkonwWord {
    /*单词Id
    * */
    private Integer wordId;

    /*学生Id
    * */
}

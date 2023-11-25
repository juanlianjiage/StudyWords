package com.example.english_test.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("cet4")
public class Cet4Word {
    @TableId(value = "word_id",type = IdType.AUTO)
    private Integer wordId;

    /*单词拼写
     * */

    private String wordSpell;

    /*单词词意
     * */

    private String wordMeaning;

    /*音标
     * */
    private String wordPhonetic;

    /*例句
     * */
    private String wordExample;

    private String translation;
}

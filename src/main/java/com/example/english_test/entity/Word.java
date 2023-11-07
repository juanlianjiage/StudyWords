package com.example.english_test.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@TableName("words")

public class Word {

    /*单词主键
    * */
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

    /*单词等级
    * */
    private String wordLevel;

    /*单词词组
    * */
    private String wordPhrase;

    /*例句
    * */
    private String wordExample;

    /*备用字段1 2`   * */
    private int data1;
    private int data2;
}

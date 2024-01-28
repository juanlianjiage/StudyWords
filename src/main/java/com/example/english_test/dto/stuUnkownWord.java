package com.example.english_test.dto;

import lombok.Data;

@Data
public class stuUnkownWord {
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
    /*例句
     * */
    private String wordExample;

    private String translation;
}

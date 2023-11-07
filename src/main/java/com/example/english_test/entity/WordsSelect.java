package com.example.english_test.entity;

import java.util.Arrays;
import java.util.List;

public class WordsSelect {

    /*单词id
    * */
    public Integer wordId;
    /*单词拼写
    * */
    public String wordSpell;

    /*正确单词词意
     * */

    public String wordMeaning;


    /*三个错误词意一个正确词意
    * */
    public List<String> errorMeaning;

    public WordsSelect() {
    }

    public WordsSelect(String wordSpell, String wordMeaning, List<String> errorMeaning) {
        this.wordSpell = wordSpell;
        this.wordMeaning = wordMeaning;
        this.errorMeaning = errorMeaning;
    }

    public String getWordSpell() {
        return wordSpell;
    }

    public void setWordSpell(String wordSpell) {
        this.wordSpell = wordSpell;
    }

    public String getWordMeaning() {
        return wordMeaning;
    }

    public void setWordMeaning(String wordMeaning) {
        this.wordMeaning = wordMeaning;
    }

    public List<String> getErrorMeaning() {
        return errorMeaning;
    }

    public void setErrorMeaning(List<String> errorMeaning) {
        this.errorMeaning = errorMeaning;
    }

    public Integer getWordId() {
        return wordId;
    }

    public void setWordId(Integer wordId) {
        this.wordId = wordId;
    }

    @Override
    public String toString() {
        return "WordsSelect{" +
                "wordId=" + wordId +
                ", wordSpell='" + wordSpell + '\'' +
                ", wordMeaning='" + wordMeaning + '\'' +
                ", errorMeaning=" + errorMeaning +
                '}';
    }
}


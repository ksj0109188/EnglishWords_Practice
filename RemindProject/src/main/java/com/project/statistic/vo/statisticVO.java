package com.project.statistic.vo;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component("statisticVO")
public class statisticVO {

    private int wordId;
    private String word;
    private int wrongCount;
    private int correctCount;
    private Timestamp savedDate;

    public int getWordId() {
        return wordId;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getWrongCount() {
        return wrongCount;
    }

    public void setWrongCount(int wrongCount) {
        this.wrongCount = wrongCount;
    }

    public int getCorrectCount() {
        return correctCount;
    }

    public void setCorrectCount(int correctCount) {
        this.correctCount = correctCount;
    }

    public Timestamp getSavedDate() {
        return savedDate;
    }

    public void setSavedDate(Timestamp savedDate) {
        this.savedDate = savedDate;
    }
}

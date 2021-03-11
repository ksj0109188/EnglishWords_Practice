package com.project.word.vo;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component("wordVO")
public class wordVO {
    private int wordId;
    private String word;
    private String mean;
    private Timestamp savedDate;
    private int wordCount;
    private String user_id;
    private boolean isNewCard;
    private boolean isWrongCard;
    private boolean isReviewCard;
    private String dailyWord_dailyId;

    public boolean isWrongCard() {
        return isWrongCard;
    }

    public void setWrongCard(boolean wrongCard) {
        isWrongCard = wrongCard;
    }

    public boolean isReviewCard() {
        return isReviewCard;
    }

    public void setReviewCard(boolean reviewCard) {
        isReviewCard = reviewCard;
    }

    public int getCountRemain() {
        return countRemain;
    }

    public void setCountRemain(int countRemain) {
        this.countRemain = countRemain;
    }

    private int countRemain;

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

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public Timestamp getSavedDate() {
        return savedDate;
    }

    public void setSavedDate(Timestamp savedDate) {
        this.savedDate = savedDate;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public boolean isNewCard() {
        return isNewCard;
    }

    public void setNewCard(boolean newCard) {
        isNewCard = newCard;
    }

    public String getDailyWord_dailyId() {
        return dailyWord_dailyId;
    }

    public void setDailyWord_dailyId(String dailyWord_dailyId) {
        this.dailyWord_dailyId = dailyWord_dailyId;
    }
}

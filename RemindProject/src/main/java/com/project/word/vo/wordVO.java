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
    private String dailyWord_dailyId;
    private boolean detection;
    private int studyQuantity;
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

    public boolean isDetection() {
        return detection;
    }

    public void setDetection(boolean detection) {
        this.detection = detection;
    }


    public int getStudyQuantity() {
        return studyQuantity;
    }

    public void setStudyQuantity(int studyQuantity) {
        this.studyQuantity = studyQuantity;
    }
}

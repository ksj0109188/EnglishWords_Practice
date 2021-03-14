package com.project.dailyWord.vo;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component("dailyWordvo")
public class dailyWordVO {
    private int dailyId;
    private String word;
    private String mean;

    public int getDailyId() {
        return dailyId;
    }

    public void setDailyId(int dailyId) {
        this.dailyId = dailyId;
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

    private Timestamp savedDate;
}

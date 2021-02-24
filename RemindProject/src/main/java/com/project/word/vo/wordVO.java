package com.project.word.vo;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component("wordVO")
public class wordVO {
    private int wordId;
    private String word;
    private String mean;
    private Timestamp SaveDate;
    private int wordcount;
    private String user_id;

    public String getDailyWord_dailyId() {
        return dailyWord_dailyId;
    }

    public void setDailyWord_dailyId(String dailyWord_dailyId) {
        this.dailyWord_dailyId = dailyWord_dailyId;
    }

    private String dailyWord_dailyId;

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

    public Timestamp getSaveDate() {
        return SaveDate;
    }

    public void setSaveDate(Timestamp saveDate) {
        SaveDate = saveDate;
    }

    public int getwordcount() { return wordcount; }

    public void setwordcount(int wordcount) {
        this.wordcount = wordcount;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}

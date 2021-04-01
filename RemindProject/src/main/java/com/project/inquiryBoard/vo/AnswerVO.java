package com.project.inquiryBoard.vo;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component("AnswerVO")
public class AnswerVO {
    private int boardId;
    private String content;
    private Timestamp savedDate;
    private String userId;
    private int AnswerId;

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getSavedDate() {
        return savedDate;
    }

    public void setSavedDate(Timestamp savedDate) {
        this.savedDate = savedDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getAnswerId() {
        return AnswerId;
    }

    public void setAnswerId(int answerId) {
        AnswerId = answerId;
    }
}

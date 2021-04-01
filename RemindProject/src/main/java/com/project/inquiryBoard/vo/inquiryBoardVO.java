package com.project.inquiryBoard.vo;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component("inquiryBoardVO")
public class inquiryBoardVO {
    private int boardId;
    private String title;
    private String content;
    private Timestamp writeDate;
    private String userId;

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(Timestamp writeDate) {
        this.writeDate = writeDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

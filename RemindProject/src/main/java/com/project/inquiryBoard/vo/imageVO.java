package com.project.inquiryBoard.vo;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component("imageVO")
public class imageVO {
    private int imageFileNo;
    private String imageFileName;
    private Timestamp savedDate;
    private int boardId;


    public int getImageFileNo() {
        return imageFileNo;
    }

    public void setImageFileNo(int imageFileNo) {
        this.imageFileNo = imageFileNo;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public Timestamp getSavedDate() {
        return savedDate;
    }

    public void setSavedDate(Timestamp savedDate) {
        this.savedDate = savedDate;
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }
}

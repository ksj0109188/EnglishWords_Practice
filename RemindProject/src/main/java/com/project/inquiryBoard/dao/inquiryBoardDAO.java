package com.project.inquiryBoard.dao;

import com.project.inquiryBoard.vo.AnswerVO;
import com.project.inquiryBoard.vo.imageVO;
import com.project.inquiryBoard.vo.inquiryBoardVO;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;

public interface inquiryBoardDAO {
    List<inquiryBoardVO> selectInquiryBoard(Map inquiryBoardMap) throws DataAccessException;

    inquiryBoardVO selectBoardDetail(Map boardMap) throws DataAccessException;

    List<AnswerVO> selectBoardAnswer(Map boardMap) throws DataAccessException;

    int maxBoardId() throws DataAccessException;

    void writeBoard(Map boardMap) throws DataAccessException;

    void writeImageBoard(List<imageVO> imageVOList) throws DataAccessException;

    int maxBoardImageFileId() throws DataAccessException;

    List<imageVO> selectBoardImage(Map boardMap) throws DataAccessException;

    void writeAnswer(Map boardMap) throws DataAccessException;

    int maxAnswerId() throws DataAccessException;
}

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

    void updateBoard(Map boardMap) throws DataAccessException;

    void updateImageBoard(List<imageVO> imageVOList) throws DataAccessException;

    void deleteBoard(Map boardMap) throws DataAccessException;

    void modifyAnswer(Map answerMap) throws DataAccessException;

    void deleteAnswer(Map answerMap) throws DataAccessException;

    int selectTotalCountBoard(Map boardMap) throws DataAccessException;
}

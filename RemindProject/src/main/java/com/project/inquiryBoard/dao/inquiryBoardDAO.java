package com.project.inquiryBoard.dao;

import com.project.inquiryBoard.vo.AnswerVO;
import com.project.inquiryBoard.vo.imageVO;
import com.project.inquiryBoard.vo.inquiryBoardVO;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;

public interface inquiryBoardDAO {
    List<inquiryBoardVO> selectInquiryBoard(Map<String, Object> inquiryBoardMap) throws DataAccessException;

    inquiryBoardVO selectBoardDetail(Map<String, Object> boardMap) throws DataAccessException;

    List<AnswerVO> selectBoardAnswer(Map<String, Object> boardMap) throws DataAccessException;

    int maxBoardId() throws DataAccessException;

    void writeBoard(Map<String, Object> boardMap) throws DataAccessException;

    void writeImageBoard(List<imageVO> imageVOList) throws DataAccessException;

    int maxBoardImageFileId() throws DataAccessException;

    List<imageVO> selectBoardImage(Map<String, Object> boardMap) throws DataAccessException;

    void writeAnswer(Map<String, Object> boardMap) throws DataAccessException;

    int maxAnswerId() throws DataAccessException;

    void updateBoard(Map<String, Object> boardMap) throws DataAccessException;

    void updateImageBoard(List<imageVO> imageVOList) throws DataAccessException;

    void deleteBoard(Map<String, Object> boardMap) throws DataAccessException;

    void modifyAnswer(Map<String, Object> answerMap) throws DataAccessException;

    void deleteAnswer(Map<String, Object> answerMap) throws DataAccessException;

    int selectTotalCountBoard(Map<String, Object> boardMap) throws DataAccessException;
}

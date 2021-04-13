package com.project.inquiryBoard.service;

import com.project.inquiryBoard.vo.AnswerVO;
import com.project.inquiryBoard.vo.imageVO;
import com.project.inquiryBoard.vo.inquiryBoardVO;

import java.util.List;
import java.util.Map;

public interface inquiryBoardService {
    List<inquiryBoardVO> selectInquiryBoard(Map<String,Object> inquiryBoardMap) throws Exception;

    inquiryBoardVO selectBoardDetail(Map<String,Object> boardMap) throws Exception;

    List<AnswerVO> selectBoardAnswer(Map<String,Object> boardMap) throws Exception;

    int writeBoard(Map<String,Object> boardMap)throws Exception;

    void writeImageBoard(Map<String,Object> boardMap) throws Exception;

    List<imageVO> selectBoardImage(Map<String,Object> boardMap) throws Exception;

    void writeAnswer(Map<String,Object> boardMap) throws Exception;

    void updateBoard(Map<String,Object> boardMap) throws Exception;

    void updateImageBoard(Map<String,Object> boardMap) throws Exception;

    void deleteBoard(Map<String,Object> boardMap) throws Exception;

    void modifyAnswer(Map<String,Object> answerMap) throws Exception;

    void deleteAnswer(Map<String,Object> answerMap) throws Exception;

    int selectTotalCountBoard(Map<String,Object> boardMap) throws Exception;
}

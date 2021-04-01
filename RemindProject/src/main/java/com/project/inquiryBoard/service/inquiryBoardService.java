package com.project.inquiryBoard.service;

import com.project.inquiryBoard.vo.AnswerVO;
import com.project.inquiryBoard.vo.imageVO;
import com.project.inquiryBoard.vo.inquiryBoardVO;

import java.util.List;
import java.util.Map;

public interface inquiryBoardService {
    List<inquiryBoardVO> selectInquiryBoard(Map inquiryBoardMap) throws Exception;

    inquiryBoardVO selectBoardDetail(Map boardMap) throws Exception;

    List<AnswerVO> selectBoardAnswer(Map boardMap) throws Exception;

    int writeBoard(Map boardMap)throws Exception;

    void writeImageBoard(Map boardMap) throws Exception;

    List<imageVO> selectBoardImage(Map boardMap) throws Exception;

    void writeAnswer(Map boardMap) throws Exception;

    void updateBoard(Map boardMap) throws Exception;

    void updateImageBoard(Map boardMap) throws Exception;

    void deleteBoard(Map boardMap) throws Exception;

    void modifyAnswer(Map answerMap) throws Exception;

    void deleteAnswer(Map answerMap) throws Exception;

    int selectTotalCountBoard(Map boardMap) throws Exception;
}

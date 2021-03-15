package com.project.inquiryBoard.service;

import com.project.inquiryBoard.vo.AnswerVO;
import com.project.inquiryBoard.vo.inquiryBoardVO;

import java.util.List;
import java.util.Map;

public interface inquiryBoardService {
    List<inquiryBoardVO> selectInquiryBoard(Map inquiryBoardMap) throws Exception;

    inquiryBoardVO selectBoardDetail(Map boardMap) throws Exception;

    List<AnswerVO> selectBoardAnswer(Map boardMap) throws Exception;
}

package com.project.inquiryBoard.service;

import com.project.inquiryBoard.dao.inquiryBoardDAO;
import com.project.inquiryBoard.vo.AnswerVO;
import com.project.inquiryBoard.vo.inquiryBoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("inquiryBoardService")
public class inquiryBoardServiceImpl implements inquiryBoardService{

    @Autowired
    inquiryBoardDAO inquiryBoardDAO;

    @Override
    public List<inquiryBoardVO> selectInquiryBoard(Map inquiryBoardMap) {
        return inquiryBoardDAO.selectInquiryBoard(inquiryBoardMap);
    }

    @Override
    public inquiryBoardVO selectBoardDetail(Map boardMap) throws Exception {
        return inquiryBoardDAO.selectBoardDetail(boardMap);
    }

    @Override
    public List<AnswerVO> selectBoardAnswer(Map boardMap) throws Exception {
        return inquiryBoardDAO.selectBoardAnswer(boardMap);
    }

    @Override
    public void writeBoard(Map boardMap) {

    }

}

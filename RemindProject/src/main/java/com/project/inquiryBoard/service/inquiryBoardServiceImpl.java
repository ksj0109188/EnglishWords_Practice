package com.project.inquiryBoard.service;

import com.project.inquiryBoard.dao.inquiryBoardDAO;
import com.project.inquiryBoard.vo.AnswerVO;
import com.project.inquiryBoard.vo.imageVO;
import com.project.inquiryBoard.vo.inquiryBoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional(propagation = Propagation.REQUIRED)
@Service("inquiryBoardService")
public class inquiryBoardServiceImpl implements inquiryBoardService {

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
    public int writeBoard(Map boardMap) throws Exception {
        int maxBoardId = inquiryBoardDAO.maxBoardId();
        boardMap.put("boardId", maxBoardId);
        inquiryBoardDAO.writeBoard(boardMap);
        return maxBoardId;
    }

    @Override
    public void writeImageBoard(Map boardMap) throws Exception {
        int maxBoardImageFileId = inquiryBoardDAO.maxBoardImageFileId();
        List<imageVO> fileList = (List<imageVO>) boardMap.get("imageFileList");
        int boardId = (int) boardMap.get("boardId");
        for (imageVO imageVO : fileList) {
            imageVO.setImageFileId(maxBoardImageFileId++);
            imageVO.setBoardId(boardId);
        }
        inquiryBoardDAO.writeImageBoard(fileList);
    }

    @Override
    public List<imageVO> selectBoardImage(Map boardMap) throws Exception {
        return inquiryBoardDAO.selectBoardImage(boardMap);
    }
}

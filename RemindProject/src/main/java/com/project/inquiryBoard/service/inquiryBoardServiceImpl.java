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
    public List<inquiryBoardVO> selectInquiryBoard(Map<String,Object> inquiryBoardMap) {
        return inquiryBoardDAO.selectInquiryBoard(inquiryBoardMap);
    }

    @Override
    public inquiryBoardVO selectBoardDetail(Map<String,Object> boardMap) throws Exception {
        return inquiryBoardDAO.selectBoardDetail(boardMap);
    }

    @Override
    public List<AnswerVO> selectBoardAnswer(Map<String,Object> boardMap) throws Exception {
        return inquiryBoardDAO.selectBoardAnswer(boardMap);
    }

    @Override
    public int writeBoard(Map<String,Object> boardMap) throws Exception {
        int maxBoardId = inquiryBoardDAO.maxBoardId();
        boardMap.put("boardId", maxBoardId);
        inquiryBoardDAO.writeBoard(boardMap);
        return maxBoardId;
    }

    @Override
    public void writeImageBoard(Map<String,Object> boardMap) throws Exception {
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
    public List<imageVO> selectBoardImage(Map<String,Object> boardMap) throws Exception {
        return inquiryBoardDAO.selectBoardImage(boardMap);
    }

    @Override
    public void writeAnswer(Map<String,Object> boardMap) throws Exception {
        int AnswerId = inquiryBoardDAO.maxAnswerId();
        boardMap.put("AnswerId", AnswerId);
        inquiryBoardDAO.writeAnswer(boardMap);
    }

    @Override
    public void updateBoard(Map<String,Object> boardMap) throws Exception {
        inquiryBoardDAO.updateBoard(boardMap);
    }

    @Override
    public void updateImageBoard(Map<String,Object> boardMap) throws Exception {
        List<imageVO> fileList = (List<imageVO>) boardMap.get("imageFileList");
        List<imageVO> exist = inquiryBoardDAO.selectBoardImage(boardMap);
        if (exist != null && exist.size() != 0) {
            for (imageVO existImage : exist) {
                for (imageVO changeImage : fileList) {
                    existImage.setImageFileName(changeImage.getImageFileName());
                }
            }
            inquiryBoardDAO.updateImageBoard(exist);
        } else {
            int maxBoardImageFileId = inquiryBoardDAO.maxBoardImageFileId();
            for (imageVO Image : fileList) {
                Image.setImageFileId(maxBoardImageFileId);
            }
            inquiryBoardDAO.writeImageBoard(fileList);
        }
    }

    @Override
    public void deleteBoard(Map<String,Object> boardMap) throws Exception {
        inquiryBoardDAO.deleteBoard(boardMap);
    }

    @Override
    public void modifyAnswer(Map<String,Object> answerMap) throws Exception {
        inquiryBoardDAO.modifyAnswer(answerMap);
    }

    @Override
    public void deleteAnswer(Map<String,Object> answerMap) throws Exception {
        inquiryBoardDAO.deleteAnswer(answerMap);
    }

    @Override
    public int selectTotalCountBoard(Map<String,Object> boardMap) throws Exception {
        return inquiryBoardDAO.selectTotalCountBoard(boardMap);
    }
}
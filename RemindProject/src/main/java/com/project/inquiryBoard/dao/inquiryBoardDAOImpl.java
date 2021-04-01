package com.project.inquiryBoard.dao;

import com.project.inquiryBoard.vo.AnswerVO;
import com.project.inquiryBoard.vo.imageVO;
import com.project.inquiryBoard.vo.inquiryBoardVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("inquiryBoardDAO")
public class inquiryBoardDAOImpl implements inquiryBoardDAO {
    @Autowired
    SqlSession session;
    @Override
    public List<inquiryBoardVO> selectInquiryBoard(Map inquiryBoardMap) throws DataAccessException {
        return session.selectList("mapper.inquiryBoard.selectInquiryBoard",inquiryBoardMap);
    }

    @Override
    public inquiryBoardVO selectBoardDetail(Map boardMap) throws DataAccessException {
        return session.selectOne("mapper.inquiryBoard.selectBoardDetail",boardMap);
    }

    @Override
    public List<AnswerVO> selectBoardAnswer(Map boardMap) throws DataAccessException {
        return session.selectList("mapper.inquiryBoard.selectBoardAnswer",boardMap);
    }

    @Override
    public int maxBoardId() throws DataAccessException {
        return session.selectOne("mapper.inquiryBoard.maxBoardId");
    }

    @Override
    public void writeBoard(Map boardMap) throws DataAccessException {
        session.insert("mapper.inquiryBoard.writeBoard",boardMap);
    }

    @Override
    public void writeImageBoard(List<imageVO> imageVOList) throws DataAccessException {
        session.insert("mapper.inquiryBoard.writeImageBoard", imageVOList);
    }

    @Override
    public int maxBoardImageFileId() throws DataAccessException {
        return session.selectOne("mapper.inquiryBoard.maxBoardImageFileId");
    }

    @Override
    public List<imageVO> selectBoardImage(Map boardMap) throws DataAccessException {
        return session.selectList("mapper.inquiryBoard.selectBoardImage",boardMap);
    }

    @Override
    public void writeAnswer(Map boardMap) throws DataAccessException {
        session.insert("mapper.inquiryBoard.writeAnswer",boardMap);
    }

    @Override
    public int maxAnswerId() throws DataAccessException {
        return session.selectOne("mapper.inquiryBoard.maxAnswerId");
    }

    @Override
    public void updateBoard(Map boardMap) throws DataAccessException {
        session.update("mapper.inquiryBoard.updateBoard",boardMap);
    }

    @Override
    public void updateImageBoard(List<imageVO> imageVOList) throws DataAccessException {
        session.update("mapper.inquiryBoard.updateImageBoard", imageVOList);
    }

    @Override
    public void deleteBoard(Map boardMap) throws DataAccessException {
        session.delete("mapper.inquiryBoard.deleteBoard",boardMap);
    }

    @Override
    public void modifyAnswer(Map answerMap) throws DataAccessException {
        session.update("mapper.inquiryBoard.modifyAnswer",answerMap);
    }

    @Override
    public void deleteAnswer(Map answerMap) throws DataAccessException {
        session.delete("mapper.inquiryBoard.deleteAnswer",answerMap);
    }

    @Override
    public int selectTotalCountBoard(Map boardMap) throws DataAccessException {
        return session.selectOne("mapper.inquiryBoard.selectTotalCountBoard",boardMap);
    }
}

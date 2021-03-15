package com.project.inquiryBoard.dao;

import com.project.inquiryBoard.vo.AnswerVO;
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
}

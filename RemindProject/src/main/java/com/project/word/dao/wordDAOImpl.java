package com.project.word.dao;

import com.project.word.vo.wordVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository("wordDao")
public class wordDAOImpl implements wordDAO {
    @Autowired
    SqlSession session;


    @Override
    public void addWord(wordVO wordvo) throws DataAccessException {
        session.insert("mapper.word.addword", wordvo);
    }

    @Override
    public int maxWordId(wordVO wordvo) throws DataAccessException {
        return session.selectOne("mapper.word.maxWordId", wordvo);
    }

    @Override
    public wordVO selectReviewCard(wordVO wordvo) throws DataAccessException {
        return session.selectOne("mapper.word.selectReviewCard", wordvo);
    }

    @Override
    public wordVO selectNewCard(wordVO wordvo)throws  DataAccessException {
        return session.selectOne("mapper.word.selectNewCard", wordvo);
    }

    @Override
    public void updateReviewCard(wordVO wordvo) throws DataAccessException {
        session.update("mapper.word.updateReviewCard",wordvo);
    }

    @Override
    public void updateAppropriate(wordVO wordvo) throws DataAccessException {
        session.update("mapper.word.updateAppropriate",wordvo);
    }

    @Override
    public wordVO selectReviewRemainCard(wordVO wordvo) throws DataAccessException {
        return session.selectOne("mapper.word.selectReviewRemainCard",wordvo);
    }
}

package com.project.word.dao;

import com.project.word.vo.wordVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository("wordDao")
public class wordDAOImpl implements wordDAO {
    @Autowired
    SqlSession session;

    @Override
    public void addWord(wordVO wordvo) throws DataAccessException {
        session.insert("mapper.word.addword", wordvo);
    }

    @Override
    public void addDailyWord(wordVO wordvo) throws DataAccessException {
        session.insert("mapper.word.addDailyWord",wordvo);
    }

    @Override
    public int countWrongReviewCard(Map wordMap) throws DataAccessException {
        return session.selectOne("mapper.word.countWrongReviewCard", wordMap);
    }

    @Override
    public int countReviewCard(Map wordMap) throws DataAccessException {
        return session.selectOne("mapper.word.countReviewCard",wordMap);
    }

    @Override
    public int countWrongNewCard(Map wordMap) throws DataAccessException {
        return session.selectOne("mapper.word.countWrongNewCard",wordMap);
    }

    @Override
    public int countNewCard(Map wordMap) throws DataAccessException {
        return session.selectOne("mapper.word.countNewCard",wordMap);
    }

    @Override
    public int maxWordId(wordVO wordvo) throws DataAccessException {
        return session.selectOne("mapper.word.maxWordId", wordvo);
    }

    @Override
    public wordVO selectReviewCard(Map wordMap) throws DataAccessException {
        return session.selectOne("mapper.word.selectReviewCard", wordMap);
    }

    @Override
    public wordVO selectNewCard(Map wordMap)throws  DataAccessException {
        return session.selectOne("mapper.word.selectNewCard", wordMap);
    }

    @Override
    public void updateWrongCard(Map wordMap) throws DataAccessException {
        session.update("mapper.word.updateWrongCard",wordMap);
    }

    @Override
    public void updateReviewCard_Appropriate(Map wordMap) throws DataAccessException {
        session.update("mapper.word.updateReviewCard_Appropriate", wordMap);
    }

    @Override
    public wordVO selectRemainedReviewCard(Map wordMap) throws DataAccessException {
        return session.selectOne("mapper.word.selectRemainedReviewCard", wordMap);
    }

    @Override
    public wordVO selectRemainedNewCard(Map wordMap) throws DataAccessException {
        return session.selectOne("mapper.word.selectRemainedNewCard",wordMap);
    }

    @Override
    public void updateNewCard_Appropriate(Map wordMap) throws DataAccessException {
        session.update("mapper.word.updateNewCard_Appropriate",wordMap);
    }
}

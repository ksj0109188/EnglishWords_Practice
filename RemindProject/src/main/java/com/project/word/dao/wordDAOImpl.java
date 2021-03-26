package com.project.word.dao;

import com.project.word.vo.wordVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;
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

    @Override
    public List<wordVO> selectModifyWord(Map<String, Object> wordMap) {
        return session.selectList("mapper.word.selectModifyWord",wordMap);
    }

    @Override
    public int totalCount(Map<String, Object> wordMap) throws DataAccessException {
        return session.selectOne("mapper.word.totalCount",wordMap);
    }

    @Override
    public wordVO selectSpecificWord(Map<String, Object> wordMap) {
        return session.selectOne("mapper.word.selectSpecificWord",wordMap);
    }

    @Override
    public void updateWord(Map<String, Object> wordMap) throws DataAccessException {
        session.selectOne("mapper.word.updateWord",wordMap);
    }

    @Override
    public void deleteWord(Map<String, Object> wordMap) throws DataAccessException {
        session.delete("mapper.word.deleteWord",wordMap);
    }


}

package com.project.word.dao;

import com.project.word.vo.wordVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository("wordDao")
public class wordDAOImpl implements wordDAO {
    @Autowired
    SqlSession session;


    @Override
    public void addWord(wordVO wordvo) throws SQLException {
        session.insert("mapper.word.addword", wordvo);
    }

    @Override
    public int maxWordId(wordVO wordvo) throws SQLException {
        return session.selectOne("mapper.word.maxWordId", wordvo);
    }

    @Override
    public wordVO selectReviewCard(Map studyMap) throws SQLException {
        return session.selectOne("mapper.word.selectReviewCard", studyMap);
    }

    @Override
    public wordVO selectNewCard(Map studyMap)throws  SQLException {
        return session.selectOne("mapper.word.selectNewCard", studyMap);
    }

    @Override
    public void updateReviewCard(wordVO wordvo) throws SQLException {
        session.update("mapper.word.updateReviewCard",wordvo);
    }


}

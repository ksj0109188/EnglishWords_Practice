package com.project.word.dao;

import com.project.word.vo.wordVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository("wordDao")
public class wordDAOImpl implements wordDAO {
    @Autowired
    SqlSession session;
    @Override
    public void addWord(wordVO wordvo) throws SQLException {
        session.insert("mapper.word.addword",wordvo);
    }

    @Override
    public int maxWordId(wordVO wordvo) throws SQLException {
         return session.selectOne("mapper.word.maxWordId",wordvo);
    }

}

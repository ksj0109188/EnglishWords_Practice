package com.project.statistic.dao;

import com.project.statistic.vo.statisticVO;
import com.project.word.vo.wordVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("statisticDAO")
public class statisticDAOImpl implements statisticDAO {
    @Autowired
    SqlSession session;

    @Override
    public void addWord(wordVO wordvo) throws DataAccessException {
        session.insert("mapper.statistic.addWord", wordvo);
    }

    @Override
    public void addDailyWord(wordVO wordvo) throws DataAccessException {
        session.insert("mapper.statistic.addDailyWord", wordvo);
    }

    @Override
    public void updateWrongCard(Map wordMap) throws DataAccessException {
        session.update("mapper.statistic.updateWrongCard", wordMap);
    }

    @Override
    public void updateAppropriateCard(Map wordMap) throws DataAccessException {
        session.update("mapper.statistic.updateAppropriateCard", wordMap);
    }

    @Override
    public int countWord(Map staMap) throws DataAccessException {
        return session.selectOne("mapper.statistic.countWord", staMap);
    }

    @Override
    public List<statisticVO> search(Map staMap) throws DataAccessException {
        return session.selectList("mapper.statistic.search", staMap);
    }


}

package com.project.dailyWord.dao;

import com.project.dailyWord.vo.dailyWordVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("dailyWordDAO")
public class dailyWordDAOImpl implements dailyWordDAO {

    @Autowired
    SqlSession session;

    @Override
    public int selectmaxId() throws DataAccessException {
        return session.selectOne("mapper.dailyWord.maxId");
    }

    @Override
    public void insertDailyWord(List<dailyWordVO> wordVOElements) throws DataAccessException {
        session.insert("mapper.dailyWord.insertDailyWord", wordVOElements);
    }

    @Override
    public List<dailyWordVO> selectDailyWord(Map dailyWordMap) throws DataAccessException {
        return session.selectList("mapper.dailyWord.selectDailyWord", dailyWordMap);
    }
}

package com.project.statistic.dao;

import com.project.statistic.vo.statisticVO;
import com.project.word.vo.wordVO;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;

public interface statisticDAO {
    void addWord(wordVO wordvo) throws DataAccessException;

    void addDailyWord(wordVO wordvo) throws DataAccessException;

    void updateWrongCard(Map<String, Object> wordMap) throws DataAccessException;

    void updateAppropriateCard(Map<String, Object> wordMap) throws DataAccessException;

    int countWord(Map<String, Object> staMap) throws DataAccessException;

    List<statisticVO> search(Map<String, Object> staMap) throws DataAccessException;
}

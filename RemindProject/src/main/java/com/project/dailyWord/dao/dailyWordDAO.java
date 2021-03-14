package com.project.dailyWord.dao;

import com.project.dailyWord.vo.dailyWordVO;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;

public interface dailyWordDAO {
    int selectmaxId() throws DataAccessException;

    void insertDailyWord(List<dailyWordVO> wordVOElements) throws DataAccessException;

    List<dailyWordVO> selectDailyWord(Map dailyWordMap) throws DataAccessException;
}

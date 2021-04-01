package com.project.dailyWord.dao;

import com.project.dailyWord.vo.dailyWordVO;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface dailyWordDAO {
    int selectmaxId() throws DataAccessException;

    void insertDailyWord(List<dailyWordVO> wordVOElements) throws DataAccessException;

    List<dailyWordVO> selectDailyWord() throws DataAccessException;
}

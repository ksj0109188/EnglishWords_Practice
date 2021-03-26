package com.project.dailyWord.service;

import com.project.dailyWord.vo.dailyWordVO;

import java.util.List;

public interface dailyWordService {
    int selectmaxId() throws Exception;

    void insertDailyWord(List<dailyWordVO> wordVOElements) throws Exception;

    List<dailyWordVO> selectDailyWord() throws Exception;
}

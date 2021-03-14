package com.project.dailyWord.service;

import com.project.dailyWord.vo.dailyWordVO;

import java.util.List;
import java.util.Map;

public interface dailyWordService {
    int selectmaxId() throws Exception;

    void insertDailyWord(List<dailyWordVO> wordVOElements) throws Exception;

    List<dailyWordVO> selectDailyWord(Map dailyWordMap) throws Exception;
}

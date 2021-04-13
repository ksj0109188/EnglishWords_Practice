package com.project.statistic.service;

import com.project.statistic.vo.statisticVO;
import com.project.word.vo.wordVO;

import java.util.List;
import java.util.Map;

public interface statisticService {
    void addWord(wordVO wordvo) throws Exception;

    void addDailyWord(wordVO wordvo) throws Exception;

    void updateWrongCard(Map<String, Object> wordMap) throws Exception;

    void updateAppropriateCard(Map<String, Object> wordMap) throws Exception;

    int countWord(Map<String, Object> staMap) throws Exception;

    List<statisticVO> search(Map<String, Object> staMap) throws Exception;
}

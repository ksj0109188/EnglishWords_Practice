package com.project.statistic.service;

import com.project.statistic.dao.statisticDAO;
import com.project.statistic.vo.statisticVO;
import com.project.word.vo.wordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional(propagation = Propagation.REQUIRED)
@Service("statisticService")
public class statisticServiceImpl implements statisticService {
    @Autowired
    statisticDAO statisticDAO;

    @Override
    public void addWord(wordVO wordvo) {
        statisticDAO.addWord(wordvo);
    }

    @Override
    public void addDailyWord(wordVO wordvo) {
        statisticDAO.addDailyWord(wordvo);
    }

    @Override
    public void updateWrongCard(Map<String, Object> wordMap) throws Exception {
        statisticDAO.updateWrongCard(wordMap);
    }

    @Override
    public void updateAppropriateCard(Map<String, Object> wordMap) throws Exception {
        statisticDAO.updateAppropriateCard(wordMap);
    }

    @Override
    public int countWord(Map<String, Object> staMap) throws Exception {
        return statisticDAO.countWord(staMap);
    }

    @Override
    public List<statisticVO> search(Map<String, Object> staMap) throws Exception {
        return statisticDAO.search(staMap);
    }


}

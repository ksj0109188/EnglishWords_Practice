package com.project.word.service;

import com.project.word.vo.wordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.word.dao.wordDAO;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service("wordservice")
@Transactional(propagation = Propagation.REQUIRED)
public class wordServiceImpl implements wordService {
    @Autowired
    wordDAO worddao;

    @Override
    public void addWord(wordVO wordvo) throws Exception {
        worddao.addWord(wordvo);
    }

    @Override
    public void addDailyWord(wordVO wordvo) throws Exception{
        worddao.addDailyWord(wordvo);
    }

    @Override
    public int countWrongReviewCard(Map wordMap) throws Exception {
        return worddao.countWrongReviewCard(wordMap);
    }

    @Override
    public int countReviewCard(Map wordMap) throws Exception {
        return worddao.countReviewCard(wordMap);
    }

    @Override
    public int countWrongNewCard(Map wordMap) throws Exception {
        return worddao.countWrongNewCard(wordMap);
    }

    @Override
    public int countNewCard(Map wordMap) throws Exception {
        return worddao.countNewCard(wordMap);
    }

    @Override
    public int maxWordId(wordVO wordvo) throws Exception {
        return worddao.maxWordId(wordvo);
    }

    @Override
    public wordVO selectReviewCard(Map wordMap) throws Exception {
        return worddao.selectReviewCard(wordMap);
    }

    @Override
    public wordVO selectNewCard(Map wordMap) throws Exception {
        return worddao.selectNewCard(wordMap);
    }

    @Override
    public void updateWrongCard(Map wordMap) throws Exception {
        worddao.updateWrongCard(wordMap);
    }

    @Override
    public void updateReviewCard_Appropriate(Map wordMap) throws Exception {
        worddao.updateReviewCard_Appropriate(wordMap);
    }

    @Override
    public wordVO selectRemainedReviewCard(Map wordMap) throws Exception {
        return worddao.selectRemainedReviewCard(wordMap);
    }

    @Override
    public wordVO selectRemainedNewCard(Map wordMap) throws Exception {
        return worddao.selectRemainedNewCard(wordMap);
    }

    @Override
    public void updateNewCard_Appropriate(Map wordMap) throws Exception {
        worddao.updateNewCard_Appropriate(wordMap);
    }
}

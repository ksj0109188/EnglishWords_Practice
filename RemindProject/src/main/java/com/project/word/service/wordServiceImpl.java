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
    public void addWord(wordVO wordvo) throws Exception {
        worddao.addWord(wordvo);
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
    public wordVO selectNewCard(wordVO wordvo) throws Exception {
        return worddao.selectNewCard(wordvo);
    }

    @Override
    public void updateReviewCard(Map wordMap) throws Exception {
        worddao.updateReviewCard(wordMap);
    }

    @Override
    public void updateAppropriate(Map wordMap) throws Exception {
        worddao.updateAppropriate(wordMap);
    }

    @Override
    public wordVO selectReviewRemainCard(Map wordMap) throws Exception {
        return worddao.selectReviewRemainCard(wordMap);
    }


}

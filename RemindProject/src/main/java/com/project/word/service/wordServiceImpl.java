package com.project.word.service;

import com.project.word.vo.wordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.word.dao.wordDAO;

import java.util.Map;

@Service("wordservice")
public class wordServiceImpl implements wordService {

    @Autowired
    wordDAO worddao;

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
    public void updateReview(Map wordMap) throws Exception {
        worddao.updateReviewCard(wordMap);
    }

    @Override
    public void updateAppropriate(wordVO wordvo) throws Exception {
        worddao.updateAppropriate(wordvo);
    }

    @Override
    public wordVO selectReviewRemainCard(Map wordMap) throws Exception {
        return worddao.selectReviewRemainCard(wordMap);
    }

    @Override
    public int countRemain(Map wordMap) throws Exception {
        return worddao.countRemain(wordMap);
    }

}

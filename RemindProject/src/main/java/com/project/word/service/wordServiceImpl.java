package com.project.word.service;

import com.project.word.vo.wordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.word.dao.wordDAO;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

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
    public wordVO selectReviewCard(wordVO wordvo) throws Exception {
        return worddao.selectReviewCard(wordvo);
    }

    @Override
    public wordVO selectNewCard(wordVO wordvo) throws Exception {
        return worddao.selectNewCard(wordvo);
    }

    @Override
    public void updateReview(wordVO wordvo) throws Exception {
        worddao.updateReviewCard(wordvo);
    }

    @Override
    public void updateAppropriate(wordVO wordvo) throws Exception {
        worddao.updateAppropriate(wordvo);
    }

    @Override
    public wordVO selectReviewRemainCard(wordVO wordvo) throws Exception {
        return worddao.selectReviewRemainCard(wordvo);
    }

}

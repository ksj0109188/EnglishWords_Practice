package com.project.word.service;

import com.project.word.vo.wordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.word.dao.wordDAO;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    public int countWrongReviewCard(Map<String,Object> wordMap) throws Exception {
        return worddao.countWrongReviewCard(wordMap);
    }

    @Override
    public int countReviewCard(Map<String,Object> wordMap) throws Exception {
        return worddao.countReviewCard(wordMap);
    }

    @Override
    public int countWrongNewCard(Map<String,Object> wordMap) throws Exception {
        return worddao.countWrongNewCard(wordMap);
    }

    @Override
    public int countNewCard(Map<String,Object> wordMap) throws Exception {
        return worddao.countNewCard(wordMap);
    }

    @Override
    public int maxWordId(wordVO wordvo) throws Exception {
        return worddao.maxWordId(wordvo);
    }

    @Override
    public wordVO selectReviewCard(Map<String,Object> wordMap) throws Exception {
        return worddao.selectReviewCard(wordMap);
    }

    @Override
    public wordVO selectNewCard(Map<String,Object> wordMap) throws Exception {
        return worddao.selectNewCard(wordMap);
    }

    @Override
    public void updateWrongCard(Map<String,Object> wordMap) throws Exception {
        worddao.updateWrongCard(wordMap);
    }

    @Override
    public void updateReviewCard_Appropriate(Map<String,Object> wordMap) throws Exception {
        worddao.updateReviewCard_Appropriate(wordMap);
    }

    @Override
    public wordVO selectRemainedReviewCard(Map<String,Object> wordMap) throws Exception {
        return worddao.selectRemainedReviewCard(wordMap);
    }

    @Override
    public wordVO selectRemainedNewCard(Map<String,Object> wordMap) throws Exception {
        return worddao.selectRemainedNewCard(wordMap);
    }

    @Override
    public void updateNewCard_Appropriate(Map<String,Object> wordMap) throws Exception {
        worddao.updateNewCard_Appropriate(wordMap);
    }

    @Override
    public List<wordVO> selectModifyWord(Map<String, Object> wordMap) throws Exception {
        return worddao.selectModifyWord(wordMap);
    }

    @Override
    public int totalCount(Map<String, Object> wordMap) throws Exception {
        return worddao.totalCount(wordMap);
    }

    @Override
    public wordVO selectSpecificWord(Map<String, Object> wordMap) {
        return worddao.selectSpecificWord(wordMap);
    }

    @Override
    public void updateWord(Map<String, Object> wordMap) throws Exception {
        worddao.updateWord(wordMap);
    }

    @Override
    public void deleteWord(Map<String, Object> wordMap) throws Exception {
        worddao.deleteWord(wordMap);
    }
}

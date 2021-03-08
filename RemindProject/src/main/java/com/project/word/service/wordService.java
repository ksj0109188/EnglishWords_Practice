package com.project.word.service;

import com.project.word.vo.wordVO;

import java.util.Map;

public interface wordService {
    void addWord  (wordVO wordvo) throws Exception;
    int maxWordId(wordVO wordvo) throws Exception;
    wordVO selectReviewCard(Map wordMap) throws Exception;
    wordVO selectNewCard(wordVO wordvo) throws Exception;
    void updateReview(Map wordMap) throws Exception;
    void updateAppropriate(wordVO wordvo) throws Exception;
    wordVO selectReviewRemainCard(Map wordMap) throws Exception;
    int countRemain(Map wordMap) throws Exception;

}

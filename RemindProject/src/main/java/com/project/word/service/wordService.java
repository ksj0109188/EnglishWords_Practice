package com.project.word.service;

import com.project.word.vo.wordVO;

import java.util.Map;

public interface wordService {
    int countReviewCard(Map wordMap) throws Exception;

    int countNewCard(Map wordMap) throws Exception;

    int countWrongReviewCard(Map wordMap) throws Exception;

    int countWrongNewCard(Map wordMap) throws Exception;

    void addWord(wordVO wordvo) throws Exception;

    int maxWordId(wordVO wordvo) throws Exception;

    wordVO selectReviewCard(Map wordMap) throws Exception;

    wordVO selectNewCard(wordVO wordvo) throws Exception;

    void updateReviewCard(Map wordMap) throws Exception;

    void updateAppropriate(Map wordMap) throws Exception;

    wordVO selectReviewRemainCard(Map wordMap) throws Exception;


}

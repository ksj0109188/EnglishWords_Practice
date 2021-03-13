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

    wordVO selectRemainedReviewCard(Map wordMap) throws Exception;

    void updateWrongCard(Map wordMap) throws Exception;

    void updateReviewCard_Appropriate(Map wordMap) throws Exception;

    wordVO selectNewCard(Map<String, String> wordMap) throws Exception;

    wordVO selectRemainedNewCard(Map wordMap) throws Exception;

    void updateNewCard_Appropriate(Map wordMap) throws Exception;
}

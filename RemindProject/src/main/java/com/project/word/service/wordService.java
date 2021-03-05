package com.project.word.service;

import com.project.word.vo.wordVO;

import java.sql.SQLException;

public interface wordService {
    void addWord  (wordVO wordvo) throws Exception;
    int maxWordId(wordVO wordvo) throws Exception;
    wordVO selectReviewCard(wordVO wordvo) throws Exception;
    wordVO selectNewCard(wordVO wordvo) throws Exception;
    void updateReview(wordVO wordvo) throws Exception;
    void updateAppropriate(wordVO wordvo) throws Exception;
    wordVO selectReviewRemainCard(wordVO wordvo) throws Exception;
}

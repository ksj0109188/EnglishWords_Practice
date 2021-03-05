package com.project.word.dao;

import com.project.word.vo.wordVO;
import org.springframework.dao.DataAccessException;

public interface wordDAO {
    void addWord(wordVO wordvo) throws DataAccessException;
    int maxWordId(wordVO wordvo) throws  DataAccessException;
    wordVO selectReviewCard(wordVO wordvo) throws DataAccessException;
    wordVO selectNewCard(wordVO wordvo) throws DataAccessException;
    void updateReviewCard(wordVO wordvo) throws DataAccessException;
    void updateAppropriate(wordVO wordvo) throws DataAccessException;
    wordVO selectReviewRemainCard(wordVO wordvo) throws DataAccessException;
}

package com.project.word.dao;

import com.project.word.vo.wordVO;
import org.springframework.dao.DataAccessException;

import java.util.Map;

public interface wordDAO {
    void addWord(wordVO wordvo) throws DataAccessException;
    int maxWordId(wordVO wordvo) throws  DataAccessException;
    wordVO selectReviewCard(Map wordMap) throws DataAccessException;
    wordVO selectNewCard(wordVO wordvo) throws DataAccessException;
    void updateReviewCard(Map wordMap) throws DataAccessException;
    void updateAppropriate(wordVO wordvo) throws DataAccessException;
    wordVO selectReviewRemainCard(Map wordMap) throws DataAccessException;
    int countRemain(Map wordMap) throws DataAccessException;
}

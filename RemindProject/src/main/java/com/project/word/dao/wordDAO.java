package com.project.word.dao;

import com.project.word.vo.wordVO;
import org.springframework.dao.DataAccessException;

import javax.xml.crypto.Data;
import java.util.Map;

public interface wordDAO {
    void addWord(wordVO wordvo) throws DataAccessException;

    void addDailyWord(wordVO wordvo) throws DataAccessException;

    int countWrongReviewCard(Map wordMap) throws DataAccessException;

    int countReviewCard(Map wordMap) throws DataAccessException;

    int countWrongNewCard(Map wordMap) throws DataAccessException;

    int countNewCard(Map wordMap) throws DataAccessException;

    int maxWordId(wordVO wordvo) throws DataAccessException;

    wordVO selectReviewCard(Map wordMap) throws DataAccessException;

    wordVO selectNewCard(Map wordMap) throws DataAccessException;

    void updateWrongCard(Map wordMap) throws DataAccessException;

    void updateReviewCard_Appropriate(Map wordMap) throws DataAccessException;

    wordVO selectRemainedReviewCard(Map wordMap) throws DataAccessException;

    wordVO selectRemainedNewCard(Map wordMap) throws DataAccessException;

    void updateNewCard_Appropriate(Map wordMap) throws DataAccessException;
}

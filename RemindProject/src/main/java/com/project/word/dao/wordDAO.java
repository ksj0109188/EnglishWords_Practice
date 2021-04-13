package com.project.word.dao;

import com.project.word.vo.wordVO;
import org.springframework.dao.DataAccessException;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Map;

public interface wordDAO {
    void addWord(wordVO wordvo) throws DataAccessException;

    void addDailyWord(wordVO wordvo) throws DataAccessException;

    int countWrongReviewCard(Map<String, Object> wordMap) throws DataAccessException;

    int countReviewCard(Map<String, Object> wordMap) throws DataAccessException;

    int countWrongNewCard(Map<String, Object> wordMap) throws DataAccessException;

    int countNewCard(Map<String, Object> wordMap) throws DataAccessException;

    int maxWordId(wordVO wordvo) throws DataAccessException;

    wordVO selectReviewCard(Map<String, Object> wordMap) throws DataAccessException;

    wordVO selectNewCard(Map<String, Object> wordMap) throws DataAccessException;

    void updateWrongCard(Map<String, Object> wordMap) throws DataAccessException;

    void updateReviewCard_Appropriate(Map<String, Object> wordMap) throws DataAccessException;

    wordVO selectRemainedReviewCard(Map<String, Object> wordMap) throws DataAccessException;

    wordVO selectRemainedNewCard(Map<String, Object> wordMap) throws DataAccessException;

    void updateNewCard_Appropriate(Map<String, Object> wordMap) throws DataAccessException;

    List<wordVO> selectModifyWord(Map<String, Object> wordMap) throws DataAccessException;

    int totalCount(Map<String, Object> wordMap) throws DataAccessException;

    wordVO selectSpecificWord(Map<String, Object> wordMap) throws DataAccessException;

    void updateWord(Map<String, Object> wordMap) throws DataAccessException;

    void deleteWord(Map<String, Object> wordMap) throws DataAccessException;
}

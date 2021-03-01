package com.project.word.service;

import com.project.word.vo.wordVO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface wordService {
    void addWord  (wordVO wordvo) throws SQLException;
    int maxWordId(wordVO wordvo) throws SQLException;
    List<wordVO> selectReviewCard(Map studyMap);
    List<wordVO> selectNewCard(Map studyMap);
}

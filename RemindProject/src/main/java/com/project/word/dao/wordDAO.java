package com.project.word.dao;

import com.project.word.vo.wordVO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface wordDAO {
    void addWord(wordVO wordvo) throws SQLException;
    int maxWordId(wordVO wordvo) throws  SQLException;
    List<wordVO> selectReviewCard(Map studyMap);
    List<wordVO> selectNewCard(Map studyMap);
}

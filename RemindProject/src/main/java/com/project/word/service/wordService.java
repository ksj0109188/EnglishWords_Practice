package com.project.word.service;

import com.project.word.vo.wordVO;

import java.sql.SQLException;

public interface wordService {
    void addWord  (wordVO wordvo) throws SQLException;
    int maxWordId(wordVO wordvo) throws SQLException;
}

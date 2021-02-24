package com.project.word.dao;

import com.project.word.vo.wordVO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

public interface wordDAO {
    void addWord(wordVO wordvo) throws SQLException;
    int maxWordId(wordVO wordvo) throws  SQLException;
}

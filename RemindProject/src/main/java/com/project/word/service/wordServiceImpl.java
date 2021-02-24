package com.project.word.service;

import com.project.word.vo.wordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.word.dao.wordDAO;

import java.sql.SQLException;

@Service("wordservice")
public class wordServiceImpl implements wordService{

    @Autowired
    wordDAO worddao;

    @Override
    public void addWord(wordVO wordvo) throws SQLException {
        worddao.addWord(wordvo);
    }

    @Override
    public int maxWordId(wordVO wordvo) throws SQLException {
        return worddao.maxWordId(wordvo);
    }
}

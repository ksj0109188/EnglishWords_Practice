package com.project.word.service;

import com.project.word.vo.wordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.word.dao.wordDAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<wordVO> selectReviewCard(Map studyMap) {
        return worddao.selectReviewCard(studyMap);
    }

    @Override
    public List<wordVO> selectNewCard(Map studyMap) {
        return worddao.selectNewCard(studyMap);
    }


}

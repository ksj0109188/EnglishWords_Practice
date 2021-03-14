package com.project.dailyWord.service;

import com.project.dailyWord.dao.dailyWordDAO;
import com.project.dailyWord.vo.dailyWordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("dailyWordService")
public class dailyWordServiceImpl implements dailyWordService {

    @Autowired
    dailyWordDAO dailyWordDAO;


    @Override
    public int selectmaxId() throws Exception{
        return dailyWordDAO.selectmaxId();
    }

    @Override
    public void insertDailyWord(List<dailyWordVO> wordVOElements) throws Exception{
        dailyWordDAO.insertDailyWord(wordVOElements);
    }

    @Override
    public List<dailyWordVO> selectDailyWord(Map dailyWordMap) throws Exception{
        return dailyWordDAO.selectDailyWord(dailyWordMap);
    }
}

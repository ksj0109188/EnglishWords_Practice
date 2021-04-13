package com.project.dailyWord.Controller;


import com.project.common.base.BaseController;
import com.project.common.mail.mailServiceImpl;
import com.project.dailyWord.service.dailyWordService;
import com.project.dailyWord.vo.dailyWordVO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@EnableScheduling
@Controller("dailyWord")
@RequestMapping(value = "/dailyWord")
public class dailyWordControllerImpl extends BaseController implements dailyWordController {

    private static final Logger logger = LoggerFactory.getLogger(dailyWordControllerImpl.class);

    @Autowired
    dailyWordVO dailyWordvo;

    @Autowired
    dailyWordService dailyWordService;

    @Scheduled(cron = "0 0 01 * * *")
    public void cloneDailyWord() {
        String connectURL = "https://learn.dict.naver.com/m/endic/today/words.nhn";
        try {
            Document doc = Jsoup.connect(connectURL).get();
            String[] words = doc.select("em.words").html().split("\n");
            Elements mean = doc.select("p.txt_ct2");
            Iterator<Element> iterable = mean.iterator();
            int maxId = dailyWordService.selectmaxId();
            List<dailyWordVO> wordVOElements = new ArrayList<dailyWordVO>();
            int wordsIndex = 0;
            while (iterable.hasNext()) {
                dailyWordVO _dailyWordvo = new dailyWordVO();
                _dailyWordvo.setDailyId(maxId);
                _dailyWordvo.setWord(words[wordsIndex]);
                _dailyWordvo.setMean(iterable.next().text());
                wordVOElements.add(_dailyWordvo);
                maxId++;
                wordsIndex++;
            }
            dailyWordService.insertDailyWord(wordVOElements);
        } catch (Exception e) {
            logger.debug("DEBUG : " + e);
        }
    }

    @RequestMapping("/dailyWordForm.do")
    @Override
    public ModelAndView dailyWordForm(HttpServletRequest request, HttpServletResponse response) {
        List<dailyWordVO> wordVOElements;
        try {
            wordVOElements = dailyWordService.selectDailyWord();
            ModelAndView modelAndView = new ModelAndView("/dailyWord/dailyWordForm");
            modelAndView.addObject("dailyWordVO",wordVOElements);
            return modelAndView;
        } catch (Exception e) {
            logger.debug("DEBUG : " + e);
            ModelAndView modelAndView = new ModelAndView("/common/error");
            return modelAndView;
        }
    }
}
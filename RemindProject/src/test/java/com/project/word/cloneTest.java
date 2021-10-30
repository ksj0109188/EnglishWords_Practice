package com.project.word;

import com.project.dailyWord.Controller.dailyWordControllerImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class cloneTest {
    private static final Logger logger = LoggerFactory.getLogger(cloneTest.class);

    @DisplayName("네이버 단어장 크롤링")
    @Test
    public void cloneTest() {
        String connectURL = "https://learn.dict.naver.com/m/endic/today/words.nhn";
        try {
            Document doc = Jsoup.connect(connectURL).get();
            Elements words = doc.select("div#word_card.word_card");
            Iterator<Element> nhnHtmlIterator = words.iterator();
            while (nhnHtmlIterator.hasNext()) {
                String word = "";
                String mean = "";
                Element nhnHtmlElements = nhnHtmlIterator.next();
                Elements word_shown_title_elements = nhnHtmlElements.select("div.word_card_header > div.word_card_title >  span.letter");
                Elements word_mean_elements = nhnHtmlElements.select("div.mean > span");
                Iterator<Element> word_title_iterator = word_shown_title_elements.iterator();
                Iterator<Element> word_mean_iterator = word_mean_elements.iterator();
                while (word_title_iterator.hasNext()) {
                    word += word_title_iterator.next().text();
                }
                while (word_mean_iterator.hasNext()) {
                    mean += word_mean_iterator.next().text();
                }
                logger.info(word);
                logger.info(mean);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

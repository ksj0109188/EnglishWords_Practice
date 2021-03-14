package com.project.word;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Iterator;


public class cloneTest {

    @DisplayName("네이버 단어장 크롤링")
    @Test
    public void cloneTest() {
        String connectURL = "https://learn.dict.naver.com/m/endic/today/words.nhn";
        try {
            Document doc = Jsoup.connect(connectURL).get();
            String[] words = doc.select("em.words").html().split("\n");
            Elements mean = doc.select("p.txt_ct2");
            Iterator<Element> iterable = mean.iterator();
            for (int i=0; i< words.length ; i++) {
                System.out.println("오늘의 단어 "+ words[i]+iterable.next().text());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.project.dailyWord.Controller;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface dailyWordController {

    void cloneDailyWord() ;
    ModelAndView dailyWordForm(HttpServletRequest request, HttpServletResponse response);
}

package com.project.statistic.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface statisticController {
    public ModelAndView statisticForm(HttpServletRequest request, HttpServletResponse response);
    ResponseEntity search(HttpServletRequest request, HttpServletResponse response, @PathVariable("word") String word);
}

package com.project.inquiryBoard.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface inquiryBoardController {

    ModelAndView selectInquiryBoard(HttpServletRequest request, HttpServletResponse response);
    ModelAndView selectBoardDetail(HttpServletRequest request, HttpServletResponse response, @PathVariable("boardId") int boardId);
    ModelAndView writeBoard(HttpServletRequest request, HttpServletResponse response);
}

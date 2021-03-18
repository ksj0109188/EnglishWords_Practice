package com.project.inquiryBoard.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface inquiryBoardController {

    ModelAndView selectInquiryBoard(HttpServletRequest request, HttpServletResponse response);
    ModelAndView selectBoardDetail(HttpServletRequest request, HttpServletResponse response, @PathVariable("boardId") int boardId);
    public ModelAndView writeBoard(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception;
}

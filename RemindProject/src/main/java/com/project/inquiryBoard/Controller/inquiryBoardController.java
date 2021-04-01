package com.project.inquiryBoard.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface inquiryBoardController {

    ModelAndView selectInquiryBoard(HttpServletRequest request, HttpServletResponse response,
                                    @RequestParam(value = "section", defaultValue = "1") int section,
                                    @RequestParam(value = "page", defaultValue = "1") int page);

    public ModelAndView selectTitle(HttpServletRequest request, HttpServletResponse response,
                                    @RequestParam(value = "title") String title,
                                    @RequestParam(value = "section", defaultValue = "1") int section,
                                    @RequestParam(value = "pageNum", defaultValue = "1") int pageNum);

    ModelAndView selectBoardDetail(HttpServletRequest request, HttpServletResponse response, @PathVariable("boardId") int boardId);

    ResponseEntity writeBoard(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception;

    ResponseEntity writeAnswer(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String, String> boardMap);

    ModelAndView modifyForm(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "boardId", required = true) int boardId);

    ModelAndView modifyBoard(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception;

    ModelAndView deleteBoard(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "boardId") int boardId,
                             @RequestParam(value = "imageFileName") String imageFileName);

    ResponseEntity modifyAnswer(HttpServletRequest request, HttpServletResponse response, @RequestBody Map AnswerMap);

    ResponseEntity deleteAnswer(HttpServletRequest request, HttpServletResponse response, @PathVariable int AnswerId);
}

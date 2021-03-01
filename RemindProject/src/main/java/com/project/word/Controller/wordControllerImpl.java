package com.project.word.Controller;

import com.project.common.base.BaseController;
import com.project.word.service.wordService;
import com.project.word.vo.wordVO;
import org.omg.CORBA.portable.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("wordcontroller")
@RequestMapping("/word")
public class wordControllerImpl extends BaseController implements wordController {
    @Autowired
    wordVO wordvo;

    @Autowired
    wordService wordservice;

    @RequestMapping(value = "/addWord.do",method = RequestMethod.POST)
    @Override
    public ResponseEntity addWord(HttpServletRequest request, HttpServletResponse response, @ModelAttribute wordVO wordvo) {
        String message;
        String Context = request.getContextPath();
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.add("content-type", "text/html; charset=utf-8");
        HttpSession ssesion = request.getSession();
        String userId = (String) ssesion.getAttribute("userId");
        wordvo.setUser_id(userId);
        try {
            int wordId = wordservice.maxWordId(wordvo);
            wordvo.setWordId(wordId);
            wordservice.addWord(wordvo);
            message = "<script>";
            message += "alert('저장완료.');";
            message += "location.href='" + Context + "/word/saveWordForm.do';";
            message += "</script>";
        } catch (SQLException e) {
            message = "<script>";
            message += "alert('저장실패 잠시 후 다시 시도해주세요.');";
            message += "location.href='" + Context + "/word/saveWordForm.do';";
            message += "</script>";
        }
        return new ResponseEntity(message,responseHeader ,HttpStatus.OK);
    }

    @RequestMapping(value="/study.do", method =RequestMethod.GET)
    @Override
    public ModelAndView reviewStudy(HttpServletRequest request, HttpServletResponse response, @RequestParam("studyQuantity") int quantity) {
        Map studyMap = new HashMap();
        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("userId");
        studyMap.put("user_id",user_id);
        studyMap.put("quantity", quantity);
        List<wordVO> wordVo=wordservice.selectReviewCard(studyMap);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("wordvo",wordVo);
        modelAndView.setViewName("/word/studyPage");
        return modelAndView;
    }

    @RequestMapping(value="/newCard.do", method =RequestMethod.GET)
    @Override
    public ModelAndView newCardStudy(HttpServletRequest request, HttpServletResponse response, @RequestParam("studyQuantity") int quantity) {
        Map studyMap = new HashMap();
        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("userId");
        studyMap.put("user_id",user_id);
        studyMap.put("quantity", quantity);
        List<wordVO> wordVo=wordservice.selectNewCard(studyMap);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("wordvo",wordVo);
        modelAndView.setViewName("/word/studyPage");
        return modelAndView;
    }
}
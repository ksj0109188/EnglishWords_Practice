package com.project.word.Controller;

import com.project.word.vo.wordVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface wordController {

    ResponseEntity addWord(HttpServletRequest request, HttpServletResponse response, @ModelAttribute wordVO wordvo);

    ResponseEntity addDailyWord(HttpServletRequest request, HttpServletResponse response, @ModelAttribute wordVO wordvo);

    ModelAndView setStudyForm(HttpServletRequest request, HttpServletResponse response);

    ModelAndView reviewCardForm(HttpServletRequest request, HttpServletResponse response);

    ResponseEntity reviewStudy_Wrong(HttpServletRequest request, HttpServletResponse response, @RequestBody Map wordMap);

    ResponseEntity reviewCardSelect(HttpServletRequest request, HttpServletResponse response);

    ResponseEntity reviewStudy_appropriate(HttpServletRequest request, HttpServletResponse response, @RequestBody Map wordMap);

    ModelAndView newCardForm(HttpServletRequest request, HttpServletResponse response);

    ResponseEntity newCardStudy_wrong(HttpServletRequest request, HttpServletResponse response, @RequestBody Map wordMap);

    ResponseEntity newCardUpdate_appropriate(HttpServletRequest request, HttpServletResponse response, @RequestBody Map wordMap);

    ResponseEntity newCardSelect(HttpServletRequest request, HttpServletResponse response);


}

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
    ModelAndView reviewStudy(HttpServletRequest request, HttpServletResponse response, @RequestParam("studyQuantity") int quantity);
    ModelAndView newCardStudy(HttpServletRequest request, HttpServletResponse response, @RequestParam("studyQuantity") int quantity);
    ResponseEntity review(HttpServletRequest request, HttpServletResponse response, @RequestBody Map wordMap);
    ResponseEntity appropriate(HttpServletRequest request, HttpServletResponse response,  @RequestBody Map wordMap);
}

package com.project.word.Controller;

import com.project.common.base.BaseController;
import com.project.word.service.wordService;
import com.project.word.vo.wordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestController("wordcontroller")
@RequestMapping("/word")
public class wordControllerImpl extends BaseController implements wordController {
    @Autowired
    wordVO wordvo;

    @Autowired
    wordService wordservice;

    @RequestMapping(value = "/addWord.do", method = RequestMethod.POST)
    @Override
    public ResponseEntity addWord(HttpServletRequest request, HttpServletResponse response, @ModelAttribute wordVO wordvo) {
        String message;
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
            message += "location.href='" + request.getContextPath() + "/word/saveWordForm.do';";
            message += "</script>";
        } catch (Exception e) {
            message = "<script>";
            message += "alert('저장실패 잠시 후 다시 시도해주세요.');";
            message += "location.href='" + request.getContextPath() + "/word/saveWordForm.do';";
            message += "</script>";
        }
        return new ResponseEntity(message, responseHeader, HttpStatus.OK);
    }

    @RequestMapping(value="/study.do", method =RequestMethod.GET)
    @Override
    public ModelAndView reviewStudy(HttpServletRequest request, HttpServletResponse response, @RequestParam("studyQuantity") int quantity) {
        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("userId");
        session.setAttribute("studyQuantity", quantity);
        wordvo.setUser_id(user_id);
        try {
            wordVO _wordVO = wordservice.selectReviewCard(wordvo);
            if (_wordVO != null) {
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.addObject("wordvo", _wordVO);
                modelAndView.setViewName("word/reviewStudyPage");
                return modelAndView;
            } else {
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("/main/mainContent");
                modelAndView.addObject("finish", "true");
                return modelAndView;
            }
        } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("/main/mainContent");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/newCard.do", method = RequestMethod.GET)
    @Override
    public ModelAndView newCardStudy(HttpServletRequest request, HttpServletResponse response, @RequestParam("studyQuantity") int quantity) {
        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("userId");
        session.setAttribute("studyQuantity", quantity);
        wordvo.setUser_id(user_id);
        try {
            wordVO _wordVO = wordservice.selectNewCard(wordvo);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("wordvo", _wordVO);
            modelAndView.setViewName("word/newCardStudyPage");
            return modelAndView;
        } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("/main/mainContent");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/reviewCard_review.do", method = RequestMethod.PUT)
    @Override
    public ResponseEntity review(HttpServletRequest request, HttpServletResponse response, @RequestBody wordVO wordvo) {
        String message;
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.add("content-type", "text/html; charset=utf-8");
        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("userId");
        int quantity = (int) session.getAttribute("studyQuantity");
        wordvo.setUser_id(user_id);
        wordvo.setWordCount(0);
        wordvo.setSavedDate(setTime(wordvo.getWordCount()));
        try {
            wordservice.updateReview(wordvo);
            wordVO _wordvo = wordservice.selectReviewCard(wordvo);
            //영어단어를 설정한 학습량만큼 다시 검색
            if (_wordvo == null && quantity > 0) {
                session.setAttribute("studyQuantity", quantity - 1);
                _wordvo = wordservice.selectReviewRemainCard(wordvo);
            }
            //공부할 데이터가 없을때.
            if (_wordvo == null) {
                message = "<script>";
                message += "alert('공부할 것이 없습니다.');";
                message += "location.href='" + request.getContextPath() + "/main/mainContent';";
                message += "</script>";
                return new ResponseEntity(message, responseHeader, HttpStatus.OK);
            }
            return new ResponseEntity(_wordvo, HttpStatus.OK);
        } catch (Exception e) {
            message = "<script>";
            message += "alert('잠시후 다시 시도해주세요.');";
            message += "location.href='" + request.getContextPath() + "/main/mainContent';";
            message += "</script>";
            return new ResponseEntity(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/reviewCard_appropriate.do")
    @Override
    public ResponseEntity appropriate(HttpServletRequest request, HttpServletResponse response, @ModelAttribute wordVO wordvo) {
        String message;
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.add("content-type", "text/html; charset=utf-8");
        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("userId");
        int quantity = (int) session.getAttribute("studyQuantity");
        if (quantity > 0) {
            session.setAttribute("studyQuantity", quantity - 1);
        }
        wordvo.setWordCount(wordvo.getWordCount() + 1);
        wordvo.setUser_id(user_id);
        wordvo.setSavedDate(setTime(wordvo.getWordCount()));
        try {
            wordservice.updateAppropriate(wordvo);
            wordVO _wordvo = wordservice.selectReviewCard(wordvo);
            //영어단어를 설정한 학습량만큼 다시 검색
            if (_wordvo == null && quantity > 0) {
                _wordvo = wordservice.selectReviewRemainCard(wordvo);
            }
            //공부할 데이터가 없을때.
            if (_wordvo == null) {
                message = "<script>";
                message += "alert('공부할 것이 없습니다.');";
                message += "location.href='" + request.getContextPath() + "/main/mainContent';";
                message += "</script>";
                return new ResponseEntity(message, responseHeader, HttpStatus.OK);
            }
            return new ResponseEntity(_wordvo, HttpStatus.OK);
        } catch (Exception e) {
            message = "<script>";
            message += "alert('잠시후 다시 시도해주세요.');";
            message += "location.href='" + request.getContextPath() + "/main/mainContent';";
            message += "</script>";
            return new ResponseEntity(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Timestamp setTime(int wordCount) {
        LocalDateTime time = LocalDateTime.now();
        if (wordCount == 0) {
            return Timestamp.valueOf(time.plusMinutes(10));
        } else if (wordCount == 1) {
            return Timestamp.valueOf(time.plusDays(1));
        } else if (wordCount == 2) {
            return Timestamp.valueOf(time.plusDays(7));
        } else if (wordCount == 3) {
            return Timestamp.valueOf(time.plusMonths(1));
        } else if (wordCount == 4) {
            return Timestamp.valueOf(time.plusMonths(6));
        } else if (wordCount == 5) {
            return Timestamp.valueOf(time.plusYears(1));
        } else {
            return Timestamp.valueOf(time.plusYears(3));
        }
    }
}
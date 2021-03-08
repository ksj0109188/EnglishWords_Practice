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
import java.util.HashMap;
import java.util.Map;

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
    public ModelAndView reviewStudy(HttpServletRequest request, HttpServletResponse response, @RequestParam("studyQuantity") int studyQuantity) {
        HttpSession session = request.getSession();
        session.setAttribute("studyQuantity", studyQuantity);
        String userId = (String) session.getAttribute("userId");
        ModelAndView modelAndView = new ModelAndView();
        wordVO _wordvo = null;
        Map wordMap = new HashMap();
        wordMap.put("user_id", userId);
        try {//틀린 카드부터 출력
            int countRemain = wordservice.countRemain(wordMap);
            session.setAttribute("countRemain", countRemain);
            if (countRemain > 0) {
                _wordvo = wordservice.selectReviewRemainCard(wordMap);
            }
            if (_wordvo != null) {
                modelAndView.addObject("wordvo", _wordvo);
                modelAndView.setViewName("word/reviewStudyPage");
                return modelAndView;
            } else {//틀린 카드가 없을때
                session.setAttribute("studyQuantity", studyQuantity - 1);
                _wordvo = wordservice.selectReviewCard(wordMap);
                if (_wordvo != null) {
                    modelAndView.addObject("wordvo", _wordvo);
                    modelAndView.setViewName("word/reviewStudyPage");
                    return modelAndView;
                }
                modelAndView = new ModelAndView();
                modelAndView.setViewName("/main/mainContent");
                modelAndView.addObject("finish", "true");
                return modelAndView;
            }
        } catch (Exception e) {
            e.printStackTrace();
            modelAndView = new ModelAndView();
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
    public ResponseEntity review(HttpServletRequest request, HttpServletResponse response, @RequestBody Map wordMap) {
        String message = "";
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.add("content-type", "text/html; charset=utf-8");
        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("userId");
        int countRemain = (int) session.getAttribute("countRemain");
        int studyQuantity = (int) session.getAttribute("studyQuantity");
        wordVO wordvo;
        wordMap.put("user_id", user_id);
        wordMap.put("wordCount", 0);
        wordMap.put("savedDate", setTime(0));
        wordMap.put("detection", true);
        wordMap.put("selectState", "notEmpty");
        wordMap.put("studyMode", "review");
        try {
            wordservice.updateReview(wordMap);
            if (countRemain < wordservice.countRemain(wordMap)) {//현재 카드가 틀린 카드로 바뀌었을때
                session.setAttribute("studyQuantity", studyQuantity - 1);
                session.setAttribute("countRemain", countRemain + 1);
            }
            wordvo = wordservice.selectReviewRemainCard(wordMap);
            if (wordvo != null) {
                return new ResponseEntity(wordvo, HttpStatus.OK);
            }
            wordvo = wordservice.selectReviewCard(wordMap);
            if (wordvo != null) {
                return new ResponseEntity(wordvo, HttpStatus.OK);
            }
            wordMap.put("selectState", "empty");
            wordvo = wordservice.selectReviewRemainCard(wordMap);
            if (wordvo != null) {
                session.setAttribute("studyQuantity", 0);
                return new ResponseEntity(wordvo, HttpStatus.OK);
            }
            message = "<script>";
            message += "alert('공부할 것이 없습니다.');";
            message += "location.href='" + request.getContextPath() + "/main/mainContent';";
            message += "</script>";
            return new ResponseEntity(message, responseHeader, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            message = "<script>";
            message += "alert('잠시후 다시 시도해주세요.');";
            message += "location.href='" + request.getContextPath() + "/main/mainContent';";
            message += "</script>";
            return new ResponseEntity(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/reviewCard_appropriate.do")
    @Override
    public ResponseEntity appropriate(HttpServletRequest request, HttpServletResponse response, @RequestBody Map wordMap) {
        String message;
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.add("content-type", "text/html; charset=utf-8");
        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("userId");
        int studyQuantity = (int) session.getAttribute("studyQuantity");
        wordvo.setWordCount(wordvo.getWordCount() + 1);
        wordvo.setUser_id(user_id);
        wordvo.setSavedDate(setTime(wordvo.getWordCount()));
        wordMap.put("user_id", user_id);
        wordMap.put("wordCount", 0);
        wordMap.put("savedDate", setTime((Integer) wordMap.get("wordcount") + 1));
        wordMap.put("detection", true);
        wordMap.put("selectState", "notEmpty");
        wordMap.put("studyMode", "review");
        studyQuantity--;
        if (studyQuantity < 0) studyQuantity = 0;
        try {
            int countRemain = wordservice.countRemain(wordMap);
            wordservice.updateAppropriate(wordvo);
            wordvo = wordservice.selectReviewRemainCard(wordMap);
            if (wordvo != null) {
//                session.setAttribute("countRemain",);
                return new ResponseEntity(wordvo, HttpStatus.OK);
            }
            wordvo = wordservice.selectReviewCard(wordMap);
            if (wordvo != null) {
                session.setAttribute("studyQuantity", studyQuantity - 1);
                return new ResponseEntity(wordvo, HttpStatus.OK);
            }
            wordMap.put("selectState", "empty");
            wordvo = wordservice.selectReviewRemainCard(wordMap);
            if (wordvo != null) {
                session.setAttribute("studyQuantity", 0);
                return new ResponseEntity(wordvo, HttpStatus.OK);
            }
            message = "<script>";
            message += "alert('공부할 것이 없습니다.');";
            message += "location.href='" + request.getContextPath() + "/main/mainContent';";
            message += "</script>";
            return new ResponseEntity(message, responseHeader, HttpStatus.OK);
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
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

    @RequestMapping(value = "/word", method = RequestMethod.POST)
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

    @RequestMapping(value = "/settingStudyForm.do")
    @Override
    public ModelAndView StudySetting(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView;
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        Map wordMap = new HashMap();
        wordMap.put("user_id", userId);
        try {
            int countWrongReviewCard = wordservice.countWrongReviewCard(wordMap);
            int countReviewCard = wordservice.countReviewCard(wordMap);
            int countWrongNewCard = wordservice.countWrongNewCard(wordMap);
            int countNewCard = wordservice.countNewCard(wordMap);

            Map setting = new HashMap();
            setting.put("countWrongReviewCard", countWrongReviewCard);
            setting.put("countReviewCard", countReviewCard);
            setting.put("countWrongNewCard", countWrongNewCard);
            setting.put("countNewCard", countNewCard);

            if (countWrongReviewCard == 0 && countReviewCard == 0 && countWrongNewCard == 0 && countNewCard == 0) {
                modelAndView = new ModelAndView();
                modelAndView.setViewName("/main/mainContent");
                modelAndView.addObject("finish", "true");
                return modelAndView;
            }
            modelAndView = new ModelAndView("/word/settingStudyForm");
            modelAndView.addObject("setting", setting);
            return modelAndView;
        } catch (Exception e) {
            e.printStackTrace();
            modelAndView = new ModelAndView("/common/error");
            return modelAndView;
        }
    }


    @RequestMapping(value = "/reviewCardForm.do", method = RequestMethod.GET)
    @Override
    public ModelAndView reviewStudy(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        ModelAndView modelAndView = new ModelAndView();
        wordVO _wordvo = null;
        Map<String, String> wordMap = new HashMap<String, String>();
        wordMap.put("user_id", userId);
        wordMap.put("studyMode", "review");
        try {//틀린 카드부터 출력
            wordMap.put("selectState", "notEmpty");
            _wordvo = wordservice.selectReviewRemainCard(wordMap);
            if (_wordvo != null) {
                modelAndView.addObject("wordvo", _wordvo);
                modelAndView.setViewName("/word/reviewCardForm");
                return modelAndView;
            }
            // 시간에 맞는 틀린카드가 없을때
            wordMap.put("selectState", "empty");
            _wordvo = wordservice.selectReviewRemainCard(wordMap);
            if (_wordvo != null) {
                modelAndView.addObject("wordvo", _wordvo);
                modelAndView.setViewName("/word/reviewCardForm");
            } else {//틀린 카드가 없을때
                _wordvo = wordservice.selectReviewCard(wordMap);
                if (_wordvo != null) {
                    modelAndView.addObject("wordvo", _wordvo);
                    modelAndView.setViewName("/word/reviewCardForm");
                    return modelAndView;
                }
                modelAndView = new ModelAndView();
                modelAndView.setViewName("/main/mainContent");
                modelAndView.addObject("finish", "true");
            }
            return modelAndView;
        } catch (Exception e) {
            e.printStackTrace();
            modelAndView = new ModelAndView();
            modelAndView.setViewName("/common/error");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/reviewStudy_reviewCard", method = RequestMethod.PUT)
    @Override
    public ResponseEntity reviewCardUpdate(HttpServletRequest request, HttpServletResponse response, @RequestBody Map wordMap) {
        HttpSession session = request.getSession();
        session.setAttribute("studyQuantity", 0);
        String user_id = (String) session.getAttribute("userId");

        wordMap.put("user_id", user_id);
        wordMap.put("wordCount", 0);
        wordMap.put("savedDate", setTime(0));

        try {
            wordservice.updateReviewCard(wordMap);
            Map ResponseMap = new HashMap();
            ResponseMap.put("Message", "SUCESS");
            return new ResponseEntity(ResponseMap, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            Map ResponseMap = new HashMap();
            ResponseMap.put("Message", "FAIL");
            return new ResponseEntity(ResponseMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @RequestMapping(value = "/reviewStudy_reviewCard", method = RequestMethod.GET)
    public ResponseEntity reviewCardSelect(HttpServletRequest request, HttpServletResponse response, @RequestParam("wordId") String wordId) {
        String message;
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.add("content-type", "text/html; charset=utf-8");
        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("userId");
        wordVO _wordvo;
        Map wordMap = new HashMap();
        wordMap.put("wordId", wordId);
        wordMap.put("user_id", user_id);
        wordMap.put("selectState", "notEmpty");
        wordMap.put("studyMode", "review");
        try {
            _wordvo = wordservice.selectReviewRemainCard(wordMap);
            if (_wordvo == null) {
                _wordvo = wordservice.selectReviewCard(wordMap); //복습해야할 카드 출력
                if (_wordvo == null) {
                    wordMap.put("selectState", "empty");
                    _wordvo = wordservice.selectReviewRemainCard(wordMap); //남은 틀린카드 출력
                }
            }
            if (_wordvo != null) {
                return new ResponseEntity(_wordvo, HttpStatus.OK);
            }
            message = "<script>";
            message += "alert('공부할 것이 없습니다.');";
            message += "location.href='" + request.getContextPath() + "/word/settingStudyForm.do';";
            message += "</script>";
            return new ResponseEntity(message, responseHeader, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            message = "<script>";
            message += "alert('에러가 발생했습니다.');";
            message += "location.href='" + request.getContextPath() + "/word/settingStudyForm.do';";
            message += "</script>";
            return new ResponseEntity(message, responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/reviewStudy_appropriateCard", method = RequestMethod.PUT)
    @Override
    public ResponseEntity appropriate(HttpServletRequest request, HttpServletResponse response, @RequestBody Map wordMap) {
        String message;
        wordVO _wordvo = null;
        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("userId");
        int increasedWordCount = Integer.parseInt((String) wordMap.get("wordCount")) + 1;
        wordMap.put("user_id", user_id);
        wordMap.put("savedDate", setTime(increasedWordCount));
        wordMap.put("wordCount", increasedWordCount);
        try {
            wordservice.updateAppropriate(wordMap);
//            _wordvo = wordservice.selectReviewRemainCard(wordMap);
//            if (_wordvo != null) {
//                return new ResponseEntity(_wordvo, HttpStatus.OK);
//            }
//            _wordvo = wordservice.selectReviewCard(wordMap);
//            if (_wordvo != null) {
//                return new ResponseEntity(_wordvo, HttpStatus.OK);
//            }
//            wordMap.put("selectState", "empty");
//            _wordvo = wordservice.selectReviewRemainCard(wordMap);
//            if (_wordvo != null) {
//                return new ResponseEntity(_wordvo, HttpStatus.OK);
//            }
            Map ResponseMap = new HashMap();
            ResponseMap.put("Message", "SUCESS");
            return new ResponseEntity(ResponseMap, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            Map ResponseMap = new HashMap();
            ResponseMap.put("Message", "SUCESS");
            return new ResponseEntity(ResponseMap, HttpStatus.OK);
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

    public Timestamp setTime(int wordCount) {
        LocalDateTime time = LocalDateTime.now();
        if (wordCount <= 0) {
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
package com.project.word.Controller;

import com.project.common.base.BaseController;
import com.project.statistic.service.statisticService;
import com.project.word.service.wordService;
import com.project.word.vo.wordVO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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

    @Autowired
    statisticService statisticservice;

    @RequestMapping(value = "/word", method = RequestMethod.POST)
    @Override
    public ResponseEntity addWord(HttpServletRequest request, HttpServletResponse response, @ModelAttribute wordVO wordvo) {
        String message;
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.add("content-type", "text/html; charset=utf-8");
        HttpSession ssesion = request.getSession();
        String userId = (String) ssesion.getAttribute("userId");
        wordvo.setUserId(userId);
        try {
            int wordId = wordservice.maxWordId(wordvo);
            wordvo.setWordId(wordId);
            wordservice.addWord(wordvo);
            statisticservice.addWord(wordvo);

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

    @RequestMapping(value = "/DailyWord", method = RequestMethod.POST)
    @Override
    public ResponseEntity addDailyWord(HttpServletRequest request, HttpServletResponse response, wordVO wordvo) {
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.add("content-type", "text/html; charset=utf-8");
        String message;
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        wordvo.setUserId(userId);
        try {
            int wordId = wordservice.maxWordId(wordvo);
            wordvo.setWordId(wordId);
            wordservice.addDailyWord(wordvo);
            statisticservice.addDailyWord(wordvo);
            message = "<script>";
            message += "alert('오늘의 단어를 나의 새카드 학습하기로 이동했습니다.');";
            message += "</script>";
            return new ResponseEntity(message, responseHeader, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            message = "<script>";
            message += "alert('잠시후 다시 시도해주세요.');";
            message += "</script>";
            return new ResponseEntity(message, responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/StudyForm.do")
    @Override
    public ModelAndView setStudyForm(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView;
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        Map wordMap = new HashMap();
        wordMap.put("userId", userId);
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

    @RequestMapping(value = "/wordBoardForm.do", method = RequestMethod.GET)
    @Override
    public ModelAndView wordBoardForm(HttpServletRequest request, HttpServletResponse response,
                                      @RequestParam(value = "section", defaultValue = "1") int section,
                                      @RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        int startPage = ((section - 1) * 100) + ((pageNum - 1) * 10);

        Map<String, Object> wordMap = new HashMap<>();
        wordMap.put("pageNum", pageNum);
        wordMap.put("section", section);
        wordMap.put("startPage", startPage);
        wordMap.put("userId", userId);

        ModelAndView modelAndView;
        List<wordVO> wordVoItems;
        try {
            wordVoItems = wordservice.selectModifyWord(wordMap);
            int totalCount = wordservice.totalCount(wordMap);
            wordMap.remove("userId");
            wordMap.put("wordVoItems", wordVoItems);
            wordMap.put("totalCount", totalCount);
            modelAndView = new ModelAndView("/word/wordBoardForm");
            modelAndView.addObject("wordMap", wordMap);
        } catch (Exception e) {
            e.printStackTrace();
            modelAndView = new ModelAndView("common/error");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/title")
    @Override
    public ModelAndView searchTitle(HttpServletRequest request, HttpServletResponse response,
                                    @RequestParam(value = "search") String search,
                                    @RequestParam(value = "section", defaultValue = "1") int section,
                                    @RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {

        ModelAndView modelAndView;
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        int startPage = ((section - 1) * 100) + ((pageNum - 1) * 10);

        Map<String, Object> wordMap = new HashMap<String, Object>();
        wordMap.put("search", search);
        wordMap.put("pageNum", pageNum);
        wordMap.put("section", section);
        wordMap.put("startPage", startPage);
        wordMap.put("selectMode", "like");
        wordMap.put("userId", userId);

        List<wordVO> wordVoItems;
        try {
            wordVoItems = wordservice.selectModifyWord(wordMap);
            int totalCount = wordservice.totalCount(wordMap);
            wordMap.remove("userId");
            wordMap.put("wordVoItems", wordVoItems);
            wordMap.put("totalCount", totalCount);
            modelAndView = new ModelAndView("/word/wordBoardForm");
            modelAndView.addObject("wordMap", wordMap);
        } catch (Exception e) {
            e.printStackTrace();
            modelAndView = new ModelAndView("common/error");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/modifyWordForm.do/{wordId}")
    public ModelAndView modifyWordForm(HttpServletRequest request, HttpServletResponse response, @PathVariable("wordId") int wordId) {
        ModelAndView modelAndView;
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");

        Map<String, Object> wordMap = new HashMap<String, Object>();
        wordMap.put("userId", userId);
        wordMap.put("wordId", wordId);

        try {
            wordVO _wordvo = wordservice.selectSpecificWord(wordMap);
            modelAndView = new ModelAndView("/word/modifyWordForm");
            modelAndView.addObject("wordvo", _wordvo);
        } catch (Exception e) {
            e.printStackTrace();
            modelAndView = new ModelAndView("common/error");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/modifyWord", method = RequestMethod.PUT)
    public ResponseEntity modifyWord(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String, Object> wordMap) {
        ResponseEntity responseEntity;
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        wordMap.put("userId", userId);
        try {
            wordservice.updateWord(wordMap);
            responseEntity = new ResponseEntity<String>("sucess", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<String>("error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/deleteWord", method = RequestMethod.DELETE)
    public ResponseEntity deleteWord(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String, Object> wordMap) {
        ResponseEntity responseEntity;
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        wordMap.put("userId", userId);
        try {
            wordservice.deleteWord(wordMap);
            responseEntity = new ResponseEntity<String>("success", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<String>("error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/reviewCardForm.do", method = RequestMethod.GET)
    @Override
    public ModelAndView reviewCardForm(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        ModelAndView modelAndView = new ModelAndView();
        wordVO _wordvo = null;
        Map<String, String> wordMap = new HashMap<String, String>();
        wordMap.put("userId", userId);
        wordMap.put("studyMode", "review");
        wordMap.put("selectState", "notEmpty");
        try {//틀린 카드부터 출력
            _wordvo = wordservice.selectRemainedReviewCard(wordMap);
            if (_wordvo == null) {
                _wordvo = wordservice.selectReviewCard(wordMap); //복습해야할 카드 출력
                if (_wordvo == null) {
                    wordMap.put("selectState", "empty");
                    _wordvo = wordservice.selectRemainedReviewCard(wordMap); //남은 틀린카드 출력
                }
            }
            modelAndView.addObject("wordvo", _wordvo);
            modelAndView.setViewName("/word/reviewCardForm");
            return modelAndView;

        } catch (Exception e) {
            e.printStackTrace();
            modelAndView = new ModelAndView();
            modelAndView.setViewName("/common/error");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/reviewStudy_wrong", method = RequestMethod.PUT)
    @Override
    public ResponseEntity reviewStudy_Wrong(HttpServletRequest request, HttpServletResponse response, @RequestBody Map wordMap) {
        HttpSession session = request.getSession();
        session.setAttribute("studyQuantity", 0);
        String userId = (String) session.getAttribute("userId");

        wordMap.put("userId", userId);
        wordMap.put("wordCount", 0);
        wordMap.put("appearanceDate", setTime(0));

        try {
            wordservice.updateWrongCard(wordMap);
            statisticservice.updateWrongCard(wordMap);
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
    @RequestMapping(value = "/reviewStudy", method = RequestMethod.GET)
    public ResponseEntity reviewCardSelect(HttpServletRequest request, HttpServletResponse response) {
        String message;
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.add("content-type", "text/html; charset=utf-8");
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        wordVO _wordvo;
        Map wordMap = new HashMap();
        wordMap.put("userId", userId);
        wordMap.put("selectState", "notEmpty");
        wordMap.put("studyMode", "review");

        try {
            _wordvo = wordservice.selectRemainedReviewCard(wordMap);
            if (_wordvo == null) {
                _wordvo = wordservice.selectReviewCard(wordMap); //복습해야할 카드 출력
                if (_wordvo == null) {
                    wordMap.put("selectState", "empty");
                    _wordvo = wordservice.selectRemainedReviewCard(wordMap); //남은 틀린카드 출력
                }
            }
            if (_wordvo != null) {
                return new ResponseEntity(_wordvo, HttpStatus.OK);
            }
            return new ResponseEntity<String>("Empty", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @RequestMapping(value = "/reviewStudy_appropriate", method = RequestMethod.PUT)
    @Override
    public ResponseEntity reviewStudy_appropriate(HttpServletRequest request, HttpServletResponse response, @RequestBody Map wordMap) {
        Map ResponseMap = new HashMap();
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        int increasedWordCount = Integer.parseInt((String) wordMap.get("wordCount")) + 1;
        wordMap.put("userId", userId);
        wordMap.put("appearanceDate", setTime(increasedWordCount));
        wordMap.put("wordCount", increasedWordCount);
        try {
            wordservice.updateReviewCard_Appropriate(wordMap);
            statisticservice.updateAppropriateCard(wordMap);
            ResponseMap.put("MESSAGE", "SUCESS");
            return new ResponseEntity(ResponseMap, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseMap.put("MESSAGE", "ERROR");
            return new ResponseEntity(ResponseMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/newCardForm.do", method = RequestMethod.GET)
    @Override
    public ModelAndView newCardForm(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        ModelAndView modelAndView = new ModelAndView();
        wordVO _wordvo = null;
        Map<String, String> wordMap = new HashMap<String, String>();
        wordMap.put("userId", userId);
        wordMap.put("studyMode", "newCardStudy");
        wordMap.put("selectState", "notEmpty");
        try {
            _wordvo = wordservice.selectRemainedNewCard(wordMap);
            if (_wordvo == null) {
                _wordvo = wordservice.selectNewCard(wordMap); //복습해야할 카드 출력
                if (_wordvo == null) {
                    wordMap.put("selectState", "empty");
                    _wordvo = wordservice.selectRemainedNewCard(wordMap); //남은 틀린카드 출력
                }
            }
            modelAndView.addObject("wordvo", _wordvo);
            modelAndView.setViewName("/word/newCardForm");
            return modelAndView;
        } catch (Exception e) {
            e.printStackTrace();
            modelAndView = new ModelAndView();
            modelAndView.setViewName("/common/error");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/newCardStudy_wrong", method = RequestMethod.PUT)
    @Override
    public ResponseEntity newCardStudy_wrong(HttpServletRequest request, HttpServletResponse response, @RequestBody Map wordMap) {

        HttpSession session = request.getSession();
        session.setAttribute("studyQuantity", 0);
        String userId = (String) session.getAttribute("userId");

        wordMap.put("userId", userId);
        wordMap.put("wordCount", 0);
        wordMap.put("appearanceDate", setTime(0));

        try {
            wordservice.updateWrongCard(wordMap);
            statisticservice.updateWrongCard(wordMap);
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

    @RequestMapping(value = "/newCardStudy_appropriate", method = RequestMethod.PUT)
    @Override
    public ResponseEntity newCardUpdate_appropriate(HttpServletRequest request, HttpServletResponse response, @RequestBody Map wordMap) {
        Map ResponseMap = new HashMap();
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        int increasedWordCount = Integer.parseInt((String) wordMap.get("wordCount")) + 1;
        wordMap.put("userId", userId);
        wordMap.put("appearanceDate", setTime(increasedWordCount));
        wordMap.put("wordCount", increasedWordCount);
        try {
            wordservice.updateNewCard_Appropriate(wordMap);
            statisticservice.updateAppropriateCard(wordMap);
            ResponseMap.put("MESSAGE", "SUCESS");
            return new ResponseEntity(ResponseMap, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseMap.put("MESSAGE", "ERROR");
            return new ResponseEntity(ResponseMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/newCardStudy", method = RequestMethod.GET)
    @Override
    public ResponseEntity newCardSelect(HttpServletRequest request, HttpServletResponse response) {
        String message;
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.add("content-type", "text/html; charset=utf-8");
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        wordVO _wordvo;
        Map wordMap = new HashMap();
        wordMap.put("userId", userId);
        wordMap.put("selectState", "notEmpty");
        wordMap.put("studyMode", "newCardStudy");
        try {
            _wordvo = wordservice.selectRemainedNewCard(wordMap);
            if (_wordvo == null) { // 시간에 맞는 틀린카드가 없다면
                _wordvo = wordservice.selectNewCard(wordMap); //새카드 출력
                if (_wordvo == null) {
                    wordMap.put("selectState", "empty");
                    _wordvo = wordservice.selectRemainedNewCard(wordMap); //새카드가 없다면 시간 관계없이 틀린카드 출력
                }
            }
            if (_wordvo != null) {
                return new ResponseEntity(_wordvo, HttpStatus.OK);
            }
            return new ResponseEntity<String>("Study_Done", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
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

    @RequestMapping(value = "papagoAPI/{word}", method = RequestMethod.GET)
    public ResponseEntity sendToPapagoAPI(HttpServletRequest request, HttpServletResponse response, @PathVariable("word") String word) {
        String clientId = "IqQYujxUWHOH54f7zT80";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "vP7xoeCWTA";//애플리케이션 클라이언트 시크릿값";
        try {
            String text = URLEncoder.encode(word, "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            // post request
            String postParams = "source=en&target=ko&text=" + text;
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer responseBuffer = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                responseBuffer.append(inputLine);
            }
            br.close();
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(responseBuffer.toString());
            jsonObject = (JSONObject) jsonParser.parse(jsonObject.get("message").toString());
            jsonObject = (JSONObject) jsonParser.parse(jsonObject.get("result").toString());
            return new ResponseEntity<Object>(jsonObject,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
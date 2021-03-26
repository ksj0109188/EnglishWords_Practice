package com.project.statistic.Controller;

import com.project.common.base.BaseController;
import com.project.statistic.service.statisticService;
import com.project.statistic.vo.statisticVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RequestMapping(value = "/statistic")
@RestController("statisticController")
public class statisticControllerImpl extends BaseController implements statisticController {

    @Autowired
    statisticService statisticService;

    @Autowired
    statisticVO statisticVO;

    @RequestMapping("/statisticForm.do")
    public ModelAndView statisticForm(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        Map<String, Object> staMap = new HashMap<>();
        staMap.put("userId", userId);
        ModelAndView modelAndView;
        try {
            int countWord = statisticService.countWord(staMap);
            staMap.remove("userId");
            staMap.put("countWord", countWord);
            modelAndView = new ModelAndView("/statistic/statisticForm");
            modelAndView.addObject("staMap", staMap);

        } catch (Exception e) {
            e.printStackTrace();
            modelAndView = new ModelAndView("/common/error");
        }
        return modelAndView;
    }

    @RequestMapping("/search/{word}")
    public ResponseEntity search(HttpServletRequest request, HttpServletResponse response, @PathVariable("word") String word) {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        Map staMap = new HashMap<>();
        staMap.put("userId", userId);
        staMap.put("word", word);
        ResponseEntity responseEntity;
        try {
            statisticVO statisticVO = statisticService.search(staMap);
            if (statisticVO == null) {
                responseEntity = new ResponseEntity<String>("Empty", HttpStatus.OK);
            } else {
                responseEntity = new ResponseEntity<statisticVO>(statisticVO, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<String>("error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}

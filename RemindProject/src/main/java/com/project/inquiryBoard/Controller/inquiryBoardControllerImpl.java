package com.project.inquiryBoard.Controller;

import com.project.common.base.BaseController;
import com.project.inquiryBoard.service.inquiryBoardService;
import com.project.inquiryBoard.vo.AnswerVO;
import com.project.inquiryBoard.vo.imageVO;
import com.project.inquiryBoard.vo.inquiryBoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "/board")
@Controller("inquiryBoardController")
public class inquiryBoardControllerImpl extends BaseController implements inquiryBoardController {

    @Autowired
    inquiryBoardService inquiryBoardService;

    @Autowired
    inquiryBoardVO inquiryBoardVO;

    @Autowired
    imageVO imageVO;

    @Autowired
    AnswerVO AnswerVO;

    @RequestMapping(value = "/boardForm.do")
    @Override
    public ModelAndView selectInquiryBoard(HttpServletRequest request, HttpServletResponse response) {
        Map inquiryBoardMap = new HashMap();
        List<inquiryBoardVO> inquiryBoardVO;
        try {
            inquiryBoardVO = inquiryBoardService.selectInquiryBoard(inquiryBoardMap);
            ModelAndView modelAndView = new ModelAndView("inquiryBoard/inquiryBoardForm");
            modelAndView.addObject("inquiryBoardVO", inquiryBoardVO);
            return modelAndView;
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("common/error");
        }

    }

    @RequestMapping(value = "/board/{boardId}", method = RequestMethod.GET)
    public ModelAndView selectBoardDetail(HttpServletRequest request, HttpServletResponse response, @PathVariable int boardId) {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        Map boardMap = new HashMap();
        boardMap.put("boardId",boardId);
        boardMap.put("userId",userId);
        inquiryBoardVO inquiryBoardVO;
        imageVO imageVO;
        List<AnswerVO> AnswerVO;
        try {
            inquiryBoardVO = inquiryBoardService.selectBoardDetail(boardMap);
            AnswerVO=inquiryBoardService.selectBoardAnswer(boardMap);
//            imageVO=inquiryBoardService.selectBoardImage(boardMap);
            ModelAndView modelAndView = new ModelAndView("inquiryBoard/detailBoardForm");
            modelAndView.addObject("inquiryBoardVO", inquiryBoardVO);
            modelAndView.addObject("AnswerVO",AnswerVO);
            return modelAndView;
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("common/error");
        }
    }
}
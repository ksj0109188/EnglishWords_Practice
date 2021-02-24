package com.project.member.controller;

import com.project.common.base.BaseController;
import com.project.member.service.MemberService;
import com.project.member.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController("MemberController")
@RequestMapping(value = "/member")
public class MemberControllerImpl extends BaseController implements MemberController {

    @Autowired
    MemberVO memberVO;

    @Autowired
    MemberService memberService;

    @RequestMapping(value = "/addMember.do", method = RequestMethod.POST)
    @Override
    public ResponseEntity addMember(HttpServletRequest request, HttpServletResponse response, @ModelAttribute MemberVO memberVO) {
        String Context = request.getContextPath();
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.add("content-type", "text/html; charset=utf-8");
        String message;
        try {
            request.setCharacterEncoding("utf-8");
            memberService.addMember(memberVO);
            message = "<script>";
            message += "alert('가입완료!!! 환영합니다!');";
            message += "location.href='" + Context + "/main/main.do';";
            message += "</script>";
            return new ResponseEntity(message, responseHeader, HttpStatus.CREATED);
        } catch (Exception e) {
            message = "<script>";
            message += "alert('회원가입 실패');";
            message += "location.href='" + Context + "/member/registerMemberForm.do';";
            message += "</script>";
            return new ResponseEntity(message, responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    @Override
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, String> memberMap) {
        ModelAndView modelAndView = new ModelAndView();
        String userId=memberService.loginMember(memberMap);
        String message;
        if(userId!=null){
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("userId",userId);
            httpSession.setAttribute("isLogin",true);
            modelAndView.setViewName("/main/mainContent");
        }else{
            message = "login fail";
            modelAndView.addObject("message",message);
            modelAndView.setViewName("/main/main");
        }
        return modelAndView;
    }
}
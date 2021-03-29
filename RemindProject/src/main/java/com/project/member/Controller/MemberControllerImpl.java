package com.project.member.Controller;

import com.project.common.base.BaseController;
import com.project.common.mail.mailService;
import com.project.member.service.MemberService;
import com.project.member.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController("MemberController")
@RequestMapping(value = "/member")
public class MemberControllerImpl extends BaseController implements MemberController {

    @Autowired
    MemberVO memberVO;

    @Autowired
    MemberService memberService;

    @Autowired
    mailService mailService;


    @RequestMapping(value = "/addMember", method = RequestMethod.POST)
    @Override
    public ModelAndView addMember(HttpServletRequest request, HttpServletResponse response, @ModelAttribute MemberVO memberVO) throws Exception {
        ModelAndView modelAndView;
        try {
            String AuthKey = mailService.getKey(false, 20);
            memberVO.setAuthKey(AuthKey);
            memberService.addMember(memberVO);
            mailService.setMail(AuthKey, memberVO.getUserId(), memberVO.getEmail(), request.getContextPath());
            modelAndView = new ModelAndView("/main/main");
            modelAndView.addObject("message", "registerSuccess");
        } catch (Exception e) {
            e.printStackTrace();
            modelAndView = new ModelAndView("/common/error");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/overlap")
    public ResponseEntity searchOverlapId (HttpServletRequest request, HttpServletResponse response, @RequestParam("userId") String userId){
        ResponseEntity responseEntity ;
        try {
            int userIdCount = memberService.searchOverlapId(userId);
            if (userIdCount == 0) {
                responseEntity = new ResponseEntity<String>("notOverlapping", HttpStatus.OK);
            }else{
                responseEntity = new ResponseEntity<String>("Overlapping", HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
            responseEntity = new ResponseEntity<String>("error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/authMember.do")
    public ModelAndView authMember(HttpServletRequest request, HttpServletResponse response, @RequestParam("userId") String userId, @RequestParam("authKey") String authKey) {
        Map<String, Object> memberMap = new HashMap<String, Object>();
        ModelAndView modelAndView;
        try {
            memberMap.put("userId", userId);
            memberMap.put("authKey", authKey);
            String savedAuthKey = memberService.selectAuthKey(memberMap);
            if (authKey.equals(savedAuthKey)) {
                modelAndView = new ModelAndView("/member/authMember");
                memberService.updateAuthKey(memberMap);
            } else {
                modelAndView = new ModelAndView("/main/main");
                modelAndView.addObject("message", "authFalse");
            }
        } catch (Exception e) {
            e.printStackTrace();
            modelAndView = new ModelAndView("/common/error");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @Override
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, String> memberMap) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        MemberVO membervo = memberService.loginMember(memberMap);
        if (membervo != null) {
            if (!membervo.getAuthKey().equals("Y")) {
                modelAndView = new ModelAndView("/main/main");
                modelAndView.addObject("message", "notAuthorization");
                return modelAndView;
            } else {
                String userId = membervo.getUserId();
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("userId", userId);
                httpSession.setAttribute("isLogin", true);
                modelAndView.setViewName("/main/mainContent");
            }
        } else {
            modelAndView.addObject("message", "loginFail");
            modelAndView.setViewName("/main/main");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("/main/main");
        HttpSession session = request.getSession();
        Object userId = session.getAttribute("userId");
        if (userId != null) {
            session.removeAttribute("userId");
        }
        return modelAndView;
    }
}
package com.project.member.Controller;

import com.project.member.vo.MemberVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface MemberController {
    ModelAndView addMember(HttpServletRequest request, HttpServletResponse response, @ModelAttribute MemberVO memberVO) throws Exception;

    ModelAndView authMember(HttpServletRequest request, HttpServletResponse response, @RequestParam("userId") String userId, @RequestParam("authKey") String authKey);

    ResponseEntity searchOverlapId(HttpServletRequest request, HttpServletResponse response, @RequestParam("userId") String userId);

    ModelAndView login(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, String> memberMap) throws Exception;

    ModelAndView logout(HttpServletRequest request, HttpServletResponse response);

    ModelAndView findUserId(HttpServletRequest request, HttpServletResponse response, @ModelAttribute MemberVO memberVO);

    ResponseEntity findUserPwd(HttpServletRequest request, HttpServletResponse response, @ModelAttribute MemberVO memberVO);
}

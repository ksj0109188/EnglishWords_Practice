package com.project.member.controller;

import com.project.member.vo.MemberVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface MemberController {
    ResponseEntity addMember(HttpServletRequest request, HttpServletResponse response, @ModelAttribute MemberVO memberVO )  throws Exception;
    ModelAndView login(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, String> memberMap) throws Exception;
}

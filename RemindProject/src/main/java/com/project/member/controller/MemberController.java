package com.project.member.controller;

import com.project.member.vo.MemberVO;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MemberController {
    void addMember(HttpServletRequest request, HttpServletResponse response, @ModelAttribute MemberVO memberVO );
}

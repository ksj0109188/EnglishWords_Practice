package com.project.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MemberController {
    void addMember(HttpServletRequest request, HttpServletResponse response);
}

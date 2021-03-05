package com.project.member.service;

import com.project.member.vo.MemberVO;

import java.util.Map;

public interface MemberService {

    void addMember(MemberVO memberVO) throws Exception;
    String loginMember(Map<String,String> memberMap) throws Exception;
}

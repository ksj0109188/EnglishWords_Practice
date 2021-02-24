package com.project.member.dao;

import com.project.member.vo.MemberVO;

import java.util.Map;

public interface MemberDAO {
    void addMember(MemberVO memberVO);
    String loginMember(Map<String, String> memberMap);
}

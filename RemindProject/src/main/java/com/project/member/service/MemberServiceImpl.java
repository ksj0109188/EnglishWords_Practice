package com.project.member.service;

import com.project.member.dao.MemberDAO;
import com.project.member.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberDAO memberDAO;

    @Override
    public void addMember(MemberVO memberVO) throws Exception {
        memberDAO.addMember(memberVO);
    }

    @Override
    public String loginMember(Map<String, String> memberMap) throws Exception {
        return memberDAO.loginMember(memberMap);
    }
}

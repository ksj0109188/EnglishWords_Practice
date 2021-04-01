package com.project.member.service;

import com.project.member.dao.MemberDAO;
import com.project.member.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
@Transactional(propagation = Propagation.REQUIRED)
@Service("memberService")
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberDAO memberDAO;

    @Override
    public void addMember(MemberVO memberVO) throws Exception {
        memberDAO.addMember(memberVO);
    }

    @Override
    public MemberVO loginMember(Map<String, String> memberMap) throws Exception {
        return memberDAO.loginMember(memberMap);
    }

    @Override
    public String selectAuthKey(Map<String, Object> memberMap) throws Exception {
        return memberDAO.selectAuthKey(memberMap);
    }

    @Override
    public void updateAuthKey(Map<String, Object> memberMap) {
        memberDAO.updateAuthKey(memberMap);
    }

    @Override
    public String checkAuthEmail(Map<String, String> memberMap) throws Exception {
        return memberDAO.checkAuthEmail(memberMap);
    }

    @Override
    public int searchOverlapId(String userId) throws Exception {
        return memberDAO.searchOverlapId(userId);
    }

    @Override
    public List<MemberVO> findUserId(MemberVO memberVO) throws Exception {
        return memberDAO.findUserId(memberVO);
    }

    @Override
    public MemberVO findUserPwd(MemberVO memberVO) throws Exception {
        return memberDAO.findUserPwd(memberVO);
    }

    @Override
    public void updateUserPwd(MemberVO memberVO) throws Exception {
        memberDAO.updateUserPwd(memberVO);
    }
}

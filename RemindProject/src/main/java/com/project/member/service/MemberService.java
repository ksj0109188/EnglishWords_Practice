package com.project.member.service;

import com.project.member.vo.MemberVO;

import java.util.List;
import java.util.Map;

public interface MemberService {
    void addMember(MemberVO memberVO) throws Exception;

    MemberVO loginMember(Map<String, String> memberMap) throws Exception;

    String selectAuthKey(Map<String, Object> memberMap) throws Exception;

    void updateAuthKey(Map<String, Object> memberMap) throws Exception;

    String checkAuthEmail(Map<String, String> memberMap) throws Exception;

    int searchOverlapId(String userId) throws Exception;

    List<MemberVO> findUserId(MemberVO memberVO) throws Exception;

    MemberVO findUserPwd(MemberVO memberVO) throws Exception;

    void updateUserPwd(MemberVO memberVO) throws Exception;

    MemberVO selectMemberInfo(Map<String, String> memberMap) throws Exception;

    void modifyMember(MemberVO memberVO) throws Exception;

    void deleteMember(Map<String, String> memberMap) throws Exception;
}

package com.project.member.dao;

import com.project.member.vo.MemberVO;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;

public interface MemberDAO {
    void addMember(MemberVO memberVO) throws DataAccessException;

    MemberVO loginMember(Map<String, String> memberMap) throws DataAccessException;

    String selectAuthKey(Map<String, Object> memberMap) throws DataAccessException;

    void updateAuthKey(Map<String, Object> memberMap) throws DataAccessException;

    String checkAuthEmail(Map<String, String> memberMap) throws DataAccessException;

    int searchOverlapId(String userId) throws DataAccessException;

    List<MemberVO> findUserId(MemberVO memberVO) throws DataAccessException;

    MemberVO findUserPwd(MemberVO memberVO) throws DataAccessException;

    void updateUserPwd(MemberVO memberVO) throws DataAccessException;

    MemberVO selectMemberInfo(Map<String, String> memberMap)throws DataAccessException;

    void modifyMember(MemberVO memberVO) throws DataAccessException;

    void deleteMember(Map<String, String> memberMap) throws DataAccessException;
}

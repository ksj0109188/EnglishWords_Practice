package com.project.member.dao;

import com.project.member.vo.MemberVO;
import org.springframework.dao.DataAccessException;

import java.util.Map;

public interface MemberDAO {
    void addMember(MemberVO memberVO) throws DataAccessException;

    MemberVO loginMember(Map<String, String> memberMap) throws DataAccessException;

    String selectAuthKey(Map<String, Object> memberMap) throws DataAccessException;

    void updateAuthKey(Map<String, Object> memberMap) throws DataAccessException;

    String checkAuthEmail(Map<String, String> memberMap) throws DataAccessException;

    int searchOverlapId(String userId) throws DataAccessException;
}

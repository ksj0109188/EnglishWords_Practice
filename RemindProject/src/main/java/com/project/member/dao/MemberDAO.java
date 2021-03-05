package com.project.member.dao;

import com.project.member.vo.MemberVO;
import org.springframework.dao.DataAccessException;

import java.util.Map;

public interface MemberDAO {
    void addMember(MemberVO memberVO) throws DataAccessException;

    String loginMember(Map<String, String> memberMap) throws DataAccessException;
}

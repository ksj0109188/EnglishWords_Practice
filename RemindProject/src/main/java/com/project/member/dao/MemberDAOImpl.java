package com.project.member.dao;

import com.project.member.vo.MemberVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO {
    @Autowired
    SqlSession sqlSession;

    @Override
    public void addMember(MemberVO memberVO) throws DataAccessException {
        sqlSession.insert("mapper.member.addMember", memberVO);
    }

    @Override
    public String loginMember(Map<String, String> memberMap) {
        return sqlSession.selectOne("mapper.member.login", memberMap);
    }
}

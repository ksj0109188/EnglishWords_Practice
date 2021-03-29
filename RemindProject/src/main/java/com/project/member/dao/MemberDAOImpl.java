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
    public MemberVO loginMember(Map<String, String> memberMap)throws DataAccessException {
        return sqlSession.selectOne("mapper.member.login", memberMap);
    }

    @Override
    public String selectAuthKey(Map<String, Object> memberMap) throws DataAccessException {
        return sqlSession.selectOne("mapper.member.selectAuthKey",memberMap);
    }

    @Override
    public void updateAuthKey(Map<String, Object> memberMap) {
        sqlSession.update("mapper.member.updateAuthKey",memberMap);
    }

    @Override
    public String checkAuthEmail(Map<String, String> memberMap) throws DataAccessException {
        return sqlSession.selectOne("mapper.member.checkAuthEmail",memberMap);
    }

    @Override
    public int searchOverlapId(String userId) throws DataAccessException {
        return sqlSession.selectOne("mapper.member.searchOverlapId",userId);
    }
}

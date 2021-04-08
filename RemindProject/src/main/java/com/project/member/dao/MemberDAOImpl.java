package com.project.member.dao;

import com.project.member.vo.MemberVO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;
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

    @Override
    public List<MemberVO> findUserId(MemberVO memberVO) throws DataAccessException {
        return sqlSession.selectList("mapper.member.findUserId",memberVO);
    }

    @Override
    public MemberVO findUserPwd(MemberVO memberVO) throws DataAccessException {
        return sqlSession.selectOne("mapper.member.findUserPwd",memberVO);
    }

    @Override
    public void updateUserPwd(MemberVO memberVO) throws DataAccessException {
        sqlSession.update("mapper.member.updateUserPwd",memberVO);
    }

    @Override
    public MemberVO selectMemberInfo(Map<String, String> memberMap) throws DataAccessException {
        return sqlSession.selectOne("mapper.member.selectMemberInfo",memberMap);
    }

    @Override
    public void modifyMember(MemberVO memberVO) throws DataAccessException {
        sqlSession.update("mapper.member.modifyMember",memberVO);
    }

    @Override
    public void deleteMember(Map<String, String> memberMap) throws DataAccessException {
        sqlSession.delete("mapper.member.deleteMember",memberMap);
    }
}

package com.sehyeon.sportspsychology.DAO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sehyeon.sportspsychology.bean.LoginVO;
import com.sehyeon.sportspsychology.mapper.Loginmapper;

@Repository
public class LoginDAOImpl implements Loginmapper {
	@Autowired
	SqlSession sqlSession;

	@Override
	public String loginCheck(LoginVO vo) {
		return sqlSession.selectOne("login.login_check",vo);
	}

	@Override
	public LoginVO findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
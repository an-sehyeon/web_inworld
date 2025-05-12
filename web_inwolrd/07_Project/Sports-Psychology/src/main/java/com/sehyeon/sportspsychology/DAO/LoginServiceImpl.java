package com.sehyeon.sportspsychology.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sehyeon.sportspsychology.bean.LoginVO;
import com.sehyeon.sportspsychology.mapper.LogService;
import com.sehyeon.sportspsychology.mapper.Loginmapper;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class LoginServiceImpl implements LogService {

	@Autowired
	Loginmapper loginmapper;
	public String loginCheck(LoginVO vo, HttpSession session) {
		
		// 전달된 값 확인
		log.info("로그인 요청 - username: " + vo.getUsername() + ", password: " + vo.getPassword());
		
		// Mapper 호출
		String name = loginmapper.loginCheck(vo);
		
		// Mapper 결과 확인
		if(name != null) {
			log.info("로그인 성공 - username: " + vo.getUsername() + ", name: " + name);
			session.setAttribute("username", vo.getUsername());
			session.setAttribute("name", name);
			return name; 	// 로그인 성공
		}
		log.info("로그인 실패: 아이디 또는 비밀번호 불일치");
		return null;		// 로그인 실패
	}


	public void logout(HttpSession session) {
		session.invalidate();
	}
}

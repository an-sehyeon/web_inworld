package com.sehyeon.psychology.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

// 로그인 관련 요청을 처리하는 컨트롤러

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LoginController {

	@GetMapping("/login")
	public String loginP(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		// 로그인하지 않은 상태에서 id를 'anonymousUser'로 설정
		String id = "anonymousUser";
		
		if(authentication != null && authentication.isAuthenticated() &&
		   !authentication.getPrincipal().equals("anonymousUser")) {
			
			//  Spring Security에서 로그인한 사용자 정보 가져오기
            Object principal = authentication.getPrincipal();


            id = authentication.getName();
        }
		
		
		model.addAttribute("id", id);  // 'id' 값을 모델에 추가)
		
		return "login";		// login.html로 이동
	}
}

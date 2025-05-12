package com.sehyeon.psychology.controller;

// 관리자 관련 기능을 처리하는 컨트롤러
// 추후 관리자 페이지에서 회원 관리, 상담 관리 등을 수행 기능 추가


import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AdminController {

	@GetMapping("/admin")
	public String adminP(Model model) {
		// 현재 로그인한 사용자 정보 가져오기
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        String id = authentication.getName(); // 사용자 아이디

	        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
	        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
	        
	       String role = ""; 
	       if (iter.hasNext()) {
	           role = iter.next().getAuthority(); // 사용자 권한
	        }

	        // Model에 추가하여 Thymeleaf에서 사용 가능하게 설정
	        model.addAttribute("id", id);
	        model.addAttribute("role", role);

	        log.info("================================");
	        log.info("Admin Page Accessed by {}", id);
	        
	        return "admin";  // admin.html로 이동
	}
}

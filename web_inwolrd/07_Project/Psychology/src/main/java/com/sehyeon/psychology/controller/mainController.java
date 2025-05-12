package com.sehyeon.psychology.controller;

// 홈페이지 및 기본적인 페이지 이동을 담당하는 컨트롤러

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/Psychology/*")
@Slf4j
public class mainController {

	@GetMapping("main")
	public String mainP(Model model) {
		
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> iter = authorities.iterator();
		GrantedAuthority auth = iter.next();
		String role = auth.getAuthority();
		
		model.addAttribute("id", id);
		model.addAttribute("role", role);
		
		log.info("================================");
		log.info("Get Main Called");
		return "main";
	}
}

package com.sehyeon.psychology.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PsychologyTestController {

    @GetMapping("/psychologyTest")
    public String showPsychologyTestPage(Model model) {
    	// 현재 로그인한 사용자 정보 가져오기
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String username = authentication.getName(); 	// 로그인한 사용자 ID 가져오기
    	
    	if(username.equals("anonymousUser")) {
    		model.addAttribute("id", null);		// 로그아웃 상태면 id를 null로 설정
    	} else {
    		model.addAttribute("id", username);	// 로그인 상태면 ID를 model에 추가
    	}
    	
        return "psychologyTest"; 
    }
}

package com.sehyeon.psychology.controller;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sehyeon.psychology.entity.PsychologyTestResultEntity;
import com.sehyeon.psychology.repository.PsychologyTestResultRepository;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AdminController {

    private final PsychologyTestResultRepository testResultRepository;

    // 생성자 주입 방식 사용
    public AdminController(PsychologyTestResultRepository testResultRepository) {
        this.testResultRepository = testResultRepository;
    }

    // 기존 관리자 페이지 유지
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

    // 새로운 기능: 사용자 테스트 결과 검색
    @GetMapping("/admin/search")
    public String searchTestResults(@RequestParam String username, Model model) {
        log.info("Searching test results for user: {}", username);

        // 입력한 username을 기준으로 결과 조회
        List<PsychologyTestResultEntity> results = testResultRepository.findByUser_Username(username);
        model.addAttribute("results", results);
        model.addAttribute("searchUsername", username);

        return "admin-results"; // 검색 결과를 보여줄 HTML 페이지
    }
}

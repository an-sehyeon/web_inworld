package com.sehyeon.psychology.controller;


//회원가입 관련 요청을 처리하는 컨트롤러
//회원가입 폼을 보여주고, 입력 데이터를 JoinService를 통해 저장

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sehyeon.psychology.dto.JoinCreateForm;
import com.sehyeon.psychology.repository.UserRepository;
import com.sehyeon.psychology.service.JoinService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class JoinController {

	private final JoinService joinService;
	private final UserRepository userRepository;

	public JoinController(JoinService joinService, UserRepository userRepository) {
	    this.joinService = joinService;
	    this.userRepository = userRepository;
	}

	// 회원가입 페이지 이동
    @GetMapping("/join")
    public String joinP(Model model, HttpSession session) {
    	
    	// 현재 SecurityContext에서 인증 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	
        // 기본값은 "anonymousUser"
        String id = "anonymousUser";

        // 인증 정보가 있고, 사용자 이름이 "anonymousUser"이면 그대로 유지하고, 
        // 로그인된 경우만 id 값을 사용자 이름으로 설정한다.
        if (authentication != null && authentication.isAuthenticated() && 
            !authentication.getPrincipal().equals("anonymousUser")) {
            id = authentication.getName();		// 로그인한 사용자 ID 가져오기
        }

        // Model에 id 값 저장
        model.addAttribute("id", id);
        model.addAttribute("joinCreateForm", new JoinCreateForm());
    	
        return "join";
    }
    
    

    // 회원가입 처리
    @PostMapping("/joinProc")
    public String joinProc(@ModelAttribute("joinCreateForm") @Valid JoinCreateForm joinCreateForm, 
    						BindingResult bindingResult, Model model,
    						HttpServletRequest request, HttpSession session,
    						RedirectAttributes redirectAttributes) {
    	
    	log.info("===========회원가입 요청 발생!===========");

    	
    	// 중복된 아이디 체크
    	if (userRepository.existsByUsername(joinCreateForm.getUsername())) {
            bindingResult.rejectValue("username", "username.exists", "이미 존재하는 아이디입니다.");
        }

        // 비밀번호 일치 여부 체크
    	if (!joinCreateForm.getPassword1().equals(joinCreateForm.getPassword2())) {
            bindingResult.rejectValue("password2", "password.mismatch", "비밀번호가 일치하지 않습니다.");
        }

        // 생년월일 형식 체크 (예: yyyy-MM-dd 형식이어야 한다고 가정)
    	if (!joinCreateForm.getBirthDate().matches("^(19|20)\\d{2}-?(0[1-9]|1[0-2])-?(0[1-9]|[12][0-9]|3[01])$")) {
            bindingResult.rejectValue("birthDate", "birthDate.invalid", "생년월일 형식이 올바르지 않습니다. (예: 1999-12-31)");
        }

    	// target 값 유효성 검사
        if (!joinCreateForm.getTarget().equals("선수") && !joinCreateForm.getTarget().equals("학부모") && !joinCreateForm.getTarget().equals("지도자")) {
            bindingResult.rejectValue("target", "target.invalid", "대상은 '선수', '학부모', '지도자' 중 하나여야 합니다.");
        }
        
        // 유효성 검사 실패 시 다시 회원가입 페이지로 이동
        if (bindingResult.hasErrors()) {
            model.addAttribute("joinCreateForm", joinCreateForm);
            log.info("=============유효성 검사 실패!=============");
            return "join";
        }    
        
        
        // 이메일 중복 검사, 회원가입 진행
        String result = joinService.registerUser(joinCreateForm);

        if ("exist".equals(result)) {
        	log.warn("이메일 중복으로 회원가입 실패: {}", joinCreateForm.getEmail());
            bindingResult.rejectValue("email", "email.exists", "이미 존재하는 이메일입니다.");
            return "join";
        } 

        if ("error".equals(result)) {
            log.error("회원가입 처리 중 예외 발생");
            model.addAttribute("errorMessage", "회원가입 중 오류가 발생했습니다. 다시 시도해주세요.");
            return "join";
        }
        
        log.info("회원가입 성공: {}", joinCreateForm.getUsername());
        
        
        redirectAttributes.addFlashAttribute("successMessage", "회원가입이 성공적으로 완료되었습니다.");
    	
    	// SecurityContext 완전 초기화
        // 현재 로그인한 사용자의 인증 정보를 제거하여 로그아웃 샅애로 만듦
        SecurityContextHolder.getContext().setAuthentication(null);
        // 현재 세션의 인증 정보를 삭제
        SecurityContextHolder.clearContext();

        // 기존 세션 삭제 후, 새로운 세션을 만들지 않음, 회원가입 후 자동 로그인 방지.
        session.invalidate();

        
        // Model에도 anonymousUser 설정
        model.addAttribute("id", "anonymousUser");

        log.info("현재 사용자 인증 정보 (초기화 후) : {}", SecurityContextHolder.getContext().getAuthentication());

        return "redirect:/login";			// 회원가입 성공 시 로그인 페이지로 이동
     
    }
    
    // 실시간 중복 아이디 체크 (AJAX 요청 처리)
    @GetMapping("/checkUsername")
    @ResponseBody
    public boolean checkUsername(@RequestParam String username) {
    	return userRepository.existsByUsername(username);
    }
    
}

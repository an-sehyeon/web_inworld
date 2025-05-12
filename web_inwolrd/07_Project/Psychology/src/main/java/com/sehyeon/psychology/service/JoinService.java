package com.sehyeon.psychology.service;

// 회원가입 관련 비즈니스 로직을 처리하는 서비스
// UserRepository를 통해 사용자 데이터를 저장

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sehyeon.psychology.dto.JoinCreateForm;
import com.sehyeon.psychology.entity.JoinEntity;
import com.sehyeon.psychology.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class JoinService {
	
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public String registerUser(JoinCreateForm joinCreateForm) {
    	try {
    		// 이메일 중복 확인
    		if (userRepository.findByEmail(joinCreateForm.getEmail()).isPresent()) {
    			log.warn("중복된 이메일 입력, 회원가입 실패: {}",joinCreateForm.getEmail());
    			return "exist";		// 이메일 중복 시 "exist" 반환
    		}
    		
    		// 역할 설정 (admin 제외)
    		String role = joinCreateForm.getUsername().equals("admin") ? "ROLE_ADMIN" : "ROLE_USER";
    		joinCreateForm.setRole(role); 			// role 값 설정 추가
    		
    		// JoinCreateForm을 JoinEntity로 변환 후 저장
    		JoinEntity joinEntity = JoinEntity.builder()
    				.username(joinCreateForm.getUsername())
                    .password(bCryptPasswordEncoder.encode(joinCreateForm.getPassword1())) // 비밀번호 암호화
                    .name(joinCreateForm.getName())
                    .birth_date(joinCreateForm.getBirthDate())
                    .phone(joinCreateForm.getPhone())
                    .address(joinCreateForm.getAddress())
                    .affiliation(joinCreateForm.getAffiliation())
                    .email(joinCreateForm.getEmail())
                    .target(joinCreateForm.getTarget())
                    .role("ROLE_USER") // role 설정
                    .build();
    		
    		userRepository.save(joinEntity);
    		log.info("회원가입 성공: {}", joinCreateForm.getUsername());
    		
    		return "success";
    	} catch (DataIntegrityViolationException e) {
    		log.error("중복된 이메일 입력으로 회원가입 실패: {}", joinCreateForm.getEmail());
    		return "exist";
    	} catch (Exception e) {
    		log.error("회원가입 중 오류 발생: {}", e.getMessage());
    		return "error";		// 예외 발생 시 "error" 반환
    	}
    }
    
    
    // 사용자 중복 체크(존재 여부 확인)
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
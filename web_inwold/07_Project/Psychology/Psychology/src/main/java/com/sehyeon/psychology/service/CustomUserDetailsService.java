package com.sehyeon.psychology.service;

import java.util.Optional;

// Spring Security에서 사용자 인증을 담당하는 서비스
// 로그인 시 사용자 정보를 조회하여 인증을 수행


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sehyeon.psychology.dto.CustomUserDetails;
import com.sehyeon.psychology.entity.JoinEntity;
import com.sehyeon.psychology.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
    	this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	Optional<JoinEntity> user = Optional.ofNullable(userRepository.findByUsername(username));

        if (user.isEmpty()) {
            log.warn("사용자를 찾을 수 없음: {}", username);
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username);
        }

        JoinEntity userEntity = user.get();
        
        // 로그 추가하여 사용자 정보 확인
        log.info("로그인 성공! 사용자 아이디: {}", userEntity.getUsername());
        log.info("사용자 이름: {}", userEntity.getName());  // 사용자 이름 확인
        log.info("사용자 역할(Role): {}", userEntity.getRole()); // Role 정보도 확인

        return new CustomUserDetails(userEntity); // 정상적으로 CustomUserDetails로 변환하여 반환
    }
}

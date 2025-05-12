package com.sehyeon.psychology.config;

// Spring Security 설정 담당 클래스
// 로그인 및 접근 권한 관리, 비밀번호 암호화 설정

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public BCryptPasswordEncoder bCryptPasswordDEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		
		http
			.authorizeHttpRequests((auth) -> auth
					.requestMatchers("/", "/login", "/loginProc", "/join", "/joinProc", "/Styles/**", "/board/list", "css/**").permitAll()		// permitAll : 해당 경로는 인증을 하지 않아도 접근 가능
					.requestMatchers("/Psychology/main").permitAll()
					.requestMatchers("/login").anonymous()							// 로그인한 사용자는 /login 접근 불가
					.requestMatchers("/admin").hasRole("ADMIN")						// hasRole : "ADMIN" 권한이 있는 사용자만 접근 가능
					.requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")			// /mypage/를 포함한 하위 경로 전체에 대해 "ADMIN", "USER" 권한을 가진 사용자만 접근 가능
					.anyRequest().authenticated()									// anyRequest().authenticated() : 위에서 설정한 특정 url을 제외한 나머지 요청은 로그인해야 접근 가능 
			)
			.formLogin(auth -> auth
		            .loginPage("/login") 							// 로그인 페이지를 LoginController에서 처리하도록 변경
		            .loginProcessingUrl("/loginProc") 				// 로그인 처리 URL
		            .defaultSuccessUrl("/Psychology/main", true) 	// 로그인 성공 후 이동할 페이지
		            .failureUrl("/login?error=true")				// 로그인 실패 시 이동할 URL (에러 메시지 출력)
		            .permitAll()
		        )
			.exceptionHandling(auth -> auth
					.authenticationEntryPoint((request, response, authException) -> {
						// 비로그인 사용자가 보호된 페이지에 접근 시, login 페이지로 이동하면서 메시지 전달
						response.sendRedirect("/login?error=loginRequired");
					})
			);
		 http
	        .logout((auth) -> auth
	        		.logoutUrl("/logout")
	                .logoutSuccessUrl("/login?logout=true")  // 로그아웃 후 logout=troe 전달
	                .invalidateHttpSession(true) // 세션 무효화
	                .deleteCookies("JSESSIONID") // 쿠키 삭제
	        );
		
			
		
		
//		http
//			.csrf((auth) -> auth.disable());
		
		
		http
			.csrf(csrf -> csrf.disable())			// CSRF 보호를 비활성화하여 로그인하지 않은 사용자의 접근 허용
			.sessionManagement(auth -> auth.sessionFixation().changeSessionId());
		
		
		http
			.sessionManagement((auth) -> auth
			.maximumSessions(1)						// 하나의 아이디에 대한 다중 로그인 허용 개수 설정
			.maxSessionsPreventsLogin(true));		// 다중 로그인 개수를 초과하였을 경우 처리 방법
													// true : 초과시 새로운 로그인 차단, false : 초과시 기존 세션 하나 삭제
		
		http
			.sessionManagement((auth) -> auth
//			.sessionFixation().none()				// 로그인 시 세션 정보 변경 안함
//			.sessionFixation().newSession()			// 로그인 시 세션 새로 생성
			.sessionFixation().changeSessionId()	// 로그인 시 동일한 세션에 대한 id 변경
			);	
			
		
		return http.build();
	}
}

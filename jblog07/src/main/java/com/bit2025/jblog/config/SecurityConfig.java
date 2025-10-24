package com.bit2025.jblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.DefaultHttpFirewall;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	/**
	 * - WebSecurity 레벨의 전역 보안 설정
	 * - 필터 체인이 실행되기 이전 단계에서 적용
	 */
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		// 기본 HTTP Firewall 정책 적용(중복 슬래시(//), 경로 탐색 문자(/../) 처리)
		return webSecurity -> webSecurity.httpFirewall(new DefaultHttpFirewall());
	}

	/**
	 * - HttpSecurity 레벨의 요청 단위 보안 설정
	 * - 인증/인가 절차, 로그인/로그아웃, 예외 처리 설정
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			// 실제 서비스에선 활성화
			.csrf(csrf -> csrf.disable())
			.formLogin(login -> {
				login
					// 사용자 정의 로그인 페이지 사용 (GET /user/login)
					.loginPage("/user/login")
					// 로그인 요청 처리 URL (POST /user/auth)
					.loginProcessingUrl("/user/auth")
					// 로그인 폼에서 아이디/비밀번호 파라미터명 지정
					.usernameParameter("blogId")
					.passwordParameter("password")
					// 로그인 성공 시 메인 페이지로 리다이렉트
					.defaultSuccessUrl("/");
			})
			.logout(logout -> {
				logout
					// 로그아웃 요청 처리 URL (GET /user/logout)
					.logoutUrl("/user/logout")
					// 로그아웃 성공 시 메인 페이지로 리다이렉트
					.logoutSuccessUrl("/");
			})
			/**
			 * URL 접근 제어 (인가 정책)
			 */
			.authorizeHttpRequests(request -> {
				request
					// 나머지 모든 요청 허용
					.anyRequest().permitAll();
			});
		
		// SecurityFilterChain 객체를 빌드하여 Spring Container에 등록
		return http.build();
	}
	
	/**
	 * PasswordEncoder 빈 등록
	 * - Spring Security에서 비밀번호를 암호화하기 위해 PasswordEncoder 구현체를 빈으로 등록
	 * - 회원가입 시 비밀번호 암호화, 사용자 비밀번호 검증에 사용
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(4);
	}
	
}

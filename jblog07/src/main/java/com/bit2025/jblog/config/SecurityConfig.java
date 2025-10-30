package com.bit2025.jblog.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.firewall.DefaultHttpFirewall;

import com.bit2025.jblog.repository.UserRepository;
import com.bit2025.jblog.security.AjaxAuthenticationFailureHandler;
import com.bit2025.jblog.security.AjaxAuthenticationSuccessHandler;
import com.bit2025.jblog.security.UserDetailsServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * SecurityFilterChain 사용자 인증 과정
 * ===
 * 로그인 요청(POST /user/auth)
 * - 1. FilterChain -> AuthenticationManager 인증 수행
 * - 2. AuthenticationProvider가 인증 처리
 * 		> UserDetailsService.loadUserByUsername()으로 사용자 정보 조회 -> UserDetails 객체 반환
 * 		> UserDetails의 비밀번호와 폼에 입력한 비밀번호 비교
 * - 3. 인증에 성공하면 SecurityContextHolder에 인증 정보(Authentication 객체) 저장
 * - 4. 메인 페이지로 리다이렉트
 */

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
	 * SecurityFilterChain 빈 등록
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
						.usernameParameter("blogId").passwordParameter("password")
						// AJAX용 success/failure handler
						.successHandler(new AjaxAuthenticationSuccessHandler())
						.failureHandler(new AjaxAuthenticationFailureHandler());
				}).logout(logout -> {
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
				})
				/**
				 * 예외 처리 설정
				 * - 사용자가 권한이 없는 URL에 접근할 경우 AccessDeniedException 발생
				 * - AccessDeniedHandler를 통해 로그인 페이지로 리다이렉트하도록 설정
				 */
				.exceptionHandling(ex -> {
					ex.accessDeniedHandler(new AccessDeniedHandler() {

						@Override
						public void handle(HttpServletRequest request, HttpServletResponse response,
								AccessDeniedException accessDeniedException) throws IOException, ServletException {
							response.sendRedirect(request.getContextPath() + "/user/login");
						}

					});
				});

		// SecurityFilterChain 객체를 빌드하여 Spring Container에 등록
		return http.build();
	}

	/**
	 * AuthenticationManager 빈 등록
	 * - AuthenticationConfiguration을 통해 Spring Security가 등록한
	 * 	 UserDetailsService와 PasswordEncoder를 자동으로 연결
	 * - 로그인 요청 시 AuthenticationManager가 호출되어 인증 수행
	 * - 내부적으로 DaoAuthenticationProvider를 사용,
	 * 	 UserDetailsServiceImpl.loadUserByUsername()이 호출됨
	 */
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
		
		/*
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);

		return new ProviderManager(authenticationProvider);
		*/
	}
	
	/**
	 * UserDetailsService 빈 등록
	 * - UserRepository를 사용하여 DB에서 사용자 정보 조회
	 * - 로그인 요청 시 AuthenticationProvider가 사용자를 검증할 때 호출됨
	 * - 반환된 UserDetailsImpl 객체는 AuthenticationProvider가 인증에 사용
	 */
	@Bean
	public UserDetailsService userDetailsService(UserRepository userRepository) {
		return new UserDetailsServiceImpl(userRepository);
	}
	
	/**
	 * PasswordEncoder 빈 등록 
	 * - 회원가입 시 비밀번호 암호화, 로그인 시 입력 비밀번호 검증에 사용
	 * - BCrypt 알고리즘 적용, strength 4
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(4);
	}

}

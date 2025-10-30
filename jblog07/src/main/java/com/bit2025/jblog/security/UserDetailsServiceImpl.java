package com.bit2025.jblog.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bit2025.jblog.repository.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	// 생성자 주입
	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/**
	 * - 로그인 요청 시 username(blogId)을 기준으로 사용자 조회
	 * - 반환된 UserDetails 객체는 AuthenticationManager가 인증에 사용
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails user = userRepository.findById(username, UserDetailsImpl.class);

		if (user == null) {
			throw new UsernameNotFoundException("사용자 '" + username + "'을(를) 찾을 수 없습니다.");
		}

		return user;
	}

}

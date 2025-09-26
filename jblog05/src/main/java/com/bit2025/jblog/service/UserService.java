package com.bit2025.jblog.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bit2025.jblog.repository.UserRepository;
import com.bit2025.jblog.vo.UserVo;

@Service
public class UserService {

	private UserRepository userRepository;

	private PasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	// join transaction
	public void addUser(UserVo vo) {
		// 1. 회원가입
		// password encoding
		String encodedPassword = passwordEncoder.encode(vo.getPassword());
		vo.setPassword(encodedPassword);
		
		userRepository.insert(vo);
		
		// 2. 회원 블로그 생성
		
		// 3. 회원 블로그 기본 카테고리 설정
	}

}

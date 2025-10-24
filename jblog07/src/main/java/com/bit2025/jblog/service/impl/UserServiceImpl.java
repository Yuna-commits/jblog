package com.bit2025.jblog.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bit2025.jblog.domain.UserVo;
import com.bit2025.jblog.repository.UserRepository;
import com.bit2025.jblog.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	// 생성자 주입
	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	/**
	 * 회원가입 처리
	 * - 비밀번호 암호화 후 저장
	 */
	@Override
	public void addUser(UserVo vo) {
		String encodedPassword = passwordEncoder.encode(vo.getPassword());
		vo.setPassword(encodedPassword);

		userRepository.insertUser(vo);
	}

	/**
	 * 사용자 조회
	 * - 로그인, 아이디 중복 체크 등에서 사용
	 * - 제네릭 findById 사용
	 */
	@Override
	public UserVo getUser(String blogId) {
		return userRepository.findById(blogId, UserVo.class);
	}

}

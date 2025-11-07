package com.bit2025.jblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bit2025.jblog.domain.UserVo;
import com.bit2025.jblog.facade.UserJoinFacade;

import jakarta.validation.Valid;

/**
 * 회원 관련 요청 처리 컨트롤러
 * ===
 * 1. 회원가입
 * 2. 로그인
 * 3. 로그아웃
 */
@Controller
@RequestMapping("/user")
public class UserController {

	private final UserJoinFacade joinService;

	// 생성자 주입 (스프링이 JoinFacadeService 빈 주입)
	public UserController( UserJoinFacade joinService) {
		this.joinService = joinService;
	}

	/**
	 * GET /user/join 
	 * - 회원가입 페이지로 이동
	 * - @ModelAttribute : 스프링이 자동으로 비어있는 UserVo 객체를 모델에 담아 뷰로 전달
	 */
	@GetMapping("/join")
	public String join(@ModelAttribute UserVo vo) {
		return "user/join";
	}

	/**
	 * POST /user/join
	 * - 회원가입 처리
	 * - @Valid : UserVo의 필드 유효성 검사
	 * - BindingResult : 유효성 검사 결과 저장
	 * ===
	 * 1. 최초 GET 요청 시에는 빈 UserVo가 생성
	 * 2. 사용자가 폼을 제출하면, 폼의 데이터가 UserVo 필드에 자동으로 바인딩
	 * 3. @Valid 검사에서 에러가 있으면 같은 UserVo를 모델에 담아 다시 join.html 렌더링
	 */
	@PostMapping("/join")
	public String join(@ModelAttribute @Valid UserVo vo, BindingResult result, Model model) {
		// 유효성 검사 실패 시
		if (result.hasErrors()) {
			model.addAttribute("userVo", vo);
			return "user/join";
		}

		// 회원가입 트랜잭션
		joinService.joinUser(vo);
		
		return "redirect:/user/joinsuccess";
	}

	/**
	 * GET /user/joinsuccess
	 * - 회원가입 완료 페이지로 이동
	 */
	@GetMapping("/joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess";
	}

	/**
	 * GET /user/login
	 * - 로그인 페이지로 이동
	 */
	@GetMapping("/login")
	public String login() {
		return "user/login";
	}
}

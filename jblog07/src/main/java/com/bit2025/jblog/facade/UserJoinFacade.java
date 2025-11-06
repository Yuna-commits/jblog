package com.bit2025.jblog.facade;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit2025.jblog.domain.BlogVo;
import com.bit2025.jblog.domain.CategoryVo;
import com.bit2025.jblog.domain.UserVo;
import com.bit2025.jblog.service.BlogService;
import com.bit2025.jblog.service.CategoryService;
import com.bit2025.jblog.service.UserService;

import jakarta.validation.Valid;

@Service
public class UserJoinFacade {

	// 인터페이스 기반 의존(DIP)
	private final UserService userService;
	private final BlogService blogService;
	private final CategoryService categoryService;

	public UserJoinFacade(UserService userService, BlogService blogService, CategoryService categoryService) {
		this.userService = userService;
		this.blogService = blogService;
		this.categoryService = categoryService;
	}

	/**
	 * 회원가입 처리를 위한 Facade 메서드
	 * ===
	 * User -> Blog -> Category 순서로 데이터 생성
	 * @Transactional : 하나의 트랜잭션으로 관리
	 * 
	 * - UserService : 사용자 정보 저장
	 * - BlogService : 개인 블로그 기본 정보 생성
	 * - CategoryService : 기본 카테고리 생성
	 * 
	 * @param vo : 회원가입 정보를 담은 UserVo
	 */
	@Transactional
	public void joinUser(@Valid UserVo vo) {
		// 사용자 등록
		userService.addUser(vo);

		// 블로그 생성
		BlogVo defaultBlog = new BlogVo();
		defaultBlog.setBlogId(vo.getBlogId());
		defaultBlog.setTitle(vo.getName() + "의 블로그");
		defaultBlog.setProfile("spring-logo.jpg");
		
		blogService.addBlog(defaultBlog);

		// 기본 카테고리 생성
		CategoryVo defaultCategory = new CategoryVo();
		defaultCategory.setBlogId(vo.getBlogId());
		defaultCategory.setName("미분류"); // 기본 카테고리
		defaultCategory.setDescription("기본 카테고리입니다.");
		
		categoryService.addCategory(defaultCategory);
	}

}

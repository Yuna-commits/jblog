package com.bit2025.jblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bit2025.jblog.dto.BlogViewDto;
import com.bit2025.jblog.facade.BlogViewFacade;

/**
 * - 정적 리소스 요청 경로와 /{blogId} 매핑이 충돌할 수 있음
 * 	 ex: /css/jblog.css -> blogId="css"로 인식
 * 
 * - 정규표현식으로 css, js, images로 시작하는 경로는 매핑에서 제외
 */
@Controller
@RequestMapping("/{blogId:^(?!css|js|images|user|api).*$}")
public class BlogController {

	private final BlogViewFacade viewBlog;
	
	public BlogController(BlogViewFacade viewBlog) {
		this.viewBlog = viewBlog;
	}

	/**
	 * 블로그 메인 페이지 조회
	 * ===
	 * /{blogId}?categoryId={}&postId={}&page={}
	 * 
	 * - categoryId == null -> 블로그 전체 게시글 목록 + 최신 게시글 표시
	 * 		&& postId != null -> 특정 게시글 표시
	 * - categoryId != null
	 * 		&& postId == null -> 해당 카테고리의 최신 게시글 표시
	 * 		&& postId != null -> 해당 카테고리의 특정 게시글 표시
	 */
	@GetMapping({ "", "/" })
	public String view(Model model,
			@PathVariable("blogId") String blogId,
			@RequestParam(value = "categoryId", required = false) Integer categoryId,
			@RequestParam(value = "postId", required = false) Integer postId,
			@RequestParam(value = "page", defaultValue = "1") int page) {

		BlogViewDto dto = viewBlog.getBlogView(blogId, categoryId, postId, page);

		model.addAttribute("blogView", dto);
		model.addAttribute("hasCategory", (categoryId != null));
		model.addAttribute("newLineChar", "\n");

		return "blog/view";
	}
	
}

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
	 * URL 패턴
	 * - GET /{blogId}/ : 블로그 메인 페이지(최신 게시글 + 전체 게시글 목록)
	 * - GET /{blogId}/{categoryId} : 특정 카테고리의 게시글 목록(최신 게시글 + 전체 게시글 목록)
	 * - GET /{blogId}/{categoryId}/{postId} : 특정 게시글 상세보기(전체 게시글 목록)
	 * 
	 * 컨트롤러에서 요청 경로를 해석,
	 * 블로그 조회에 필요한 정보는 BlogViewFacade에 위임해서 얻음
	 */
	@GetMapping({ "", "/", "/{categoryId}", "/{categoryId}/{postId}"})
	public String view(Model model,
			@PathVariable("blogId") String blogId,
			@PathVariable(value = "categoryId", required = false) Integer categoryId,
			@PathVariable(value = "postId", required = false) Integer postId,
			@RequestParam(value = "page", defaultValue = "1") int page) {
		
		BlogViewDto dto = viewBlog.getBlogView(blogId, categoryId, postId, page);
		
		model.addAttribute("blogView", dto);
		model.addAttribute("newLineChar", "\n");
		
		System.out.println(dto);
		
		return "blog/view";
	}
	
}

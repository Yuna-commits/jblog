package com.bit2025.jblog.facade;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bit2025.jblog.domain.BlogVo;
import com.bit2025.jblog.domain.CategoryVo;
import com.bit2025.jblog.domain.PostVo;
import com.bit2025.jblog.dto.BlogViewDto;
import com.bit2025.jblog.dto.PostPageDto;
import com.bit2025.jblog.service.BlogService;
import com.bit2025.jblog.service.CategoryService;
import com.bit2025.jblog.service.PostService;

@Service
public class BlogViewFacade {

	private final BlogService blogService;
	private final CategoryService categoryService;
	private final PostService postService;

	public BlogViewFacade(BlogService blogService, CategoryService categoryService, PostService postService) {
		this.blogService = blogService;
		this.categoryService = categoryService;
		this.postService = postService;
	}
	
	/**
	 * 개인 블로그 통합 조회 Facade 메서드
	 * - 블로그 화면에 표시할 모든 데이터(블로그 정보, 카테고리, 게시글, 페이징)를 조합, 반환
	 * ===
	 * 동작 방식
	 * - blogId로 블로그 기본 정보, 카테고리 목록 조회
	 * 
	 * - categoryId의 유무에 따라 게시글 목록 범위 결정
	 * 	 - null -> 블로그 전체 게시글 목록
	 * 	 - 값 존재 -> 해당 카테고리의 게시글 목록
	 * 
	 * - postId의 유무에 따라 표시할 게시글 선택
	 * 	 - null -> 최신 게시글(블로그, 카테고리)
	 * 	 - 값 존재 -> 지정된 게시글의 상세 정보
	 * ===
	 * @param blogId : 블로그 식별자
	 * @param categoryId : 선택된 카테고리(optional)
	 * @param postId : 선택된 게시글(optional)
	 * @param page : 페이지 번호(default : 1)
	 * @return BlogViewDto : 블로그 화면 데이터 통합 DTO
	 */
	public BlogViewDto getBlogView(String blogId, Integer categoryId, Integer postId, int page) {
		// 개인 블로그 조회
		BlogVo blogInfo = blogService.getBlog(blogId);

		// 등록된 카테고리 목록 조회
		List<CategoryVo> categories = categoryService.getCategories(blogId);

		// 게시글
		PostVo selectedPost = (postId != null) 
						? postService.getPost(postId) // 선택된 게시글
						: postService.getLatestPost(blogId, categoryId); // 최신 게시글
		
		// 서비스에서 categoryId 유무에 따라 조회할 목록의 범위(전체, 카테고리) 판단
		PostPageDto postPage = postService.getPosts(blogId, categoryId, page);

		return new BlogViewDto(blogInfo, categories, selectedPost, postPage);
	}
}

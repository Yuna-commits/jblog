package com.bit2025.jblog.dto;

import java.util.List;

import com.bit2025.jblog.domain.BlogVo;
import com.bit2025.jblog.domain.CategoryVo;
import com.bit2025.jblog.domain.PostVo;

/**
 * 블로그 화면 표시에 필요한 데이터만 전달
 */
public class BlogViewDto {

	private BlogVo blogInfo;
	private List<CategoryVo> categories;
	private PostVo selectedPost; // 현재 표시 중인 게시글
	private PostPageDto postPage; // 페이징 적용 게시글 목록

	public BlogViewDto(BlogVo blogInfo, List<CategoryVo> categories, PostVo selectedPost, PostPageDto postPage) {
		this.blogInfo = blogInfo;
		this.categories = categories;
		this.selectedPost = selectedPost;
		this.postPage = postPage;
	}

	public BlogVo getBlogInfo() {
		return blogInfo;
	}

	public List<CategoryVo> getCategories() {
		return categories;
	}

	public PostVo getSelectedPost() {
		return selectedPost;
	}
	
	public PostPageDto getPostPage() {
		return postPage;
	}

	@Override
	public String toString() {
		return "BlogViewDto [blogInfo=" + blogInfo + ", categories=" + categories + ", selectedPost=" + selectedPost
				+ ", postPage=" + postPage + "]";
	}

}

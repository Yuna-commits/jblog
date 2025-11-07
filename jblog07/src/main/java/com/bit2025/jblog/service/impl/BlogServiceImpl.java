package com.bit2025.jblog.service.impl;

import org.springframework.stereotype.Service;

import com.bit2025.jblog.domain.BlogVo;
import com.bit2025.jblog.domain.UserVo;
import com.bit2025.jblog.repository.BlogRepository;
import com.bit2025.jblog.service.BlogService;

import jakarta.validation.Valid;

@Service
public class BlogServiceImpl implements BlogService {

	private final BlogRepository blogRepository;

	// 생성자 주입
	public BlogServiceImpl(BlogRepository blogRepository) {
		this.blogRepository = blogRepository;
	}
	
	// 블로그 생성
	@Override
	public void createDefaultBlog(@Valid UserVo vo) {
		BlogVo defaultBlog = new BlogVo();
		
		defaultBlog.setBlogId(vo.getBlogId());
		defaultBlog.setTitle(vo.getName() + "의 블로그");
		defaultBlog.setProfile("spring-logo.jpg");
		
		blogRepository.insertBlog(defaultBlog);
	}

	// 개인 블로그 조회
	@Override
	public BlogVo getBlog(String blogId) {
		return blogRepository.findByBlogId(blogId);
	}

}

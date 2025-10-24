package com.bit2025.jblog.service.impl;

import org.springframework.stereotype.Service;

import com.bit2025.jblog.domain.BlogVo;
import com.bit2025.jblog.repository.BlogRepository;
import com.bit2025.jblog.service.BlogService;

@Service
public class BlogServiceImpl implements BlogService {

	private final BlogRepository blogRepository;

	// 생성자 주입
	public BlogServiceImpl(BlogRepository blogRepository) {
		this.blogRepository = blogRepository;
	}

	@Override
	public void addBlog(BlogVo vo) {
		blogRepository.insertBlog(vo);
	}
}

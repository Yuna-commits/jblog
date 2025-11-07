package com.bit2025.jblog.service;

import com.bit2025.jblog.domain.BlogVo;
import com.bit2025.jblog.domain.UserVo;

import jakarta.validation.Valid;

public interface BlogService {

	void createDefaultBlog(@Valid UserVo vo);

	BlogVo getBlog(String blogId);
	
}

package com.bit2025.jblog.service;

import com.bit2025.jblog.domain.BlogVo;

public interface BlogService {

	void addBlog(BlogVo vo);

	BlogVo getBlog(String blogId);
	
}

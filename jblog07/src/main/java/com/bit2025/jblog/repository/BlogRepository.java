package com.bit2025.jblog.repository;

import com.bit2025.jblog.domain.BlogVo;

public interface BlogRepository {

	int insertBlog(BlogVo vo);

	BlogVo findByBlogId(String blogId);
	
}

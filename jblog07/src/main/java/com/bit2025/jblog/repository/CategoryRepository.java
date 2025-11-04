package com.bit2025.jblog.repository;

import java.util.List;

import com.bit2025.jblog.domain.CategoryVo;

public interface CategoryRepository {

	int insertCategory(CategoryVo vo);

	List<CategoryVo> findCategoriesByBlogId(String blogId);
	
}

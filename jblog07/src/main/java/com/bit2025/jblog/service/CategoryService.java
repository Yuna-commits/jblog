package com.bit2025.jblog.service;

import java.util.List;

import com.bit2025.jblog.domain.CategoryVo;

public interface CategoryService {

	void addCategory(CategoryVo vo);

	List<CategoryVo> getCategories(String blogId);
	
}

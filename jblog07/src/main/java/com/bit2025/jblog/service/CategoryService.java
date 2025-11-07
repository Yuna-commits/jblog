package com.bit2025.jblog.service;

import java.util.List;

import com.bit2025.jblog.domain.CategoryVo;
import com.bit2025.jblog.domain.UserVo;

import jakarta.validation.Valid;

public interface CategoryService {

	void addCategory(CategoryVo vo);

	void createDefaultCategory(@Valid UserVo vo);

	List<CategoryVo> getCategories(String blogId);
	
}

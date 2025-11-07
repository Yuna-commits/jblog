package com.bit2025.jblog.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bit2025.jblog.domain.CategoryVo;
import com.bit2025.jblog.domain.UserVo;
import com.bit2025.jblog.repository.CategoryRepository;
import com.bit2025.jblog.service.CategoryService;

import jakarta.validation.Valid;

@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;

	// 생성자 주입
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	// 기본 카테고리 등록
	@Override
	public void createDefaultCategory(@Valid UserVo vo) {
		CategoryVo defaultCategory = new CategoryVo();
		
		defaultCategory.setBlogId(vo.getBlogId());
		defaultCategory.setName("미분류"); // 기본 카테고리
		defaultCategory.setDescription("기본 카테고리입니다.");
		
		categoryRepository.insertCategory(defaultCategory);
	}

	// 카테고리 등록
	@Override
	public void addCategory(CategoryVo vo) {
		categoryRepository.insertCategory(vo);
	}

	// 등록된 카테고리 목록 조회
	@Override
	public List<CategoryVo> getCategories(String blogId) {
		return categoryRepository.findCategoriesByBlogId(blogId);
	}

}

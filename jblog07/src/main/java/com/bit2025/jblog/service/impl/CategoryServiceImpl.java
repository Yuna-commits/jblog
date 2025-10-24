package com.bit2025.jblog.service.impl;

import org.springframework.stereotype.Service;

import com.bit2025.jblog.domain.CategoryVo;
import com.bit2025.jblog.repository.CategoryRepository;
import com.bit2025.jblog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;

	// 생성자 주입
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public void addCategory(CategoryVo vo) {
		categoryRepository.insertCategory(vo);
	}

}

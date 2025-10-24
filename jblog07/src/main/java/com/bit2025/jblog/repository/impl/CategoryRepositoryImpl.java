package com.bit2025.jblog.repository.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bit2025.jblog.domain.CategoryVo;
import com.bit2025.jblog.repository.CategoryRepository;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

	private final SqlSession sqlSession;

	// 생성자 주입
	public CategoryRepositoryImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public int insertCategory(CategoryVo vo) {
		return sqlSession.insert("category.insertCategory", vo);
	}

}

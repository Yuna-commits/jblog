package com.bit2025.jblog.repository.impl;

import java.util.List;

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

	// 카테고리 등록
	@Override
	public int insertCategory(CategoryVo vo) {
		return sqlSession.insert("category.insertCategory", vo);
	}

	// 특정 블로그에 등록된 카테고리 조회
	@Override
	public List<CategoryVo> findCategoriesByBlogId(String blogId) {
		return sqlSession.selectList("category.findCategoriesByBlogId", blogId);
	}

}

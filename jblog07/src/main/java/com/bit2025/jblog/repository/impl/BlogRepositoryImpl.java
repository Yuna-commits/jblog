package com.bit2025.jblog.repository.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bit2025.jblog.domain.BlogVo;
import com.bit2025.jblog.repository.BlogRepository;

@Repository
public class BlogRepositoryImpl implements BlogRepository {

	private final SqlSession sqlSession;

	// 생성자 주입
	public BlogRepositoryImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public int insertBlog(BlogVo vo) {
		return sqlSession.insert("blog.insertBlog", vo);
	}

}

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

	// 블로그 등록
	@Override
	public int insertBlog(BlogVo vo) {
		return sqlSession.insert("blog.insertBlog", vo);
	}

	// 블로그 조회
	@Override
	public BlogVo findByBlogId(String blogId) {
		return sqlSession.selectOne("blog.findByBlogId", blogId);
	}

}

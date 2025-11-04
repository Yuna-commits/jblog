package com.bit2025.jblog.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bit2025.jblog.domain.PostVo;
import com.bit2025.jblog.repository.PostRepository;

@Repository
public class PostRepositoryImpl implements PostRepository {

	private final SqlSession sqlSession;

	public PostRepositoryImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	// 특정 게시글 조회
	@Override
	public PostVo findByPostId(Integer postId) {
		return sqlSession.selectOne("post.findByPostId", postId);
	}

	// 최신 게시글 조회(블로그 전체 or 카테고리)
	@Override
	public PostVo findLatestPost(String blogId, Integer categoryId) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("blogId", blogId);
		map.put("categoryId", categoryId);
		
		return sqlSession.selectOne("post.findLatestPost", map);
	}

	// 게시글 목록 조회(블로그 전체 or 카테고리)
	@Override
	public List<PostVo> findPosts(String blogId, Integer categoryId, int offset, int pageSize) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("blogId", blogId);
		map.put("categoryId", categoryId);
		map.put("offset", offset);
		map.put("pageSize", pageSize);

		return sqlSession.selectList("post.findPosts", map);
	}

	// 총 게시글 수(블로그 전체 or 카테고리)
	@Override
	public int countPosts(String blogId, Integer categoryId) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("blogId", blogId);
		map.put("categoryId", categoryId);
		
		return sqlSession.selectOne("post.countPosts", map);
	}

}

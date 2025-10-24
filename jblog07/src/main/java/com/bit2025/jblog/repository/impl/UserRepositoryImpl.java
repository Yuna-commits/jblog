package com.bit2025.jblog.repository.impl;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bit2025.jblog.domain.UserVo;
import com.bit2025.jblog.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class UserRepositoryImpl implements UserRepository {

	private final SqlSession sqlSession;
	private final ObjectMapper objectMapper = new ObjectMapper(); // 재사용

	// 생성자 주입
	public UserRepositoryImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	/**
	 * 회원가입
	 */
	@Override
	public int insertUser(UserVo vo) {
		return sqlSession.insert("user.insertUser", vo);
	}

	/**
	 * 사용자 조회
	 * - 제네릭 사용 : 로그인, Ajax 중복 체크 재사용
	 */
	@Override
	public <R> R findById(String blogId, Class<R> resultType) {
		Map<String, Object> map = sqlSession.selectOne("user.findById", blogId);

		if(map == null) return null;
		
		return objectMapper.convertValue(map, resultType);
	}

}

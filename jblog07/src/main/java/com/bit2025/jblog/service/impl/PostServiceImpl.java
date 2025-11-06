package com.bit2025.jblog.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bit2025.jblog.domain.PostVo;
import com.bit2025.jblog.dto.PostPageDto;
import com.bit2025.jblog.repository.PostRepository;
import com.bit2025.jblog.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	private final PostRepository postRepository;
	private static final int PAGE_SIZE = 5;

	public PostServiceImpl(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	// 특정 게시글 조회
	@Override
	public PostVo getPost(Integer postId) {
		return postRepository.findByPostId(postId);
	}

	// 최신 게시글 조회
	@Override
	public PostVo getLatestPost(String blogId, Integer categoryId) {
		return postRepository.findLatestPost(blogId, categoryId);
	}

	// 통합 게시글 조회 메서드
	@Override
	public PostPageDto getPosts(String blogId, Integer categoryId, int page) {
		int offset = (page - 1) * PAGE_SIZE;

		List<PostVo> posts = postRepository.findPosts(blogId, categoryId, offset, PAGE_SIZE);
		int totalCount = postRepository.countPosts(blogId, categoryId);

		return new PostPageDto(posts, offset, PAGE_SIZE, totalCount);
	}

}

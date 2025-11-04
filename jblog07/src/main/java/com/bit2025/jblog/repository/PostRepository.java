package com.bit2025.jblog.repository;

import java.util.List;

import com.bit2025.jblog.domain.PostVo;

public interface PostRepository {

	PostVo findByPostId(Integer postId);

	PostVo findLatestPost(String blogId, Integer categoryId);
	
	List<PostVo> findPosts(String blogId, Integer categoryId, int offset, int pageSize);

	int countPosts(String blogId, Integer categoryId);

}

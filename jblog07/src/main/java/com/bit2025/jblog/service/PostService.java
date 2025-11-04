package com.bit2025.jblog.service;

import com.bit2025.jblog.domain.PostVo;
import com.bit2025.jblog.dto.PostPageDto;

public interface PostService {

	PostVo getPost(Integer postId);

	PostVo getLatestPost(String blogId, Integer categoryId);
	
	PostPageDto getPosts(String blogId, Integer categoryId, int page);

}

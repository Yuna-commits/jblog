package com.bit2025.jblog.service;

import com.bit2025.jblog.domain.UserVo;

public interface UserService {

	void addUser(UserVo vo);

	UserVo getUser(String blogId);
	
}

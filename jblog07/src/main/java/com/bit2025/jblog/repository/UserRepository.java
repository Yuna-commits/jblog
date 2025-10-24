package com.bit2025.jblog.repository;

import com.bit2025.jblog.domain.UserVo;

public interface UserRepository {

	int insertUser(UserVo vo);

	<R> R findById(String blogId, Class<R> class1);

}

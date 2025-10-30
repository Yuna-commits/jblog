package com.bit2025.jblog.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bit2025.jblog.domain.UserVo;

// UserVo를 Spring Security가 이해할 수 있는 객체로 변환
public class UserDetailsImpl extends UserVo implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.emptyList();
	}

	@Override
	public String getPassword() {
		return super.getPassword();
	}

	@Override
	public String getUsername() {
		return super.getBlogId();
	}

}

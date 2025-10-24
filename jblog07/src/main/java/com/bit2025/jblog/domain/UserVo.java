package com.bit2025.jblog.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserVo {

	/**
	 * @NotBlank : null, "", " " 허용 x
	 */
	@NotBlank(message = "아이디는 필수 입력입니다.")
	@Size(max = 50, message = "아이디는 50자 이하로 입력해주세요.")
	private String blogId;

	@NotBlank(message = "이름은 필수 입력입니다.")
	@Size(max = 45, message = "이름은 45자 이하로 입력해주세요.")
	private String name;

	@NotBlank(message = "비밀번호는 필수 입력입니다.")
	@Size(min = 4, max = 64, message = "비밀번호는 4 ~ 64자 이내로 입력해주세요.")
	private String password;

	public String getBlogId() {
		return blogId;
	}

	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserVo [blogId=" + blogId + ", name=" + name + ", password=" + password + "]";
	}

}

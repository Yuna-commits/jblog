package com.bit2025.jblog.domain;

import jakarta.validation.constraints.NotBlank;

public class BlogVo {

	private String blogId; // user id

	@NotBlank(message = "블로그 이름은 필수 입력입니다.")
	private String title;

	@NotBlank(message = "블로그 이미지는 필수 입력입니다.")
	private String profile; // URL

	public String getBlogId() {
		return blogId;
	}

	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	@Override
	public String toString() {
		return "BlogVo [blogId=" + blogId + ", title=" + title + ", profile=" + profile + "]";
	}

}

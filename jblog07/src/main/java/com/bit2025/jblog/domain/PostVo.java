package com.bit2025.jblog.domain;

import jakarta.validation.constraints.NotBlank;

public class PostVo {

	private Integer postId;
	
	@NotBlank(message = "게시글 제목은 필수 입력입니다.")
	private String title;
	
	@NotBlank(message = "게시글 내용은 필수 입력입니다.")
	private String contents;
	private String regDate;

	private Integer categoryId;
	private String categoryName;

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "PostVo [postId=" + postId + ", title=" + title + ", contents=" + contents + ", regDate=" + regDate
				+ ", categoryId=" + categoryId + ", categoryName=" + categoryName + "]";
	}

}

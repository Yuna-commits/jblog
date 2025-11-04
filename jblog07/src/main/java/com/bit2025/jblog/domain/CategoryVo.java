package com.bit2025.jblog.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryVo {

	private Integer categoryId;

	@NotBlank(message = "카테고리는 필수 입력입니다.")
	private String name;

	@Size(max = 200, message = "카테고리 설명은 200자 이내로 입력해주세요.")
	private String description;

	private String blogId; // user id

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBlogId() {
		return blogId;
	}

	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}

	@Override
	public String toString() {
		return "CategoryVo [categoryId=" + categoryId + ", name=" + name + ", description=" + description + ", blogId="
				+ blogId + "]";
	}

}

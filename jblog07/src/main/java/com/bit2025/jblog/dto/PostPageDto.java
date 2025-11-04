package com.bit2025.jblog.dto;

import java.util.List;

import com.bit2025.jblog.domain.PostVo;

public class PostPageDto {
	
	private List<PostVo> posts; // 현재 페이지의 게시글 목록
	private int offset; // 게시글 조회 시작점
	private int pageSize; // 한 페이지에 보일 게시글의 수(5)
	private int totalCount; // 전체 게시글 수
	private int totalPages; // 전체 페이지 수

	public PostPageDto(List<PostVo> posts, int offset, int pageSize, int totalCount) {
		this.posts = posts;
		this.offset = offset;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.totalPages = (int) Math.ceil((double) totalCount / pageSize);
	}

	// 현재 페이지 번호
	public int getCurrentPage() {
		return (offset / pageSize) + 1;
	}

	// 다음 페이지 존재 여부
	public boolean hasNextPage() {
		return (getCurrentPage() < totalPages);
	}

	// 이전 페이지 존재 여부
	public boolean hasPrevPage() {
		return (getCurrentPage() > 1);
	}

	public List<PostVo> getPosts() {
		return posts;
	}

	public int getOffset() {
		return offset;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getTotalPages() {
		return totalPages;
	}

	@Override
	public String toString() {
		return "PostPageDto [posts=" + posts + ", offset=" + offset + ", pageSize=" + pageSize + ", totalCount="
				+ totalCount + ", totalPages=" + totalPages + "]";
	}

}

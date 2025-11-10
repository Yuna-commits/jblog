package com.bit2025.jblog.dto;

import java.util.List;

import com.bit2025.jblog.domain.PostVo;

public class PostPageDto {

	private List<PostVo> posts; // 현재 페이지의 게시글 목록
	private int pageSize; // 한 페이지에 보일 게시글의 수(5)
	private int totalCount; // 전체 게시글 수
	private int totalPages; // 전체 페이지 수
	private int currentPage; // 현재 페이지

	private static final int PAGE_GROUP_SIZE = 5; // 한 페이지에 보일 페이지 번호 개수

	public PostPageDto(List<PostVo> posts, int offset, int pageSize, int totalCount) {
		this.posts = posts;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.currentPage = (offset / pageSize) + 1;
		this.totalPages = (int) Math.ceil((double) totalCount / pageSize);
	}

	/**
	 * 시작 페이지 번호 계산 
	 * ex) 1~5, 6~10, ...
	 */
	public int getStartPage() {
		return ((currentPage - 1) / PAGE_GROUP_SIZE) * PAGE_GROUP_SIZE + 1;
	}

	// 마지막 페이지 번호 계산(전체 페이지 수를 초과하지 않도록 제한)
	public int getEndPage() {
		return Math.min(totalPages, getStartPage() + PAGE_GROUP_SIZE - 1);
	}

	// 다음 그룹 존재 여부
	public boolean hasNextGroup() {
		return (getEndPage() < totalPages);
	}

	// 이전 그룹 존재 여부
	public boolean hasPrevGroup() {
		return (getStartPage() > 1);
	}

	public List<PostVo> getPosts() {
		return posts;
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

	public int getCurrentPage() {
		return currentPage;
	}

	@Override
	public String toString() {
		return "PostPageDto [posts=" + posts + ", pageSize=" + pageSize + ", totalCount=" + totalCount + ", totalPages="
				+ totalPages + ", currentPage=" + currentPage + "]";
	}
}

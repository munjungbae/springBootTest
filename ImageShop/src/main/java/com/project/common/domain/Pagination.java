package com.project.common.domain;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class Pagination {
	private int totalCount; 			// 전체 게시글 수
	private int startPage; 				// 시작 페이지 (시작페이지 11)
	private int endPage; 				// 끝 페이지 (끝 페이지 20)
	private boolean prev; 				// 이전글 목록 (1페이지가 아닐시에만 true)
	private boolean next; 				// 다음글 목록 (끝페이지가 아닐시에만 true)
	private int displayPageNum = 10; 	// 보여줄 페이지수
	private PageRequest pageRequest;	// 현재페이지,페이지사이즈 (현재페이지 12)

	public void setPageRequest(PageRequest pageRequest) {
		this.pageRequest = pageRequest;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
	}

	private void calcData() {
		//endPage = (ceil(12 / 10.0)) * 10 => 20page 
		// 현재페이지가 12페이지 일 때 endPage = (int) (Math.ceil(pageRequest.getPage() / (double) displayPageNum) * displayPageNum); => endPage = (int) (Math.ceil(1.2) * 10 ) => 2*10 => endPage = 20
		endPage = (int) (Math.ceil(pageRequest.getPage() / (double) displayPageNum) * displayPageNum);
		// startPage = ( 20 - 10 ) + 1; = 11
		// startPage ~ endPage => 11 ~ 20 page
		startPage = (endPage - displayPageNum) + 1;
		// 567 / 10(한페이지 보여질 수) => 57페이지
		int tempEndPage = (int) (Math.ceil(totalCount / (double) pageRequest.getSizePerPage()));
		if (endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		prev = startPage == 1 ? false : true;
		next = ((endPage * pageRequest.getSizePerPage()) >= totalCount) ? false : true;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public boolean isNext() {
		return next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public PageRequest getPageRequest() {
		return pageRequest;
	}

	// UriComponents=> ?page=5&perPageNum=10
	public String makeQuery(int page) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance().queryParam("page", page)
				.queryParam("sizePerPage", pageRequest.getSizePerPage()).build();
		return uriComponents.toUriString();
	}
}

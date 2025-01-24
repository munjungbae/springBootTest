package com.project.common.domain;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageRequest {
	// 현재페이지
	private int page;
	// 한페이지당 사이즈
	private int sizePerPage;
	private String searchType;
	private String keyword;

	public PageRequest() {
		this.page = 1;
		this.sizePerPage = 5;
	}

	public void setPage(int page) {
		this.page = page <= 0 ? 1 : page;
	}

	public void setSizePerPage(int size) {
		this.sizePerPage = (size <= 0 || size > 100) ? 10 : sizePerPage;
	}

	public int getPage() {
		return page;
	}

	// 마이바티스 SQL 매퍼를 위한 메서드
	// 해당되는 페이지 수의 위치를 설정 ( ex) 3page 요청시 (3-1) * 10 = 20부터 30까지 시작 )
	public int getPageStart() {
		return (this.page - 1) * sizePerPage;
	}

	// 마이바티스 SQL 매퍼를 위한 메서드
	public int getSizePerPage() {
		return this.sizePerPage;
	}

	// 검색 유형과 검색어를 멤버변수의 Getter/Setter 메서드
	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	// 멤버 변수를 활용하여 다양한 형태의 쿼리파라미터를 생성한다(변경 추가).
	public String toUriString() {
		UriComponents uriComponents = UriComponentsBuilder.newInstance().queryParam("page", this.page)
				.queryParam("sizePerPage", this.sizePerPage)
				.queryParam("searchType", this.searchType)
				.queryParam("keyword", this.keyword)
				.build();
		return uriComponents.toUriString();
	}

	//page=(사용자가 원하는 페이지)&size=this.sizePerPage값&searchType=writer&keyword=
	public String toUriString(int page) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance().queryParam("page", page)
				.queryParam("size", this.sizePerPage)
				.queryParam("searchType", this.searchType)
				.queryParam("keyword", this.keyword)
				.build();
		return uriComponents.toUriString();
	}

	// 멤버 변수를 활용하여 다양한 형태의 쿼리파라미터를 생성한다.
	// ?page2&size=10 의 값을 가짐
	public String toUriStringByPage(int page) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance().queryParam("page", page)
				.queryParam("sizePerPage", this.sizePerPage).build();
		return uriComponents.toUriString();
	}

}
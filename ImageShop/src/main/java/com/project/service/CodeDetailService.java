package com.project.service;

import java.util.List;

import com.project.domain.CodeDetail;

public interface CodeDetailService {

	public void register(CodeDetail codeDetail) throws Exception;

	public List<CodeDetail> list() throws Exception;

	// 상세 페이지
	public CodeDetail read(CodeDetail codeDetail) throws Exception;

	public void modify(CodeDetail codeDetail) throws Exception;

	public void remove(CodeDetail codeDetail) throws Exception;

}

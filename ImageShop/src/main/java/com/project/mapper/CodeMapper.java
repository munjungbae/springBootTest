package com.project.mapper;

import java.util.List;

import com.project.common.domain.CodeLabelValue;

public interface CodeMapper {

	public List<CodeLabelValue> getCodeGroupList() throws Exception;

	public List<CodeLabelValue> getCodeList(String groupCode) throws Exception;
}

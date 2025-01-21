package com.project.common.domain;

import lombok.Data;

//code_group 테이블에서 사용되어 있다 표시된 (use_yn = y) 인 그룹 이름을 관리하기 위한 도메인
@Data
public class CodeLabelValue {
	private final String value;
	private final String label;
}

package com.zeus.domain;

import java.sql.Date;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Board {
	private int boardNo;
	
	//입력 값의 검사규칙 반드시 입력해야 하는 필드 선언
	@NotBlank
	private String title;
	private String content;
	private String writer;
	private Date regDate;
}

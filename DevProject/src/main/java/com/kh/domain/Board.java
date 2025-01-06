package com.kh.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter // lombok 사용하여 getter 생성
@Setter	// lombok 사용하여 setter 생성	
@NoArgsConstructor // lombok 사용하여 디생 생성.
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(of="boardNo") //equals 생성
@AllArgsConstructor //모든 매개변수를 받을 수 있게 됨.

@Data //위의 lombok기반 어노테이션 통합본
public class Board {
	private int boardNo;
	@NonNull
	private String title;
	private String content;
	private Date regDate;
}

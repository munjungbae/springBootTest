package com.kh.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Getter // lombok 사용하여 getter 생성
//@Setter // lombok 사용하여 setter 생성
//@ToString(exclude = "content") // 해당 객체 배제
//@AllArgsConstructor // 모든 매개변수를 받을 수 있게 됨.
//@NoArgsConstructor // lombok 사용하여 디생 생성.
//@RequiredArgsConstructor // 매개변수 생성자 생성
//@EqualsAndHashCode(of = "boardNo") // equals 생성 (기준은 boardNo)


@Data // 위의 lombok기반 어노테이션 통합본
public class Board {
	private int boardNo;
	private String title;
	private String writer;
	private String content;
	private Date regDate;
}

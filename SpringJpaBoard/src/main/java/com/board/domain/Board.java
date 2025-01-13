package com.board.domain;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
//Board클래스를 오라클과 연결시키기 위해 선언하는@
@Entity
//시퀀스 지정
@SequenceGenerator(
		name="JPABOARD_SEQ_GEN", //시퀀스 사용자 지정 이름
		sequenceName="jpaboard_seq", //실제시퀀스 이름
		initialValue=1, //시작값
		allocationSize=1 //증가치
)
//Board클래스를 오라클의  name="JPABOARD" 과 실제 연결시켜주는 @
@Table(name="JPABOARD")
public class Board {
	@Id // PrimaryKey 지정
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "JPABOARD_SEQ_GEN") //사용자가 지정한 시퀀스를 아래 변수명에 지정
	@Column(name="board_no") //오라클의 컬럼명을 아래 변수명과 동일 시 하도록 지정 
	private Long boardNo;
	
	@Column(name="title") //오라클과 변수명이 같다면 안써도 무방 
	private String title; //오라클 속성명과 같다면 그대로 작성
	
	@Column(name="content")
	private String content;
	
	@Column(name="writer")
	private String writer;
	
	@CreationTimestamp // 시간을 숫자값으로 지정
	@Column(name="reg_date")
	private Date regDate;
}

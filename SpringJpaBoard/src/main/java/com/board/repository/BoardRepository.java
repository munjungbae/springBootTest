package com.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.board.domain.Board;

//	테이블 구조를위한 기준 = Board, 가장 핵심 아이디( 즉 핵심 키 TYPE ) int 니까 Integer
//	즉 쿼리문을 위하 기준을 설정 
// 	Integer를 기준으로 삭제, 수정, 읽기 등등 퀴리문 ?에 들어갈 기준을 설정
// 	Board를 기준으로 입력
public interface BoardRepository extends JpaRepository<Board, Long> {

}


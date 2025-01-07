package com.kh;

import java.util.Date;

import com.kh.domain.Board;
import com.kh.service.BoardService;

public class LomboksApplication {
	public static void main(String[] args) {
		
		BoardService bs = new BoardService();
		
		//빌더로 만든 보드를 생성
		Board board = Board.builder().
						boardNo(1).
						title("빌더 테스트").
						writer("테스트 내용").
						content("테스트 리스트").
						regDate(new Date()).
						build();
		
		bs.create(board);
		bs.readByBoardNo(1);
		bs.delete(board);
	}
}

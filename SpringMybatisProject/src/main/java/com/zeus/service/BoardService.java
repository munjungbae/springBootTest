package com.zeus.service;

import java.util.List;

import com.zeus.domain.Board;

//해당 BoardMapper의 오버라이딩은 BoardMapper.xml로 대체되어 오버라이딩
//Mybatisframework에서 객체를 자동 생성하여 호출 가능
public interface BoardService {
	public void register(Board board) throws Exception;

	public Board read(Integer boardNo) throws Exception;

	public void modify(Board board) throws Exception;

	public void remove(Integer boardNo) throws Exception;

	public List<Board> list() throws Exception;

	public List<Board> search(String title) throws Exception;
}

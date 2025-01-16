package com.zeus.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zeus.domain.Board;

public interface BoardMapper {
	//함수명이 xml의 ID이다
	public void create(Board board) throws Exception;

	public Board read(Integer boardNo) throws Exception;

	public void update(Board board) throws Exception;

	public void delete(Integer boardNo) throws Exception;

	public List<Board> list(String option, String search) throws Exception;
	
	public List<Board> search(@Param("title") String title) throws Exception;
}
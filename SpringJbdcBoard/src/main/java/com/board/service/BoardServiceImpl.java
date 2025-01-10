package com.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.dao.BoardDAO;
import com.board.domain.Board;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO dao;

	@Override
	public void register(Board board) throws Exception {
		dao.create(board);
	}

	@Override
	public List<Board> list() throws Exception {
		List<Board> board = dao.list();
		return board;
	}

	@Override
	public Board read(Board board) throws Exception {
		return dao.read(board);
	}

	@Override
	public boolean remove(Board board) throws Exception {
		return dao.delete(board);
	}

	@Override
	public int modify(Board board) throws Exception {
		return dao.modify(board);

	}

}

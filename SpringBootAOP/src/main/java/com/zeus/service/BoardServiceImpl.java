package com.zeus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zeus.domain.Board;
import com.zeus.mapper.BoardMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper mapper;

	@Override
	public void register(Board board) throws Exception {
		log.info("register");
		mapper.create(board);
	}

	@Override
	public Board read(Integer boardNo) throws Exception {
		log.info("read");
		return mapper.read(boardNo);
	}

	@Override
	public void modify(Board board) throws Exception {
		log.info("modify");
		mapper.update(board);
	}

	@Override
	public void remove(Integer boardNo) throws Exception {
		log.info("remove");
		mapper.delete(boardNo);
	}

	@Override
	public List<Board> list() throws Exception {
		log.info("list");
		return mapper.list();
	}

}

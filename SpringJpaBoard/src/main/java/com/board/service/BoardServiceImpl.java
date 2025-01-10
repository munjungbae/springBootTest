package com.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.board.domain.Board;
import com.board.repository.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {

	// jap컨테이너가 BoardRepository를 가지고 클래스 내 JpaRepository<Board, Integer>를 기준으로
	// curd작성
	@Autowired
	private BoardRepository repository;

	@Override
	@Transactional // save는 트랜잭션을 걸어주어야 함
	public void register(Board board) throws Exception {
		repository.save(board); // save => insert

	}

	@Override
	@Transactional(readOnly = true)
	public Board read(Long boardNo) throws Exception {
		return repository.getOne(boardNo); // getOne => boardNo의 해당하는 것들 읽어오기
	}

	@Override
	public void modify(Board board) throws Exception {
		// repository.getOne(board.getBoardNo())로 boardNo의 에 해당하는 정보를 가져온 뒤 아래 entity에
		// 정보입력
		Board boardEntity = repository.getOne(board.getBoardNo());
		boardEntity.setTitle(board.getTitle());
		boardEntity.setContent(board.getContent());
		boardEntity.setWriter(board.getWriter());
		boardEntity.setRegDate(board.getRegDate());
	}

	@Override
	@Transactional
	public void remove(Long boardNo) throws Exception {
		//deleteById(boardNo) = >boardNo에 해당하는 정보삭제
		repository.deleteById(boardNo);

	}

	@Override
	public List<Board> list() throws Exception {
		// findAll(Sort.by(Direction.DESC, "boardNo") "boardNo"를 기준으로 desc로 정렬하여 모두 가지고오기 즉 select
		return repository.findAll(Sort.by(Direction.DESC, "boardNo"));
	}

}

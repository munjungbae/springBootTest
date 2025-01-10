package com.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.board.domain.Board;
import com.board.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	public BoardService service;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerForm(Board board, Model model) throws Exception {
		log.info("Request registerForm");
		return "board/register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(Board board, Model model) throws Exception {
		log.info("Request register");
		service.register(board);
		model.addAttribute("msg", "등록이 완료되었습니다.");
		return "board/success";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model) throws Exception {
		log.info("Request list");
		model.addAttribute("list", service.list());
	}

	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(Board board, Model model) throws Exception {
		log.info("Request read");
		model.addAttribute(service.read(board.getBoardNo()));
	}

	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public String remove(Long boardNo, Model model) throws Exception {
		log.info("Request remove");
		Board board = new Board();
		board.setBoardNo(boardNo);
		service.remove(board.getBoardNo());
		model.addAttribute("msg", "삭제가 완료되었습니다.");

		return "board/success";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(Board board, Model model) throws Exception {
		log.info("modify");
		service.modify(board);
		model.addAttribute("msg", "수정이 완료되었습니다.");
		return "board/success";
	}

}

package com.zeus.controller;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zeus.domain.Board;
import com.zeus.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board")
//기반이 되는 mapper클래스 탐색 후 설정
@MapperScan(basePackages = "com.zeus.mapper")
public class BoardController {
	// 서비스 이용하여 DB에접근

	@Autowired
	private BoardService service;

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(String title, Model model) throws Exception {
		log.info("search");
		Board board = new Board();
		board.setTitle(title);
		model.addAttribute("board", board);
		model.addAttribute("list", service.search(title));
		return "board/list";
	}

	// 게시판 입력 폼 요청
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerForm(Board board, Model model) throws Exception {
		log.info("registerForm");
	}

	// post방식 요청 시 게시판 내용 입력 요청
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(Board board, Model model) throws Exception {
		service.register(board);
		model.addAttribute("msg", "등록이 완료되었습니다.");
		return "board/success";
	}

	// 게시판 전체 리스트 요청 , 이후 전체리스트 가져온 뒤 /list.jsp 화면에 전달 (void 이기 떄문에)
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model) throws Exception {
		log.info("list");
		model.addAttribute(new Board());
		model.addAttribute("list", service.list());
	}

	// 게시글 상세보기 요청 이후 요청 값을 /read.jsp에 전달
	// @RequestParam("boardNo") => url로 오는 boardNo 값을 해당 함수에 전달 int boardNo의 형태로
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(@RequestParam("boardNo") int boardNo, Model model) throws Exception {
		model.addAttribute(service.read(boardNo));
	}

	// 게시글 삭제 후 요청 값을 model에 저장, 이후 /board/success.jsp로 해당 값 전달
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(@RequestParam("boardNo") int boardNo, Model model) throws Exception {
		service.remove(boardNo);
		model.addAttribute("msg", "삭제가 완료되었습니다.");
		return "board/success";
	}

	// 게시글 수정을 위한 화면 요청 해당 된 게시글 값을 /modify.jsp에 전달.
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyForm(int boardNo, Model model) throws Exception {
		model.addAttribute(service.read(boardNo));
	}

	// post값으로 들어온 board를 가지고 수정요청. 이후 model에 메세지를 전달 후 success.jsp로 이동
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(Board board, Model model) throws Exception {
		service.modify(board);
		model.addAttribute("msg", "수정이 완료되었습니다.");
		return "board/success";
	}
}
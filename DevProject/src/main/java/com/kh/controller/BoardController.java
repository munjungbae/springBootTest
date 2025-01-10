package com.kh.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.domain.Board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
//@RequestMapping("/board")
public class BoardController {
//
//	@RequestMapping(value = "/read/{readNo}", method = RequestMethod.GET)
//	public String read(@PathVariable("readNo") int readNo, Model model) {
//
//		log.info("readNo = " + readNo);
//		model.addAttribute(readNo);
//		return "Home";
//	}
//
//	@GetMapping(value = "/list")
//	public void  list() {
//		log.info("listValue");
//	}

	@PostMapping(value = "/{boardNo}")
	public ResponseEntity<String> modifyByHeader(@PathVariable("boardNo") int boardNo, @RequestBody Board board) {

		log.info("boardNo = " + boardNo);
		log.info("board = " + board.toString());
		ResponseEntity<String> entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		return entity;

	}

	@PutMapping(value = "/{boardNo}")
	public ResponseEntity<String> modify(@PathVariable("boardNo") int boardNo, @RequestBody Board board) {

		log.info("boardNo = " + boardNo);
		log.info("board = " + board.toString());
		ResponseEntity<String> entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		return entity;

	}

	@GetMapping(value = "/{boardNo}", produces = "application/json")
	public ResponseEntity<Board> boardGetOne(@PathVariable("boardNo") int boardNo) {

		log.info("get boardNo = " +boardNo);
		Board board = new Board();
		board.setBoardNo(boardNo);
		board.setTitle("제목");
		board.setContent("내용입니다.");
		board.setWriter("홍길동");
		board.setRegDate(new Date());
		
		ResponseEntity<Board> entity = new ResponseEntity<Board>(board, HttpStatus.OK);
		return entity;

	}

//	@GetMapping(value = "/register")
//	public String registerForm() {
//		log.info("GET 방식 등록 폼");
//		return "success";
//	}
//
//	@GetMapping(value = "/register/{boardNo}")
//	public String registerForm(@PathVariable("boardNo") int boardNo) {
//		log.info("boardNo = " + boardNo);
//		return "success";
//	}
//
//	@PostMapping(value = "/register")
//	public String registerForm2() {
//		log.info("Post 방식 등록 폼");
//		return "success";
//	}
//
//	@GetMapping(value = "/modify")
//	public String modifyForm() {
//		log.info("Get 방식 등록 폼");
//		return "success";
//	}
//
//	@PostMapping(value = "/modify")
//	public String modifyForm2() {
//		log.info("Post 방식 등록 폼");
//		return "success";
//	}
//
//	@PostMapping(value = "/remove")
//	public String removeForm2() {
//		log.info("Post 방식 등록 폼");
//		return "success";
//	}
//
//	@GetMapping(value = "/list")
//	public String listForm() {
//		log.info("Get 방식 등록 폼");
//		return "success";
//	}
//
//	@GetMapping(value = "/regForm")
//	public String regForm() {
//		log.info("regForm 호출 get방식 ");
//		return "/board/regForm";
//	}
//
//	@GetMapping(value = "/readForm")
//	public String readForm() {
//		log.info("readFrom 호출 get방식 ");
//		return "/board/readForm";
//	}
//
//	@GetMapping("/registerForm")
//	public void success(HttpServletResponse response, HttpServletRequest request) {
//		PrintWriter out;
//		try {
//			response.setContentType("text/html; charset=utf-8");
//			out = response.getWriter();
//			out.println("<script>");
//			out.println("alert('등록 완료');");
//			out.println("window.location.href='./listBoard'");
//			out.println("</script>");
//
//			log.info("등록성공");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@GetMapping("/modifyForm")
//	public void modify(HttpServletResponse response, HttpServletRequest request) {
//		PrintWriter out;
//		try {
//			response.setContentType("text/html; charset=utf-8");
//			out = response.getWriter();
//			out.println("<script>");
//			out.println("alert('수정완료');");
//			out.println("window.location.href='./listBoard'");
//			out.println("</script>");
//
//			log.info("등록성공");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

}

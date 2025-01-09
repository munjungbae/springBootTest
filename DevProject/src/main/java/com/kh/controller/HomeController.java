package com.kh.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.domain.Board;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	// 멤버변수
//	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

//	@RequestMapping(value="/", method=RequestMethod.GET) // @RequestMapping의 값을 받는 5가지 => put, patch, get, post, delete
//	public String Home(Locale locale,Model model) {
//		//locale, model 객체주입 된 것을 활룡하여 출력
//		Date date = new Date();
//		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
//		String formatedDate = df.format(date);
//		model.addAttribute("작업시간", formatedDate);
//		log.info(model.toString());
//		System.out.println(model);
//		logger.info("logger = "+model.toString());
//		
//		
//		// mapping 으로 해당 수 호출 시 prefix로 인해 Home 앞에 /WEB-INF/views/ 작성
//		// prefix 이후 suffix로 인해 Home 뒤에 .jsp 작성
//		return "Home"; //즉 해당 위치의 Home.jsp호출 => /WEB-INF/views/Home.jsp
//		
//	}
//	
//	@GetMapping(value = "/ajaxHome")
//	public String ajaxHome() {
//		return "ajaxHome";
//	}
//	@GetMapping(value = "/ajaxHome2")
//	public String ajaxHome2() {
//		return "XmlHome";
//	}
//	@GetMapping(value = "/ajaxHome3")
//	public String ajaxHome3() {
//		return "ajaxHome2";
//	}
	@GetMapping(value = "/ajaxHome4")
	public String ajaxHome4() {
		return "ajaxHome3";
	}

	@GetMapping(value = "/goHome01")
	public String goHome01() {
		log.info("redirect:/goHome02");
		return "redirect:/goHome02";
	}

	@GetMapping(value = "/goHome02")
	public String goHome02() {
		log.info("goHome02");
		return "Home";
	}

	@ResponseBody
	@GetMapping(value = "/gohome11")
	public Map<String, Board> home08() {
		log.info("컬렉션 Map 타입 home08");
		Map<String, Board> map = new HashMap<String, Board>();
		Board board1 = new Board();
		board1.setBoardNo(1);
		board1.setTitle("제목");
		board1.setContent("내용입니다.");
		board1.setWriter("홍길동");
		board1.setRegDate(new Date());
		map.put("asdasfasfasdf", board1);
		Board board2 = new Board();
		board2.setBoardNo(2);
		board2.setTitle("제목2");
		board2.setContent("내용입니다2.");
		board2.setWriter("홍길동2");
		board2.setRegDate(new Date());
		map.put("k42452345", board2);
		return map;
	}

//	@RequestMapping("/form")
//	public String formHome() {
//		return "form";
//	}
//	
//	@RequestMapping("/listBoard")
//	public String list() {
//		return "/board/listBoard";
//	}

	@RequestMapping(value = "/memberInsert", method = RequestMethod.GET)
	public String memberInsert() {
		return "memberInsert";
	}

	@RequestMapping(value = "/registerFileUpForm", method = RequestMethod.GET)
	public String registerFileUpForm() {
		return "registerFileUpForm";
	}

	@RequestMapping(value = "/ajaxHome5", method = RequestMethod.GET)
	public String ajaxHome5() {
		return "ajaxHome4";
	}
	
	@RequestMapping(value = "/ajaxHome6", method = RequestMethod.GET)
	public String ajaxHome6() {
		return "ajaxHome5";
	}
}

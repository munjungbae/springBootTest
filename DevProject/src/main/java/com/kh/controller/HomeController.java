package com.kh.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	//멤버변수
//	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
//	@RequestMapping(value="/", method=RequestMethod.GET) // @RequestMapping의 값을 받는 5가지 => put, patch, get, post, delete
//	public String Home(Locale locale,Model model) {
//		//locale, model 객체주입 된 것을 활룡하여 출력
//		Date date = new Date();
//		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
//		String formatedDate = df.format(date);
//		model.addAttribute("작업시간", formatedDate);
////		log.info(model.toString());
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
//		
//		return "ajaxHome";
//	}
	
	@RequestMapping("/form")
	public String formHome() {
		return "form";
	}
	
	@RequestMapping("/listBoard")
	public String list() {
		return "/board/listBoard";
	}
}

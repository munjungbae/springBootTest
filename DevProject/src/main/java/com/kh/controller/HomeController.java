package com.kh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping(value="/")
	public String Home() {
		// mapping 으로 해당 수 호출 시 prefix로 인해 Home 앞에 /WEB-INF/views/ 작성
		// prefix 이후 suffix로 인해 Home 뒤에 .jsp 작성
		return "Home"; //즉 해당 위치의 Home.jsp호출 => /WEB-INF/views/Home.jsp
		
	}
}

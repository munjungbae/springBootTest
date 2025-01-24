package com.kh.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.domain.User;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

@org.springframework.web.bind.annotation.RestController
@Slf4j
@Builder
public class RestController {

	@GetMapping("blog")
	public String httpGet() {
		return "Get 요청처리";
	}

	@PostMapping("blog")
	// @ResponseBody : 자바 객체를 JSON 방식으로 변환하여 브라우저에게 전송
	// @RequestBody : 브라우저가 JSON방식으로 전송시 자바객체로 변환
	public List<User> httpPost(@RequestBody User user) {

		List<User> list = new ArrayList<User>();
		list.add(user);
		list.add(user);

		return list;
	}

	@PutMapping("blog")
	public String httpPut(@RequestBody User user) {

		return "Put 요청처리" + user.toString();
	}

	@DeleteMapping("blog")
	public String httpDelete(@RequestParam int id) {

		return "Delete 요청처리" + id;
	}
}

package com.kh.controller;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.domain.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {

	@PostMapping(value = "/insert")
	public String insetMember(Member member, @DateTimeFormat(pattern="yyyy년 MM월 dd일") Date dateOfBirth) {
		log.info("registerBeans");
		log.info("member.getUserId() = " + member.getUserId());
		log.info("member.getPassword() = " + member.getPassword());
		log.info("member.getCoin() = " + member.getCoin());
		log.info("member.dateOfBirth() = " + member.getDateOfBirth());
		log.info("pattern='yyyy년 MM월 dd일' = " + dateOfBirth);

		return "Home";
	}
}

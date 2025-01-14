package com.mun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/customer")
public class CustomerController {

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home() {
		log.info("home");
		return "/customer/home";
	}
}

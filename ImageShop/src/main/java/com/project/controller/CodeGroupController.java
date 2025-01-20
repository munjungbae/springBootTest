package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.domain.CodeGroup;
import com.project.service.CodeGroupService;

@Controller
@RequestMapping("/codegroup")
public class CodeGroupController {
	@Autowired
	private CodeGroupService service;

	// 등록 페이지
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerForm(Model model) throws Exception {
		CodeGroup codeGroup = new CodeGroup();
		model.addAttribute(codeGroup);
	}

}

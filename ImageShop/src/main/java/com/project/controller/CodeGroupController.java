package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.domain.CodeGroup;
import com.project.service.CodeGroupService;

@Controller
@RequestMapping("/codegroup")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class CodeGroupController {
	@Autowired
	private CodeGroupService service;

	// 등록 페이지
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerForm(Model model) throws Exception {
		CodeGroup codeGroup = new CodeGroup();
		model.addAttribute(codeGroup);
	}

	// 등록처리
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(CodeGroup codeGroup, RedirectAttributes rttr) throws Exception {
		service.register(codeGroup);
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/codegroup/list";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model) throws Exception {
		model.addAttribute("list", service.list());
	}

	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(String groupCode, Model model) throws Exception {
		model.addAttribute(service.read(groupCode));
	}

	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyForm(String groupCode, Model model) throws Exception {
		model.addAttribute(service.read(groupCode));
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(CodeGroup codeGroup, RedirectAttributes rttr) throws Exception {
		service.modify(codeGroup);
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/codegroup/list";
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(String groupCode, RedirectAttributes rttr) throws Exception {
		service.remove(groupCode);
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/codegroup/list";
	}

}

package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.common.domain.CodeLabelValue;
import com.project.domain.CodeDetail;
import com.project.service.CodeDetailService;
import com.project.service.CodeGroupService;
import com.project.service.CodeService;

@Controller
@RequestMapping("/codedetail")
public class CodeDetailController {

	// code_group 테이블에서 사용되어 있다 표시된 (use_yn = y) 인 그룹 이름을 관리하기 위한 도메인
	@Autowired
	private CodeService codeService;

	@Autowired
	private CodeDetailService codeDetailService;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerForm(Model model) throws Exception {
		CodeDetail codeDetail = new CodeDetail();
		model.addAttribute(codeDetail);

		// 그룹코드 목록을 조회하여 뷰에 전달
		List<CodeLabelValue> groupCodeList = codeService.getCodeGroupList();
		model.addAttribute("groupCodeList", groupCodeList);
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(CodeDetail codeDetail, RedirectAttributes rttr) throws Exception {
		codeDetailService.register(codeDetail);
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/codedetail/list";
	}

	// 목록 페이지
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model) throws Exception {
		model.addAttribute("list", codeDetailService.list());
	}

	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(CodeDetail codeDetail, Model model) throws Exception {
		model.addAttribute(codeDetailService.read(codeDetail));
		// 그룹코드 목록을 조회하여 뷰에 전달
		List<CodeLabelValue> groupCodeList = codeService.getCodeGroupList();
		model.addAttribute("groupCodeList", groupCodeList);
	}

	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyForm(CodeDetail codeDetail, Model model) throws Exception {
		model.addAttribute(codeDetailService.read(codeDetail));
		// 그룹코드 목록을 조회하여 뷰에 전달
		List<CodeLabelValue> groupCodeList = codeService.getCodeGroupList();
		model.addAttribute("groupCodeList", groupCodeList);
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(CodeDetail codeDetail, RedirectAttributes rttr) throws Exception {
		codeDetailService.modify(codeDetail);
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/codedetail/list";
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(CodeDetail codeDetail, RedirectAttributes rttr) throws Exception {
		codeDetailService.remove(codeDetail);
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/codedetail/list";
	}
}

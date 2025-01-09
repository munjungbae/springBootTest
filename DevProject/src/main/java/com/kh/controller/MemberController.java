package com.kh.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.domain.FileMember;
import com.kh.domain.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {

	@PostMapping(value = "/insert")
	public String insetMember(@ModelAttribute("userId") String userId, @ModelAttribute("password") String password) {
		log.info("memberInsert");
		return "Home";
	}

	@PostMapping(value = "/redirect")
	public String redirectBlahblah(Member member, RedirectAttributes rt) {
		log.info("Redirect");
		rt.addFlashAttribute("member", member);
		return "redirect:/member/result";
	}

	@RequestMapping(value = "/result")
	public String resultBlahblah(Member member) {
		log.info("result");
		return "result";
	}

	@RequestMapping(value = "/registerFileUp02", method = RequestMethod.POST)
	public String registerFileUp02(@RequestBody FileMember fileMember) throws Exception {

		if (!fileMember.getPicture().isEmpty()) {
			for (MultipartFile data : fileMember.getPicture()) {
				log.info("originalName1: " + data.getOriginalFilename());
				log.info("size1: " + data.getSize());
				log.info("contentType1: " + data.getContentType());

				if (!data.isEmpty()) {
					String fileName = data.getOriginalFilename();
					data.transferTo(new File("C:/SpringBootProject/upload_files/" + fileName));
				}
			}
		}
		return "Home";
	}

	@PostMapping(value = "/register06")
	public ResponseEntity<String> register06(@RequestBody List<Member> memberList) {
		log.info("register06");
		for (Member member : memberList) {
			log.info("userId = " + member.getUserId());
			log.info("password = " + member.getPassword());
		}
		ResponseEntity<String> entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		return entity;
	}

	@RequestMapping(value = "/uploadAjax", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception {

		log.info("originalName1: " + file.getOriginalFilename());
		log.info("size1: " + file.getSize());
		log.info("contentType1: " + file.getContentType());

		if (!file.isEmpty()) {
			String fileName = file.getOriginalFilename();
			file.transferTo(new File("C:/SpringBootProject/upload_files/" + fileName));

		}
		log.info("originalName: " + file.getOriginalFilename());
		ResponseEntity<String> entity = new ResponseEntity<String>("UPLOAD SUCCESS " + file.getOriginalFilename(),
				HttpStatus.OK);
		return entity;
	}

	@RequestMapping(value = "/registerSpringFormCheckboxes01", method = RequestMethod.GET)
	public String registerSpringFormCheckboxes01(Model model) {
		log.info("registerSpringFormCheckboxes01");
		Map<String, String> hobbyMap = new HashMap<String, String>();
		hobbyMap.put("01", "Sports");
		hobbyMap.put("02", "Music");
		hobbyMap.put("03", "Movie");
		model.addAttribute("hobbyMap", hobbyMap);
		model.addAttribute("member", new Member());
		return "registerSpringFormCheckboxes01"; // 뷰 파일명
	}

	@RequestMapping(value = "/registerSpringFormErrors", method = RequestMethod.GET)
	public String registerSpringFormErrors(Model model) {
		log.info("registerSpringFormErrors");
		Member member = new Member();
		member.setEmail("aaa@ccc.com");
		member.setUserName("홍길동");
		model.addAttribute("member", member);
		return "registerSpringFormErrors"; // 뷰 파일명
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@Validated Member member, BindingResult result) {
		log.info("register");
		if (result.hasErrors()) {
			return "registerSpringFormErrors";
		}
		log.info("member.getUserId() = " + member.getUserId());
		log.info("member.getUserName() = " + member.getUserName());
		log.info("member.getEmail() = " + member.getEmail());
		return "errorsResult";
	}

}

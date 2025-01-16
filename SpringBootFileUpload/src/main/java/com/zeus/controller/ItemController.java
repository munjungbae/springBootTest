package com.zeus.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.resource.HttpResource;

import com.zeus.domain.Item;
import com.zeus.service.ItemService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/item")
@MapperScan(basePackages = "com.zeus.mapper")
public class ItemController {

	@Autowired
	private ItemService itemService;

	@Value("${upload.path}")
	private String uploadPath;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model) throws Exception {
		model.addAttribute("itemList", itemService.list());
	}

	// IMG게시판 등록 화면 요청
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerForm(Model model) {
		model.addAttribute(new Item());
		return "item/register";
	}

	// IMG게시판 DB 등록 및 파일 저장 요청
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(Item item, Model model) throws Exception {
		MultipartFile file = item.getPicture();
		log.info("originalName: " + file.getOriginalFilename());
		log.info("size: " + file.getSize());
		log.info("contentType: " + file.getContentType());
		// uploadFile(String originalName, byte[] fileData) 구조임
		// file.getBytes() => 이미지 파일을 byte[]로 만들어서 전송
		String createdFileName = uploadFile(file.getOriginalFilename(), file.getBytes());
		item.setPictureUrl(createdFileName);
		this.itemService.regist(item);
		model.addAttribute("msg", "등록이 완료되었습니다.");
		return "item/success";
	}

	// IMG게시판 등록 수정화면 요청
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modifyForm(Integer itemId, Model model) throws Exception {
		Item item = this.itemService.read(itemId);
		model.addAttribute(item);
		return "item/modify";
	}

	// IMG게시판 파일 DB 등록 및 파일 저장 수정 요청
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(Item item, Model model) throws Exception {
		MultipartFile file = item.getPicture();
		if (file != null && file.getSize() > 0) {
			log.info("originalName: " + file.getOriginalFilename());
			log.info("size: " + file.getSize());
			log.info("contentType: " + file.getContentType());
			String createdFileName = uploadFile(file.getOriginalFilename(), file.getBytes());
			item.setPictureUrl(createdFileName);
		}
		itemService.modify(item);
		model.addAttribute("msg", "수정이 완료되었습니다.");
		return "item/success";
	}

	// IMG게시판 제거 화면 요청
	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public String removeForm(Integer itemId, Model model) throws Exception {
		Item item = this.itemService.read(itemId);
		model.addAttribute(item);
		return "item/remove";
	}

	// IMG게시판 파일 내용 삭제 요청
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(Item item, Model model) throws Exception {
		this.itemService.remove(item.getItemId());
		model.addAttribute("msg", "삭제가 완료되었습니다.");
		return "item/success";
	}

	// 파일 업로드를 위해 사용 할 멤버함수
	private String uploadFile(String originalName, byte[] fileData) throws Exception {
		// 랜덤 UUID 생성
		UUID uid = UUID.randomUUID();
		// 파일 명을 준다면 중복이 일어나지 않는 이름_파일원본이름 을 제작
		String createdFileName = uid.toString() + "_" + originalName;
		// uploadPath위치에 저장
		File target = new File(uploadPath, createdFileName);
		FileCopyUtils.copy(fileData, target);
		// 그리고 이름_파일원본이름 값 반환
		return createdFileName;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(Item item, Model model, HttpServletResponse response) throws Exception {
		PrintWriter out;
		
		String fileName = itemService.getPicture(item.getItemId());
		
		File file = new File(uploadPath + "/" + fileName);
		boolean flag = file.exists();
		
		if (flag) {
			file.delete();
		} else {
			response.setContentType("text/html; charset=utf-8");
			out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정완료');");
			out.println("</script>");
		}
		return "item/modify";
	}

	// 브라우저에서 이미지를 <img src="/item/display/파일명 또는 이미지 번호">와 같이 부여하면 파일명 또는 이미지 번호의
	// 게시판에서 화면에 전달해주는 함수이다.
	@ResponseBody
	@RequestMapping("/display")
	public ResponseEntity<byte[]> displayFile(Integer itemId) throws Exception {
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;

		// 14e31fb0-9f60-4aa9-b61f-23dddeadf223_asdfasdf.jpg 정보 입력
		String fileName = itemService.getPicture(itemId);
		log.info("FILE NAME: " + fileName);
		try {
			// 14e31fb0-9f60-4aa9-b61f-23dddeadf223_asdfasdf.jpg => "jpg" 값만 가져옴
			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
			MediaType mType = getMediaType(formatName);

			// 헤더 생성
			HttpHeaders headers = new HttpHeaders();

			// in = c:/upload/14e31fb0-9f60-4aa9-b61f-23dddeadf223_asdfasdf.jpg 값을 가져옴
			in = new FileInputStream(uploadPath + File.separator + fileName); // File.separator => 파일과 파일 간 구별 관계
			if (mType != null) {
				// MediaType.IMAGE_JPEG 를 헤더에 추가
				headers.setContentType(mType);
			}
			// IOUtils.toByteArray(in) => in에 들어가는 내용을 배열로 변경
			// headers => 해당 내용이 헤더에 있다
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			in.close();
		}
		return entity;
	}

	private MediaType getMediaType(String formatName) {
		if (formatName != null) {
			if (formatName.equals("JPG")) {
				return MediaType.IMAGE_JPEG;
			}
			if (formatName.equals("GIF")) {
				return MediaType.IMAGE_GIF;
			}
			if (formatName.equals("PNG")) {
				return MediaType.IMAGE_PNG;
			}
		}
		return null;
	}
}

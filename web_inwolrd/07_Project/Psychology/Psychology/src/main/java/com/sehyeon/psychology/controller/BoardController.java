package com.sehyeon.psychology.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.sehyeon.psychology.bean.BoardVO;
import com.sehyeon.psychology.dto.BoardDAO;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardDAO dao; 
	
	// List
	@GetMapping("list")
	public void list(Model model) {
		log.info("--------------------------------------->");
		log.info("Get List Called");
		model.addAttribute("list", dao.getList());
	}
	
	// register(글쓰기) 화면 호출용
	@GetMapping("register")
	public void register() {
		
	}
	
	// register(글쓰기) 처리용
	@PostMapping("/register")
	public String write(BoardVO board, RedirectAttributes rttr, HttpSession session) {
		
		log.info("글 작성 요청 받음 : " + board);
		
		int result = dao.register(board);
		
		if(result > 0) {
			rttr.addFlashAttribute("msg", "글이 성공적으로 등록되었습니다.");
			log.info(result + "건 등록 완료");
		} else {
			rttr.addFlashAttribute("msg", "글 등록 실패!");
		}
		
		return "redirect:/board/list";
	}
	
	// read(글읽기) 처리용
	// localhost:10001/psychology/read?bno=N를 호출했을 때 내용을 보여주는 페이지
	@RequestMapping("read")
	public void read(Long bno, Model model) {
		log.info("------------------------------------->");
		log.info("read : bno = " + bno);
		model.addAttribute("vo", dao.read(bno));
	}
		
	// remove 처리용
	// localhost:10001/psychology/remove?bno=N를 호출했을 때 삭제하는 기능
	@RequestMapping("remove")
	public RedirectView remove(Long bno, RedirectAttributes rttr) {
		log.info("--------------------------------->");
		// log.info("삭제 건수 : " + dao.remove(bno));
		log.info("--------------------------------->");
		
		if(dao.remove(bno) > 0) {
			rttr.addFlashAttribute("msg", "글 삭제에 성공하였습니다.");
		}
		else {
			rttr.addFlashAttribute("msg", "잘못된 접근입니다.");
		}
		
		return new RedirectView("list");
	}
	
	// modify 처리용
	// localhost:10000/board/modify?bno=N를 호출했을 때 수정페이지를 로딩하는 기능
	@GetMapping("modify")
	public void modify(Long bno, Model model) {
		// bno를 가지고 BoardVO를 얻어온 후 model에 태우는 과정이 필요
		model.addAttribute("board", dao.read(bno));
	}
	
	@PostMapping("modify")
	public RedirectView modifyPost(BoardVO board, RedirectAttributes rttr) {
		int count = dao.modify(board);
		
		if(count > 0)
			rttr.addFlashAttribute("msg", "글 " + board.getBno() + " 업데이트 완료");
		else
			rttr.addFlashAttribute("msg", "글 수정에 실패하였습니다.");
		
		return new RedirectView("list");
		}
}

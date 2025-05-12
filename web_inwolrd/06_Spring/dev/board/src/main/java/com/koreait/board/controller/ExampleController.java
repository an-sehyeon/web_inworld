package com.koreait.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.koreait.board.bean.ArtVO;
import com.koreait.board.bean.SubjectVO;
import com.koreait.board.dao.TimeDAO;
import com.koreait.board.util.MyUtil;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ex/*")		// localhost:10000/ex는 제껍니다.
public class ExampleController {
	
	@Autowired
//	private TimeMapper mapper;
	private TimeDAO timeDAO;
	
	@GetMapping("")
	public void notting() {
		log.info("===Nothing Method is called===");
	}
	
	@PostMapping("sports")
	public void postSports() {		
	// Controller에서 void는 메서드가 종료되면 자동으로 Mapping keyword로 이동한다.
	// void getSports()는 template/ex/sports.html을 찾고 있음	
		log.info(MyUtil.BLUE + "My favorite sports is the soccer." + MyUtil.END);
	}
	
	@GetMapping("music")
	public String getMusic() {
		log.info(MyUtil.BLUE + "My favorite music is the classical music" + MyUtil.END);
		return "/ex/sports";
	}
	
	// classic way
	// http://localhost:10000/ex/classic?title=nocturne&artist=Chopin
	@GetMapping("classic")
	public String classicTest(HttpServletRequest req ) {
		String title = req.getParameter("title");
		String artist = req.getParameter("artist");
		log.info("classic : " + title + "(" + artist + ")" );
		return "index";
	}
	
	// spring way I
	// http://localhost:10000/ex/modern?title=nocturne&artist=Chopin
	@GetMapping("modern")
	public String modern(String title, String artist) {
		log.info("modern : " + title + "(" + artist + ")" );
		return "index";
	}
	
	// spring way II
	// http://localhost:10000/ex/future?title=nocturne&artist=Chopin
	@GetMapping("future")
	public String future(ArtVO artVO, String title) {
		log.info("future : " + artVO );
		log.info("future : " + title );
		return "index";
	}
	
	// http://localhost:10000/ex/play?t=nocturne&a=Bach
	@GetMapping("play")
	public String play(@RequestParam("t") String title, 
					   @RequestParam("a") String artist) {
		log.info("play : " + title + "(" + artist + ")" );
		return "index";
	}
	
	@GetMapping("query")
	public void query(@ModelAttribute("MyModel") ArtVO vo, String desc) {
		vo.setTitle("Piano Concerto No.1");
		vo.setArtist("차이코프스키");
		vo.setDesc(desc);
	}
	
	@GetMapping("subject")		// void인 경우 /ex/subject.html으로 자동 이동
	public void subject() {	
			log.info("----------------------- subject called");
	}
	
	@GetMapping("subjectVO")
	public String subjectVO(Model model, SubjectVO vo) {
		// Model을 이용하여 kor 10점, math 20점, eng 40점을 sebjectVO.html로 전달하시오.
		// subjectVO.html에서는 kor, math, eng 점수를 받아서 출력하시오.
		
//		model.addAttribute("kor", kor);
//		model.addAttribute("eng", eng);
//		model.addAttribute("math", math);
//		model.addAttribute("sum", kor+eng+math);
//		model.addAttribute("avg", (kor+eng+math)/3F);
		model.addAttribute("tm", timeDAO.getTime());
		model.addAttribute("vo", vo);
		
		return "/ex/subjectVO";
	}
	
	
	
	
}

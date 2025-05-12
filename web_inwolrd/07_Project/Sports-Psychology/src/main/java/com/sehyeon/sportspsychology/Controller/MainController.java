package com.sehyeon.sportspsychology.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/sportspsychology/*")
@Slf4j
public class MainController {
	
//	@Autowired
//	private SportsPsychologyDAO dao = new SportsPsychologyDAO();
//	
	@GetMapping("main")
	public String main() {
		log.info("------------------------------------------>");
		log.info("Get Main Called");
		return "SportsPsychology/main";
	}

}

package com.sehyeon.psychology.controller;

import com.sehyeon.psychology.dto.PsychologyTestDTO;
import com.sehyeon.psychology.entity.PsychologyTestResultEntity;
import com.sehyeon.psychology.service.PsychologyTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller  
@RequestMapping("/psychologyTest")
public class PsychologyTestController {

    @GetMapping  //  "/psychologyTest" 경로에서 HTML 반환
    public String psychologyTestP() {
        return "psychologyTest";  // templates/psychologyTest.html 반환
    }
}

// JSON 반환을 위한 REST API 컨트롤러
@RestController
@RequestMapping("/psychologyTest/api")
class PsychologyTestRestController {

    @Autowired
    private PsychologyTestService testService;

    @PostMapping("/save")
    public String saveTestResult(@RequestBody PsychologyTestDTO testDTO) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            testDTO.setUserId(((UserDetails) principal).getUsername().hashCode());
        }

        testService.saveTestResult(testDTO);
        return "테스트 결과가 저장되었습니다!";
    }

    @GetMapping("/results/{userId}")
    public List<PsychologyTestResultEntity> getUserResults(@PathVariable int userId) {
        return testService.getResultsByUserId(userId);
    }
}
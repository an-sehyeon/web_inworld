package com.sehyeon.psychology.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sehyeon.psychology.dto.PsychologyTestResultDTO;
import com.sehyeon.psychology.entity.JoinEntity;
import com.sehyeon.psychology.entity.PsychologyTestResultEntity;
import com.sehyeon.psychology.repository.PsychologyTestResultRepository;
import com.sehyeon.psychology.repository.UserRepository;
import com.sehyeon.psychology.service.PsychologyTestResultService;

@RestController
@RequestMapping("/api/test-results")
public class PsychologyTestResultController {

    @Autowired
    private PsychologyTestResultService service;

    @Autowired
    private UserRepository userRepository; // UserRepository 주입 

    @Autowired
    private PsychologyTestResultRepository repository; // PsychologyTestResultRepository 주입

    // 테스트 결과 저장 API
    @PostMapping("/save")
    public String saveTestResult(@RequestBody PsychologyTestResultDTO dto) {
        JoinEntity user = userRepository.findByUsername(dto.getUsername());  

        if (user == null) {
            throw new RuntimeException("사용자를 찾을 수 없습니다. username : " + dto.getUsername());  // 문자열 연결 
        }

        // 같은 사용자 ID와 테스트 이름이 있는지 확인
        Optional<PsychologyTestResultEntity> existingResult = repository.findByUserAndTestName(user, dto.getTestName());

        if (existingResult.isPresent()) {
            // 기존 데이터 유지하면서 updated_at만 업데이트
            PsychologyTestResultEntity result = existingResult.get();
            result.setUpdatedAt(LocalDateTime.now());
            repository.save(result);
        } else {
            // 기존 데이터를 덮어쓰지 않고 새 데이터로 저장
            PsychologyTestResultEntity newResult = PsychologyTestResultEntity.builder()
                    .user(user)
                    .testName(dto.getTestName())
                    .testResult(dto.getTestResult())
                    .cognitiveScore(dto.getCognitiveScore())
                    .somaticScore(dto.getSomaticScore())
                    .confidenceScore(dto.getConfidenceScore())
                    .totalScore(dto.getTotalScore())
                    .createdAt(LocalDateTime.now())  // created_at 추가
                    .updatedAt(null)  // 처음 삽입 시 updated_at 없음
                    .build();

            repository.save(newResult);  // UserRepository → repository로 수정 (UserRepository는 JoinEntity 관련 저장용)
        }

        return "Test result saved successfully.";
    }
}

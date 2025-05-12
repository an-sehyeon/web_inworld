package com.sehyeon.psychology.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sehyeon.psychology.dto.PsychologyTestResultDTO;
import com.sehyeon.psychology.entity.JoinEntity;
import com.sehyeon.psychology.entity.PsychologyTestResultEntity;
import com.sehyeon.psychology.repository.PsychologyTestResultRepository;
import com.sehyeon.psychology.repository.UserRepository;

@Service
public class PsychologyTestResultService {
    
    @Autowired
    private PsychologyTestResultRepository repository;

    @Autowired
    private UserRepository userRepository;

    public void saveTestResult(PsychologyTestResultDTO dto) {
        System.out.println("저장 요청된 사용자: " + dto.getUsername());  // 디버깅 추가

        if (dto.getUsername() == null || dto.getUsername().trim().isEmpty()) {
            throw new RuntimeException("사용자 이름이 비어 있습니다.");
        }

        JoinEntity user = userRepository.findByUsername(dto.getUsername());

        if (user == null) {
            throw new RuntimeException("사용자를 찾을 수 없습니다. username: " + dto.getUsername());
        }

        System.out.println("사용자 확인됨: " + user.getUsername());

        PsychologyTestResultEntity result = PsychologyTestResultEntity.builder()
                .user(user)
                .testName(dto.getTestName())
                .testResult(dto.getTestResult())  			// JSON 데이터 그대로 저장
                .cognitiveScore(dto.getCognitiveScore())
                .somaticScore(dto.getSomaticScore())
                .confidenceScore(dto.getConfidenceScore())
                .totalScore(dto.getTotalScore())
                .build();

        repository.save(result);
        System.out.println("테스트 결과 저장 완료");
    }
}

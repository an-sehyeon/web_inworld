package com.sehyeon.psychology.service;

import com.sehyeon.psychology.dto.PsychologyTestDTO;
import com.sehyeon.psychology.entity.PsychologyTestResultEntity;
import com.sehyeon.psychology.entity.JoinEntity;
import com.sehyeon.psychology.repository.PsychologyTestRepository;
import com.sehyeon.psychology.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PsychologyTestService {

    @Autowired
    private PsychologyTestRepository testRepository;

    @Autowired
    private UserRepository userRepository;

    public void saveTestResult(PsychologyTestDTO testDTO) {
        Optional<JoinEntity> user = userRepository.findById(testDTO.getUserId());
        if (user.isPresent()) {
            PsychologyTestResultEntity result = PsychologyTestResultEntity.builder()
                    .user(user.get())
                    .testname(testDTO.getTestName())
                    .testresult(testDTO.getTestResult())
                    .totalScore(testDTO.getTotalScore())
                    .build();
            testRepository.save(result);
        }
    }

    public List<PsychologyTestResultEntity> getResultsByUserId(int userId) {
        return testRepository.findByUser_Id(userId);
    }
}
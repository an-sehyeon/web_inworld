package com.sehyeon.psychology.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PsychologyTestDTO {
    private int userId;
    private String testName;
    private String testResult;  // JSON 형태로 저장
    private int totalScore;  // 총점 저장
}
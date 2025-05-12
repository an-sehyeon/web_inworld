package com.sehyeon.psychology.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PsychologyTestResultDTO {
    private String username;
    private String testName;
    private String testResult; // JSON 형태의 응답 데이터
    private Double cognitiveScore;
    private Double somaticScore;
    private Double confidenceScore;
    private int totalScore;
}

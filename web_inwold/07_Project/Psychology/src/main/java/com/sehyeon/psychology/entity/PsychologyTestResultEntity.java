package com.sehyeon.psychology.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "psychology_test_result")
public class PsychologyTestResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private JoinEntity user;

    @Column(name = "test_name", nullable = false)
    private String testName;

    @Column(name = "test_result", columnDefinition = "JSON", nullable = false)
    private String testResult;

    @Column(nullable = false)
    private Double cognitiveScore;

    @Column(nullable = false)
    private Double somaticScore;

    @Column(nullable = false)
    private Double confidenceScore;

    @Column(name = "total_score", nullable = false)
    private int totalScore;

    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;  // updated_at 컬럼 추가 (수정됨)

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now(); // 데이터 삽입 시 created_at 자동 설정 (수정됨)
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now(); // 데이터 업데이트 시 updated_at 자동 설정 (수정됨)
    }
}

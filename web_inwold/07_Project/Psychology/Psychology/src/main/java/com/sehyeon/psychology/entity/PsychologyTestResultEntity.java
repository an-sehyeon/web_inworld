package com.sehyeon.psychology.entity;

import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	    private String testname;

	    @Column(name = "test_result", columnDefinition = "JSON")
	    private String testresult;  // JSON 형태로 저장

	    @Column(name = "total_score", nullable = false)
	    private int totalScore;  // 총 점수 저장

	    @Column(name = "created_at", updatable = false, nullable = false)
	    private LocalDateTime createdAt = LocalDateTime.now();
	}
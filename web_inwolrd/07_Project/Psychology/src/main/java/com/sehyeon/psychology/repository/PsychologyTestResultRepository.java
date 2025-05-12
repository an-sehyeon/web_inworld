package com.sehyeon.psychology.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sehyeon.psychology.entity.JoinEntity;
import com.sehyeon.psychology.entity.PsychologyTestResultEntity;

@Repository
public interface PsychologyTestResultRepository extends JpaRepository<PsychologyTestResultEntity, Integer> {
    List<PsychologyTestResultEntity> findByUser_Username(String username);

	Optional<PsychologyTestResultEntity> findByUserAndTestName(JoinEntity user, String testName);
}

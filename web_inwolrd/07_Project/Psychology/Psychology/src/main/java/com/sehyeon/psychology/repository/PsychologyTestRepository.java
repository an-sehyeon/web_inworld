package com.sehyeon.psychology.repository;

import com.sehyeon.psychology.entity.PsychologyTestResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PsychologyTestRepository extends JpaRepository<PsychologyTestResultEntity, Integer> {
    List<PsychologyTestResultEntity> findByUser_Id(int userId);
}
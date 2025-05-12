package com.sehyeon.psychology.repository;

//데이터베이스 접근
//JoinEntity와 연결된 DB 쿼리를 수행하는 클래스
//Spring Data JPA를 사용하여 사용자 정보를 조회 및 저장


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sehyeon.psychology.entity.JoinEntity;
import com.sehyeon.psychology.entity.PsychologyTestResultEntity;

public interface UserRepository extends JpaRepository<JoinEntity, Integer> {

	boolean existsByUsername(String username);
	
	 JoinEntity findByUsername(String username);
	 
	 Optional<JoinEntity> findByEmail(String email);  // 이메일로 사용자 찾기

	 @Query("SELECT r FROM PsychologyTestResultEntity r WHERE r.user = :user AND r.testName = :testName")
	 Optional<PsychologyTestResultEntity> findByUserAndTestName(@Param("user") JoinEntity user, @Param("testName") String testName);

}

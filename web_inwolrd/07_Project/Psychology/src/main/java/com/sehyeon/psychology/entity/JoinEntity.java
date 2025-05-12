package com.sehyeon.psychology.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

// DB 테이블과 매핑되는 클래스
// 사용자 정보를 담는 엔티티 클래스
// DB의 joinentity 테이블과 매핑

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "joinentity") 	// DB 테이블 이름
@Builder
@AllArgsConstructor
@NoArgsConstructor  // 기본 생성자 추가
public class JoinEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(unique = true)
    private String username;		// 아이디
    private String password;		// 비밀번호
    private String name;			// 이름
    private String birth_date;		// 생일
    private String phone;			// 전화번호
    private String address;			// 주소
    private String affiliation;		// 소속
    
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private String created_at;		// 생성 날짜
    
    @LastModifiedDate
    @Column(nullable = false)
    private String update_at;		// 수정 날짜
    @Column(unique =true)
    private String email;			// 이메일
    private String target;			// 대상 
    private String role;			// 권한
}

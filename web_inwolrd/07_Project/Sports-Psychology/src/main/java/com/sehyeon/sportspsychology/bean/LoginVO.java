package com.sehyeon.sportspsychology.bean;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class LoginVO {
	
	// "Users" 테이블의 컬럼과 매칭되는 변수들
	private int id;						// 회원 고유 ID(번호)
	private String name;				// 이름
	private String birthDate;			// 생년월일
	private String username;			// 사용자명
	private String password;			// 비밀번호
	private String phone;				// 전화번호
	private String email;				// 이메일
	private String address;				// 주소
	private String affiliation;			// 소속
	private String role;				// 대상
	private LocalDateTime createdAt;	// 레코드 생성 시점 (타임스탬프)
	private LocalDateTime updatedAt;	// 레코드 업데이트 시점 (타임스탬프)
	
	// 기본 생성자
	public LoginVO() {}
	
	// 매개변수가 있는 생성자
	// 매개변수 생성자는 객체를 더 직관적이고 효율적으로 생성할 수 있게 도와줌
	// 초기화 과정을 통제할 수 있고, 객체 생성 시 필수 값을 설정하도록 강제할 수 있음
	public LoginVO(int id, String name, String birthDate, String username, String password, String phone, 
					String email, String address, String affiliation, String role, LocalDateTime createAt, LocalDateTime updatedAt) {
		
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.affiliation = affiliation;
		this.role = role;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	// Getter와 Setter 메서드들
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id; 
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    @Override
    public String toString() {
    	return "UsersDAO " +
    		   "[id=" + id +
    		   ", name=" + name +
    		   ", birthDate=" + birthDate +
    		   ", username=" + username +
    		   ", password=" + password +
    		   ", phone=" + phone +
    		   ", email=" + email + 
    		   ", address=" + address +
    		   ", affiliation=" + affiliation +
    		   ", role=" + role +
    		   ", createdAt=" + createdAt +
    		   ", updatedAt=" + updatedAt +
    		   "]";
    }
}

package com.sehyeon.psychology.dto;

// 회원가입 폼 데이터를 검증하고 전달하는 객체

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JoinCreateForm {

	@Size(min = 3, max = 25)
	@NotBlank(message = "사용자 ID는 필수 항목입니다.")
	private String username;
	
	@Size(min = 3, max = 25, message ="비밀번호는 8자 이상 20자 이하로 입력해주세요.")
	private String password1;

	 @NotBlank(message = "비밀번호 확인을 입력해주세요.")
	private String password2;
	
	@NotBlank(message = "이름은 필수 항목입니다.")
	private String name;
	
	@NotBlank(message = "생년월일을 입력해주세요.")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "생년월일 형식이 올바르지 않습니다. (예: 1999-12-31)")
	private String birthDate;
	
	@Pattern(regexp = "^(010|011|016|017|018|019)-?[0-9]{4}-?[0-9]{4}$", message ="전화번호 형식이 올바르지 않습니다.")
	@NotBlank(message = "전화번호는 필수 항목입니다.")
	private String phone;
	
	private String address;
	
	private String affiliation;
	
	@Email(message ="올바른 이메일 형식이 아닙니다.")
	private String email;
	
	@NotBlank(message = "대상을 선택해주세요.")
	private String target;
	
	public String getTarget() {
		return target;
	}
	
	public String role;
	
	public void setTarget(String target) {
		if(target.equals("관리자")) {
			// '관리자'는 제외하고, '선수', '학부모', '지도자' 중 하나만 선택하도록 제한
            throw new IllegalArgumentException("관리자는 선택할 수 없습니다.");
        }
        this.target = target;
    }

}
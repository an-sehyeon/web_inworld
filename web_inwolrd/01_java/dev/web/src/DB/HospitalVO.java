package DB;

public class HospitalVO {										// HospitalVO 클래스 생성
	String hptl_nm;												// 병원의 이름을 저장하는 변수 
	String sido_cd;												// 병원이 위치한 지역 코드를 저장하는 변수
	int doc_num;												// 병원의 의사 수를 저장하는 변수
	
	public void setVO(String name, String sido, int num) {		// 병원 정보를 설정하는 메서드
		hptl_nm = name;											// 병원의 이름을 매개변수 name으로 설정			
		sido_cd = sido;											// 병원의 지역 코드를 매개변수 sido으로 설정
		doc_num = num;											// 병원의 의사 수를 매개변수 num으로 설정
	}
	
	// Object 클래스의 후손
	// toString 메서드를 자연스럽게 보유하게 됨
	// toString의 내용이 맘에 안든다면(무조건 안듬)
	// Overriding
	public String toString() {
		return "병원의 이름은 " + hptl_nm + ", 위치는 " + sido_cd + ", 의사 수는 " + doc_num + "입니다.";		// 메서드 호출 시 해당 문자열 반환
	}
	
}

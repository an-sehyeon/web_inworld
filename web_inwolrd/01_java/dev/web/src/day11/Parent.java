package day11;

import util.MyUtil;

public class Parent {											// 부모클래스인 Parent 클래스 생성

	String name = "Parent";										// name은 Prent클래스의 인스턴스 변수로서 "Parent"로 초기화 
	String familyName = "Leopard";								// familyName은 Prent클래스의 인스턴스 변수로서 "Leopard"로 초기화
	
	Parent(){													// Parent클래스의 기본 생성자 호출 시 실행될 코드
		MyUtil.p("Parent Created");								// 메시지 출력
	}
	
	void eat() {												// eat메서드 호출 시 아래 코드 출력
		MyUtil.p("나" + this.name + "은 저녁 식사를 합니다.");			// this.name = Parent 클래스내의 name인 Parent를 가리킴
		MyUtil.p("빵 먹기");
		MyUtil.p("밥 먹기");
		MyUtil.p("음료수 먹기");
		MyUtil.p("연타발에서 곱창 먹기");
	
	}
	
}

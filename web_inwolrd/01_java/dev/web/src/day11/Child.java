package day11;

import util.MyUtil;

public class Child extends Parent{							// Child클래스가 Parent클래스 상속

	String name = "Child";									// 인스턴스 변수 (자식 클래스에서 정의된 name)
	
	Child(){
		MyUtil.p("Child Created");							// MyUtil클래스의 p메서드를 통해 문자열 출력
	}
	
	void print() {											// print 메서드 호출시 아래 코드 실행
		String name = "Super Child";						// 지역 변수 name을 "Super Child"로 초기화 (이 메서드 내에서만 유효)
		MyUtil.p("name = " + name);							// 지역 변수 name을 출력
		MyUtil.p("this.name = " + this.name);				// this : 현재 객체안의 name인 Super Child 출력
		MyUtil.p("familyName = " + this.familyName);		// 상속받은 부모클래스에 있는 Parent클래스의 familyNmae 출력
		MyUtil.p("Super.name = " + super.name);				// 부모클래스의 name 변수 출력
	}
	
	@Override												// 부모클래스의 메서드를 재정의하기 위함
	void eat() {											// Parent클래스의 eat 메서드 재정의(오버라이딩)
		super.eat();										// Parent클래스의 eat객체 호출
		MyUtil.p("나" + this.name + "은 저녁 식사를 합니다.");		// 자식클래스의 인스턴스 변수인 name(Child)를 사용하여 추가적인 메시지 출력
		MyUtil.p("아이스크림 먹기");								// 자식클래스에서 추가한 메시지
		MyUtil.p("불량식품 아폰론 먹기");							// 자식클래스에서 추가한 메시지
	}
	
}

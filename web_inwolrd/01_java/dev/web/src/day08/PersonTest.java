package day08;

public class PersonTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Person 객체 p1생성 (기본 생성자 호출)
		Person p1 = new Person();								// Person 클래스의 기본 생성자 호출
		System.out.println("현재까지의 인간 수 : " + p1.numofman);	// 현재까지 생성도니 Person 객체의 수 출력
		System.out.println("이름 : " + p1.name + " 나이 : " + p1.age); // p1객체의 초기 이름과 나이 출력
		p1.name = "Steven King";						// p1 객체의 name 필드를 "Steven King"으로 설정
		p1.age = 60;									// p1 객체의 age 필드를 60으로 설정
		System.out.println("이름 : " + p1.name + " 나이 : " + p1.age); // 변경된 p1 객체의 이름과 나이 출력
		
		
		Person p2 = new Person();
		System.out.println("현재까지의 인간 수 : " + p2.numofman);
		
	
		System.out.println("현재까지의 인간 수 : " + Person.numofman);
	
		
		Person p3 = new Person("맥도날드");		// 맥도날드라는 이름을 가진 Person 객체 생성
		System.out.println("현재까지의 인간 수 : " + Person.numofman);
		
		Person p4 = new Person("르브론", 40);		// 르브론이라는 이름과 40의 나이를 가진 Person 객체 생성
		
		p1.introduce();		// p1객체의 이름과 나이를 출력하는 메서드 호출
		p2.introduce();		// p2객체의 이름과 나이를 출력하는 메서드 호출
		p3.introduce();		// p3객체의 이름과 나이를 출력하는 메서드 호출
		p4.introduce();		// p4객체의 이름과 나이를 출력하는 메서드 호출
	}

}


package day09;

import util.MyUtil;

public class ClassTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MyUtil.p(MyUtil.NEGATIVE + MyUtil.MAGENTA + "클래스 테스트" + MyUtil.END);
		
		Car car1 = new Car();		// Car클래스의 기본 생성자를 이용해 객체 car1 생성
		MyUtil.p(car1.toString());	// car1의 toString 메서드를 호출하여 기본 생성자로 초기화된 값 출력
		
		Car car2 = new Car("Black", "MyTrip", "Luxury");	// Car클래스에 매개변수 생성자를 이용해 객체 car2 생성
		MyUtil.p(car2.toString());	// car2의 toString 메서드를 호출하여 전달된 인자값이 반영된 문자열 출력

	}

}

class Car{
	// 인스턴스 변수 생성
	String color;
	String name;
	String type;
	
	// 기본 생성자
	Car(){
		// 인스턴스 변수 초기화
		color = "미정";
		name = "비밀";
		type = "완전기본형";
		MyUtil.p("Car() Called");		// 객체 생성시 호출되는 메서드 p를 이용해 출력
	}
	
	// 매개변수가 있는 생성자
	Car(String color, String name, String type){
		// this를 사용해 인스턴스 변수 초기화
		this.color = color;
		this.name = name;
		this.type = type;
		MyUtil.p("Car(...) Called");	// 객체 생성시 호출되는 메서드 p를 이용해 출력
	}
	
	public String toString() {		// 객체 정보를 문자열로 반환하는 메서드
		return "제 색상은 " + color + ", 이름은 " + name + ", 타입은 " + type;
	}
	
	
	// Code Block
	// 생성자(Constructor)가 여러 개가 있는데, 공통적으로 할 일이 있을 경우
	
	// 정적 초기화 블록
	static {
		MyUtil.p("static{      } Called");		// 객체가 생성될 때 단 한번만 호출되는 함수, 가장먼저 호출됨
	}
	
	
	// 인스턴스 초기화 블록
	{
		MyUtil.p("{     } Called");				// 객체가 생성될 때마다 호출되는 함수
	}
}

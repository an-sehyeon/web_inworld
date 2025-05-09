package day11;

import java.util.Scanner;

import util.MyUtil;

public class ClassMain {

	public static void main(String[] args) {														
		// TODO Auto-generated method stub
		
		ClassC c = new ClassC();										// ClassC의 기본 생성자 호출, 부모클래스(ClassP) 생성자도 함께 호출됨
				
		System.out.println("어떤 폰 가지고 계세요?(1.일반폰, 2.스마트폰) : ");	// 코드 출력
		Scanner sc = new Scanner(System.in);							// 사용자 입력을 받기 위한 Scanner 객체 생성
		int sel = sc.nextInt();											// 입력받은 값을 int형인 sel변수에 대입
		
		Phone myPhone;													// Phone 타입의 myPhone변수를 선언 (Phone 또는 SmartPhone 객체를 가리킬 수 있음)
		if(sel == 1) {													// 입력받은 값이 1이라면?
			myPhone = new Phone();										// Phone()객체 생성
		}else {															// 1이 아니라면?
			myPhone = new SmartPhone();									// SmartPhone()객체 생성
		}
		
		myPhone.sendText("asdfasdfasdfasfas");							// myPhone 객체에서 문자보내기 기능 호출, 문자열 매개변수로 전달
		myPhone.call();													// myPhone 객체에서 전화걸기 기능 호출
		SmartPhone sp = (SmartPhone)myPhone;							// myPhone을 SmartPhone으로 강제 형변환, 그러나 myPhone이 SmartPhone 객체여야만 형변환이 가능함
		sp.installApp();												// sp 객체에서 앱 설치 기능 호출 (SmartPhone에만 있는 기능)
		sp.webSerfing();												// sp 객체에서 웹 서핑 기능 호출 (SmartPhone에만 있는 기능)
	}

}

class Phone{															// Phone 클래스 생성
	public void sendText(String str) {									// 문자보내기 기능, sendText의 문자열을 str이 값을 전달받음
		if(str.length() > 80) {											// str 문자열의 길이가 80자 초과라면?
			MyUtil.p("문자열이 너무 깁니다.");								// 코드출력
		}else {															// str 문자열의 길이가 80자 미만이라면?
			MyUtil.p(str);												// str 문자 출력
		}
	}
	
	public void call() {												// 전화걸기 기능 구현
		MyUtil.p("Calling...");											// 코드 출력
	}
}

class SmartPhone extends Phone{											// SmartPhone 클래스 생성, Phone클래스 상속받음
	public void installApp() {											// 앱 설치 기능 구현
		MyUtil.p("INstalling App....");									// 코드 출력			
	}
	
	public void webSerfing() {											// 웹서핑 기능 구현
		MyUtil.p("Web Swerfing...");									// 코드 출력
	}
	
	@Override
	public void sendText(String str) {									// class Phone의 sendText 변경 
		MyUtil.p(str);													// 입력된 문자열 출력
	}
}












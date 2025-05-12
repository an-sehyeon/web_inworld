package day08;

import util.MyUtil;

public class Person {
	
	// Field
	// Instance 변수 (객체별로 개별적으로 유지되는 변수)
	public int age;
	String name = "Default";		// 이름의 기본값을 "Default"로 설정
	
	// Class 변수(static) (모든 객체가 공유하는 변수)
	public static String place = "The Earth";		// 모든 Person 객체가 공유하는 변수
	public static int numofman = 0;					// 생성된 Person 객체의 수를 세는 변수
	
	// Constructor (생성자)
	// 여러 타입으로 만들 수 있다.	
	Person(){						// 기본 생성자, 인자 안받음
		MyUtil.p("인간이 탄생하였도다");	// 생성될때마다 메시지를 출력
		numofman++;					// Person객체가 생성될때마다 numofman 값이 1씩 증감됨
	}

	
	Person(String x){		// 이름을 인자로 받는 생성자
		name = x;			// 전달받은 인자값을 name에 저장
		MyUtil.p("인간 " + name + "이(가) 탄생하였도다.");
		numofman++;			// Person객체가 생성될때마다 numofman 값이 1씩 증감됨
		
	}
	
	
	Person(String x, int n){	// 이름과 나이를 인자로 받는 생성자
		name = x;				// 전달받은 인자값을 name에 저장
		age = n;				// 전달받은 인자값을 age에 저장
		MyUtil.p("인간 " + name + "(" + age + "살)이(가) 탄생하였도다.");
		numofman++;				// Person객체가 생성될때마다 numofman 값이 1씩 증감됨
		
	}
	
	public void introduce() {
		System.out.println("저 " + name + "은(는) " + age + "살입니다. 성격 안좋습니다.");
	}
}

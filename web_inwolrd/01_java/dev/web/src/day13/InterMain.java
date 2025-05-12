package day13;

import util.MyUtil;

public class InterMain {										// MyClass와 MyInterface의 기능을 테스트하는 메인 클래스

	public static void main(String[] args) {

		MyClass mc = new MyClass();								// MyClass의 기본 생성자를 호출하여 인스턴스 mc를 생성
		MyUtil.p("mc.aaa = " + mc.aaa);							// MyClass의 인스턴스 변수인 aaa 출력(값 : 100)
//		MyUtil.p("MyClass.aaa = " + MyClass.aaa); 				// MyClass에서 새롭게 정의하는 경우  static속성은 사라짐
		MyUtil.p("MyInterface.aaa = " + MyInterface.aaa);		// MyInterface에서 정의한 aaa출력
		
		mc.dMethod();											// MyClass의 default 메서드 dMethod 호출
		mc.dMethod2();											// MyClass에서 재정의한 dMethod2 호출
		
//		mc.sMethod();											// MyClass에서는 static 메서드 호출X
		MyInterface.sMethod();									// MyInterface의 static 메서드 호출
		
		System.out.println("\n");
//		MyInterface mi = new MyInterface();						// 인터페이스는 직접 인스턴스를 만들 수 없음
		MyInterface mi = new MyClass();							// MyClass를 통해 MyInterface 타입의 객체 mi 생성
		mi.dMethod2();											// static 메서드는 인터페이스에서만 호출 가능
//		mi.sMethod();
	}

}

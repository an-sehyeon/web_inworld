package day13;

import util.MyUtil;

public class MyClass implements MyInterface{				// MyClass 클래스는 MyInterface 인터페이스를 구현

	public int aaa = 1000;									// aaa는 MyClass의 인스턴스 변수로, 1000으로 초기화
															// 인터페이스의 aaa와는 별개의 변수
	
	public void myMethod() {								// 인터페이스에서 정의한 myMethod 구현
		
	}
	
	public void dMethod2() {								// 인터페이스의 default 메서드를 새롭게 정의하면, 기존 default는 사라진다.
		MyUtil.p("[dMethod2] 난 베끼지 않는다 새롭게 정의할 뿐");
	}

}

package day13;

import util.MyUtil;

public interface MyInterface {					// MyInterface라는 인터페이스 생성
	
	int aaa = 500;								// 인터페이스에서 변수를 정의하면 자동으로 static이 된다.
	
	void myMethod();							// 인터페이스에서 선언된 추상 메서드, 구체적인 동작을 정의X, 매개변수X 즉 어떤 입력도 받지않음
												// 인터페이스를 구현하는 클래스에서 구체적인 동작을 반드시 구현해야됨
	
	default void dMethod() {					// default는 인터페이스가 메서드를 구현한다.
		MyUtil.p("[dMethod] Called");
	}
	
	default void dMethod2() {					// 두번째 default 메서드
		MyUtil.p("[dMethod2] Called");
	}
	
	static void sMethod() {						// static 메서드는 인터페이스에 직접 구현되며, 인터페이스 자체에서 호출 가능
		MyUtil.p("[sMethod] 누가 날 부르셨어요?");
	}

}

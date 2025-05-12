package day09;

import util.MyUtil;

public class AuthEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MyClass c = new MyClass();				// MyClass클래스의 생성자를 이용해 객체 c 생성
		MyUtil.cp("Auth Example");				// MyUtil클래스의 cp 메서드를 이용해 문자열 출력
		MyUtil.p("c.var1 = " + c.var1);			// MyClass의 var1 호출
		c.var1 = 100;							// 변수를 외부에서 바꿀 수 있다? => 위험성 존재
		MyUtil.p("c.var1 = " + c.var1);			// 변경된 var1 값 출력
		
		//MyUtil.p("c.var2 = " + c.var2); 		// var2는 private 변수이므로 외부에서 접근할 수 없어 컴파일 오류 발생
		MyUtil.p("c.var2 = " + c.getVar2());	// var2를 getVar2 메서드를 간접적으로 접근하여 값 출력
		c.setVar2(5000);						// setVar2 메서드를 이용해 var2 값을 5000으로 변경 
		MyUtil.p("c.var2 = " + c.getVar2());	// 변경된 var2 확인
		
	}

}

class MyClass{
	// 인스턴스 변수 선언
	public int var1 = 3;					// public 접근제어자로 선언된 var1, 외부에서 직접 접근 가능
	private int var2 = 10;					// private 접근제어자로 선언된 var2, 외부에서 접근 불가

	// @Getter
	public int getVar2() {					// var2를 사용하는 방법
		return var2;
	}
	
	// @Setter
	public void setVar2(int var) {			// var2를 바꾸고 싶을 때 방법
		var2 = var;
	}
	
	// 변수는 대부분 private
	// => 변수마다 set/get 메서드가 존재할 가능성이 높음
	// 변수가 100개다..... => set/get 메서드를 내가 100개씩 개발??
	
	// getter / setter는 추후 Spring에서 lombok 라이브러리를 통해 
	// 자동생성 시킬 수 있다.

}
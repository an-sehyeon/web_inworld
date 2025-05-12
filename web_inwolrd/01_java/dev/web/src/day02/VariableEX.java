


package day02;		// 패키지 선언

public class VariableEX {		// 클래스 선언

	public static void main(String[] args) {		// 메인 메서드, 가장 먼저 실행되는 메서드 
		System.out.println("Variable Example");
		p("Variable Example II");		// 아래 정의된 p 메서드를 호출하면서  "Variable Example II"라는 문자열을 인수로 전달
		
		// 1. int, long, float, double
		int var1 = 30;	// int형 변수 var1을 30에 대입, 정수를 정수에	
		long var2 = 100;	// long형 변수 var2을 100에 대입, 정수를 정수보다 큰 곳에
		
		//float var3 = 3.13564987;	// 소수를 입력하면 기본 타입을 double로 인식 -> 오류
		float var3 = 3.13564987f;	// F를 주면 float로 인식
				
		double var4 = 3.1;
		
		float var5 = (float)3.1;  // float로 형변환, explicit cast, 명시적 형변환
		int var6 = (int)var4;	// 소수점 이하를 포기하고 정수로 출력
		
		p("var 6 = " + var6);
		
		float var7 = 3.12341654644654f;
		double var8 = 3.12314346512313;
		
		p("var7 ="+ var7); 
		p("var8 ="+ var8); 
	
		// 2. char - 저장은 숫자타입으로 저장, 읽을 때는 문자로 읽힘
		char ch1 = 'a';		// ASCII (American Standard Code for International Interchange)
		char ch2 = 97;		
		char ch3 = 0x61;	// 0x 는 16진수임을 의미
		p("ch1, 2, 3 = " + ch1 + ch2 + ch3);
		
		// 3. String
		String myName = "BTS";
		p("My name is " + myName);
		
		// 4. boolean : true 또는 false
		boolean b1 = true;
		boolean b2 = 3 > 5;
		p("b1, b2 = " + b1 +" "+ b2);
	}
	
	public static void p(String str) {		// p 메서드 : 문자열을 매개변수로 받아 콘솔에 출력하는 메서드
		System.out.println(str);		// 전달된 문자열을 콘솔에 출력
	}
	
}

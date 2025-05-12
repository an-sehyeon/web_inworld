package day13;

import util.MyUtil;

public class CalApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MyUtil.cp(MyUtil.BOLD + "Super Calculator De Jesus");		// MyUtil클래스의 cp메서드를 호출하여 문자열 출력
		
//		CalculatorDummy c = new CalculatorDummy();					// CalculatorDummy클래스의 기본 생성자를 호출하여 인스턴스 c를 생성
		Calculator c = new Calculator();							// Calculator클래스의 기본 생성자를 호출하여 인스턴스 c를 생성
			
		c.setOperand(10, 21, 30);									// setOperand 메서드를 호출하여 연산에 필요한 세 개의 값을 설정
		MyUtil.p("합계는 " + c.sum());								// MyUtil클래스의 p메서를 호출하여 문자열과, 연산하기 위해 설정해놓은 값의 총합 출력
		MyUtil.p("평균은 " + c.avg());								// MyUtil클래스의 p메서를 호출하여 문자열과, 연산하기 위해 설정해놓은 값의 평균 출력
		
		
	}

}

class CalculatorDummy implements CalculatorI{						// CalculatorDummy 클래스가 CalculatorI 인터페이스 구현

	@Override														// 테스트 목적		
	public void setOperand(int aaa, int bbb, int ccc) {				// 어떤 값이 전달되더라도 아무 동작을 하지 않는 메서드
		
	}

	@Override
	public int sum() {												// 인자값을 어떤걸 넣든 100을 리턴
		return 100;
	}

	@Override
	public double avg() {										 	// 인자값을 어떤걸 넣든 30.0을 리턴
		return 30.0;
	}
}
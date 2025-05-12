package day13;

public class Calculator implements CalculatorI{			// Calculator 클래스가 CalculatorI 인터페이스를 구현

	int left, right, center;							// 세 개의 피연산자를 저장할 인스턴스 변수 선언
	
	@Override
	public void setOperand(int f, int s, int t) {		// CalculatorI 인터페이스에서 정의된 메서드 구현 
														// 전달받은 세 개의 값을 인스턴스 변수에 저장
		left = f;										 
		right = s;
		center = t;
	}

	@Override
	public int sum() {									// sum함수는 지정된 세 개의 피연산자를 더하는 기능
		return left + right + center;					// 총합을 리턴					
	}

	@Override
	public double avg() {								// avg함수는 저장된 값의 평균을 구해주는 기능
		return (int)(sum() / 3D * 1000) / 1000D;		// 평균을 구해 소수점 셋째 자리까지 반올림하여 리턴
	}

	
	
}

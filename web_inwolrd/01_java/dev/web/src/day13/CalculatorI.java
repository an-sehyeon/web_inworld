package day13;

public interface CalculatorI {								// CalculatorI라는 계산기 기능을 정의하는 인터페이스 생성
	void setOperand(int first, int secomd, int third);		// 피연산자를 설정하는 메서드, 세 개의 정수 피연산자를 인자로 받음
	int sum();												// 인스턴스에 저장된 피연산자들의 합을 구함
	double avg();											// 세 피연산자의 평균을 소수점 자리까지 계산 
	

}

package day15;

public class ExceptionTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calculator c = new Calculator();								// Calculator객체를 생성해 c변수에 저장 
		c.setOperand(100, 27);											// setOperand 기능을 활용해 피연산자 설정
		c.divide1();													// 0으로 나누기, 오류 발생 가능성 없음	
		
		c.setOperand(100, 0);
		// c.divide1();  												// 0 나누기에 대한 대비가 안되어있어 프로그램 예외 종료
		c.divide2();													// 예외처리한 나누기
		
		try {
			c.divide3();												// 예외 발생 가능성 있는 나누기
		}
		catch(Exception e) { e.printStackTrace(); }						// 예외 발생시 추적해서 출력
		
		
		c.setOperand(100000, 0);										// 왼쪽 피연산자 100000으로 설정
		try {
			c.divide4();												// 사용자가 정의한 예외 발생 가능성 있는 나누기
		}
		catch(Exception e) { e.printStackTrace(); }
		
		System.out.println("프로그램 완벽 종료");
	}
	
	void test() throws Exception {										// test 메서드는 Calculator객체를 생성하고 divide3() 메서드를 호출
		Calculator c = new Calculator();
		c.divide3();
	}

}

class Calculator {
	int left, right;																		// 왼쪽과 오른쪽 피연산자를 각각 변수에 저장
	
	public void setOperand(int left, int right) {											// 피연산자를 설정하는 메서드
		this.left = left;																	// 왼쪽 피연산자 left변수에 저장		
		this.right = right;																	// 오른쪽 피연산자 right변수에 저장
	}
	
	// 무대비 나누기 (예외 처리 없음)
	public void divide1() {
		System.out.println("[divide()] " + left/right);										// 왼쪽 피연산자 나누기 오른쪽 피연산자, 결과 출력
	}
	
	// 완벽한 예외 대비
	public void divide2() {
		try {
			System.out.println("[divide()] " + left/right);									// 나눗셈 결과 출력
		}
		catch(ArithmeticException e) {														// 예외 발생 시 처리 
			System.out.println("[divide()] 아리스메틱 오류 발생!");
			System.out.println("[divide()][B001] 오류 데이터 : " + left + ", " + right);
			System.out.println("[divide()] 잼민이는 집에 가");
			System.out.println(e.getMessage());												// 예외 메시지 출력
			System.out.println(e.toString());												// 예외 정보 출력	
			e.printStackTrace();															// 예외 스택 추적해서 출력
		}
	}
	
	// 예고 또는 경고
	public void divide3() throws Exception {  												// 나 울 가능성 있다, 예외 날 수 있다
		System.out.println("[divide()] " + left/right);
	}
	
	// 사용자(개발자, 나)가 예외를 강제로 발생시키는 메서드
	public void divide4() throws Exception {
		if(left >= 10000) 																	// left가 10000 이상인 경우 나누기를 하고 싶지 않다면?
			throw new Exception("10000 이상 입력하셨습니다 맞을래요?");								// 예외 발생
		
		System.out.println("[divide()] " + left/right);										// 나눗셈 결과 출력
	}
}









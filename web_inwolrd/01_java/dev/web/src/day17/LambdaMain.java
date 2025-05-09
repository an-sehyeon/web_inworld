package day17;

import java.util.ArrayList;

public class LambdaMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 1. Standard
		CalculableClass cc = new CalculableClass();									// CalculableClass 객체를 cc변수에 저장
		cc.calculate(100, 1000);													// calculatge메서드를 호출해 100, 1000 매개변수 전달
		// 수식이 오래되었다... 바꿔 => 본체를 바꿔야 함
		
		// 2. Anonymous Instance(익명객체)
		Calculable c1 = new Calculable() {											// Calculable 인터페이스를 구현하는 익명 객체를 생성 후 calculate 메서드를 오버라이드
			public void calculate(int x, int y) {
				System.out.println("[익명객체-일반] " + (x*y));						
			}
		};
		c1.calculate(100, 1000);													// 익명객체의 calculate 메서드 호출
		
		// 3. Lambda I : 람다식으로 정의하면 자동으로 메서드와 매핑
		// 인터페이스가 반드시 1개 (구현을 해야하는) 메서드만 있다면 가능
		Calculable c2 = (x, y) -> { System.out.println("[람다정식] " + (x*y)); };		
		c2.calculate(100, 1000);
		
		// 4. Lambda II : return 값이 있는 경우
		CalculableReturn c3 = (x) -> { return 3.14 * x * 2; };
		System.out.println("[람다리턴] " + c3.calculate(100));
		
		// 5. 인풋이 1개 뿐이면 괄호 생략
		CalculableReturn c4 = x -> { return 3.14 * x * 2; };
		System.out.println("[람다리턴] " + c4.calculate(100));
		
		// 6. 리턴이 한 줄이면 중괄호도 생략
		CalculableReturn c5 = x -> 3.14 * x * 2;
		System.out.println("[람다리턴] " + c5.calculate(100));
		
		// 7. 인터페이스를 내가 만들어야 하나요? 별 의미도 없는데
		// UnaryOperator : 1개 입력, 1개 출력
		// BinaryOperator : 2개 입력, 1개 출력
		// Consumer, forEach
		
		// 8. 람다식의 대표적인 예제 -> forEach
		ArrayList<String> ar = new ArrayList();
		ar.add("Steak");
		ar.add("Striper");
		ar.add("Rock Group");
		
		for(String name : ar) {
			System.out.println(name);
		}
		
		ar.forEach(name -> System.out.println(name));
		// 입력이 들어가서 아무 가공없이 사용될 경우
		ar.forEach(System.out::println);	
	}

}

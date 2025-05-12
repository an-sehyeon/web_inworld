package day17;

public class CalculableClass implements Calculable { 	// Calculable 인터페이스를 구현하는 CalculableClass 클래스
	public void calculate(int x, int y) {				// Calculable 인터페이스에서 정의된 calculate메서드 구현
		System.out.println("[일반객체] " + (x+y));			// x와 y의 합계 출력
	}
}

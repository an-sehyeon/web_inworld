package day12;

public class RefTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Ref1 ref1 = new Ref1();	// Ref1 클래스의 인스턴스를 생성
	}
		// Ref1 객체를 생성하면 Ref2 객체를 생성하고 Ref2 객체를 생성하면 다시 Ref1 객체를 생성, 객체 간의 상호 무한 호출로 메모리 부족

}
class Ref1{						// 클래스 Ref1 정의
		Ref2 ref2;				// Ref1 클래스는 Ref2 클래스 타입의 필드를 가짐
		
		Ref1(){					// Ref1의 생성자. Ref1 객체가 생성될 때 Ref2 객체를 생성
			ref2 = new Ref2();	// Ref2 객체가 생성될 때 다시 Ref1 객체를 생성하므로 순환 참조 발생
		}
	}
	
class Ref2{						// 클래스 Ref2 정의
	Ref1 ref1;					// Ref2 클래스는 Ref1 클래스 타입의 필드를 가짐
	
	Ref2(){						// Ref2의 생성자. Ref2 객체가 생성될 때 Ref1객체를 생성
		ref1 = new Ref1();		// Ref1 객체가 생성도리 때 다시 Ref2 객체를 생성하므로 순환 참조 발생
	}
}


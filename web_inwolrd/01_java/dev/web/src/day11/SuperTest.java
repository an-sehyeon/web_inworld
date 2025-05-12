package day11;

public class SuperTest {							// Parent클래스와 Child클래스를 test하기위한 SuperTest 클래스 생성

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Parent p = new Parent();					// p : Parent클래스의 객체 생성 및 Parent 생성자 호출 
		System.out.println("\n");					
		Child c = new Child();						// c : Child클래스의 객체 생성 및 Child 생성자 호출
		System.out.println("\n");	
		c.print();									// Child클래스의 print 메서드 호출
		System.out.println("\n");
		p.eat();									// Parent클래스의 eat 메서드 호출
		System.out.println("\n");
		c.eat();									// Child클래스에서 오버라이드된 eat 메서드 호출 (부모 메서드에서 추가된 코드까지 출력)
		
	}

}


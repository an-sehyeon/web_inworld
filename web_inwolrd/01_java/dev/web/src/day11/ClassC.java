package day11;

import util.MyUtil;

public class ClassC extends ClassP {	// ClassC 클래스가 ClassP클래스를 상속
	
	ClassC(){							// ClassC 클래스의 생성자 정의
		MyUtil.p("Class C Created");	// ClassP를 상속받았기 때문에 생성자를 호출하면 ClassP의 생성자가 호출된 뒤 ClassC 생성자가 실행됨
	}

}

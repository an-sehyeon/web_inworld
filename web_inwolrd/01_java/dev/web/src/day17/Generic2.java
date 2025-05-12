package day17;

import java.util.ArrayList;

public class Generic2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LuxuryBox<Instrument> box1 = new LuxuryBox();		// LuxuryBox<Instrument>타입의 box1생성
		LuxuryBox<Violin> box2 = new LuxuryBox();			// LuxuryBox<Violin>타입의 box2생성
		LuxuryBox<Flute> box3 = new LuxuryBox();			// LuxuryBox<Flute>타입의 box3생성
		// LuxuryBox<Bat> box4 = new LuxuryBox();			// extends Instrument 조건에 의해 입구컷 당함

		
		Bat bat1 = new Bat();								// bat1 객체를 생성
		System.out.println(box3.<String>get("Cello"));		// get 메서드를 사용하여 문자열 출력
		System.out.println(box3.<Bat>get(bat1));			// get 메서드를 사용하여 bat1객체를 가져옴
	}

}

class Instrument {   }										// Instrument 클래스 정의 (악기 클래스의 기본이 되는 클래스)
class Violin extends Instrument {   }						// Violin 클래스 정의 (Instrument를 상속받아 생성된 악기 클래스)
class Flute extends Instrument {   }						// Flute 클래스 정의 (Instrument를 상속받아 생성된 악기 클래스)

class SportsTool {   }										// SportsTool 클래스 정의
class Bat extends SportsTool {   }							// Bat 클래스 정의 (SportsTool을 상속받음)

class LuxuryBox<T extends Instrument> {						// LuxuryBox 클래스 정의, Instrument 타입을 상속하는 객체만 저장 가능한 제네릭 클래스
	ArrayList<T> item = new ArrayList();					// Instrument 또는 하위 클래스만 저장 가능한 ArrayList 생성
	
	public <T> T get(T data) {								// 제네릭 메서드 get : 어떤 타입의 데이터라도 받아 반환하는 메서드
		return data;
	}
}









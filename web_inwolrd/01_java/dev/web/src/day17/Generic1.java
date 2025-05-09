package day17;

import java.util.ArrayList;

public class Generic1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Food ramen = new Food("라면", "그냥 끓이면 됨 아님 날로 먹음");		// Food 객체를 생성후 ramen변수에 저장
		
		// OldBox
		OldBox ob = new OldBox();									// OldBox 객체를 ob변수에 저장
		ob.add(ramen);												// ob객체에 ramen데이터 추가
		System.out.println(((Food)(ob.get(0))).getRecipe());		// 추가된 ramen객체의 Recipe 출력
		
		// NewBox
		NewBox<Food> nb = new NewBox();								// NewBox에 Food타입으로 제네릭 설정 후 객체 생성 후 nb변수에 저장
		nb.add(ramen);												// nb에 ramen 데이터 추가		
		//  nb.add(new String("AAA"));  							// Generic을 사용하면 정한 타입 외에는 사용 불가
		System.out.println(nb.get(0).getRecipe());					// 추가된 ramen객체의 Recipe 출력			
	}

}

class Food {														// Food 클래스 정의
	String name;													// 음식 이름을 저장하는 name변수 
	String recipe;													// 음식 조리법을 저장하는 recipe변수 
	
	Food(String name, String recipe){								// 생성자 name과 recipe 변수 초기화
		this.name = name;
		this.recipe = recipe;
	}
	
	String getRecipe() {											// Recipe값을 반환하는 메서드
		return recipe;
	}
}

class OldBox {														// 제네릭이 없는 OldBox 클래스 정의
	ArrayList item = new ArrayList();								// 다양한 객체를 저장할 ArrayList 생성후 item변수에 저장	
	
	void add(Object o) {											// 모든 타입의 객체 추가 가능
		item.add(o);
	}
	
	Object get(int index) {											// 저장된 객체 반환
		return item.get(index);
	}
}

class NewBox<T> {													// 제네릭이 적용된 NewBox 클래스 정의
	// T, E, (K, V) : T=Type, E=Element, K=Key, V=Value
	ArrayList<T> item = new ArrayList();							// 특정 타입만 저장 가능한 ArrayList
	
	void add(T o) {													// 특정 타입의 객체 추가 가능
		item.add(o);
	}
	
	T get(int index) {												// 저장된 객체 반환			
		return item.get(index);
	}
}

class StrangeBox<DisneyLand> {										// StrangeBox 클래스 : 제네릭 타입 이름을 DisneyLand로 정의
	DisneyLand name;												// DisneyLand 타입의 name변수 
}
package day16;

import java.util.HashSet;
import java.util.TreeSet;

public class SetCollection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 1. HashSet
		HashSet hs = new HashSet();								// HashSet객체를 생성해 hs변수에 저장
		hs.add("매트릭스");										// hs에 "매트릭스" 추가	
		hs.add("주글래살래");										// hs에 "주글래살래" 추가
		hs.add("쇼생크탈출");										// hs에 "쇼생크탈출" 추가
		hs.add(new Puppy());									// hs에 Puppy 객체 추가
		System.out.println("HashSet : " + hs);					// HashSet 출력
		
		// 2. TreeSet
		TreeSet ts = new TreeSet();
		ts.add("매트릭스");
		ts.add("주글래살래");
		ts.add("쇼생크탈출");
		ts.add("터미네이터");
		ts.add("다크나이트");
		//ts.add(new Puppy());  => Comparator 구현 시 가능할 껄?
		System.out.println("TreeSet : " + ts);
		
		TreeSet subSet1 = (TreeSet)ts.subSet("매트릭스", "주글래살래");
		System.out.println("SubSet1 : " + subSet1);
		
		TreeSet subSet2 = (TreeSet)ts.subSet("매트릭스", true, "주글래살래", true);		// "매트리스"부터 "주글래살래"까지 포함하는 부분 집합을 만들어 subSet2에 저장
		System.out.println("SubSet2 : " + subSet2);
		
		TreeSet subSet3 = (TreeSet)ts.subSet("다", "텨");								// 정렬된 데이터 순으로 "다" 부터 "텨" 까지의 요소를 포함하는 부분 집합을 subSet3에 저장
		System.out.println("SubSet3 : " + subSet3);
		
		
	}
}

class Puppy {														// Puppy 클래스 생성
	String name = "쪼랭이";											// name 설정
	int age = 4;													// age 설정
	
	@Override 
	public String toString() {									
		return "Puppy{name = '" + name + "', age = " + age + "}";	// 문자열로 리턴
	}
}

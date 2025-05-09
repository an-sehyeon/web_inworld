package day16;

import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

public class MapCollection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// REST : 리소스와 http명령어로 웹 접속을 하는 프로토콜
		HashMap<String, Integer> hm = new HashMap(); 			// <  > : Generic, String을 키로, Integer을 값으로 사용하는 HashMap 생성 후 hm변수에 저장
		TreeMap<String, Integer> tm = new TreeMap(); 			// <  > : Generic, String을 키로, Integer을 값으로 사용하는 TreeMap 생성 후 tm변수에 저장
		
		// (1) put() : 데이터 저장
		String[] food = {"Steak", "Chicken", "Rice", "Curri"};	// food라는 String타입의 배열 생성 후 배열에 음식 이름 삽입
		int[] price = {10000, 15000, 9000, 500};				// price라는 int타입의 배열 생성 후 금액 삽입
		
		for(int i=0; i<food.length; i++) {						// i를 0부터 1씩 증감하며 food배열의 요소값만큼 반복
			hm.put(food[i], price[i]);							// food배열과 price배열의 i번째 인덱스를 hm변수에 저장
			tm.put(food[i], price[i]);							// food배열과 price배열의 i번째 인덱스를 tm변수에 저장
		}
		
		System.out.println("HashMap : " + hm);					// HashMap은 저장순서를 보장하지 않음, 입력 순서와 출력 순서는 다를 수 있음
		System.out.println("TreeMap : " + tm);					// TreeMap은 오름차순으로 정렬해 데이터를 저
		
		// (2) get() : 데이터 조회
		System.out.println("Chicken : " + hm.get("Chicken"));	// hm변수에 "Chicken"데이터 조회 후 출력
		
		// 모두 꺼내고 싶을 때
		// 1) Enhanced for
		for(String key : hm.keySet()) {							// HashMap의 모든 키-값 쌍을 하나씩 꺼내어 출력
			System.out.println(key + " : " + hm.get(key));
		}

		
		// 2) Iterator()
		Iterator keys = tm.keySet().iterator();					// TreeMap의 키를 순서대로 순회하는 Iterator 생성
		
		while(keys.hasNext()) {									// 다음 키가 있을 동안 반복
			String key = (String)keys.next();					// 키를 하나 가져와 key 변수에 저장
			System.out.println(key + " : " + tm.get(key));		// 키와 해당 키의 값 출력
		}

		// (3) remove(키값) : 데이터 삭제, replace(키값, Value) : 해당 키의 데이터를 Value로 변경
		hm.remove("Rice");										// hm의 Rice데이터 삭제
		hm.replace("Steak", 1000);								// hm의 "Steak"데이터의 값을 1000으로 변경
		System.out.println("New HM : " + hm);					// 변경된 hm 출력
		
		// Map Collection은 차후 등장할 JSON 형식을 해석하는데 유용하다.
		
	}

}

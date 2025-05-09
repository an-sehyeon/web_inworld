package day16;

import java.util.ArrayList;
import java.util.LinkedList;

public class ListCollection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 1. ArrayList
		ArrayList ar = new ArrayList();						// ArrayList객체를 생성해 ar변수에 저장
		
		// (1) 리스트에 요소 추가(add)
		ar.add("돈까스");										// ar배열에 "돈까스" 추가
		ar.add("치킨까스");									// ar배열에 "치킨까스" 추가
		ar.add("쌀국수");										// ar배열에 "쌀국수" 추가
		ar.add("라면");										// ar배열에 "라면" 추가
		System.out.println(ar);								// ar배열 출력
		
		for(int i=0; i<ar.size(); i++) {
			System.out.println(i + " : " + ar.get(i));  	// get(i) i번에 해당하는 데이터 가져오기
		}
		
		// (2) 요소 삭제
		ar.remove(1);										// ar배열의 2번째 인덱스 삭제
		ar.remove("돈까스");									// ar배열의 "돈까스"삭제		
		System.out.println("[메뉴 2개 삭제 후] " + ar);			// 문자열 + 현재 배열의 값 출려
		
		// (3) 요소 변경
		ar.set(0, "스파게티");									// ar배열의 첫번째 인덱스 값 "스파게티"로 변경
		System.out.println("[메뉴 변경 후] " + ar);				// 변경된 배열 출력

		try {														// 예외 발생 가능성 있는 코드
			System.out.println("[메뉴 변경 후] " + ar.get(100));		// ar배열의 101번째 인덱스 값을 get한다면?
		}
		catch(Exception e) {										// 예외 발생시 출력 코드
			System.out.println("[메뉴 변경 후] " + "니가 직접 해먹어");
		}
		
		// (4) size() : 요소 개수 구하기
		System.out.println("총 메뉴 개수는 " + ar.size() + "개 입니다"); 	// ar.size : ar배열의 요소 개수
		
		// (5) contains() : 요소 있는지 알아보기(true, false)
		System.out.println("contains : 라면 있나요 - " + (ar.contains("라면")? "응":"아니"));		// ar변수에 "라면"이 있다면 응, 없다면 아니 출력
		System.out.println("contains : 돈까스 있나요 - " + ar.contains("돈까스"));				// ar변수에 "돈까스"가 있다면 true, 없다면 false출력
		
		// 2. LinkedList
		LinkedList link = new LinkedList();						// LinkedList 객체를 생성해 link변수에 저장
		link.add("슬램덩크");										// link 리스트에 "슬램덩크"추가
		link.add(0, "트위스터스");									// link 리스트에 "트위스터스"추가
		link.add(1, "사랑의하츄핑");								// link 리스트에 "사랑의하츄핑"추가
		System.out.println("LinkedList : " + link);				// link 리스트 출력
		
		// 3. Performance
		ArrayList app1 = new ArrayList();						// ArrayList객체를 생성해 app1변수에 저장
		LinkedList app2 = new LinkedList();						// LinkedList객체를 생성해 app2변수에 저장
		
		final int SAMPLE = 10000;								// int타입의 SAMPLE 변수를 가진 final 클래스를 생성해 1000 할당
		for(int i=0; i<SAMPLE; i++) {							// i를 1씩 증감하며 10000번 반복
			app1.add(i);										// i를 app1 List에 담아줌
			app2.add(i);										// i를 app2 List에 담아줌
		}
		
		// read test
		long start, mid, end;									// 시간 측정을 위한 long타입의 변수들 생성
		start = System.currentTimeMillis();						// 현재시간을 밀리초 단위로 변환해서 start변수에 저장
		for(int i=0; i<SAMPLE; i++) {
			app1.get(i);
		}
		mid = System.currentTimeMillis();						// 현재시간을 밀리초 단위로 변환해서 mid변수에 저장
		for(int i=0; i<SAMPLE; i++) {
			app2.get(i);
		}
		end = System.currentTimeMillis();
		
		System.out.println("***** Read Competition *****");
		System.out.println("ArrayList : " + (mid-start));		// ArrayList가 1000번 반복문 실행되는 시간을 밀리초 단위로 출력
		System.out.println("LinkedList : " + (end-mid));		// LinkedList가 1000번 반복문 실행되는 시간을 밀리초 단위초 출력
		
		// insert test
		start = System.currentTimeMillis();
		for(int i=0; i<SAMPLE; i++) {
			app1.add(7000, i);									// app1변수에 i번째 인덱스에 7000을 삽입
		}
		mid = System.currentTimeMillis();
		for(int i=0; i<SAMPLE; i++) {
			app2.add(7000, i);									// app2변수에 i번째 인덱스에 7000을 삽입
		}
		end = System.currentTimeMillis();
		
		System.out.println("***** Insert Competition *****");
		System.out.println("ArrayList : " + (mid-start));		// ArrayList 1000번의 insert하는데 걸리는 시간 출력
		System.out.println("LinkedList : " + (end-mid));		// LinkedList 1000번의 insert하는데 걸리는 시간 출력
	}

}

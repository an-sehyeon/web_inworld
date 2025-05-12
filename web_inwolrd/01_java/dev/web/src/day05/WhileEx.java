
package day05;

import util.MyUtil;

public class WhileEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MyUtil.p("While Statement");
		
		int i = 0;
		while(i < 10) {		// i가 10보다 작을때까지 반복
			MyUtil.p("Current i = " + i);		
			i++;				// 반복될때마다 i가 1씩 증감
		}
		
		
		MyUtil.p("After While i = " + i); 	// i가 10보다 같거나 커야 반복문이 중단되기 때문에 현재 i의 값은 10
		
		
		// Infinity Loop
		i = 0;		// i를 다시 0으로 초기화
		while(true) {	// true일때 계속 반복
			MyUtil.p("This i is " + i);
			i++;
			if(i > 5)		// 만약 i가 5보다 크다면?
				break;			// 반복문 탈출
		}
		
		
	}

}

package day04;

import java.util.Random;

import util.MyUtil;

public class RandomEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyUtil.p("Random Example");
		
		Random rd = new Random();
		
		// Random Number Test
		MyUtil.p("nextInt() : " + rd.nextInt());		// int 범위의 모든 값을 랜덤하게 생성
		MyUtil.p("nextInt(100) : " + rd.nextInt(100));	// 0부터 99까지의 범위에서 랜덤한 정수를 생성
		MyUtil.p("nextFloat() : " + rd.nextFloat());	// 0.0 (포함)부터 1.0 (미포함) 사이의 랜덤한 부동 소수점을 생성
		MyUtil.p("nextDouble() : " + rd.nextDouble());	
		
		
		// nextInt(N) : 0부터 N-1까지 중 하나가 나오는 것을 이용하여
		// 주사위 범위(1~6) 내 숫자가 나오도록 만드시오
		int result1 = rd.nextInt(1,7); 	// 1부터 6까지의 범위에서 랜덤한 정수를 생성
		MyUtil.p("첫번째 주사위 숫자 : " + result1);
		
		int result2 = rd.nextInt(6) + 1; // 0부터 5까지의 범위에서 랜덤한 정수를 생성. 여기에 1을 더하면 1부터 6까지의 숫자가 됨.
		MyUtil.p("두번째 주사위 숫자 : " + result2);
		
		
		// Random Alphabet(A-Z)
		// 생각해볼 것 = 'A'는 10진수 65
		// 대문자 알파벳 중 하나를 뽑으시오.
		char rchar1 = (char) rd.nextInt(65,91);		// (65,91)은 65부터 90까지의 난수를 생성하고 이를 char로 형변환
		MyUtil.p("첫번째 Random Cahr : " + rchar1);	// 하여 대문자 알파벳 중 하나를 반환
													// 즉, 'A'(65부터) 'Z'(90)까지의 범위에서 랜덤하게 하나의 문자 출력
		
		
		char rchar2 = (char)(rd.nextInt(26) + 65);	// (26)은 0부터 25까지의 난수 생성. 여기에 65를 더해 65부터 90까지
		MyUtil.p("두번째 Random Cahr : " + rchar2);	// 이 값은 char로 변환되어 결과적으로 A(65)부터 Z(90)까지 
													// 랜덤하게 하나의 문자 출력
		
		
		
		// 대소문자중 Random Alphabet( A-Z 또는 a-z)
		// 생각해볼 것 : 'A' 65, 'a' 97
		// 과연 대소문자 중 하나는 어덯게 구현할까?
		
		char rchar = (char)(rd.nextInt(26) + 65 + 32 * rd.nextInt(2) );	
		// rd.nextInt(26) : 0부터 25까지의 난수 생성, 알파벳의 위치를 결정 ( 즉, 'A'부터 'Z').
		// + 65 : A의 아스키 코드 값인 65를 더해주면 A부터 Z까지 대문자 중 랜덤 선택됨
		// + 32 * rd.nextInt(2) : rd.next(2)는 0 또는 1을 생성
		// 0또는 1이 랜덤으로 나오는데
		// 0일 경우 : 32 * 0 = 0이 더해져서 대문자 유지
		// 1일 경우 : 32 * 1 = 32가 더해지며, 대문자에 32를 더해 소문자로 변환
		// 최종적으로 rchar는 대문자 A부터 Z 또는 소문자 a부터 z 중 하나를 랜덤 선택
				
		MyUtil.p("Random Cahr : " + rchar);
				
		// 문자를 표시하는 방식(ASCII)
		// A : 65(0 X 41) , a : 97(0 X 61)
		// 0 : 48(0 x 30) 
		// bit : 0 or 1, byte = 8bit, 01010101
		// bit를 원자라고 인식, byte를 분자라고 인식
		
	}

}


package day06;

import util.MyUtil;

public class ForEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// MyUtil클래스의 p메소드 호출
		MyUtil.p(MyUtil.BOLD + MyUtil.RED + "For Example" + MyUtil.END);
		
		// For
		for(int i = 0; i < 10; i++) {
			MyUtil.p("this i is " + i);
		}
		
		//MyUtil.p("this i is " + i);	// i는 지역변수, for문을 벗어났기 때문에 존재하지 않는 변수
		
		int k = 10000;
		for(k = 0; k < 10; k++) {
			MyUtil.p("[1] This k is " + k);
		}
		
		MyUtil.p("[1] Final k is " + k);	// k를 10000으로 초기화 했지만 for문에서 0으로 다시 초기화 하여 사용 후 
											// 10이 됐을 때 for문을 탈출했기 때문에 현재 k의 값은 10
		
		
		
		// do - while
		boolean isTeacher = true;
		do {
			MyUtil.p("야 이 바보야");
		} while(isTeacher != true);		// 출력이 안될것으로 예상했으나 do는 먼저 실행부터 한다.
		
		
		
		// 실습과제
		// 제어문을 사용하여 1부터 100까지를 출력하되,
		// 3의 배수만 제외하고 출력하시오.
		
		// 방법 1
		for(int i = 1; i <= 100; i++) {
			if(i % 3 != 0) {
				System.out.print(i + " ");
			}
		}
		System.out.println("");
		
		// 방법 2
		int j = 1;
		while (j <= 100) {
			if(j % 3 != 0) {
				System.out.print(j + " ");
			}
			j++;
		}
		System.out.println("");
		
		// continue 사용
		for(int i = 1; i <= 100; i++) {
			if(i % 3 == 0) continue;	// continue를 만나면 반복문의 처음으로 돌아간다.
			System.out.print(i + " ");
		}
		
		
		
	}

}

package day04;

import java.util.Scanner;

public class UserInput {

	public static void main(String[] args) {
		// Run > Run Configurations.. > argumets탭 > java UserInput > 20240813 Chang 3
		
		// TODO Auto-generated method stub
		// 1. 실행 시 입력하기
		System.out.println("작업일자 : " + args[0]);

		// 이름(작업자)과 작업유형 출력하기
		System.out.println("이름(작업자) : " + args[1] + ", 작업유형 : " + args[2]);
		
		// 없는 인덱스 사용 시 오류(예외), 예외는 오류의 일종 사용자가 커버 가능한 오류
		// System.out.println("이거는 ? " + args[3]);   
		
		System.out.println("");
		
		// 2. Scanner 사용하기
		Scanner sc = new Scanner(System.in);
		System.out.print("이름을 입력하세요>>> : ");
		String name = sc.next();    // 문자열로 입력받기
		System.out.println("앗 당신이 그 유명한 " + name + "님?");
		
		System.out.println("");
		
		System.out.print("문자 1을 입력하세요>>> : ");
		String a1 = sc.next();
		System.out.print("숫자 1을 입력하세요>>> : ");
		int a2 = sc.nextInt();
		System.out.println("a1 : " + (a1 + 1));		// sc.next (문자)로 입력받아서 입력받은 값과 1을 붙여줌
		System.out.println("a2 : " + (a2 + 1));		// sc.nextint (정수)로 입력받아서 입력받은 값과 1을 더해줌
		
	
	}
}

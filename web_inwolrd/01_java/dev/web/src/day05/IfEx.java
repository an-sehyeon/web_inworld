

package day05;

import java.util.Random;
import java.util.Scanner;

import util.MyUtil;

public class IfEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// MyUtil클래스의 p메서드를 출력
		MyUtil.p(MyUtil.RED + "If Example" + MyUtil.END);	// "If Example"을 빨간색으로 출력하고 이후 색상을 기본값으로 재설정
		MyUtil.p("");
		
		Random rd = new Random();		// Random 객체 생성
		int a = rd.nextInt(151);		// 0부터 150까지의 랜덤한 정수 생성
	
		
		if(a > 100) {			// 램덤으로 받은 정수인 a가 100보다 크다면?
			MyUtil.p(MyUtil.RED + "a는 매우 큽니다. : " + a + MyUtil.END);	// a의 값이 매우 크다고 빨간색으로 출력
		}else if(a > 50) {		// a가 50보다 크고 100보다 같거나 작다면?
			MyUtil.p(MyUtil.GREEN + "a는 큽니다. : " + a + MyUtil.END);	// a의 값이 크다고 초록색으로 출력
		}else {					// 위의 조건이 모두 아니라면?
			MyUtil.p("a는 결코 크지 않습니다. : " + a);						// a는 결코 크지 않다고 출력
		}
		
		
		
		
		// nesed
		// 영어와 수학 점수가 모두 60점 이상이면 Pass, 아니면 Fail
		// 영어 : **점, 수학 : **점, Pass or Fail
		// 과제 : 위 로직을 구현하시오.
		MyUtil.p("");
		MyUtil.p("첫번째 방법");			// 중첩 반복문
		
		int eng = rd.nextInt(101);		// 0부터 100까지 정수를 랜덤으로 받아 변수eng에 저장
		int mat = rd.nextInt(101);		// 0부터 100까지 정수를 랜덤으로 받아 변수mat에 저장
		
		if(eng >= 60) {
			if(mat >= 60) {
				MyUtil.p(MyUtil.GREEN + "영어 : " + eng + "점, 수학 : " + mat + "점 결과 : Pass " + MyUtil.END);
			}else {
				MyUtil.p(MyUtil.GREEN + "영어 : " + eng + "점, 수학 : " + mat + "점 결과 : Fail " + MyUtil.END);
			}
		}	
		else {
				MyUtil.p(MyUtil.GREEN + "영어 : " + eng + "점, 수학 : " + mat + "점 결과 : Fail " + MyUtil.END);
			
		}

		
		
		
		
		MyUtil.p("");
		MyUtil.p("두번째 방법");
		
		String PF = "";
		if(eng >= 60)
			if(mat>= 60)
				PF = "Pass";
			else
				PF = "Fail";
		else
			PF = "Fail";
		
		MyUtil.p(MyUtil.RED + "영어 : " + eng + "점, 수학 : " + mat + "점" + PF + MyUtil.END);
						
		
		
		
		
		
		MyUtil.p("");
		MyUtil.p("세번째 방법");			// 관계연산자 사용
		
		int eng1 = rd.nextInt(101);		// 0부터 100까지 정수를 랜덤으로 받아 변수eng1에 저장
		int mat1 = rd.nextInt(101);		// 0부터 100까지 정수를 랜덤으로 받아 변수mat1에 저장
		
		if(eng1 >= 60 && mat1 >= 60) {	// 입력받은 eng1와 mat1의 값이 모두 60이상이라면?
			MyUtil.p(MyUtil.GREEN + "영어 : " + eng1 + "점, 수학 : " + mat1 + "점 결과 : Pass " + MyUtil.END);		
		}else {							// 위 조건이 아니라면?							
			MyUtil.p(MyUtil.RED + "영어 : " + eng1 + "점, 수학 : " + mat1 + "점 결과 : Fail " + MyUtil.END);			
		}
		
		
		if(eng1 < 60 || mat1 < 60) {	// 입력받은 eng1와 mat1의 값이 모두 60미만이라면?
			MyUtil.p(MyUtil.RED + "영어 : " + eng1 + "점, 수학 : " + mat1 + "점 결과 : Fail " + MyUtil.END);		
		}else {							// 위 조건이 아니라면?							
			MyUtil.p(MyUtil.GREEN + "영어 : " + eng1 + "점, 수학 : " + mat1 + "점 결과 : Pass " + MyUtil.END);			
		}
		
		
		
		
		
		
		
		
		
		MyUtil.p("");
		MyUtil.p("Scanner 이용");
		
		Scanner sc = new Scanner(System.in);		// 사용자로부터 입력을 받기 위한 객체 생성
		
		MyUtil.p("영어점수를 입력하세요>>> : ");			
		int eng3 = sc.nextInt();						// 사용자로부터 영어점수를 정수로 입력받아 변수eng3에 저장
		MyUtil.p("수학점수를 입력하세요>>> : ");
		int mat3 = sc.nextInt();						// 사용자로부터 수학점수를 정수로 입력받아 변수mat3에 저장
		
		if(eng3 >= 60 && mat3 >= 60) {		// 입력받은 eng3와 mat3의 값이 모두 60이상이라면?
			MyUtil.p(MyUtil.GREEN + "영어 : " + eng3 + "점, 수학 : " + mat3 + "점 결과 : Pass " + MyUtil.END);		// Pass 초록색으로 출력
		}else {								// 위 조건이 아니라면?
			MyUtil.p(MyUtil.GREEN + "영어 : " + eng3 + "점, 수학 : " + mat3 + "점 결과 : Pass " + MyUtil.END);			// Fail 빨간색으로 출력
		}
		
		
		
		
		
		
	}

}

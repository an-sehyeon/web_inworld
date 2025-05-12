package day08;

import java.util.Random;

import util.MyUtil;

public class ArrayTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		MyUtil u = new MyUtil();					// NyUtil 클래스의 인스턴스를 생성하여 u를 통해 메서드 호출 가능	
		u.p("Array Test : Difficulty - GOD\n");			
		
		// 열 개의 이름, 열 개의 임의의 점수(소수점 1자리)
		// 점수 중 최고점과 최저점을 찾은 후 
		// 그에 해당하는 이름과 점수를 출력
		
		// 0. 타이틀 출력
		u.cp("최고점과 최저점 정보");			// MyUtil 클래스의 cp메서드 호출하여 타이틀을 랜덤 색상으로 출력
		
		// 1. 10개의 이름 설정
		String[] names = {"A", "B", "C", "D", "E", "F", "G","E", "F", "G", "H", "I", "J"}; 
	
		
		
		
		// 2. 10개의 임의의 점수 생성(소수점 1자리) 생성
		Random rd = new Random();
		
		// 첫번째 방법 : 0 ~  1사이의 소수를 뽑은 후 만드는 방법
		float score1 = rd.nextFloat();	// score1 : 0 ~ 1 사이의 소수
		score1 = score1 * 1000;			// score1 : 0부터 1000사이 소수
		score1 = (int)score1; 			// score1 : 0부터 1000까지의 정수
		score1 = score1 / 10;
		System.out.println("score1 : " + score1);

		// 두번째 방법 : 0 ~ 1000 사이의 정수를 뽑은 후 만드는 방법
		float score2 = rd.nextInt(1001) / 10f;	
		System.out.println("score2 : " + score2  + "\n");
		
		
		float[] scores = new float[10];				// 10크기의 float타입의 scores배열 생성	
		for(int i = 0; i < scores.length; i++) {	// scores길이 만큼 반복
			scores[i] = rd.nextInt(1001) / 10F;		// 0에서 1000 사이의 랜덤한 정수를 생성하여 10으로 나눈 후,
		}											// 소수점 1자리까지의 점수를 저장
		
		
		// 점수 출력 방법1
//		for(float score : scores) {					// scores 배열의 각 요소를 반복하며
//			u.p("score : " + score);				// 각 요소의 점수를 출력
//		}

		// 점수 출력 방법2
		for(int i = 0; i < scores.length; i++) {			// 배열의 길이만큼 반복하며 
			u.p("score[" + names[i] + "] : " + scores[i]);	// 인덱스번호를 이용해 저장된 해당 인덱스의 
		}													// names와 점수를 출력	
		
		
		
		
		// 3. 최고점과 최저점을 담을 변수 생성
		int indexTop = 0, indexBottom = 0;		// 최고점과 최저점을 담을 변수
		float scoreTop = 0, scoreBottom = 100;	// 현재 최고점과 최저점을 담을 변수
		
		
		// 4. 최고점과 최저점 찾기
		for(int i = 0; i < scores.length; i++) {	// scores배열 길이만큼 반복
			// 최고점인가 체크	
			if(scores[i] > scoreTop) {				// 현재 점수가 기존 최고점보다 크다면?
				scoreTop = scores[i];				// scoreTop변수에 저장 (점수 최신화)
				indexTop = i;						// 그리고 indexTop에 현재 i의 값 저장
			}
			
			// 최저점인가 체크
			if(scores[i] < scoreBottom) {			// 현재 점수가 기존 최저점보다 낮다면?
				scoreBottom = scores[i];			// scoreBottom 변수에 저장 (점수 최신화)
				indexBottom = i;					// 그리고 indexBottom에 현재 i의 값 저장
			}
			
		}
		
		
		// 5. 출력하기
		u.p("\nResult");
		u.p("Top Score : " + scoreTop + "(" + names[indexTop] + ")");
		u.p("Lowest Score : " + scoreBottom + "(" + names[indexBottom] + ")");
		

	}

}

package day14;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import util.MyUtil;

public class BaseClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MyUtil.cp("Base Class Example");										// MyUtil클래스의 cp메서드를 호출하여 문자출력
		
		// 1. System
		long ctime = System.currentTimeMillis();								// 현재 시간을 밀리초 단위로 변환한 값을 long타입의 ctime변수에 저장
		System.out.println("현재 시간 : " + ctime);								// 현재 시간 출력
		
		long point1, point2;													// long 타입의 변수 2개 생성
		long elapsedTime;														// 반복문 수행 시간을 저장할 변수 
		
		point1 = System.currentTimeMillis();									// point1변수에 현재 시간 저장 (for문 시작 시간)
		for(int i=0; i<1000000; i++) {											// 0부터 1000000까지 반복문 수행
			;
		}
		point2 = System.currentTimeMillis();									// point2변수에 현재 시간 저장 (for문 종료 시간)
		elapsedTime = point2 - point1;											// 반복문이 수행되는 시간의 결과값(point1과 point2의 차이)을 elapsedTime 변수에 저장
		System.out.println("for문 수행시간(ms) : " + elapsedTime);					// for문이 실행되는데 걸린 시간을 밀리초 단위로 출력
		
		
		// 2. StringBuffer
		System.out.println("\n" + "=========String:Buffer==========");
		StringBuffer sb = new StringBuffer();									// StringBuffer객체를 생성하여 sb변수에 저장
		sb.append("Eriks Steakhouse");											// sb에 "Eriks Steakhouse" 문자열을 저장
		System.out.println(sb);											
		sb.insert(6, "Famous ");												// 기존 sb 값에 6번째 인덱스에 "Famous"를 삽입
		System.out.println(sb);
		// sb.insert(100, "I am genius");  // 인덱스 범위(문자열의 길이)를 넘어가면 오류
		sb.delete(1, 10);														// sb의 2번째부터 10번째까지 삭제		
		System.out.println(sb);
		sb.delete(3, 1000);  // delete는 종료 인덱스를 큰 숫자를 넣어도 수행				// sb의 4번째부터 1000번째까지 삭제
		System.out.println(sb);
		
		// String, StringBuffer Performance Contest
		String app1 = "";														// String 타입의 app1변수를 빈 문자열로 초기화
		StringBuffer app2 = new StringBuffer();									// StringBuffer 객체를 생성하여 app2에 저장
		
		final int SAMPLE_CNT = 10000;											// SAMPLE_CNT는 변경할 수 없는 상수로 선언, 값은 10000으로 초기화
		// 1. 샘플 크기를 변경할 때 편리하다. 한 번만 바꿔주면 되기 때문에
		// 2. 실수로 하나만 샘플 수가 변경되는 위험을 막아준다.
		long elapsedTime1, elapsedTime2;										// long타입의 app1과 app2 각각의 작업시간을 담아줄 변수 생성
		
		point1 = System.currentTimeMillis();									// 현재시간을 밀리초단위로 point1에 저장
		for(int i=0; i<SAMPLE_CNT; i++) {										// 0부터 10000까지 반복
			app1 = app1 + i;  													// String을 사용하여 i를 문자열로 변환 후 app1에 계속 연결 (0 -> "01" -> "012" -> ...)
																				// 반복될때마다 새로운 문자열 객체가 생성되기 때문에 비효율
		}
		point2 = System.currentTimeMillis();									// 반복문이 끝난 후 현재시간을 point2변수에 저장
		elapsedTime1 = point2 - point1;											// 반복문이 수행되는 시간의 결과값을 elapsedTime1변수에 저장
		
		point1 = System.currentTimeMillis();									// 다시 현재시간을 밀리초 단위로 point1에 저장
		for(int i=0; i<SAMPLE_CNT; i++) {				
			app2.append(i);														// StringBuffer를 사용하여 i를 버퍼에 추가
		}
		point2 = System.currentTimeMillis();									// 반복문이 끝난 후 현재 시간 저장
		elapsedTime2 = point2 - point1;											// StringBuffer를 사용한 작업에 걸린 시간을 계산하여 저장
		
		MyUtil.cp("*** Performance Contest Result ***");
		MyUtil.p("String : " + elapsedTime1 + "(ms)");							// String을 사용하여 작업에 걸린 시간 출력
		MyUtil.p("StringBuffer : " + elapsedTime2 + "(ms)");					// StringBuffer를 사용하여 작업에 걸린 시간 출력
		
		// 3. Math
		System.out.println("\n" + "=========Math==========");
		System.out.println("Math.getRound(3.152, 2) : " + getRound(3.152, 2));	// getRound 메서드를 호출하여 인자값을 소수점 2번째 자리에서 반올림
		
		// 4. Date
		System.out.println("\n" + "=========Date==========");
		Date d = new Date();  // 현재 시간											// 현재 시간을 나타내는 Date 객체를 생성해 d변수에 저장
		System.out.println("현재 시간 : " + d);									// 현재 시간 출력
		
		// format이 심하게 맘에 들지 않을 경우 => SimpleDateFormat
		SimpleDateFormat f1 = new SimpleDateFormat("yyyyMMdd");					// SimpleDateFormat 객체를 생성해 현재 기준으로 연도,월,일 순서를 f1변수에 저장 
		System.out.println("현재 시간 : " + f1.format(d));							// 연도,월,일 순서로 현재 날짜 출력(데이터포맷)
		
		SimpleDateFormat f2 = new SimpleDateFormat("yyyy-MM-dd");				// SimpleDateFormat 객체를 생성해 "yyyy-MM-dd" 형식으로 현재 날짜를 f2변수에 저장
		System.out.println("현재 시간 : " + f2.format(d));
		
		SimpleDateFormat f3 = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");		// SimpleDateFormat 객체를 생성해 "yyyy-MM-dd hh:mm:ss" 형식으로 현재 날짜,시간을 f3변수에 저장
		System.out.println("현재 시간 : " + f3.format(d));
		
		// 5. Calendar
		System.out.println("\n" + "=========Calender==========");				
		Calendar c = Calendar.getInstance();									// Calendar.getInstance : 현재 시스템의 날짜와 시간을 기준으로 반환, c변수에 저장 
		
		// get 메서드로 내가 원하는 달력의 값을 얻어낼 수 있다.
		System.out.println("오늘의 요일은 : " + c.get(Calendar.DAY_OF_WEEK));				// c.get을 호출해 DAY_OF_WEEK 필드의 값인 현재 날짜의 요일을 숫자로 출력
		System.out.println("연도 : " + c.get(Calendar.YEAR));								// c.get을 호출해 YEAR 필드의 값인 현재 날짜의 연도를 출력
		System.out.println("월 : " + (c.get(Calendar.MONTH)+1));							// c.get을 호출해 MONTH)+1 필드의 값인 현재 날짜의 월을 출력(0부터 시작하므로 1을 더함)
		System.out.println("일 : " + c.get(Calendar.DAY_OF_MONTH));						// c.get을 호출해 DAY_OF_MONTH 필드의 값인 현재 날짜의 일을 출력
		System.out.println("오늘이 이번 연도 몇 번째 날 : " + c.get(Calendar.DAY_OF_YEAR));	 	// c.get을 호출해 DAY_OF_YEAR 필드의 값인 현재 날짜가 올해의 몇 번째 날인지 출력
		System.out.println("오늘이 이번 연도 몇 번째 주 : " + c.get(Calendar.WEEK_OF_YEAR));	// c.get을 호출해 WEEK_OF_YEAR 필드의 값인 현재 날짜가 올해의 몇 번째 주인지 출력
		}
	
		// a를 b자리에서 반올림(b >= 0)
		public static double getRound(double a, int b) {						// double 데이터 타입의 getRound 메서드 생성, 인자값을 2개 받음 (a: 값, b: 반올림 자리수)
		double result = 0.0;													// 결과값을 저장할 변수를 0.0으로 초기화
		double var = a;															// 입력값 a를 var에 저장하여 사용
		
		// 10을 b의 회수만큼 곱한다.
		for(int i=0; i<b; i++) {												// b의 값만큼 반복
			var = var * 10;														// var에 10을 곱하여 소수점 위치를 이동		
		}
		
		// 소수점 이하를 버린다.
		var = Math.round(var);													// var를 반올림하여 소수점 이하를 버림
		
		// 10을 b의 회수만큼 나눈다.
		for(int i=0; i<b; i++) {												// b의 값만큼 반복
			var = var / 10;														// var를 다시 10으로 나우어 소수점 위치를 복원
		}
		
		result = var;															// 결과값을 result에 저장
		return result;															// 결과값 반환		
		}
}

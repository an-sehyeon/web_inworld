



package day03;

import util.MyUtil;

public class OperEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

// 저장할때 자동으로 import 찾아주는 설정
// window -> preferences -> save Actions 
// -> perform the selected actions on save 체크
		
		// MyUtil 클래스의 인스턴스를 생성하여 메서드 호출에 사용
		MyUtil u = new MyUtil();
		u.p("Operation Example");
		
		
		// 1. 대입연산
		// 정수형 변수 a에 100을 대입하고, 문자열 변수 b에 Summer를 대입
		int a = 100;
		String b = "Summer";
		u.p("1. 대입연산 : " + a + ", " + b);
		
		
		// 2.대입연산2
		// 변수 a에 10을 더한 값을 다시 a에 대입
		a += 10;  // 더해서 넣는다.
		u.p("2. 대입연산 : " + a);
		
		
		// 3. 산술연산
		// 우선순위에 따라 산술연산을 수행하여 변수 a에 값을 대입
		a = 5 + 5 * 2;   // a = 15 (곱셈이 먼저 수행됨)
		a = (5+5) * 2;   // a = 20 (괄호 안의 덧셈이 먼저 수행됨)
		u.p("3. 산술연산 : " + a);
		
		
		// 4. 산술연산2
		// 상수로 선언된 변수 NUM_OF_GROUP을 사용하여 나머지 연산을 수행
		int NUM_OF_GROUP = 11;   // 대문자 변수명 : 상수,   '_'들어가는 변수명 : 내부(internal) 변수
		int jop = 28673521 % NUM_OF_GROUP;  // 28673521을 11로 나눈 나머지를 구함
		u.p("4. 담당그룹은 " + jop + "입니다.");
		
		
		// 5. 산술연산3
		// 정수와 실수의 나눗셈 결과를 확인
		float numf = 10 / 4;    // 정수와 정수의 연산결과는 정수
		u.p("5. numf = " + numf);
		float numf2 = 10F / 4F;    // 실수와 실수의 연산결과는 실수
		u.p("5. numf2 = " + numf2);
		double numd = 10F / 4;				// 암시적 Cast
		float numf3 = (float)0D / 4;		// 명시적 Cast,    넓은 범위의 타입을 작은 범위의 타입에 담으려면 에러!!
		
		
		// 6. 0으로 나누어보기
		//float num = 10 / 0;      // 정수를 0으로 나누려고 하여 산술 예외 발생(코멘트로 처리됨)
		
		
		// 7. 증감연산
		// 후위 및 전위 증감 연산자 사용 예제
		int x = 10, y = 10;
		x++; ++y;       // x는 후위증감으로 11, y는 전위 증감으로 11
		u.p("7. x, y = " + x +", " + y);
		int x1 = x++;     //  x1에 11이 저장되고, 이후 x는 12로 증가
		int y1 = ++y;     //  y를 12로 증가시킨 후 y1에 저장
		u.p("7. x1, y1 = " + x1 +", " + y1);
		
		
		// 이름을 나타내는 변수를 만들고 이름을 넣는다.
		// 나이를 나타내는 변수를 만들고 나이를 넣는다.
		// 내 이름은 ***이고, 내 냐이는 ***입니다. 를 출력하시오.
		String name = "안세현";
		int age = 28;
		u.p("내 이름은 " + name +"이고, 내 나이는 " + age + "입니다." );
		
		
		// 8. 비교연산
		// 두 변수 a1과 a2의 값이 같은지 비교하여 결과 출력
		int a1 = 10, a2 = 5;
		u.p("8. a1 == a2 : " + (a1 == a2));

		
		// 9. 논리연산
		// 논리 연산자 사용 예제
		u.p("ture && false : " + (true && false));  // 둘 다 true여야 결과가 true
		u.p("ture || false : " + (true || false));  // 둘 중 하나만 true여도 결과가 true
		u.p("ture ^ false : " + (true ^ false));    // 두 값이 달라야 결과가 true
		
		
		// c1 = 10, c2 = 7, c3 = 50일 때
		// c1 > c2 > c3가 맞는지 결과를 출력하시오.
		// c1이 c2보다 크고, c2가 c3보다 큰지 확인하는 논리 연산
		int c1 = 10, c2 = 7, c3 = 50;
		u.p("c1 > c2 > c3 : " + ((c1>c2)&& (c2>c3)));
		
		
		// 10. 삼항연산
		int score = 70;
		String dad1 = score > 60? "잘했어" : "#%#%#$#$";       // score가 60보다 크면 "잘했어", 아니면 "#%#%#$#$"
		String dad2 = score > 99? "당연" : "그것도 점수냐";       // score가 99보다 크면 "당연", 아니면 "그것도 점수냐"
		u.p("10. dad1 = " + dad1);
		u.p("10. dad2 = " + dad2);

	
		String host1 = (score > 30)? "맥주" : "야 그냥 귀국해";
		u.p("10.host1 = " + host1);
		
	}

}

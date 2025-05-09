package day07;

import util.MyUtil;

public class ArrayEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MyUtil.p("Array Example");
		
		String[] str = new String[5];		// String타입의 str 배열 생성 크기는 5
		str[0] = "Eliot";					// str의 첫번째 배열의 값에 "Eliot" 저장
		str[3] = "Adbula";					// str의 네번째 배열의 값에 "Adbula" 저장
		MyUtil.p("str.length?" + str.length);
		
		String[] stdName1 = new String[5];					// String타입의 stdName1 배열 생성 크기는 5 
		String[] stdName2 = {"A","B","C","D","Federer"};	// String타입의 stdName2 배열에 각각의 값 저장
		
		// String의 재사용성 
		String aaa = new String("James");
		String bbb = "David";
		String ccc = new String("James");
		String ddd = "David";
		
		MyUtil.p("aaa == ccc? " + (aaa == ccc));	// aaa와 ccc의 메모리위치와 값이 일치하냐?
		MyUtil.p("bbb == ddd? " + (bbb == ddd));	// bbb와 ddd의 메모리위치와 값이 일치하냐?
		MyUtil.p("aaa == ccc? " + aaa.equals(ccc)); // 가지고있는 값이 같냐?
		
		int score = 100;
		Integer score1 = new Integer(100);
		
		int[] score2 = {10,20,10,30,20};
		
		// 학생의 이름과 점수들을 출력해보세요.
		// 학생이름1 : **점
		// 학생이름2 : **점
		// ...
		
		for(int i = 0; i < score2.length; i++) {
			MyUtil.p(stdName2[i] + " : " +  score2[i]);
		}
		
		
		// 2-dimensional Array (2차원배열)
		String[][] classStd = {{"A","B"},{"C","D"}};
		// classStd[0][0] ~[1][1]
		// A학생의 위치는 classStd[0][0]
		// C학생의 위치는 classStd[1][0]
		// String[] class0 = classStd[0];		
		
		// 학년, 반, 학생 => String[][][]
		// => 한번 써보면 안쓰게 됨  
		
		// 모든 학생 이름 출력하기
		MyUtil.p("");
		MyUtil.p("Display All Student's Name");
		MyUtil.p(MyUtil.GREEN + "General" + MyUtil.END);
		for(int i = 0; i < stdName2.length; i++) {
			MyUtil.p(stdName2[i]);
		}
		
		
		MyUtil.p("");
		MyUtil.p("Display All Student's Name");
		MyUtil.p(MyUtil.RED + "Enhanced For" + MyUtil.END);
		
		// stdName2에서 하나를 꺼내서 name에 넣고 한 바퀴 수행
		for(String name : stdName2) { 	// stdName2는 String의 집합
			MyUtil.p(name);
			// 이름이 "C"이면 그만둠
			if(name.equals("C")) {
				break;
			}
		}
		
		
		// Enhanced For를 활용하여 점수를 출력하시오.
		for(int sco : score2) {
			MyUtil.p("점수 : " + sco);
		}
		
		
		// Enhanced For를 활용한 Difficulty GOD 문제
		// String [][] classStd = {{"A","B"},{"C","D"}};
		// Enhanced For를 활용하여 모든 학생을 출력하시오.
		
		
		// 2중 for문
		for(String[] cls : classStd) {			// classStd[0], classStd[1]
			for(String std : cls) {
				MyUtil.p("학생 이름 : " + std );
			}
		}
		
		// day01 패키지의 Welcome 클래스 호출
//		Welcome welcome = new Welcome();
//		String[] zzz = null;
//		welcome.main(zzz);
		

	}

}

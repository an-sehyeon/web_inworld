
package day05;

import java.util.Random;

import util.MyUtil;

public class SwitchEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Random rd = new Random();
		
		int i = rd.nextInt(3);		// i는 0, 1, 2 중 하나
		
		switch(i) {
			case 0 : 
				MyUtil.p("0입니다. 꽝!!");
				break;
			case 1 : 
				MyUtil.p("1입니다. 이것도 꽝!~");
				break;
			case 2 :
				MyUtil.p(MyUtil.RED + "This is the TWO!!!!" + MyUtil.END);
				MyUtil.p("축!!!!당첨!!! 거액의 상품 증정!!");
				break;
			default : 
				MyUtil.p("엄청난 버그 발생..... 시스템 이상함...");
		}
		
		
		
		// 의도적으로 break를 빼는 경우
		// 아래 단계 작업이 상위 작업을 반드시 거쳐야 하는 경우
		// 작업 간 포함 개념
		
		int workProcess = rd.nextInt(4);
		MyUtil.p("");
		MyUtil.p(MyUtil.GREEN + "### 앞으로의 작업 단계 ###" + MyUtil.END);
		
		switch(workProcess) {
			case 0:
				MyUtil.p("Stem1 : 작업준비");
			case 1:
				MyUtil.p("Stem2 : 개발준비");
			case 2:
				MyUtil.p("Stem3 : 개발완료");
			case 3:
				MyUtil.p("Stem4 : 출시준비");
				
		}
		
		
		
		// if statement로 구현한다면
		MyUtil.p("");
		
		if(workProcess == 0) {
			MyUtil.p("Step1 : 작업준비");
			MyUtil.p("Step2 : 개발준비");
			MyUtil.p("Step3 : 개발완료");
			MyUtil.p("Step4 : 출시준비");
		}
		else if(workProcess == 1) {
			MyUtil.p("Step2 : 개발준비");
			MyUtil.p("Step3 : 개발완료");
			MyUtil.p("Step4 : 출시준비");
		}
		else if(workProcess == 2) {
			MyUtil.p("Step3 : 개발완료");
			MyUtil.p("Step4 : 출시준비");
		}
		else if(workProcess == 3) {
			MyUtil.p("Step4 : 출시준비");
		}
	}

}

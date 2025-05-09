
package day10;

import java.util.Random;

import util.MyUtil;

public class DepartEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Department[] d = new Department[5];		// 백화점 5, 5크기의 배열 생성
		
		d[0] = new Department("강남점");			// 0번째 인덱스에 "강남점" 저장
		d[1] = new Department("본점");			// 1번째 인덱스에 "본점" 저장
		d[2] = new Department("USE점");			// 2번째 인덱스에 "USE점" 저장
		d[3] = new Department("일본점");			// 3번째 인덱스에 "일본점" 저장
		d[4] = new Department("속리산점");			// 4번째 인덱스에 "속리산점" 저장
		
		d[0].setOpenInd(true); 					// setOpenInd를 true로 초기화
		Department.setPranOpenInd(true);		// setPranOpenInd를 true로 초기화
		
		Random rd = new Random();
		for(int i = 0; i < d.length; i++) { 	// d배열의 크기만큼 반복
			if(rd.nextInt(2) == 1) 				// 랜덤으로 0과 1중 받아서 1이라면?
				d[i].setOpenInd(true);			// 현재 i번째 인덱스의 값을 true
			else								// 0이라면?
				d[i].setOpenInd(false);			// false 저장
		}
			
		MyUtil.p("지점 오픈 상태 체크 결과");
		for(int i = 0; i < d.length; i++) {		// d가 가지고 있는 배열 값들을 순서대로 하나씩 출력
			MyUtil.p("[" + d[i].name + "]" + (d[i].getOpenStatus()? "열었어":"닫았어"));
		}										// 현재 배열의 getOpenStatus 값이 true라면 열었어, false라면 닫았어 출력
		
		System.out.println("\n");

		// 1억 이하의 임의의 금액을 얻은 후 1000단위 이하를 절삭하여 amt에 추가
		for(int i = 0; i < 10; i++) {								// 10번 반복 'd'를 각각 10씩 반복하며 누적 매출액을 더하기 위함
			for(int j = 0; j < d.length; j++) {						// i가 1번 반복할 때마다 j는 5번 반복하며, 이는 각각의 인덱스에 매출액을 랜덤으로 받아 저장하기 위함 
				int todayAmt = rd.nextInt(100000001) / 1000 * 1000;	// 0부터 100000000까지 중 랜덤으로 하나를 받은 값을 (1000 * 1000)결과값에 나눈 후 todayAm	t에 저장
				d[j].addAmt(todayAmt);								// 현재 인덱스에 매출액을 더해주고 저장(총매출액을 출력하기 위함)
		
//				MyUtil.p(d[j].name + "의 현재 매출액은 : " + d[j].getAmt());		// 중첩반복문이 종료될때마다 현재 각각의 인덱스의 값들을 출력
			}
//			System.out.println("\n");
		}
		
		System.out.println("\n");
		
		
		for(Department dd : d) {						// 배열의 인덱스를 하나씩 꺼내와서
			MyUtil.p(dd.name + " : " + dd.getAmt());	// dd의 이름과 총매출액 출력
		}
		
		// 가장 매출이 높은 지점은?
		String topName = null;							// String타입 변수 생성 후 null로 초기화, 최고 매출 지점명 담을 변수 
		int topAmt = 0;									// int타입 변수 생성 후 초기화, 최고 매출액 담을 변수						
		
		for(Department dd : d) {						// 배열의 인덱스를 하나씩 꺼내와서
			if(dd.getAmt() > topAmt) {					// 해당 인덱스의 총매출액이 topAmt보다 크다면?
				topName = dd.name;						// topName에 해당 인덱스의 name을 저장	
				topAmt = dd.getAmt();					// topAmt에 해당 인덱스의 총매출액을 저장
			}
		}
		
		MyUtil.cp("최대 매출 지점");
		MyUtil.p(MyUtil.RED + MyUtil.BOLD + topName + " : " + topAmt + MyUtil.END);
		
		
		
		
	}

}

class Department{
	// 1. name : 백화점 지점 이름, 생성할 때 지정 가능
	// 2. pranName : 브랜드 이름, 수정 불가, 최초에 "내백화점"으로 세팅
	// 3. amt : 지점의 매출액, 초기값은 0, 직접 수정 불가
	// 4. openInd : 현재 지점의 오픈 가능 여부(true or false)
	// 5. pranOpenInd : 전체(브랜드) 백화점의 오픈 가능 여부
	// 6. getOpenStatus() : 현재 지점이 오픈 가능한지 여부
	// 7. addAmt(int amt) : 매출액을 더하는 메서드
	
	// Step 0. 클래스 설명넣기
	// 백화점 지점 단위를 객체로 갖는 클래스
	
	// step 1. 필드 정의
	String name;
	static final String pranName = "내백화점";			// 모든 지점 공통
	int amt;
	private boolean openInd;
	private static boolean pranOpenInd;
	
	
	// step 2. Constructor
	// 지정 이름을 지정 이름 세팅, 안하면 "미정" 세팅
	Department(){
		//name = "미정";
		this("미정");
	}
	
	Department(String name){
		this.name = name;
	}
	
	
	// step 3. getOpenStatus 구현
	public boolean getOpenStatus() {
		
		// 1번 방식
//		if(openInd == true) {
//			if(pranOpenInd == true) {
//				return true;
//			}
//			else {
//				return false;
//			}
//		}
		
		
		// 2번 방식		
//		if(openInd == true && pranOpenInd == true) {
//			return true;
//			}
//		return false;

		
		// 3번 방식
//		if(openInd && pranOpenInd) {
//			return true;
//		}
//		return false;
		
		
		// 4번 방식
		return openInd && pranOpenInd;
		}
		
		
		// step 4. Getter/Setter
		public void setOpenInd(boolean status) {
			openInd = status;
		}
	
		public static void setPranOpenInd(boolean status) {
			pranOpenInd = status;
		}
		
		public int getAmt() {
			return amt;
		}
		
		
		// step 5. User Function
		// 매출액을 더하는 메서드
		public void addAmt(int amt) {
//			this.amt = this.amt + amt;
			this.amt += amt;
		}
		
		
				
	}
	
	
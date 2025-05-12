package day15;

public class MyExceptionTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 예외 처리 테스트를 위해 다양한 청구 금액으로 sendReceipt를 호출
		try {
			// sendReceipt(400000000);
			// sendReceipt(300);
			sendReceipt(2000000);												// sendReceipt메서드를 호출해 인자값 2000000전달, 정상적인 금액이므로 예외X
		}
		catch(Exception e) {															
			e.printStackTrace();												// 예외 발생시 해당 예외에 대한 정보 출력
			
			// 예외처리 통계DB에 입력 등 후속 조치 가능
			try {
				// 데이터베이스에 데이터 추가
				// 이 경우 중첩 예외처리가 일어날 수 있음
			}
			catch(Exception e2) { e2.printStackTrace(); }						// 상위 catch 블록 안에서 다른 예외 상황을 대비,
																				// 첫번째 예외 처리 도중에 또 다른 예외가 발생할 경우 처리하기 위한 블록		
		}
		finally {																// 예외 발생여부에 상관없이 반드시 실행되는 코드
			System.out.println("[finally] 오류나던지 말던지 나는 수행해라");
		}
		
		System.out.println("[회계 프로그램] 정상 종료");								// 프로그램이 정상적으로 종료됨을 알리는 메시지 출력
	}

	public static void sendReceipt(int amt) throws MyException {				// 청구금액(amt)에 따른 예외를 발생시키는 메서드
		if(amt > 100000000)														// 청구 금액이 100000000원을 초과하면 예외 발생
			throw new MyException("[B001] 과도한 청구금액 넌 백퍼 사기꾼");
		else if(amt < 1000)														// 청구 금액이 1000원 미만이면 예외 발생	
			throw new MyException("[B002] 금액 " + amt + "원은 니 돈주고 사먹어");
		
		System.out.println("[sendReceipt()] " + amt + "원 정상처리");				// 예외가 발생하지 않으면 코드 실행
	}
}

// 사용자 정의 예외 클래스, Exception을 상속받음
class MyException extends Exception {
	MyException(String msg){													// 예외 발생시 아래 메시지를 출력하고 추가 로그를 출력하는 생성자
		super(msg);																// 부모 클래스인 Exception에 메시지 전달
		System.out.println("[MyException] 경찰서 고발");
		System.out.println("[MyException] 메시지 로그 기록");
		System.out.println("[MyException] 모든 전원 오프");
		System.out.println("[MyException] 전원 계약 해지");
		System.out.println("[MyException] 관련자 모두 무급 출근");
		System.out.println("[MyException] 사장님에게 보고");
	}
}

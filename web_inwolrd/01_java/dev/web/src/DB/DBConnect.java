package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DBConnect {

	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	// Constructor
	DBConnect(){
		try {															// 예외처리
			Class.forName("org.mariadb.jdbc.Driver");					// MariaDB jdbc 드라이버 로드
			conn = DriverManager.getConnection(
					"jdbc:mariadb://localhost:3399/test",				// MariaDB 서버 주소, 포트번호, DB 테이블명
					"root",												// 데이터베이스 아이디			
					"3416"												// 비밀번호
					);
			
			if(conn != null) {											
				System.out.println("Congratulations!! Connect OK");		// 연결 성공 시 메시지 출력
			}
			
		}
		catch(Exception e) {e.printStackTrace(); }						// 예외 발생 시 오류 메시지 출력
	}
	
	public static void main(String[] args) {												// 프로그램 실행 시 호출되는 메인 메서드
		// TODO Auto-generated method stub
		
		System.out.println("DB Connect Program");											// 문자열 출력
		DBConnect dbconn = new DBConnect();													// DBConnect 객체 생성 후 dbconn변수에 저장
		
		// for Test
		HospitalVO vo = new HospitalVO();													// HospitalVo 클래스의 객체를 생성 후 vo변수에 저장
		vo.setVO("테스트병원","하늘", 12534);													// 테스트용 병원 정보 설정
		System.out.println(vo.toString());													// vo 객체의 toString() 메서드 호출하여 병원 정보 출력
		
		dbconn.select1("서울").forEach(System.out::println);									// 람다function, select1 메서드 실행 결과 출력
		System.out.println("--------------------------------------------------------");
		dbconn.select2("서울").forEach(System.out::println);									// select2 메서드 실행 결과 출력
	}

	// Statement를 사용한 정적 SQL 문 사용
	public ArrayList<HospitalVO> select1(String region){									// select1 매서드  
		
		ArrayList<HospitalVO> arr = new ArrayList<>();										// 병원 목록을 저장할 ArrayList 
		
		// SQL 문 작성
		String sql = "";
		sql += "select * from hptl_mast_bak ";												// hptl_mast_bak 테이블에서
		sql += "where sido_cd_nm = '" + region + "' ";										// sido_cd_nm 컬럼 값이 rehion인 데이터를
		sql += "limit 5";																	// 최대 5개의 결과만 조회
		
		System.out.println(sql);															// 실행할 SQL 문 출력
		
		try {
			stmt = conn.createStatement();													// Statement 객체 생성
			rs = stmt.executeQuery(sql);													// rs변수에 SQL 문 실행 결과 저장	
			
			
			while(rs.next()) {																// 결과가 있을 때까지 반복
				HospitalVO vo = new HospitalVO();											
				vo.setVO(rs.getString("hptl_nm"),											// 병원 이름
						 rs.getString("sido_cd_nm"),										// 지역 코드	
						 rs.getInt("doc_num"));												// 의사 수
				arr.add(vo);																// HospitalVO 객체를 ArrayList에 추가
						
			}
			
			rs = null;																		// 결과값을 담은 rs변수 초기화
			
		}
		catch(Exception e) {e.printStackTrace();}											// 예외 발생 시 오류 메시지 출력
		
		return arr;																			// 조회 결과 반환
	}
	
	
	
	
	
	
	// PreparedStament
public ArrayList<HospitalVO> select2(String region){										// select2 메서드
		
		ArrayList<HospitalVO> arr = new ArrayList<>();										// 병원 목록을 저장할 ArrayList	
		
		
		// PreparedStatement는 변수 부분을 ?로 생성
		String sql = "";
		sql += "select * from hptl_mast_bak ";
		sql += "where sido_cd_nm = ? ";														// 조건 부분에 ? 사용
		sql += "limit 5";
		
		System.out.println(sql);
		
		try {
			pstmt = conn.prepareStatement(sql);												// PreparedStatement 객체 생성
			pstmt.setString(1, region);														// 첫번째 ? 자리에 region 값 설정
			rs = pstmt.executeQuery();														// SQL 문 실행 및 결과 저장
			
			
			while(rs.next()) {
				HospitalVO vo = new HospitalVO();
				vo.setVO(rs.getString("hptl_nm"),
						 rs.getString("sido_cd_nm"),
						 rs.getInt("doc_num"));
				arr.add(vo);
						
			}

			rs = null;
			
		}
		catch(Exception e) {e.printStackTrace();}
		
		return arr;
	}
}
			
	




















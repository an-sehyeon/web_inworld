package com.korea.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Order")								// /Order 요청이 들어오면 이 서블릿이 처리하도록 설정
public class OrderServlet extends HttpServlet {		// HttpServlet클래스를 상속받아 OrderServlet 정의

		// ANSI 색상 코드 정의
		private final String RED = "\033[91m";		// 빨강
		private final String GREEN = "\033[92m";	// 초록
		private final String END = "\033[0m";		// 색깔 끝내기
		
		
		protected void doGet(HttpServletRequest request,
							 HttpServletResponse response)
					throws ServletException, IOException {
			System.out.println(RED + "[OrderServlet] Called" + END);		// 호출이 됐다면 빨간색으로 해당 문자열 출력
					
			response.setContentType("text/html; charset=UTF-8");			// 응답 데이터의 콘텐츠 타입을 설정(HTML 형식, UTF-8 인코딩)
			
			// remen, price, location을 입력으로 받는다면 
			String food = request.getParameter("ramen");					// request 파라미터에서 "ramen" 값을 읽어옴 (라면 종류)
			String loc = request.getParameter("location");					// request 파라미터에서 "location"값을 읽어옴 (장소)
			int price = Integer.parseInt(request.getParameter("price"));	// 문자열을 정수로 변환
			
			System.out.println(food + ":" + loc + ":" + price);
			
			PrintWriter out = response.getWriter();							// 변수 out : 응답 객체에서 출력 스트림을 가져옴(HTML 작성용)
			out.println("<html><body>");									// out.println() : 클라이언트에게 데이터를 HTML 형식으로 전송하는 메서드
			out.println("<h1>손님께서 주문하신 내역</h1><hr>");
			out.println("<h2> 라면 : " + food );
			
			if(loc.equals("니네집")) {										// 문자열끼리 비교, loc에 "니네집" 입력 시
				System.out.println("니네집");									
				out.println("<h2>니네집인데 공짜지</h2>");						// 해당 문자열 출력	
			}
			else if(loc.equals("융프라우")) {									// loc에 "융프라우" 입력 시
				out.println("<h2>가격은 " + price * 10 + "원입니다.</h2>");	// 해당 코드 실행
			}
			else {															// 위 조건에 모두 포함되지 않는다면?
				out.println("<h2>안팔아</h2>");								// 문자열 출력
			}
			
			out.println("</body></html>");

		}
}

package day12;

import util.MyUtil;

public class StringTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MyUtil.cp("String Class");							// MyUtil클래스의 cp메서드를 호출하여 문자열 출력
		
		// 1. length()
		String str1 = "qwerqwerqwrqwerqewrqwerqwerqerw";	// 문자 개수 확인
		String str2 = "한글은 몇 자로 칩니까?";					// 한글도 1자로 인식
		MyUtil.p("[lenght()] eng : " + str1.length());		// MyUtil클래스의 p메서드를 호출하여 문자열 + str1의 문자열 길이 출력
		MyUtil.p("[lenght()] kor : " + str2.length());		// MyUtil클래스의 p메서드를 호출하여 문자열 + str2의 문자열 길이 출력
		
		
		// 2. charAt()
		String str3 = "이것도 한글이 궁금해요";					// String 타입에 str3변수에 문자열 대입
		MyUtil.p("[charAt()] : " + str3.charAt(5));			// charAt함수를 활용하여 str3의 문자열에 6번째 글자 출력, 공백도 포함
		
		
		// 3. equals, equalsIgnoreCase
		String str4 = new String ("abcde");
		String str5 = new String ("abcde");
		MyUtil.p("[equals()] : str4 == str5 : " + (str4==str5));								// "=="연산자는 두 참조 변수가 같은 객체를 참조하는지 확인, 
																								// 다른 메모리 주소에 할당된 객체이므로 결과는 false
		MyUtil.p("[eauals()] str4.eauls(str5) : " + str4.equals(str5)); 						// equals 메서드는 문자열의 내용을 비교, 문자열이 같기 때문에 결과값은 true
		String str6 = new String("ABCDE");
		MyUtil.p("[equals()] str4.equalsIgnoreCase(str6) : " + str4.equalsIgnoreCase(str6));	// equalsIgnoreCase 메서드는 대소문자를 무시하고 문자열 내용을 비교
		
		// 4. replace()
		String str7 = "Heaven helps those who help themselves";
		String str8 = "help";
		MyUtil.p("[replace()] : " + str7.replace(str8, "____"));	// str7에 문자열 중 str8에 할당된 문자열인 help를 "____"로 대체
		String str9 = str7.replace("e","_");						// str7의 문자열 중 "e"를 "_"로 대체
		MyUtil.p("[replace()] : " + str9);
		
		
		// 5. substring()
		String str10 = str7.substring(0,4);							// str7 변수에 담긴 문자열의 첫번째글자부터 4번째 글자까지 str10에 할당
		MyUtil.p("[substring(0,4)] : " + str10);					
		
		// 두 번째 인자를 생략하는 경우
		String str11 = str7.substring(5);							// str7의 1번째부터 4번째까지의 인자를 생략하고 str11에 할당
		MyUtil.p("[substing(5)] : " + str11);
		
		// 실습 문제 : str12에 str7의 오른쪽 다섯 글자를 넣으시오.
		String str12 = str7.substring(str7.length()-5);				// str7의 길이 수의 -5를 한 값을 첫번째 글자부터 제외하고 나머지 글자 출력 
		MyUtil.p("[substring()] : " + str12  );
		
		
		// 6.trim()
		MyUtil.p("[trim()] : " + "       qwerqwrqerqer       ".trim());		// 문자열의 공백 제거 후 출력
		
		
		// 7. split()
		String[] str13 = str7.split(" helps ");						// str7의 문자열 중 "helps" 문자열을 기준으로 분리하여 문자열 배열인 str13에 저장
		for(String s : str13) {										// str13에 저장된 문자열 배열의 요소값을 반복하며 출력 
			MyUtil.p("[split---()] : " + s);
		}
		
		// 단어별
		String[] str14 = str7.split(" ");							// str7의 문자열 중 공백을 기준으로 분리하여 str14 배열에 저장
		for(String s : str14) MyUtil.p("[split()] : " + s);			// 반복하며 str14의 배열 요소값을 하나씩 출력
		
		
		// 글자별
		String[] str15 = str7.split("");							// str7의 문자열을 개별적으로 분리하여 str15에 배열로 저장
		for(String s : str15) MyUtil.p("[split15()] : " + s);		// str15에 담긴 배열을 하나씩 반복하며 출력 
		
		
		// 구분자가 2개일 때 어떻게 할 수 있을까? e 또는 공백(space)
		String[] str16 = str7.split("[ e]");						// str7의 문자열 중 'e' 또는 공백을 기준으로 분리하여 문자열을 str16에 배열로 저장
		for(String s : str16) MyUtil.p("[split16()] : " + s);			// str16에 담긴 배열을 반복하며 출력
		
		
		// 8. matches(regular expraession)
		String str17 = "298-182318-82321793";						// string 타입의 문자열, str17에 초기화
		String regExp = "[0-9]{3,4}[-][0-9]{5,7}[-][0-9]{6,100}";	// [0-9] {3,4} : 0~9까지의 숫자가 3개에서 4개 ...
		boolean result = str17.matches(regExp);						// str17이 matches 메서드를 활용하여 regExp 규칙과 일치하는지 검사 후 result에 저장
		MyUtil.p("[matches()] : " + result);
		
		String fileName = "data.zip";
		String checkRule = "(.U?\\.(exe|sh|zip|alz)$)";
		result = fileName.matches(checkRule);										// fileName이 checkRule 규칙에 부합하는지의 결과를 result에 저장
		MyUtil.p("[matches()] : " + (result? "당신 지금 뭐하는거야":"업로드가능"));			// fileName이 checkRule 규칙에 부합한다면 "당신 지금 뭐하는 거야" 출력
																					// 규칙에 부합하지 않는다면 "업로드 가능" 출력
		
		// phoneNum이 전화번호 타입인지 검사
		String phoneNum = "011-123-2314";
		result = chekPhoneNumber(phoneNum);
		if(result)											// result 규칙에 부합한다면?
			MyUtil.p("완벽한 전화번호 입력 완료");					// 코드출력
		else												// 규칙에 부합하지 않는다면?
			MyUtil.p("너 지금 뭐 입력한거냐");					// 코드출력
	}

	private static boolean chekPhoneNumber(String str) {
		// str이 전화번호 타입인지 검사하여 전화번호 타입이면 true return
		// 숫자3자리 + "-" + 숫자3-4자리 + "-" + 숫자4자리
		String checkRule = "[0-9]{3}[-][0-9]{3,4}[-][0-9]{4}";

		return str.matches(checkRule);
	}


}



package day01;

public class Welcome {
	public static void main(String[] args) {
		System.out.println("welcome to my house");
		
		// "welcome to my house"를 빨간색으로 출력하고 줄을 바꾼다.
		// ANSI 이스케이프 코드를 사용하여 글씨 색상을 빨간색으로 변경 
		System.out.println("\033[91m" + "welcome to my house" + "\033[0m");	   

		System.out.print("welcome to your house");
		System.out.print("welcome to his house");	// print와 printfln의 차이
		
	}
}

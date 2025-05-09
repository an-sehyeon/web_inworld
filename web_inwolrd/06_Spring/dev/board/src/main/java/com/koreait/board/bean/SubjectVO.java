package com.koreait.board.bean;

import lombok.Data;

@Data		// get 함수를 만들어주기 위해서 필요함
public class SubjectVO {
	private int kor;
	private int math;
	private int eng;
	private int sum;
	private float avg;
	
	public int getSum() {
		return kor + math + eng;
	}
	
	public float getAvg() {
		return (kor + math + eng) / 3F;
	}
}

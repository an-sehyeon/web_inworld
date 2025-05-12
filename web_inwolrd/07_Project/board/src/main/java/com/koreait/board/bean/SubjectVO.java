package com.koreait.board.bean;

import lombok.Data;

@Data
public class SubjectVO {
	private int kor;
	private int math;
	private int eng;
	private int sum;
	private float avg;
	
	public int getSum() {
		return kor + math + eng;
	}
	
	public int getAvg() {
		return Math.round((kor + math + eng)/3F);
	}
}

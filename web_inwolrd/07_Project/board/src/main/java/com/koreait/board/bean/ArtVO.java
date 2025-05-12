package com.koreait.board.bean;

import lombok.Data;

@Data
public class ArtVO {
	private String title;
	private String artist;
	private String desc;
	
	public String getPrice() {
		return "1억";
	}
}

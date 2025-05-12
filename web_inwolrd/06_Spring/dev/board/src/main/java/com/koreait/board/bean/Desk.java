package com.koreait.board.bean;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class Desk {
	private String name;
	private String materal;
	
	public Desk() {
		this.name = "Island";
		this.materal = "Wood";
	}
}

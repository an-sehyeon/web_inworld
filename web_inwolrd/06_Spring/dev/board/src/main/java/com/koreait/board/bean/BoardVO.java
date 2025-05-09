package com.koreait.board.bean;

import java.util.List;

import lombok.Data;

@Data
public class BoardVO {
	private Long bno;
	private String title;
	private String content;
	private String writer;
	private String regdate;
	private String updatedate;
	private List<AttachFileVO> attachFile;
	// input tag의 name에 
	// attachFile[i].fileNmae
	// attachFile[i].uuid
	// attachFile[i].uploadPath
	// attachFile[i].image
	// 방식으로 submit하면 자동으로 List에 add된다.
}

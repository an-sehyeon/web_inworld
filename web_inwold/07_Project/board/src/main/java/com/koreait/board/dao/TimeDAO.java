package com.koreait.board.dao;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.board.mapper.TimeMapper;

@Repository  // @Component의 자식 Annotation으로 DAO에 사용
public class TimeDAO {

	@Autowired
	private TimeMapper mapper;
	
	public String getTime() {
		Random rd = new Random();
		int num = rd.nextInt(2);
		
		if(num == 0) {
			return mapper.getTime1();
		}
		else {
			return mapper.getTime2();
		}
	}
}

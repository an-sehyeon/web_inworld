package com.sehyeon.psychology.dto;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sehyeon.psychology.bean.BoardVO;
import com.sehyeon.psychology.mapper.BoardMapper;

import lombok.extern.slf4j.Slf4j;


@Repository
@Slf4j
public class BoardDAO {
	@Autowired
	private BoardMapper mapper;
	
	// getList
	public List<BoardVO> getList(){
		return mapper.getList();
	}
	
	// register
	public int register(BoardVO board) {
		log.info("[DAO] DB INSERT 실행: " + board);
		
	    try {
	        return mapper.insertSelectKey(board);
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("게시글 등록 중 오류 발생", e);  	// 예외를 던져서 중복 실행 방지
	    }
	}

	
	// read
	public BoardVO read(Long bno) {
		return mapper.get(bno);
	}
	
	// modify
	public int modify(BoardVO board) {
		return mapper.update(board);
	}
	
	// remove
		public int remove(Long bno) {
			return mapper.delete(bno);
		}
}

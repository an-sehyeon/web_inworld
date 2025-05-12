package com.koreait.board.bean;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.koreait.board.util.MyUtil;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class DependencyTest {
	
	@Autowired		
	public ClassRoom cr;
	
	@BeforeAll		// 모든 테스트 수행 전 한 번 수행할 일
	public static void init1() {
		log.info(MyUtil.BLUE + "Init1 for Test" + MyUtil.END);
	}
	
	@BeforeEach		// 모든 테스트 메서드 수행 전
	public void init2() {
		log.info("Init2 for Test");
	}
	
	
	@Test
	public void testDependency() {
		log.info(MyUtil.BLUE + MyUtil.BOLD + "Test Start" + MyUtil.END);
		log.info("ClassRoom : " + cr);
		Desk myDesk = new Desk();
		myDesk.setName("Norway");
		myDesk.setMateral("Iron");
		cr.setDesk1(myDesk);
		log.info("ClassRoom : " + cr);
	}
	
	// @Test 마크가 없으므로 출력 X
	public void testDummy() {
		log.info("Dummy Test");
	}
}

package com.sehyeon.sportspsychology.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.sehyeon.sportspsychology.bean.LoginVO;


@Mapper
public interface Loginmapper {
	String loginCheck(LoginVO vo);

	LoginVO findByUsername(String username);
}

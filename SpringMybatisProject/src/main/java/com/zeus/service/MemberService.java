package com.zeus.service;

import com.zeus.domain.Member;

//해당 BoardMapper의 오버라이딩은 BoardMapper.xml로 대체되어 오버라이딩
//Mybatisframework에서 객체를 자동 생성하여 호출 가능
public interface MemberService {
	public void register(Member member) throws Exception;
}

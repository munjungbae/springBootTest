package com.zeus.service;

import java.util.List;

import com.zeus.domain.Member;

//해당 BoardMapper의 오버라이딩은 BoardMapper.xml로 대체되어 오버라이딩
//Mybatisframework에서 객체를 자동 생성하여 호출 가능
public interface MemberService {
	public void register(Member member) throws Exception;

	public Member read(int userNo) throws Exception;

	public void modify(Member member) throws Exception;

	public void remove(int userNo) throws Exception;

	public List<Member> list() throws Exception;

}

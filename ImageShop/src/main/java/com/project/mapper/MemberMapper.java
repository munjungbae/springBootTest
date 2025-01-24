package com.project.mapper;

import java.util.List;

import com.project.domain.Member;
import com.project.domain.MemberAuth;

public interface MemberMapper {
	// 등록 처리
	public void create(Member member) throws Exception;

	// 권한 생성
	public void createAuth(MemberAuth memberAuth) throws Exception;

	public List<Member> list() throws Exception;

	public Member read(Integer userNo) throws Exception;

	public Member readByUserId(String userId);

	// 권한 수정
	public void modifyAuth(MemberAuth memberAuth) throws Exception;

	// 수정 처리
	public void update(Member member) throws Exception;

	// 삭제 처리
	public void delete(int userNo) throws Exception;

	// 권한 삭제
	public void deleteAuth(int userNo) throws Exception;

	public int countAll() throws Exception;
}

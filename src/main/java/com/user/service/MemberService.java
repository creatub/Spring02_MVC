package com.user.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.common.exception.NotUserException;
import com.user.domain.MemberVO;

@Mapper
public interface MemberService {
	
	int insertMember(MemberVO vo);
	boolean idCheck(String userid);
	
	List<MemberVO> selectAll();
	MemberVO selectByUserid(String userid);
	
	MemberVO loginCheck(MemberVO tmpUser) throws NotUserException;
}

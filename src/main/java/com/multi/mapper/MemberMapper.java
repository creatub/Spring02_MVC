package com.multi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.common.exception.NotUserException;
import com.user.domain.MemberVO;

@Mapper
public interface MemberMapper {
	
	int insertMember(MemberVO vo);
	int idCheck(String userid);
	
	List<MemberVO> selectAll();
	MemberVO selectByUserid(String userid);
	
	MemberVO loginCheck(MemberVO tmpUser) throws NotUserException;
}

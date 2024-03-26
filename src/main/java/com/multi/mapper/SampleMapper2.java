package com.multi.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import com.user.domain.MemberVO;

@Mapper
public interface SampleMapper2 {
	
	@Update("update member set mileage=mileage+#{mileage} where userid=#{userid}")
	public int updateMemberMileage(MemberVO user);
	
}

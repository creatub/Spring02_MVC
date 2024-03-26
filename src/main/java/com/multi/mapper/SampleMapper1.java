package com.multi.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.memo.model.MemoVO;

@Mapper
public interface SampleMapper1 {
	
	@Insert("insert into memo(no,name,msg,wdate) values(memo_seq.nextval,#{name},#{msg:VARCHAR},SYSDATE)")
	public int insertMemo(MemoVO vo);

}

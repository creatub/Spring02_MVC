package com.multi.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.memo.model.MemoVO;

@Mapper
public interface MemoMapper {

	int insertMemo(MemoVO vo);//C
	
	List<MemoVO> listMemo();//R
	List<MemoVO> listMemoPaging(Map<String, Object> map);//R
	
	MemoVO getMemo(int no);//R
	
	int deleteMemo(int no);//D
	
	int updateMemo(MemoVO vo);//U

	int getMemoTotalCount();
}

package com.memo.service;

import java.util.List;
import java.util.Map;

import com.memo.model.MemoVO;

public interface MemoService {
	int insertMemo(MemoVO vo);//C
	
	List<MemoVO> listMemo(int start, int end);//R--oracle老 版快
	List<MemoVO> listMemoMySQL(int limit, int offset);//mySQL老 版快
	
	MemoVO getMemo(int no);//R
	
	int deleteMemo(int no);//D
	
	int updateMemo(MemoVO vo);//U
	
	int getMemoTotalCount();
}

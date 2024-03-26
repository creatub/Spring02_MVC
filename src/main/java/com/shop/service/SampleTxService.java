package com.shop.service;

import com.memo.model.MemoVO;
import com.user.domain.MemberVO;

public interface SampleTxService {

	public void addMemo(MemoVO vo, MemberVO user);
}

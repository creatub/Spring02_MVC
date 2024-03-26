package com.shop.service;

import java.sql.SQLException;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.memo.model.MemoVO;
import com.multi.mapper.SampleMapper1;
import com.multi.mapper.SampleMapper2;
import com.user.domain.MemberVO;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class SampleTxServiceImpl implements SampleTxService {

	@Inject
	private SampleMapper1 mapper1; //Memo관련
	
	@Inject
	private SampleMapper2 mapper2; //Member관련
	
	@Transactional(rollbackFor={RuntimeException.class, SQLException.class})
	@Override
	public void addMemo(MemoVO vo, MemberVO user) {
		// 적립해준 뒤 메모 테이블에 글을 등록
		log.info("mapper2 마일리지 적립--------------");
		log.info("user: "+user);
		mapper2.updateMemberMileage(user);//적립금 500
		
		log.info("mapper1 메모글 등록----------------");
		log.info("memo: "+vo);
		mapper1.insertMemo(vo);
	}

}

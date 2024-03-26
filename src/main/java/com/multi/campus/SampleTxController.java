package com.multi.campus;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.common.util.CommonUtil;
import com.memo.model.MemoVO;
import com.shop.service.SampleTxService;
import com.user.domain.MemberVO;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class SampleTxController {

	@Inject
	private SampleTxService service;
	
	@Inject
	private CommonUtil util;
	
	@GetMapping("/tx")
	public String transactionTest(Model m) {
		String str="�� �Ǹ� 500���ϸ��� ����";
//		str+="ũ�� 100 �Ѿ�� ��û���� ���� ���� �Ἥ Transaction�� �׽�Ʈ�� ���̴�";
//		str+="ũ�� 100 �Ѿ�� ��û���� ���� ���� �Ἥ Transaction�� �׽�Ʈ�� ���̴�";
//		str+="ũ�� 100 �Ѿ�� ��û���� ���� ���� �Ἥ Transaction�� �׽�Ʈ�� ���̴�";
		MemberVO user =new MemberVO();
		user.setUserid("choi");
		user.setMileage(500);//�������� ���ϸ���
		
		MemoVO memo=new MemoVO();
		memo.setMsg(str);
		memo.setName(user.getUserid());
		///////////////////////////
		service.addMemo(memo, user);
		///////////////////////////
		String msg=user.getUserid()+"�� �� ���ٸ޸��� ��ϿϷ�";
		String loc="index";
		return util.addMsgLoc(m,msg,loc);
	}
}

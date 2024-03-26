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
		String str="잘 되면 500마일리지 적립";
//		str+="크기 100 넘어가게 엄청나게 많은 글을 써서 Transaction을 테스트할 것이다";
//		str+="크기 100 넘어가게 엄청나게 많은 글을 써서 Transaction을 테스트할 것이다";
//		str+="크기 100 넘어가게 엄청나게 많은 글을 써서 Transaction을 테스트할 것이다";
		MemberVO user =new MemberVO();
		user.setUserid("choi");
		user.setMileage(500);//적립해줄 마일리지
		
		MemoVO memo=new MemoVO();
		memo.setMsg(str);
		memo.setName(user.getUserid());
		///////////////////////////
		service.addMemo(memo, user);
		///////////////////////////
		String msg=user.getUserid()+"님 글 한줄메모장 등록완료";
		String loc="index";
		return util.addMsgLoc(m,msg,loc);
	}
}

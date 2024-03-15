package com.shop.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.domain.CartVO;
import com.shop.service.ShopService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/user")
public class CartController {

	@Inject
	private ShopService shopService;
	
	@PostMapping("/cartAdd") // 상품번호와 수량이 파라미터로 들어옴
	public String addCart(Model m, CartVO cvo) {
		log.info("cvo: "+cvo);
		//세션에서 로그인한 사람의 아이디 가져오기
		String userid="hong";//수정 예정
		cvo.setUserid(userid);
		//장바구니에 추가
		int n=shopService.addCart(cvo);//insert or update
		//특정 회원의 장바구니 목록 가져오기
		List<CartVO> cartArr=shopService.selectCartView(userid);
		
		log.info("cartArr: "+cartArr);
		m.addAttribute("cartArr",cartArr);
		
		
		return "shop/cartList";//forward방식
	}//---------
}

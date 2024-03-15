package com.shop.domain;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class CartVO {
	private int cnum;//장바구니 번호
	private String userid;//회원아이디(fk)
	private int pnum;//상품번호(fk)
	private int pqty;//수량(1~50개 까지만 가능)
	private Date cdate;//장바구니 담은 날짜
	
	//장바구니 상품정보
	private List<ProductVO> items;
	
	private int cartTotalPrice;//장바구니에 담긴 모든 상품의 총 금액
	private int cartTotalPoint;//총 포인트
	
//	private String pname;
//	private String pimage1;
//	private int price;
//	private int saleprice;
//	private int point;
}

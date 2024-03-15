package com.shop.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.common.util.CommonUtil;
import com.multi.mapper.CartMapper;
import com.multi.mapper.ProductMapper;
import com.shop.domain.CartVO;
import com.shop.domain.ProductVO;

@Service("shopService")
public class ShopServiceImpl implements ShopService {

	@Inject
	private ProductMapper prodMapper;
	
	@Inject
	private CartMapper cartMapper;
	
	@Inject
	private CommonUtil util;
	
	@Override
	public List<ProductVO> selectByPspec(String pspec) {
		return prodMapper.selectByPspec(pspec);
	}

	@Override
	public List<ProductVO> selectByCategory(int cg_num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductVO selectByPnum(int pnum) {
		return prodMapper.getProduct(pnum);
	}

	@Override
	public int addCart(CartVO cartVo) {
		//1. 추가한 상품이 이미 장바구니에 담겨있는 경우==> 수량만 수정 ==>update문
		//2. 추가한 상품이 장바구니에 없는 경우 ===> insert문
		//[1] userid와 pnum으로 cart테이블에서 장바구니 번호를 가져오자
		Integer cnum = cartMapper.selectCartNum(cartVo);
		int n = 0;
		if(cnum==null) {//[1_1] 새로 담는 상품인 경우
			n=cartMapper.addCart(cartVo);
		}else {//[1_2] 이미 담긴 상품인 경우
			n=cartMapper.updateCartQty(cartVo);
		}
		return n;
	}

	@Override
	public int updateCartQty(CartVO cartVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int editCart(CartVO cartVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CartVO> selectCartView(String userid) {
		return cartMapper.selectCartView(userid);
	}

	@Override
	public int delCart(int cartNum) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delCartAll(CartVO cartVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delCartOrder(Map<String, Integer> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CartVO getCartTotal(String userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delCartByOrder(String userid, int pnum) {
		// TODO Auto-generated method stub

	}

}

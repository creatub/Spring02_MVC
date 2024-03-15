package com.multi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shop.domain.CartVO;

@Mapper
public interface CartMapper {
	//��ٱ��� ��ȣ�� ���� ��쿣 MyBatis�� Null�� ��ȯ�ϱ� ������ int�� �ƴ� IntegerŸ���� ���
	Integer selectCartNum(CartVO cvo);//pnum, userid�� ��ٱ���(cnum)��������
	int updateCartQty(CartVO cvo);
	int addCart(CartVO cvo);
	
	List<CartVO> selectCartView(String userid);
	CartVO getCartTotal(String userid);
	
	int delCart(int cartNum);
	int editCart(CartVO cvo);
	int delCartAll(String userid);
	
}

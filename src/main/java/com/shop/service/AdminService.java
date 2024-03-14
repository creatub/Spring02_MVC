package com.shop.service;

import java.util.List;

import com.shop.domain.CategoryVO;
import com.shop.domain.ProductVO;

public interface AdminService {
	/**[관리자 모드] 카테고리 관리 */
	public List<CategoryVO> getUpCategory();

	public List<CategoryVO> getDownCategory(int upCg_code);

	public int categoryAdd(CategoryVO cvo);

	public int categoryDelete(int cg_code);

	/** [관리자 모드]- 상품 정보 등록하기 */
	public int productInsert(ProductVO prod);

	public List<ProductVO> productList();

	public ProductVO getProduct(int pnum);
	
	public int productUpdate(ProductVO prod);
	
	public int productDelete(int pnum);
}

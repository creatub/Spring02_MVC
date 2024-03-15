package com.shop.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.domain.ProductVO;
import com.shop.service.ShopService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class IndexController {

	@Resource(name="shopService") //객체 이름으로 주입한다(byName)
	private ShopService shopService;
	
	//메서드가 뷰네임을 반환하지 않으면(void) => RequestMapping()에 등록된 url-pattern "/index"
	//가 뷰네임이 된다
	@RequestMapping("/index")
	public void showIndex(Model m) {
		List<ProductVO> newItems = shopService.selectByPspec("NEW");
		log.info("NEW상품: "+newItems.size()+"개");
		
		List<ProductVO> hitItems = shopService.selectByPspec("HIT");
		log.info("HIT상품: "+hitItems.size()+"개");

		m.addAttribute("newItems", newItems);
		m.addAttribute("hitItems", hitItems);
		
		// "/WEB-INF/views/index.jsp"
	}
	
	@GetMapping("/prodDetail")
	public String productDetail(Model m, @RequestParam(defaultValue="0") int pnum) {
		if(pnum==0) {
			return "redirect:index";
		}
		ProductVO item = shopService.selectByPnum(pnum);
		m.addAttribute("item",item);
		
		return "shop/prodDetail";
	}
}

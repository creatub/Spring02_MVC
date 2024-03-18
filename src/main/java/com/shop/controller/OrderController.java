package com.shop.controller;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/user")
public class OrderController {

	@RequestMapping("/order")
	public String orderSheet(@RequestParam("pnum") int [] pnum, @RequestParam("pqty") int[] pqty) {
		log.info("pnum: "+Arrays.toString(pnum));
		log.info("pqty: "+Arrays.toString(pqty));
		return "shop/orderSheet";
	}
}

package com.shop.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.common.util.CommonUtil;
import com.shop.domain.CategoryVO;
import com.shop.domain.ProductVO;
import com.shop.service.AdminService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/admin")
public class AdminController {
	
	@Inject
	private CommonUtil util;
	
	@Inject
	private AdminService adminService;
	
	@GetMapping("/prodForm")
	public String productForm(Model m) {
		
		List<CategoryVO> upCgList=adminService.getUpCategory();
		m.addAttribute("upCgList",upCgList);
		
		return "admin/prodInput";
	}//------------
	
	//0. servlet-context.xml에 multipartResolver빈 등록해야 함
	@PostMapping("/prodInsert")
	public String productInsert(Model m, 
			ProductVO item, @RequestParam("pimage") List<MultipartFile> pimage,
			HttpServletRequest req) {
		log.info("item: "+item);
		log.info("pimage: "+pimage);
		//1. 업로드할 디렉토리의 절대경로 얻기
		ServletContext app = req.getServletContext();
		String upDir = app.getRealPath("/resources/product_images");
		log.info("upDir: " + upDir);
		
		//2. 파일 업로드 처리
		if(pimage!=null) {
			for(int i=0;i<pimage.size();i++) {
				MultipartFile mf=pimage.get(i);
				if(!mf.isEmpty()) {//업로드했다면
					try {
						String fileName=mf.getOriginalFilename();//첨부파일명
						mf.transferTo(new File(upDir,fileName));//업로드처리
						//3. 업로드한 파일명을 ProductVO의 pimage1, pimage2, pimage3에 setting
						if(i==0) {
							item.setPimage1(fileName);
						}else if(i==1) {
							item.setPimage2(fileName);
						}else if(i==2) {
							item.setPimage3(fileName);
						}
					}catch(IOException e) {
						log.error("파일 업로드 중 에러: "+e);
					}
				}//if
			}//for---
		}//if----
		log.info("item 2: "+item);
		
		
		int n = adminService.productInsert(item);
		
		String msg=(n>0)?"등록 성공":"등록 실패";
		String loc=(n>0)?"prodList":"javascript:history.back()";
		
		return util.addMsgLoc(m, msg, loc);
	}//-----------------------------------
	
	@GetMapping("/prodList")
	public String productList(Model m) {
		
		List<ProductVO> itemList = adminService.productList();
		m.addAttribute("itemList", itemList);
		return "admin/prodList";
	}
	
	@PostMapping("/prodDel")
	public String productDelete(Model m, @RequestParam("pnum") int num) {
		
		int n = adminService.productDelete(num);
		
		String msg=(n>0)?"삭제 완료":"삭제 실패";
		String loc=(n>0)?"prodList":"javascript:history.back()";
		
		return util.addMsgLoc(m, msg, loc);
	}
}

package com.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.board.domain.BoardVO;
import com.board.domain.PagingVO;
import com.board.service.BoardService;
import com.common.util.CommonUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequiredArgsConstructor
public class BoardController {

	private final CommonUtil util;
	
	private final BoardService bService;
	
	@GetMapping("/board/write")
	public String boardForm() {
		
		return "board/boardWrite";
	}//--------------
	
	@PostMapping("/user/write")
	public String boardInsert(Model m, BoardVO vo,
			@RequestParam("mfilename") MultipartFile mfilename, HttpSession ses) {
		
		log.info("vo: " + vo);
		//0. 유효성 체크 (userid, passwd)
		if(vo.getUserid().trim().isBlank()||vo.getUserid()==null||vo.getPasswd().trim().isBlank()||vo.getPasswd()==null) {
			return "redirect:../board/write";
		}
		//1. 파일 업로드 처리
		ServletContext app = ses.getServletContext();
		String upDir = app.getRealPath("/resources/board_upload");
		log.info("upDir: "+upDir);
		
		File dir= new File(upDir);
		if(!dir.exists()) {
			dir.mkdirs();//업로드 디렉토리 생성
		}
		if(!mfilename.isEmpty()) {//첨부한 파일이 있다면
			String fname=mfilename.getOriginalFilename();//원본파일명
			long fsize=mfilename.getSize();//첨부파일 크기
			UUID uuid=UUID.randomUUID();
			String filename=uuid.toString()+"_"+fname;//물리적 파일명
			
			log.info("fname: "+fname);
			log.info("filename: "+filename);
			
			vo.setFilename(filename);
			vo.setOriginFilename(fname);
			vo.setFilesize(fsize);
			
			//업로드 처리
			try {
				mfilename.transferTo(new File(upDir, filename));
			}catch(IOException e) {
				log.error("파일업로드 실패: "+e);
			}
		}//if---------------
		
		int n=0;
		String msg= "테스트";
		String loc="../board/list";
		
		if("write".equals(vo.getMode())) {
			n=bService.insertBoard(vo);
			msg="글쓰기 ";
		}else if("edit".equals(vo.getMode())) {
			n=bService.updateBoard(vo);
			msg="글수정 ";
		}else if("rewrite".equals(vo.getMode())) {
			n=bService.rewriteBoard(vo);
			msg="답글 쓰기 ";
		}
		msg+=(n>0)? "성공":"실패";
		if(n<=0) {
			loc="javascript:history.back()";
		}
		
		return util.addMsgLoc(m, msg, loc);
	}//-----------------------------
	
	@GetMapping("/board/list")
	public String boardList(Model m, PagingVO page) {
		//1. 총 게시글 수 가져오기
		int boardSum = bService.getTotalCount(page);
		//2. 게시글 목록 가져오기
		List<BoardVO> boardArr = bService.getBoardAll(page);
		m.addAttribute("boardSum", boardSum);
		m.addAttribute("boardArr", boardArr);
		
		return "board/boardList";
	}
}

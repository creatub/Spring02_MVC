package com.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
			for(int i=0;i<20;i++)
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
	public String boardList(Model m, PagingVO page, HttpServletRequest req) {
		log.info("page1: "+page);
		//1. 총 게시글 수 가져오기
		int boardSum = bService.getTotalCount(page);
		
		page.setTotalCount(boardSum); // 게시글 수
		page.setOneRecordPage(5);//한 페이지에 보여줄 목록 개수
		page.setPagingBlock(5);//페이징 블럭 단위값 설정
		///////////////
		page.init();//페이징 관련 연산을 수행하는 메서드 호출
		///////////////
		log.info("page2: "+page);
		//2. 게시글 목록 가져오기
		List<BoardVO> boardList = bService.getBoardAll(page);
		
		//3. 페이지 네비게이션 문자열 받아오기
		String myctx=req.getContextPath();
		String loc="board/list";
		String pageNavi=page.getPageNavi(myctx, loc);
		
		m.addAttribute("boardSum", boardSum);
		m.addAttribute("boardList", boardList);
		m.addAttribute("page", page);
		m.addAttribute("pageNavi", pageNavi);
		
		return "board/boardList";
//		return "board/boardList2";
	}//-----------------------------
	
	@GetMapping("/board/view/{num}")
	public String boardView(Model m, @PathVariable("num") int num) {
		log.info("num: "+num);
		//1. 조회수 증가
		bService.updateReadnum(num);
		//2. 글 번호로 글 내용 가져오기
		BoardVO board = bService.selectBoardByNum(num);
		
		m.addAttribute("vo", board);
		
		return "board/boardView";
	}//---------------
	
	//답변 글쓰기
	@PostMapping("/user/rewrite")
	public String boardRewriteForm(Model m, BoardVO vo) {
		log.info("vo: "+vo); //부모글 글번호(num), 글제목(subject)
		m.addAttribute("vo",vo);
		return "board/boardRewrite";
	}//----------------
	@PostMapping("/user/edit")
    public String boardEditForm(Model m, BoardVO vo) {
        log.info("vo: "+vo);
        if(vo.getNum()==0||vo.getPasswd()==null) {
            return "redirect:/campus/board/list";
        }
        BoardVO dbVo=this.bService.selectBoardByNum(vo.getNum());
        if(dbVo==null) {
            return util.addMsgBack(m, "해당 글은 없어요");
        }
        //비번 체크
        if(!dbVo.getPasswd().equals(vo.getPasswd())) {
            return util.addMsgBack(m, "비밀번호가 일치하지 않아요");
        }
        m.addAttribute("vo",dbVo);
        return "board/boardEdit";
    }//--------------------------------------
    
    @PostMapping("/user/delete")
    public String boardDelete(Model m,@RequestParam(defaultValue="0") int num,
            @RequestParam(defaultValue = "") String passwd, HttpSession session) {
        log.info("num: "+num+", passwd: "+passwd);
        if(num==0||passwd.isEmpty()) {
            return "redirect:../board/list";
        }
        //해당 글을 DB에서 가져오기
        BoardVO vo=this.bService.selectBoardByNum(num);
        //비밀번호 체크
        String dbPasswd=vo.getPasswd();
        if(!dbPasswd.equals(passwd)) {
            return util.addMsgBack(m, "비밀번호가 일치하지 않아요");
        }
        //db에서 글 삭제처리
        int n=bService.deleteBoard(num);
        
        String upDir=session.getServletContext().getRealPath("/resources/board_upload");
        
        //서버에 첨부한 파일이 있다면 서버에서 삭제 처리
        if(n>0 && vo.getFilename()!=null) {
            File f=new File(upDir, vo.getFilename());
            if(f.exists()) {
                boolean b=f.delete();
                log.info("파일 삭제 여부: "+ b);
            }
        }
        
        String str=(n>0)? "삭제 성공":"삭제 실패";
        String loc=(n>0)?"../board/list":"javascript:history.back()";
        return util.addMsgLoc(m, str, loc);
    }//-----------------------------------
	
}

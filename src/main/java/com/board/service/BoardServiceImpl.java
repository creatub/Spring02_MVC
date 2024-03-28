package com.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.board.domain.BoardVO;
import com.board.domain.PagingVO;
import com.multi.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service("boardService")
@RequiredArgsConstructor
@Log4j
public class BoardServiceImpl implements BoardService {

	@Inject
	private final BoardMapper bMapper;
	
	@Override
	public int insertBoard(BoardVO board) {
		return bMapper.insertBoard(board);
	}

	@Override
	public List<BoardVO> getBoardAll(PagingVO paging) {
		return bMapper.getBoardAll(paging);
	}

	@Override
	public int getTotalCount(PagingVO paging) {
		return bMapper.getTotalCount(paging);
	}

	@Override
	public BoardVO selectBoardByNum(int num) {
		return bMapper.selectBoardByNum(num);
	}

	@Override
	public int updateReadnum(int num) {
		return bMapper.updateReadnum(num);
	}

	@Override
	public int deleteBoard(int num) {
		return bMapper.deleteBoard(num);
	}

	@Override
	public int updateBoard(BoardVO board) {
		return bMapper.updateBoard(board);
	}
	//답변 글쓰기 ----------
	@Override
	public int rewriteBoard(BoardVO board) {
		//[1] 부모글의 글번호(num)로 부모글의 refer(글그룹 번호), lev(답변레벨), sunbun(순서) 가져오기 ==> select문
		BoardVO parent = this.selectRefLevSunbun(board.getNum());
		log.info("parent: "+parent);
		//[2] 기존에 달린 답변글들이 있다면, 내 답변글을 insert하기 전에 기존 답변글들의 sunbun을 하나씩 증가시키자
		this.updateSunbun(parent);
		//[3] 내가 쓴 답변 글을 insert한다. ==> insert문
		//내가 쓴 답변글==> board
		//부모글 ==> parent (부모글 refer, lev, sunbun)
		board.setRefer(parent.getRefer());//부모글의 글그룹번호와 동일하게
		board.setLev(parent.getLev()+1);//답변레벨=부모Lev+1
		board.setSunbun(parent.getSunbun()+1);//순번=부모sunbun+1
		
		return bMapper.rewriteBoard(board);
	}

	@Override
	public BoardVO selectRefLevSunbun(int num) {
		return bMapper.selectRefLevSunbun(num);
	}

	@Override
	public int updateSunbun(BoardVO parent) {
		return bMapper.updateSunbun(parent);
	}

}

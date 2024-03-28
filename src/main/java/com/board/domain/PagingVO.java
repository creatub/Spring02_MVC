package com.board.domain;

import lombok.Data;

/* 페이징 처리 및 검색 기능을 모듈화하여
 * 재사용 가능하도록 처리하자 
 */
@Data
public class PagingVO {
	//페이징 처리 관련 프로퍼티
	private int pageNum;//현재 보여줄 페이지 번호
	private int oneRecordPage; // 한 페이지 당 보여줄 목록 개수
	private int totalCount;//총 게시글 수
	private int pageCount;//페이지 수
	
	//DB에서 레코드 끊어오기 위한 프로퍼티
	private int start;
	private int end;
	
	//페이징 블럭처리 위한 프로퍼티
	private int pagingBlock=5;//한 블럭 당 보여줄 페이지수
	private int prevBlock;// 이전 5개 (이전 블럭)
	private int nextBlock;// 이후 5개 (이후 블럭)
	
	//검색 관련 프로퍼티
	private String findType;//검색 유형
	private String findKeyword;//검색어
	
	public void init() {
		if(findType==null) {
			findType="";
		}
		if(findKeyword==null) {
			findKeyword="";
		}
		//페이지 수 구하기
		pageCount=(totalCount-1)/oneRecordPage+1;
		
		if(pageNum<1) {
			pageNum=1; //1페이지를 디폴트로
		}
		if(pageNum<pageCount) {
			pageNum=pageCount;//마지막 페이지로 설정
		}
		//select...
		//where rn>0 and rn<6 ==> xml에 부등호가 들어가면 시작태그< >로 인식 ==> 마크업 혹은 character data로 표기
		/*pageNum	oneRecordPage	start	end
		 * 1		5				0		6
		 * 2		5				6		11
		 * 3		5				11		15
		 * 
		 * start=(pageNum-1)*oneRecordPage;
		 * end= start+(oneRecordPage+1);
		 */
		start=(pageNum-1)*oneRecordPage;
		end= start+(oneRecordPage+1);
		
		/*페이징 블럭 연산 *******************
		 * pagingBlock=5;
		 * pageNum
		 * [1][2][3][4][5]▶ | ◀[6][7][8][9][10]▶ | ◀[11][12][13][14][15]▶ | ◀[16][17][18][19][20]▶
		 * 
		 * pageNum	pagingBlock		prevBlock(이전 5개)	nextBlock(이후 5개)
		 * 1~5 		5				0					6
		 * 6~10		5				5					11
		 * 11~15	5				10					16
		 * 
		 * prevBlock = (pageNum-1)/pagingBlock * pagingBlock
		 * nextBlock = prevBlock + (pagingBlock+1)
		 */
		prevBlock = (pageNum-1)/pagingBlock * pagingBlock;
		nextBlock = prevBlock + (pagingBlock+1);
		
		
	}//init()----------------
	
	/**페이지 네비게이션 문자열을 반환하는 메서드
     * myctx: 컨텍스트명(/campus)
     * loc : board/list
     * */
    public String getPageNavi(String myctx, String loc) {
        String link=myctx+"/"+loc; // /campus/board/list
        String qStr="?findType="+findType+"&findKeyword="+findKeyword;
        link+=qStr;
        
        StringBuilder buf=new StringBuilder();
        buf.append("<ul class='pagination justify-content-center'>");
        
        if(prevBlock>0) {
            buf.append("<li class='page-item'>")
                .append("<a class='page-link' href='"+link+"&pageNum="+prevBlock+"'>")
                .append("Prev")
                .append("</a>")
                .append("</li>");
        }
        for(int i=prevBlock+1;i<=nextBlock-1 && i<=pageCount  ;i++) {
            String css=(i==pageNum)?"active":"";
            buf.append("<li class='page-item "+css+"'>")
            .append("<a class='page-link' href='"+link+"&pageNum="+i+"'>")
            .append(i)
            .append("</a>")
            .append("</li>");
            
        }//for------
        if(nextBlock <= pageCount) {
            buf.append("<li class='page-item'>")
                .append("<a class='page-link' href='"+link+"&pageNum="+nextBlock+"'>")
                .append("Next")
                .append("</a>")
                .append("</li>");
        }
        buf.append("</ul>");
        return buf.toString();
    }//---------------------------------------------
	
}//////////////////////////////

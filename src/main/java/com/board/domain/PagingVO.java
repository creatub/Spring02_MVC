package com.board.domain;

import lombok.Data;

/* ����¡ ó�� �� �˻� ����� ���ȭ�Ͽ�
 * ���� �����ϵ��� ó������ 
 */
@Data
public class PagingVO {
	//����¡ ó�� ���� ������Ƽ
	private int pageNum;//���� ������ ������ ��ȣ
	private int oneRecordPage; // �� ������ �� ������ ��� ����
	private int totalCount;//�� �Խñ� ��
	private int pageCount;//������ ��
	
	//DB���� ���ڵ� ������� ���� ������Ƽ
	private int start;
	private int end;
	
	//����¡ ��ó�� ���� ������Ƽ
	private int pagingBlock=5;//�� �� �� ������ ��������
	private int prevBlock;// ���� 5�� (���� ��)
	private int nextBlock;// ���� 5�� (���� ��)
	
	//�˻� ���� ������Ƽ
	private String findType;//�˻� ����
	private String findKeyword;//�˻���
	
	public void init() {
		if(findType==null) {
			findType="";
		}
		if(findKeyword==null) {
			findKeyword="";
		}
		//������ �� ���ϱ�
		pageCount=(totalCount-1)/oneRecordPage+1;
		
		if(pageNum<1) {
			pageNum=1; //1�������� ����Ʈ��
		}
		if(pageNum<pageCount) {
			pageNum=pageCount;//������ �������� ����
		}
		//select...
		//where rn>0 and rn<6 ==> xml�� �ε�ȣ�� ���� �����±�< >�� �ν� ==> ��ũ�� Ȥ�� character data�� ǥ��
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
		
		/*����¡ �� ���� *******************
		 * pagingBlock=5;
		 * pageNum
		 * [1][2][3][4][5]�� | ��[6][7][8][9][10]�� | ��[11][12][13][14][15]�� | ��[16][17][18][19][20]��
		 * 
		 * pageNum	pagingBlock		prevBlock(���� 5��)	nextBlock(���� 5��)
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
	
	/**������ �׺���̼� ���ڿ��� ��ȯ�ϴ� �޼���
     * myctx: ���ؽ�Ʈ��(/campus)
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

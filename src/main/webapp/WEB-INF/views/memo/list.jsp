<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
	body *{
		padding:0;
		margin:0;
		box-sizing: border-box;
	}
	#memoList{
		text-align:center;
	}
	ul.memos{
		margin:2em auto;
	}
	ul.memos li{
		list-style:none;
		line-height:40px;
		height:40px;
		padding:5px;
		border-bottom:1px solid silver;
		float:left;
		
	}
	ul.memos li:nth-child(4n+1){
		width:10%
	}
	ul.memos li:nth-child(4n+2){
		width:50%
	}
	ul.memos li:nth-child(4n+3){
		width:10%
	}
	ul.memos li:nth-child(4n+4){
		width:30%
	}
	span.ed{
		float:right;
	}

</style>
<!-- 글쓰기 페이지 포함 -------------- -->
<br>
	<!-- 컨트롤러 url 매핑 이름 써주면 갖고옴-->
	<c:import url="/memo" />
<br><br><br><br><br><br><br>

<!-- --------------------------- -->
<div id="memoList">
	<!-- <h1 class="text-center">한줄 메모장 목록</h1> -->
	<ul class="memos">
		<li>글번호</li>
		<li>메모내용</li>
		<li>작성자</li>
		<li>작성일</li>
		<!-- ------------ -->
		<c:forEach var="memo" items="${voList }">
			<li>
				<c:out value="${memo.no }"/>
			</li>
			<li>
				<c:out value="${memo.msg }"/>
			<span class="ed">
			 [<a href="#" onclick="edit('<c:out value='${memo.no }'/>')">수정</a>|
            <a href="memoDel?no=<c:out value='${memo.no }'/>">삭제</a>]
			</span>
			</li>
			<li>
				<c:out value="${memo.name }"/>
			</li>
			<li>
			<fmt:formatDate value="${memo.wdate }" pattern="yy-MM-dd" />
			</li>
		</c:forEach>
		
		<!-- ------------ -->
	</ul>
	<div style="clear:both"></div>
	<br>
	<div class="page">
	<c:forEach var="i" begin="1" end="${pageCount }">
		[<a href="memoList?pageNum=${i }">${i}</a>]
	</c:forEach>
		<span class="ed">총 게시글 수 <c:out value="${totalCount }"/>개</span>
	</div>
</div>

<script>
    const edit=function(num){
        //alert(num);
        let url='memoEdit?no='+num;//GET방식 요청
        win=window.open(url,"edit","width=700, height=400, left=100, top=100")
    }
</script>
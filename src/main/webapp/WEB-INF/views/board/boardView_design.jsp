<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- boardView.jsp -->
<style>
	td:nth-child(2n){
		text-align:left;
		padding-left:1.5em;
	}
</style>
<div class="container">
		<h1 class="text-center mt-4">Spring Board 내용</h1>
	<br>
	<br> 
	<!--/wview/12
		/board/write
	  -->
	<div class="text-center">  
		<a href="../write">글쓰기</a>|<a href="${myctx}/board/list">글목록</a>| <br>
	</div>
	
	<br>
	<br>
	<h3>해당 게시글은 존재하지 않아요</h3>
	<table class="table table-bordered">
		<tr>
			<td width="20%" class="m1"><b>글번호</b></td>
			<td width="30%">
			a
			</td>
			<td width="20%" class="m1"><b>작성일</b></td>
			<td width="30%">
			a
			</td>
		</tr>
		<tr>
			<td width="20%"  class="m1"><b>글쓴이</b></td>
			<td width="30%">a</td>
			<td width="20%"  class="m1"><b>조회수</b></td>
			<td width="30%">a</td>
		</tr>
		<tr>

			<td width="20%"  class="m1"><b>첨부파일</b></td>
			<td colspan="3" class="text-left">&nbsp; 
			<!-- 첨부파일이 있다면 --> 

				<a	href="a" download> 
				a</a>				
				<img src="a" style="width:3em">
				[ a ]bytes
			</td>
		</tr>
		<tr>
			<td width="20%" class="m1"><b>제목</b></td>
			<td colspan="3">a}</td>
		</tr>
		<tr>
			<td width="20%" class="m1"><b>글내용</b></td>
			<td colspan="3">
			
			<pre>a}</pre>
			
			</td>
		</tr>
		<tr>
			<td colspan="4" class="text-center">
			
				<a href="#"	onclick="goEdit()">글수정</a>| 
				<a href="#" onclick="goDel()">글삭제</a> |
			
			
			<a href="javascript:goRe()">답변쓰기</a>
				
		</td>
		</tr>
	</table>

	<br><br>
	<!-- 삭제 또는 수정 form 시작------------------------------- -->
	<form id="bf" name="bf" method="post">
		<!-- 수정 또는 삭제할 글번호 =>hidden -->
		<input type="hidden" name="num" value="${board.num}">
		<div id="divPwd" style="display:none" class="col-8 offset-2 text-center">
			<label for="pwd">비밀번호</label>
			<input type="password" name="passwd" id="pwd" placeholder="Password" required>
			<button class="btn btn-info" id="btn1"></button>
		</div>
	</form>
	<!-- --------------------------------------------------- -->
	
	<!-- 답변달기  form---------------------------------- -->
    <form name="reF" id="reF" action="${myctx}/user/rewrite" method="post">
      	<!-- hidden으로 부모글의 글번호(num)와 제목(subject)을 넘기자 -->
      	<input type="hidden" name="num" value="<c:out value="1"/>">
      	<input type="hidden" name="subject" value="<c:out value="a"/>">
    </form>
   <!-- ---------------------------------------------- -->
	
	

</div><!--  .container end -->

<script>
	function goRe(){
		reF.submit();
	}//---------------------


	function goDel(){
		let yn=confirm("글을 삭제할까요?");
		if(yn){
			let obj=document.getElementById('divPwd');
			let btn1=document.getElementById('btn1');	
			obj.style.display='block';
			btn1.innerText='글삭제';
			bf.action='${myctx}/user/delete';//절대경로
		}//if---
		
	}//goDel()----------------
	
	
	function goEdit(){
		let obj=document.getElementById('divPwd');
		let btn1=document.getElementById('btn1');	
		obj.style.display='block';
		btn1.innerText='글수정';
		bf.action='${myctx}/user/edit';
	}//goDel()----------------
	
</script>























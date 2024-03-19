<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	.jumbotron, .navbar{
		display:none;
	}
	#id{
		width:150px;
	}
</style>
<script>
	function id_check(){
		if(!idf.userid.value){
			alert('아이디를 입력하세요');
			idf.userid.focus();
			return false;
		}//---
		return true;
	}
	function setId(uid){
		//window>document>form>input,select,
		//opener==>부모창(window객체)
		opener.document.mf.userid.value = uid;
		//self==>자기창(window객체)
		self.close();//팝업창 닫기
	}
</script>
<!-- idCheck.jsp -->
<div class="container">
	<div id="idResult" style="margin:1em auto">
		<h3 style="color:red">${msg}</h3>
		<br>
		<c:if test="${result eq 'ok'}">
		<button class="btn btn-outline-success" onclick="setId('${uid}')">아이디 사용하기</button>
		</c:if>
		<br>
	</div>
	<form name="idf" action="idCheck" method="post" onsubmit="return id_check()">
	<!-- id_check가 반환하는 값이 true이면 반환 false이면 반환 안함 -->
		<label for="id">아이디</label>
		<input type="text" name="userid" id="userid" class="form-control"
		placeholder="ID" autofocus="autofocus"><br>
		<button class="btn btn-outline-success">확  인</button>
	
	</form>

</div>
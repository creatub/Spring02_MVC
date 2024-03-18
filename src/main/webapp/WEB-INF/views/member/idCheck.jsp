<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
	.wrap header, footer{
		display:none;
	}
	#id{
		width:150px;
	}
</style>
<script>
	function id_check(){
		if(!idf.id.value){
			alert('아이디를 입력하세요');
			idf.id.focus();
			return false;
		}//---
		return true;
	}
</script>
<!-- idCheck.jsp -->
<div class="container">
	<form name="idf" action="idCheck" method="post" onsubmit="return id_check()">
	<!-- id_check가 반환하는 값이 true이면 반환 false이면 반환 안함 -->
		<label for="id">아이디</label>
		<input type="text" name="id" id="id" placeholder="ID" autofocus="autofocus">
		<button class="btn">확  인</button>
	
	</form>

</div>
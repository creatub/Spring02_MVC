<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import ="javax.servlet.http.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-8 offset-2" style="height:450px">
	<h1 class="text-secondary text-center">Login</h1>
	<br><br>
	<%--
		el표현식을 이용한 쿠키 출력
		${cookie.키이름.value}
		
		<h1>${cookie.uid.value }</h1>
		
		${cookie} ==> Map객체
		${cookie.키이름} ==> Cookie객체
		${cookie.키이름.value} ==> String객체. 쿠키.getValue()
	 --%>
	
	<form name="loginF" method="post" action="login">
	<table class="table">
		<tr>
			<th width="20%">아이디</th>
			<td>
			<input type="text" name="userid" value="${cookie.uid.value }" required class="form-control"><!-- required하면 유효성 체크 안해도 입력안하면 alert -->
			</td>
		</tr>
		<tr>
			<th width="20%">비밀번호</th>
			<td>
			<input type="text" name="pwd" required class="form-control">
			</td>
		</tr>
		<tr>
			<td colspan="2" style="text-align:center">
				<label for="saveId">
					<input type="checkbox" name="saveId" id="saveId" 
					<c:if test="${cookie.uid.value ne null }">
						checked
					</c:if>
					style="width:10%">아이디 저장
				</label>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="btn btn-primary">로그인</button>
			</td>
	</table>
	</form>
</div>

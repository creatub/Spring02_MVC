<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
#pageNavi{
	width:60%;
	margin:auto;
	text-align:center;
}
.pagination li{
	float:left;
	list-style-type:none;
	padding:5px;
	width:2.5em;
	border:1px solid #ddd;
	border-radius:3px;
}

</style>
<div id="wrap" class="container">
	<h1>회원 목록 [관리자 페이지 - Admin]</h1>
	<br>
	<h3>총 회원수 : ${totalCount }</h3>
	<table border="1">
		<tr>
			<th>이름</th>
			<th>아이디</th>
			<th>연락처</th>
			<th>가입일</th>
			<th>삭제 | 수정</th>
		</tr>
		<!-- ------------- -->
		<c:choose>
			<c:when test="${memberAll eq null or empty memberAll}">
			<tr>
				<td colspan="5">
				<b>데이터가 없습니다</b>
				</td>
			</tr>
			</c:when>
			<c:otherwise>
				<c:forEach var="user" items="${memberAll}">
					<tr>
						<td>${user.name}</td>
						<td>${user.id}</td>
						<td>${user.tel}</td>
						<td>${user.indate}</td>
						<%-- <td><a href="MemberDelete?id=<%=vo.getId()%> ">삭제</a></td>--%>
						<td>
						<a href="#" onclick="goDel('${user.id}')">삭제</a> | 
						<a href="#" onclick="goEdit('${user.id}')">수정</a>
						</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		<!-- ------------- -->
	</table>
	<div id="pageNavi">
		<ul class="pagination">
		<c:forEach begin="1" end="${pageCount }" var="i">
			<li><a href="memberList.do?pageNum=${i}">${i}</a></li>
		</c:forEach>
		</ul>
	</div>
	<!-- 삭제 관련 form -->
	<form name="df" method="post" action="memberDelete.do">
		<input type="hidden" name="id">
	</form>
	<!-- 수정 관련 form -->
	<form name="uf" method="post" action="memberUpdate.do">
		<input type="hidden" name="id">
	</form>
</div>
	<script>
		function goEdit(uid){
			uf.id.value=uid;
			let yn = confirm(uid+"님의 정보를 수정할까요?");
			if(yn==true){
				uf.submit();
			}
		}//-------------
		function goDel(uid){
			df.id.value=uid;
			let yn = confirm(uid+"님의 정보를 삭제할까요?");
			if(yn==true){
				df.submit();
			}
		}//-----------
	</script>

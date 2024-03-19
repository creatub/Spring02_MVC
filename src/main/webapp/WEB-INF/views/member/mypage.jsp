<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="container">
	
	<h1 class="text-center">MyPage- 회원 인증 페이지</h1>
	<p class="alert alert-success py-5">
		회원들만 들어올 수 있는 페이지 입니다.
		<br>
		<ul>
			<li>회원 아이디 : ${loginUser.userid }</li>
			<li>회원 이름 : ${loginUser.name }</li>
			<li>회원 상태 : ${loginUser.mstateStr }[${loginUser.mstate }]</li>
			<li>연 락 처 : ${loginUser.allHp }</li>
			<li>주  소 : ${loginUser.addr }</li>
		</ul>
		 
	</p>


</div>

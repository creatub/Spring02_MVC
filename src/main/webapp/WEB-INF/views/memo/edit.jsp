<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
 body *{
		padding:0; 
		margin:0;
		box-sizing: border-box;		
	}
  h1{
  	text-align: center;
  	font-family: sans-serif;
  	margin:1em;
  }	
  button{
  	padding:5px;
  	width:100px;
  	border:1px solid #ddd;
  	border-radius:3px;
  }	
  input{
  	padding:4px;
  	width:60%;
  	border:1px solid #ddd;
  }
  ul.memo{
  	width:80%;
  	margin:1em auto;
  }
  ul.memo li{
	float:left;
	line-hight:40px;
	height:40px;
	padding:4px;
  }
  ul.memo li{
	list-style:none;
	
	border-top:1px solid silver;
  }
  ul.memo li:nth-child(2n+1){
  	width:20%;
	
  }
  ul.memo li:nth-child(2n){
  	width:80%;	
  }
  </style>
<div class="container">
<h1>한줄 메모장-Edit</h1>
<form name="mf" action="memoEdit" method="post">
<input type="hidden" name="no" value="${vo.no}">
    <ul class="memo">
        <li>작성자</li>
        <li><input type="text" name="name" value="${vo.name}" id="name" placeholder="Name" required="required"></li>
        <li>메모내용</li>
        <li><input type="text" name="msg" value="${vo.msg}" id="msg" placeholder="Message" required="required"></li>
        <li></li>
        <li><button>글수정</button></li>
    </ul>
</form>
</div>
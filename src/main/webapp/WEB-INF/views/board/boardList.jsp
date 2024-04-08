<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
	.container{
		overflow-y: auto;
	}
</style>

<div class="container text-center">
		<h1 class="text-center mt-4">Spring Board 목록</h1>
	<br><br>
	<a href="write">글쓰기</a>
	<br><br>
	<!-- 검색 form 시작-----------------------  -->
	<div id="divFind" class="col-md-10 offset-1 text-center">
		<form name="findF" id="findF" action="list"
						class="form-inline">
						<select name="findType" id="findType" class="form-control m-3">
							<option value="0">::검색 유형::</option>
							<option value="1">제 목</option>
							<option value="2">작성자</option>
							<option value="3">글내용</option>
						</select> 
						<input type="text" name="findKeyword" id="findKeyword"
							placeholder="검색어를 입력하세요" class="form-control m-3" required>
						<button class="btn btn-primary">검 색</button>
		</form>
	</div>
	<!-- ----------------------------------- -->
	<br><br>
	<table class="table">
		<tr class="m1">
			<th width="10%">글번호</th>
			<th width="40%">제  목</th>
			<th width="20%">작성자</th>
			<th width="20%">작성일</th>
			<th width="10%">조회수</th>			
		</tr>
		<c:choose>
            <c:when test="${boardList eq null or empty boardList}">
			<tr>
				<td colspan="5">
					<b>데이터가 없습니다</b>
				</td>
			</tr>
			</c:when>
            <c:otherwise>
              	<c:forEach var="vo" items="${boardList}" varStatus="state">
				<tr>
					<td><c:out value="${vo.num}"/></td>
					<td align="left" style="padding-left:20px">
						<!-- 답변 레벨에 따라 들여쓰기 -->
						<c:forEach var="k" begin="1" end="${vo.getLev()}">
							&nbsp;&nbsp;&nbsp;
						</c:forEach>
						<c:if test="${vo.lev>0}">
							<img src="${myctx}/resources/reply.png" width="15px">
						</c:if>
						<a href="view/<c:out value='${vo.num}'/>"><c:out value='${vo.subject }'/></a>
						<!-- 최신글에 new 붙이기 -->
						<c:if test="${vo.newImg < 1}">
							<span class="badge badge-danger">New</span>
						</c:if>
						<c:if test="${vo.filename ne null}">
							<img src="${pageContext.request.contextPath}/resources/attach.png" width="15px">
						</c:if>
					</td>
					<td><c:out value='${vo.userid}'/></td>
					<td>
						<fmt:formatDate value="${vo.wdate}" pattern="yy-MM-dd"/>
					</td>
					<td><c:out value='${vo.readnum}'/></td> 
				</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>

		<tr>
			<td colspan="3">
				<ul class="pagination justify-content-center">
				<c:if test="${page.prevBlock >0}">
					<li class="page-item">
						<a class="page-link" href="list?pageNum=${page.prevBlock }">Prev</a>
					</li>
				</c:if>
				<%-- <c:forEach var="i" begin="1" end="${page.pageCount}"> --%>
				<c:forEach var="i" begin="${page.prevBlock +1}" end="${page.nextBlock-1}">
					<c:if test="${i<=page.pageCount}">
						<li class="page-item <c:if test="${i eq page.pageNum }">active</c:if>">
							<a class="page-link"
							  href="list?pageNum=${i}">${i}</a>
						</li>
					</c:if>
				</c:forEach>
				<c:if test="${page.nextBlock < page.pageCount}">
					<li class="page-item">
						<a class="page-link" href="list?pageNum=${page.nextBlock}">Next</a>
					</li>
				</c:if>

				</ul>
				
				
			</td>
			<td colspan="2">
				총게시글 수: <span style="color:red;font-weight:bold">${boardSum }</span> 개
			</td>
		</tr>
	</table>

</div>





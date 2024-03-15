<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- prodList.jsp -->
<h2 class="text-center">상품목록-Product List</h2>
<div class="row my-4">
	<div class="col-md-10 offset-1">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>상품번호</th>				
					<th>카테고리</th>				
					<th>상품명</th>				
					<th>이미지</th>				
					<th>가격</th>				
					<th>수정|삭제</th>				
				</tr>
			</thead>
			<tbody>
			<!-- --------------- -->
			<c:choose>
				<c:when test="${itemList eq null or empty itemList }">
					<tr><td colspan="6">
					<b>데이터가 없습니다</b>
					</td></tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="item" items="${itemList}">
						<tr>
							<td>${item.pnum}</td>
							<td>${item.upCg_name}>${item.downCg_name}</td>
							<td>
								<a href="../prodDetail?pnum=${item.pnum }">${item.pname}</a>
								<span class="badge badge-info">${item.pspec}</span>
							</td>
							<td>
								<a href="../prodDetail?pnum=${item.pnum }">
								<img src="${myctx}/resources/product_images/${item.pimage1}" width="100px">
								</a>
							</td>
							<td>
							정 가 :<del>
								<fmt:formatNumber value="${item.price}" pattern="###,###"/>
							</del> 원 <br>
							판매가 :<b>
								<fmt:formatNumber value="${item.saleprice}" pattern="###,###"/>
							</b>원 <br>
							<span class="badge badge-danger">${item.percent }% 할인</span>
							<span class="badge badge-warning">${item.point }p 적립</span>
							</td>
							<td>
							<a href='#' onclick="edit('${item.pnum}')">수정</a>|
							<a href='#' onclick="remove('${item.pnum}')">삭제</a>
							</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			<!-- --------------- -->
			</tbody>
		</table>
	</div>
</div>
	<form id="pf" method="post">
		<input type="hidden" name="pnum" id="pnum">
	</form>
	
	<script>
		function edit(num){
			//form이름.pnum.value=num;
			$('#pnum').val(num);//jquery함수 val(): setter, getter 둘 다 사용함
								//$('#pnum').val(100)==>setter
								//$('#pnum').val() ==> getter
			//$('form[id="pf"]') => 속서 선택자
			$('#pf').prop("action","prodEditForm")
					.submit();
		}
		function remove(num){
			$('#pnum').val(num);
			$('#pf').prop("action","prodDel")
					.submit();
			
		}
	</script>
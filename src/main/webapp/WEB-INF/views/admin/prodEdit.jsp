<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<h1 class="text-center">상품 수정 - Product Edit</h1>
<div class="row mt-5">
<div class="col-md-10 offset-1">
	<form name="pf" id="pf" action="prodInsert" method="post" enctype="multipart/form-data">
	<!-- hidden data(mode값이 edit이면 수정처리, mode값이 없다면 insert처리) -->
		<input type="hidden" name="mode" value="edit">
	<!-- -------------------------------------------------------- -->
		<table class="table">
			<tr>
				<th width="20%">카테고리</th>
				<td width="80%">
					<span class="text-primary">${item.upCg_name } > ${item.downCg_name }</span>
					<select name="upCg_code" id="upCg_code">
						<option value="">::상위 카테고리::</option>
						<c:forEach var="up" items="${upCgList}">
							<option value="${up.upCg_code}" <c:if test="${up.upCg_code eq item.upCg_code}">selected</c:if>>
								${up.upCg_name}
							</option>
						</c:forEach>
					</select>
					<!-- 하위 카테고리 -->
					<span id="downCg">
					<select name="downCg_code" id="downCg_code">
						<option value="">::하위 카테고리::</option>
						<option value="1"  <c:if test="${item.downCg_code eq 1}">selected</c:if>  >노트북</option>
						<option value="2" <c:if test="${item.downCg_code eq 2}">selected</c:if>>냉장고</option>
					</select>
					</span>
				</td>
			</tr>
			<!-- 상품번호(PK) readonly -->
			<tr>
				<td width="20%"><b>상품번호</b></td>
				<td width="80%">
				<input type="text" name="pnum" value="${item.pnum }" id="pnum" class="form-control" readonly>
				</td>
			</tr>
			<tr>
				<td width="20%"><b>상품명</b></td>
				<td width="80%">
				<input type="text" name="pname" value="${item.pname }" id="pname" class="form-control">
				</td>
			</tr>
			<tr>
				<td width="20%"><b>제조사</b></td>
				<td width="80%">
				<input type="text" name="pcompany" value="${item.pcompany }" id="pcompany" class="form-control">
				</td>
			</tr>
			<tr>
				<td width="20%">
					<b>상품 스펙</b>
				</td>
				<td width="80%"> <span class="badge badge-primary">${item.pspec}</span>
				<select name="pspec" id="pspec">
					<option value="NEW" <c:if test="${item.pspec eq 'NEW' }">selected</c:if> >NEW</option>
					<option value="HIT" <c:if test="${item.pspec eq 'HIT' }">selected</c:if>>HIT</option>
					<option value="BEST" <c:if test="${item.pspec eq 'BEST' }">selected</c:if>>BEST</option>
				</select>
				
				</td>
			</tr>
			<tr>
				<td width="20%">
				<b>상품 이미지</b>
				</td>
				<td width="80%">
				<!-- db:pimage1,pimage2,pimage3, vo:pimage1,pimage2,pimage3 (String) 
					파일업로드 시 => MultipartFile타입으로 받아야 함
					input name을 VO의 프로퍼티명과 다르게 주자. input name을 pimage
					@RequestParam(name="pimage") List<MultipartFile> pimage
				-->
				<c:if test="${item.pimage1 ne null }">
					<img src="../resources/product_images/${item.pimage1}" width="120px">
				</c:if>
				<c:if test="${item.pimage2 ne null }">
					<img src="../resources/product_images/${item.pimage2}" width="120px">
				</c:if>
				<c:if test="${item.pimage3 ne null }">
					<img src="../resources/product_images/${item.pimage3}" width="120px">
				</c:if>
					<br>					
					<input type="file" name="pimage" accept="images/*"  class="form-control"><br>
					<input type="file" name="pimage" accept="images/*"  class="form-control"><br>
					<input type="file" name="pimage" accept="images/*"  class="form-control"><br>				
				</td>
			</tr>
			<tr>
				<td width="20%">
				<b>상품 수량</b>
				</td>
				<td width="80%">
					<input type="number" name="pqty" value="${item.pqty }" id="pqty" min="1" max="100"
					 class="form-control">
				</td>
			</tr>
			<tr>
				<td width="20%">
				<b>상품 정가</b>
				</td>
				<td width="80%">
					<input type="number" name="price" value="${item.price}" id="price" min="0"  required
					 class="form-control">
				</td>
			</tr>
			<tr>
				<td width="20%">
				<b>상품 판매가</b>
				</td>
				<td width="80%">
					<input type="number" name="saleprice" value="${item.saleprice}" id="saleprice"  min="0"
					 required 
					class="form-control">
				</td>
			</tr>
			<tr>
				<td width="20%">
				<b>상품 설명</b>
				</td>
				<td width="80%">
					<textarea name="pcontents" value="${item.pcontents}" id="pcontents"
					 rows="5" cols="60" class="form-control"></textarea>
				</td>
			</tr>
			<tr>
				<td width="20%">
				<b>상품 포인트</b>
				</td>
				<td width="80%">
					<input type="number" name="point" value="${item.point}" id="point"
					 min="0" required
					 class="form-control">
				</td>
			</tr>
			<tr>
				<td colspan="2" class="text-center">
					<button class="btn btn-outline-primary">상품수정</button>				
					<button class="btn btn-outline-danger">다시쓰기</button>
				</td>
			</tr>
		</table>
	</form>
</div>
</div>
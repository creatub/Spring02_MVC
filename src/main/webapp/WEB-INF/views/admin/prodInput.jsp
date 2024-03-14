<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<h1 class="text-center">상품 등록 - Product Form</h1>
<div class="row">
<div class="col-md-10 offset-1">
	<form name="pf" id="pf" action="prodInsert" method="post" enctype="multipart/form-data">
		<table class="table">
			<tr>
				<th width="20%">카테고리</th>
				<td width="80%">
					<select name="upCg_code" id="upCg_code">
						<option value="">::상위 카테고리::</option>
						<c:forEach var="up" items="${upCgList}">
							<option value="${up.upCg_code}">
								${up.upCg_name}
							</option>
						</c:forEach>
					</select>
					<!-- 하위 카테고리 -->
					<span id="downCg">
					<select name="downCg_code" id="downCg_code">
						<option value="">::하위 카테고리::</option>
						<option value="1">노트북</option>
						<option value="2">냉장고</option>
					</select>
					</span>
				</td>
			</tr>
			
			<tr>
				<td width="20%"><b>상품명</b></td>
				<td width="80%">
				<input type="text" name="pname" id="pname" class="form-control">
				</td>
			</tr>
			<tr>
				<td width="20%"><b>제조사</b></td>
				<td width="80%">
				<input type="text" name="pcompany" id="pcompany" class="form-control">
				</td>
			</tr>
			<tr>
				<td width="20%">
					<b>상품 스펙</b>
				</td>
				<td width="80%">
				<select name="pspec" id="pspec">
					<option value="NEW" selected>NEW</option>
					<option value="HIT">HIT</option>
					<option value="BEST">BEST</option>
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
					<input type="number" name="pqty" id="pqty" min="1" max="100"
					 class="form-control">
				</td>
			</tr>
			<tr>
				<td width="20%">
				<b>상품 정가</b>
				</td>
				<td width="80%">
					<input type="number" name="price" id="price" min="0"  required
					 class="form-control">
				</td>
			</tr>
			<tr>
				<td width="20%">
				<b>상품 판매가</b>
				</td>
				<td width="80%">
					<input type="number" name="saleprice" id="saleprice"  min="0"
					 required 
					class="form-control">
				</td>
			</tr>
			<tr>
				<td width="20%">
				<b>상품 설명</b>
				</td>
				<td width="80%">
					<textarea name="pcontents" id="pcontents"
					 rows="5" cols="60" class="form-control"></textarea>
				</td>
			</tr>
			<tr>
				<td width="20%">
				<b>상품 포인트</b>
				</td>
				<td width="80%">
					<input type="number" name="point" id="point"
					 min="0" required
					 class="form-control">
				</td>
			</tr>
			<tr>
				<td colspan="2" class="text-center">
					<button class="btn btn-outline-primary">상품등록</button>				
					<button class="btn btn-outline-danger">다시쓰기</button>
				</td>
			</tr>
		</table>
	</form>
</div>
</div>
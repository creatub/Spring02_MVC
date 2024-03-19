<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${cartArr ne null and not empty cartArr }">
	<c:set var="uid" value="${cartArr[0].userid}" />
</c:if>

    <h2 class="text-center text-secondary">
        ${loginUser.name} [${loginUser.userid}]  님의 장바구니
    </h2>
    <br><br>
    <div>
    <!-- 주문폼 시작------------------------------- -->
    <form name="orderF" action="order" method="post">
        <table class="table table-striped">
                <thead>
                    <tr>
                        <th>상품번호</th>
                        <th>상품명</th>
                        <th>수량</th>
                        <th>단가</th>
                        <th>금액</th>
                        <th>삭제</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- --------------------------- -->
                <c:choose>
                	<c:when test="${cartArr eq null or empty cartArr }">
                        <tr>
                            <td colspan="6">
                                <b>장바구니에 담긴 상품이 없습니다</b>
                            </td>
                        </tr>
                    </c:when>
                	<c:otherwise>
	                	<c:forEach var="cart" items="${cartArr }" varStatus="state">
                            <tr>
                                <td>
                                <label>
                                <input type="checkbox" name="pnum" id="pnum${state.index}"
                                  value="${cart.pnum}">
                                ${cart.pnum}
                                </label>
                                </td>
                                <td>
                                <h4>${cart.items[0].pname}</h4>
                                <a href="../prodDetail?pnum=${cart.pnum}" target="_blank">
                                <img src="../resources/product_images/${cart.items[0].pimage1}"
                                 class="img-fluid" style="width:140px">
                                </a>
                                </td>
                                <td>
                                
                                <input type="number" name="pqty" id="pqty${state.index}"
                                 value="${cart.pqty}" min="1" max="50">
                                <button type="button" class="btn btn-success"
                                 onClick="cartEdit('${cart.cnum}','${state.index}')">수정</button>
                                </td>
                                <td>
                                <del><fmt:formatNumber value="${cart.items[0].price}" pattern="###,###"/> 원</del><br>
                                <fmt:formatNumber value="${cart.items[0].saleprice}" pattern="###,###"/> 원
                                <span class="badge badge-danger">${cart.items[0].percent}% 할인</span>
                                <span class="badge badge-success">${cart.items[0].point}POINT</span> 
                                </td>
                                <td>
                                <fmt:formatNumber value="${cart.items[0].totalPrice}" pattern="###,###"/> 원<br>
                                <span class="badge badge-success">${cart.items[0].totalPoint}POINT</span> 
                                </td>
                                <td>
                                <a href="javascript:cartDel('${cart.cnum}')">삭제</a>
                                </td>
                            </tr>
    	                </c:forEach>
    	            </c:otherwise>
    	            </c:choose>
                    <!-- --------------------------- -->
                    
                    <tr>
                        <td colspan="3">
                            <h5>장바구니 총액: <span class='text-danger'> 
                                <fmt:formatNumber value="${cartSum.cartTotalPrice}"  pattern="###,###" />
                            원</span></h5>
                            <h5>장바구니 총포인트: <span class='text-success'> 
                                <fmt:formatNumber value="${cartSum.cartTotalPoint}"  pattern="###,###" />
                            Point</span></h5>
                        </td>
                        <td colspan="3">
                            <button type="button" class="btn btn-outline-info" onclick="goOrder()">주문하기</button>              
                            <button type="button" class="btn btn-outline-danger" onclick="location.href='../index'">계속쇼핑</button>
                            <button type="button" class="btn btn-outline-success" onclick="cartDelAll()">장바구니 비우기</button>
                        </td>
                    </tr>
                </tbody>
            
            </table>
    </form>
    </div>
    <!-- 주문폼 끝------------------- -->
    
    <!-- 삭제 폼 -------------------- -->
    <form name="df" id="df" action="cartDel">
        <input type="hidden" name="cnum" id="del_cnum">
    </form>       
    <!--수정 폼-----------------------  -->
    <form name="ef" id="ef" action="cartEdit">
        <input type="hidden" name="cnum" id="edit_cnum">
        <input type="hidden" name="pqty" id="edit_pqty">
    </form>
    <!-- ------------------------------ -->
    
<script>
	function goOrder(){
		//1. 장바구니에 담긴 상품이 없는 경우
		var chk = $('input[name="pnum"]')
		//alert(chk.length);
		if(chk.length==0){
			return;
		}
		//2. 체크박스에 체크하지 않은 경우 alert()
		let count=0;
		for(var i=0;i<chk.length;i++){
			var b=$(chk[i]).is(":checked");
			//alert(b);
			if(b){//체크박스에 체크된 경우 => pnum, pqty가 서버로 넘어간다
				count++;
				$('#pqty'+i).prop('disabled', false);
			}else{ // 체크 안된 경우는 => pqty가 넘어간다 ==> 넘어가지 않도록 막아주자
				$('#pqty'+i).prop('disabled', true);
				//disabled속성을 가진 입력 폼들은 서버에 전송되지 않는다.
			}
		}//for----
		if(count==0){
			alert('주문할 상품을 선택하세요');
			return;
		}
		//3. 체크한 상품번호와 수량만 서버에 넘긴다
		orderF.method='get'; // 나중에 post 수정
		orderF.submit();
	}//---------------------
    function cartEdit(cnum, i){
        //alert(cnum+"/"+i);
        let qty = $('#pqty'+i).val();//getter
        //alert(qty);
        $('#edit_cnum').val(cnum); //setter
        $('#edit_pqty').val(qty); //setter
        $('#ef').prop('method','post')
        		.submit();
    }//-------------------------------
    function cartDel(cnum){//특정 상품 삭제
        let yn=confirm('상품을 정말 삭제할까요?')
        if(!yn) return;
    	$('#del_cnum').val(cnum);
    	
    	$('#df').prop('method','post')
    			.submit();
    }//------------------------
    function cartDelAll(){//장바구니 모두 비우기
        let yn=confirm('정말 모두삭제할까요?')
        if(yn){
            location.href="cartDelAll";
        }
    }//---------------------------
</script>
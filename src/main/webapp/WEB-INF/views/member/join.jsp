<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 외부 js파일 참조 ------------------ -->
<script src="resources/js/member.js"></script>
<!-- ------------------------------ -->
<script>
	var win = null;
	function openWin(){
		var url="idCheck?mode=pop";
		win=open(url, "idCheck", "width=500, height=300, left=100, top=100");
		//get방식 요청
	}
</script>
<div class="col-md-10 offset-1">
	<h2 class="text-center mt-4">Signup Page</h2>
        <br><br>
        
        <form name="mf" action="signup" method="post">
    
        <table  id="userTable" class="table">             
                <tr>
                    <th>이름</th>
                    <td>
                        <input type="text" name="name" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th>아이디</th>
                    <td>
                        <input type="text" name="userid" class="form-control" readonly>
                        <button type="button" class="btn btn-success" onclick="openWin()">아이디 중복체크</button>
                    </td>
                </tr>
                <tr>
                    <th>비밀번호</th>
                    <td>
                        <input type="password" name="pwd"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th>비밀번호 확인</th>
                    <td>
                        <input type="password" name="pwd2"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th>연락처</th>
                    <td>
                        <select name="hp1" class="hp"  class="form-control">
                            <option value="010">010</option>
                            <option value="011">011</option>
                            <option value="017">017</option>
                        </select>
                    
                        <input type="text" name="hp2" class="hp" maxlength="4"  class="form-control">-
                        <input type="text" name="hp3" class="hp" maxlength="4"  class="form-control">                       
                    </td>
                </tr>
                <tr>
                    <th>우편번호</th>
                    <td>
                        <input type="text" name="post" id="post" class="post" class="form-control">
                        <button type="button" class="btn btn-success" onclick="" >우편번호 찾기</button>
                    </td>
                </tr>
                <tr>
                    <th>주소</th>
                    <td>
                        <input type="text" name="addr1"  id="addr1"  class="form-control"><br>
                        <input type="text" name="addr2" id="addr2"  class="form-control">
                    </td>
                </tr>
              <!--   <tr>
                    <td colspan="2">
                    
                        <label for="agree">
                            이용약관에 동의하십니까?
                            <input type="checkbox" name="agree" id="agree" style="width:2.5em">
                        </label>
                    
                    </td>
                </tr>
                
                <tr>
                    <td colspan="2">
                    
                        <iframe src="a" width="90%" height="120px"></iframe>
                    
                    </td>
                </tr> -->
                
                <tr>
                    <td colspan="2" align="center" style="text-align:center;height:28px">
                        <button type="button" class="btn btn-info" onclick="check()">회원가입</button>
                        <button type="reset" class="btn btn-warning">다시쓰기</button>
                    </td>
                </tr>
            </table>
        </form>
</div>        






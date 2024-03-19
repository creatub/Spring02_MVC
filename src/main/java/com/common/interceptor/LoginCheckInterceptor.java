package com.common.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.user.domain.MemberVO;

/*Interceptor
 *  - 컨트롤러가 실행되기 전에 사전 처리할 일이 있으면 
 *    스프링에서는 인터셉터에서 구현한다.
 *  - 구현 방법
 *  1. 인터셉터 구현
 *     [1] HandlerInterceptor인터페이스를 상속받는 방법
 *     [2] HandlerInterceptorAdapter 추상클래스를 상속받는 방법
 *      
 *  2. 인터셉터 등록 => servlet-context.xml에서 등록하고 매핑 정보를 설정
 *  <!-- Interceptor  ================================ -->
	<interceptors>
		<!-- 로그인 체크 인터셉터 -->
		<interceptor>
			<mapping path="/user/**"/>
			<mapping path="/admin/**"/>
			<beans:bean class="com.common.interceptor.LoginCheckInterceptor"/>
		</interceptor>
		<!-- 관리자 체크 인터셉터 -->
	</interceptors>
 *  
 *  */
public class LoginCheckInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession ses=request.getSession();
		MemberVO user=(MemberVO)ses.getAttribute("loginUser");
		if(user==null) {
			request.setAttribute("msg", "로그인해야 이용할 수 있습니다");
			String loc=request.getContextPath()+"/login";
			request.setAttribute("loc", loc);
			
			RequestDispatcher disp=request.getRequestDispatcher("/WEB-INF/views/message.jsp");
			disp.forward(request, response);
			
			return false;//false를 반환하면 컨트롤러로 넘어가지 못함
		}
		return true;//true를 반환하면 컨트롤러가 실행됨
	}

	//[1] preHandle(): 사전처리 Controller를 실행하기 전에 호출되는 메서드
	//[2] postHandle(): 사후처리 Controller를 실행한 후, 아직 뷰를 실행하기 전에 호출되는 메서드
	//[3] afterCompletion(): 사후처리 Controller를 실행하고 뷰를 실행한 후에 호출
	
	
}/////////////////////////////////////////

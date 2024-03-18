package com.common.exception;

import java.sql.SQLException;

import javax.inject.Inject;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.common.util.CommonUtil;

import lombok.extern.log4j.Log4j;
//servlet-context.xml에 com.common.exception 패키지를 component-scan에 대상 패키지로 등록해야 함
@ControllerAdvice
@Log4j
public class CommonExceptionAdvice {

	@Inject
	private CommonUtil util;
	
	@ExceptionHandler(NumberFormatException.class)
	public String exceptionHandler(Exception ex, Model m) {
		String msg = ex.getMessage();//예외 메시지
		
		return util.addMsgBack(m,msg);
	}//----------
	
	@ExceptionHandler(SQLException.class)
	public String sqlExceptionHandler(Exception ex, Model m) {
		m.addAttribute("error", ex.getMessage());
		return "shop/error";
	}
}

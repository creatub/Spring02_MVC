package com.common.exception;

public class NotUserException extends Exception {
	public NotUserException() {
		super("회원이 아닙니다 NotUserException");
	}
	public NotUserException(String msg) {
		super(msg);
	}
}////////////////////////////

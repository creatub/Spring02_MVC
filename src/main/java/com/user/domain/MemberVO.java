package com.user.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class MemberVO {

	private String userid;//PK
	private String name;
	private String pwd;
	private String hp1;
	private String hp2;
	private String hp3;
	
	private String post;
	private String addr1;
	private String addr2;
	private Date indate;
	private int mileage;
	private int mstate; // 0:일반회원, -1:정지회원. -2:탈퇴회원
	private String mstateStr;
	
	public String getAllHp() {
		return hp1+"-"+hp2+"-"+hp3;
	}//-----------------------
	public String getAddr() {
		return "["+post+"] "+addr1+" "+addr2;
	}//-----------------------
	public String getMstateStr() {
		if(mstate==0) return "일반회원";
		else if(mstate==-1) return "정지회원";
		else if(mstate==-2) return "탈퇴회원";
		else if(mstate==9) return "관리자";
		else return "";
	}//-----------------------
}//////////////////////////////////

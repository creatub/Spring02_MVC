package com.memo.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class MemoVO {

	private int no;
	private String name;
	private String msg;
	private Timestamp wdate;
}

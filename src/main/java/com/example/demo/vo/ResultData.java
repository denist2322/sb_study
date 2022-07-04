package com.example.demo.vo;

import lombok.Getter;

public class ResultData {
	@Getter
	private String resultCode;
	@Getter
	private String msg;
	@Getter
	private String data1Name;	
	@Getter
	private Object data1;
	
	private ResultData() {
		
	}
	
	public static ResultData from(String resultCode, String msg, String data1Name, Object data1) {
		ResultData rd = new ResultData();
		rd.resultCode = resultCode;
		rd.msg = msg;
		rd.data1Name = data1Name;
		rd.data1 = data1;
		
		
		return rd;
	}
	
	public static ResultData from(String resultCode, String msg) {
		
		return from(resultCode, msg, null, null);
	}
	
	public boolean isSuccess() {
		return resultCode.startsWith("S-");
	}
	
	public boolean isFail() {
		return isSuccess() == false;
	}
	
	public static ResultData from(ResultData oldRd, String data1Name, Object data1) {
		return from(oldRd.getResultCode(), oldRd.getMsg(), data1Name, data1);
	}
}

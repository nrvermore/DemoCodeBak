package com.labwinner.common;

import java.util.List;

public class ResultVo {
	
	//返回码
	private int errCode;
	
	//返回消息
	private String errMsg;
	
	//返回数据
	private List resultList;
	
	private Object resultData;
	
	private Object resultData1;

	public ResultVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResultVo(int errCode, String errMsg, List resultList,
			Object resultData, Object resultData1) {
		super();
		this.errCode = errCode;
		this.errMsg = errMsg;
		this.resultList = resultList;
		this.resultData = resultData;
		this.resultData1 = resultData1;
	}

	public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public Object getResultData() {
		return resultData;
	}

	public void setResultData(Object resultData) {
		this.resultData = resultData;
	}

	public Object getResultData1() {
		return resultData1;
	}

	public void setResultData1(Object resultData1) {
		this.resultData1 = resultData1;
	}
	
	
}

package com.labwinner.vo;

import java.util.List;

public class MessageVo {

	private String messageTitle;
	
	private String messageContent;
	
	private String firstContent;
	
	private String secondContent;
	
	private String thirdContent;
	
	private String fouthContent;
	
	private String remark;
	
	private Integer type;
	
	private List<String> filenames;
	
	private List<String> fileStrs;
	
	private List<Integer> idList;
	
	private Integer messageType;

	public MessageVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MessageVo(String messageTitle, String messageContent,
			String firstContent, String secondContent, String thirdContent,
			String fouthContent, String remark, List<String> filenames,
			List<String> fileStrs, List<Integer> idList, Integer messageType) {
		super();
		this.messageTitle = messageTitle;
		this.messageContent = messageContent;
		this.firstContent = firstContent;
		this.secondContent = secondContent;
		this.thirdContent = thirdContent;
		this.fouthContent = fouthContent;
		this.remark = remark;
		this.filenames = filenames;
		this.fileStrs = fileStrs;
		this.idList = idList;
		this.messageType = messageType;
	}

	public String getMessageTitle() {
		return messageTitle;
	}

	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public String getFirstContent() {
		return firstContent;
	}

	public void setFirstContent(String firstContent) {
		this.firstContent = firstContent;
	}

	public String getSecondContent() {
		return secondContent;
	}

	public void setSecondContent(String secondContent) {
		this.secondContent = secondContent;
	}

	public String getThirdContent() {
		return thirdContent;
	}

	public void setThirdContent(String thirdContent) {
		this.thirdContent = thirdContent;
	}

	public String getFouthContent() {
		return fouthContent;
	}

	public void setFouthContent(String fouthContent) {
		this.fouthContent = fouthContent;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<String> getFilenames() {
		return filenames;
	}

	public void setFilenames(List<String> filenames) {
		this.filenames = filenames;
	}

	public List<String> getFileStrs() {
		return fileStrs;
	}

	public void setFileStrs(List<String> fileStrs) {
		this.fileStrs = fileStrs;
	}

	public List<Integer> getIdList() {
		return idList;
	} 

	public void setIdList(List<Integer> idList) {
		this.idList = idList;
	}

	public Integer getMessageType() {
		return messageType;
	}

	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	
}

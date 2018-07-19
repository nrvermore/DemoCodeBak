package com.labwinner.vo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


import java.util.Map;

import com.labwinner.domain.KnowledgeClassifyPostil;
import com.labwinner.domain.SysUser;


public class KnowledgeVo {
	/**
	 * 知识附件
	 */
	private Integer knowledgeAccId;
	/**
	 * 日程表标题
	 */
	private String content;
	
	private String url;
	
	private String classfy;
	
	
	
	public Integer getKnowledgeAccId() {
		return knowledgeAccId;
	}
	public void setKnowledgeAccId(Integer knowledgeAccId) {
		this.knowledgeAccId = knowledgeAccId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getClassfy() {
		return classfy;
	}
	public void setClassfy(String classfy) {
		this.classfy = classfy;
	}
	
	
	
	


	

	
	


	
	
	
	
	
}

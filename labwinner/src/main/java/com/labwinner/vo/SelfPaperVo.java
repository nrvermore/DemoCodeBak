package com.labwinner.vo;

import java.util.List;


import java.util.Map;

import com.labwinner.domain.KnowledgeClassify;
import com.labwinner.domain.KnowledgeClassifyReacRela;
import com.labwinner.domain.KnowledgeProRela;
import com.labwinner.domain.SelfPaper;

public class SelfPaperVo {
	
	private SelfPaper selfPaper;
	private List<String> pdfStrs;
	private List<KnowledgeProRela> knowledgeProRela;
	private List<KnowledgeClassifyReacRela> knowledgeClassifyReac;
	private KnowledgeClassify knowledgeClassify;
	private String pdfName;
	private String  isEditOrNot;
	private String scureRankIds;
	private List<Map<String, Object>> userList;
	public SelfPaper getSelfPaper() {
		return selfPaper;
	}
	public void setSelfPaper(SelfPaper selfPaper) {
		this.selfPaper = selfPaper;
	}
	public List<String> getPdfStrs() {
		return pdfStrs;
	}
	public void setPdfStrs(List<String> pdfStrs) {
		this.pdfStrs = pdfStrs;
	}
	public List<KnowledgeProRela> getKnowledgeProRela() {
		return knowledgeProRela;
	}
	public void setKnowledgeProRela(List<KnowledgeProRela> knowledgeProRela) {
		this.knowledgeProRela = knowledgeProRela;
	}
	public List<KnowledgeClassifyReacRela> getKnowledgeClassifyReac() {
		return knowledgeClassifyReac;
	}
	public void setKnowledgeClassifyReac(
			List<KnowledgeClassifyReacRela> knowledgeClassifyReac) {
		this.knowledgeClassifyReac = knowledgeClassifyReac;
	}
	public KnowledgeClassify getKnowledgeClassify() {
		return knowledgeClassify;
	}
	public void setKnowledgeClassify(KnowledgeClassify knowledgeClassify) {
		this.knowledgeClassify = knowledgeClassify;
	}
	public String getPdfName() {
		return pdfName;
	}
	public void setPdfName(String pdfName) {
		this.pdfName = pdfName;
	}
	public String getIsEditOrNot() {
		return isEditOrNot;
	}
	public void setIsEditOrNot(String isEditOrNot) {
		this.isEditOrNot = isEditOrNot;
	}
	public String getScureRankIds() {
		return scureRankIds;
	}
	public void setScureRankIds(String scureRankIds) {
		this.scureRankIds = scureRankIds;
	}
	public List<Map<String, Object>> getUserList() {
		return userList;
	}
	public void setUserList(List<Map<String, Object>> userList) {
		this.userList = userList;
	}
	



}

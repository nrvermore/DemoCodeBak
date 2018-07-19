package com.labwinner.vo;

import java.util.List;

import com.labwinner.domain.KnowledgeClassify;
import com.labwinner.domain.KnowledgeClassifyReacRela;
import com.labwinner.domain.KnowledgeField;
import com.labwinner.domain.KnowledgeProRela;
import com.labwinner.domain.Patent;

public class PatentVo {
	
	private Patent patent;
	private List<String> pdfStrs;
	private List<KnowledgeProRela> knowledgeProRela;
	private List<KnowledgeClassifyReacRela> knowledgeClassifyReac;
	private KnowledgeClassify knowledgeClassify;
	private String pdfName;
	private String  isEditOrNot;
	private List<KnowledgeField> knowledgeFields;
	
	public Patent getPatent() {
		return patent;
	}
	public void setPatent(Patent patent) {
		this.patent = patent;
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
	public List<KnowledgeField> getKnowledgeFields() {
		return knowledgeFields;
	}
	public void setKnowledgeFields(List<KnowledgeField> knowledgeFields) {
		this.knowledgeFields = knowledgeFields;
	}
	
	



}

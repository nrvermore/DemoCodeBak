package com.labwinner.domain;

import java.util.HashSet;
import java.util.Set;

	/**
	 * KnowledgeClassify entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 知识分类
	 */
public class KnowledgeClassify implements java.io.Serializable {

	/**
	 * 知识分类主键
	 */
	private Integer knowledgeClassifyId;
	
	/**
	 * 知识分类名称
	 */
	private String knowledgeClassifyName;
	
	/**
	 * 知识附件表
	 */
	//private Set<KnowledgeAcc> knowledgeAccs = new HashSet<KnowledgeAcc>(0);
	
	/**
	 * 知识项目关系表
	 */
	//private Set<KnowledgeProRela> knowledgeProRelas = new HashSet<KnowledgeProRela>(0);
			
	
	/**
	 * 试验基本信息表
	 */
	//private Set<Reaction> reactions = new HashSet<Reaction>(0);

	// Constructors

	/** default constructor */
	public KnowledgeClassify() {
	}

	/** full constructor */
	public KnowledgeClassify(String knowledgeClassifyName,
			Set<KnowledgeAcc> knowledgeAccs,
			Set<KnowledgeProRela> knowledgeProRelas, Set<Reaction> reactions) {
		this.knowledgeClassifyName = knowledgeClassifyName;
		//this.knowledgeAccs = knowledgeAccs;
		//this.knowledgeProRelas = knowledgeProRelas;
		//this.reactions = reactions;
	}

	public Integer getKnowledgeClassifyId() {
		return knowledgeClassifyId;
	}

	public void setKnowledgeClassifyId(Integer knowledgeClassifyId) {
		this.knowledgeClassifyId = knowledgeClassifyId;
	}

	public String getKnowledgeClassifyName() {
		return knowledgeClassifyName;
	}

	public void setKnowledgeClassifyName(String knowledgeClassifyName) {
		this.knowledgeClassifyName = knowledgeClassifyName;
	}

//	public Set<KnowledgeAcc> getKnowledgeAccs() {
//		return knowledgeAccs;
//	}
//
//	public void setKnowledgeAccs(Set<KnowledgeAcc> knowledgeAccs) {
//		this.knowledgeAccs = knowledgeAccs;
//	}
//
//	public Set<KnowledgeProRela> getKnowledgeProRelas() {
//		return knowledgeProRelas;
//	}
//
//	public void setKnowledgeProRelas(Set<KnowledgeProRela> knowledgeProRelas) {
//		this.knowledgeProRelas = knowledgeProRelas;
//	}
//
//	public Set<Reaction> getReactions() {
//		return reactions;
//	}
//
//	public void setReactions(Set<Reaction> reactions) {
//		this.reactions = reactions;
//	}

	

}
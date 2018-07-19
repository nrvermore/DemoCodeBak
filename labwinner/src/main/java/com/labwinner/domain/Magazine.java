package com.labwinner.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description 杂志信息实体类
 * @author wangll
 * @version V1.0
 * @date 2017年6月27日 上午11:49:52
 */
public class Magazine implements java.io.Serializable {

	/**
	 * 杂志信息主键
	 */
	private Integer magazineId;
	
	/**
	 * 杂志中文名
	 */
	private String chName;
	
	/**
	 * 杂志英文名
	 */
	private String enName;
	
	/**
	 * issn
	 */
	private String issn;
	
	/**
	 * 语言
	 */
	private String language;
	
	
	private String eissn;
	
	
	private String nameAbbr;
	

	/**
	 * 创刊日期
	 */
	private String sponsor;

	private Set<JournalArticle> journalArticles = new HashSet<JournalArticle>(0);

	public Magazine() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Magazine(Integer magazineId, String chName, String enName,
			String issn, String language, String eissn, String nameAbbr,
			String sponsor, Set<JournalArticle> journalArticles) {
		super();
		this.magazineId = magazineId;
		this.chName = chName;
		this.enName = enName;
		this.issn = issn;
		this.language = language;
		this.eissn = eissn;
		this.nameAbbr = nameAbbr;
		this.sponsor = sponsor;
		this.journalArticles = journalArticles;
	}

	public Integer getMagazineId() {
		return magazineId;
	}

	public void setMagazineId(Integer magazineId) {
		this.magazineId = magazineId;
	}

	public String getChName() {
		return chName;
	}

	public void setChName(String chName) {
		this.chName = chName;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getIssn() {
		return issn;
	}

	public void setIssn(String issn) {
		this.issn = issn;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getEissn() {
		return eissn;
	}

	public void setEissn(String eissn) {
		this.eissn = eissn;
	}

	public String getNameAbbr() {
		return nameAbbr;
	}

	public void setNameAbbr(String nameAbbr) {
		this.nameAbbr = nameAbbr;
	}

	public String getSponsor() {
		return sponsor;
	}

	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	public Set<JournalArticle> getJournalArticles() {
		return journalArticles;
	}

	public void setJournalArticles(Set<JournalArticle> journalArticles) {
		this.journalArticles = journalArticles;
	}
	
	
	
}
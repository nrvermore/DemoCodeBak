package com.labwinner.domain;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description 专家实体类
 * @author liuhq
 * @version V1.0
 * @date 2017年5月17日 下午12:06:11
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public class Expert implements java.io.Serializable {

	// Fields

	/**
	 * 专家主键
	 */
	private Integer expertId;

	/**
	 * 中文名字
	 */
	private String expertNameCh;

	/**
	 * 英文名字
	 */
	private String expertNameEn;

	/**
	 * 国籍
	 */
	private String country;

	/**
	 * 民族
	 */
	private String nation;

	/**
	 * 生日
	 */
	private Date birthday;

	/**
	 * 出生地
	 */
	private String homeplace;

	/**
	 * 主要成就
	 */
	private String achievement;
	
	/**
	 * 履历
	 */
	private String record;

	/**
	 * 籍贯
	 */
	private String nativePlace;

	/**
	 * 工作单位
	 */
	private String company;
	
	/**
	 * 研究领域
	 */
	private String researchArea;
	/**
	 * 职称
	 */
	private String professionalTitle;

	/**
	 * 头像
	 */
	private String imgUrl;
	
	private String url;
	
	private Integer source;
	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	/**
	 * 论文数量
	 */
	private Integer count;

	public Expert() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Expert(Integer expertId, String expertNameCh, String expertNameEn,
			String country, String nation, Date birthday, String homeplace,
			String achievement, String record, String nativePlace,
			String company, String researchArea, String professionalTitle,
			String imgUrl, String url,Integer source,Integer count) {
		super();
		this.expertId = expertId;
		this.expertNameCh = expertNameCh;
		this.expertNameEn = expertNameEn;
		this.country = country;
		this.nation = nation;
		this.birthday = birthday;
		this.homeplace = homeplace;
		this.achievement = achievement;
		this.record = record;
		this.nativePlace = nativePlace;
		this.company = company;
		this.researchArea = researchArea;
		this.professionalTitle = professionalTitle;
		this.imgUrl = imgUrl;
		this.url = url;
		this.count=count;
		this.source=source;
	}

	public Integer getExpertId() {
		return expertId;
	}

	public void setExpertId(Integer expertId) {
		this.expertId = expertId;
	}

	public String getExpertNameCh() {
		return expertNameCh;
	}

	public void setExpertNameCh(String expertNameCh) {
		this.expertNameCh = expertNameCh;
	}

	public String getExpertNameEn() {
		return expertNameEn;
	}

	public void setExpertNameEn(String expertNameEn) {
		this.expertNameEn = expertNameEn;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getHomeplace() {
		return homeplace;
	}

	public void setHomeplace(String homeplace) {
		this.homeplace = homeplace;
	}

	public String getAchievement() {
		return achievement;
	}

	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}

	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getResearchArea() {
		return researchArea;
	}

	public void setResearchArea(String researchArea) {
		this.researchArea = researchArea;
	}

	public String getProfessionalTitle() {
		return professionalTitle;
	}

	public void setProfessionalTitle(String professionalTitle) {
		this.professionalTitle = professionalTitle;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	
	
	
	
}
package com.labwinner.domain;

import java.util.HashSet;
import java.util.Set;

	/**
	 * OrderState entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 热门检索参数表
	 */
public class HotSearch implements java.io.Serializable {

	/**
	 * 热门检索主键
	 */
	private Integer HotSearchId;
	
	/**
	 * 热门检索
	 */
	private String SearchName;

	/**
	 * 热门检索次数
	 */
	private Integer SearchNum;
	
	// Constructors

	/** default constructor */
	public HotSearch() {
	}

	/** full constructor */
	public HotSearch(String SearchName,Integer SearchNum) {
		this.SearchName = SearchName;
		this.SearchNum = SearchNum;
	}

	public Integer getHotSearchId() {
		return HotSearchId;
	}

	public void setHotSearchId(Integer hotSearchId) {
		HotSearchId = hotSearchId;
	}

	public String getSearchName() {
		return SearchName;
	}

	public void setSearchName(String searchName) {
		SearchName = searchName;
	}

	public Integer getSearchNum() {
		return SearchNum;
	}

	public void setSearchNum(Integer searchNum) {
		SearchNum = searchNum;
	}

	

}
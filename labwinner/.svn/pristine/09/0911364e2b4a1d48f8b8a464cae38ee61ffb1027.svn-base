package com.labwinner.domain;

import java.util.HashSet;
import java.util.Set;

	/**
	 * SecureRank entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 保密级别
	 */
public class SecureRank implements java.io.Serializable {

	/**
	 * 保密级别主键
	 */
	private Integer secureRankId;
	
	/**
	 * 保密级别
	 */
	private String secureRank;
	
	/**
	 * 库存信息表
	 */
	private Set<Inventory> inventories = new HashSet<Inventory>(0);

	// Constructors

	/** default constructor */
	public SecureRank() {
	}

	/** full constructor */
	public SecureRank(String secureRank, Set<Inventory> inventories) {
		this.secureRank = secureRank;
		this.inventories = inventories;
	}

	public Integer getSecureRankId() {
		return secureRankId;
	}

	public void setSecureRankId(Integer secureRankId) {
		this.secureRankId = secureRankId;
	}

	public String getSecureRank() {
		return secureRank;
	}

	public void setSecureRank(String secureRank) {
		this.secureRank = secureRank;
	}

	public Set<Inventory> getInventories() {
		return inventories;
	}

	public void setInventories(Set<Inventory> inventories) {
		this.inventories = inventories;
	}

	

}
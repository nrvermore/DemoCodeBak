package com.labwinner.vo;

import java.util.List;

import com.labwinner.domain.InventoryLocation;
import com.labwinner.domain.MaterialType;
import com.labwinner.domain.Measurement;
import com.labwinner.domain.PriceCurrency;
import com.labwinner.domain.SecureRank;
import com.labwinner.domain.Supplier;
import com.labwinner.domain.SysUser;

public class InventoryDataVo {

	private List<Measurement> gList;
	private List<Measurement> molList;

	private MaterialType types;

	private List<PriceCurrency> priceCurrencyList;

	private List<InventoryLocation> location;

	private List<Supplier> supplierList;

	private List<SysUser> userList;

	private List<SecureRank> rankList;

	public InventoryDataVo() {
	}

	public List<Measurement> getgList() {
		return gList;
	}

	public void setgList(List<Measurement> gList) {
		this.gList = gList;
	}

	public List<Measurement> getMolList() {
		return molList;
	}

	public void setMolList(List<Measurement> molList) {
		this.molList = molList;
	}

	public MaterialType getTypes() {
		return types;
	}

	public void setTypes(MaterialType types) {
		this.types = types;
	}

	public List<PriceCurrency> getPriceCurrencyList() {
		return priceCurrencyList;
	}

	public void setPriceCurrencyList(List<PriceCurrency> priceCurrencyList) {
		this.priceCurrencyList = priceCurrencyList;
	}



	public List<InventoryLocation> getLocation() {
		return location;
	}

	public void setLocation(List<InventoryLocation> location) {
		this.location = location;
	}

	public List<Supplier> getSupplierList() {
		return supplierList;
	}

	public void setSupplierList(List<Supplier> supplierList) {
		this.supplierList = supplierList;
	}

	public List<SysUser> getUserList() {
		return userList;
	}

	public void setUserList(List<SysUser> userList) {
		this.userList = userList;
	}

	public List<SecureRank> getRankList() {
		return rankList;
	}

	public void setRankList(List<SecureRank> rankList) {
		this.rankList = rankList;
	}

}

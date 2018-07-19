package com.labwinner.domain;

public class QrInfo implements java.io.Serializable{
	
	private Integer qrId;
	
	private String qrName;
	
	private String qrPath;
	
	private String qr;
	
	private Inventory inventory;
	
	private Inventories inventories;
	
	public QrInfo(){}

	public Integer getQrId() {
		return qrId;
	}

	public void setQrId(Integer qrId) {
		this.qrId = qrId;
	}

	

	public String getQrName() {
		return qrName;
	}

	public void setQrName(String qrName) {
		this.qrName = qrName;
	}

	public String getQrPath() {
		return qrPath;
	}

	public void setQrPath(String qrPath) {
		this.qrPath = qrPath;
	}

	public String getQr() {
		return qr;
	}

	public void setQr(String qr) {
		this.qr = qr;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public Inventories getInventories() {
		return inventories;
	}

	public void setInventories(Inventories inventories) {
		this.inventories = inventories;
	};
	
}

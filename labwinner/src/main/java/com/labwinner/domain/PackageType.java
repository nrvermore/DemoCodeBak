package com.labwinner.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 包装类型
 *
 * Created by bysocket on 07/02/2017.
 */
public class PackageType {

    /**
     * 包装类型主键
     */
    private  Integer packageTypeId;

    /**
     * 包装类型名称
     */
    private String packageTypeName;
    /**
     * 包装类型状态
     */
    private Integer flag;

    
    private List<ProductSummary> productSummaries = new ArrayList<ProductSummary>(0);
    
	public PackageType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PackageType(Integer packageTypeId, String packageTypeName,
			Integer flag, List<ProductSummary> productSummaries) {
		super();
		this.packageTypeId = packageTypeId;
		this.packageTypeName = packageTypeName;
		this.flag = flag;
		this.productSummaries = productSummaries;
	}

	public Integer getPackageTypeId() {
		return packageTypeId;
	}

	public void setPackageTypeId(Integer packageTypeId) {
		this.packageTypeId = packageTypeId;
	}

	public String getPackageTypeName() {
		return packageTypeName;
	}

	public void setPackageTypeName(String packageTypeName) {
		this.packageTypeName = packageTypeName;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public List<ProductSummary> getProductSummaries() {
		return productSummaries;
	}

	public void setProductSummaries(List<ProductSummary> productSummaries) {
		this.productSummaries = productSummaries;
	}

}

package com.labwinner.service;
import java.util.List;
import com.labwinner.domain.PackageType;
/**
 * 包装类型Service接口
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月18日 下午4:16:55
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public interface PackageTypeService {

	public void save(PackageType packageType) ;
    
	public void delete(Integer id) ;
     
	public PackageType getById(Integer id);
   
	public List<PackageType> getAll();
	
	public List<PackageType> getAllName();
	
	public List<PackageType> getFlagAll();
	
	public List<PackageType> getAllPageable(String keyword);

	public void update(PackageType packageType);
	
}
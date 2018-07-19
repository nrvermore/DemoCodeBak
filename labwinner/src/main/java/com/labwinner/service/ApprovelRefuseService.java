package com.labwinner.service;


import java.util.List;
import com.labwinner.domain.ApprovelRefuse;
/**
 * 试验设备预约Service接口
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月18日 下午4:16:55
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public interface ApprovelRefuseService {

	public void save(ApprovelRefuse approvelRefuse) ;
    
	public void delete(Integer id) ;
     
	public ApprovelRefuse getById(Integer id);
   
	public List<ApprovelRefuse> getAll();
	
	public List<ApprovelRefuse> getAllName();
	
	public List<ApprovelRefuse> getFlagAll();
	
	public List<ApprovelRefuse> getAllPageable(String keyword);

	public void update(ApprovelRefuse approvelRefuse);
	
}
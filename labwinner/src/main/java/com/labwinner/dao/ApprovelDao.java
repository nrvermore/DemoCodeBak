package com.labwinner.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.Approvel;
import com.labwinner.domain.ApprovelRefuse;

/**
 * @Description 附件Dao
 * @author liuhq
 * @version V1.0
 * @date 2017年6月9日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public interface ApprovelDao {

	/**
	 * @Description 获取所有对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月9日
	 */
	public List<Approvel> getAll(@Param("roleName") String roleName,@Param("userId")Integer userId);

	/**
	 * @Description 根据主键获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月9日
	 */
	Approvel getById(Integer id);

	/**
	 * @Description 保存对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月9日
	 */
	void save(Approvel approvel);

	/**
	 * @Description 更新对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月9日
	 */
	void update(Approvel approvel);

	/**
	 * @Description 删除对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月9日
	 */
	void delete(Integer id);

	public List<Approvel> getByOrderNumber(@Param("roleName")String roleName, @Param("userId") Integer userId,
			@Param("keyword") String keyword);

	public List<Map<String, Object>> getRefuseList();

	public List<Map<String, Object>> getOrderState();

	public List<Map<String, Object>> getAllForFirst(@Param("roleName") String roleName,@Param("userId")Integer userId);
	
	public List<Approvel> getScreenOrder();
	
	public Integer getAllOrders();

}

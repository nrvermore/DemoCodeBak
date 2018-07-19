package com.labwinner.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.Device;
import com.labwinner.domain.DeviceAppointment;

/**
 * 试验设备预约DAO接口
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月19日 上午9:32:45
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public interface DeviceAppointmentDao {
	
	
	/**
	 * 保存对象的方法
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @return 
	 * @date 2017年5月19日 上午9:34:40
	 */
	public void save(DeviceAppointment deviceAppointment);
	
	
	/**
	 * 删除对象的方法
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午9:38:07
	 */
	public void delete(java.lang.Integer id);
	
	/**
	 * 删除对象的方法
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午9:38:07
	 */
	public void deleteByReactionId(Integer id);
    
	
	/**
	 * 根据id查找对象的方法
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午9:38:49
	 */
	public DeviceAppointment getById(java.lang.Integer id);
    
	/**
	 * 根据字段查找对象的方法
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午9:38:49
	 */
	public List<String> findByDeviceName(String keyword);
    
	/**
	 * 获取所有预约状态的方法
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午9:38:49
	 */
	public List<DeviceAppointment> findByDeviceState();
	
	/**
	 *显示所有对象列表 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午9:39:33
	 */
	
	public List<DeviceAppointment> getAll(@Param("userId")Integer userId,@Param("roleName")String roleName);
	
	
	/**
	 *分页显示所有对象列表 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午9:39:33
	 */
	
	public List<DeviceAppointment> getAllPageable(@Param("userId")Integer userId,@Param("roleName")String roleName,@Param("keyword")String keyword);

	/**
	 * APP分页根据关键字显示预约中所有对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午4:20:54
	 */
	public List<DeviceAppointment> getAllPageableAppByKeyword(@Param("userId")Integer userId,@Param("roleName")String roleName,@Param("startCount")Integer startCount,@Param("endCount")Integer endCount,@Param("keyword")String keyword);
	/**
	 * APP分页显示所有预约中对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午4:20:54
	 */
	public List<DeviceAppointment> getAllPageableApp(@Param("userId")Integer userId,@Param("roleName")String roleName,@Param("startCount")Integer startCount,@Param("endCount")Integer endCount);

	
	/**
	 * APP分页根据关键字显示所有已经过期对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午4:20:54
	 */
	public List<DeviceAppointment> getExpirePageableAppByKeyword(@Param("userId")Integer userId,@Param("roleName")String roleName,@Param("startCount")Integer startCount,@Param("endCount")Integer endCount,@Param("keyword")String keyword);
	/**
	 * APP分页显示所有已经过期对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午4:20:54
	 */
	public List<DeviceAppointment> getExpirePageableApp(@Param("userId")Integer userId,@Param("roleName")String roleName,@Param("startCount")Integer startCount,@Param("endCount")Integer endCount);
	
	//获取预约中关键字查询total
	public List<DeviceAppointment> getTotalByKeyword(@Param("userId")Integer userId,@Param("keyword")String keyword);
	
	//获取已过期关键字查询total
	public List<DeviceAppointment> getExpireTotalByKeyword(@Param("userId")Integer userId,@Param("keyword")String keyword);
	
	
	//获取预约中total
	public List<DeviceAppointment> getTotal(@Param("userId")Integer userId);
	
	//获取已过期total
	public List<DeviceAppointment> getExpireTotal(@Param("userId")Integer userId);
	
	
	/**
	 * 判断同台设备被预约的结束时间最大值
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午4:20:54
	 */
	public String getByMaxDate(Integer id);
	
	/**
	 * 编辑预约的方法
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @return 
	 * @date 2017年5月19日 上午9:40:10
	 */
	public void update(DeviceAppointment deviceAppointment);
	
	/**
	 * 编辑预约状态的方法
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @return 
	 * @date 2017年5月19日 上午9:40:10
	 */
	public void updateState(DeviceAppointment deviceAppointment);
	
	/**
	  * @Description 判断设备在指定日期是否已经预约
	  * @author suhg
	  * @version V1.0
	  * @date 2017年5月19日 上午11:21:34
	  */
	public Integer isAppointment(Integer id, String date);
	
	/**
	  * @Description 判断当前用户在指定日期是否已经预约
	  * @author suhg
	  * @version V1.0
	  * @date 2017年5月19日 上午11:21:34
	  */
	public boolean haveAppointment(Integer id, Integer userId, String date);
	
	
	public Integer getAllAppointment();
	
	//获取已过期total
	public List<DeviceAppointment> getAllUse();
	
}
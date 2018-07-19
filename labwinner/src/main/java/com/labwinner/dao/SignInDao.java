package com.labwinner.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.SignIn;
/**
 * 试验设备预约DAO接口
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月18日 下午4:16:55
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public interface SignInDao {
	
	/**
	 * 保存对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午4:19:42
	 */
	public void save(SignIn signIn) ;
    
	
	/**
	 * 删除对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午4:20:05
	 */
	public void delete(java.lang.Integer id) ;
     
	
	/**
	 * 根据id查找对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午4:20:29
	 */
	public SignIn getById(Integer userId);
	
	
     /**
      * 显示所有对象
      * @Description TODO
      * @author suhg
      * @version V1.0
      * @date 2017年5月18日 下午4:20:54
      */
	public List<SignIn> getAll(Integer userId);
	
	/**
     * 显示最近一周所有对象
     * @Description TODO
     * @author xux
     * @version V1.0
     * @date 2017年5月18日 下午4:20:54
     */
	public List<SignIn> getWeekList(Integer userId);
	
	

	/**
     * 显示某一天所有签到对象
     * @Description TODO
     * @author xux
     * @version V1.0
     * @date 2017年5月18日 下午4:20:54
     */
	public List<SignIn> getAllByDay(String date);
	
	/**
     * 显示当天签到次数
     * @Description TODO
     * @author xux
     * @version V1.0
     * @date 2017年5月18日 下午4:20:54
     */
	public Integer getSignCount(Integer userId);
	
	
	/**
	 * 修改对象的方法
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午4:50:32
	 */
	public void update(SignIn signIn);


	public List<SignIn> getSignForFirt(@Param("roleName")String roleName,@Param("userId") Integer userId);


	public List<SignIn> getDayList();


	public List<SignIn> getGroupDayList();
	
	public List<SignIn> getExcelList(@Param("startDate")String startDate,@Param("endDate")String endDate);
	
}
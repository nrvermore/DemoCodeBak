package com.labwinner.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.DeviceVideo;
/**
 * 试验设备视频DAO接口
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月18日 下午4:16:55
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public interface DeviceVideoDao {
	
	/**
	 * 保存对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午4:19:42
	 */
	public void save(DeviceVideo deviceVideo) ;
	
	/**
	 * 删除对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午4:20:05
	 */
	public void delete(Integer id) ;
     
	
	/**
	 * 根据id查找对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午4:20:29
	 */
	public DeviceVideo getById(Integer id);
	
     /**
      * 显示所有对象
      * @Description TODO
      * @author suhg
      * @version V1.0
      * @date 2017年5月18日 下午4:20:54
      */
	public List<DeviceVideo> getAll();
	
	/**
	 * 根据设备id获取显示所有对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午4:20:54
	 */
	public List<DeviceVideo> getAllByDeviceId(Integer id);
	
	
	/**
	 * 分页显示所有对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午4:20:54
	 */
	public List<DeviceVideo> getAllPageableByKeyword(String keyword);
	/**
	 * APP分页根据关键字显示所有对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午4:20:54
	 */
	public List<DeviceVideo> getAllPageableAppByKeyword(@Param("startCount")Integer startCount,@Param("endCount")Integer endCount,@Param("keyword")String keyword);
	/**
	 * APP分页显示所有对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午4:20:54
	 */
	public List<DeviceVideo> getAllPageableApp(@Param("startCount")Integer startCount,@Param("endCount")Integer endCount);
	
	
	/**
	 * 修改对象的方法
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午4:50:32
	 */
	public void update(DeviceVideo deviceVideo);
}
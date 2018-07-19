package com.labwinner.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.Device;
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
public interface DeviceService {
	
	/**
	 * 保存对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午4:19:42
	 */
	public void save(Device device) ;
	
	/**
	 * 编辑对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午4:19:42
	 */
	public void updateQrName(Device device) ;
    
	
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
	public Device getById(Integer id);
	
	/**
	 * 根据字段查找对象的方法
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午9:38:49
	 */
	public List<String> findByDeviceName(String keyword);
     /**
      * 显示所有对象
      * @Description TODO
      * @author suhg
      * @version V1.0
      * @date 2017年5月18日 下午4:20:54
      */
	public List<Device> getAll();
	
	
	public List<Device> getAllDevices();
	
	//获取所有设备型号
	public List<Device> getAllModelName();
	
	public List<Device> getAllName();
	
	/**
	 * 分页显示所有对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午4:20:54
	 */
	public List<Device> getAllPageable(String keyword);
	
	/**
	 * APP分页更具关键字显示所有对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午4:20:54
	 */
	public List<Device> getAllPageableAppByKeyword(@Param("startCount")Integer startCount,@Param("endCount")Integer endCount,@Param("keyword")String keyword);

	/**
	 * APP分页显示所有对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午4:20:54
	 */
	public List<Device> getAllPageableApp(@Param("startCount")Integer startCount,@Param("endCount")Integer endCount);
	
	/**
	 * 获取修改日期
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @param date 
	 * @param date 
	 * @date 2017年5月18日 下午4:20:54
	 */
	
	/**
	 * 分页显示所有对象
	 * @Description TODO
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 下午4:20:54
	 */
	public List<Device> getByType(Integer typeId);
	
	public Device getByModifydate(Date date);
	
	/**
	 * 修改对象的方法
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午4:50:32
	 */
	public void update(Device device);


	public List<Device> getByLocationId(Integer id);
	
	public List<String> getBarCodes();
	
	public Device getByBarcode(String barcode);
	
	public List<Device> getByKeyword(String keyword);
	
}
package com.labwinner.service;

import java.util.List;

import com.labwinner.domain.Device;
import com.labwinner.domain.DevicePicture;

/**
 * 设备图片Service接口
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月19日 下午2:17:55
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */

public interface DevicePictureService{
	
	/**
	 * 保存对象的方法
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午2:20:59
	 */
	public void save(DevicePicture devicePicture);

    /**
     * 删除对象方法
     * @Description TODO
     * @author suhg
     * @version V1.0
     * @date 2017年5月19日 下午2:21:23
     */
	public void delete(Integer id,String fileName);

	/**
	 * 删除对象方法
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午2:21:23
	 */
	public void deleteByDeviceId(Integer id);
	
    /**
     * 根据id查找对象
     * @Description TODO
     * @author suhg
     * @version V1.0
     * @date 2017年5月19日 下午2:21:41
     */
	public List<String> getByPictureId(Integer id);
    
	
	/**
	 * 显示所有对象列表
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午2:22:13
	 */
	public List<DevicePicture> getAll();
     
	
	/**
      *修改对象 
      * @Description TODO
      * @author suhg
      * @version V1.0
      * @date 2017年5月19日 下午2:22:31
      */
	public void update(DevicePicture devicePicture);

	
}
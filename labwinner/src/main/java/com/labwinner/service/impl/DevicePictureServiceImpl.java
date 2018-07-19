package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.DevicePictureDao;
import com.labwinner.domain.Device;
import com.labwinner.domain.DevicePicture;
import com.labwinner.service.DevicePictureService;

/**
 * 设备图片Service
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月19日 下午2:56:20
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@Service
public class DevicePictureServiceImpl implements DevicePictureService{
	private static final Logger log = LoggerFactory
			.getLogger(DevicePictureServiceImpl.class);
	

	@Autowired
	private DevicePictureDao  devicePictureDao;
	
	
	/**
	 * 保存对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午3:17:52
	 */
	public void save(DevicePicture devicePicture) {
		log.debug("saving DevicePicture instance");
		try {
			devicePictureDao.save(devicePicture);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	
	/**
	 * 删除对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午3:17:16
	 */
	public void delete(Integer id,String fileName) {
		log.debug("deleting DevicePicture instance");
		try {
			devicePictureDao.delete(id, fileName);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
      
	
	/**
	 * 根据id查找对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午3:19:05
	 */
	@Override
	public List<String> getByPictureId(Integer id) {
		
		return devicePictureDao.getByPictureId(id);
	}

	/**
	 * 获取所有对象列表
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午3:19:29
	 */
	public List<DevicePicture> getAll() {
		log.debug("finding all DevicePicture instances");
		try {
			return devicePictureDao.getAll();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}

	}
   
	/**
	 * 修改对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午3:20:26
	 */
	public void update(DevicePicture devicePicture) {
		log.debug("merging DevicePicture instance");
		try {
			 devicePictureDao.update(devicePicture);
			log.debug("merge successful");
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}


	@Override
	public void deleteByDeviceId(Integer id) {
		log.debug("deleting DevicePicture instance");
		try {
			devicePictureDao.deleteByDeviceId(id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

}
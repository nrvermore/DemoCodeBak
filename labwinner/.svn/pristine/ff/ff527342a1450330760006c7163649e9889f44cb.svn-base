package com.labwinner.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.DeviceDao;
import com.labwinner.domain.Device;
import com.labwinner.domain.DeviceAppointment;
import com.labwinner.service.DeviceService;

/**
 * 设备Service
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月18日 下午4:55:46
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */

@Service
public class DeviceServiceImpl implements DeviceService{
	private static final Logger log = LoggerFactory
			.getLogger(DeviceServiceImpl.class);
    
	@Autowired
	private DeviceDao deviceDao;
    
    
	/**
	 * 保存对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午5:59:46
	 */
	public void save(Device device) {
		log.debug("saving Device instance");
		try {
			deviceDao.save(device);
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
	 * @date 2017年5月18日 下午6:00:30
	 */
	public void delete(Integer id) {
		log.debug("deleting Device instance");
		try {
			deviceDao.delete(id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
     
    
	/**
	 * 根据id号查找对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:00:58
	 */
	public Device getById(Integer id) {
		log.debug("getting Device instance with id: " + id);
		try {
			Device instance = deviceDao.getById(id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/**
	 * 根据字段查找对象的方法
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午9:38:49
	 */
	public List<String> findByDeviceName(String keyword) {
		
		List<String> device =deviceDao.findByDeviceName(keyword);
		
		return device;
	}
	/**
	 * 显示所有对象列表
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:02:18
	 */
	public List<Device> getAll() {
		log.debug("finding all Device instances");
		try {
			 return deviceDao.getAll();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
    
	
	/**
	 * 获取修改日期
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午4:20:54
	 */
	public Device getByModifydate(Date date){
		
		return deviceDao.getByModifydate();
		}
	
	
	
	/**
	 * 修改对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:02:43
	 */
	public void update(Device device) {
		log.debug("merging Device instance");
		try {
			 deviceDao.update(device);
			log.debug("merge successful");
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}


	@Override
	public List<Device> getAllPageable(String keyword) {
		log.debug("finding all Device instances");
		try {
			 return deviceDao.getAllPageable(keyword);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}


	@Override
	public List<Device> getByType(Integer typeId) {
		log.debug("finding all Device instances");
		try {
			 return deviceDao.getByType(typeId);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}


	@Override
	public List<Device> getByLocationId(Integer id) {
		log.debug("finding all Device instances");
		try {
			 return deviceDao.getByLocationId(id);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}


	@Override
	public List<Device> getAllName() {
		log.debug("finding all Device instances");
		try {
			 return deviceDao.getAllName();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}


	@Override
	public List<String> getBarCodes() {
		log.debug("finding all Device instances");
		try {
			 return deviceDao.getBarCodes();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}


	@Override
	public Device getByBarcode(String barcode) {
		log.debug("finding all Device instances");
		try {
			 return deviceDao.getByBarcode(barcode);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}


	@Override
	public List<Device> getAllPageableAppByKeyword(Integer startCount, Integer endCount,
			String keyword) {
		log.debug("finding all Device instances");
		try {
			 return deviceDao.getAllPageableAppByKeyword(startCount, endCount, keyword);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}


	@Override
	public List<Device> getAllPageableApp(Integer startCount, Integer endCount) {
		log.debug("finding all Device instances");
		try {
			 return deviceDao.getAllPageableApp(startCount, endCount);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}


	@Override
	public void updateQrName(Device device) {
		log.debug("merging Device instance");
		try {
			 deviceDao.updateQrName(device);
			log.debug("merge successful");
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}


	@Override
	public List<Device> getAllDevices() {
		log.debug("finding all Device instances");
		try {
			 return deviceDao.getAllDevices();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}


	@Override
	public List<Device> getByKeyword(String keyword) {
		log.debug("finding all Device instances");
		try {
			 return deviceDao.getByKeyword(keyword);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}


	@Override
	public List<Device> getAllModelName() {
		log.debug("finding all Device instances");
		try {
			 return deviceDao.getAllModelName();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	
}
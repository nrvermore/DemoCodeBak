package com.labwinner.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.labwinner.dao.DeviceAppointmentDao;
import com.labwinner.dao.DeviceDao;
import com.labwinner.dao.SysUserDao;
import com.labwinner.domain.Device;
import com.labwinner.domain.DeviceAppointment;
import com.labwinner.domain.DeviceAppointmentDate;
import com.labwinner.domain.SysUser;
import com.labwinner.service.DeviceAppointmentService;


/**
 * 试验设备预约Service
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月19日 上午10:51:34
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */


@Service
public class DeviceAppointmentServiceImpl implements DeviceAppointmentService{
	private static final Logger log = LoggerFactory
			.getLogger(DeviceAppointmentServiceImpl.class);
	
    
	@Autowired
	private DeviceAppointmentDao deviceAppointmentDao;
	
	@Autowired
	private DeviceDao deviceDao;
	
	@Autowired
	private SysUserDao sysUserDao;
			
			
	/**
	 * 保存对象的方法
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @return 
	 * @date 2017年5月19日 上午10:54:47
	 */
	public void save(DeviceAppointment deviceAppointment) {
		log.debug("saving DeviceAppointment instance");
		try {
			deviceAppointmentDao.save(deviceAppointment);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	
	/**
	 * 删除对象的方法
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午10:55:34
	 */
	public void delete(Integer id) {
		log.debug("deleting DeviceAppointment instance");
		try {
			deviceAppointmentDao.delete(id);
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
	 * @date 2017年5月19日 上午11:00:03
	 */
	public DeviceAppointment getById(Integer id) {
		log.debug("getting DeviceAppointment instance with id: " + id);
		try {
			DeviceAppointment instance = deviceAppointmentDao.getById(id);
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
		
		List<String> deviceAppointment =  deviceAppointmentDao.findByDeviceName(keyword);
		
		return deviceAppointment;
		
	}
	
	/**
	 * 获取所有对象列表
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午11:05:33
	 */
	public List<DeviceAppointment> getAll(Integer userId,String roleName) {
		log.debug("finding all DeviceAppointment instances");
		try {
		  return deviceAppointmentDao.getAll(userId, roleName);
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
	 * @return 
	 * @date 2017年5月19日 上午11:06:06
	 */
	public void update(DeviceAppointment deviceAppointment) {
		log.debug("merging DeviceAppointment instance");
		try {
			 deviceAppointmentDao.update(deviceAppointment);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * 判断同台设备被预约的结束时间最大值
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午4:20:54
	 */
	@Override
	public String getByMaxDate(Integer id) {
		  return deviceAppointmentDao.getByMaxDate(id);
		
    }
	
	/**
	  * @Description 根据设备主键获取可预约日期
	  * @author suhg
	  * @version V1.0
	  * @date 2017年5月19日 上午11:21:34
	  */
	public DeviceAppointmentDate getAppointmentDates(Integer id) {
		// 获取当前登录用户
		SysUser sysUser = new SysUser();
		String username = getPrincipal();
		List<SysUser> sysUserList = sysUserDao.getByUsername(username);
		if (sysUserList != null && sysUserList.size() > 0) {
			sysUser = sysUserList.get(0);
		}
		// 返回对象
        DeviceAppointmentDate deviceAppointmentDate = new DeviceAppointmentDate();
        // 可预约日期
        List<String> date = new ArrayList<String>();
        // 已经预约日期
        List<String> haveAppointmentList = new ArrayList<String>();
        // 获取当前日期
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy,MM,dd");
		Device device=deviceDao.getById(id);
		if(device!=null){
		Integer numberPeoplet = deviceDao.getById(id).getNumberPeoplet();
        while (numberPeoplet <= deviceAppointmentDao.isAppointment(id, sdf.format(calendar.getTime()))) {
        	calendar.add(Calendar.DAY_OF_MONTH, 1);
        	/*if (!deviceAppointmentDao.haveAppointment(id, sysUser.getUserId(), sdf.format(calendar.getTime()))) {
        		calendar.add(Calendar.DAY_OF_MONTH, 1);
        		date.add(sdf.format(calendar.getTime()));
            }*/
        }
        if (!deviceAppointmentDao.haveAppointment(id, sysUser.getUserId(), sdf.format(calendar.getTime()))) {
        	date.add(sdf.format(calendar.getTime()));
        }
        for(int i = 1; i < 15; i++) {
        	calendar.add(Calendar.DAY_OF_MONTH, 1);
        	if ((deviceAppointmentDao.isAppointment(id, sdf.format(calendar.getTime())) < numberPeoplet) 
        			&& !deviceAppointmentDao.haveAppointment(id, sysUser.getUserId(), sdf.format(calendar.getTime()))) {
        		date.add(sdf.format(calendar.getTime()));
        	} else {
        		haveAppointmentList.add(sdf.format(calendar.getTime()));
        	}
        }
        deviceAppointmentDate.setDate(date);
        deviceAppointmentDate.setHaveAppointmentList(haveAppointmentList);
		return deviceAppointmentDate;
		}
		return null;
    }

	/**
	 * @Description 获取当前登录用户
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月1日
	 */
	public String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

	

	@Override
	public List<DeviceAppointment> getAllPageable(Integer userId,String roleName,String keyword) {
		log.debug("finding all DeviceAppointment instances");
		try {
		  return deviceAppointmentDao.getAllPageable(userId, roleName, keyword);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}


	@Override
	public List<DeviceAppointment> getAllPageableAppByKeyword(Integer userId,
			String roleName, Integer startCount, Integer endCount,
			String keyword) {
		log.debug("getAllPageableAppByKeyword instances");
		try {
		  return deviceAppointmentDao.getAllPageableAppByKeyword(userId, roleName, startCount, endCount, keyword);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}


	@Override
	public List<DeviceAppointment> getAllPageableApp(Integer userId,
			String roleName, Integer startCount, Integer endCount) {
		log.debug("getAllPageableApp instances");
		try {
		  return deviceAppointmentDao.getAllPageableApp(userId, roleName, startCount, endCount);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}


	@Override
	public List<DeviceAppointment> findByDeviceState() {
		log.debug("getByKeyword instances");
		try {
		  return deviceAppointmentDao.findByDeviceState();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}


	@Override
	public void updateState(DeviceAppointment deviceAppointment) {
		log.debug("getByKeyword instances");
		try {
		   deviceAppointmentDao.updateState(deviceAppointment);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}


	@Override
	public List<DeviceAppointment> getExpirePageableAppByKeyword(
			Integer userId, String roleName, Integer startCount,
			Integer endCount, String keyword) {
		log.debug("getByKeyword instances");
		try {
			return  deviceAppointmentDao.getExpirePageableAppByKeyword(userId, roleName, startCount, endCount, keyword);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}


	@Override
	public List<DeviceAppointment> getExpirePageableApp(Integer userId,
			String roleName, Integer startCount, Integer endCount) {
		log.debug("getByKeyword instances");
		try {
			return  deviceAppointmentDao.getExpirePageableApp(userId, roleName, startCount, endCount);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}


	@Override
	public List<DeviceAppointment> getTotalByKeyword(Integer userId,
			String keyword) {
		log.debug("getByKeyword instances");
		try {
			return  deviceAppointmentDao.getTotalByKeyword(userId, keyword);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}


	@Override
	public List<DeviceAppointment> getExpireTotalByKeyword(Integer userId,
			String keyword) {
		log.debug("getByKeyword instances");
		try {
			return  deviceAppointmentDao.getExpireTotalByKeyword(userId, keyword);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}


	@Override
	public List<DeviceAppointment> getTotal(Integer userId) {
		log.debug("getByKeyword instances");
		try {
			return  deviceAppointmentDao.getTotal(userId);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}


	@Override
	public List<DeviceAppointment> getExpireTotal(Integer userId) {
		log.debug("getByKeyword instances");
		try {
			return  deviceAppointmentDao.getExpireTotal(userId);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}


	@Override
	public void deleteByReactionId(Integer id) {
		log.debug("getByKeyword instances");
		try {
			deviceAppointmentDao.deleteByReactionId(id);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}


	@Override
	public Integer getAllAppointment() {
		log.debug("getByKeyword instances");
		try {
			return  deviceAppointmentDao.getAllAppointment();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}


	@Override
	public List<DeviceAppointment> getAllUse() {
		log.debug("getByKeyword instances");
		try {
			return  deviceAppointmentDao.getAllUse();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
}
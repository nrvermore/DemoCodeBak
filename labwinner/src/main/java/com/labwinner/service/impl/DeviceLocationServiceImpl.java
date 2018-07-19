package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.DeviceLocationDao;
import com.labwinner.domain.DeviceLocation;
import com.labwinner.domain.InventoryLocation;
import com.labwinner.service.DeviceLocationService;
@Service
public class DeviceLocationServiceImpl implements DeviceLocationService{
	private static final Logger log = LoggerFactory
			.getLogger(DeviceLocationServiceImpl.class);
	
	   
	@Autowired
	private DeviceLocationDao deviceLocationDao;
	
	
	/**
	 * 保存对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午12:27:14
	 */
	public void save(DeviceLocation deviceLocation) {
		log.debug("saving DeviceLocation instance");
		try {
			deviceLocationDao.save(deviceLocation);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}


	@Override
	public List<DeviceLocation> getAll() {
		log.debug("finding all Measurement instances");
		try {
			List<DeviceLocation> LocaList= deviceLocationDao.getAll();
			return LocaList;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}


	@Override
	public DeviceLocation getDeviceLocation(Integer deviceLocaId) {
		log.debug("finding all Measurement instances");
		try {
			DeviceLocation location= deviceLocationDao.getDeviceLocation(deviceLocaId);
			return location;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}


	@Override
	public List<DeviceLocation> getDeviceLocations(Integer deviceLocaPid) {
		log.debug("finding all Measurement instances");
		try {
			List<DeviceLocation> LocaList= deviceLocationDao.getDeviceLocations(deviceLocaPid);
			return LocaList;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}


	@Override
	public DeviceLocation getTree(Integer deviceLocaId) {
		// 根据cid获取节点对象(SELECT * FROM tb_tree t WHERE t.cid=?)
		DeviceLocation node = deviceLocationDao.getDeviceLocation(deviceLocaId);
		// 查询cid下的所有子节点(SELECT * FROM tb_tree t WHERE t.pid=?)
		List<DeviceLocation> childTreeNodes = deviceLocationDao
				.getDeviceLocations(deviceLocaId);
		// 遍历子节点
		for (DeviceLocation child : childTreeNodes) {
			DeviceLocation n = getTree(child.getDeviceLocaId()); // 递归
			node.getChildren().add(n);
		}
		return node;
	}


	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		deviceLocationDao.delete(id);
	}


	@Override
	public void update(DeviceLocation deviceLocation) {
		deviceLocationDao.update(deviceLocation);
		
	}


	@Override
	public DeviceLocation getById(Integer id) {
		// TODO Auto-generated method stub
		return deviceLocationDao.getById(id);
	}


	@Override
	public List<DeviceLocation> getAllPageable(String keyword) {
		// TODO Auto-generated method stub
		return deviceLocationDao.getAllPageable(keyword);
	}


	@Override
	public List<DeviceLocation> getAllFirst() {
		log.debug("finding all Measurement instances");
		try {
			List<DeviceLocation> LocaList= deviceLocationDao.getAllFirst();
			return LocaList;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}


	@Override
	public List<DeviceLocation> getAllLocation(Integer id) {
		log.debug("finding all Measurement instances");
		try {
			List<DeviceLocation> LocaList= deviceLocationDao.getAllLocation(id);
			return LocaList;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
   

}
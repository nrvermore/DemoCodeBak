package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.MaterialTypeDao;
import com.labwinner.domain.DeviceLocation;
import com.labwinner.domain.MaterialType;
import com.labwinner.service.MaterialTypeService;

/**
 * A data access object (DAO) providing persistence and search support for
 * MaterialType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.labwinner.hibernate.MaterialType
 * @author MyEclipse Persistence Tools
 */

@Service
public class MaterialTypeServiceImpl implements MaterialTypeService{
	private static final Logger log = LoggerFactory
			.getLogger(MaterialTypeServiceImpl.class);
	
	@Autowired 
	private MaterialTypeDao materialTypeDao;
	
	public void save(MaterialType materialType) {
		log.debug("saving Measurement instance");
		try {
			materialTypeDao.save(materialType);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public List<MaterialType> getAll() {
		log.debug("finding all Measurement instances");
		try {
			List<MaterialType> materialTypeList= materialTypeDao.getAll();
			return materialTypeList;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public void delete(Integer id) {
		materialTypeDao.delete(id);
	}

	@Override
	public MaterialType getById(Integer id) {
		
		return materialTypeDao.getById(id);
	}

	@Override
	public void update(MaterialType materialType) {
		materialTypeDao.update(materialType);
	}

	@Override
	public MaterialType getMaterialType(Integer materialTypeCodeId) {
		log.debug("finding all Measurement instances");
		try {
			MaterialType materialType= materialTypeDao.getMaterialType(materialTypeCodeId);
			return materialType;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<MaterialType> getMaterialTypes(Integer materialCategoryId) {
		log.debug("finding all Measurement instances");
		try {
			List<MaterialType> materialTypes= materialTypeDao.getMaterialTypes(materialCategoryId);
			return materialTypes;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<MaterialType> getAllPageable(String keyword) {
		
		return materialTypeDao.getAllPageable(keyword);
	}

	@Override
	public MaterialType getTree(Integer materialTypeCodeId) {
		// 根据cid获取节点对象(SELECT * FROM tb_tree t WHERE t.cid=?)
		MaterialType node = materialTypeDao.getMaterialType(materialTypeCodeId);
		// 查询cid下的所有子节点(SELECT * FROM tb_tree t WHERE t.pid=?)
		List<MaterialType> childTreeNodes = materialTypeDao.getMaterialTypes(materialTypeCodeId);
		// 遍历子节点
		if(childTreeNodes!=null && childTreeNodes.size()>0){
			for (MaterialType child : childTreeNodes) {
				MaterialType n = getTree(child.getMaterialTypeCodeId()); // 递归
				node.getChildren().add(n);
			}
		}
		return node;
	}
}
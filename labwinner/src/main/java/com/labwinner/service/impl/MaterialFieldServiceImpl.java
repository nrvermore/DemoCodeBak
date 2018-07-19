package com.labwinner.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.ExpertDao;
import com.labwinner.dao.MaterialFieldDao;
import com.labwinner.domain.Expert;
import com.labwinner.domain.InventoryLocation;
import com.labwinner.domain.MaterialField;
import com.labwinner.service.MaterialFieldService;
@Service
public class MaterialFieldServiceImpl implements MaterialFieldService {
	@Autowired
	private MaterialFieldDao materialFieldDao;
	@Override
	public List<MaterialField> getAll() {
		try {
			List<MaterialField> materialField= materialFieldDao.getAll();
			return materialField;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	public MaterialField getTree(Integer cid) {
		// 根据cid获取节点对象(SELECT * FROM tb_tree t WHERE t.cid=?)
		MaterialField node = materialFieldDao.getLocation(cid);
				// 查询cid下的所有子节点(SELECT * FROM tb_tree t WHERE t.pid=?)
				List<MaterialField> childTreeNodes = materialFieldDao
						.getLocations(cid);
				// 遍历子节点
				if(childTreeNodes!=null && childTreeNodes.size()>0){
					for (MaterialField child : childTreeNodes) {
						MaterialField n = getTree(child.getCid()); // 递归
						node.getChildren().add(n);
					}
				}
				return node;
	}

	@Override
	public List<MaterialField> getAllFirst() {
		try {
			List<MaterialField> materialField= materialFieldDao.getAllFirst();
			return materialField;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	public MaterialField getByCid(Integer id) {
		try {
			MaterialField materialField= materialFieldDao.getByCid(id);
			return materialField;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	public List<MaterialField> getAllSamePid(Integer filedId) {
		try {
			List<MaterialField> materialField= materialFieldDao.getAllSamePid(filedId);
			return materialField;
		} catch (RuntimeException re) {
			throw re;
		}
	}

}

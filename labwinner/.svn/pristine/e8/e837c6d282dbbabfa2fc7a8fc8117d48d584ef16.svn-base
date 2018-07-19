package com.labwinner.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.ExpertDao;
import com.labwinner.dao.KnowledgeFieldDao;
import com.labwinner.dao.MaterialFieldDao;
import com.labwinner.domain.Expert;
import com.labwinner.domain.InventoryLocation;
import com.labwinner.domain.KnowledgeField;
import com.labwinner.domain.MaterialField;
import com.labwinner.service.KnowledgeFieldService;
import com.labwinner.service.MaterialFieldService;
@Service
public class KnowledgeFieldServiceImpl implements KnowledgeFieldService {
	@Autowired
	private KnowledgeFieldDao knowledgeFieldDao;

	@Override
	public List<KnowledgeField> getAll() {
		try {
			List<KnowledgeField> knowledgeFields= knowledgeFieldDao.getAll();
			return knowledgeFields;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	public List<KnowledgeField> getAllPageable(String keyword) {
		try {
			List<KnowledgeField> knowledgeFields= knowledgeFieldDao.getAllPageable(keyword);
			return knowledgeFields;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	public void save(KnowledgeField knowledgeField) {
		try {
			knowledgeFieldDao.save(knowledgeField);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	public void update(KnowledgeField knowledgeField) {
		try {
			knowledgeFieldDao.update(knowledgeField);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	public void delete(Integer id) {
		try {
			knowledgeFieldDao.delete(id);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	public KnowledgeField getById(int id) {
		try {
			KnowledgeField knowledgeField= knowledgeFieldDao.getById(id);
			return knowledgeField;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getDefaultImageList(String fieldImage) {
		try {
			List<Map<String, Object>> knowledgeField= knowledgeFieldDao.getDefaultImageList(fieldImage);
			return knowledgeField;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	public int getCountByKnowledgeField(Integer knowledgeFieldId) {
		try {
			int count= knowledgeFieldDao.getCountByKnowledgeField(knowledgeFieldId);
			return count;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	public int getCountByKnowledgeFieldForPatent(Integer knowledgeFieldId) {
		try {
			int count= knowledgeFieldDao.getCountByKnowledgeFieldForPatent(knowledgeFieldId);
			return count;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	

}

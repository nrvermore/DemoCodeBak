package com.labwinner.dao;

import java.util.List;
import com.labwinner.domain.DesignTechnologyRelation;
/**
 * 设计工艺关系DAO接口
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月18日 下午4:16:55
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public interface DesignTechnologyRelationDao {
 
	public void save(DesignTechnologyRelation designTechnologyRelation) ;

	public void delete(Integer id);
	
	public void deleteByTechnologyId(Integer id);
     
	public List<DesignTechnologyRelation> getById(Integer id);
      
	public List<DesignTechnologyRelation> getByTechnologyId(Integer id);
	
	public List<DesignTechnologyRelation> getAll();

	public List<DesignTechnologyRelation> getAllPageable(String keyword);

	public void update(DesignTechnologyRelation designTechnologyRelation);
}
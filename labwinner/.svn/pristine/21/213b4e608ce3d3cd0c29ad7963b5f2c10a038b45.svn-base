package com.labwinner.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.CommentEntity;

public interface CommentDao {
	
	public void save(CommentEntity commentEntity);
	
	public void update(CommentEntity commentEntity);
	
	public void delete(Integer id);
	
	public void deleteByModuleId(@Param("moduleId")Integer moduleId,@Param("typeId")Integer typeId);
	
	public List<CommentEntity> getByModuleId(@Param("moduleId")Integer moduleId,@Param("typeId")Integer typeId);

}

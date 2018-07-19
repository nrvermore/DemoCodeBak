package com.labwinner.dao;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.CommentLike;

public interface CommentLikeDao {

	public void save(CommentLike commentLike);
	
	public void delete(Integer id);
	
	public void deleteById(Integer id);
	
	public Integer getNum(Integer id);
	
	public CommentLike getByUser(@Param("userId")Integer userId,@Param("agencyId")Integer agencyId,@Param("commentId")Integer commentId);
}

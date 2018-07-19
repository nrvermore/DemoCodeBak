package com.labwinner.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.MarketAssist;

public interface MarketAssistDao {
	
	public void save(MarketAssist marketAssist);
	
	public void delete(Integer id);
	
	public void update(MarketAssist marketAssist);
	
	public MarketAssist getbyId(Integer id);
	
	public List<MarketAssist> getAll(@Param("startCount")Integer startCount,@Param("endCount")Integer endCount);
	
	public List<MarketAssist> getByKeywordId(@Param("id")Integer id,@Param("startCount")Integer startCount,@Param("endCount")Integer endCount);
	
	public List<MarketAssist> getUser(@Param("userId")Integer userId,@Param("agencyId")Integer agencyId,
			@Param("startCount")Integer startCount,@Param("endCount")Integer endCount);

}

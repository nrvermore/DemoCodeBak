package com.labwinner.dao;


import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.ReactionProcess;

public interface ReactionProcessDao {
	
	public void save (ReactionProcess reactionProcess);
	
	public void update(ReactionProcess reactionProcess);
	
	public void updateProcessStatus(@Param("date")Date date,@Param("process")Integer process,@Param("reactionId")Integer reactionId,@Param("statusId")Integer statusId );
	
	public void updateProcess(@Param("reactionProcess")String reactionProcess,@Param("id")Integer id);
	
	//修改试验步骤编号
	public void updateProcessNumber(ReactionProcess reactionProcess);
	
	//单个修改步骤
	public void updateOneProcess(ReactionProcess reactionProcess);
	
	
	public ReactionProcess getByProcessId(Integer id);
	
	public void delete(Integer id);
	
	public void deleteByReactionId(Integer id);
	
	
	public ReactionProcess getById(Integer id);
	
	public List<Integer> getByReactionId(Integer id);
	
	public List<ReactionProcess> getAll();

	public List<ReactionProcess> getProcessByReactionId(Integer id);
	
}

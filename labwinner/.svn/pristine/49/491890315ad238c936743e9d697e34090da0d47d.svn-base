package com.labwinner.dao;

import java.util.List;
import java.util.Map;

import org.apache.activemq.broker.UserIDBroker;
import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.Analytics;
import com.labwinner.domain.Reaction;
import com.labwinner.vo.HomePageReacVo;

public interface ReactionDao {
	
	public void save(Reaction reaction);
	
	public void update(Reaction reaction);
	
	public void unLockReaction(Reaction reaction);
	
	public void delete (Integer id);
	
	public Reaction getBasicReaction(Integer id);
	
	public Reaction getProcessByreactionId(Integer id);
	
	//APP根据实验ID获取该实验的试验步骤对象
	public Reaction getProcessByReactionId(Integer id);
	
	public Reaction getById(Integer id);
	
	public List<Reaction> getByKeyword(String keyword);
	
	public List<Reaction> getAll();
	
	public Integer getCount(Integer numberId);
	
	public List<Reaction> getByUserAll(Integer userId);
	
	public List<Reaction> getAppointmentByReactionDesignId(Integer id);
	
	public List<Reaction> getUserList(Integer userId);
	
	public List<Reaction> getUserListByKeyword(@Param("keyword")String keyword,@Param("userId")Integer userId);
	
	//根据登录人关键字查询获组实验步骤对象集合
	public List<Reaction> getProcessyByUserIdAndKeyword(@Param("keyword")String keyword,@Param("userId")Integer userId);
	
	//根据登录人关键字查询获组实验步骤对象集合
	public List<Reaction> getProcessyByUserIdNoKeyword(Integer userId);
	
	//团队负责人关键字查询获组实验步骤对象集合
	public List<Reaction> getProcessByKeyword(String keyword);
	
	//团队负责人获组实验步骤对象集合
	public List<Reaction> getProcessNoKeyword();
	
	//APP根据登录人关键字查询获单一实验步骤对象集合
	public List<Reaction> getPrivateProcessyByUserIdAndKeyword(@Param("keyword")String keyword,@Param("userId")Integer userId);
	
	//APP根据登录人获单一实验步骤对象集合
	public List<Reaction> getPrivateProcessyByUserIdNoKeyword(Integer userId);
	
	//APP团队负责人关键字查询获单一实验步骤对象集合
	public List<Reaction> getPrivateProcessByKeyword(String keyword);
	
	//APP团队负责人获单一实验步骤对象集合
	public List<Reaction> getPrivateProcessNoKeyword();
	
	public List<Map<String,Object>> getArticleById(Integer id);
	
	public void deleteRelation(@Param("reactionId")Integer reactionId,
			@Param("classfiyId")Integer classfiyId,@Param("knowledgeId")Integer knowledgeId);

	public List<HomePageReacVo> getByProId(@Param("proId")Integer proId,@Param("userId")Integer userId,@Param("roleName")String roleName,@Param("proRoleName")String proRoleName);
	
	public List<Reaction> getUserReactions(@Param("userId")Integer userId,@Param("pmUserId")Integer pmUserId,@Param("proId")Integer proId,@Param("roleName")String roleName);

	public List<Reaction> getUserListReportByKeyword(@Param("keyword")String keyword,
			@Param("userId")Integer userId);

	public List<Reaction> getReportByKeyword(String keyword);

	public List<Reaction> getReportUserList(Integer userId);

	public List<Reaction> getAllReport();
	
	public List<Reaction> getByDesignId(Integer id);

	public List<HomePageReacVo> getReactionByNumber(@Param("proId")Integer proId,@Param("userId")Integer userId,@Param("roleName")String roleName,
			@Param("proRoleName")String proRoleName, @Param("number")int number);
	
	public List<Reaction> getMyReportByKeyword(@Param("keyword")String keyword,
			@Param("userId")Integer userId);
	
	public List<Reaction> getMyReportUserList(Integer userId);
	
	public Reaction getByReactionId(Integer id);
	
	public List<Reaction> getFirstMyReportUserList(Integer userId);
	
	public List<Reaction> getFirstReportUserList(Integer userId);
	
	public List<Reaction> getFirstAllReport();
	
	public List<Reaction> getScreenAll();
	
}

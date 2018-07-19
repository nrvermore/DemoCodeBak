package com.labwinner.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.Message;

public interface MessageDao {

	public void save(Message msg);
	
	public void update(Message msg);
	
	public void updateState (Message msg);
	
	public void delete(Integer id);
	
	public void deleteMessage(@Param("id")Integer id,@Param("column")Integer column);
	
	public void updateMessageByDelete(@Param("id")Integer id,@Param("flag")Integer flag);
	
	public Message getById(Integer id);
	
	public Message getByReceiverId(@Param("id")Integer id,@Param("userId")Integer userId);
	
	public List<Message> getAll();
	
	public List<Message> getByKeyword(@Param("keyword")String keyword,@Param("userId")Integer userId,@Param("size")Integer size);
	
	public List<Message> getByKeywords(@Param("keyword")String keyword,@Param("userId")Integer userId);
	
//	public List<Message> getReceiversKeyword(@Param("typeId")Integer typeId,@Param("keyword")String keyword,@Param("userId")Integer userId,@Param("size")Integer size);

//	public List<Message> getSenderKeyword(@Param("typeId")Integer typeId,@Param("keyword")String keyword,@Param("userId")Integer userId,@Param("size")Integer size);
	
	public List<Message> getByType(Integer id);
	
	public List<Message> getReceivers(@Param("typeId")Integer typeId,@Param("userId")Integer userId,@Param("size")Integer size);
	
	public List<Message> getSenders(@Param("typeId")Integer typeId,@Param("userId")Integer userId,@Param("size")Integer size);
	
	public List<Message> getWebReceivers(@Param("typeId")Integer typeId,@Param("userId")Integer userId);
	
	public List<Message> getWebSenders(@Param("typeId")Integer typeId,@Param("userId")Integer userId);
	
	public List<Message> getReceiversByKeyword(@Param("typeId")Integer typeId,@Param("userId")Integer userId,@Param("keyword")String keyword);
	
	public List<Message> getSendersByKeyword(@Param("typeId")Integer typeId,@Param("userId")Integer userId,@Param("keyword")String keyword);
	
	public List<Message> getRecentContacter(Integer userId);
	
	public List<Message> getAppMessages(Integer userId);
	
	public List<Message> getAllByDay(String date);
	
	public List<Message> getAllByDayAndType();
	
	public List<Integer> getMySendDayPaper(Integer userId);
	
	public List<Integer> getWeekMySendDayPaper(Integer userId);
	
	//根据用户获取收到的日报
	public List<Message> getMessageByUserId(Integer userId);
	
	//根据用户获取发出的日报
	public List<Message> getMessageGiveByUserId(Integer userId);
	
	//根据用户获取收到的周报
	public List<Message> getMessageWeekByUserId(Integer userId);
	
	//根据用户获取发出的周报
	public List<Message> getMessageWeekGiveByUserId(Integer userId);
	
	public Integer getTotalUnread(@Param("userId")Integer userId,@Param("typeId")Integer typeId);
	
	public Integer getDailyOrWeekUnread(@Param("userId")Integer userId,@Param("detailId")Integer detailId);

	public List<Map<String, Object>> getUserMessage(@Param("type")int type, @Param("userId")Integer userId);

	public List<Map<String, Object>> getNoteMessage(@Param("type")int type, @Param("userId")Integer userId);

	public List<Map<String, Object>> getMessageList(@Param("msgDetailtypeId")Integer msgDetailtypeId,
			@Param("userId")Integer userId);

	public void updateList(@Param("msgDetailtypeId")Integer msgDetailtypeId, @Param("userId")Integer userId);

	public void updateReviceMessage(@Param("userId")Integer userId, @Param("id")Integer id);

	public void saveBase(Message message);

	public List<Map<String, Object>> getMarketMessage(@Param("type")int type, @Param("userId")Integer userId,@Param("agencyId")Integer agencyId);

	public List<Map<String, Object>> getMarketMessageList(
			@Param("msgDetailtypeId")Integer msgDetailtypeId, @Param("userId")Integer userId,@Param("agencyId") Integer agencyId);

	public void updateMarketList(@Param("msgDetailtypeId")Integer msgDetailtypeId, @Param("userId")Integer userId,@Param("agencyId")Integer agencyId);

	public void updateMarket(@Param("userId")Integer userId, @Param("id")Integer id,@Param("agencyId")Integer agencyId);

	public void updateMessageForDelete(@Param("userId")Integer userId, @Param("id")Integer id);

	public Map<String, Object> getAppoint(int bussId);

	public void deleteBase(Integer messageId);

	public int getMessageUnread(Integer userId);

	public int getBaseMessageUnread(@Param("userId")Integer userId, @Param("agencyId")Integer agencyId);

	public List<Map<String, Object>> getUserMessageList(
			@Param("msgDetailtypeId")Integer msgDetailtypeId, @Param("loginUserId")int loginUserId, @Param("userId")Integer userId);

	public int getUserMessageNum(@Param("detailTypeId")int detailTypeId, @Param("loginUserId")int loginUserId,
			@Param("userId")Integer userId);
	
	public List<Integer> getDayPaperByUser(@Param("userId")Integer userId,@Param("logInUserId")Integer logInUserId);
	
	public List<Integer> getWeekDayPaperByUser(@Param("userId")Integer userId,@Param("logInUserId")Integer logInUserId);
	
	public List<Message> getAllMessages(@Param("userId")Integer userId,@Param("logInUserId")Integer logInUserId);
	
}

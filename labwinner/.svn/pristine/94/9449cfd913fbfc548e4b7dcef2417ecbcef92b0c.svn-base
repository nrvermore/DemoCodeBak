package com.labwinner.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.Message;

public interface MessageService {

	public void save(Message msg);
	
	public void update(Message msg);
	
	public void updateState(Message msg);
	
	public void delete(Integer id);
	
	public void deleteMessage(@Param("id")Integer id,@Param("column")Integer column);
	
	public void updateMessageByDelete(Integer id,Integer flag);
	
	public Message getById(Integer id);
	
	public Message getByReceiverId(Integer id,Integer userId);
	
	public List<Message> getAll();
	
	public List<Integer> getMySendDayPaper(Integer userId);
	
	public List<Message> getAllByDay(String date);
	
	public List<Message> getAllByDayAndType();
	
	//根据用户获取收到的日报
	public List<Message> getMessageByUserId(Integer userId);
	
	//根据用户获取发出的日报
	public List<Message> getMessageGiveByUserId(Integer userId);
	
	//根据用户获取收到的周报
	public List<Message> getMessageWeekByUserId(Integer userId);
	
	//根据用户获取发出的周报
	public List<Message> getMessageWeekGiveByUserId(Integer userId);
	
	public List<Message> getByKeyword(String keyword,Integer userId,Integer size);
	
	public List<Message> getByKeywords(String keyword,Integer userId);
	
//	public List<Message> getReceiversKeyword(Integer typeId,String keyword,Integer userId,Integer size);
	
//	public List<Message> getSenderKeyword(Integer typeId,String keyword,Integer userId,Integer size);
	
	public List<Message> getByType(Integer id);
	
	public List<Message> getReceivers(Integer typeId,Integer userId,Integer size);
	
	public List<Message> getSenders(Integer typeId,Integer userId,Integer size);
	
	public List<Message> getWebReceivers(Integer typeId,Integer userId);
	
	public List<Message> getWebSenders(Integer typeId,Integer userId);
	
	public List<Message> getReceiversByKeyword(Integer typeId,Integer userId,String keyword);
	
	public List<Message> getSendersByKeyword(Integer typeId,Integer userId,String keyword);
	
	public List<Message> getRecentContacter(Integer userId);
	
	public List<Message> getAppMessages(Integer userId);
	
	public Integer getTotalUnread(Integer userId,Integer typeId);
	
	public Integer getDailyOrWeekUnread(Integer userId,Integer detailId);

	public List<Map<String, Object>> getUserMessage(int type, Integer userId);

	public List<Map<String, Object>> getNoteMessage(int type, Integer userId);

	public List<Map<String, Object>> getMessageList(Integer msgDetailtypeId,
			Integer userId);

	public void updateList(Integer msgDetailtypeId, Integer userId);

	public void updateReviceMessage(Integer userId, Integer id);

	public void saveBase(Message message);

	public List<Map<String, Object>> getMarketMessage(int type, Integer userId,Integer agencyId);

	public List<Map<String, Object>> getMarketMessageList(
			Integer msgDetailtypeId, Integer userId, Integer agencyId);

	public void updateMarketList(Integer msgDetailtypeId, Integer userId,Integer agencyId);

	public void updateMarket(Integer userId, Integer id,Integer agencyId);

	public void updateMessageForDelete(Integer id, Integer userId);

	public Map<String, Object> getAppoint(int bussId);

	public void deleteBase(Integer messageId);

	public int getMessageUnread(Integer userId);

	public int getBaseMessageUnread(Integer userId, Integer agencyId);

	public List<Map<String, Object>> getUserMessageList(
			Integer msgDetailtypeId, int loginUserId, Integer userId);

	public int getUserMessageNum(int detailTypeId, int loginUserId,
			Integer userId);

	public List<Integer> getDayPaperByUser(Integer userId,Integer logInUserId);
	
	public List<Message> getAllMessages(Integer userId,Integer logInUserId);
	
	public List<Integer> getWeekMySendDayPaper(Integer userId);
	
	public List<Integer> getWeekDayPaperByUser(@Param("userId")Integer userId,@Param("logInUserId")Integer logInUserId);
	
}

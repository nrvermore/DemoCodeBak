package com.labwinner.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.MessageRecipients;

public interface MessageRecipientsDao {

	public void save(MessageRecipients recipients);
	
	public void update(MessageRecipients recipients);
	
	public void updateMsgState(MessageRecipients recipients);
	
	public void delete(Integer id);
	
	public Integer getByMsgId(Integer id);
	
	public List<MessageRecipients> getAll();
	
	public void deleteByMsgId(Integer id);
	
	public void updateMessageByDelete(@Param("id")Integer id,@Param("userId")Integer userId,@Param("flag")Integer flag);

	public void saveBase(MessageRecipients messageRecipients);

	public List<MessageRecipients> getRecipientsByBussId(@Param("userId")Integer userId,
			@Param("id")Integer id, @Param("msgDetailtypeId")Integer msgDetailtypeId);

	public List<MessageRecipients> getRecipientsMarketAssist(@Param("userId")Integer userId,
			@Param("id")Integer id,@Param("agencyId")Integer agencyId);

	public void updateBase(MessageRecipients messageRecipient);

	public List<MessageRecipients> getByMeeasgeId(int messageId);
	
	public List<MessageRecipients> getByUserId(Integer userId);

	public void deleteBase(Integer msgRecipientsId);

	public List<MessageRecipients> getRecipientsByUserId(@Param("userId")Integer userId,
			@Param("id")Integer id, @Param("msgDetailtypeId")Integer msgDetailtypeId);

	public List<MessageRecipients> getRecipientsByUser(@Param("userId")Integer userId,
			@Param("id")Integer id, @Param("msgDetailtypeId")int msgDetailtypeId);
}

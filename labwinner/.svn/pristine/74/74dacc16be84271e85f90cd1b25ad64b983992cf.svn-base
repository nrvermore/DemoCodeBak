package com.labwinner.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.MessageRecipients;

public interface MessageRecipientsService {

	public void save(MessageRecipients recipients);
	
	public void update(MessageRecipients recipients);
	
	public void updateMsgState(MessageRecipients recipients);
	
	public void delete(Integer id);
	
	public Integer getByMsgId(Integer id);
	
	public List<MessageRecipients> getAll();
	
	public List<MessageRecipients> getByUserId(Integer userId);
	
	public void deleteByMsgId(Integer id);
	
	public void updateMessageByDelete(Integer id,Integer userId,Integer flag);

	public void saveBase(MessageRecipients messageRecipients);

	public List<MessageRecipients> getRecipientsByBussId(Integer userId,
			Integer id, Integer msgDetailtypeId);

	public List<MessageRecipients> getRecipientsMarketAssist(Integer userId,
			Integer id,Integer agencyId);

	public void updateBase(MessageRecipients messageRecipient);

	public List<MessageRecipients> getByMeeasgeId(int messageId);

	public void deleteBase(Integer msgRecipientsId);

	public List<MessageRecipients> getRecipientsByUserId(Integer userId,
			Integer id, int msgDetailtypeId);

	public List<MessageRecipients> getRecipientsByUser(Integer userId, Integer id,
			int msgDetailtypeId);
}

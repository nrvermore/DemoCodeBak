package com.labwinner.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.MessageDao;
import com.labwinner.domain.Message;
import com.labwinner.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService{

	
	private static final Logger log = LoggerFactory
			.getLogger(MessageServiceImpl.class);
	@Autowired
	private MessageDao messageDao;
	
	
	@Override
	public void save(Message msg) {
		log.debug("saving message instance");
		try {
			messageDao.save(msg);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void update(Message msg) {
		log.debug("saving message instance");
		try {
			messageDao.update(msg);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void delete(Integer id) {
		log.debug("saving message instance");
		try {
			messageDao.delete(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public Message getById(Integer id) {
		log.debug("saving message instance");
		try {
			return messageDao.getById(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Message> getAll() {
		log.debug("saving message instance");
		try {
			return messageDao.getAll();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}


	@Override
	public List<Message> getByType(Integer id) {
		log.debug("saving message instance");
		try {
			return messageDao.getByType(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	

	@Override
	public List<Message> getRecentContacter(Integer userId) {
		log.debug("saving message instance");
		try {
			return messageDao.getRecentContacter(userId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void updateState(Message msg) {
		log.debug("saving message instance");
		try {
			messageDao.updateState(msg);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public Message getByReceiverId(Integer id,Integer userId) {
		log.debug("saving message instance");
		try {
			return messageDao.getByReceiverId(id,userId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

//	@Override
//	public List<Message> getReceiversKeyword(Integer typeId, String keyword,
//			Integer userId, Integer size) {
//		log.debug("saving message instance");
//		try {
//			return messageDao.getReceiversKeyword(typeId,keyword,userId,size);
//		} catch (RuntimeException re) {
//			log.error("save failed", re);
//			throw re;
//		}
//	}
//
//	@Override
//	public List<Message> getSenderKeyword(Integer typeId, String keyword,
//			Integer userId, Integer size) {
//		log.debug("saving message instance");
//		try {
//			return messageDao.getSenderKeyword(typeId,keyword,userId,size);
//		} catch (RuntimeException re) {
//			log.error("save failed", re);
//			throw re;
//		}
//	}

	@Override
	public List<Message> getReceivers(Integer typeId, Integer userId,
			Integer size) {
		log.debug("saving message instance");
		try {
			return messageDao.getReceivers(typeId,userId,size);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Message> getSenders(Integer typeId, Integer userId, Integer size) {
		log.debug("saving message instance");
		try {
			return messageDao.getSenders(typeId,userId,size);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Message> getByKeyword(String keyword, Integer userId,
			Integer size) {
		log.debug("saving message instance");
		try {
			return messageDao.getByKeyword(keyword,userId,size);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Message> getReceiversByKeyword(Integer typeId, Integer userId,
			String keyword) {
		log.debug("saving message instance");
		try {
			return messageDao.getReceiversByKeyword(typeId,userId,keyword);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Message> getSendersByKeyword(Integer typeId, Integer userId,
			String keyword) {
		log.debug("saving message instance");
		try {
			return messageDao.getSendersByKeyword(typeId,userId,keyword);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Message> getByKeywords(String keyword, Integer userId) {
		log.debug("saving message instance");
		try {
			return messageDao.getByKeywords(keyword,userId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void deleteMessage(Integer id, Integer column) {
		log.debug("deleteMessage message instance");
		try {
			messageDao.deleteMessage(id,column);
			log.debug("deleteMessage successful");
		} catch (RuntimeException re) {
			log.error("deleteMessage failed", re);
			throw re;
		}
		
	}

	@Override
	public void updateMessageByDelete(Integer id, Integer flag) {
		log.debug("updateMessageByDelete message instance");
		try {
			messageDao.updateMessageByDelete(id,flag);
			log.debug("updateMessageByDelete successful");
		} catch (RuntimeException re) {
			log.error("updateMessageByDelete failed", re);
			throw re;
		}
		
	}

	@Override
	public List<Message> getAppMessages(Integer userId) {
		log.debug("saving message instance");
		try {
			return messageDao.getAppMessages(userId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public Integer getTotalUnread(Integer userId,Integer typeId) {
		log.debug("saving message instance");
		try {
			return messageDao.getTotalUnread(userId,typeId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Message> getWebReceivers(Integer typeId, Integer userId) {
		log.debug("saving message instance");
		try {
			return messageDao.getWebReceivers(typeId,userId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Message> getWebSenders(Integer typeId, Integer userId) {
		log.debug("saving message instance");
		try {
			return messageDao.getWebSenders(typeId,userId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getUserMessage(int type, Integer userId) {
		log.debug("saving message instance");
		try {
			return messageDao.getUserMessage(type,userId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getNoteMessage(int type, Integer userId) {
		try {
			return messageDao.getNoteMessage(type,userId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getMessageList(Integer msgDetailtypeId,
			Integer userId) {
		try {
			return messageDao.getMessageList(msgDetailtypeId,userId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void updateList(Integer msgDetailtypeId, Integer userId) {
		messageDao.updateList(msgDetailtypeId,userId);
	}

	@Override
	public void updateReviceMessage(Integer userId, Integer id) {
		messageDao.updateReviceMessage(userId,id);
	}

	@Override
	public void saveBase(Message message) {
		try {
			messageDao.saveBase(message);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getMarketMessage(int type, Integer userId,Integer agencyId) {
		try {
			return messageDao.getMarketMessage(type,userId,agencyId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getMarketMessageList(
			Integer msgDetailtypeId, Integer userId, Integer agencyId) {
		try {
			return messageDao.getMarketMessageList(msgDetailtypeId,userId,agencyId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void updateMarketList(Integer msgDetailtypeId, Integer userId,Integer agencyId) {
		messageDao.updateMarketList(msgDetailtypeId,userId,agencyId);
	}

	@Override
	public void updateMarket(Integer userId, Integer id,Integer agencyId) {
		// TODO Auto-generated method stub
		messageDao.updateMarket(userId,id,agencyId);
	}

	@Override
	public void updateMessageForDelete(Integer id, Integer userId) {
		// TODO Auto-generated method stub
		messageDao.updateMessageForDelete(userId,id);
	}

	@Override
	public Map<String, Object> getAppoint(int bussId) {
		try {
			return messageDao.getAppoint(bussId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void deleteBase(Integer messageId) {
		// TODO Auto-generated method stub
		messageDao.deleteBase(messageId);
	}

	@Override
	public int getMessageUnread(Integer userId) {
		log.debug("saving message instance");
		try {
			return messageDao.getMessageUnread(userId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public int getBaseMessageUnread(Integer userId, Integer agencyId) {
		log.debug("saving message instance");
		try {
			return messageDao.getBaseMessageUnread(userId,agencyId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getUserMessageList(
			Integer msgDetailtypeId, int loginUserId, Integer userId) {
		try {
			return messageDao.getUserMessageList(msgDetailtypeId,loginUserId,userId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public int getUserMessageNum(int detailTypeId, int loginUserId,
			Integer userId) {
		try {
			return messageDao.getUserMessageNum(detailTypeId,loginUserId,userId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Message> getAllByDay(String date) {
		log.debug("getAllByDay message instance");
		try {
			return messageDao.getAllByDay(date);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Integer> getDayPaperByUser(Integer userId,Integer logInUserId) {
		log.debug("getAllByDay message instance");
		try {
			return messageDao.getDayPaperByUser(userId,logInUserId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Message> getAllMessages(Integer userId, Integer logInUserId) {
		log.debug("getAllByDay message instance");
		try {
			return messageDao.getAllMessages(userId,logInUserId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Message> getAllByDayAndType() {
		log.debug("getAllByDayAndType message instance");
		try {
			return messageDao.getAllByDayAndType();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Message> getMessageByUserId(Integer userId) {
		log.debug("getMessageByUserId message instance");
		try {
			return messageDao.getMessageByUserId(userId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Message> getMessageGiveByUserId(Integer userId) {
		log.debug("getMessageByUserId message instance");
		try {
			return messageDao.getMessageGiveByUserId(userId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Message> getMessageWeekByUserId(Integer userId) {
		log.debug("getMessageWeekByUserId message instance");
		try {
			return messageDao.getMessageWeekByUserId(userId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Message> getMessageWeekGiveByUserId(Integer userId) {
		log.debug("getMessageWeekGiveByUserId message instance");
		try {
			return messageDao.getMessageWeekGiveByUserId(userId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Integer> getMySendDayPaper(Integer userId) {
		log.debug("getMySendDayPaper message instance");
		try {
			return messageDao.getMySendDayPaper(userId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}

}

	@Override
	public List<Integer> getWeekMySendDayPaper(Integer userId) {
		log.debug("getWeekMySendDayPaper message instance");
		try {
			return messageDao.getWeekMySendDayPaper(userId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Integer> getWeekDayPaperByUser(Integer userId,
			Integer logInUserId) {
		log.debug("getWeekDayPaperByUser message instance");
		try {
			return messageDao.getWeekDayPaperByUser(userId, logInUserId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public Integer getDailyOrWeekUnread(Integer userId, Integer detailId) {
		log.debug("getWeekDayPaperByUser message instance");
		try {
			return messageDao.getDailyOrWeekUnread(userId, detailId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	}

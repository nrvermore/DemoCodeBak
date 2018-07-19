package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.MessageRecipientsDao;
import com.labwinner.domain.MessageRecipients;
import com.labwinner.service.MessageRecipientsService;

@Service
public class MessageRecipientsServiceImpl implements MessageRecipientsService{

	private static final Logger log = LoggerFactory
			.getLogger(MessageRecipientsServiceImpl.class);
	@Autowired
	private MessageRecipientsDao messageRecipientsDao;
	
	@Override
	public void save(MessageRecipients recipients) {
		log.debug("saving recipients instance");
		try {
			messageRecipientsDao.save(recipients);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void update(MessageRecipients recipients) {
		log.debug("update recipients instance");
		try {
			messageRecipientsDao.update(recipients);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void delete(Integer id) {
		log.debug("update recipients instance");
		try {
			messageRecipientsDao.delete(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public List<MessageRecipients> getAll() {
		log.debug("update recipients instance");
		try {
			return messageRecipientsDao.getAll();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void deleteByMsgId(Integer id) {
		log.debug("update recipients instance");
		try {
			messageRecipientsDao.deleteByMsgId(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void updateMessageByDelete(Integer id, Integer userId,Integer flag) {
		log.debug("update recipients instance");
		try {
			messageRecipientsDao.updateMessageByDelete(id,userId,flag);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public Integer getByMsgId(Integer id) {
		log.debug("update recipients instance");
		try {
			return messageRecipientsDao.getByMsgId(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void saveBase(MessageRecipients messageRecipients) {
		try {
			messageRecipientsDao.saveBase(messageRecipients);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<MessageRecipients> getRecipientsByBussId(Integer userId,
			Integer id, Integer msgDetailtypeId) {
		try {
			return messageRecipientsDao.getRecipientsByBussId(userId,id,msgDetailtypeId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<MessageRecipients> getRecipientsMarketAssist(Integer userId,
			Integer id,Integer agencyId) {
		try {
			return messageRecipientsDao.getRecipientsMarketAssist(userId,id,agencyId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void updateBase(MessageRecipients messageRecipient) {
		log.debug("update recipients instance");
		try {
			messageRecipientsDao.updateBase(messageRecipient);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<MessageRecipients> getByMeeasgeId(int messageId) {
		try {
			return messageRecipientsDao.getByMeeasgeId(messageId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void deleteBase(Integer msgRecipientsId) {
		log.debug("update recipients instance");
		try {
			messageRecipientsDao.deleteBase(msgRecipientsId);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<MessageRecipients> getRecipientsByUserId(Integer userId,
			Integer id, int msgDetailtypeId) {
		try {
			return messageRecipientsDao.getRecipientsByUserId(userId,id,msgDetailtypeId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<MessageRecipients> getRecipientsByUser(Integer userId,
			Integer id, int msgDetailtypeId) {
		try {
			return messageRecipientsDao.getRecipientsByUser(userId,id,msgDetailtypeId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<MessageRecipients> getByUserId(Integer userId) {
		log.debug("getByUserId recipients instance");
		try {
			return messageRecipientsDao.getByUserId(userId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void updateMsgState(MessageRecipients recipients) {
		log.debug("updateMsgState recipients instance");
		try {
			messageRecipientsDao.updateMsgState(recipients);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

}

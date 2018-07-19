package com.labwinner.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.AssistCommentDao;
import com.labwinner.domain.AssistComment;
import com.labwinner.service.AssistCommentService;

@Service
public class AssistCommentServiceImpl implements AssistCommentService{

	private static final Logger log = LoggerFactory
			.getLogger(AssistCommentServiceImpl.class);
	
	@Autowired
	private AssistCommentDao assistCommentDao;
	
	private List<AssistComment> chirdren = new ArrayList<AssistComment>(0);;
	
	@Override
	public void save(AssistComment assistComment) {
		log.debug("saving marketAssist instance");
		try {
			assistCommentDao.save(assistComment);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void delete(Integer id) {
		log.debug("saving marketAssist instance");
		try {
			assistCommentDao.delete(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void deleteById(Integer id) {
		log.debug("saving marketAssist instance");
		try {
			assistCommentDao.deleteById(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public Integer getNumById(Integer id) {
		log.debug("saving marketAssist instance");
		try {
			return assistCommentDao.getNumById(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}

	}

	@Override
	public List<AssistComment> getByPid(Integer id) {
		log.debug("saving marketAssist instance");
		try {
			return assistCommentDao.getByPid(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public AssistComment getById(Integer id) {
		log.debug("saving marketAssist instance");
		try {
			return assistCommentDao.getById(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	/**
	 * 递归算法解析成树形结构
	 *
	 * @param cid
	 * @return
	 * @author jiqinlin
	 */
//	public AssistComment getTree(Integer id) {
//		// 根据cid获取节点对象(SELECT * FROM tb_tree t WHERE t.cid=?)
//		AssistComment assistComment = assistCommentDao.getById(id);
//		// 查询cid下的所有子节点(SELECT * FROM tb_tree t WHERE t.pid=?)
//		List<AssistComment> childTreeNodes = assistCommentDao
//				.getByPid(id);
//		// 遍历子节点
//		if(childTreeNodes!=null && childTreeNodes.size()>0){
//			for (AssistComment child : childTreeNodes) {
//				AssistComment n = getTree(child.getAssistCommentId()); // 递归
////				assistComment.getChildren().add(n);
//				childTreeNodes.add(n);
//			}
//		}
//		return assistComment;
//	}

	@Override
	public List<AssistComment> getFirstComments(Integer asistId) {
		log.debug("saving marketAssist instance");
		try {
			return assistCommentDao.getFirstComments(asistId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	
	public List<AssistComment> getTree(Integer id) {
		// 根据cid获取节点对象(SELECT * FROM tb_tree t WHERE t.cid=?)
		AssistComment assistComment = assistCommentDao.getById(id);
		// 查询cid下的所有子节点(SELECT * FROM tb_tree t WHERE t.pid=?)
		List<AssistComment> childTreeNodes = assistCommentDao
				.getByPid(id);
		// 遍历子节点
		if(childTreeNodes!=null && childTreeNodes.size()>0){
			for (AssistComment child : childTreeNodes) {
				chirdren.add(child);
				getTree(child.getAssistCommentId()); // 递归
//				assistComment.getChildren().add(n);
//				childTreeNodes.add(n);
			}
		}
		return chirdren;
	}

	@Override
	public List<AssistComment> getComments(Integer id) {
		log.debug("saving marketAssist instance");
		try {
			return assistCommentDao.getComments(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void deleteByFirstId(Integer id) {
		log.debug("saving marketAssist instance");
		try {
			assistCommentDao.deleteByFirstId(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public AssistComment getLastAssistComment(Integer userId, Integer agencyId) {
		log.debug("saving marketAssist instance");
		try {
			return assistCommentDao.getLastAssistComment(userId,agencyId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}


}

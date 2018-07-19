package com.labwinner.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.DesignDosageDao;
import com.labwinner.dao.ReactionDesignChemicalDao;
import com.labwinner.dao.ReactionDesignDao;
import com.labwinner.dao.ReactionDesignParameterDao;
import com.labwinner.dao.ReactionDesignProcessDao;
import com.labwinner.dao.ReactionRecordDao;
import com.labwinner.domain.ReactionDesign;
import com.labwinner.service.ReactionDesignService;

@Service
public class ReactionDesignServiceImpl implements ReactionDesignService {

	private static final Logger log = LoggerFactory
			.getLogger(ReactionDesignServiceImpl.class);

	@Autowired
	private ReactionDesignDao reactionDesignDao;

	@Autowired
	private ReactionDesignChemicalDao reactionDesignChemicalDao;

	@Autowired
	private ReactionDesignParameterDao reactionDesignParameterDao;

	@Autowired
	private ReactionDesignProcessDao reactionDesignProcessDao;

	@Autowired
	private DesignDosageDao designDosageDao;

	@Autowired
	private ReactionRecordDao reactionRecordDao;

	@Override
	public void save(ReactionDesign reactionDesign) {
		log.debug("saving ReactionDesign instance");
		try {
			reactionDesignDao.save(reactionDesign);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}

	}

	@Override
	public void update(ReactionDesign reactionDesign) {
		log.debug("update ReactionDesign instance");
		try {
			reactionDesignDao.update(reactionDesign);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}

	}

	@Override
	public void delete(Integer id) {
		log.debug("deleting ReactionDesign instance");
		try {
			reactionDesignDao.delete(id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}

	}

	@Override
	public ReactionDesign getById(Integer id) {
		log.debug("getById ReactionDesign instance");
		try {
			log.debug("getById successful");
			return reactionDesignDao.getById(id);

		} catch (RuntimeException re) {
			log.error("getById failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionDesign> getAll() {
		log.debug("get ReactionDesign instance");
		try {
			log.debug("getAll successful");
			return reactionDesignDao.getAll();

		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionDesign> getAllPageable(String keyword) {
		log.debug("update ReactionDesign instance");
		try {
			log.debug("getAll successful");
			List<ReactionDesign> reactionDesignList = reactionDesignDao
					.getAllPageable(keyword);

			return reactionDesignList;
		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public void updateExecute(ReactionDesign reactionDesign) {

		log.debug("update ReactionDesign instance");
		try {
			log.debug("getAll successful");
			reactionDesignDao.updateExecute(reactionDesign);
		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}

	}

	@Override
	public List<ReactionDesign> getUserList(Integer userId) {
		log.debug("update ReactionDesign instance");
		try {
			log.debug("getAll successful");
			return reactionDesignDao.getUserList(userId);
		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionDesign> getUserListByKeyword(String keyword, Integer userId) {
		log.debug("update ReactionDesign instance");
		try {
			log.debug("getAll successful");
			return reactionDesignDao.getUserListByKeyword(keyword, userId);

		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public Integer getCount(Integer numberId) {
		log.debug("update ReactionDesign instance");
		try {
			log.debug("getCount successful");
			return reactionDesignDao.getCount(numberId);

		} catch (RuntimeException re) {
			log.error("getCount failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionDesign> getAllDesigns(Integer page,Integer pageSize) {
		log.debug("update ReactionDesign instance");
		try {
			log.debug("getAll successful");
			return reactionDesignDao.getAllDesigns(page,pageSize);

		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionDesign> getUserDesigns(
			Integer userId,Integer page,Integer pageSize) {
		log.debug("update ReactionDesign instance");
		try {
			log.debug("getAll successful");
			return reactionDesignDao.getUserDesigns(userId,page,pageSize);

		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionDesign> getAllDesignsByKeyword(String keyword,Integer page,Integer pageSize) {
		log.debug("update ReactionDesign instance");
		try {
			log.debug("getAll successful");
			return reactionDesignDao.getAllDesignsByKeyword(keyword,page,pageSize);

		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionDesign> getUserDesignsByKeyword(Integer userId, String keyword,Integer page,Integer pageSize) {
		log.debug("update ReactionDesign instance");
		try {
			log.debug("getAll successful");
			return reactionDesignDao.getUserDesignsByKeyword(userId,keyword,page,pageSize);

		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public ReactionDesign getChemicals(Integer id) {
		log.debug("update ReactionDesign instance");
		try {
			log.debug("getAll successful");
			return reactionDesignDao.getChemicals(id);

		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public Integer getUserDesignsCount(Integer userId, String keyword) {
		log.debug("update ReactionDesign instance");
		try {
			log.debug("getAll successful");
			return reactionDesignDao.getUserDesignsCount(userId,keyword);

		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public Integer getAllDesignsCount(String keyword) {
		log.debug("update ReactionDesign instance");
		try {
			log.debug("getAll successful");
			return reactionDesignDao.getAllDesignsCount(keyword);

		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public Integer getUserCount(Integer userId) {
		log.debug("update ReactionDesign instance");
		try {
			log.debug("getAll successful");
			return reactionDesignDao.getUserCount(userId);

		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public Integer getAllCount() {
		log.debug("update ReactionDesign instance");
		try {
			log.debug("getAll successful");
			return reactionDesignDao.getAllCount();

		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionDesign> getAllDesignsList() {
		log.debug("update ReactionDesign instance");
		try {
			log.debug("getAll successful");
			return reactionDesignDao.getAllDesignsList();

		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionDesign> getUserDesignsList(Integer userId) {
		log.debug("update ReactionDesign instance");
		try {
			log.debug("getAll successful");
			return reactionDesignDao.getUserDesignsList(userId);

		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionDesign> getMyDesignsByKeyword(String keyword,
			Integer userId) {
		log.debug("update ReactionDesign instance");
		try {
			log.debug("getAll successful");
			return reactionDesignDao.getMyDesignsByKeyword(keyword,userId);

		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionDesign> getMyList(Integer userId) {
		log.debug("update ReactionDesign instance");
		try {
			log.debug("getAll successful");
			return reactionDesignDao.getMyList(userId);

		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public void saveNoProject(ReactionDesign reactionDesign) {
		log.debug("saveNoProject ReactionDesign instance");
		try {
			log.debug("saveNoProject successful");
			reactionDesignDao.saveNoProject(reactionDesign);
		} catch (RuntimeException re) {
			log.error("saveNoProject failed", re);
			throw re;
		}
	}

	@Override
	public void updateNoProject(ReactionDesign reactionDesign) {
		log.debug("updateNoProject ReactionDesign instance");
		try {
			log.debug("updateNoProject successful");
			reactionDesignDao.updateNoProject(reactionDesign);
		} catch (RuntimeException re) {
			log.error("updateNoProject failed", re);
			throw re;
		}
	}

	@Override
	public ReactionDesign getFindDeleteById(Integer id) {
		log.debug("getFindDeleteById ReactionDesign instance");
		try {
			log.debug("getFindDeleteById successful");
			return reactionDesignDao.getFindDeleteById(id);

		} catch (RuntimeException re) {
			log.error("getFindDeleteById failed", re);
			throw re;
		}
	}

	@Override
	public ReactionDesign getSolutionById(Integer id) {
		log.debug("getSolutionById ReactionDesign instance");
		try {
			log.debug("getSolutionById successful");
			return reactionDesignDao.getSolutionById(id);

		} catch (RuntimeException re) {
			log.error("getSolutionById failed", re);
			throw re;
		}
	}

	@Override
	public ReactionDesign getReactionRecordsById(Integer id) {
		log.debug("getReactionRecordsById ReactionDesign instance");
		try {
			log.debug("getReactionRecordsById successful");
			return reactionDesignDao.getReactionRecordsById(id);

		} catch (RuntimeException re) {
			log.error("getReactionRecordsById failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionDesign> getReactionGropNameByType(Integer type) {
		log.debug("get ReactionDesign instance");
		try {
			log.debug("getReactionGropNameByType successful");
			return reactionDesignDao.getReactionGropNameByType(type);

		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public ReactionDesign getDesignTechnologyRelationById(Integer id) {
		log.debug("getById ReactionDesign instance");
		try {
			log.debug("getById successful");
			return reactionDesignDao.getDesignTechnologyRelationById(id);

		} catch (RuntimeException re) {
			log.error("getById failed", re);
			throw re;
		}
	}

	@Override
	public ReactionDesign getReactionDesignById(Integer id) {
		log.debug("getById ReactionDesign instance");
		try {
			log.debug("getById successful");
			return reactionDesignDao.getReactionDesignById(id);

		} catch (RuntimeException re) {
			log.error("getById failed", re);
			throw re;
		}
	
	}

	@Override
	public List<ReactionDesign> getMySingleDesignList(Integer userId) {
		log.debug("getById ReactionDesign instance");
		try {
			log.debug("getById successful");
			return reactionDesignDao.getMySingleDesignList(userId);

		} catch (RuntimeException re) {
			log.error("getById failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionDesign> getAllSingleDesigns() {
		log.debug("getById ReactionDesign instance");
		try {
			log.debug("getById successful");
			return reactionDesignDao.getAllSingleDesigns();

		} catch (RuntimeException re) {
			log.error("getById failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionDesign> getTeamSingleDesigns(Integer userId) {
		log.debug("getById ReactionDesign instance");
		try {
			log.debug("getById successful");
			return reactionDesignDao.getTeamSingleDesigns(userId);

		} catch (RuntimeException re) {
			log.error("getById failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionDesign> getAppMyDesignsByKeyword(String keyword,
			Integer userId) {
		log.debug("update ReactionDesign instance");
		try {
			log.debug("getAll successful");
			return reactionDesignDao.getAppMyDesignsByKeyword(keyword,userId);

		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionDesign> getAppMyList(Integer userId) {
		log.debug("update ReactionDesign instance");
		try {
			log.debug("getAll successful");
			return reactionDesignDao.getAppMyList(userId);

		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionDesign> getAppMySingleDesignsByKeyword(String keyword,
			Integer userId,Integer appType) {
		log.debug("update ReactionDesign instance");
		try {
			log.debug("getAll successful");
			return reactionDesignDao.getAppMySingleDesignsByKeyword(keyword,userId,appType);

		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionDesign> getAppMySingleList(Integer userId,Integer appType) {
		log.debug("update ReactionDesign instance");
		try {
			log.debug("getAll successful");
			return reactionDesignDao.getAppMySingleList(userId,appType);

		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionDesign> getAppUserListByKeyword(String keyword,
			Integer userId) {
		log.debug("update ReactionDesign instance");
		try {
			log.debug("getAll successful");
			return reactionDesignDao.getAppUserListByKeyword(keyword,userId);

		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionDesign> getAppAllPageable(String keyword) {
		log.debug("update ReactionDesign instance");
		try {
			log.debug("getAll successful");
			return reactionDesignDao.getAppAllPageable(keyword);

		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionDesign> getAppUserList(Integer userId) {
		log.debug("update ReactionDesign instance");
		try {
			log.debug("getAll successful");
			return reactionDesignDao.getAppUserList(userId);

		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionDesign> getAppAll() {
		log.debug("update ReactionDesign instance");
		try {
			log.debug("getAll successful");
			return reactionDesignDao.getAppAll();

		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionDesign> getAppUserSingleByKeyword(String keyword,
			Integer userId,Integer appType) {
		log.debug("update ReactionDesign instance");
		try {
			log.debug("getAll successful");
			return reactionDesignDao.getAppUserSingleByKeyword(keyword,userId,appType);

		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionDesign> getAppAllSinglePageable(String keyword,Integer appType) {
		log.debug("update ReactionDesign instance");
		try {
			log.debug("getAll successful");
			return reactionDesignDao.getAppAllSinglePageable(keyword,appType);

		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionDesign> getAppUserSingleList(Integer userId,Integer appType) {
		log.debug("update ReactionDesign instance");
		try {
			log.debug("getAll successful");
			return reactionDesignDao.getAppUserSingleList(userId,appType);

		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionDesign> getAppAllSingle(Integer appType) {
		log.debug("update ReactionDesign instance");
		try {
			log.debug("getAll successful");
			return reactionDesignDao.getAppAllSingle(appType);

		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionDesign> getDesignTechnologyByUserId(Integer userId,String keyword) {
		log.debug("get ReactionDesign instance");
		try {
			log.debug("getAll successful");
			return reactionDesignDao.getDesignTechnologyByUserId(userId, keyword);

		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionDesign> getDesignTechnologyByUserIdNokeyword(Integer userId) {
		log.debug("get ReactionDesign instance");
		try {
			log.debug("getAll successful");
			return reactionDesignDao.getDesignTechnologyByUserIdNokeyword(userId);

		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public ReactionDesign getAppSingleDesignById(Integer id) {
		log.debug("get ReactionDesign instance");
		try {
			log.debug("getAll successful");
			return reactionDesignDao.getAppSingleDesignById(id);

		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionDesign> getTechnologyByUserIdAndKeyword(Integer userId,
			String keyword) {
		log.debug("get ReactionDesign instance");
		try {
			log.debug("getAll successful");
			return reactionDesignDao.getTechnologyByUserIdAndKeyword(userId, keyword);

		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionDesign> getTechnologyByUserId(Integer userId) {
		log.debug("get ReactionDesign instance");
		try {
			log.debug("getAll successful");
			return reactionDesignDao.getTechnologyByUserId(userId);

		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionDesign> getFirstTeamDesign() {
		log.debug("get ReactionDesign instance");
		try {
			log.debug("getAll successful");
			return reactionDesignDao.getFirstTeamDesign();

		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionDesign> getFirstTeamUserDesign(Integer userId) {
		log.debug("get ReactionDesign instance");
		try {
			log.debug("getAll successful");
			return reactionDesignDao.getFirstTeamUserDesign(userId);

		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionDesign> getFirstMyDesigns(Integer userId) {
		log.debug("get ReactionDesign instance");
		try {
			log.debug("getAll successful");
			return reactionDesignDao.getFirstMyDesigns(userId);

		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public List<String> getSingleNames() {
		log.debug("saving Inventory instance");
		try {
			log.debug("save successful");
			return reactionDesignDao.getSingleNames();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionDesign> getNoTechnologyAppMySingleDesignsByKeyword(
			String keyword, Integer userId, Integer appType) {
		log.debug("saving Inventory instance");
		try {
			log.debug("save successful");
			return reactionDesignDao.getNoTechnologyAppMySingleDesignsByKeyword(keyword, userId, appType);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionDesign> getNoTechnologyAppMySingleList(Integer userId,
			Integer appType) {
		log.debug("saving Inventory instance");
		try {
			log.debug("save successful");
			return reactionDesignDao.getNoTechnologyAppMySingleList(userId, appType);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionDesign> getDesignTechnologyNoUserIdBykeyword(
			String keyword) {
		log.debug("saving Inventory instance");
		try {
			log.debug("save successful");
			return reactionDesignDao.getDesignTechnologyNoUserIdBykeyword(keyword);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionDesign> getDesignTechnologyNoUserId() {
		log.debug("saving Inventory instance");
		try {
			log.debug("save successful");
			return reactionDesignDao.getDesignTechnologyNoUserId();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}



	@Override
	public List<ReactionDesign> getNoTechnologyAppMySingleListByNoUserId() {
		log.debug("saving Inventory instance");
		try {
			log.debug("save successful");
			return reactionDesignDao.getNoTechnologyAppMySingleListByNoUserId();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionDesign> getNoTechnologyAppMySingleDesignsByNoUserIdKeyword(
			String keyword, Integer appType) {
		log.debug("saving Inventory instance");
		try {
			log.debug("save successful");
			return reactionDesignDao.getNoTechnologyAppMySingleDesignsByNoUserIdKeyword(keyword, appType);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionDesign> getPrivateReactionByUserId(Integer userId) {
		log.debug("saving Inventory instance");
		try {
			log.debug("save successful");
			return reactionDesignDao.getPrivateReactionByUserId(userId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionDesign> getPrivateReaction() {
		log.debug("saving Inventory instance");
		try {
			log.debug("save successful");
			return reactionDesignDao.getPrivateReaction();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionDesign> getTeamReactionByUserId(Integer userId) {
		log.debug("saving Inventory instance");
		try {
			log.debug("save successful");
			return reactionDesignDao.getTeamReactionByUserId(userId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionDesign> getTeamReaction() {
		log.debug("saving Inventory instance");
		try {
			log.debug("save successful");
			return reactionDesignDao.getTeamReaction();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	
}

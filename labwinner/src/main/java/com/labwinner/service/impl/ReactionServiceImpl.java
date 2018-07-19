package com.labwinner.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.AnalyticalAttachmentDao;
import com.labwinner.dao.AnalyticsDao;
import com.labwinner.dao.AnalyticsDeviceDao;
import com.labwinner.dao.CommentDao;
import com.labwinner.dao.DeviceAppointmentDao;
import com.labwinner.dao.ExecuteChemicalDao;
import com.labwinner.dao.ExecuteChemicalGroupDao;
import com.labwinner.dao.ExecuteParameterDao;
import com.labwinner.dao.ExecuteSolutionDao;
import com.labwinner.dao.KnowledgeClassifyDao;
import com.labwinner.dao.KnowledgeReacRelaDao;
import com.labwinner.dao.NoteAssistantDao;
import com.labwinner.dao.NoteDao;
import com.labwinner.dao.ProjectBasicInfoDao;
import com.labwinner.dao.ProjectNumberDao;
import com.labwinner.dao.PrototypeDao;
import com.labwinner.dao.ReactionDao;
import com.labwinner.dao.ReactionDesignDao;
import com.labwinner.dao.ReactionDeviceDao;
import com.labwinner.dao.ReactionImageDao;
import com.labwinner.dao.ReactionProcessDao;
import com.labwinner.dao.ReactionRecordDao;
import com.labwinner.dao.ReactionStatusDao;
import com.labwinner.dao.ReactionTestDao;
import com.labwinner.dao.TeamAssistDao;
import com.labwinner.dao.TestAttachmentDao;
import com.labwinner.dao.TestDeviceDao;
import com.labwinner.domain.ExecuteChemicalGroup;
import com.labwinner.domain.Reaction;
import com.labwinner.domain.ReactionDesign;
import com.labwinner.domain.ReactionRecord;
import com.labwinner.service.ReactionService;
import com.labwinner.vo.HomePageReacVo;

@Service
public class ReactionServiceImpl implements ReactionService {

	private static final Logger log = LoggerFactory
			.getLogger(ReactionServiceImpl.class);
	@Autowired
	private ReactionDao reactionDao;

	@Autowired
	private AnalyticsDao analyticDao;

	@Autowired
	private AnalyticsDeviceDao analyticsDeviceDao;

	@Autowired
	private AnalyticalAttachmentDao analyticalAttDao;

	@Autowired
	private ReactionProcessDao reactionProcessDao;

	@Autowired
	private NoteDao noteDao;

	@Autowired
	ReactionImageDao reactionImageDao;

	@Autowired
	NoteAssistantDao noteAssistantDao;

	@Autowired
	private DeviceAppointmentDao deviceAppointmentDao;

	@Autowired
	KnowledgeReacRelaDao KnowledgeClassifyReacRelaDao;

	@Autowired
	KnowledgeClassifyDao knowledgeClassifyDao;

	@Autowired
	ProjectBasicInfoDao projectBasicInfoDao;

	@Autowired
	ReactionStatusDao reactionStatusDao;

	@Autowired
	ReactionRecordDao reactionRecordDao;

	@Autowired
	ReactionDesignDao reactionDesignDao;

	@Autowired
	ProjectNumberDao projectNumberDao;

	@Autowired
	ExecuteChemicalDao executeChemicalDao;
	
	@Autowired
	ExecuteChemicalGroupDao executeChemicalGroupDao;
	
	@Autowired
	ExecuteSolutionDao executeSolutionDao;

	@Autowired
	ExecuteParameterDao executeParemeterDao;

	@Autowired
	ReactionTestDao reactionTestDao;

	@Autowired
	TestDeviceDao testDeviceDao;

	@Autowired
	TestAttachmentDao testAttachementDao;
	
	@Autowired
	PrototypeDao prototypeDao;
	
	@Autowired
	TeamAssistDao teamAssistDao;
	
	@Autowired
	ReactionDeviceDao reactionDeviceDao;
	
	@Autowired
	CommentDao commentDao;


	@Override
	public void save(Reaction reaction) {
		log.debug("saving reaction instance");
		try {
			reactionDao.save(reaction);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}

	}

	@Override
	public void update(Reaction reaction) {
		log.debug("saving reaction instance");
		try {
			reactionDao.update(reaction);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}

	}

	@Override
	public void delete(Integer id) {
		log.debug("saving reaction instance");
		try {
				//TODO 批量删除
				// 删除实验测试
				List<Integer> ids = reactionTestDao.getTestIds(id);
				if(ids !=null && ids.size()>0){
					testDeviceDao.batchRemove(ids);
					testAttachementDao.batchRemove(ids);
				}
				reactionTestDao.deleteByReactionId(id);
	
				// 删除实验分析
				List<Integer> analyticIds = analyticDao.getAnalyticIds(id);
				if(analyticIds !=null && analyticIds.size()>0){
					analyticsDeviceDao.batchRemove(analyticIds);
					analyticalAttDao.batchRemove(analyticIds);
				}
				analyticDao.deleteByReactionId(id);
				
				//删除设备预约
				deviceAppointmentDao.deleteByReactionId(id);
				
				// 删除随手记
				List<Integer> noteIdList = noteDao.getByReactionIdList(id);
				if(noteIdList !=null && noteIdList.size()>0){
					reactionImageDao.batchRemove(noteIdList);
					noteAssistantDao.batchRemove(noteIdList);
				}
				noteDao.deleteByReactionId(id);
				
				List<Integer> processIdList = reactionProcessDao.getByReactionId(id);
				if(processIdList !=null && processIdList.size()>0){
					// 删除样品表
					prototypeDao.batchRemove(processIdList);
					
					for (Integer processId : processIdList) {
						// 删除执行原料表
						List<ExecuteChemicalGroup> executeChemicalGroups  = executeChemicalGroupDao.getByProcessId(processId);
						for (ExecuteChemicalGroup executeChemicalGroup : executeChemicalGroups) {
							executeChemicalDao.deleteByGroupId(executeChemicalGroup.getChemicalGroupId());
						}
						
						executeChemicalGroupDao.deleteByProcessId(processId);
						
						commentDao.deleteByModuleId(processId, 1);
					}
					
					
					// 删除执行溶液表
					executeSolutionDao.batchRemove(processIdList);
					// 删除试验设备表
					reactionDeviceDao.batchRemove(processIdList);
					// 删除执行参数表
					executeParemeterDao.batchRemove(processIdList);
					// 删除团队协助表
					teamAssistDao.batchRemove(processIdList);
				}
				// 删除实验记录
				reactionProcessDao.deleteByReactionId(id);
				// 删除实验
				reactionDao.delete(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}

	}

	@Override
	public Reaction getById(Integer id) {
		log.debug("saving reaction instance");
		try {
			log.debug("save successful");
			return reactionDao.getById(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Reaction> getByKeyword(String keyword) {
		log.debug("saving reaction instance");
		try {
			log.debug("save successful");
			return reactionDao.getByKeyword(keyword);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Reaction> getAll() {
		log.debug("saving reaction instance");
		try {
			log.debug("save successful");
			return reactionDao.getAll();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void createReaction(ReactionDesign reactionDesign) {

		List<ReactionRecord> recordList = new ArrayList<ReactionRecord>(
				reactionDesign.getReactionRecords());
		for (ReactionRecord record : recordList) {
			record.getReactionNum();
		}
	}

	@Override
	public List<HomePageReacVo> getByProId(Integer proId,Integer userId,String roleName,String proRoleName) {
		log.debug("saving reaction instance");
		try {
			log.debug("save successful");
			return reactionDao.getByProId(proId,userId,roleName,proRoleName);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getArticleById(Integer id) {
		log.debug("saving reaction instance");
		try {
			log.debug("save successful");
			return reactionDao.getArticleById(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void deleteRelation(Integer reactionId, Integer classfiyId,
			Integer knowledgeId) {
		log.debug("saving reaction instance");
		try {
			reactionDao.deleteRelation(reactionId,classfiyId,knowledgeId);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}
	@Override
	public List<Reaction> getByUserAll(Integer userId) {
		log.debug("saving reaction instance");
		try {
			log.debug("get successful");
			return reactionDao.getByUserAll(userId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Reaction> getUserList(Integer userId) {
		log.debug("saving reaction instance");
		try {
			log.debug("get successful");
			return reactionDao.getUserList(userId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Reaction> getUserListByKeyword(String keyword, Integer userId) {
		log.debug("saving reaction instance");
		try {
			log.debug("get successful");
			return reactionDao.getUserListByKeyword(keyword,userId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Reaction> getUserReactions(Integer userId,Integer pmUserId,Integer proId,
			String roleName) {
		log.debug("saving reaction instance");
		try {
			log.debug("get successful");
			return reactionDao.getUserReactions(userId,pmUserId,proId,roleName);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public Integer getCount(Integer numberId) {
		log.debug("saving reaction instance");
		try {
			log.debug("get successful");
			return reactionDao.getCount(numberId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void unLockReaction(Reaction reaction) {
		log.debug("saving reaction instance");
		try {
			reactionDao.unLockReaction(reaction);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public List<Reaction> getUserListReportByKeyword(String keyword,
			Integer userId) {
		log.debug("saving reaction instance");
		try {
			log.debug("get successful");
			return reactionDao.getUserListReportByKeyword(keyword,userId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Reaction> getReportByKeyword(String keyword) {
		log.debug("saving reaction instance");
		try {
			log.debug("get successful");
			return reactionDao.getReportByKeyword(keyword);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Reaction> getReportUserList(Integer userId) {
		log.debug("saving reaction instance");
		try {
			log.debug("get successful");
			return reactionDao.getReportUserList(userId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Reaction> getAllReport() {
		log.debug("saving reaction instance");
		try {
			log.debug("get successful");
			return reactionDao.getAllReport();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public Reaction getBasicReaction(Integer id) {
		log.debug("saving reaction instance");
		try {
			log.debug("save successful");
			return reactionDao.getBasicReaction(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Reaction> getByDesignId(Integer id) {
		log.debug("saving reaction instance");
		try {
			log.debug("get successful");
			return reactionDao.getByDesignId(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<HomePageReacVo> getReactionByNumber(Integer proId, int userId,
			String roleName, String proRoleName, int number) {
		log.debug("saving reaction instance");
		try {
			log.debug("get successful");
			return reactionDao.getReactionByNumber(proId,userId,roleName,proRoleName,number);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Reaction> getMyReportByKeyword(String keyword, Integer userId) {
		log.debug("saving reaction instance");
		try {
			log.debug("get successful");
			return reactionDao.getMyReportByKeyword(keyword,userId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Reaction> getMyReportUserList(Integer userId) {
		log.debug("saving reaction instance");
		try {
			log.debug("get successful");
			return reactionDao.getMyReportUserList(userId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public Reaction getByReactionId(Integer id) {
		log.debug("saving reaction instance");
		try {
			log.debug("get successful");
			return reactionDao.getByReactionId(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Reaction> getAppointmentByReactionDesignId(Integer id) {
		log.debug("saving reaction instance");
		try {
			log.debug("save successful");
			return reactionDao.getAppointmentByReactionDesignId(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public Reaction getProcessByreactionId(Integer id) {
		log.debug("saving reaction instance");
		try {
			log.debug("save successful");
			return reactionDao.getProcessByreactionId(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Reaction> getProcessyByUserIdAndKeyword(String keyword,
			Integer userId) {
		log.debug("saving reaction instance");
		try {
			log.debug("save successful");
			return reactionDao.getProcessyByUserIdAndKeyword(keyword, userId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Reaction> getProcessyByUserIdNoKeyword(Integer userId) {
		log.debug("saving reaction instance");
		try {
			log.debug("save successful");
			return reactionDao.getProcessyByUserIdNoKeyword(userId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Reaction> getProcessByKeyword(String keyword) {
		log.debug("saving reaction instance");
		try {
			log.debug("save successful");
			return reactionDao.getProcessByKeyword(keyword);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Reaction> getProcessNoKeyword() {
		log.debug("saving reaction instance");
		try {
			log.debug("save successful");
			return reactionDao.getProcessNoKeyword();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Reaction> getPrivateProcessyByUserIdAndKeyword(String keyword,
			Integer userId) {
		log.debug("saving reaction instance");
		try {
			log.debug("save successful");
			return reactionDao.getPrivateProcessyByUserIdAndKeyword(keyword, userId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Reaction> getPrivateProcessyByUserIdNoKeyword(Integer userId) {
		log.debug("saving reaction instance");
		try {
			log.debug("save successful");
			return reactionDao.getPrivateProcessyByUserIdNoKeyword(userId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Reaction> getPrivateProcessByKeyword(String keyword) {
		log.debug("saving reaction instance");
		try {
			log.debug("save successful");
			return reactionDao.getPrivateProcessByKeyword(keyword);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Reaction> getPrivateProcessNoKeyword() {
		log.debug("get reaction instance");
		try {
			log.debug("save successful");
			return reactionDao.getPrivateProcessNoKeyword();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public Reaction getProcessByReactionId(Integer id) {
		log.debug("get reaction instance");
		try {
			log.debug("save successful");
			return reactionDao.getProcessByReactionId(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Reaction> getFirstMyReportUserList(Integer userId) {
		log.debug("get reaction instance");
		try {
			log.debug("save successful");
			return reactionDao.getFirstMyReportUserList(userId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Reaction> getFirstReportUserList(Integer userId) {
		log.debug("get reaction instance");
		try {
			log.debug("save successful");
			return reactionDao.getFirstReportUserList(userId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Reaction> getFirstAllReport() {
		log.debug("get reaction instance");
		try {
			log.debug("save successful");
			return reactionDao.getFirstAllReport();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Reaction> getScreenAll() {
		log.debug("get reaction instance");
		try {
			log.debug("save successful");
			return reactionDao.getScreenAll();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	
}

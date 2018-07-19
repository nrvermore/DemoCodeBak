package com.labwinner.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.JournalArticleDao;
import com.labwinner.domain.JournalArticle;
import com.labwinner.service.JournalArticleService;

@Service
public class JournalArticleServiceImpl implements JournalArticleService{

	private static final Logger log = LoggerFactory
			.getLogger(JournalArticleServiceImpl.class);
	
	@Autowired
	private JournalArticleDao journalArticleDao;
	
	@Override
	public void save(JournalArticle journalArticle) {
		log.debug("saving JournalArticle instance");
		try {
			journalArticleDao.save(journalArticle);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void update(JournalArticle journalArticle) {
		log.debug("update JournalArticle instance");
		try {
			journalArticleDao.update(journalArticle);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		
	}

	@Override
	public void delete(Integer id) {
		log.debug("deleting JournalArticle instance");
		try {
			journalArticleDao.delete(id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		
	}

	@Override
	public JournalArticle getById(Integer id) {
		log.debug("getById JournalArticle instance");
		try {
			log.debug("getById successful");
			return journalArticleDao.getById(id);
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<JournalArticle> getByName(Integer id,String keyword,String roleName,Integer angency) {
		log.debug("update JournalArticle instance");
		try {
			log.debug("getById successful");
			List<JournalArticle> journalArticles =journalArticleDao.getByName(id,keyword,roleName,angency);
			return  journalArticles;
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<JournalArticle> getAll(Integer id,String roleName,Integer angency) {
		log.debug("update JournalArticle instance");
		try {
			log.debug("getById successful");
			List<JournalArticle> journalArticles =journalArticleDao.getAll(id,roleName,angency);
			return  journalArticles;
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public void saveUser(Integer id, List<String> userId) {
		log.debug("saving journalUser instance");
		try {
			journalArticleDao.saveUser(id,userId);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<JournalArticle> saveByBatch(List<Map> lsit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean getResult(String issn, String title, String author,
			String writeDate, Integer userId, Integer secure) {
		try {
			List<JournalArticle> journalArticle=journalArticleDao.getResult(issn,title,author,userId,writeDate,secure);
			if(journalArticle.size()>0){
				return false;
			}else{
				return true;
			}
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getKnowledge(String roleName, Integer userId) {
		List<Map<String, Object>> listKnowlwdge=new ArrayList<Map<String,Object>>();
		try {
			listKnowlwdge=journalArticleDao.getKnowledge(roleName,userId);
			if(listKnowlwdge.size()>0){
				return listKnowlwdge;
			}else{
				return null;
			}
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getTeamNum(String roleName, Integer userId) {
		log.debug("getInventoryNum  instance");
		try {
		return	journalArticleDao.getInventoryNum(roleName,userId);
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<JournalArticle> getAllForApp(Integer userId, Integer size,String roleName) {
		log.debug("update JournalArticle instance");
		try {
			log.debug("getById successful");
			List<JournalArticle> journalArticles =journalArticleDao.getAllForApp(userId,size,roleName);
			return  journalArticles;
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<JournalArticle> getByNameForApp(Integer userId, String keyword,
			Integer size,String roleName) {
		log.debug("getById JournalArticle instance");
		try {
			log.debug("getById successful");
			return journalArticleDao.getByNameForApp(userId,keyword,size,roleName);
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getUpKnowledge() {
		log.debug("getUpKnowledge JournalArticle instance");
		List<Map<String, Object>> res=new ArrayList<Map<String,Object>>();
		try {
			res=journalArticleDao.getUpKnowledge();
		} catch (Exception e) {
			log.error("getUpKnowledge failed", e);
		}
		return res;
	}

	@Override
	public List<Map<String, Object>> getUpProject() {
		log.debug("getUpProject JournalArticle instance");
		List<Map<String, Object>> res=new ArrayList<Map<String,Object>>();
		try {
			res=journalArticleDao.getUpProject();
		} catch (Exception e) {
			log.error("getUpProject failed", e);
		}
		return res;
	}

	@Override
	public String getCrnter(Integer userId) {
		// TODO Auto-generated method stub
		log.debug("getUpKnowledge JournalArticle instance");
		String center="";
		try {
			center=journalArticleDao.getCrnter(userId);
		} catch (Exception e) {
			log.error("getCrnter failed", e);
		}
		return center;
	}

	@Override
	public void deletePro(int i, Integer id) {
		log.debug("deleting JournalArticle instance");
		try {
			journalArticleDao.deletePro(i,id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public void deleteReac(int i, Integer id) {
		log.debug("deleting JournalArticle instance");
		try {
			journalArticleDao.deleteReac(i,id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<String> getProRole(int proId, Integer userId) {
		// TODO Auto-generated method stub
				log.debug("getUpKnowledge JournalArticle instance");
				List<String> res=new ArrayList<String>();
				try {
					res=journalArticleDao.getProRole(proId,userId);
				} catch (Exception e) {
					log.error("getCrnter failed", e);
				}
				return res;
	}

	@Override
	public String getShare() {
		// TODO Auto-generated method stub
				log.debug("getUpKnowledge JournalArticle instance");
				String center="";
				try {
					List<Map<String, Object>> list=journalArticleDao.getShare();
					if(list!=null&&list.size()>0){
						center=String.valueOf(list.get(0).get("name"));
					}
				} catch (Exception e) {
					log.error("getCrnter failed", e);
				}
				return center;
	}

	@Override
	public int getDeviceAppointment(String roleName, Integer userId) {
		log.debug("getInventoryNum  instance");
		try {
		return	journalArticleDao.getDeviceAppointment(roleName,userId);
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public int getJournalNum(Integer userId, String roleName,Integer angency) {
		log.debug("getInventoryNum  instance");
		try {
		return	journalArticleDao.getJournalNum(userId,roleName,angency);
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getHotSearch() {
		log.debug("finding all Approvel instances");
		try {
			return journalArticleDao.getHotSearch();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getExpertNameCh() {
		log.debug("finding all Approvel instances");
		try {
			return journalArticleDao.getExpertNameCh();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	@Override
	public List<Map<String, String>> getExpertNameEn() {
		log.debug("finding all Approvel instances");
		try {
			return journalArticleDao.getExpertNameEn();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getKnowledgeFirst(String roleName,
			Integer userId) {
		List<Map<String, Object>> listKnowlwdge=new ArrayList<Map<String,Object>>();
		try {
			listKnowlwdge=journalArticleDao.getKnowledgeFirst(roleName,userId);
			if(listKnowlwdge.size()>0){
				return listKnowlwdge;
			}else{
				return null;
			}
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		// TODO Auto-generated method stub
	}

	@Override
	public int getKnowledgeNum(String searchName,Integer userId, String roleName) {
		log.debug("getInventoryNum  instance");
		try {
		return	journalArticleDao.getKnowledgeNum(searchName,userId,roleName);
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getUserJourKnowList() {
		log.debug("finding all Approvel instances");
		try {
			return journalArticleDao.getUserJourKnowList();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getUserSelfList() {
		log.debug("finding all Approvel instances");
		try {
			return journalArticleDao.getUserSelfList();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getUserPatentList() {
		log.debug("finding all Approvel instances");
		try {
			return journalArticleDao.getUserPatentList();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getUserNoteList() {
		log.debug("finding all Approvel instances");
		try {
			return journalArticleDao.getUserNoteList();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getByHotSearch(String keyword,
			Integer userId, String roleName) {
		log.debug("finding all Approvel instances");
		try {
			return journalArticleDao.getByHotSearch(keyword,userId,roleName);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<JournalArticle> getAllForPerson(String expertName) {
	
			log.debug("finding all Approvel instances");
			try {
				return journalArticleDao.getAllForPerson(expertName);
			} catch (RuntimeException re) {
				log.error("find all failed", re);
				throw re;
			}
	}

	@Override
	public Map<String, Object> getExpertById(Integer id) {
		log.debug("finding all Approvel instances");
		try {
			return journalArticleDao.getExpertById(id);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<JournalArticle> getAllForPersonByName(String keyword,String expertName) {
		log.debug("finding all Approvel instances");
		try {
			return journalArticleDao.getAllForPersonByName(keyword,expertName);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<JournalArticle> getAllByExpert(Integer userId, String roleName,
			String name,Integer angency) {
		try {
			return journalArticleDao.getAllByExpert(userId,roleName,name,angency);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<JournalArticle> getAllByExpertWord(Integer userId,
			String roleName, String name,String keyword,Integer angency) {
		try {
			return journalArticleDao.getAllByExpertWord(userId,roleName,name,keyword,angency);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<JournalArticle> getByFiledId(Integer userId, Integer filedId,
			String roleName,Integer angency) {
		try {
			return journalArticleDao.getByFiledId(userId,filedId,roleName,angency);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<JournalArticle> getByAll(Integer userId, Integer filedId,
			String roleName, String keyword,Integer angency) {
		try {
			return journalArticleDao.getByAll(userId,filedId,roleName,keyword,angency);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<JournalArticle> getAllByTitleAndExpert(Integer userId,
			String roleName, int angency, String keyword) {
		try {
			return journalArticleDao.getAllByTitleAndExpert(userId,roleName,angency,keyword);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getAllBase(Integer userId, String roleName,String keyword) {
		try {
			return journalArticleDao.getAllBase(userId,roleName,keyword);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getAllPerson(Integer userId,
			String roleName, int angency, String keyword) {
		try {
			return journalArticleDao.getAllPerson(userId,roleName,angency,keyword);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getAllBaseByFiledId(Integer userId,
			String roleName, String keyword, Integer filedId) {
		try {
			return journalArticleDao.getAllBaseByFiledId(userId,roleName,keyword,filedId);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getAllPersonByFiledId(Integer userId,
			String roleName, int angency, String keyword, Integer filedId) {
		try {
			return journalArticleDao.getAllPersonByFiledId(userId,roleName,angency,keyword,filedId);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getExpertName() {
		log.debug("finding all Approvel instances");
		try {
			return journalArticleDao.getExpertName();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getUserExpert() {
		log.debug("finding all Approvel instances");
		try {
			return journalArticleDao.getUserExpert();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<Integer> getAllIds(Integer userId, String roleName,
			Integer agencyId) {
		log.debug("update JournalArticle instance");
		try {
			log.debug("getById successful");
			List<Integer> journalArticles =journalArticleDao.getAllIds(userId,roleName,agencyId);
			return  journalArticles;
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getAllKnowlist(Integer userId,
			String roleName, String keyword) {
		try {
			return journalArticleDao.getAllKnowlist(userId,roleName,keyword);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getAllBaseKnowByFiledId(Integer userId,
			String roleName, String keyword, Integer filedId) {
		try {
			return journalArticleDao.getAllBaseKnowByFiledId(userId,roleName,keyword,filedId);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> findPort(String webPort) {
		try {
			return journalArticleDao.findPort(webPort);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getAgencyContents(int angencyId) {
		try {
			return journalArticleDao.getAgencyContents(angencyId);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public int journalBaseNum() {
		try {
			return journalArticleDao.journalBaseNum();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public int journalSlefNum(Integer userId, int angency) {
		try {
			return journalArticleDao.journalSlefNum(userId,angency);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	@Override
	public int journalAgencyNum(Integer userId, int angency) {
		try {
			return journalArticleDao.journalAgencyNum(userId,angency);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<JournalArticle> getByKnowledgeFieldId(Integer id) {
		try {
			return journalArticleDao.getByKnowledgeFieldId(id);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getProductUserList() {
		try {
			return journalArticleDao.getProductUserList();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getUserByKeywordList(String keyword) {
		try {
			return journalArticleDao.getUserByKeywordList(keyword);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<JournalArticle> getAgencyJournalArticle(Integer userId,
			String roleName, int angency) {
		try {
			return journalArticleDao.getAgencyJournalArticle(userId,roleName,angency);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<JournalArticle> getBaseJournalArticle(Integer userId,
			String roleName, int angency) {
		try {
			return journalArticleDao.getBaseJournalArticle(userId,roleName,angency);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getSelfKnowledge(Integer userId,
			Integer agencyId,String keyword) {
		try {
			return journalArticleDao.getSelfKnowledge(userId,agencyId,keyword);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getAllPersonKnowlist(Integer userId,
			String roleName, String keyword, Integer filedId) {
		try {
			return journalArticleDao.getAllPersonKnowlist(userId,roleName,keyword,filedId);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getSelfKnowledgeForApp(Integer userId,
			Integer agencyId, String keyword) {
		try {
			return journalArticleDao.getSelfKnowledgeForApp(userId,agencyId,keyword);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getKnowledgeFirstBase() {
		try {
			return journalArticleDao.getKnowledgeFirstBase();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getAllJournals(Integer userId,
			String roleName, String keyword) {
		try {
			return journalArticleDao.getAllJournals(userId,roleName,keyword);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getAllSelf(Integer userId,
			String roleName, String keyword) {
		try {
			return journalArticleDao.getAllSelf(userId,roleName,keyword);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getAllPatent(Integer userId,
			String roleName, String keyword) {
		try {
			return journalArticleDao.getAllPatent(userId,roleName,keyword);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public Map<String, Object> getAgencyContentsById(int angencyId) {
		try {
			return journalArticleDao.getAgencyContentsById(angencyId);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public Map<String, Object> getStaticFile() {
		try {
			return journalArticleDao.getStaticFile();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public Map<String, Object> getAgencyPic(String pageUrl) {
		try {
			return journalArticleDao.getAgencyPic(pageUrl);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public JournalArticle getByAccId(Integer knowledgeAccId) {
		try {
			return journalArticleDao.getByAccId(knowledgeAccId);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public int getMaterialNum(Integer cid) {
		try {
			return journalArticleDao.getMaterialNum(cid);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public int getPersonKnoledgeNum(Integer userId) {
		try {
			return journalArticleDao.getPersonKnoledgeNum(userId);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

}

package com.labwinner.service;

import java.util.List;
import java.util.Map;

import com.labwinner.domain.JournalArticle;
/**
 * @Description 专利Service
 * @author wangll
 * @version V1.0
 * @date 2017年6月28日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public interface JournalArticleService {
	
	public void save(JournalArticle journalArticle);
	
	public void update(JournalArticle journalArticle);
	
	public void delete(Integer id);
	
	public JournalArticle getById(Integer id);
	
	public List<JournalArticle> getByName(Integer id,String keyword,String roleName,Integer angency);
	
	public List<JournalArticle> getAll(Integer id,String roleName,Integer angency);
	
	public void saveUser(Integer id,List<String> userId);

	public List<JournalArticle> saveByBatch(List<Map> lsit);

	public Boolean getResult(String issn, String title, String author,
			String writeDate, Integer userId, Integer secure);

	public List<Map<String, Object>> getKnowledge(String roleName, Integer userId);

	public int getTeamNum(String roleName, Integer userId);

	public List<JournalArticle> getAllForApp(Integer userId, Integer size,String roleName);

	public List<JournalArticle> getByNameForApp(Integer userId, String keyword,
			Integer size,String roleName);

	public List<Map<String, Object>> getUpKnowledge();

	public List<Map<String, Object>> getUpProject();

	public String getCrnter(Integer userId);

	public void deletePro(int i, Integer id);

	public void deleteReac(int i, Integer id);

	public List<String> getProRole(int proId, Integer userId);

	public String getShare();

	public int getDeviceAppointment(String roleName, Integer userId);

	public int getJournalNum(Integer userId, String roleName,Integer angency);

	public List<Map<String, Object>> getHotSearch();

	public List<Map<String, Object>> getExpertNameCh();

	public List<Map<String, String>> getExpertNameEn();

	public List<Map<String, Object>> getKnowledgeFirst(String roleName,
			Integer userId);

	public int getKnowledgeNum(String searchName,Integer userId, String roleName);

	public List<Map<String, Object>> getUserJourKnowList();

	public List<Map<String, Object>> getUserSelfList();

	public List<Map<String, Object>> getUserPatentList();

	public List<Map<String, Object>> getUserNoteList();

	public List<Map<String, Object>> getByHotSearch(String keyword,
			Integer userId, String roleName);

	public List<JournalArticle> getAllForPerson(String expertName);

	public Map<String, Object> getExpertById(Integer id);

	public List<JournalArticle> getAllForPersonByName(String keyword,String expertName);

	public List<JournalArticle> getAllByExpert(Integer userId, String roleName,
			String name,Integer angency);

	public List<JournalArticle> getAllByExpertWord(Integer userId,
			String roleName, String name,String keyword,Integer angency);

	public List<JournalArticle> getByFiledId(Integer userId, Integer filedId,
			String roleName,Integer angency);

	public List<JournalArticle> getByAll(Integer userId, Integer filedId,
			String roleName, String keyword,Integer angency);

	public List<JournalArticle> getAllByTitleAndExpert(Integer userId,
			String roleName, int angency, String keyword);

	public List<Map<String, Object>> getAllBase(Integer userId, String roleName,String keyword);

	public List<Map<String, Object>> getAllPerson(Integer userId,
			String roleName, int angency, String keyword);

	public List<Map<String, Object>> getAllBaseByFiledId(Integer userId,
			String roleName, String keyword, Integer filedId);

	public List<Map<String, Object>> getAllPersonByFiledId(Integer userId,
			String roleName, int angency, String keyword, Integer filedId);

	public List<Map<String, Object>> getExpertName();

	public List<Map<String, Object>> getUserExpert();

	public List<Integer> getAllIds(Integer userId, String roleName,
			Integer agencyId);

	public List<Map<String, Object>> getAllKnowlist(Integer userId,
			String roleName, String keyword);

	public List<Map<String, Object>> getAllBaseKnowByFiledId(Integer userId,
			String roleName, String keyword, Integer filedId);

	public List<Map<String, Object>> findPort(String webPort);

	public List<Map<String, Object>> getAgencyContents(int angencyId);

	public int journalBaseNum();

	public int journalSlefNum(Integer userId, int angency);

	public int journalAgencyNum(Integer userId, int angency);

	public List<JournalArticle> getByKnowledgeFieldId(Integer id);

	public List<Map<String, Object>> getProductUserList();

	public List<Map<String, Object>> getUserByKeywordList(String keyword);

	public List<JournalArticle> getAgencyJournalArticle(Integer userId,
			String roleName, int angency);

	public List<JournalArticle> getBaseJournalArticle(Integer userId,
			String roleName, int angency);

	public List<Map<String, Object>> getSelfKnowledge(Integer userId,
			Integer agencyId,String keyword);

	public List<Map<String, Object>> getAllPersonKnowlist(Integer userId,
			String roleName, String keyword, Integer filedId);

	public List<Map<String, Object>> getSelfKnowledgeForApp(Integer userId,
			Integer agencyId, String keyword);

	public List<Map<String, Object>> getKnowledgeFirstBase();

	public List<Map<String, Object>> getAllJournals(Integer userId,
			String roleName, String keyword);

	public List<Map<String, Object>> getAllSelf(Integer userId,
			String roleName, String keyword);

	public List<Map<String, Object>> getAllPatent(Integer userId,
			String roleName, String keyword);

	public Map<String, Object> getAgencyContentsById(int angencyId);

	public Map<String, Object> getStaticFile();

	public Map<String, Object> getAgencyPic(String pageUrl);

	public JournalArticle getByAccId(Integer knowledgeAccId);

	public int getMaterialNum(Integer cid);

	public int getPersonKnoledgeNum(Integer userId);
}

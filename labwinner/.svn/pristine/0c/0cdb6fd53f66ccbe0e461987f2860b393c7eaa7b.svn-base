package com.labwinner.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.JournalArticle;

public interface JournalArticleDao {
	
	public void save(JournalArticle journalArticle);
	
	public void update(JournalArticle journalArticle);
	
	public void delete(Integer id);
	
	public JournalArticle getById(Integer id);
	
	public List<JournalArticle> getByName(@Param("id")Integer id,
			@Param("keyword")String keyword,@Param("roleName")String roleName,@Param("angency")Integer angency);
	
	public List<JournalArticle> getAll(@Param("id")Integer id,@Param("roleName")String roleName,@Param("angency")Integer angency);

	public void saveUser(Integer id,List<String> userId);

	public List<JournalArticle> getResult(@Param("issn")String issn, @Param("title")String title, @Param("author")String author,
			@Param("userId") Integer userId ,@Param("writeDate")String writeDate, @Param("secure")Integer secure);

	public List<Map<String, Object>> getKnowledge(@Param("roleName")String roleName,@Param("userId") Integer userId);

	public int getInventoryNum(@Param("roleName")String roleName, @Param("userId")Integer userId);

	public List<JournalArticle> getAllForApp(@Param("userId")Integer userId, @Param("size")Integer size,@Param("roleName")String roleName);

	public List<JournalArticle> getByNameForApp(@Param("userId")Integer userId, @Param("keyword") String keyword,
			@Param("size") Integer size,@Param("roleName")String roleName);

	public List<Map<String, Object>> getUpKnowledge();

	public List<Map<String, Object>> getUpProject();

	public String getCrnter(Integer userId);

	public void deletePro(@Param("i")Integer i,@Param("id") Integer id);

	public void deleteReac(@Param("i")Integer i,@Param("id") Integer id);

	public List<String> getProRole(@Param("proId")int proId, @Param("userId")Integer userId);

	public List<Map<String, Object>> getShare();

	public int getDeviceAppointment(@Param("roleName")String roleName, @Param("userId")Integer userId);

	public int getJournalNum(@Param("userId")Integer userId, @Param("roleName")String roleName,@Param("angency")Integer angency);

	public List<Map<String, Object>> getHotSearch();

	public List<Map<String, Object>> getExpertNameCh();

	public List<Map<String, String>> getExpertNameEn();

	public List<Map<String, Object>> getKnowledgeFirst(@Param("roleName")String roleName,
			@Param("userId") Integer userId);

	public int getKnowledgeNum(@Param("searchName")String searchName,@Param("userId")Integer userId, @Param("roleName")String roleName);

	public List<Map<String, Object>> getUserJourKnowList();

	public List<Map<String, Object>> getUserSelfList();

	public List<Map<String, Object>> getUserPatentList();

	public List<Map<String, Object>> getUserNoteList();

	public List<Map<String, Object>> getByHotSearch(@Param("keyword")String keyword,
			@Param("userId")Integer userId, @Param("roleName")String roleName);

	public List<JournalArticle> getAllForPerson(String expertName);

	public Map<String, Object> getExpertById(Integer id);

	public List<JournalArticle> getAllForPersonByName(@Param("keyword")String keyword,@Param("expertName")String expertName);

	public List<JournalArticle> getAllByExpert(@Param("userId")Integer userId, @Param("roleName")String roleName,
			@Param("name")String name,@Param("angency")Integer angency);

	public List<JournalArticle> getAllByExpertWord(@Param("userId")Integer userId,
			@Param("roleName")String roleName,@Param("name") String name,@Param("keyword") String keyword,@Param("angency")Integer angency);

	public List<JournalArticle> getByFiledId(@Param("userId")Integer userId, @Param("filedId")Integer filedId,
			@Param("roleName")String roleName,@Param("angency")Integer angency);

	public List<JournalArticle> getByAll(@Param("userId")Integer userId, @Param("filedId")Integer filedId,
			@Param("roleName")String roleName, @Param("keyword")String keyword,@Param("angency")Integer angency);

	public List<JournalArticle> getAllByTitleAndExpert( @Param("userId")Integer userId,
			 @Param("roleName")String roleName,  @Param("angency")int angency,  @Param("keyword")String keyword);

	public List<Map<String, Object>> getAllBase( @Param("userId")Integer userId,
			@Param("roleName")String roleName, @Param("keyword")String keyword);

	public List<Map<String, Object>> getAllPerson(@Param("userId")Integer userId,
			@Param("roleName")String roleName, @Param("angency")int angency, @Param("keyword")String keyword);

	public List<Map<String, Object>> getAllBaseByFiledId(@Param("userId")Integer userId,
			@Param("roleName")String roleName,@Param("keyword") String keyword, @Param("filedId")Integer filedId);

	public List<Map<String, Object>> getAllPersonByFiledId(@Param("userId")Integer userId,
			@Param("roleName")String roleName, @Param("angency")int angency, @Param("keyword")String keyword,@Param("filedId") Integer filedId);

	public List<Map<String, Object>> getExpertName();

	public List<Map<String, Object>> getUserExpert();

	public List<Integer> getAllIds(@Param("userId")Integer userId, @Param("roleName")String roleName,
			@Param("agencyId")Integer agencyId);

	public List<Map<String, Object>> getAllKnowlist(@Param("userId")Integer userId,
			@Param("roleName")String roleName, @Param("keyword")String keyword);

	public List<Map<String, Object>> getAllBaseKnowByFiledId(@Param("userId")Integer userId,
			@Param("roleName")String roleName, @Param("keyword")String keyword, @Param("filedId")Integer filedId);

	public List<Map<String, Object>> findPort(String webPort);

	public List<Map<String, Object>> getAgencyContents(int angencyId);

	public int journalBaseNum();

	public int journalSlefNum(@Param("userId")Integer userId, @Param("angency")int angency);

	public int journalAgencyNum(@Param("userId")Integer userId, @Param("angency")int angency);

	public List<JournalArticle> getByKnowledgeFieldId(Integer id);

	public List<Map<String, Object>> getProductUserList();

	public List<Map<String, Object>> getUserByKeywordList(String keyword);

	public List<JournalArticle> getAgencyJournalArticle(@Param("userId")Integer userId,
			@Param("roleName")String roleName, @Param("angency")int angency);

	public List<JournalArticle> getBaseJournalArticle(@Param("userId")Integer userId,
			@Param("roleName")String roleName, @Param("angency")int angency);

	public List<Map<String, Object>> getSelfKnowledge(@Param("userId")Integer userId,
			@Param("agencyId")Integer agencyId,@Param("keyword")String keyword);

	public List<Map<String, Object>> getAllPersonKnowlist(@Param("userId")Integer userId,
			@Param("roleName")String roleName, @Param("keyword")String keyword, @Param("filedId")Integer filedId);

	public List<Map<String, Object>> getSelfKnowledgeForApp(@Param("userId")Integer userId,
			@Param("agencyId")Integer agencyId, @Param("keyword")String keyword);

	public List<Map<String, Object>> getKnowledgeFirstBase();

	public List<Map<String, Object>> getAllJournals(@Param("userId")Integer userId,
			@Param("roleName")String roleName, @Param("keyword")String keyword);

	public List<Map<String, Object>> getAllSelf(@Param("userId")Integer userId,
			@Param("roleName")String roleName,@Param("keyword") String keyword);

	public List<Map<String, Object>> getAllPatent(@Param("userId")Integer userId,
			@Param("roleName")String roleName, @Param("keyword")String keyword);

	public Map<String, Object> getAgencyContentsById(int angencyId);

	public Map<String, Object> getStaticFile();

	public Map<String, Object> getAgencyPic(String pageUrl);

	public JournalArticle getByAccId(Integer knowledgeAccId);

	public int getMaterialNum(Integer cid);

	public int getPersonKnoledgeNum(Integer userId);

}

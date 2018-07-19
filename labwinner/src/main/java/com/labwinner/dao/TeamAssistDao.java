package com.labwinner.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.SysUser;
import com.labwinner.domain.TeamAssist;

public interface TeamAssistDao {
	
	public void save(TeamAssist teamAssist);
	
	public void update(TeamAssist teamAssist);
	
	public void delete(Integer id);
	
	public void deleteByProcessId(Integer id);
	
	public void batchRemove(List<Integer> ids);
	
	public TeamAssist getById(Integer id);

	public List<Map<String, String>> getByProcessId(Integer processId);

}

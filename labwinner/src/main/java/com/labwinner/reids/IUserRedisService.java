package com.labwinner.reids;

import java.util.List;
import java.util.Set;

import com.labwinner.domain.SysUser;

public interface IUserRedisService {
//	 public void saveUsers(List<SysUser> users);
	 public List<String> findAll();
//	 public SysUser findById(String id);
	 public void deleteById(String id);
	 
	 public void saveUsersToken(String userId,String token);
	 public String findUsersToken(String userId);
	 public void putUsersToken(String userId,String token);
}

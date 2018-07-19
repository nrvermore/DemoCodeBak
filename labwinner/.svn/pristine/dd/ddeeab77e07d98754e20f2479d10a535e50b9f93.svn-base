package com.labwinner.reids;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.labwinner.configuration.IRedisService;
import com.labwinner.domain.SysUser;



@Service
public class UserRedisServiceImpl extends IRedisService<SysUser> implements IUserRedisService{
    
    private static final String USER_REDIS_KEY = "USER_REDIS_KEY";
    
    @Override
    protected String getRedisKey() {
        return USER_REDIS_KEY;
    }

//    @Override
//    public void saveUsers(List<SysUser> users) {
//        for (SysUser user : users) {
//            put(String.valueOf(user.getUserId()), user, -1);
//        }
//    }
//
    @Override
    public List<String> findAll() {
        return  getAllToken();
    }
//
//    @Override
//    public SysUser findById(String id) {
//        return get(id);
//    }
//
    @Override
    public void deleteById(String id) {
        remove(id);
    }

	@Override
	public void saveUsersToken(String userId,String token) {
		 putToken(userId, token, 3L);
	}

	@Override
	public String findUsersToken(String userId) {
	return	getToken(userId);
		
	}

	@Override
	public void putUsersToken(String userId, String token) {
		putExpire(String.valueOf(userId), token, 3L);
		
	}

	


}

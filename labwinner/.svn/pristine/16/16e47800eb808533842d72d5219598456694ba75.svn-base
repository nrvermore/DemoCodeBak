package com.labwinner.scienjus.authorization.manager.impl;


import com.labwinner.reids.IUserRedisService;
import com.labwinner.scienjus.authorization.manager.TokenManager;
import com.labwinner.scienjus.authorization.model.TokenModel;
import com.labwinner.scienjus.config.Constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 通过Redis存储和验证token的实现类
 * @see com.scienjus.authorization.manager.TokenManager
 * @author zwl
 */
@Component
public class RedisTokenManager implements TokenManager {
	
    
	@Autowired  
	private IUserRedisService iUserRedisService;


    public TokenModel createToken(String userId,String sessionId) {
        //使用uuid作为源token
        //String token = UUID.randomUUID().toString().replace("-", "");
    	//String token = session.getId();
        TokenModel model = new TokenModel(userId, sessionId);
        //存储到redis并设置过期时间
        //redis.boundValueOps(String.valueOf(userId)).set(token, Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        iUserRedisService.saveUsersToken(userId,sessionId);
        return model;
    }

    public TokenModel getToken(String authentication) {
        if (authentication == null || authentication.length() == 0) {
            return null;
        }
//        String[] param = authentication.split(":");
//        if (param.length != 2) {
//            return null;
//        }
        //使用userId和源token简单拼接成的token，可以增加加密措施
//        String userId = param[0];
//        String token = param[1];
        return new TokenModel(authentication, authentication);
    }

    public boolean checkToken(TokenModel model) {
        if (model == null) {
            return false;
        }
        //String token = redis.boundValueOps(String.valueOf(model.getUserId())).get();
        String token =iUserRedisService.findUsersToken(model.getUserId());
        if (token == null || !token.equals(model.getToken())) {
            return false;
        }
        //如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
       // redis.boundValueOps(String.valueOf(model.getUserId())).expire(Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        iUserRedisService.putUsersToken(model.getUserId(),model.getToken());
        return true;
    }

    public void deleteToken(long userId) {
    	iUserRedisService.deleteById(String.valueOf(userId));
    }
}

package com.oracle.ssm.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.oracle.ssm.model.User;
@Component
public class JedisUtil {
	@Autowired
	private  RedisTemplate jedis;
	
	public  User getUserBySession(HttpServletRequest request) {
		String userKey="session:"+request.getRemoteAddr();
		System.out.println("com.oracle.ssm.util JedisUtil.getUserBySession:");
		System.out.println("userKey="+userKey);
		User user=(User)jedis.getHashValueSerializer().deserialize((byte[])(jedis.boundValueOps(userKey).get()));
		return user;
	}

}

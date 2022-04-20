package com.oracle.ssm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.ssm.model.User;
import com.oracle.ssm.service.UserService;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private UserService userService;
	
	@Autowired
	@Qualifier("redisTemplate")  
	private RedisTemplate jedis;
	
	@Autowired
	private JedisConnectionFactory jedisFactory;
	/**
	 * ǰ����¼ҳ��
	 * @return
	 */
	@RequestMapping("/toLogin")
	public String toLogin() {
		return "index/login";
	}
	
	@RequestMapping("/login")
	public void login(@RequestBody User user,HttpServletRequest request,HttpServletResponse response) throws Exception {
		System.out.println("loginUser="+user);
		user=userService.searchUserByLoginNameAndPassword(user);
		if(user!=null) {
			//��session
			//����ͨ���������redis��ģ��tomcat��session,session���� 
			String host=request.getRemoteAddr();
			String key="session:"+host;
			System.out.println("key="+key);
			byte[] bytes=jedis.getHashValueSerializer().serialize(user);
			jedis.boundValueOps(key).set(bytes);
			response.getWriter().write("1");
		}else {
			response.getWriter().write("0");
		}
		
	}

}

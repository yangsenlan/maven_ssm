package com.oracle.ssm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.oracle.ssm.model.User;

public class RedisSessionInterceptor  implements HandlerInterceptor{
	@Autowired
	@Qualifier("redisTemplate") 
	private RedisTemplate jedis;

	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		request.setAttribute("path", request.getContextPath());
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	/**
	 * session����
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		String url=request.getRequestURI();
		if(url.contains("login")||url.contains("shop")||url.contains("goods")) {
			return true;
		}else {
			String ip=request.getRemoteAddr();
			String key="session:"+ip;
			
			
			if(jedis.hasKey(key)) {
				byte[] bytes=(byte[]) jedis.opsForValue().get(key);
				User user=(User) jedis.getHashValueSerializer().deserialize(bytes);
				return true;
			}else {
				response.sendRedirect(request.getContextPath()+"/login/toLogin");
				return false;
			}
				
		}
		
	}

}

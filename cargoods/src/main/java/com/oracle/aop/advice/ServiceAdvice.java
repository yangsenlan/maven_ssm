package com.oracle.aop.advice;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.oracle.ssm.model.SysLog;
import com.oracle.ssm.model.User;
import com.oracle.ssm.service.SysLogService;

/**
 * @author xiaogaotongxue:
 * @version ����ʱ�䣺2019��8��1�� ����1:50:06
 * 
 */
@Component("ServiceAdvice")
public class ServiceAdvice {
	@Autowired
	private SysLogService sysLogService;
	@Autowired
	@Qualifier("redisTemplate") 
	private RedisTemplate jedis;

	public void after(JoinPoint joinpoint, Object object) {
		System.out.println(object);
		System.out.println("�ύ����");
		// ȡ������
		String methodName = joinpoint.getSignature().getName();
		String tableName = ""; // ����

		int operate_type = 0;// ��������

		if (methodName.contains("add")) {
			tableName = methodName.substring("add".length());
			operate_type = 1;

		}
		if (methodName.contains("insert")) {
			tableName = methodName.substring("insert".length());
			operate_type = 1;

		}
		if (methodName.contains("delete")) {
			tableName = methodName.substring("delete".length(), methodName.indexOf("By"));
			operate_type = 2;
		}
		if (methodName.contains("update")) {
			tableName = methodName.substring("update".length(), methodName.indexOf("By"));
			operate_type = 3;
		}
		// ����
		Object[] arrays = joinpoint.getArgs();
		StringBuffer rs = new StringBuffer();
		String paramClass;
		for (Object object2 : arrays) {
			// ȡ������������
			paramClass = object2.getClass().getName();
			paramClass = paramClass.substring(paramClass.lastIndexOf(".") + 1);
			rs.append("[����������]" + paramClass + ",ֵ:(id:" + joinpoint.getArgs()[0] + ")");

		}

		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = attr.getRequest();
		byte[] bytes=(byte[]) jedis.boundValueOps("session:"+request.getRemoteAddr()).get();
		User user=(User) jedis.getHashValueSerializer().deserialize(bytes);
		
		String json=JSON.toJSONString(object);

		SysLog sysLog = new SysLog();
		sysLog.setCreateTime(new Date());
		sysLog.setOperateType(operate_type);
		sysLog.setParam(rs.toString());
		sysLog.setTableName(tableName);
		sysLog.setResult(json);

		sysLog.setUserId(user.getId());
		System.err.println("user" + user.getLoginName() + "Id" + user.getId());
		System.err.println("methodName" + methodName);
		System.err.println("table" + tableName);
		System.err.println("param����" + rs.toString());
		System.err.println("��������" + operate_type);
		System.err.println("result"+json);
		// System.err.println("��ȡ����ֵ" + object.toString());
		sysLogService.insertSysLog(sysLog);

	}

}

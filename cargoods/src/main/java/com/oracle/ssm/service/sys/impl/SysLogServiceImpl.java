package com.oracle.ssm.service.sys.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.ssm.mapper.SysLogMapper;
import com.oracle.ssm.model.SysLog;
import com.oracle.ssm.service.SysLogService;

import javax.annotation.Resource;

@Service
public class SysLogServiceImpl implements SysLogService {
	@Autowired
	private SysLogMapper sysLogMapper;

	@Override
	public void insertSysLog(SysLog log) {
		// TODO Auto-generated method stub
		sysLogMapper.insertSelective(log);
	}

}

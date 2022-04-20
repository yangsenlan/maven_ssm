package com.oracle.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.ssm.mapper.UserMapper;
import com.oracle.ssm.model.User;
import com.oracle.ssm.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public void insertUserByUser(User user) {
		// TODO Auto-generated method stub
		 userMapper.insertSelective(user);
	}

	@Override
	public User searchUserByLoginNameAndPassword(User user) {
		// TODO Auto-generated method stub
		return userMapper.searchUserByLoginNameAndPassword(user);
	}

}

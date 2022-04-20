package com.oracle.ssm.service;

import com.oracle.ssm.model.User;

public interface UserService {
	public void insertUserByUser(User user);
	public User searchUserByLoginNameAndPassword(User user);

}

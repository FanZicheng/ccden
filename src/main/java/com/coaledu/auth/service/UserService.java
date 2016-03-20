package com.coaledu.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coaledu.auth.iservice.IUserService;
import com.coaledu.auth.mapper.UserMapper;
import com.coaledu.auth.model.User;

@Service("userService")
public class UserService implements IUserService {
	
	private UserMapper userMapper;
	
	
	public UserMapper getUserMapper() {
		return userMapper;
	}
	
	@Autowired
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
	public void addUser(User user) {
		userMapper.addUser(user);
	}

	@Override
	public void deleteUserById(Integer id) {
		userMapper.deleteUserById(id);
	}

	@Override
	public void updateUser(User user) {
		userMapper.updateUser(user);
	}

	@Override
	public User getUserById(Integer id) {
		return userMapper.getUserById(id);
	}

}

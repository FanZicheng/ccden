package com.coaledu.auth.mapper;

import com.coaledu.auth.model.User;

public interface UserMapper {

	int addUser(User user);
    
    int deleteUserById(Integer id);

    int updateUser(User user);
    
    User getUserById(Integer id);
}
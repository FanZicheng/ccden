package com.coaledu.auth.iservice;

import com.coaledu.auth.model.User;

public interface IUserService {
	
	public void addUser(User user);
    
    public void deleteUserById(Integer id);

    public void updateUser(User user);
    
    public User getUserById(Integer id);
}

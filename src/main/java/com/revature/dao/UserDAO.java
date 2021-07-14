package com.revature.dao;

import java.util.List;

import com.revature.models.User;

public interface UserDAO {
	List<User> findAllUsers();
	User findUname(String uName);
	boolean updateUser(User user);

}

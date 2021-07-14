package com.revature.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class UserDAOImpl implements UserDAO {

	@Override
	public List<User> findAllUsers() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM user_info";
		
			Statement statement = conn.createStatement();
		
			ResultSet result = statement.executeQuery(sql);
		
			List<User> list = new ArrayList<>();
		
		//Result sets have a cursor similarly to Scanner or other I/O classes.
			while(result.next()) {
				User user = new User();
				user.setFirstName(result.getString("first_name"));
				user.setLastName(result.getString("last_name"));
				user.setUserName(result.getString("user_name"));
				user.setAddress(result.getString("address"));
				user.setPhoneNumber(result.getString("phone_number"));
				user.setEmail(result.getString("email"));
				list.add(user);
			}
		
			return list;
		
		}catch(SQLException e) {
				e.printStackTrace();
		}
		return null;
	}

	@Override
	public User findUname(String uName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

}

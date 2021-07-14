package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.Account;
import com.revature.utils.ConnectionUtil;

public class AccountDAOImpl implements AccountDAO {

	@Override
	public boolean addAccount(Account account) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "INSERT INTO USER_INFO (user_name, pass_word, first_name, last_name, access_type)"
					+ "VALUES(?, ?, ?, ?, ?);";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int index = 0;
			
			statement.setString(++index, account.getuName());
			statement.setString(++index, account.getpWord());
			statement.setString(++index, account.getFname());
			statement.setString(++index, account.getLname());
			statement.setString(++index, account.getAccessType());
			statement.execute();
			
			if(account.getAccessType().equals("customer"))
			{
			String sqlBank = "INSERT INTO BANK_ACCOUNT (u_name, p_word, has_checkings, has_savings)"
					+ "VALUES(?, ?, ?, ?);";
			
			PreparedStatement statementBank = conn.prepareStatement(sqlBank);
			
			index = 0;
			
			statementBank.setString(++index, account.getuName());
			statementBank.setString(++index, account.getpWord());
			statementBank.setBoolean(++index, account.isHas_checkings());
			statementBank.setBoolean(++index, account.isHas_savings());
			statementBank.execute();
			}
			return true;
				
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean checkUser(String uName) {
		
try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "SELECT * FROM user_info WHERE user_name = ?";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, uName);
			
			ResultSet result = statement.executeQuery();
			
			if (result.next()) {
				return true;
			}
			else
				return false;
				
				
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
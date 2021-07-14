package com.revature.dao;

import com.revature.models.Account;

public interface AccountDAO {
	public boolean addAccount(Account account);
	public boolean checkUser(String uName);
}

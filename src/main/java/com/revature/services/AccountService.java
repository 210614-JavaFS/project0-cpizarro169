package com.revature.services;

import com.revature.dao.AccountDAO;
import com.revature.dao.AccountDAOImpl;
import com.revature.models.Account;

public class AccountService {

	private static AccountDAO accountDAO = new AccountDAOImpl();
		
	public boolean addAccount(Account account) {
		return accountDAO.addAccount(account);
	}
	
	public boolean checkUser(String uName) {
		return accountDAO.checkUser(uName);
	}
	
}

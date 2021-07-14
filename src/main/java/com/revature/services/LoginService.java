package com.revature.services;

import com.revature.dao.LoginDAO;
import com.revature.dao.LoginDAOImpl;

public class LoginService {
	
	private static LoginDAO loginDAO = new LoginDAOImpl();
	
	public String loginCheck(String uName, String pWord) {
		return loginDAO.loginCheck(uName, pWord);
	}
	
	public boolean makeDeposit(String uName, double amount) {
		return loginDAO.makeDeposit(uName, amount);
	}
	public boolean makeWithdrawal(String uName, double amount) {
		return loginDAO.makeWithdrawal(uName, amount);
	}
	
	public boolean makeTransfer(String uName, double amount, int accountNumber) {
		return loginDAO.makeTransfer(uName, amount, accountNumber);
	}
	
	public boolean approveAccountEmployee() {
		return loginDAO.approveAccountEmployee();
	}
	
	public boolean approveAccountAdmin() {
		return loginDAO.approveAccountAdmin();
	}
	
	public boolean checkAccountEmployee(String uName) {
		return loginDAO.checkAccountEmployee(uName);
	}
	
	public boolean checkInfoEmployee(String uName) {
		return loginDAO.checkInfoEmployee(uName);
	}
	
	public boolean deleteAccount(String uName) {
		return loginDAO.deleteAccount(uName);
	}
	
	public boolean editInformation(String uName) {
		return loginDAO.editInformation(uName);
	}

}

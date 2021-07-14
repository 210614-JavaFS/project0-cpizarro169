package com.revature.dao;

public interface LoginDAO {
	public String loginCheck(String uName, String pWord);
	
	public boolean makeDeposit(String uName, double amount); 

	public boolean makeWithdrawal(String uName, double amount); 
	
	public boolean makeTransfer(String uName, double amount, int accountNumber);
	
	public boolean approveAccountEmployee();
	
	public boolean approveAccountAdmin();
	
	public boolean checkAccountEmployee(String uName);
	
	public boolean checkInfoEmployee(String uName);
	
	public boolean deleteAccount(String uName);
	
	public boolean editInformation(String uName);

}

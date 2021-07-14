package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.utils.ConnectionUtil;

public class LoginDAOImpl implements LoginDAO {

	@Override
	public String loginCheck(String uName, String pWord) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT access_type, approval_status FROM user_info WHERE user_name = ? AND pass_word = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, uName);
			statement.setString(2, pWord);
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				if(result.getInt(2) == 2)
				{
					String accessType = result.getString(1);
					System.out.println("Login successful");
					return accessType;
				}
				else if(result.getInt(2) == 1)
				{
					System.out.println("Your account was denied, please call for further assistance");
					return "failure";
				}
				else
				{
					System.out.println("Account is still awaiting approval");
					return "failure";
				}
			}
				
			else {
				System.out.println("Username or password didn't match");
				return "failure";
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return "failure";
	}

	@Override
	public boolean makeDeposit(String uName, double amount) {
		try(Connection conn = ConnectionUtil.getConnection()){
			Scanner sc = new Scanner(System.in);
			int whichAccount;
			boolean trigger = true;
			System.out.println("Deposit to\n1:Checkings\n2:Savings");
			whichAccount = sc.nextInt();
			while(trigger)
			switch(whichAccount) {
			case 1:
				String sqlCheckings = "SELECT has_checkings FROM bank_account WHERE u_name = ?;";
				PreparedStatement statementCheckings = conn.prepareStatement(sqlCheckings);
				statementCheckings.setString(1, uName);
				ResultSet resultCheckings = statementCheckings.executeQuery();
				resultCheckings.next();
				if(resultCheckings.getBoolean(1))
				{
				String sql = "SELECT checkings_balance FROM bank_account WHERE u_name = ?;";
				//Scanner sc = new Scanner(System.in);
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1, uName);
				
				ResultSet result = statement.executeQuery();
				result.next();
				double currentBalance = result.getDouble(1);
				System.out.println("Current Balance is: $" + currentBalance);
				while(trigger)
				{
					if (amount <= 0.0)
					{
						System.out.println("The amount is not valid, please enter a valid amount");
						amount = sc.nextDouble();
					}
					else 
					{
						System.out.println("Ok we will be depoisiting: $" + amount);
						trigger = false;
					}
				}
				//sc.close();
				String sqlUpdate = "UPDATE bank_account SET checkings_balance = ? WHERE u_name = ?;";
				PreparedStatement statementUpdate = conn.prepareStatement(sqlUpdate);
				statementUpdate.setDouble(1, currentBalance+amount);
				statementUpdate.setString(2, uName);
				statementUpdate.executeUpdate();
				System.out.println("Deposit successful\nNew Balance is: $" + (currentBalance+amount));
				return true;
				}
				else
				{
					System.out.println("You don't have a checkings account");
					return false;
				}
			case 2:
				String sqlSavings = "SELECT has_savings FROM bank_account WHERE u_name = ?;";
				PreparedStatement statementSavings = conn.prepareStatement(sqlSavings);
				statementSavings.setString(1, uName);
				ResultSet resultSavings = statementSavings.executeQuery();
				resultSavings.next();
				if(resultSavings.getBoolean(1))
				{
				String sql = "SELECT savings_balance FROM bank_account WHERE u_name = ?;";
				//Scanner sc = new Scanner(System.in);
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1, uName);
				
				ResultSet result = statement.executeQuery();
				result.next();
				double currentBalance = result.getDouble(1);
				System.out.println("Current Balance is: $" + currentBalance);
				while(trigger)
				{
					if (amount <= 0.0)
					{
						System.out.println("The amount is not valid, please enter a valid amount");
						amount = sc.nextDouble();
					}
					else 
					{
						System.out.println("Ok we will be depoisiting: $" + amount);
						trigger = false;
					}
				}
				//sc.close();
				String sqlUpdate = "UPDATE bank_account SET savings_balance = ? WHERE u_name = ?;";
				PreparedStatement statementUpdate = conn.prepareStatement(sqlUpdate);
				statementUpdate.setDouble(1, currentBalance+amount);
				statementUpdate.setString(2, uName);
				statementUpdate.executeUpdate();
				System.out.println("Deposit successful\nNew Balance is: $" + (currentBalance+amount));
				return true;
				}
				else
				{
					System.out.println("You don't have a savings account");
					return false;
				}
				
			default:
				System.out.println("Choose a valid option");
				}
				
				
			}
			
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean makeWithdrawal(String uName, double amount) {
		try(Connection conn = ConnectionUtil.getConnection()){
			Scanner sc = new Scanner(System.in);
			int whichAccount;
			boolean trigger = true;
			System.out.println("Withdraw from\n1:Checkings\n2:Savings");
			whichAccount = sc.nextInt();
			while(trigger)
			switch(whichAccount) {
			case 1:
				String sqlCheckings = "SELECT has_checkings FROM bank_account WHERE u_name = ?;";
				PreparedStatement statementCheckings = conn.prepareStatement(sqlCheckings);
				statementCheckings.setString(1, uName);
				ResultSet resultCheckings = statementCheckings.executeQuery();
				resultCheckings.next();
				if(resultCheckings.getBoolean(1))
				{
				String sql = "SELECT checkings_balance FROM bank_account WHERE u_name = ?;";
				//Scanner sc = new Scanner(System.in);
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1, uName);
				
				ResultSet result = statement.executeQuery();
				result.next();
				double currentBalance = result.getDouble(1);
				System.out.println("Current Balance is: $" + currentBalance);
				while(trigger)
				{
					if (amount <= 0.0)
					{
						System.out.println("The amount is not valid, please enter a valid amount");
						amount = sc.nextDouble();
					}
					else if (amount > currentBalance)
					{
						System.out.println("Sorry, we don't do overdrafts.");
						return false;
					}
					else 
					{
						System.out.println("Ok we will be withdrawing: $" + amount);
						trigger = false;
					}
				}
				//sc.close();
				String sqlUpdate = "UPDATE bank_account SET checkings_balance = ? WHERE u_name = ?;";
				PreparedStatement statementUpdate = conn.prepareStatement(sqlUpdate);
				statementUpdate.setDouble(1, currentBalance-amount);
				statementUpdate.setString(2, uName);
				statementUpdate.executeUpdate();
				System.out.println("Deposit successful\nNew Balance is: $" + (currentBalance-amount));
				return true;
				}
				else
				{
					System.out.println("You don't have a checkings account");
					return false;
				}
			case 2:
				String sqlSavings = "SELECT has_savings FROM bank_account WHERE u_name = ?;";
				PreparedStatement statementSavings = conn.prepareStatement(sqlSavings);
				statementSavings.setString(1, uName);
				ResultSet resultSavings = statementSavings.executeQuery();
				resultSavings.next();
				if(resultSavings.getBoolean(1))
				{
				String sql = "SELECT savings_balance FROM bank_account WHERE u_name = ?;";
				//Scanner sc = new Scanner(System.in);
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1, uName);
				
				ResultSet result = statement.executeQuery();
				result.next();
				double currentBalance = result.getDouble(1);
				System.out.println("Current Balance is: $" + currentBalance);
				while(trigger)
				{
					if (amount <= 0.0)
					{
						System.out.println("The amount is not valid, please enter a valid amount");
						amount = sc.nextDouble();
					}
					else if (amount > currentBalance)
					{
						System.out.println("Sorry, we don't do overdrafts.");
						return false;
					}
					else 
					{
						System.out.println("Ok we will be withdrawing: $" + amount);
						trigger = false;
					}
				}
				//sc.close();
				String sqlUpdate = "UPDATE bank_account SET savings_balance = ? WHERE u_name = ?;";
				PreparedStatement statementUpdate = conn.prepareStatement(sqlUpdate);
				statementUpdate.setDouble(1, currentBalance-amount);
				statementUpdate.setString(2, uName);
				statementUpdate.executeUpdate();
				System.out.println("Deposit successful\nNew Balance is: $" + (currentBalance-amount));
				return true;
				}
				else
				{
					System.out.println("You don't have a savings account");
					return false;
				}
				
			default:
				System.out.println("Choose a valid option");
				}
				
				
			}
			
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public boolean makeTransfer(String uName, double amount, int accountNumber) {
		try(Connection conn = ConnectionUtil.getConnection()){
			Scanner sc = new Scanner(System.in);
			int whichAccount;
			boolean trigger = true;
			String sqlAccount = "SELECT account_id,has_checkings FROM bank_account WHERE account_id = ?;";
			PreparedStatement statementAccount = conn.prepareStatement(sqlAccount);
			statementAccount.setString(1, uName);
			ResultSet resultAccount = statementAccount.executeQuery();
			if(resultAccount.next() && resultAccount.getBoolean(2))
			{
			System.out.println("Transfer from\n1:Checkings\n2:Savings");
			whichAccount = sc.nextInt();
			while(trigger)
			switch(whichAccount) {
			case 1:
				String sqlCheckings = "SELECT has_checkings FROM bank_account WHERE u_name = ?;";
				PreparedStatement statementCheckings = conn.prepareStatement(sqlCheckings);
				statementCheckings.setString(1, uName);
				ResultSet resultCheckings = statementCheckings.executeQuery();
				resultCheckings.next();
				if(resultCheckings.getBoolean(1))
				{
				String sql = "SELECT checkings_balance FROM bank_account WHERE u_name = ?;";
				//Scanner sc = new Scanner(System.in);
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1, uName);
				
				ResultSet result = statement.executeQuery();
				result.next();
				double currentBalance = result.getDouble(1);
				System.out.println("Current Balance is: $" + currentBalance);
				while(trigger)
				{
					if (amount <= 0.0)
					{
						System.out.println("The amount is not valid, please enter a valid amount");
						amount = sc.nextDouble();
					}
					else if (amount > currentBalance)
					{
						System.out.println("Sorry, we don't do overdrafts.");
						return false;
					}
					else 
					{
						System.out.println("Ok we will be withdrawing: $" + amount);
						trigger = false;
					}
				}
				//sc.close();
				String sqlUpdate = "UPDATE bank_account SET checkings_balance = ? WHERE u_name = ?;";
				PreparedStatement statementUpdate = conn.prepareStatement(sqlUpdate);
				statementUpdate.setDouble(1, currentBalance-amount);
				statementUpdate.setString(2, uName);
				statementUpdate.executeUpdate();
				System.out.println("Deposit successful\nNew Balance is: $" + (currentBalance-amount));
				return true;
				}
				else
				{
					System.out.println("You don't have a checkings account");
					return false;
				}
			case 2:
				String sqlSavings = "SELECT has_savings FROM bank_account WHERE u_name = ?;";
				PreparedStatement statementSavings = conn.prepareStatement(sqlSavings);
				statementSavings.setString(1, uName);
				ResultSet resultSavings = statementSavings.executeQuery();
				resultSavings.next();
				if(resultSavings.getBoolean(1))
				{
				String sql = "SELECT savings_balance FROM bank_account WHERE u_name = ?;";
				//Scanner sc = new Scanner(System.in);
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1, uName);
				
				ResultSet result = statement.executeQuery();
				result.next();
				double currentBalance = result.getDouble(1);
				System.out.println("Current Balance is: $" + currentBalance);
				while(trigger)
				{
					if (amount <= 0.0)
					{
						System.out.println("The amount is not valid, please enter a valid amount");
						amount = sc.nextDouble();
					}
					else if (amount > currentBalance)
					{
						System.out.println("Sorry, we don't do overdrafts.");
						return false;
					}
					else 
					{
						System.out.println("Ok we will be withdrawing: $" + amount);
						trigger = false;
					}
				}
				//sc.close();
				String sqlUpdate = "UPDATE bank_account SET savings_balance = ? WHERE u_name = ?;";
				PreparedStatement statementUpdate = conn.prepareStatement(sqlUpdate);
				statementUpdate.setDouble(1, currentBalance-amount);
				statementUpdate.setString(2, uName);
				statementUpdate.executeUpdate();
				System.out.println("Deposit successful\nNew Balance is: $" + (currentBalance-amount));
				return true;
				}
				else
				{
					System.out.println("You don't have a savings account");
					return false;
				}
				
			default:
				System.out.println("Choose a valid option");
				}
				
			}
			else
			{
				System.out.println("Account Number is invalid or Account doesnt have an active checkings account");
			}
			}
			
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;

	}
}

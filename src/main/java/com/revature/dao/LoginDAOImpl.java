package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.models.Account;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class LoginDAOImpl implements LoginDAO {
	private static Logger log = LoggerFactory.getLogger(LoginDAOImpl.class);

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
					log.info("Login successful");
					return accessType;
				}
				else if(result.getInt(2) == 1)
				{
					System.out.println("Your account was denied, please call for further assistance");
					log.warn("Your account was denied, please call for further assistance");
					return "failure";
				}
				else
				{
					System.out.println("Account is still awaiting approval");
					log.warn("Your account was denied, please call for further assistance");
					return "failure";
				}
			}
				
			else {
				System.out.println("Username or password didn't match");
				log.warn("Username or password didn't match");
				return "failure";
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			log.warn("Username or password not found");
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
						log.warn("The amount is not valid, please enter a valid amount");
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
				log.info("Deposit successful");
				return true;
				}
				else
				{
					System.out.println("You don't have a checkings account");
					log.warn("No checkings account available");
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
				log.info("Deposit successful");
				return true;
				}
				else
				{
					System.out.println("You don't have a savings account");
					log.warn("No savings account available");
					return false;
				}
				
			default:
				System.out.println("Choose a valid option");
				log.warn("invalid choice");
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
				System.out.println("Withdrawal successful\nNew Balance is: $" + (currentBalance-amount));
				log.info("Withdrawal successful");
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
						log.warn("Amount invalid");
						amount = sc.nextDouble();
					}
					else if (amount > currentBalance)
					{
						System.out.println("Sorry, we don't do overdrafts.");
						log.warn("Overdraft attempted");
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
				System.out.println("Withdrawal successful\nNew Balance is: $" + (currentBalance-amount));
				log.info("Withdrawal successful");
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
			//System.out.println("check 1");
			String sqlAccount = "SELECT account_id,has_checkings FROM bank_account WHERE account_id = ?;";
			PreparedStatement statementAccount = conn.prepareStatement(sqlAccount);
			statementAccount.setInt(1, accountNumber);
			ResultSet resultAccount = statementAccount.executeQuery();
			System.out.println("check 2");
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
						System.out.println("Ok we will be transferring: $" + amount);
						trigger = false;
					}
				}
				//sc.close();
				String sqlTarget = "SELECT checkings_balance FROM bank_account WHERE account_id = ?;";
				//Scanner sc = new Scanner(System.in);
				PreparedStatement statementTarget = conn.prepareStatement(sqlTarget);
				statementTarget.setInt(1, accountNumber);
				ResultSet resultTarget = statementTarget.executeQuery();
				resultTarget.next();
				double currentBalanceTarget = resultTarget.getDouble(1);
				System.out.println("Target has a balance of: " + currentBalanceTarget);
				String sqlUpdateOrigin = "UPDATE bank_account SET checkings_balance = ? WHERE u_name = ?;";
				PreparedStatement statementUpdateOrigin = conn.prepareStatement(sqlUpdateOrigin);
				statementUpdateOrigin.setDouble(1, currentBalance-amount);
				statementUpdateOrigin.setString(2, uName);
				statementUpdateOrigin.executeUpdate();
				String sqlUpdateTarget = "UPDATE bank_account SET checkings_balance = ? WHERE account_id = ?;";
				PreparedStatement statementUpdateTarget = conn.prepareStatement(sqlUpdateTarget);
				statementUpdateTarget.setDouble(1, currentBalanceTarget+amount);
				statementUpdateTarget.setInt(2, accountNumber);
				statementUpdateTarget.executeUpdate();
				System.out.println("Transfer successful\nNew Balance is: $" + (currentBalance-amount));
				log.info("Transfer successful");
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
				String sqlTarget = "SELECT savings_balance FROM bank_account WHERE account_id = ?;";
				//Scanner sc = new Scanner(System.in);
				PreparedStatement statementTarget = conn.prepareStatement(sqlTarget);
				statementTarget.setInt(1, accountNumber);
				
				ResultSet resultTarget = statement.executeQuery();
				resultTarget.next();
				double currentBalanceTarget = resultTarget.getDouble(1);
				String sqlUpdateOrigin = "UPDATE bank_account SET savings_balance = ? WHERE u_name = ?;";
				PreparedStatement statementUpdateOrigin = conn.prepareStatement(sqlUpdateOrigin);
				statementUpdateOrigin.setDouble(1, currentBalance-amount);
				statementUpdateOrigin.setString(2, uName);
				statementUpdateOrigin.executeUpdate();
				String sqlUpdateTarget = "UPDATE bank_account SET checkings_balance = ? WHERE u_name = ?;";
				PreparedStatement statementUpdateTarget = conn.prepareStatement(sqlUpdateTarget);
				statementUpdateTarget.setDouble(1, currentBalanceTarget+amount);
				statementUpdateTarget.setString(2, uName);
				statementUpdateTarget.executeUpdate();
				System.out.println("Transfer successful\nNew Balance is: $" + (currentBalanceTarget-amount));
				log.info("Transfer successful");
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

	@Override
	public boolean approveAccountEmployee() {
		try(Connection conn = ConnectionUtil.getConnection()){
		User user = new User();
		String sql = "SELECT * FROM user_info WHERE approval_status = 0 AND access_type = 'customer';";
		//Scanner sc = new Scanner(System.in);
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet result = statement.executeQuery();
		while(result.next())
		{
		user.setFirstName(result.getString("first_name"));
		user.setLastName(result.getString("last_name"));
		user.setUserName(result.getString("user_name"));
		user.setAddress(result.getString("address"));
		user.setPhoneNumber(result.getString("phone_number"));
		user.setEmail(result.getString("email"));
		System.out.println(user);
		}
		System.out.println("Which user do you want to focus on?");
		Scanner sc = new Scanner(System.in);
		String uName = sc.nextLine();
		if (uName.equals("back"))
			return true;
		String sqlTarget = "UPDATE user_info SET approval_status = ? WHERE user_name = ?";
		PreparedStatement statementTarget = conn.prepareStatement(sqlTarget);
		System.out.println("Do you want to:\n1: Approve\n2: Deny");
		int choice = sc.nextInt();
		if(choice == 1)
		{
			statementTarget.setInt(1, 2);
		}
		else if(choice == 2)
		{
			statementTarget.setInt(1, 1);
		}
		statementTarget.setString(2,uName);
		statementTarget.executeUpdate();
		System.out.println("Decision Successful");
		log.info("Decision successful");
		
		return true;
	}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
}

	@Override
	public boolean checkAccountEmployee(String uName) {
		try(Connection conn = ConnectionUtil.getConnection()){
			Account account = new Account();
			String sql = "SELECT * FROM bank_account WHERE u_name = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1,uName);
			ResultSet result = statement.executeQuery();
			if(result.next())
			{
			account.setuName(result.getString("u_name"));
			account.setpWord(result.getString("p_word"));
			account.setAccountID(result.getInt("account_id"));
			account.setHas_checkings(result.getBoolean("has_checkings"));
			account.setChecking_balance(result.getDouble("checkings_balance"));
			account.setHas_checkings(result.getBoolean("has_checkings"));
			account.setChecking_balance(result.getDouble("checkings_balance"));
			}
			System.out.println(account);
			
			return true;
		}
			catch(SQLException e) {
				e.printStackTrace();
			}
			return false;
	}

	@Override
	public boolean checkInfoEmployee(String uName) {
		try(Connection conn = ConnectionUtil.getConnection()){
			User user = new User();
			String sql = "SELECT * FROM user_info WHERE user_name = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1,uName);
			ResultSet result = statement.executeQuery();
			if(result.next())
			{
				user.setFirstName(result.getString("first_name"));
				user.setLastName(result.getString("last_name"));
				user.setUserName(result.getString("user_name"));
				user.setAddress(result.getString("address"));
				user.setPhoneNumber(result.getString("phone_number"));
				user.setEmail(result.getString("email"));
			}
			System.out.println(user);
			
			return true;
		}
			catch(SQLException e) {
				e.printStackTrace();
			}
			return false;
	}
	
	@Override
	public boolean editInformation(String uName) {
		try(Connection conn = ConnectionUtil.getConnection()){
			User user = new User();
			String sql = "SELECT * FROM user_info WHERE user_name = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1,uName);
			ResultSet result = statement.executeQuery();
			if(result.next())
			{
				user.setFirstName(result.getString("first_name"));
				user.setLastName(result.getString("last_name"));
				user.setUserName(result.getString("user_name"));
				user.setAddress(result.getString("address"));
				user.setPhoneNumber(result.getString("phone_number"));
				user.setEmail(result.getString("email"));
			}
			System.out.println("Your Current Information Is:");
			System.out.println(user);
			boolean trigger = true;
			Scanner sc = new Scanner(System.in);
			while(trigger) {
			System.out.println("Which information would you like to edit?\n1:First name\n2:Last name\n3:Address\n4:Phone number\n5:Email\n6:Exit");
			int choice = sc.nextInt();
			sc.nextLine();
			switch (choice)
			{
			case 1:
				System.out.println("Enter new first name");
				user.setFirstName(sc.nextLine());
				String sqlfName = "UPDATE user_info SET first_name = ? WHERE user_name = ?";
				PreparedStatement statementfName = conn.prepareStatement(sqlfName);
				statementfName.setString(1,user.getFirstName());
				statementfName.setString(2,uName);
				statementfName.executeUpdate();
				System.out.println("Update complete");
				return true;
			case 2:
				System.out.println("Enter new last name");
				user.setLastName(sc.nextLine());
				String sqllName = "UPDATE user_info SET last_name = ? WHERE user_name = ?";
				PreparedStatement statementlName = conn.prepareStatement(sqllName);
				statementlName.setString(1,user.getLastName());
				statementlName.setString(2,uName);
				statementlName.executeUpdate();
				System.out.println("Update complete");
				return true;
				
			case 3:
				System.out.println("Enter new address");
				user.setAddress(sc.nextLine());
				String sqlAddress = "UPDATE user_info SET address = ? WHERE user_name = ?";
				PreparedStatement statementAddress = conn.prepareStatement(sqlAddress);
				statementAddress.setString(1,user.getAddress());
				statementAddress.setString(2,uName);
				statementAddress.executeUpdate();
				System.out.println("Update complete");
				return true;
			case 4:
				System.out.println("Enter new phone number");
				user.setPhoneNumber(sc.nextLine());
				String sqlPhone = "UPDATE user_info SET phone_number = ? WHERE user_name = ?";
				PreparedStatement statementPhone = conn.prepareStatement(sqlPhone);
				statementPhone.setString(1,user.getPhoneNumber());
				statementPhone.setString(2,uName);
				statementPhone.executeUpdate();
				System.out.println("Update complete");
				return true;
			case 5:
				System.out.println("Enter new email");
				user.setEmail(sc.nextLine());
				String sqlEmail = "UPDATE user_info SET email = ? WHERE user_name = ?";
				PreparedStatement statementEmail = conn.prepareStatement(sqlEmail);
				statementEmail.setString(1,user.getEmail());
				statementEmail.setString(2,uName);
				statementEmail.executeUpdate();
				System.out.println("Update complete");
				return true;
			case 6:
				System.out.println("Returning");
				return true;
			default:
				System.out.println("Please choose a valid option");
				
			}
			}
		}
			catch(SQLException e) {
				e.printStackTrace();
			}
			return false;
	}

	@Override
	public boolean deleteAccount(String uName) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM user_info WHERE user_name = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1,uName);
			ResultSet result = statement.executeQuery();
			if(result.next())
			{
				System.out.println("User located and preparing for deletion");
			}
			else
				return false;
			String sqlBank = "DELETE FROM bank_account WHERE u_name =?";
			PreparedStatement statementbank = conn.prepareStatement(sqlBank);
			statementbank.setString(1,uName);
			statementbank.executeUpdate();
			String sqlUser = "DELETE FROM user_info WHERE user_name =?";
			PreparedStatement statementuser = conn.prepareStatement(sqlUser);
			statementuser.setString(1,uName);
			statementuser.executeUpdate();
			System.out.println("Deletion Completed");
			return true;
		}
			catch(SQLException e) {
				e.printStackTrace();
			}
			return false;
	}

	@Override
	public boolean approveAccountAdmin() {
		try(Connection conn = ConnectionUtil.getConnection()){
			User user = new User();
			String sql = "SELECT * FROM user_info WHERE approval_status = 0;";
			//Scanner sc = new Scanner(System.in);
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			while(result.next())
			{
			user.setFirstName(result.getString("first_name"));
			user.setLastName(result.getString("last_name"));
			user.setUserName(result.getString("user_name"));
			user.setAddress(result.getString("address"));
			user.setPhoneNumber(result.getString("phone_number"));
			user.setEmail(result.getString("email"));
			user.setAccessType(result.getString("access_type"));
			System.out.println(user);
			}
			System.out.println("Which user do you want to focus on?");
			Scanner sc = new Scanner(System.in);
			String uName = sc.nextLine();
			if (uName.equals("back"))
				return true;
			String sqlTarget = "UPDATE user_info SET approval_status = ? WHERE user_name = ?";
			PreparedStatement statementTarget = conn.prepareStatement(sqlTarget);
			System.out.println("Do you want to:\n1: Approve\n2: Deny");
			int choice = sc.nextInt();
			if(choice == 1)
			{
				statementTarget.setInt(1, 2);
			}
			else if(choice == 2)
			{
				statementTarget.setInt(1, 1);
			}
			statementTarget.setString(2,uName);
			statementTarget.executeUpdate();
			System.out.println("Decision Successful");
			log.info("Decision successful");
			
			return true;
		}
			catch(SQLException e) {
				e.printStackTrace();
			}
			return false;
	}
}

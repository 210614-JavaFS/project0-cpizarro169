package com.revature.controllers;

import java.util.Scanner;

import com.revature.models.Account;
import com.revature.services.AccountService;

public class AccountController {

	private static AccountService accountService = new AccountService();
	private static Scanner sc = new Scanner(System.in);
	
	public void createAccount() {
		boolean trigger = true;
		boolean checkings = false;
		boolean savings = false;
		Account account = new Account();
		System.out.println("Lets make your account\n"
				+ "Input your first name");
		String fName = sc.nextLine();
		System.out.println("Input your last name");
		String lName = sc.nextLine();
		System.out.println("Input your desired username");
		String uName = sc.nextLine();
		while (accountService.checkUser(uName))
		{
			System.out.println("Username is taken, try another one");
			uName = sc.nextLine();
		}
		trigger = true;
		System.out.println("Input your desired password");
		String pWord = sc.nextLine();
		String accessType = "";
		
		
		while(trigger) {
			System.out.println("Are you a\n1: A customer\n2: An employee\n3: An admin");
			int accessResult = sc.nextInt();
			switch (accessResult) {
				case 1:
					accessType = "customer";
					account.setFname(fName);
					account.setLname(lName);
					account.setuName(uName);
					account.setpWord(pWord);
					account.setAccessType(accessType);
					while(trigger)
					{
						System.out.println("Do you want\n1: A Checkings Account\n2: A Savings acount\n3: Both");
						int accountResult = sc.nextInt();
						switch (accountResult) {
						case 1:
							checkings = true;
							trigger = false;
							break;
						case 2:
							savings = true;
							trigger = false;
							break;
						case 3:
							savings = true;
							checkings = true;
							trigger = false;
							break;
						default:
							System.out.println("Please choose a valid option");
						}
					}
					account.setHas_checkings(checkings);
					account.setHas_savings(savings);
					break;
				case 2:
					trigger = false;
					accessType = "employee";
					account.setFname(fName);
					account.setLname(lName);
					account.setuName(uName);
					account.setpWord(pWord);
					account.setAccessType(accessType);
					break;
				case 3:
					trigger = false;
					accessType = "admin";
					account.setFname(fName);
					account.setLname(lName);
					account.setuName(uName);
					account.setpWord(pWord);
					account.setAccessType(accessType);
					break;
				default:
					System.out.println("That doesn't look right, lets try again.");
					
			}
		}
		

		
		
		if(accountService.addAccount(account)) {
			System.out.println("Your account was created successfully");
		}
		else {
			System.out.println("An error has occured");
		}
		sc.nextLine();
	}
}
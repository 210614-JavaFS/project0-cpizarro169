package com.revature.controllers;

import java.util.Scanner;

import com.revature.services.LoginService;

public class LoginController {
	private static LoginService loginService = new LoginService();
	private static Scanner sc = new Scanner(System.in);
	
	public void login() {
		
		System.out.println("Enter your username");
		String uName = sc.nextLine();
		
		System.out.println("Enter your password");
		String pWord = sc.nextLine();
		
		String loggedIn = loginService.loginCheck(uName, pWord);
		
		if(loggedIn.equals("failure")) {
			System.out.println("Going Back");
			return;
		}
		else if(loggedIn.equals("employee")) {
			System.out.println("Welcome Employee");
			//employeeOptions();
			return;
		}
		else if(loggedIn.equals("customer")) {
			System.out.println("Welcome Customer");
			customerOptions(uName);
			return;
		}
		else if(loggedIn.equals("admin")) {
			System.out.println("Welcome Admin");
			//adminOptions();
			return;
		}
	}
	
	public void customerOptions(String uName) {
		while(true) {
			System.out.println("Here are your options\n"
					+ "1:Deposit\n"
					+ "2:Withdrawal\n"
					+ "3:Transfer\n"
					+ "4:Update Information\n"
					+ "5:Logout");
			
			int response = sc.nextInt();
			double amount;
			
			switch(response) {
			case 1:
				System.out.println("How much would you like to deposit?");
				amount = sc.nextDouble();
				sc.nextLine();
				if (loginService.makeDeposit(uName, amount))
					{
						continue;
					}
				else 
					{
					System.out.println("Sorry about then error.");
					}
				break;
			case 2:
				System.out.println("How much would you like to withdraw?");
				amount = sc.nextDouble();
				sc.nextLine();
				if (loginService.makeWithdrawal(uName, amount))
					{
						continue;
					}
				else 
					{
					System.out.println("Sorry about then error.");
					}
				break;
			case 3:
				System.out.println("How much would you like to transfer?");
				amount = sc.nextDouble();
				sc.nextLine();
				System.out.println("Enter the account number you wish to transfer to:");
				int transferAccount = sc.nextInt();
				sc.nextLine();
				loginService.makeTransfer(uName, amount, transferAccount);
				break;
			case 4:
				//loginService.updateInformation();
				break;
			case 5:
				System.out.println("Thank you for choosing Rev Bank! Returning to the main menu");
				return;
			default:
				System.out.println("Please try again");
			}
		}
		
		
	}

}

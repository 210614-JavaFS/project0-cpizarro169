package com.revature;


//import java.sql.SQLException;
import java.util.Scanner;

//import com.revature.models.*;
import com.revature.controllers.AccountController;
import com.revature.controllers.LoginController;

public class Driver {

	private static AccountController accountController = new AccountController();
	private static LoginController loginController = new LoginController();
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args)  {
		System.out.println("Welcome to Rev Bank");
		
		while(true) {
			System.out.println("What would you like to do?\n 1: Make an account\n 2: Log in\n 3: Exit");
			int choice = sc.nextInt();
			
			switch(choice) {
				case 1:
					accountController.createAccount();
					break;
				case 2:
					loginController.login();
					break;
				case 3:
					System.out.println("Now exiting");
					System.exit(0);
			}
		}

	}

}
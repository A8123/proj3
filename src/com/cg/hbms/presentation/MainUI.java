package com.cg.hbms.presentation;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.PropertyConfigurator;

import com.cg.hbms.exceptions.HMSExceptions;
import com.cg.hbms.service.HotelServiceImpl;
import com.cg.hbms.service.IHotelService;

public class MainUI {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		PropertyConfigurator.configure("resources/log4j.properties");
		IHotelService service = new HotelServiceImpl();
		int choice = 0;
		int loginAttempts = 0;

		while (choice != 2 && loginAttempts < 3) {
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + "\n"
					+ "~~~ Welcome to CheapStays.com ~~~" + "\n"
					+ "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("Select your option");
			System.out.println("[1]. Login" + "\n" + "[2]. Exit");
			try {
				//choice = 1; 
				choice= scanner.nextInt();
			} catch (InputMismatchException e) {
				System.err.println("Enter digits only(1-2)");
			}
			switch (choice) {
			case 1:
				//scanner.next();
				System.out.print("UserName? ");
				//String userName = "Admin"; 
				//scanner.next();
				String userName = scanner.next();
				System.out.print("Password? ");
				//String password = "123"; 
				//scanner.next();
				String password = scanner.next();
				loginAttempts++;
				try {
					String role = service.getRole(userName,password);
					System.out.println(role);
					if(role.equals("Admin")){
						AdminConsole console = new AdminConsole();
						console.start(userName);
					}
					else if(role.equals("Employee")){
						EmployeeConsole console = new EmployeeConsole();
						console.start(userName);
					}
					
				} catch (HMSExceptions e) {
					System.err.println(e.getMessage());
				}
				System.exit(0);
			case 2:
				System.out.println("Thank You Visit Us Again");
				System.exit(0);
				break;
			default:
				System.exit(0);
				break;
			}

		}
		System.out.println("More than 3 attempts" + "\n" + "Exitting");
		System.exit(0);
	}

}

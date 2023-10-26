package main;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Login {
	Scanner sc = new Scanner(System.in);
	ArtistOperation artist;
	CustomerOperation customer;
	
	public Login() {
		artist = new ArtistOperation();
		customer = new CustomerOperation();
	}
	
	public void welcome() {
		System.out.println("\nWelcome to Order Management System of Art Gallery");
		System.out.println("=================================================");
		System.out.println("1. Admin User");
		System.out.println("2. Customer User");
		System.out.println("3. Exit");
		
		try {
			System.out.print("\nEnter option: ");
			int option = sc.nextInt();
			
			switch(option) {
				case 1: artistLogin(); break;
				case 2: customerLogin(); break;
				case 3: exit(); break;
				default: {
					System.out.println("*Input must be between 1 and 3. Please try again.");
					welcome();
				}
			}
		} catch(InputMismatchException ex) {
			System.out.println("*Input must be integer number. Please try again.");
			sc.nextLine();
			welcome();
		}
	}
	
	public void artistLogin() {
		sc.nextLine();
		System.out.print("\nEnter admin user name: ");
		String name = sc.nextLine();
		System.out.print("Enter admin user password: ");
		String password = sc.next();
		
		if(name.equals("admin") && password.equals("admin123")) {
			artist.welcome();
		} else {
			System.out.println("*Invalid username and password.");
			artistLogin();
		}
	}
	
	public void customerLogin() {
		sc.nextLine();
		System.out.print("\nEnter customer user name: ");
		String name = sc.nextLine();
		System.out.print("Enter customer user password: ");
		String password = sc.next();
		
		if(name.equals("customer") && password.equals("customer123")) {
			customer.welcome();
		} else {
			System.out.println("*Invalid username and password.");
			customerLogin();
		}
	}
	
	public void exit() {
		System.out.println("\nThank you for using Order Management System of Art Gallery");
		System.exit(0);
	}
}

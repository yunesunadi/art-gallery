package main;

import java.util.Scanner;

public class User {
	Scanner sc = new Scanner(System.in);
	
	public String enterNumber(String id, boolean idStatus, String str) {
		while(!idStatus) {
			try {
				System.out.print("Enter " + str + ": ");
				id = sc.nextLine();
				String val = Character.toUpperCase(str.charAt(0)) + str.substring(1);
				
				if(Utilities.checkNumber(id, val)) {
					idStatus = true;
				}
			} catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
		return id;
	}
	
	public String enterName(String name, boolean nameStatus, String user) {
		while(!nameStatus) {
			try {
				System.out.print("Enter " + user + " name: ");
				name = sc.nextLine();
				String val = Character.toUpperCase(user.charAt(0)) + user.substring(1);
				
				if(Utilities.checkString(name, val + " name")) {
					nameStatus = true;
				}
			} catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
		return name;
	}
	
	public String enterEmail(String email, boolean emailStatus, String user) {
		while(!emailStatus) {
			try {
				System.out.print("Enter " + user + " email: ");
				email = sc.next();
				String val = Character.toUpperCase(user.charAt(0)) + user.substring(1);
				if (Utilities.checkEmail(email, val + " email")) {
					emailStatus = true;
				}
			} catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
		return email;
	}
	
	public String enterAddress(String address, String user) {
		System.out.print("Enter " + user + " address: ");
		address = sc.nextLine();
		return address;
	}
	
	public String enterPhoneNumber(String phoneNumber, boolean phoneNumberStatus, String user) {
		while(!phoneNumberStatus) {
			try {
				System.out.print("Enter " + user + " phone number: ");
				phoneNumber = sc.nextLine();
				String val = Character.toUpperCase(user.charAt(0)) + user.substring(1);
				if (Utilities.checkPhoneNumber(phoneNumber, val + " phone number")) {
					phoneNumberStatus = true;
				}
			} catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
		return phoneNumber;
	}
	
	public void displayArtworkInfo(Artwork aw) {
		System.out.println("\nID: " + aw.getId());
		System.out.println("Name: " + aw.getName());
		System.out.println("Description: " + aw.getDescription());
		System.out.println("Price: " + aw.getPrice());
		System.out.println("Type: " + aw.getType());
		System.out.println("Category: " + aw.getCategory());
		System.out.println("Artist Name: " + aw.getArtist());
	}
	
	public void displaySoldOut(Artwork aw) {
		if(aw.isSoldOut()) {
			System.out.println("***Sold Out***");
		} else {
			System.out.println("***Available***");
		}
	}
	
	public void displayOrderedArtworkInfo(OrderedArtwork oa) {
		System.out.println("\nID: " + oa.getId());
		System.out.println("Customer Name: " + oa.getCustomer().getName());
		System.out.println("Artwork Name: " + oa.getArtwork());
		System.out.println("Ordered Date: " + oa.getOrderDate());		
	}
}
